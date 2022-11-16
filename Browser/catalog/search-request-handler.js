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
    let titleTd = document.createElement('th');
    titleTd.appendChild(document.createTextNode("Title"));
    let releaseDateTd = document.createElement('th');
    releaseDateTd.appendChild(document.createTextNode("Release date"));
    let userScoreAverageTd = document.createElement('th');
    userScoreAverageTd.appendChild(document.createTextNode("User score average"));
    headersTr.appendChild(titleTd);
    headersTr.appendChild(releaseDateTd);
    headersTr.appendChild(userScoreAverageTd);
    table.appendChild(headersTr);

    // Display table content
    let i = 0;
    for (let book in jsonData.content) {
        let bookTr = document.createElement('tr');
        let titleTd = document.createElement('td');
        let releaseDateTd = document.createElement('td');
        let userScoreAverageTd = document.createElement('td');

        //bookTr.setAttribute("onclick", `window.open("../about-book/about-book.html")`);
        titleTd.appendChild(document.createTextNode(jsonData.content[i].title));
        releaseDateTd.appendChild(document.createTextNode(jsonData.content[i].releaseDate));
        let userScoreAverage = jsonData.content[i].userScoreAverage;
        if (userScoreAverage == null) {
            userScoreAverage = "No user scores";
        }
        userScoreAverageTd.appendChild(document.createTextNode(userScoreAverage));

        bookTr.appendChild(titleTd);
        bookTr.appendChild(releaseDateTd);
        bookTr.appendChild(userScoreAverageTd);

        table.appendChild(bookTr);
        i++;
    }
}