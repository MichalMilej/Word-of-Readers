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
    let loggedUserScoreSelect = document.getElementById('loggedUserScoreSelect');
    let requestBody = {
        "score": parseInt(loggedUserScoreSelect.value)
    }
    let userId = localStorage.getItem('userId');
    let userScoreRes;
    try {
        let getUserScoreRes = await requestGetUserScore(bookId, userId);
        let getUserScoreJson = await getUserScoreRes.json();

        if (getUserScoreJson.id != null && loggedUserScoreSelect.value == 'none') {
            userScoreRes = await requestDeleteUserScore(getUserScoreJson.id);
            if (userScoreRes.ok) {
                updateUserScoreCount(-1);
            }
        } else if (getUserScoreJson.id != null) {
            userScoreRes = await requestPatchUserScore(getUserScoreJson.id, requestBody);
        } else {
            userScoreRes = await requestPostUserScore(bookId, requestBody);
            if (userScoreRes.ok) {
                updateUserScoreCount(1);
            }
        }

        if (userScoreRes.ok) {
            displayUserScoreSavedMessage();
        }
    } catch(err) {
        console.log(err);
    }
}

function updateUserScoreCount(incrementValue) {
    let userScoreCountSpan = document.getElementById('userScoreCountSpan');
    userScoreCountSpan.textContent = (parseInt(userScoreCountSpan.textContent) + incrementValue);
}

function displayUserScoreSavedMessage() {
    let loggedUserScoreSelectInfo = document.getElementById('loggedUserScoreSelectInfo');
    loggedUserScoreSelectInfo.style.display = 'inline';

    window.setTimeout(() => {
        let loggedUserScoreSelectInfo = document.getElementById('loggedUserScoreSelectInfo');
        loggedUserScoreSelectInfo.style.display = 'none';
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