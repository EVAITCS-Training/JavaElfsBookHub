//alert("Hello Class")
//Mutable Variable
let books = [];

//DOM Manipulation
//Document Object Model

//Conatant/Final variable
const bookForm = document.getElementById('book-form');
const bookContainer = document.getElementById('books-container');

bookForm.addEventListener('submit', function(e) {
    e.preventDefault();

    const title = document.getElementById('title').value;
    const author = document.getElementById('author').value;
    const publishDate = document.getElementById('publish-date').value;
    const price = document.getElementById('price').value;
    const genre = document.getElementById('genre').value;

    const book = {
        id: Date.now(),
        title: title,
        author: author,
        publishDate: publishDate,
        price: price,
        genre: genre,
        rating: 0,
        dateAdded: new Date()
    };

    books.push(book);

    bookForm.reset();

    displayBooks();
});

function displayBooks() {
    bookContainer.innerText = '';

    if(books.length === 0) {
        bookContainer.innerHTML ='<p>No books yet. Add your first book!</p>';
        return;
    }
    books.forEach(book => {
        const bookRow = document.createElement('tr');
        bookRow.innerHTML = `
        <th>${book.title}</th>
        <td>${book.author}</td>
        <td>${book.publishDate}</td>
        <td>${book.genre}</td>
        <td><button onclick="deleteBook(${book.id})">Delete</button></td>
        `;
        bookContainer.appendChild(bookRow);
    });
}

function deleteBook(id) {
    books = books.filter(book => book.id !== id);
    displayBooks();
}

displayBooks();