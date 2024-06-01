import React, { useEffect, useState } from "react";
import axios from "axios";

const LogList = ({ tourId }) => {
    const [logs, setLogs] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchLogs = async () => {
            try {
                const response = await axios.get(`/tour-logs/${tourId}`);
                setLogs(response.data);
                setLoading(false);
            } catch (err) {
                setError(err.message);
                setLoading(false);
            }
        };

        if (tourId) {
            fetchLogs();
        }
    }, [tourId]);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div>
            <h2>Tour Logs</h2>
            <ul>
                {logs.map((log) => (
                    <li key={log.id}>
                        <p>Timestamp: {log.timestamp}</p>
                        <p>Comment: {log.comment}</p>
                        <p>Difficulty: {log.difficulty} / 10</p>
                        <p>Total Distance: {log.totalDistance} m</p>
                        <p>Total Time: {log.totalTime}</p>
                        <p>Rating: {log.rating} / 10</p>
                        <br/>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default LogList;
