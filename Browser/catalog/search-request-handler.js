async function getBooksByTitle() {
    const title = document.getElementById("titleInput").value;
    const resultTable = document.getElementById("searchResultTable");
    resultTable.innerHTML = "";
    try {
        const response = await fetch(`http://localhost:8080/books?title=${title}&pageNumber=0&pageSize=8`);
        const jsonData = await response.json();
        displayBooksInTable(jsonData, resultTable);
    } catch(err) {
        resultTable.appendChild(document.createTextNode("There was a problem with retrieving and displaying data from server."));
        console.error("There was a problem with retrieving and displaying data from server.");
    }
}

function displayBooksInTable(jsonData, table) {
    // Display table headers
    let headersTr = document.createElement('tr');
    let titleTh = document.createElement('th');
    titleTh.appendChild(document.createTextNode("Title"));
    let releaseDateTh = document.createElement('th');
    releaseDateTh.appendChild(document.createTextNode("Release date"));
    let publisherTh = document.createElement('th');
    publisherTh.appendChild(document.createTextNode("Publisher"));
    let authorTh = document.createElement('th');
    authorTh.appendChild(document.createTextNode("Author"));
    headersTr.appendChild(titleTh);
    headersTr.appendChild(releaseDateTh);
    headersTr.appendChild(publisherTh);
    headersTr.appendChild(authorTh);
    table.appendChild(headersTr);

    // Display table content
    let i = 0;
    for (let book in jsonData.content) {
        let bookTr = document.createElement('tr');
        let titleTd = document.createElement('td');
        let releaseDateTd = document.createElement('td');
        let publisherTd = document.createElement('td');
        let authorTd = document.createElement('td');

        let bookId = jsonData.content[i].id;
        bookTr.setAttribute("onclick", `window.location.href="../about-book/about-book.html?bookId=${bookId}"`);
        bookTr.setAttribute("class", "bookRow");
        //bookTr.setAttribute()
        // setting title
        titleTd.appendChild(document.createTextNode(jsonData.content[i].title));
        // setting release date
        releaseDateTd.appendChild(document.createTextNode(jsonData.content[i].releaseDate));
        // setting publisher
        if (jsonData.content[i].publisherResponse == null) {
            publisherTd.appendChild(document.createTextNode(""));
        } else {
            publisherTd.appendChild(document.createTextNode(jsonData.content[i].publisherResponse.name));
        }
        // setting authors last names
        let authorsLastNames = "";
        if (jsonData.content[i].authorResponses != null) {
            let j = 0;
            for (author in jsonData.content[i].authorResponses) {
                if (j != 0) {
                    authorsLastNames += ", ";
                }
                authorsLastNames += jsonData.content[i].authorResponses[j++].lastName;
            }
        }
        authorTd.appendChild(document.createTextNode(authorsLastNames));

        bookTr.appendChild(titleTd);
        bookTr.appendChild(releaseDateTd);
        bookTr.appendChild(publisherTd);
        bookTr.appendChild(authorTd);

        table.appendChild(bookTr);
        i++;
    }
}