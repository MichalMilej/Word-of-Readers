let url = window.location.href;
const urlParams = new URLSearchParams(url.split("?")[1]);
let bookId = null;
if (urlParams.has("bookId")) {
    bookId = urlParams.get("bookId");
    displayBookTitle();
    displayUserInfo();
}

function displayBookTitle() {
    let bookTitleH = document.getElementById('bookTitleH');
    bookTitleH.appendChild(document.createTextNode(sessionStorage.getItem("bookTitle")));
}

function displayUserInfo() {
    let userProfilePhotoImg = document.getElementById('userProfilePhotoImg');
    userProfilePhotoImg.src = localStorage.getItem("userProfilePhotoLocation");
    let userInfoTd = document.getElementById('userInfoTd');
    userInfoTd.appendChild(document.createTextNode(localStorage.getItem("username")));
}

async function publishOpinion() {
    let opinionTextarea = document.getElementById('opinionTextarea');
    if (opinionTextarea.value.length == 0) {
        resultInfo.innerHTML = "Your opinion is empty.";
    }
    
    if (bookId != null && isUserLoggedIn()) {
        let resultInfo = document.getElementById('resultInfo');
        try {
            let body = {
                "text": opinionTextarea.value
            }
            let response = await sendPublishOpinionRequest(body);
            if (response.status == 201) {
                window.location.href = `../about-book.html?bookId=${bookId}`;
            } else {
                resultInfo.innerHTML = "Could not publish opinion.";
            }
        } catch (error) {
            resultInfo.innerHTML = "There was a problem with server connection.";
            console.log(error);
        }
    }
}
function sendPublishOpinionRequest(body) {
    return fetch(`http://localhost:8080/books/${bookId}/reviews`, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Basic ${localStorage.getItem('authorization')}`
        },
        body: JSON.stringify(body)
    })
}