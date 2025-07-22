const searchBtns = document.querySelectorAll(".search-btn")

function createStarRating(bookId, rating){
    const maxRating = 5;
    let starString = ''
    let count = 1;
    for(; count <= maxRating; count++){
        starString += `<span class="${count <= rating ? 'gold-star': ''}" onclick="addRating(${bookId}, ${count})">&#9733;</span>`;
    }

    return starString;
}

const searchByTitle = async (title) => {
    try {
        const response = await fetch(`http://localhost:8080/books/search/${encodeURIComponent(title)}`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const data = await response.json();
        console.log('Book data:', data);
        return data;
    } catch (error) {
        console.error('Search failed:', error);
        alert(`Search failed: ${error.message}`);
        throw error; // Re-throw to handle in caller
    }
}

searchBtns.forEach(btn => {
    btn.addEventListener("click", async (e) => { // Make this async
        e.preventDefault();
        const form = btn.closest('.search-form-wrapper');
        const titleInput = form.querySelector('.search-input');

        if (titleInput && titleInput.value.trim()) {
            try {
                const data = await searchByTitle(titleInput.value.trim()); // Await the result
                console.table(data);
                
                const resultsContainer = document.getElementById("search-results-container");
                resultsContainer.innerHTML = '';

                const bookCard = document.createElement('div');
                bookCard.innerHTML = `
                    <h3>${data.title}</h3>
                    <h4>${data.author}</h4>
                    <p>${data.genre}</p>
                    <p>${createStarRating(data.id, data.rating)}</p>
                `;
                resultsContainer.appendChild(bookCard);
            } catch (error) {
                // Handle error case
                console.error('Failed to load book:', error);
            }
        }
    });
});


