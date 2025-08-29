CREATE TABLE Customer (
    customerId INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    shortName VARCHAR(50) NOT NULL,
    fullName VARCHAR(100) NOT NULL,
    address1 VARCHAR(80),
    address2 VARCHAR(80),
    address3 VARCHAR(80),
    city VARCHAR(50),
    postalCode VARCHAR(10) NOT NULL
);
