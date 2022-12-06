localStorage.setItem("lastPage", "catalog");

let currentPageNumber = 0;
let currentPageSize = 10;

function loadBooks() {
    currentPageNumber = 0;
    getBooksByTitleAndGenre(currentPageNumber, currentPageSize);
}

async function getBooksByTitleAndGenre(pageNumber, pageSize) {
    const title = document.getElementById("titleInput").value;
    const resultTable = document.getElementById("searchResultTable");
    resultTable.innerHTML = "";
    
    try {
        let response;
        if (selectedGenresIds.length == 0) {
            response = await fetch(`http://localhost:8080/books?title=${title}&pageNumber=${pageNumber}&pageSize=${pageSize}`);
        } else {
            response = await requestGetBooksWithGenreFilter(title, selectedGenresIds, pageNumber, pageSize);
        }
        const json = await response.json();
        displayBooksInTable(json, resultTable);
        displayControlButtons(pageNumber, json.totalPages);
    } catch(err) {
        resultTable.appendChild(document.createTextNode("There was a problem with retrieving and displaying books data from server."));
        console.error("There was a problem with retrieving and displaying books data from server.");
    }
}

function displayBooksInTable(json, table) {
    // Display table headers
    let headersTr = document.createElement('tr');
    let titleTh = document.createElement('th');
    titleTh.appendChild(document.createTextNode("Title"));
    let releaseDateTh = document.createElement('th');
    releaseDateTh.appendChild(document.createTextNode("Release date"));
    let publisherTh = document.createElement('th');
    publisherTh.appendChild(document.createTextNode("Publisher"));
    let authorsTh = document.createElement('th');
    authorsTh.appendChild(document.createTextNode("Authors"));

    let userScoreAverageTh = document.createElement('th');
    userScoreAverageTh.appendChild(document.createTextNode('Average score'));
    userScoreAverageTh.classList.add('shortTh');

    let userScoreCountTh = document.createElement('th');
    userScoreCountTh.appendChild(document.createTextNode('Scores'));
    userScoreCountTh.classList.add('shortTh');
    
    headersTr.appendChild(titleTh);
    headersTr.appendChild(releaseDateTh);
    headersTr.appendChild(publisherTh);
    headersTr.appendChild(authorsTh);
    headersTr.appendChild(userScoreAverageTh);
    headersTr.appendChild(userScoreCountTh);

    table.appendChild(headersTr);

    // Display table content
    let bookIndex = 0;
    for (let book in json.content) {
        let bookTr = document.createElement('tr');
        let titleTd = document.createElement('td');
        let releaseDateTd = document.createElement('td');
        let publisherTd = document.createElement('td');
        let authorsTd = document.createElement('td');
        let userScoreAverageTd = document.createElement('td');
        userScoreAverageTd.style.textAlign = 'right';
        let userScoreCountTd = document.createElement('td');
        userScoreCountTd.style.textAlign = 'right';

        let bookId = json.content[bookIndex].id;
        bookTr.setAttribute("onclick", `window.location.href="../about-book/about-book.html?bookId=${bookId}"`);
        bookTr.setAttribute("class", "bookRow");
        // setting title
        titleTd.appendChild(document.createTextNode(json.content[bookIndex].title));
        // setting release date
        releaseDateTd.appendChild(document.createTextNode(json.content[bookIndex].releaseDate));
        // setting publisher
        if (json.content[bookIndex].publisherResponse == null) {
            publisherTd.appendChild(document.createTextNode(""));
        } else {
            publisherTd.appendChild(document.createTextNode(json.content[bookIndex].publisherResponse.name));
        }
        // setting authors last names
        let authorsNames = "";
        if (json.content[bookIndex].authorResponses != null) {
            let j = 0;
            for (author in json.content[bookIndex].authorResponses) {
                if (j != 0) {
                    authorsNames += ", ";
                }
                authorsNames += json.content[bookIndex].authorResponses[j].firstName + " ";
                authorsNames += json.content[bookIndex].authorResponses[j++].lastName;
            }
        }
        authorsTd.appendChild(document.createTextNode(authorsNames));
        // setting average score
        let userScoreAverage = json.content[bookIndex].userScoreAverage;
        if (userScoreAverage != null) {
            userScoreAverage = (Math.round(userScoreAverage * 10) / 10).toFixed(1);
            userScoreAverageTd.appendChild(document.createTextNode(userScoreAverage));
        }
        // setting user score count
        userScoreCountTd.appendChild(document.createTextNode(json.content[bookIndex].userScoreCount));

        bookTr.appendChild(titleTd);
        bookTr.appendChild(releaseDateTd);
        bookTr.appendChild(publisherTd);
        bookTr.appendChild(authorsTd);
        bookTr.appendChild(userScoreAverageTd);
        bookTr.appendChild(userScoreCountTd);

        table.appendChild(bookTr);
        bookIndex++;
    }
}

function requestGetBooksWithGenreFilter(title, genresIds, pageNumber, pageSize) {
    return fetch(`http://localhost:8080/books?title=${title}&genresIds=${genresIds}&pageNumber=${pageNumber}&pageSize=${pageSize}`);
}

function displayControlButtons(pageNumber, totalPages) {
    let previousPageBtn = document.getElementById('previousPageBtn');
    let nextPageBtn = document.getElementById('nextPageBtn');
    
    if (pageNumber > 0) {
        previousPageBtn.style.display = 'inline';
    }
    if (pageNumber == 0) {
        previousPageBtn.style.display = 'none';
    }
    if (pageNumber + 1 == totalPages) {
        nextPageBtn.style.display = 'none';
    } else {
        nextPageBtn.style.display = 'inline';
    }
}

function loadNextBooks() {
    currentPageNumber++;
    getBooksByTitleAndGenre(currentPageNumber, currentPageSize);
}

function loadPreviousBooks() {
    currentPageNumber--;
    getBooksByTitleAndGenre(currentPageNumber, currentPageSize);
}