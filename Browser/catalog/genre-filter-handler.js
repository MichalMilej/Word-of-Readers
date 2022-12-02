const selectedGenresIds = [];
loadGenres();

async function loadGenres() {
    let genresRes = await requestGetGenres();
    let json = await genresRes.json();
    displayGenres(json);
}

function displayGenres(json) {
    let genreFilterTable = document.getElementById('genreFilterTable');
    let genreIndex = 0;
    let genreFilterTr = document.createElement('tr');;
    for (genre in json) {
        let genreTd = document.createElement('td');

        let genreCheckbox = document.createElement('input');
        genreCheckbox.type = 'checkbox';
        genreCheckbox.id = json[genreIndex].name + "Checkbox";
        genreCheckbox.classList.add('genreCheckbox');
        genreCheckbox.setAttribute("onchange", (`genreCheckboxClicked("${json[genreIndex].id}")`));
        genreTd.appendChild(genreCheckbox);

        let genreLabel = document.createElement('label');
        genreLabel.for = genreCheckbox.id;
        genreLabel.innerText = json[genreIndex].name;
        genreLabel.classList.add('genreLabel');
        genreTd.appendChild(genreLabel);

        genreFilterTr.appendChild(genreTd);
        genreIndex++;

        if (genreIndex % 4 == 0) {
            genreFilterTable.appendChild(genreFilterTr);
            genreFilterTr = document.createElement('tr');
        }
    }
}

function requestGetGenres() {
    return fetch(`http://localhost:8080/books/genres`);
}

function genreCheckboxClicked(genreId) {
    if (selectedGenresIds.includes(genreId)) {
        let index = selectedGenresIds.indexOf(genreId);
        selectedGenresIds.splice(index, 1);
    } else {
        selectedGenresIds.push(genreId);
    }
}