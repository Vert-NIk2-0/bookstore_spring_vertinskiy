CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    second_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    date_of_birth DATE,
    phone_number VARCHAR(20),
    gender VARCHAR(10)
);

INSERT INTO users (first_name, second_name, email, date_of_birth, phone_number, gender)
VALUES
    ('John', 'Doe', 'john.doe@example.com', '1985-01-15', '1234567890', 'Male'),
    ('Jane', 'Smith', 'jane.smith@example.com', '1990-02-20', '0987654321', 'Female'),
    ('Alice', 'Johnson', 'alice.johnson@example.com', '1988-03-30', '2345678901', 'Female'),
    ('Bob', 'Brown', 'bob.brown@example.com', '1982-04-25', '3456789012', 'Male'),
    ('Charlie', 'Davis', 'charlie.davis@example.com', '1995-05-10', '4567890123', 'Male'),
    ('Eve', 'Williams', 'eve.williams@example.com', '1992-06-05', '5678901234', 'Female'),
    ('Frank', 'Taylor', 'frank.taylor@example.com', '1987-07-15', '6789012345', 'Male'),
    ('Grace', 'Anderson', 'grace.anderson@example.com', '1991-08-25', '7890123456', 'Female'),
    ('Hank', 'Moore', 'hank.moore@example.com', '1980-09-30', '8901234567', 'Male'),
    ('Ivy', 'Thomas', 'ivy.thomas@example.com', '1983-10-10', '9012345678', 'Female'),
    ('Jack', 'Jackson', 'jack.jackson@example.com', '1994-11-20', '0123456789', 'Male'),
    ('Kate', 'White', 'kate.white@example.com', '1986-12-05', '1234509876', 'Female'),
    ('Leo', 'Harris', 'leo.harris@example.com', '1993-01-15', '2345610987', 'Male'),
    ('Mia', 'Martin', 'mia.martin@example.com', '1989-02-25', '3456721098', 'Female'),
    ('Nick', 'Garcia', 'nick.garcia@example.com', '1984-03-10', '4567832109', 'Male'),
    ('Olivia', 'Martinez', 'olivia.martinez@example.com', '1991-04-30', '5678943210', 'Female'),
    ('Paul', 'Robinson', 'paul.robinson@example.com', '1990-05-15', '6789054321', 'Male'),
    ('Quinn', 'Clark', 'quinn.clark@example.com', '1987-06-20', '7890165432', 'Male'),
    ('Rose', 'Lewis', 'rose.lewis@example.com', '1992-07-25', '8901276543', 'Female'),
    ('Steve', 'Lee', 'steve.lee@example.com', '1988-08-30', '9012387654', 'Male'),
    ('Tina', 'Walker', 'tina.walker@example.com', '1995-09-10', '0123498765', 'Female'),
    ('Uma', 'Hall', 'uma.hall@example.com', '1986-10-15', '1234509876', 'Female'),
    ('Victor', 'Young', 'victor.young@example.com', '1994-11-20', '2345610987', 'Male'),
    ('Wendy', 'Allen', 'wendy.allen@example.com', '1983-12-05', '3456721098', 'Female'),
    ('Xander', 'King', 'xander.king@example.com', '1982-01-10', '4567832109', 'Male');
