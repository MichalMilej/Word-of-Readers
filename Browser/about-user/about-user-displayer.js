if (isUserLoggedIn()) {
    displayUserInfo();
}

async function displayUserInfo() {
    let profilePhotoImg = document.getElementById('profilePhotoImg');
    let usernameSpan = document.getElementById('usernameSpan');
    let emailSpan = document.getElementById('emailSpan');
    let roleSpan = document.getElementById('roleSpan');

    try {
        const response = await sendGetUserInfoRequest(localStorage.getItem("userId"), localStorage.getItem("authorization"));
        const json = await response.json();

        profilePhotoImg.src = json.profilePhotoResponse.location;
        usernameSpan.textContent = json.username;
        emailSpan.textContent = json.email;
        roleSpan.textContent = json.userRole.toLowerCase();

        localStorage.setItem("userProfilePhotoLocation", json.profilePhotoResponse.location);
    } catch(error) {
        profilePhotoImg.innerHTML="There was a problem with server connection.";
    }
}

function sendGetUserInfoRequest(userId, authorization) {
    return fetch(`http://localhost:8080/users/${userId}`, {
        headers: {
            'Authorization': `Basic ${authorization}`
        }
    });
}

function switchToPreviousPage() {
    let lastPage = localStorage.getItem("lastPage");
    if (lastPage == "catalog") {
        window.location.href = "../catalog/catalog.html";
    } else {
        window.location.href = `../about-book/about-book.html?bookId=${localStorage.getItem('bookId')}`;
    }
}