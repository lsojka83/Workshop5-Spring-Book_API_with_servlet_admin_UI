const apihost = 'http://localhost:8080';

document.addEventListener('DOMContentLoaded', function () {

    apiBooks().then(
        function (value){
        value.forEach(
            function (book) {
                renderBook(book.id, book.isbn, book.title, book.author, book.publisher, book.type);
                console.log(book.id);
            }
        )
    }
    );
});

function apiBooks() {
    return fetch(
        apihost + '/books',
        {}
    ).then(
        function (resp) {
            if (!resp.ok) {
                alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
            }
            return resp.json();
        }
    )
}

function renderBook(id, isbn, title, author, publisher, type) {

    const table_body = document.querySelector('.tbody');
    const table_row = document.createElement('tr');
    table_row.className = "odd";
    table_row.setAttribute("role", "row")
    const table_row_data_id = document.createElement('td');
    table_row_data_id.className = "sorting_1";
    const table_row_data_isbn = document.createElement('td');
    const table_row_data_title = document.createElement('td');
    const table_row_data_author = document.createElement('td');
    const table_row_data_publisher = document.createElement('td');
    const table_row_data_type = document.createElement('td');
    table_row_data_id.innerText = id;
    table_row_data_isbn.innerText = isbn;
    table_row_data_title.innerText = title;
    table_row_data_author.innerText = author;
    table_row_data_publisher.innerText = publisher;
    table_row_data_type.innerText = type;
    table_row.appendChild(table_row_data_id);
    table_row.appendChild(table_row_data_isbn);
    table_row.appendChild(table_row_data_title);
    table_row.appendChild(table_row_data_author);
    table_row.appendChild(table_row_data_publisher);
    table_row.appendChild(table_row_data_type);
    table_body.appendChild(table_row);

}










