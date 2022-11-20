let url = window.location.href;
const urlParams = new URLSearchParams(url.split("?")[1]);
if (urlParams.has("bookId")) {
    let bookId = urlParams.get("bookId");
    loadAndDisplayBookData(bookId);
}

async function loadAndDisplayBookData(bookId) {
    try {
        const response = await fetch(`http://localhost:8080/books/${bookId}`);
        const jsonData = await response.json();
        displayBookData(jsonData);
    } catch(err) {
        console.error("There was a problem with retrieving and displaying book data from server.")
    }
}

function displayBookData(jsonData) {
    let coverImg = document.getElementById("coverImg");
    let titleTd = document.getElementById("titleTd");
    let authorsTd = document.getElementById("authorsTd");
    let publisherTd = document.getElementById("publisherTd");
    let releaseDateTd = document.getElementById("releaseDateTd");
    let userScoreAverageTd = document.getElementById("userScoreAverageTd");
    let userScoreCountTd = document.getElementById("userScoreCountTd");
    let descriptionP = document.getElementById("descriptionP");

    if (jsonData.coverResponse != null) {
        coverImg.src = jsonData.coverResponse.location;
    }
    titleTd.appendChild(document.createTextNode(jsonData.title));
    setAuthorsTd(authorsTd, jsonData);
    if (jsonData.publisherResponse != null) {
        publisherTd.appendChild(document.createTextNode(jsonData.publisherResponse.name));
    }
    releaseDateTd.appendChild(document.createTextNode(jsonData.releaseDate));
    if (jsonData.userScoreAverage != null) {
        userScoreAverageTd.appendChild(document.createTextNode(jsonData.userScoreAverage))
    }
    if (jsonData.userScoreCount != null) {
        userScoreCountTd.appendChild(document.createTextNode(jsonData.userScoreCount + " readers"))
    }
    descriptionP.appendChild(document.createTextNode(jsonData.description));
}

function setAuthorsTd(authorsTd, jsonData) {
    let i = 0;
    for (author in jsonData.authorResponses) {
        if (i != 0) {
            authorsTd.appendChild(document.createTextNode(",\n"));
        }
        authorsTd.appendChild(document.createTextNode(jsonData.authorResponses[i].firstName + " "));
        if (jsonData.authorResponses[i].secondName != null) {
            authorsTd.appendChild(document.createTextNode(jsonData.authorResponses[i].secondName + " "));
        }
        authorsTd.appendChild(document.createTextNode(jsonData.authorResponses[i].lastName));
        i++;
    }
}