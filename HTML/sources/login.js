function checkLogin() {
    let mail = document.forms["loginForm"]["mail"].value;
    let password = document.forms["loginForm"]["password"].value;
    deleteCookies();
    fetch("http://localhost:8080/db_library/checkPassword?" +
        "mail=" + mail +
        "&password=" + password)
        .then(response => response.json())
        .then(data => {
            if (data === null) {
                return;
            }
            document.cookie = mail + "," + password + ",";
            let cookies = document.cookie;
            if (data.type == "czytelnik") {
                window.location = "../sites/browseop.html"
            } else {
                console.log(data.type);
                window.location = "../sites/browse.html"
            }
        })
        .catch(error => {
            console.log("Wrong??");
        })
}

function register() {

    let fname = document.forms["registerForm"]["fname"].value;
    let sname = document.forms["registerForm"]["sname"].value;
    let birthdate = document.forms["registerForm"]["birthdate"].value;
    let city = document.forms["registerForm"]["city"].value;
    let mail = document.forms["registerForm"]["mail"].value;
    let password = document.forms["registerForm"]["password"].value;
    console.log(birthdate);
    var help = "http://localhost:8080/db_library/newUser?" +
        "fname=" + fname +
        "&sname=" + sname +
        "&mail=" + mail +
        "&birthdate=" + birthdate +
        "&city=" + city +
        "&type=czytelnik" +
        "&password=" + password;
    deleteCookies();
    fetch(help, {method: 'POST'})
        .then(response => response.json())
        .then(data => {
            if (data === null) {
                return;
            }
            document.cookie = data.mail + "," + data.password + ",";
            let cookies = document.cookie;
            window.location = "../sites/browse.html"
        })
        .catch(error => {
            console.log("Wrong?");
        })
    return false;
}

function deleteCookies() {
    var allCookies = document.cookie.split(';');
    for (var i = 0; i < allCookies.length; i++)
        document.cookie = allCookies[i] + "=;expires="
            + new Date(0).toUTCString();

}