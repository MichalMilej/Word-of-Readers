var opinionPageNumber = 0;

if (bookId != null) {
    loadAndDisplayOpinions(bookId, 0, 10);
}

async function loadAndDisplayOpinions(bookId, pageNumber, pageSize) {
    hideButtons();
    try {
        const response = await fetch(`http://localhost:8080/books/${bookId}/reviews?pageNumber=${pageNumber}&pageSize=${pageSize}`);
        const json = await response.json();
        let pageFull = displayOpinions(json, pageSize);
        if (pageFull == "pageFull") {
            setNextPageButtonDisplay("block");
        }
        if (opinionPageNumber > 0) {
            setPreviousPageButtonDisplay("block");
        }
    } catch(err) {
        console.error("There was a problem with retrieving and displaying book opinions from server.")
    }
}

function displayOpinions(json, pageSize) {
    let opinionsArea = document.getElementById("opinionsArea");
    opinionsArea.innerHTML = "";

    let opinionIndex = 0;
    for (opinion in json.content) {
        let opinionTable = document.createElement('table');
        opinionTable.classList.add('opinionTable');

        let opinionMetadataTr = document.createElement('tr');
        let textTr = document.createElement('tr');
        let reactionsTr = document.createElement('tr');

        let userTd = document.createElement('td');
        let textTd = document.createElement('td');
        let reactionsTd = document.createElement('td');

        displayProfilePhoto(userTd, opinionIndex, json);
        userTd.appendChild(document.createTextNode(` ${json.content[opinionIndex].userResponsePublic.username},
         ${json.content[opinionIndex].publicationDate}`));
        setDeleteOpinionBtn(userTd, opinionIndex, json);

        textTd.appendChild(document.createTextNode(json.content[opinionIndex].text));

        setReactionsTd(reactionsTd, opinionIndex, json);

        opinionMetadataTr.appendChild(userTd);
        textTr.appendChild(textTd);
        reactionsTr.appendChild(reactionsTd);

        opinionTable.appendChild(opinionMetadataTr);
        opinionTable.appendChild(textTr);
        opinionTable.appendChild(reactionsTr);

        opinionsArea.appendChild(opinionTable);
        opinionIndex++;
    }

    if (opinionIndex == pageSize) {
        return "pageFull";
    } else {
        return "pageNotFull";
    }
}

function displayProfilePhoto(td, opinionIndex, json) {
    let profilePhotoImg = document.createElement('img');
    profilePhotoImg.src = json.content[opinionIndex].userResponsePublic.profilePhotoResponse.location;
    td.appendChild(profilePhotoImg);
}

function setDeleteOpinionBtn(td, opinionIndex, json) {
    if (isUserLoggedIn() && (isUserModOrAdmin()
        || json.content[opinionIndex].userResponsePublic.id == localStorage.getItem("userId"))) {

        let deleteOpinionBtn = document.createElement('input');
        deleteOpinionBtn.type = "button";
        deleteOpinionBtn.classList.add("deleteOpinionBtn");
        deleteOpinionBtn.value = 'X';
        let opinionId = json.content[opinionIndex].id;
        deleteOpinionBtn.setAttribute("onclick", (`sendDeleteOpinionRequest("${opinionId}")`));

        td.appendChild(deleteOpinionBtn);
    }
}

async function sendDeleteOpinionRequest(opinionId) {
    let response = await fetch(`http://localhost:8080/books/reviews/${opinionId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Basic ${localStorage.getItem('authorization')}`
        }
    })
    if (response.ok) {
        console.log('reload');
        window.location.reload();
    }
}

function hideButtons() {
    setNextPageButtonDisplay("none");
    setPreviousPageButtonDisplay("none");
}

function setNextPageButtonDisplay(display) {
    let nextPageBtn = document.getElementById('nextPageBtn');
    nextPageBtn.style = `display: ${display};`;
}

function setPreviousPageButtonDisplay(display) {
    let previousPageBtn = document.getElementById('previousPageBtn');
    previousPageBtn.style = `display: ${display};`;
}

function loadNextOpinions() {
    opinionPageNumber++;
    loadAndDisplayOpinions(bookId, opinionPageNumber, 10);
}

function loadPreviousOpinions() {
    opinionPageNumber--;
    loadAndDisplayOpinions(bookId, opinionPageNumber, 10);
}

