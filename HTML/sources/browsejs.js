//http://localhost:8080/db_library/books
function getBooks(){
fetch("http://localhost:8080/db_library/books" )
        // get the JSON data
        .then(response => response.json())
        // use (display) the JSON data
        .then(data => {
            console.log(data);
            const table=document.getElementById("books");
            let subTD;
            for (let i = 0; i < data.length; i++) {
                const mainTR=document.createElement('tr');
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
                if(data[i].owner_ID===0||data[i].owner_ID===null){
                    subTD.innerHTML='TAK';
                     subTD.style.color = 'darkgreen';
                }
                else{
                    subTD.innerHTML="NIE";
                    subTD.style.color='red';
                }
                subTD.style.fontWeight='bold';
                mainTR.appendChild(subTD);
                subTD = document.createElement('td');
                subTD.innerHTML = "<button class='favourite'>" +
                    " <i class='fa fa-heart' aria-hidden='true'></i> </button>"
                mainTR.appendChild(subTD);
                table.appendChild(mainTR);
            }
            const content=document.getElementById("content");
            content.appendChild(table);
        });

}
document.addEventListener("DOMContentLoaded", getBooks);
