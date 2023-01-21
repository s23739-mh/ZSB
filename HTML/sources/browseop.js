document.addEventListener("DOMContentLoaded", isLogged);
document.addEventListener("DOMContentLoaded", getBooks);

function deleteCookies() {
    var allCookies = document.cookie.split(';');

    for (var i = 0; i < allCookies.length; i++)
        document.cookie = allCookies[i] + "=;expires="
            + new Date(0).toUTCString();
    window.location = "../sites/szopmain.html";
}

function isLogged() {
    var allCookies = document.cookie.split(',');
    var help = ("http://localhost:8080/db_library/" +
        "checkPassword?" +
        "mail=" +
        allCookies[0] +
        "&password=" +
        allCookies[1]);
    if (allCookies.length != 3) {
        deleteCookies();
    } else {

        fetch(help)
            .then(response => response.json())
            .then(data => {
                if (data === null) {
                    deleteCookies();
                } else {
                    if (data.type !== "pracownik") {
                        deleteCookies();
                    }
                }
            })

            .catch(error => {
                deleteCookies();
            })
    }
}

function getBooks() {
    fetch("http://localhost:8080/db_library/books")
        // get the JSON data
        .then(response => response.json())
        // use (display) the JSON data
        .then(data => {
            // console.log(data);
            const table = document.getElementById("books");
            let subTD;
            for (let i = 0; i < data.length; i++) {
                const mainTR = document.createElement('tr');
                subTD = document.createElement('td');
                subTD.innerHTML = data[i].title;
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                subTD.innerHTML = data[i].author;
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                subTD.innerHTML = data[i].genre;
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                subTD.innerHTML = data[i].language;
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                subTD.innerHTML = data[i].pubyear;
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                subTD.innerHTML = data[i].publisher;
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                if (data[i].owner_mail == "" || data[i].owner_mail == null || data[i].end ==null ) {
                    subTD.innerHTML = 'TAK';
                    subTD.style.color = 'darkgreen';
                } else {
                    subTD.innerHTML = "NIE";
                    subTD.style.color = 'red';
                }
                subTD.style.fontWeight = 'bold';
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                subTD.innerHTML = "<button class='favourite'>" +
                    " <i class='fa fa-cogs' aria-hidden='true' onclick='editBook(\"" + data[i].id + "\")'></i> </button>" +
                    "<button class='delete'>" +
                    " <i class='fa fa-trash' aria-hidden='true' onclick='deleteBook(\"" + data[i].id + "\")'></i> </button>" +
                    "<button class='add'>" +
                    " <i class='fa fa-plus' aria-hidden='true' onclick='addUser()'></i> </button>";
                mainTR.appendChild(subTD);
                table.appendChild(mainTR);
            }
            const content = document.getElementById("content");
            content.appendChild(table);
        });

}

function editBook(x) {
    window.location = "../sites/addBook.html?id=" + x;
}

function deleteBook(x) {
    var help = "http://localhost:8080/db_library/removeBook?id=" + x;
    fetch(help, {method: 'DELETE'});
    window.location.reload();
}

function addUser() {
    window.location = "../sites/addBook.html";
}

//http://localhost:8080/db_library/book?id=1

//http://localhost:8080/db_library/removeBook?id=1