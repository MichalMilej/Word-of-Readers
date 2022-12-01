function setReactionsTd(reactionsTd, opinionIndex, json) {
    reactionsTd.appendChild(document.createTextNode(json.content[opinionIndex].likes + " likes ")); 
    let likeBtn = document.createElement('input');
    likeBtn.classList.add("reactionBtn");
    likeBtn.type = "button";
    likeBtn.value = "+";
    reactionsTd.appendChild(likeBtn);

    reactionsTd.appendChild(document.createTextNode(" " + json.content[opinionIndex].dislikes + " dislikes "));
    let dislikeBtn = document.createElement('input');
    dislikeBtn.classList.add("reactionBtn");
    dislikeBtn.type = "button";
    dislikeBtn.value = "-";
    reactionsTd.appendChild(dislikeBtn);

    if (isUserLoggedIn()) {
        setReactionsButtons(likeBtn, dislikeBtn, json.content[opinionIndex].id);
    }
}

async function setReactionsButtons(likeBtn, dislikeBtn, opinionId) {
    likeBtn.style.display = 'inline';
    dislikeBtn.style.display = 'inline';

    let userId = localStorage.getItem("userId");

    let json = await requestGetUserReaction(opinionId, userId);
    let reactionId = json.id;
    let currentReaction = json.userReaction;

    if (currentReaction == "LIKE") {
        likeBtn.classList.add('likeReactionBtnClicked');
    } else if (currentReaction == "DISLIKE") {
        dislikeBtn.classList.add('dislikeReactionBtnClicked');
    }

    likeBtn.setAttribute("onclick", `reactionBtnClicked("${currentReaction}", "LIKE", "${reactionId}", "${opinionId}")`);
    dislikeBtn.setAttribute("onclick", `reactionBtnClicked("${currentReaction}", "DISLIKE", "${reactionId}", "${opinionId}")`);
}

async function requestGetUserReaction(opinionId, userId) {
    let response = await fetch(`http://localhost:8080/books/reviews/${opinionId}/reactions/user/${userId}`);
    return response.json();
}

async function reactionBtnClicked(currentReaction, clickedReaction, reactionId, opinionId) {
    let reaction;
    if (currentReaction == "LIKE" && clickedReaction == 'LIKE') {
        reaction = 'NONE';
    } else if (clickedReaction == 'LIKE') {
        reaction = 'LIKE';
    } else if (currentReaction == "DISLIKE" && clickedReaction == 'DISLIKE') {
        reaction = 'NONE';
    } else {
        reaction = 'DISLIKE';
    }

    // Send request to server
    let requestBody = {
        "userReaction": reaction
    }
    if (reactionId == "null") {
        let res = await requestPostReaction(opinionId, requestBody);
        if (res.status == 201) {
            window.location.reload();
        }
    } else {
        let res = await requestPatchReaction(reactionId, requestBody);
        if (res.ok) {
            window.location.reload();
        }
    }
}

function requestPostReaction(opinionId, body) {
    return fetch(`http://localhost:8080/books/reviews/${opinionId}/reactions`, {
        method: 'POST',
        headers: {
            'Authorization': `BASIC ${localStorage.getItem('authorization')}`,
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    });
}

function requestPatchReaction(reactionId, body) {
    return fetch(`http://localhost:8080/books/reviews/reactions/${reactionId}`, {
        method: 'PATCH',
        headers: {
            'Authorization': `BASIC ${localStorage.getItem('authorization')}`,
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    });
}