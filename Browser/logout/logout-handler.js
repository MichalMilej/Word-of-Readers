function logout() {
    localStorage.removeItem("userId")
    localStorage.removeItem("username");
    localStorage.removeItem("authorization");
    window.location.reload();
}