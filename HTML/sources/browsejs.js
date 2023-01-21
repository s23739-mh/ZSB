//http://localhost:8080/db_library/books

var cmail = "";

function isLogged() {
    var allCookies = document.cookie.split(',');
    var help = ("http://localhost:8080/db_library/" +
        "checkPassword?" +
        "mail=" +
        allCookies[0] +
        "&password=" +
        allCookies[1]);
    cmail = allCookies[0];
    if (allCookies.length != 3) {
        deleteCookies();
    } else {

        fetch(help)
            .then(response => response.json())
            .then(data => {
                if (data === null) {
                    deleteCookies();
                } else {
                    type = JSON.stringify(data.type);
                    role = JSON.stringify("pracownik");
                    if (type == role) {
                        window.location = "../sites/browseop.html";
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
                    " <i class='fa fa-heart' aria-hidden='true' onclick='addFavourite(\"" + data[i].id + "\")'></i> </button>" +
                    "<button class='favourite'>" +
                    " <i class='fa fa-times' aria-hidden='true' onclick='removeFavourite(\"" + data[i].id + "\")'></i> </button>"
                mainTR.appendChild(subTD);
                table.appendChild(mainTR);
            }
            const content = document.getElementById("content");
            content.appendChild(table);
        });

}

document.addEventListener("DOMContentLoaded", getBooks);
document.addEventListener("DOMContentLoaded", isLogged);

function deleteCookies() {
    var allCookies = document.cookie.split(';');

    for (var i = 0; i < allCookies.length; i++)
        document.cookie = allCookies[i] + "=;expires="
            + new Date(0).toUTCString();
    window.location = "../sites/szopmain.html";
}

function addFavourite(id) {
    var help = "http://localhost:8080/db_library/newFavourite?id_book=" + id +
        "&mail_user=" + cmail;
    fetch(help, {method: 'POST'})
        .then(response => response.json())
        .then(data => {
            location.reload();
        })
}

function removeFavourite(id) {
    var help = "http://localhost:8080/db_library/deleteFavourite?id_book=" + id +
        "&mail_user=" + cmail;
    fetch(help, {method: 'DELETE'})
        .then(response => response.json())
        .then(data => {
            location.reload();
        }).catch(error => {
        alert("Nie masz tej książki ulubionej");
    })
}

function search(text){
    var help="";
}