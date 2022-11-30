async function login() {
    let submitResultP = document.getElementById('submitResultP');
    submitResultP.innerHTML = "";

    let usernameIn = document.getElementById('usernameIn');
    let passwordIn = document.getElementById('passwordIn');
    const username = usernameIn.value;
    const password = passwordIn.value;
    const authorization = btoa(`${username}:${password}`);

    if (username.length == 0 || password.length < 8) {
        submitResultP.innerHTML = "Incorrect username or password.";
        return;
    }

    try {
        const response = await sendLoginRequest(username, authorization);
        if (response.status == 401) {
            submitResultP.innerHTML = "Incorrect username or password.";
            return;
        }
        if (response.ok) {
            const json = await response.json();
            localStorage.setItem("userId", json.id)
            localStorage.setItem("username", username);
            localStorage.setItem("userProfilePhotoLocation", json.profilePhotoResponse.location);
            localStorage.setItem("userRole", json.userRole);
            localStorage.setItem("authorization", btoa(`${username}:${password}`));

            loadPreviousPage();
        }
    } catch(error) {
        submitResultP.innerHTML = "There was a problem with server connection.";
    }
}

function sendLoginRequest(username, authorization) {
    return fetch(`http://localhost:8080/users/username/${username}`, {
        headers: {
            'Authorization': `Basic ${authorization}`
        }
    });
}

function loadPreviousPage() {
    let lastPage = sessionStorage.getItem("lastPage");
    let location = "../about-user/about-user.html";
    switch(lastPage) {
        case "catalog":
            location = "../catalog/catalog.html";
        break;
        case "about-book":
            location = `../about-book/about-book.html?bookId=${sessionStorage.getItem("bookId")}`;
        break;
    }
    window.location.href = location;
}