if (isUserLoggedIn()) {
    hideStandardPanel();
    loadUserInfo();
    displayUserPanel();

    if (isUserModOrAdmin()) {
        displayManagementBtn();
    }
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

function displayManagementBtn() {
    let managementBtn = document.getElementById('managementBtn');
    managementBtn.style.display = 'block';
}

function openManagementPage() {
    window.open("http://localhost:8080/api", "_blank").focus();
}