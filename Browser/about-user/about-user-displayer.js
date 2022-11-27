if (isUserLoggedIn()) {
    displayUserInfo();
}

function isUserLoggedIn() {
    if (localStorage.getItem("userId") == null 
        || localStorage.getItem("username") == null
        || localStorage.getItem("authorization") == null) {
            return false;
        }
    return true;
}

async function displayUserInfo() {
    let profilePhotoImg = document.getElementById('profilePhotoImg');
    let usernameTd = document.getElementById('usernameTd');
    let emailTd = document.getElementById('emailTd');
    let roleTd = document.getElementById('roleTd');

    try {
        const response = await sendGetUserInfoRequest(localStorage.getItem("userId"), localStorage.getItem("authorization"));
        const json = await response.json();

        profilePhotoImg.src = json.profilePhotoResponse.location;
        usernameTd.appendChild(document.createTextNode(json.username));
        emailTd.appendChild(document.createTextNode(json.email));
        roleTd.appendChild(document.createTextNode(json.userRole.toLowerCase()));
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