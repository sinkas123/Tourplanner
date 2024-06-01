import React from "react";
import axios from "axios";

const TourDetails = ({ tour, onDelete, onEdit }) => {
    if (!tour) {
        return <div>Select a tour to see the details</div>;
    }

    const onDeleteTour = async () => {
        try {
            await axios.delete(`/tour/id/${tour.id}`);
            onDelete(tour.id);
        } catch (error) {
            console.error("Error deleting the tour", error);
        }
    };

    return (
        <div>
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
            <button onClick={onEdit} style={{marginRight: '10px'}}>Update Tour</button>
            <button onClick={onDeleteTour} style={{marginRight: '10px'}}>Delete Tour</button>
            <br/>
            <button style={{marginRight: '10px', marginTop: '10px'}}>Placeholder Create Tour-Report</button>
            <button style={{marginTop: '10px'}}>Placeholder Create Summarize-Report</button>
        </div>
    );
};

export default TourDetails;