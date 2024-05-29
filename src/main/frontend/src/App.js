import './App.css';
import axios from 'axios';
import React from "react";

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
