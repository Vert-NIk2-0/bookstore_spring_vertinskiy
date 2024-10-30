

CREATE TYPE role_type AS ENUM ('user', 'admin');

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role role_type NOT NULL
);

INSERT INTO roles (role) VALUES ('user'), ('admin');

CREATE TYPE gender_type AS ENUM ('male', 'female');

CREATE TABLE genders (
    id SERIAL PRIMARY KEY,
    gender gender_type NOT NULL
);

INSERT INTO genders (gender) VALUES ('male'), ('female');

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE NOT NULL,
    date_of_birth DATE,
    phone_number VARCHAR(20),
    gender_id INT REFERENCES genders(id),
    login VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL,
    role_id INT REFERENCES roles(id) DEFAULT (SELECT id FROM roles WHERE role = 'user')
);

INSERT INTO users (first_name, last_name, email, date_of_birth, phone_number, gender_id, login, password, role_id)
VALUES
    ('John', 'Doe', 'john.doe@example.com', '1985-01-15', '1234567890', (SELECT id FROM genders WHERE gender = 'male'), '1234', 'john_doe', (SELECT id FROM roles WHERE role = 'user')),
    ('Jane', 'Smith', 'jane.smith@example.com', '1990-02-20', '0987654321', (SELECT id FROM genders WHERE gender = 'female'), '5678', 'jane_smith', (SELECT id FROM roles WHERE role = 'admin')),
    ('Alice', 'Johnson', 'alice.johnson@example.com', '1988-03-30', '2345678901', (SELECT id FROM genders WHERE gender = 'female'), '9101', 'alice_johnson', (SELECT id FROM roles WHERE role = 'user')),
    ('Bob', 'Brown', 'bob.brown@example.com', '1982-04-25', '3456789012', (SELECT id FROM genders WHERE gender = 'male'), '1121', 'bob_brown', (SELECT id FROM roles WHERE role = 'user')),
    ('Charlie', 'Davis', 'charlie.davis@example.com', '1995-05-10', '4567890123', (SELECT id FROM genders WHERE gender = 'male'), '3141', 'charlie_davis', (SELECT id FROM roles WHERE role = 'admin')),
    ('Eve', 'Williams', 'eve.williams@example.com', '1992-06-05', '5678901234', (SELECT id FROM genders WHERE gender = 'female'), '5161', 'eve_williams', (SELECT id FROM roles WHERE role = 'user')),
    ('Frank', 'Taylor', 'frank.taylor@example.com', '1987-07-15', '6789012345', (SELECT id FROM genders WHERE gender = 'male'), '7181', 'frank_taylor', (SELECT id FROM roles WHERE role = 'admin')),
    ('Grace', 'Anderson', 'grace.anderson@example.com', '1991-08-25', '7890123456', (SELECT id FROM genders WHERE gender = 'female'), '9202', 'grace_anderson', (SELECT id FROM roles WHERE role = 'user')),
    ('Hank', 'Moore', 'hank.moore@example.com', '1980-09-30', '8901234567', (SELECT id FROM genders WHERE gender = 'male'), '1222', 'hank_moore', (SELECT id FROM roles WHERE role = 'user')),
    ('Ivy', 'Thomas', 'ivy.thomas@example.com', '1983-10-10', '9012345678', (SELECT id FROM genders WHERE gender = 'female'), '3242', 'ivy_thomas', (SELECT id FROM roles WHERE role = 'admin')),
    ('Jack', 'Jackson', 'jack.jackson@example.com', '1994-11-20', '0123456789', (SELECT id FROM genders WHERE gender = 'male'), '5262', 'jack_jackson', (SELECT id FROM roles WHERE role = 'user')),
    ('Kate', 'White', 'kate.white@example.com', '1986-12-05', '1234509876', (SELECT id FROM genders WHERE gender = 'female'), '7282', 'kate_white', (SELECT id FROM roles WHERE role = 'admin')),
    ('Leo', 'Harris', 'leo.harris@example.com', '1993-01-15', '2345610987', (SELECT id FROM genders WHERE gender = 'male'), '9303', 'leo_harris', (SELECT id FROM roles WHERE role = 'user')),
    ('Mia', 'Martin', 'mia.martin@example.com', '1989-02-25', '3456721098', (SELECT id FROM genders WHERE gender = 'female'), '1323', 'mia_martin', (SELECT id FROM roles WHERE role = 'user')),
    ('Nick', 'Garcia', 'nick.garcia@example.com', '1984-03-10', '4567832109', (SELECT id FROM genders WHERE gender = 'male'), '3343', 'nick_garcia', (SELECT id FROM roles WHERE role = 'admin')),
    ('Olivia', 'Martinez', 'olivia.martinez@example.com', '1991-04-30', '5678943210', (SELECT id FROM genders WHERE gender = 'female'), '5363', 'olivia_martinez', (SELECT id FROM roles WHERE role = 'user')),
    ('Paul', 'Robinson', 'paul.robinson@example.com', '1990-05-15', '6789054321', (SELECT id FROM genders WHERE gender = 'male'), '7383', 'paul_robinson', (SELECT id FROM roles WHERE role = 'admin')),
    ('Quinn', 'Clark', 'quinn.clark@example.com', '1987-06-20', '7890165432', (SELECT id FROM genders WHERE gender = 'male'), '9404', 'quinn_clark', (SELECT id FROM roles WHERE role = 'user')),
    ('Rose', 'Lewis', 'rose.lewis@example.com', '1992-07-25', '8901276543', (SELECT id FROM genders WHERE gender = 'female'), '1424', 'rose_lewis', (SELECT id FROM roles WHERE role = 'user')),
    ('Steve', 'Lee', 'steve.lee@example.com', '1988-08-30', '9012387654', (SELECT id FROM genders WHERE gender = 'male'), '3444', 'steve_lee', (SELECT id FROM roles WHERE role = 'admin')),
    ('Tina', 'Walker', 'tina.walker@example.com', '1995-09-10', '0123498765', (SELECT id FROM genders WHERE gender = 'female'), '5464', 'tina_walker', (SELECT id FROM roles WHERE role = 'user')),
    ('Uma', 'Hall', 'uma.hall@example.com', '1986-10-15', '1234509876', (SELECT id FROM genders WHERE gender = 'female'), '7484', 'uma_hall', (SELECT id FROM roles WHERE role = 'admin')),
    ('Victor', 'Young', 'victor.young@example.com', '1994-11-20', '2345610987', (SELECT id FROM genders WHERE gender = 'male'), '9505', 'victor_young', (SELECT id FROM roles WHERE role = 'user')),
    ('Wendy', 'Allen', 'wendy.allen@example.com', '1983-12-05', '3456721098', (SELECT id FROM genders WHERE gender = 'female'), '1525', 'wendy_allen', (SELECT id FROM roles WHERE role = 'user')),
    ('Xander', 'King', 'xander.king@example.com', '1982-01-10', '4567832109', (SELECT id FROM genders WHERE gender = 'male'), '3545', 'xander_king', (SELECT id FROM roles WHERE role = 'admin'));




