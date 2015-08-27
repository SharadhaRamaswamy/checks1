use smartcals;

ALTER TABLE administratorlogin ADD LoginTimeStamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP after Location;
ALTER TABLE administratorlogin ADD LoginAttempts int after Password;
ALTER TABLE administratorlogin ADD PRIMARY KEY(UserName);
ALTER TABLE administratorlogin ADD Status varchar (100);
ALTER TABLE administratorlogin ADD Role varchar (100);

INSERT INTO administratorlogin (VendingMachineID, UserName, Password, Location, Status, Role) VALUES (1000, 'admin', sha('test123'), 'santa clara', 'ACTIVE', 'ADMIN');
