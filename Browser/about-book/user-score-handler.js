if (isUserLoggedIn() && bookId != null) {
    let loggedUserScoreTr = document.getElementById('loggedUserScoreTr');
    loggedUserScoreTr.style.display = "table-row";
    loadLoggedUserScoreSelectValue();
}

async function loadLoggedUserScoreSelectValue() {
    let getUserScoreRes;
    try {
        getUserScoreRes = await requestGetUserScore(bookId, localStorage.getItem('userId'));
        if (getUserScoreRes.ok) {
            let json = await getUserScoreRes.json();
            let loggedUserScoreSelect = document.getElementById('loggedUserScoreSelect');
            loggedUserScoreSelect.value = json.score;
        }
    } catch(err) {
        console.log(getUserScoreRes.status);
        if (getUserScoreRes.status != 404) {
            console.log(err);
        }
    }
}

async function saveUserScore() {
    let selectedValue = document.getElementById('loggedUserScoreSelect').value;
    let userId = localStorage.getItem('userId');
    try {
        let currentUserScoreRes = await requestGetUserScore(bookId, userId);
        let currentUserScoreJson = await currentUserScoreRes.json();

        let resStatus;
        if (currentUserScoreJson.id != null && selectedValue == 'none') {
            resStatus = (await requestDeleteUserScore(currentUserScoreJson.id)).status;
        } else {
            let requestBody = {
                "score": parseInt(selectedValue)
            }
            if (currentUserScoreJson.id != null) {
                resStatus = (await requestPatchUserScore(currentUserScoreJson.id, requestBody)).status;
            } else {
                resStatus = (await requestPostUserScore(bookId, requestBody)).status;
            }
        }   

        if (resStatus == 200 || resStatus == 201) {
            displayUserScoreSavedMessage();
            updateScoreInfo(bookId);
        }
    } catch(err) {
        console.log(err);
    }
}

async function updateScoreInfo(bookId) {
    let bookRes = await requestGetBook(bookId);
    let json = await bookRes.json();

    setUserScoreAverageSpan(json);
    setUserScoreCountSpan(json);
}

function displayUserScoreSavedMessage() {
    let loggedUserScoreSelectInfoTr = document.getElementById('loggedUserScoreSelectInfoTr');
    loggedUserScoreSelectInfoTr.style.display = 'table-row';

    window.setTimeout(() => {
        let loggedUserScoreSelectInfoTr = document.getElementById('loggedUserScoreSelectInfoTr');
        loggedUserScoreSelectInfoTr.style.display = 'none';
    }, 2000);
}

function requestPostUserScore(bookId, body) {
    return fetch(`http://localhost:8080/books/${bookId}/user-scores`, {
        method: 'POST',
        headers: {
            'Authorization': `BASIC ${localStorage.getItem('authorization')}`,
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    })
}

function requestGetUserScore(bookId, userId) {
    return fetch(`http://localhost:8080/books/${bookId}/user-score/user/${userId}`);
}

function requestPatchUserScore(userScoreId, body) {
    return fetch(`http://localhost:8080/books/user-scores/${userScoreId}`, {
        method: 'PATCH',
        headers: {
            'Authorization': `BASIC ${localStorage.getItem('authorization')}`,
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    });
}

function requestDeleteUserScore(userScoreId) {
    return fetch(`http://localhost:8080/books/user-scores/${userScoreId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `BASIC ${localStorage.getItem('authorization')}`,
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
}