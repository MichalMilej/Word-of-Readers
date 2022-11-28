function isUserLoggedIn() {
    if (localStorage.getItem("userId") == null 
        || localStorage.getItem("username") == null
        || localStorage.getItem("authorization") == null) {
            return false;
        }
    return true;
}