

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
    ('John', 'Doe', 'john.doe@example.com', '1985-01-15', '1234567890', (SELECT id FROM genders WHERE gender = 'male'), 'john_doe', '1234', (SELECT id FROM roles WHERE role = 'user')),
    ('Jane', 'Smith', 'jane.smith@example.com', '1990-02-20', '0987654321', (SELECT id FROM genders WHERE gender = 'female'), 'jane_smith', '5678', (SELECT id FROM roles WHERE role = 'admin')),
    ('Alice', 'Johnson', 'alice.johnson@example.com', '1988-03-30', '2345678901', (SELECT id FROM genders WHERE gender = 'female'), 'alice_johnson', '9101', (SELECT id FROM roles WHERE role = 'user')),
    ('Bob', 'Brown', 'bob.brown@example.com', '1982-04-25', '3456789012', (SELECT id FROM genders WHERE gender = 'male'), 'bob_brown', '1121', (SELECT id FROM roles WHERE role = 'user')),
    ('Charlie', 'Davis', 'charlie.davis@example.com', '1995-05-10', '4567890123', (SELECT id FROM genders WHERE gender = 'male'), 'charlie_davis', '3141', (SELECT id FROM roles WHERE role = 'admin')),
    ('Eve', 'Williams', 'eve.williams@example.com', '1992-06-05', '5678901234', (SELECT id FROM genders WHERE gender = 'female'), 'eve_williams', '5161', (SELECT id FROM roles WHERE role = 'user')),
    ('Frank', 'Taylor', 'frank.taylor@example.com', '1987-07-15', '6789012345', (SELECT id FROM genders WHERE gender = 'male'), 'frank_taylor', '7181', (SELECT id FROM roles WHERE role = 'admin')),
    ('Grace', 'Anderson', 'grace.anderson@example.com', '1991-08-25', '7890123456', (SELECT id FROM genders WHERE gender = 'female'), 'grace_anderson', '9202', (SELECT id FROM roles WHERE role = 'user')),
    ('Hank', 'Moore', 'hank.moore@example.com', '1980-09-30', '8901234567', (SELECT id FROM genders WHERE gender = 'male'), 'hank_moore', '1222', (SELECT id FROM roles WHERE role = 'user')),
    ('Ivy', 'Thomas', 'ivy.thomas@example.com', '1983-10-10', '9012345678', (SELECT id FROM genders WHERE gender = 'female'), 'ivy_thomas', '3242', (SELECT id FROM roles WHERE role = 'admin')),
    ('Jack', 'Jackson', 'jack.jackson@example.com', '1994-11-20', '0123456789', (SELECT id FROM genders WHERE gender = 'male'),  'jack_jackson', '5262', (SELECT id FROM roles WHERE role = 'user')),
    ('Kate', 'White', 'kate.white@example.com', '1986-12-05', '1234509876', (SELECT id FROM genders WHERE gender = 'female'), 'kate_white', '7282', (SELECT id FROM roles WHERE role = 'admin')),
    ('Leo', 'Harris', 'leo.harris@example.com', '1993-01-15', '2345610987', (SELECT id FROM genders WHERE gender = 'male'), 'leo_harris', '9303', (SELECT id FROM roles WHERE role = 'user')),
    ('Mia', 'Martin', 'mia.martin@example.com', '1989-02-25', '3456721098', (SELECT id FROM genders WHERE gender = 'female'), 'mia_martin', '1323', (SELECT id FROM roles WHERE role = 'user')),
    ('Nick', 'Garcia', 'nick.garcia@example.com', '1984-03-10', '4567832109', (SELECT id FROM genders WHERE gender = 'male'), 'nick_garcia', '3343', (SELECT id FROM roles WHERE role = 'admin')),
    ('Olivia', 'Martinez', 'olivia.martinez@example.com', '1991-04-30', '5678943210', (SELECT id FROM genders WHERE gender = 'female'), 'olivia_martinez', '5363', (SELECT id FROM roles WHERE role = 'user')),
    ('Paul', 'Robinson', 'paul.robinson@example.com', '1990-05-15', '6789054321', (SELECT id FROM genders WHERE gender = 'male'), 'paul_robinson', '7383', (SELECT id FROM roles WHERE role = 'admin')),
    ('Quinn', 'Clark', 'quinn.clark@example.com', '1987-06-20', '7890165432', (SELECT id FROM genders WHERE gender = 'male'), 'quinn_clark', '9404', (SELECT id FROM roles WHERE role = 'user')),
    ('Rose', 'Lewis', 'rose.lewis@example.com', '1992-07-25', '8901276543', (SELECT id FROM genders WHERE gender = 'female'), 'rose_lewis', '1424', (SELECT id FROM roles WHERE role = 'user')),
    ('Steve', 'Lee', 'steve.lee@example.com', '1988-08-30', '9012387654', (SELECT id FROM genders WHERE gender = 'male'), 'steve_lee', '3444', (SELECT id FROM roles WHERE role = 'admin')),
    ('Tina', 'Walker', 'tina.walker@example.com', '1995-09-10', '0123498765', (SELECT id FROM genders WHERE gender = 'female'), 'tina_walker', '5464', (SELECT id FROM roles WHERE role = 'user')),
    ('Uma', 'Hall', 'uma.hall@example.com', '1986-10-15', '1234509876', (SELECT id FROM genders WHERE gender = 'female'), 'uma_hall', '7484', (SELECT id FROM roles WHERE role = 'admin')),
    ('Victor', 'Young', 'victor.young@example.com', '1994-11-20', '2345610987', (SELECT id FROM genders WHERE gender = 'male'), 'victor_young', '9505', (SELECT id FROM roles WHERE role = 'user')),
    ('Wendy', 'Allen', 'wendy.allen@example.com', '1983-12-05', '3456721098', (SELECT id FROM genders WHERE gender = 'female'), 'wendy_allen', '1525', (SELECT id FROM roles WHERE role = 'user')),
    ('Xander', 'King', 'xander.king@example.com', '1982-01-10', '4567832109', (SELECT id FROM genders WHERE gender = 'male'), 'xander_king', '3545', (SELECT id FROM roles WHERE role = 'admin'));




