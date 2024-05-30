import './App.css';
import React, { useState } from "react";
import TourForm from "./TourForm";

const App = () => {
    const [tours, setTours] = useState([]);

    const handleCreateTour = (newTour) => {
        setTours([...tours, newTour]);
    };

    return (
        <div>
            <h1>Create a New Tour</h1>
            <TourForm onCreateTour={handleCreateTour} />
            <h2>All Tours</h2>
            <ul>
                {tours.map((tour, index) => (
                    <li key={index}>
                        <h3>{tour.name}</h3>
                        <p>{tour.description}</p>
                        <div>
                            <h4>From:</h4>
                            <p>Country: {tour.from.country}</p>
                            <p>Postal Number: {tour.from.postalNumber}</p>
                            <p>Street: {tour.from.street}</p>
                        </div>
                        <div>
                            <h4>To:</h4>
                            <p>Country: {tour.to.country}</p>
                            <p>Postal Number: {tour.to.postalNumber}</p>
                            <p>Street: {tour.to.street}</p>
                        </div>
                        <p>Transport Type: {tour.transportType}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default App;


/*
function CreateUser() {
// Function to handle the button click
const handleButtonClick = () => {
  // Hard-coded test data
  const testData = {
    name: 'Test User',
    email: 'testuser@example.com',
    age: 22
  };

  // Send a POST request to the backend
  axios.post('/person', testData)
      .then(response => {
        console.log('Data sent successfully:', response.data);
      })
      .catch(error => {
        console.error('Error sending data:', error);
      });
};

  return (
      <div>
        <h1>Button creates user</h1>
        <button onClick={handleButtonClick}>Create User</button>
      </div>
  )
}

export default CreateUser;
*/


