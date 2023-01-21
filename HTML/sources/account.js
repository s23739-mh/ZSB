document.addEventListener("DOMContentLoaded", isLogged);

function deleteCookies() {
    var allCookies = document.cookie.split(';');

    for (var i = 0; i < allCookies.length; i++)
        document.cookie = allCookies[i] + "=;expires="
            + new Date(0).toUTCString();
    window.location = "../sites/szopmain.html";
}

var global = [];
var mail = "";

function isLogged() {
    var allCookies = document.cookie.split(',');
    var help = ("http://localhost:8080/db_library/" +
        "checkPassword?" +
        "mail=" +
        allCookies[0] +
        "&password=" +
        allCookies[1]);
    if (allCookies.length == 3) {

        fetch(help)
            .then(response => response.json())
            .then(data => {
                if (data === null) {
                    deleteCookies();
                }
                const table = document.getElementById("information");
                let h, p;
                h = document.createElement('h3');
                h.innerHTML = "Imię i nazwisko";
                p = document.createElement('p');
                p.innerHTML = data.fname + " " + data.sname;
                table.appendChild(h);
                table.appendChild(p);

                h = document.createElement('h3');
                h.innerHTML = "Adres e-mail";
                p = document.createElement('p');
                p.innerHTML = data.mail;
                table.appendChild(h);
                table.appendChild(p);

                h = document.createElement('h3');
                h.innerHTML = "Miejsce Zamieszkania";
                p = document.createElement('p');
                p.innerHTML = data.city;
                table.appendChild(h);
                table.appendChild(p);

                let kratos = data.birthdate.split('T');

                h = document.createElement('h3');
                h.innerHTML = "Data urodzenia";
                p = document.createElement('p');
                p.innerHTML = kratos[0];
                table.appendChild(h);
                table.appendChild(p);

                const sys_info = document.getElementById("system_information");

                h = document.createElement('h3');
                h.innerHTML = "Rodzaj uprawnień";
                p = document.createElement('p');
                p.innerHTML = data.type;
                sys_info.appendChild(h);
                sys_info.appendChild(p);

                if (data.type !== "pracownik") {
                    let x = document.getElementById("buttonop");
                    x.parentNode.removeChild(x);
                }else{
                    let x = document.getElementById("buttonnormal");
                    x.parentNode.removeChild(x);
                }
                mail = data.mail;
                var help2 = "http://localhost:8080/db_library/getBookByMail?mail=" + data.mail;
                return fetch(help2);
            })
            .then(response => response.json())
            .then(data => {
                var booking = document.getElementById("booking");
                let placeholder = "";
                for (let i = 0; i < data.length; i++) {
                    placeholder += data[i].title + "</br>";
                }
                booking.innerHTML = placeholder;
                var help3 = "http://localhost:8080/db_library/getFavourites?mail_user=" + mail;
                return fetch(help3);
            })
            .then(response => response.json())
            .then(data => {
                for (let i = 0; i < data.length; i++) {
                    global.push(data[i].id_book);
                }
                return getFavourites();
            })
            .catch(error => {
                deleteCookies();
            })

    } else {
        deleteCookies();
    }
}

function getFavourites() {
    var help4 = "http://localhost:8080/db_library/book?id=";
    var help5 = "";
    for (let i = 0; i < global.length; i++) {
        help5 = help4 + global[i];
        fetch(help5)
            .then(response => response.json())
            .then(data => {
                var favourites = document.getElementById("favourites");
                var p = document.createElement('p');
                p.innerHTML = data.title;
                favourites.appendChild(p);
            })
    }
    return false;
}