import React, { useState } from 'react';
import BookList from './components/books/BookList';
import BookForm from './components/books/BookForm';

function App() {
    const [updateFlag, setUpdateFlag] = useState(false);

    const handleUpdate = () => {
        setUpdateFlag(!updateFlag);
    };

    return (
        <div className="App">
            <h1>Book Management</h1>
            <BookForm onAdd={handleUpdate} />
            <BookList onDelete={handleUpdate} />
        </div>
    );
}

export default App;
