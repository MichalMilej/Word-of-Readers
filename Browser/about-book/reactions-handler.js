function setReactionsTd(reactionsTd, opinionIndex, json) {
    let optionalSpace = ',';
    if (isUserLoggedIn()) {
        optionalSpace = ' ';
    }
    let opinionId = json.content[opinionIndex].id;

    let likesSpan = document.createElement('span');
    likesSpan.textContent = json.content[opinionIndex].likes;
    likesSpan.id = "likesSpan" + opinionId;
    reactionsTd.appendChild(likesSpan);
    reactionsTd.appendChild(document.createTextNode(" likes" + optionalSpace));

    let likeBtn = document.createElement('input');
    likeBtn.classList.add("reactionBtn");
    likeBtn.type = "button";
    likeBtn.value = "+";
    likeBtn.id = "likeBtn" + opinionId;
    reactionsTd.appendChild(likeBtn);
    reactionsTd.appendChild(document.createTextNode(" "));

    let dislikesSpan = document.createElement('span');
    dislikesSpan.textContent = json.content[opinionIndex].dislikes;
    dislikesSpan.id = "dislikesSpan" + opinionId;
    reactionsTd.appendChild(dislikesSpan);
    reactionsTd.appendChild(document.createTextNode(" dislikes "));

    let dislikeBtn = document.createElement('input');
    dislikeBtn.classList.add("reactionBtn");
    dislikeBtn.type = "button";
    dislikeBtn.value = "-";
    dislikeBtn.id = "dislikeBtn" + opinionId;
    reactionsTd.appendChild(dislikeBtn);

    if (isUserLoggedIn()) {
        setReactionsButtons(likeBtn, dislikeBtn, opinionId);
    }
}

async function setReactionsButtons(likeBtn, dislikeBtn, opinionId) {
    likeBtn.style.display = 'inline';
    dislikeBtn.style.display = 'inline';

    let userId = localStorage.getItem("userId");

    let json = await requestGetUserReaction(opinionId, userId);
    let reactionId = json.id;
    let currentReaction = json.userReaction;

    likeBtn.classList.remove('likeReactionBtnClicked');
    dislikeBtn.classList.remove('dislikeReactionBtnClicked');
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
    let newReaction;
    if (currentReaction == "LIKE" && clickedReaction == 'LIKE') {
        newReaction = 'NONE';
    } else if (clickedReaction == 'LIKE') {
        newReaction = 'LIKE';
    } else if (currentReaction == "DISLIKE" && clickedReaction == 'DISLIKE') {
        newReaction = 'NONE';
    } else {
        newReaction = 'DISLIKE';
    }

    // Send request to server
    let requestBody = {
        "userReaction": newReaction
    }
    try {
        let res;
        if (reactionId == "null") {
            res = await requestPostReaction(opinionId, requestBody);
        } else {
            res = await requestPatchReaction(reactionId, requestBody);
        } 
        if (res.status == 201 || res.ok) {
            updateReactionsNumber(opinionId, currentReaction, newReaction);
            updateReactionBtn(opinionId, currentReaction, newReaction);
        }
    } catch(err) {
        console.log(err);
    }
}

function updateReactionsNumber(opinionId, currentReaction, newReaction) {
    let likesSpan = document.getElementById('likesSpan' + opinionId);
    let dislikesSpan = document.getElementById('dislikesSpan' + opinionId);
    if (currentReaction == 'LIKE') {
        likesSpan.textContent = (parseInt(likesSpan.textContent) - 1);
    } else if (newReaction == 'LIKE') {
        likesSpan.textContent = (parseInt(likesSpan.textContent) + 1);
    }
    if (currentReaction == 'DISLIKE') {
        dislikesSpan.textContent = (parseInt(dislikesSpan.textContent) - 1);
    } else if (newReaction == 'DISLIKE') {
        dislikesSpan.textContent = (parseInt(dislikesSpan.textContent) + 1);
    }
}

function updateReactionBtn(opinionId, currentReaction, newReaction) {
    let likeBtn = document.getElementById(`likeBtn${opinionId}`);
    let dislikeBtn = document.getElementById(`dislikeBtn${opinionId}`);

    setReactionsButtons(likeBtn, dislikeBtn, opinionId);
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