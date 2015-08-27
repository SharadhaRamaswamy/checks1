/*RUN THIS ONCE

create database SmartCals;  */


use SmartCals;

CREATE TABLE IF NOT EXISTS Product (
	ProductID int PRIMARY KEY, 
    ProductName varchar(200), 
    Category varchar(100)
);

CREATE TABLE IF NOT EXISTS VendingMachine (
	VendingMachineID int PRIMARY KEY,
	Location varchar(200), 
	Status varchar(100)
);

/*CREATE TABLE IF NOT EXISTS Customer	(
	SmartCalCardNumber INT PRIMARY KEY AUTO_INCREMENT, 
    CustomerName varchar(250),
    CreatedOn TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);*/

CREATE TABLE IF NOT EXISTS SmartCalCardDetails (	
	SmartCalCardNumber INT PRIMARY KEY AUTO_INCREMENT,
	CardBalance DOUBLE,
	LastModifiedTimestamp  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE SmartCalCardDetails AUTO_INCREMENT=100001;

CREATE TABLE IF NOT EXISTS Inventory (
	SKU INT PRIMARY KEY, 
    ProductID INT,
    VendingMachineID INT,
    Price DOUBLE,	
    Quantity INT,
    InventoryStatus varchar(100),
    LastModifiedTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE,
    FOREIGN KEY(VendingMachineID) REFERENCES VendingMachine(VendingMachineID) ON DELETE CASCADE
);


/* CREATE TABLE IF NOT EXISTS OrderDetails (	
	OrderNumber INT PRIMARY KEY,
    SmartCalCardNumber INT,
    FOREIGN KEY(SmartCalCardNumber) REFERENCES smartcalcarddetails(SmartCalCardNumber) ON DELETE CASCADE,
	PaymentType varchar(100),
	SKU INT,
	LineItemPrice DOUBLE,
	LineItemQuantity INT,
	TotalAmount DOUBLE,
	SalesTax DOUBLE,
	OrderTimeStamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	OrderStatus varchar(100),
    Currency varchar(3)    
); */

CREATE TABLE IF NOT EXISTS SalesSummary (
	OrderNumber INT,
    FOREIGN KEY(OrderNumber) REFERENCES OrderDetails(OrderNumber) ON DELETE CASCADE,
	DailySalesAmount DOUBLE,
    LastModifiedTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS AdministratorLogin (
	VendingMachineID INT,
    UserName varchar(30),
	Password varchar(30), 
    Location varchar(100), 
    FOREIGN KEY (VendingMachineID) REFERENCES VendingMachine(VendingMachineID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS NutritionalInfo (
	ProductID INT,
    FOREIGN KEY(ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE,
	ServingSize INT,
    Calories INT,
    TotalFat INT,
    SaturatedFat INT,
    TransFat INT,
    Cholestrol INT,
    Sodium INT,
    TotalCarbs INT,
    DietaryFiber INT,
    Sugars INT,
    Protein INT,
    Iron INT,
	SmartTag varchar(200)
);


desc Product;
desc Inventory;
desc VendingMachine;
desc SmartCalCardDetails;
desc OrderDetails;
desc SalesSummary;
desc AdministratorLogin;
desc NutritionalInfo;