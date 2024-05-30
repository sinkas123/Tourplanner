import React, { useEffect, useState } from "react";
import axios from "axios";

const TourList = () => {
    const [tours, setTours] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchTours = async () => {
            try {
                const response = await axios.get('/tour');
                setTours(response.data);
                setLoading(false);
            } catch (err) {
                setError(err.message);
                setLoading(false);
            }
        };

        fetchTours();
    }, []);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div>
            <h2>Tours List</h2>
            <ul>
                {tours.map((tour, index) => (
                    <li key={index}>
                        <h3>{tour.name}</h3>
                        <p>{tour.description}</p>
                        <div>
                            <h4>From:</h4>
                            <p>{tour.startLocation}</p>
                        </div>
                        <div>
                            <h4>To:</h4>
                            <p>{tour.endLocation}</p>
                        </div>
                        <p>Transport Type: {tour.transportType}</p>
                        <p>Distance: {tour.tourDistance} m</p>
                        <p>Estimated Time: {tour.estimatedTime} minutes</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TourList;
