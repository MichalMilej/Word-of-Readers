let url = window.location.href;
const urlParams = new URLSearchParams(url.split("?")[1]);
var bookId = null;
if (urlParams.has("bookId")) {
    bookId = urlParams.get("bookId");
    localStorage.setItem("lastPage", "about-book");
    localStorage.setItem("bookId", bookId);
    loadAndDisplayBookData(bookId);
}

async function loadAndDisplayBookData(bookId) {
    try {
        const response = await fetch(`http://localhost:8080/books/${bookId}`);
        const json = await response.json();
        displayBookData(json);
    } catch(err) {
        console.error("There was a problem with retrieving and displaying book data from server.")
    }
}

function displayBookData(json) {
    let coverImg = document.getElementById("coverImg");
    let titleTd = document.getElementById("titleTd");
    let authorsTd = document.getElementById("authorsTd");
    let publisherTd = document.getElementById("publisherTd");
    let releaseDateTd = document.getElementById("releaseDateTd");
    let genresTd = document.getElementById("genresTd");

    let userScoreAverageTd = document.getElementById("userScoreAverageTd");
    let userScoreCountTd = document.getElementById("userScoreCountTd");
    let descriptionP = document.getElementById("descriptionP");

    if (json.coverResponse != null) {
        coverImg.src = json.coverResponse.location;
    }
    titleTd.appendChild(document.createTextNode(json.title));
    sessionStorage.setItem("bookTitle", json.title);
    setAuthorsTd(authorsTd, json);
    if (json.publisherResponse != null) {
        publisherTd.appendChild(document.createTextNode(json.publisherResponse.name));
    }
    releaseDateTd.appendChild(document.createTextNode(json.releaseDate));
    setGenresTd(genresTd, json);
    if (json.userScoreAverage != null) {
        let userScoreAverage = parseFloat(json.userScoreAverage);
        let roundUserScoreAverage = Math.round(userScoreAverage * 10) / 10;
        userScoreAverageTd.appendChild(document.createTextNode(roundUserScoreAverage));
    }
    if (json.userScoreCount != null) {
        let readers = json.userScoreCount == 1 ? "reader" : "readers";
        let userScoreCountSpan = document.getElementById('userScoreCountSpan');
        userScoreCountSpan.textContent = json.userScoreCount;
        userScoreCountTd.appendChild(document.createTextNode(" " + readers));
    }
    descriptionP.appendChild(document.createTextNode(json.description));

    bookTitle = json.title;
}

function setAuthorsTd(authorsTd, json) {
    let authorIndex = 0;
    for (author in json.authorResponses) {
        if (authorIndex != 0) {
            authorsTd.appendChild(document.createTextNode(",\n"));
        }
        authorsTd.appendChild(document.createTextNode(json.authorResponses[authorIndex].firstName + " "));
        if (json.authorResponses[authorIndex].secondName != null) {
            authorsTd.appendChild(document.createTextNode(json.authorResponses[authorIndex].secondName + " "));
        }
        authorsTd.appendChild(document.createTextNode(json.authorResponses[authorIndex].lastName));
        authorIndex++;
    }
}

function setGenresTd(genresTd, json) {
    let genreIndex = 0;
    for (genre in json.genreResponses) {
        if (genreIndex != 0) {
            genresTd.appendChild(document.createTextNode(", "));
        }
        genresTd.appendChild(document.createTextNode(json.genreResponses[genreIndex].name));
        genreIndex++;
    }
}