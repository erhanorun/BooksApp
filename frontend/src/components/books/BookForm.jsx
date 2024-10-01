import React, { useState } from 'react';
import axios from 'axios';

const BookForm = ({ onAdd }) => {
    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [publisher, setPublisher] = useState('');
    const [pageCount, setPageCount] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const newBook = { title, author, publisher, pageCount };
        await axios.post('http://localhost:8080/v1/addBook', newBook);
        onAdd();
        setTitle('');
        setAuthor('');
        setPublisher('');
        setPageCount('');
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Add a New Book</h2>
            <div>
                <input
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    placeholder="Title"
                    required
                />
            </div>
            <div>
                <input
                    type="text"
                    value={author}
                    onChange={(e) => setAuthor(e.target.value)}
                    placeholder="Author"
                    required
                />
            </div>
            <div>
                <input
                    type="text"
                    value={publisher}
                    onChange={(e) => setPublisher(e.target.value)}
                    placeholder="Publisher"
                    required
                />
            </div>
            <div>
                <input
                    type="number"
                    value={pageCount}
                    onChange={(e) => setPageCount(e.target.value)}
                    placeholder="Page Count"
                    required
                />
            </div>
            <button type="submit">Add Book</button>
        </form>
    );
};

export default BookForm;
