import './App.css';
import React, { useState } from "react";
import TourForm from "./TourForm";
import TourList from "./TourList";
import TourDetails from "./TourDetails";

const App = () => {
    const [tours, setTours] = useState([]);
    const [selectedTour, setSelectedTour] = useState(null);
    const [creatingTour, setCreatingTour] = useState(false);

    const handleCreateTour = (newTour) => {
        setTours([...tours, newTour]);
        setCreatingTour(false);
    };

    const handleSelectTour = (tour) => {
        setSelectedTour(tour);
        setCreatingTour(false);
    };

    const handleCreateButtonClick = () => {
        setSelectedTour(null);
        setCreatingTour(true);
    };

    const handleDeleteTour = (tourId) => {
        setTours(tours.filter(tour => tour.id !== tourId));
        setSelectedTour(null);
    };

    let displayComponent;
    if (creatingTour) {
        displayComponent = <TourForm onCreateTour={handleCreateTour} />;
    } else if (selectedTour) {
        displayComponent = <TourDetails tour={selectedTour} onDelete={handleDeleteTour} />;
    }

    return (
        <div>
            <h1>Tourplanner</h1>
            <div className="app-container">
                <div className="tour-list">
                    <TourList onSelectTour={handleSelectTour} onCreateTour={handleCreateButtonClick} />
                </div>
                <div className="display-component">
                    {displayComponent}
                </div>
            </div>
        </div>
    );
};

export default App;
