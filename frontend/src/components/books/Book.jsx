import React, {useState, useEffect} from "react";
import ReactDOM from "react-dom";

function Books() {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [booksList, setBooksList] = useState([]);

    useEffect(() => {
        fetch("/v1/books/all")
        .then(res => res.json())
        .then(
            (result) => {
                setIsLoaded(true);
                console.log(result);
                setBooksList(result)
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
        return <ul
        style={{height: 20}}>
            {booksList.map(books => (
                <li>
                    {books.title} {books.author} {books.publisher} {books.page_count}
                </li>
            ))}
        </ul>
    } 
}

export default Books;
