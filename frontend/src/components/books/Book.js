import React, {useState, useEffect} from "react";
import ReactDOM from "react-dom";

function Books() {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [booksList, setBookslist] = useState([]);

    useEffect(() => {
        fetch("/v1/books/all")
        .then(res => res.json())
        .then(
            (result) => {
                setIsLoaded(true);
                setBookslist(result)
            },
            (error) => {
                setIsLoaded(true);
                setError(error);
            }
        )
    }, [])

    if(error) {
        return <div> Error !!! </div>;
    } else if(!isLoaded) {
        return <div> Loading... </div>;
    } else {
        <ul>
            {booksList.map(books => (
                <li>
                    {books.title} {books.author} {books.publisher} {books.page_count}
                </li>
            ))}
        </ul>
    } 
}

export default Books;