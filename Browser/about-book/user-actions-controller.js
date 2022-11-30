if (isUserLoggedIn()) {
    let publishOpinionBtn = document.getElementById('publishOpinionBtn');
    publishOpinionBtn.style.display = "block";
}

function openPublishOpinionPage() {
    if (bookId != null) {
        window.location.href = `publish-opinion/publish-opinion.html?bookId=${bookId}`;
    }
}