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
            <h2>Tour Details</h2>
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
            <br/>
            <button onClick={onEdit}>Update Tour</button>
            <br/>
            <button onClick={onDeleteTour}>Delete Tour</button>
            <br/>
            <button>Placeholder Create Tour-Report</button>
            <br/>
            <button>Placeholder Create Summarize-Report</button>

        </div>
    );
};

export default TourDetails;