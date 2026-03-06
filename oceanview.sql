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
('R004', 'Ravindu', 'Matale', '0719081468', 'Double Room', '2026-03-04', '2026-03-07'),
('R002', 'Chamath', 'Deniyaya', '0719868630', 'Single Room', '2026-03-01', '2026-03-03'),
('R003', 'Renuka', 'Colombo', '0712996930', 'Double Room', '2026-03-03', '2026-03-07'),
('R005', 'Anjulee ', 'Dehiwala', '0773036931', 'Family Suite', '2026-03-05', '2026-03-07');

