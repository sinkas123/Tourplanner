import './App.css';
import React, { useState } from "react";
import TourForm from "./components/TourForm";
import TourList from "./components/TourList";
import TourDetails from "./components/TourDetails";
import TourUpdate from "./components/TourUpdate";
import LogList from "./components/LogList";

const App = () => {
    const [tours, setTours] = useState([]);
    const [selectedTour, setSelectedTour] = useState(null);
    const [selectedTourId, setSelectedTourId] = useState(null);
    const [creatingTour, setCreatingTour] = useState(false);
    const [editingTour, setEditingTour] = useState(false);
    const [activeTab, setActiveTab] = useState('details');

    const handleCreateTour = (newTour) => {
        setTours([...tours, newTour]);
        setCreatingTour(false);
    };

    const handleSelectTour = (tour) => {
        setSelectedTour(tour);
        setSelectedTourId(tour.id);
        setCreatingTour(false);
        setActiveTab('details');
    };

    const handleCreateButtonClick = () => {
        setSelectedTour(null);
        setSelectedTourId(null);
        setCreatingTour(true);
        setActiveTab('details');
    };

    const handleDeleteTour = (tourId) => {
        setTours(tours.filter(tour => tour.id !== tourId));
        setSelectedTour(null);
        setSelectedTourId(null);
    };

    const handleEditTour = () => {
        setEditingTour(true);
    };

    const handleUpdateTour = (updatedTour) => {
        setTours(tours.map(tour => (tour.id === updatedTour.id ? updatedTour : tour)));
        setSelectedTour(updatedTour);
        setEditingTour(false);
    };

    const handleCancelUpdate = () => {
        setEditingTour(false);
    };

    let displayComponent;
    if (creatingTour) {
        displayComponent = <TourForm onCreateTour={handleCreateTour} />;
    } else if (editingTour) {
        displayComponent = <TourUpdate tour={selectedTour} onUpdateTour={handleUpdateTour} onCancel={handleCancelUpdate} />;
    } else if (selectedTour) {
        if (activeTab === 'details') {
            displayComponent = <TourDetails tour={selectedTour} onDelete={() => handleDeleteTour(selectedTour.id)} onEdit={handleEditTour} />;
        } else if (activeTab === 'logs') {
            displayComponent = <LogList tourId={selectedTour.id} />;
        }
    }

    return (
        <div>
            <h1>Tourplanner</h1>
            <div className="app-container">
                <div className="tour-list">
                    <TourList
                        tours={tours}
                        selectedTourId={selectedTourId}
                        onSelectTour={handleSelectTour}
                        onCreateTour={handleCreateButtonClick}
                    />
                </div>
                <div className="display-component">
                    <ul className="tab-list">
                        <li className={activeTab === 'details' ? 'active' : ''} onClick={() => setActiveTab('details')}>Route Details</li>
                        <li className={activeTab === 'logs' ? 'active' : ''} onClick={() => setActiveTab('logs')}>Tour Logs</li>
                    </ul>
                    {displayComponent}
                </div>
            </div>
        </div>
    );
};

export default App;
