//alert("Hello Class")
//Mutable Variable
let books = [
     
];

//DOM Manipulation
//Document Object Model

//Conatant/Final variable
const bookForm = document.getElementById('book-form');
const bookContainer = document.getElementById('books-container');
const search = document.getElementById('search');

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
    saveToLocalStorage();
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
        <td>${createStarRating(book.id, book.rating)}</td>
        <td><button onclick="deleteBook(${book.id})">Delete</button></td>
        `;
        bookContainer.appendChild(bookRow);
    });
}

function addRating(id, rating) {
    const book = books.find(book => book.id === id);
    if (book) {
        book.rating = rating;
        saveToLocalStorage();
        displayBooks();
    }
}

function createStarRating(bookId, rating){
    const maxRating = 5;
    let starString = ''
    let count = 1;
    for(; count <= maxRating; count++){
        starString += `<span class="${count <= rating ? 'gold-star': ''}" onclick="addRating(${bookId}, ${count})">&#9733;</span>`;
    }

    return starString;
}

function deleteBook(id) {
    books = books.filter(book => book.id !== id);
    saveToLocalStorage();
    displayBooks();
}

search.addEventListener('keypress', function(e){
    if (e.key === 'Enter') {
        e.preventDefault()
        searchBooks();
    }
}) 

const searchBooks = () => {
    const searchTerm = document.getElementById('search').value.toLowerCase();

    const filiteredBooks = books.filter(book => 
        book.title.toLowerCase().includes(searchTerm) ||
        book.author.toLowerCase().includes(searchTerm) ||
        book.genre.toLowerCase().includes(searchTerm)
    );

    displayFiliteredBooks(filiteredBooks);
}

const displayFiliteredBooks = (filiteredBooks) => {
    const searchResults = document.getElementById('search-results');

    searchResults.innerHTML = '';

    if(filiteredBooks.length === 0){
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'No results found';
        searchResults.appendChild(errorMessage);
        return;
    }

    const searchList = document.createElement('ol');
    searchList.setAttribute('type', 'I');

    filiteredBooks.forEach(book => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `
        <div>
            <h4>${book.title}</h4>
            <h5>${book.author}</h5>
            <p>${book.publishDate}</p>
            <p>${book.genre}</p>
            <p>${book.rating}</p>
        </div>
        `;
        searchList.appendChild(listItem);
    })
    searchResults.appendChild(searchList);
}

function saveToLocalStorage() {
    localStorage.setItem('bookHubBooks', JSON.stringify(books));
}

function loadFromLocalStorage() {
    const saved = localStorage.getItem('bookHubBooks');

    if(saved) {
        books = JSON.parse(saved);

        books.forEach(book => book.dateAdded = new Date(book.dateAdded));
        displayBooks();
    }
}

window.addEventListener('DOMContentLoaded', loadFromLocalStorage);
window.addEventListener('DOMContentLoaded', displayBooks);