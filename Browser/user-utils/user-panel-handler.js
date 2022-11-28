if (isUserLoggedIn()) {
    hideStandardPanel();
    displayUserPanel();
    console.log('done');
}

function hideStandardPanel() {
    let loginBtn = document.getElementById('loginBtn');
    let registrationBtn = document.getElementById('registrationBtn');

    loginBtn.style.display = "none";
    registrationBtn.style.display = "none";
}

function displayUserPanel() {
    let logoutBtn = document.getElementById('logoutBtn');
    logoutBtn.style.display = "block";
}