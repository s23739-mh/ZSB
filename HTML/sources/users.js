document.addEventListener("DOMContentLoaded", isLogged);
document.addEventListener("DOMContentLoaded", getUsers);

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
    if (allCookies.length == 3) {

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
    } else {
        deleteCookies();
    }
}

function getUsers() {
    fetch("http://localhost:8080/db_library/allUsers")
        .then(response => response.json())
        .then(data => {
            const table = document.getElementById("users");
            let subTD;
            for (let i = 0; i < data.length; i++) {
                const mainTR = document.createElement('tr');
                subTD = document.createElement('td');
                subTD.innerHTML = data[i].fname + " " + data[i].sname;
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                subTD.innerHTML = data[i].mail;
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                let kratos = data[i].birthdate.split('T');
                ;
                subTD.innerHTML = kratos[0];
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                subTD.innerHTML = data[i].city;
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                subTD.innerHTML = "<button class='favourite'>" +
                    " <i class='fa fa-cogs' aria-hidden='true' onclick='editUser(\"" + data[i].mail + "\")'></i> </button>" +
                    "<button class='delete'>" +
                    " <i class='fa fa-trash' aria-hidden='true' onclick='deleteUser(\"" + data[i].mail + "\")'></i> </button>" +
                    "<button class='add'>" +
                    " <i class='fa fa-plus' aria-hidden='true' onclick='addUser()'></i> </button>";
                mainTR.appendChild(subTD);
                table.appendChild(mainTR);
            }
            const content = document.getElementById("content");
            content.appendChild(table);
        });
}

function deleteUser(x) {
    var help = "http://localhost:8080/db_library/removeUser?mail=" + x;
    fetch(help, {method: 'DELETE'});
    window.location.reload();
}

function addUser() {
    window.location = "../sites/addUser.html";
}

function editUser(x) {
    console.log(x);
    window.location = "../sites/addUser.html?mail=" + x;
}
