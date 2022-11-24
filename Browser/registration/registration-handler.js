async function registerUser() {
    let username = document.getElementById('username');
    let password1 = document.getElementById('password1');
    let password2 = document.getElementById('password2');
    let email = document.getElementById('email');

    let resultMessage = document.getElementById('submitResultInfo');
    resultMessage.innerHTML = "";
    if (!isUsernameOk(username.value)) {
        resultMessage.innerHTML = "Username is incorrect";
        return;
    }
    if (!arePasswordsEquals(password1.value, password2.value)) {
        resultMessage.innerHTML = "Passwords are not equals";
        return;
    }
    if (!isPasswordSafe(password1.value)) {
        resultMessage.innerHTML = "Password should be at least 8 characters long";
        return;
    }
    if (!isEmailOk(email.value)) {
        resultMessage.innerHTML = "E-mail address is incorrect";
        return;
    }

    user = {
        "username": username.value,
        "password": password1.value,
        "email": email.value
    }
    const response = await register(user);
    if (response.ok) {
        window.location.href="registration-successful/registration-successful.html";
    } else {
        resultMessage.innerHTML = "User not created. An error ocurred."
    }
}

async function register(user) {
    const response = await fetch("http://localhost:8080/users", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    return response;
}
