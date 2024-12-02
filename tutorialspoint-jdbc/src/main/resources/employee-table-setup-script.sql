DROP database if exists TUTORIALSPOINT;

Create database if not exists TUTORIALSPOINT;

use TUTORIALSPOINT;

Drop table if exists Employees;

Create table Employees
	(
    id int not null primary key auto_increment,
    age int not null,
    first varchar(255),
    last varchar(255)
    );

-- INSERT INTO Employees (age, first, last) VALUES (100, 18, 'Zara', 'Ali');
-- INSERT INTO Employees VALUES (101, 25, 'Mahnaz', 'Fatma');
-- INSERT INTO Employees VALUES (102, 30, 'Zaid', 'Khan');
-- INSERT INTO Employees VALUES (103, 28, 'Sumit', 'Mittal');

INSERT INTO Employees (age, first, last) VALUES (18, 'Zara', 'Ali');
INSERT INTO Employees (age, first, last) VALUES (25, 'Mahnaz', 'Fatma');
INSERT INTO Employees (age, first, last) VALUES (30, 'Zaid', 'Khan');
INSERT INTO Employees (age, first, last) VALUES (28, 'Sumit', 'Mittal');

ALTER TABLE Employees AUTO_INCREMENT=100; 