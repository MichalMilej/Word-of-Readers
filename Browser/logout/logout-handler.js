function logout() {
    localStorage.removeItem("userId")
    localStorage.removeItem("username");
    localStorage.removeItem("userRole");
    localStorage.removeItem("userProfilePhotoLocation");
    localStorage.removeItem("authorization");
    window.location.reload();
}