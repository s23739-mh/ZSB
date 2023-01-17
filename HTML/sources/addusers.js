document.addEventListener("DOMContentLoaded", isLogged);

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
                    createForm();
                }
            })
            .catch(error => {
                deleteCookies();
            })
    }
}

function addUser() {
    let fname = document.forms["addForm"]["fname"].value;
    let sname = document.forms["addForm"]["sname"].value;
    let birthdate = document.forms["addForm"]["birthdate"].value;
    let city = document.forms["addForm"]["city"].value;
    let mail = document.forms["addForm"]["mail"].value;
    let role = "czytelnik";
    let password = document.forms["addForm"]["password"].value;
    let x = parent.document.URL.substring(parent.document.URL.indexOf('html') + 10, parent.document.URL.length);

    if (x.length != 0) {
        var help2 = "http://localhost:8080/db_library/getUser?mail=" + x;
        fetch(help2)
            .then(response => response.json())
            .then(data => {
                if (data.type === "pracownik") {
                    role = "pracownik";
                    var help = "http://localhost:8080/db_library/newUser?" +
                        "fname=" + fname +
                        "&sname=" + sname +
                        "&mail=" + mail +
                        "&birthdate=" + birthdate +
                        "&city=" + city +
                        "&type=" + role +
                        "&password=" + password;
                    return fetch(help, {method: 'POST'});
                }
            }).then(response => response.json())
            .then(data => {
                if (data === null) {
                    return;
                }
                window.location = "../sites/editusers.html"
            })
            .catch(error => {
                console.log("Wrongdata");
            })
            .catch(error => {
                deleteCookies();
            });
    } else {
        var help = "http://localhost:8080/db_library/newUser?" +
            "fname=" + fname +
            "&sname=" + sname +
            "&mail=" + mail +
            "&birthdate=" + birthdate +
            "&city=" + city +
            "&type=czytelnik" +
            "&password=" + password;
        fetch(help, {method: 'POST'})
            .then(response => response.json())
            .then(data => {
                if (data === null) {
                    return;
                }
                window.location = "../sites/editusers.html"
            })
            .catch(error => {
                console.log("Wrongdata");
                deleteCookies();
            });
    }


    return false;
}

function createForm() {
    let x = parent.document.URL.substring(parent.document.URL.indexOf('html') + 10, parent.document.URL.length);
    if (x.length != 0) {
        var help = "http://localhost:8080/db_library/getUser?mail=" + x;
        fetch(help)
            .then(response => response.json())
            .then(data => {
                const table = document.getElementById("loginform");
                let form = document.getElementById("form");
                let betterDate = data.birthdate.split("T");

                table.removeChild(form);

                form.innerHTML = "<form id='form' name='addForm' action='#' onSubmit='return editUser()'>" +
                    "Imię: <input type='text' name='fname' id='fname' value=\'" + data.fname + "\'><br>" +
                    "Nazwisko: <input type='text' name='sname' id='sname' value=\'" + data.sname + "\'><br>" +
                    "Data Urodzenia: <input type='date' name='birthdate' id='birthdate' value=\'" + betterDate[0] + "\'><br>" +
                    "Miejscowość: <input type='text' name='city' id='city' value=\'" + data.city + "\'><br>" +
                    "E-mail: <input type='email' name='mail' id='mail' value=\'" + data.mail + "\'><br>" +
                    "Hasło: <input type='text' name='password' id='password' value=\'" + data.password + "\'><br>" +
                    "<input type='submit'>" +
                    "</form>";

                table.appendChild(form);


            })
            .catch(error => {

            })
    }
}

function editUser() {
    let x = parent.document.URL.substring(parent.document.URL.indexOf('html') + 10, parent.document.URL.length);
    if (x.length != 0) {
        var help = "http://localhost:8080/db_library/removeUser?mail=" + x;
        fetch(help, {method: 'DELETE'});
    }
    addUser();
    return false;
}

