sessionStorage.setItem("lastPage", "catalog");

async function getBooksByTitle() {
    const title = document.getElementById("titleInput").value;
    const resultTable = document.getElementById("searchResultTable");
    resultTable.innerHTML = "";
    try {
        const response = await fetch(`http://localhost:8080/books?title=${title}&pageNumber=0&pageSize=8`);
        const json = await response.json();
        displayBooksInTable(json, resultTable);
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
    headersTr.appendChild(titleTh);
    headersTr.appendChild(releaseDateTh);
    headersTr.appendChild(publisherTh);
    headersTr.appendChild(authorsTh);
    table.appendChild(headersTr);

    // Display table content
    let bookIndex = 0;
    for (let book in json.content) {
        let bookTr = document.createElement('tr');
        let titleTd = document.createElement('td');
        let releaseDateTd = document.createElement('td');
        let publisherTd = document.createElement('td');
        let authorsTd = document.createElement('td');

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
        let authorsLastNames = "";
        if (json.content[bookIndex].authorResponses != null) {
            let j = 0;
            for (author in json.content[bookIndex].authorResponses) {
                if (j != 0) {
                    authorsLastNames += ", ";
                }
                authorsLastNames += json.content[bookIndex].authorResponses[j++].lastName;
            }
        }
        authorsTd.appendChild(document.createTextNode(authorsLastNames));

        bookTr.appendChild(titleTd);
        bookTr.appendChild(releaseDateTd);
        bookTr.appendChild(publisherTd);
        bookTr.appendChild(authorsTd);

        table.appendChild(bookTr);
        bookIndex++;
    }
}