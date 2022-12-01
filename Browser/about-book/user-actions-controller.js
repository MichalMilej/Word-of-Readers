if (isUserLoggedIn()) {
    let publishOpinionBtn = document.getElementById('publishOpinionBtn');
    publishOpinionBtn.style.display = "block";

    let loggedUserScoreTr = document.getElementById('loggedUserScoreTr');
    loggedUserScoreTr.style.display = "table-row";
}

function openPublishOpinionPage() {
    if (bookId != null) {
        window.location.href = `publish-opinion/publish-opinion.html?bookId=${bookId}`;
    }
}