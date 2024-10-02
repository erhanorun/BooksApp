import React, { useEffect, useState } from 'react';
import axios from 'axios';

const BookList = ({ onDelete }) => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        fetchBooks();
    }, []);

    const fetchBooks = async () => {
        const result = await axios.get('http://localhost:8080/v1/books');
        setBooks(result.data);
    };

    const handleDelete = async (id) => {
        await axios.delete(`http://localhost:8080/v1/deleteBook/${id}`);
        onDelete();
        fetchBooks();
    };

    return (
        <div>
            <h2>Book List</h2>
            <ul>
                {books.map((book) => (
                    <li key={book.id}>
                        Book ID: <strong> {book.id} </strong> <br />
                        <strong>{book.title}</strong> by {book.author} <br />
                        Publisher: {book.publisher} <br />
                        <strong>Page Count: {book.pageCount} </strong> <br />
                        <button onClick={() => handleDelete(book.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default BookList;
