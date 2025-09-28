CREATE TABLE training_sessions (
    id VARCHAR(255) PRIMARY KEY,
    date TIMESTAMP,
    duration INT,
    distance DOUBLE,
    type VARCHAR(100),
    notes VARCHAR(1000)
);
