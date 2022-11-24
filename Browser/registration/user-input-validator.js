function isUsernameOk(username) {
    if (username == null || username.length < 1 || username.includes(' ')) {
        return false;
    }
    return true;
}

function arePasswordsEquals(password1, password2) {
    if (password1 == null || password1 != password2) {
        return false;
    }
    return true;
}

function isPasswordSafe(password) {
    if (password.length < 8) {
        return false;
    }
    return true;
}

function isEmailOk(email) {
    if (email == null || email.length < 2 || !email.includes('@')) {
        return false;
    }
    return true;
}