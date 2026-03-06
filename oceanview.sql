CREATE TABLE reservations (
    reservationNumber VARCHAR(20) PRIMARY KEY,
    guestName VARCHAR(100),
    address VARCHAR(255),
    contactNumber VARCHAR(20),
    roomType VARCHAR(50),
    checkInDate DATE,
    checkOutDate DATE
);

INSERT INTO reservations VALUES
('R001','Hasindu','Galle','0712345678','Single Room','2026-02-01','2026-02-03');

