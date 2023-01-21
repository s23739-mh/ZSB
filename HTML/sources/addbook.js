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

function addBook() {
    let title = document.forms["addForm"]["title"].value;
    let author = document.forms["addForm"]["author"].value;
    // let genre = document.forms["addForm"]["genre"].value;
    let genre = document.getElementById("genre").value;
    let language = document.forms["addForm"]["language"].value;
    let pubyear = document.forms["addForm"]["pubyear"].value;
    let publisher = document.forms["addForm"]["publisher"].value;
    let owner_mail = document.forms["addForm"]["owner_mail"].value;
    let beginning = document.forms["addForm"]["beginning"].value;
    let end = document.forms["addForm"]["end"].value;

    if(beginning==null||end==null||beginning==""||end==""){
        beginning="1970-01-02";
        end="1970-01-02";
    }
    console.log(beginning);
    var help = "http://localhost:8080/db_library/addBook?" +
        "id=0" +
        "&title=" + title +
        "&author=" + author +
        "&genre=" + genre +
        "&language=" + language +
        "&pubyear=" + pubyear +
        "&publisher=" + publisher +
        "&owner_ID=" + owner_mail +
        "&beginning=" + beginning +
        "&end=" + end;
    fetch(help, {method: 'POST'})
        .then(response => response.json())
        .then(data => {
            if (data === null) {
                deleteCookies();
                return;
            }
            window.location = "../sites/browseop.html"
        })
        .catch(error => {
            console.log("Wrongdata");
            deleteCookies();

        });

    return false;
}

function createForm() {
    let x = parent.document.URL.substring(parent.document.URL.indexOf('html') + 8, parent.document.URL.length);
    if (x.length != 0) {
        var help = "http://localhost:8080/db_library/book?id=" + x;
        fetch(help)
            .then(response => response.json())
            .then(data => {
                const table = document.getElementById("loginform");
                let form = document.getElementById("form");
                let betterDate1 = data.beginning;
                let betterDate2 = data.end;
                if (data.beginning != "undefined" && data.beginning != null) {
                    betterDate1 = data.beginning.split("T")[0];
                    betterDate2 = data.end.split("T")[0];
                }


                table.removeChild(form);
                form.innerHTML = "<form id='form' name='addForm' action='#' onSubmit='return editBook()'>" +
                    "Tytuł: <input type='text' name='title' id='title' value=\'" + data.title + "\'><br>" +
                    "Autor: <input type='text' name='author' id='author' value=\'" + data.author + "\'><br>" +
                    "Gatunek:<input list='genres' id='genre' onchange='resetIfInvalid(this);' required >" +
                    "<datalist id='genres' value=\'" + data.genre + "\'>" +
                    "<option value='FANTASY'>" +
                    "<option value='ADVENTURE'>" +
                    "<option value='ROMANCE' data-value='ROMANCE'>" +
                    "<option value='CONTEMPORARY'>" +
                    "<option value='DYSTOPIAN'>" +
                    "<option value='MYSTERY'>" +
                    "<option value='HORROR'>" +
                    "<option value='THRILLER'>" +
                    "<option value='PARANORMAL'>" +
                    "<option value='HISTORICAL_FICTION'>" +
                    "<option value='SCIENCE_FICTION'>" +
                    "<option value='CHILDREN'>" +
                    "<option value='MEMOIR'>" +
                    "<option value='COOKBOOK'>" +
                    "<option value='ART'>" +
                    "<option value='DEVELOPMENT'>" +
                    "<option value='HEALTH'>" +
                    "<option value='HISTORY'>" +
                    "<option value='HUMOR'>" +
                    "</datalist></br>" +
                    "Język: <input type='text' name='language' id='language' value=\'" + data.language + "\'><br>" +
                    "Rok wydania: <input type='text' name='pubyear' id='pubyear' value=\'" + data.pubyear + "\'><br>" +
                    "Wydawca: <input type='text' name='publisher' id='publisher' value=\'" + data.publisher + "\'><br>" +
                    "Mail właściciela: <input type='email' name='mail' id='owner_mail' value=\'" + data.owner_ID + "\'><br>" +
                    "Początek wypożyczenia: <input type='date' name='beginning' id='beginning' value=\'" + betterDate1 + "\'><br>" +
                    "Koniec wypożyczenia: <input type='date' name='end' id='end' value=\'" + betterDate2 + "\'><br>" +
                    "<input type='submit'>" +
                    "</form>";
                table.appendChild(form);
                let option = document.getElementById("genre");
                option.value = "HORROR";
                document.getElementById("owner_mail").required = false;
            })
            .catch(error => {

            })
    }
    return false;
}

function editBook() {
    let x = parent.document.URL.substring(parent.document.URL.indexOf('html') + 8, parent.document.URL.length);
    if (x.length != 0) {
        var help = "http://localhost:8080/db_library/removeBook?id=" + x;
        fetch(help, {method: 'DELETE'});
    }
    addBook();
    return false;
}

function resetIfInvalid(el) {
    //just for beeing sure that nothing is done if no value selected
    if (el.value == "")
        return;
    var options = el.list.options;
    for (var i = 0; i < options.length; i++) {
        if (el.value == options[i].value)
            //option matches: work is done
            return;
    }
    //no match was found: reset the value
    el.value = "";
}
