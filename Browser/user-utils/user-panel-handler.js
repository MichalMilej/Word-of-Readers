if (isUserLoggedIn()) {
    hideStandardPanel();
    loadUserInfo();
    displayUserPanel();
}

function hideStandardPanel() {
    let loginBtn = document.getElementById('loginBtn');
    let registrationBtn = document.getElementById('registrationBtn');

    loginBtn.style.display = "none";
    registrationBtn.style.display = "none";
}

function loadUserInfo() {
    let loggedUserUsernameP = document.getElementById('loggedUserUsernameP');
    loggedUserUsernameP.appendChild(document.createTextNode(localStorage.getItem('username')));
    let loggedUserImg = document.getElementById('loggedUserProfilePhotoImg');
    loggedUserImg.src = localStorage.getItem("userProfilePhotoLocation");
}

function displayUserPanel() {
    let loggedInSection = document.getElementById('loggedInSection');
    loggedInSection.style.display = "block";
}