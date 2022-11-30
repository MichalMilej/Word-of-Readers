function isUserLoggedIn() {
    if (localStorage.getItem("userId") == null 
        || localStorage.getItem("username") == null
        || localStorage.getItem("authorization") == null) {
            return false;
        }
    return true;
}

function isUserModOrAdmin() {
    return localStorage.getItem("userRole") == "MOD" 
    || localStorage.getItem("userRole") ==  "ADMIN";
}