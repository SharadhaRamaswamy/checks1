create database SmartCals8;


use SmartCals8;

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



CREATE TABLE IF NOT EXISTS SmartCalCardDetails (	
	SmartCalCardNumber INT PRIMARY KEY AUTO_INCREMENT,
	CardBalance DOUBLE,
	LastModifiedTimestamp  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE SmartCalCardDetails AUTO_INCREMENT=100001;

CREATE TABLE IF NOT EXISTS Inventory (
	SKU INT PRIMARY KEY AUTO_INCREMENT,
    ProductID INT,
    VendingMachineID INT,
    Price DOUBLE,	
    Quantity INT,
    InventoryStatus varchar(100),
    LastModifiedTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE,
    FOREIGN KEY(VendingMachineID) REFERENCES VendingMachine(VendingMachineID) ON DELETE CASCADE
);

ALTER TABLE Inventory AUTO_INCREMENT=1000;


CREATE TABLE IF NOT EXISTS OrderDetails (	
	OrderNumber INT PRIMARY KEY AUTO_INCREMENT,
    SmartCalCardNumber INT,
	PaymentType varchar(100),
	SKU INT,
	LineItemPrice DOUBLE,
	LineItemQuantity INT,
	TotalAmount DOUBLE,
	OrderTimeStamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	OrderStatus varchar(100),
  ProductId int(11),
  VendingMachineId int(11)
);

CREATE TABLE SalesSummary (
 VendingMachineID int(11) DEFAULT NULL,
 TotalSalesUSD double DEFAULT NULL,
 LastModifiedTimestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY VendingMachineID_idx (`VendingMachineID`),
  CONSTRAINT VendingMachineID FOREIGN KEY (VendingMachineID) REFERENCES vendingmachine (VendingMachineID) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS AdministratorLogin (
	VendingMachineID INT,
    UserName varchar(30),
	Password varchar(150),
    Location varchar(100),
    LoginAttempts int,
	Status varchar (100),
    LoginTimeStamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (UserName, VendingMachineID),
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

insert into product (ProductID,ProductName,Category) values(100,"diet coke","beverage"),(101,"Diet Pepsi","beverage"),(102,"AppleJuice","Beverage"),(103,"frappuccino","Beverage"),(104,"Green o2 water","Beverage"),(105,"vita coco water","Beverage"),(106,"simply orange","beverage"),(200,"Chocolate Peanut Butter","Snacks"),(201,"Salted Cashews","Snacks"),(202,"pistchios","Snacks"),(203,"lays BBQ","Snacks"),(204,"dorittos nacho's cheese","Snacks"),(205,"Natural Valley Oats & Honey Granolaa Bar","Snacks"),(206,"salted peanuts","Snacks"),(207,"almonds","Snacks"),(208,"wheat thins","Snacks"),(300,"kitkat","Candy"),(301,"Twix","Candy"),(302,"MilkyWay","Candy"),(303,"Hershey's Alomond Bar","Candy"),(304,"Snickers","Candy"),(305,"M&M peamut","Candy");


alter table nutritionalinfo
modify column ServingSize varchar(50);
alter table nutritionalinfo
modify column Calories varchar(50);

alter table nutritionalinfo
modify column TotalFat varchar(50);


alter table nutritionalinfo
modify column SaturatedFat varchar(50);


alter table nutritionalinfo
modify column TransFat varchar(50);


alter table nutritionalinfo
modify column Cholestrol varchar(50);


alter table nutritionalinfo
modify column Sodium varchar(50);


alter table nutritionalinfo
modify column TotalCarbs varchar(50);

alter table nutritionalinfo
modify column DietaryFiber varchar(50);

alter table nutritionalinfo
modify column Sugars varchar(50);

alter table nutritionalinfo
modify column Protein varchar(50);

alter table nutritionalinfo
modify column Iron varchar(50);

insert into nutritionalinfo values (100,"12 floz","0cal","0g","0g","0g","0mg","40mg","0g","0g","0g","0g","0g","gluten free");
insert into nutritionalinfo values (101,"12 floz","0cal","0g","0g","0g","0g","35mg","0g","0g","0g","0g","0g","gluten free");
insert into nutritionalinfo values (102,"8.5 floz","120cal","0.3g","0.1g","0.2g","0mg","10mg","30g","0.5g","25g","0.3g","1%","low fat");
insert into nutritionalinfo values (103,"9.5 floz","200cal","3g","2g","1g","15mg","100mg","37g","0g","32g","6g","0g","low calorie");
insert into nutritionalinfo values (104,"8 floz","0cal","0g","0g","0g","0mg","0mg","0g","0g","0.4g","0g","0g","zero fat");
insert into nutritionalinfo values (105,"8 floz","45cal","0g","0g","0g","0mg","25mg","11g","0g","11g","0g","0g","low sodium");
insert into nutritionalinfo values (106,"8 floz","110cal","0g","0g","0g","0mg","0mg","26g","3g","23g","1%","2g","gluten free");

insert into nutritionalinfo values (200,"1 bar","160cal","5g","1g","2g","5mg","270mg","25g","17g","1g","20g","2g","low carb");
insert into nutritionalinfo values (201,"100g","581cal","48g","8g","4g","0mg","308mg","30g","3g","5g","17g","3g","high sodium");
insert into nutritionalinfo values (202,"100g","562cal","45g","6g","2g","0mg","1mg","28g","10g","8g","2g","1g","high calorie");
insert into nutritionalinfo values (203,"27 chips","270cal","17g","1.5g","2g","0mg","270mg","27g","1g","3g","3g","1g","high sodium");
insert into nutritionalinfo values (204,"11 chips","140cal","8g","1.5g","1g","0mg","210mg","17g","1g","0g","2g","1g","low calorie");
insert into nutritionalinfo values (205,"2 bar","190cal","7g","1g","1g","0mg","180mg","29g","2g","11g","3g","2g","low sodium");
insert into nutritionalinfo values (206,"100g","599cal","53g","9g","3g","0mg","320mg","15g","9g","4g","28g","2g","high calorie");
insert into nutritionalinfo values (207,"100g","576cal","49g","3.7g","2g","0mg","1mg","22g","12g","3.9g","705mg","3g","high protein");
insert into nutritionalinfo values (208,"16 crackers","140cal","5g","1g","1g","0mg","230mg","22g","2g","4g","2g","1g","low fat");
insert into nutritionalinfo values (300,"4 bar","210cal","11g","2g","2g","0mg","30mg","27g","1g","21g","3g","2g","zero cholestrol");
insert into nutritionalinfo values (301,"2 bar","250cal","12g","7g","2g","5mg","100mg","34g","1g","24g","2g","1g","low sodium");
insert into nutritionalinfo values (302,"1 bar","240cal","9g","7g","1g","10mg","75mg","37g","1g","31g","2g","2g","low sodium");
insert into nutritionalinfo values (303,"1 bar","210cal","13g","6g","2g","10mg","25mg","21g","2g","19g","4g","4g","low sodium");
insert into nutritionalinfo values (304,"1 unit","250cal","12g","4.5g","2g","5mg","120mg","33g","1g","27g","4g","2g","low sodium");
insert into nutritionalinfo values (305,"1 unit","250cal","13g","5g","2g","5mg","25mg","30g","2g","25g","5g","2g","low sodium");

alter table vendingmachine
add type varchar(50);

insert into vendingmachine values(1000,"santa clara","active","school");
insert into vendingmachine values(2000,"santa clara","active","school");

insert into inventory(ProductID,VendingMachineID,Price,Quantity,InventoryStatus) values(100,1000,12.99,1,"available"),(101,1000,12.99,1,"available"),(102,1000,2.69,1,"available"),(103,1000,2.25,1,"available"),(104,1000,2.00,1,"available");
insert into inventory(ProductID,VendingMachineID,Price,Quantity,InventoryStatus) values(103,2000,2.25,1,"available"),(105,2000,3.26,1,"available"),(106,2000,1,1,"available");
insert into inventory(ProductID,VendingMachineID,Price,Quantity,InventoryStatus) values(200,1000,1.89,1,"available"),(201,1000,10.00,1,"available"),(202,1000,10.00,1,"available"),(203,1000,2.48,1,"available"),(204,1000,2.36,1,"available");
insert into inventory(ProductID,VendingMachineID,Price,Quantity,InventoryStatus) values(205,2000,1.09,1,"available"),(206,2000,10.00,1,"available"),(207,2000,10.00,1,"available"),(208,2000,3.89,1,"available");
insert into inventory(ProductID,VendingMachineID,Price,Quantity,InventoryStatus) values(300,1000,2.99,1,"available"),(301,1000,2.29,1,"available"),(302,1000,2.89,1,"available");
insert into inventory(ProductID,VendingMachineID,Price,Quantity,InventoryStatus) values(303,2000,2.89,1,"available"),(304,2000,3.09,1,"available"),(305,2000,2.10,1,"available");

alter table product
add Price double;

update product
set Price = 12.99 where ProductID = 100;

update product
set Price = 12.99 where ProductID = 101;

update product
set Price = 2.69 where ProductID = 102;

update product
set Price = 2.25 where ProductID = 103;

update product
set Price = 2 where ProductID = 104;

update product
set Price = 3.26 where ProductID = 105;

update product
set Price = 1 where ProductID = 106;

update product
set Price = 1.89 where ProductID = 200;

update product
set Price = 10 where ProductID = 201;

update product
set Price = 10 where ProductID = 202;

update product
set Price = 2.48 where ProductID = 203;

update product
set Price = 2.36 where ProductID = 204;

update product
set Price = 1.09 where ProductID = 205;

update product
set Price = 10 where ProductID = 206;

update product
set Price = 10 where ProductID = 207;

update product
set Price = 3.89 where ProductID = 208;

update product
set Price = 2.99 where ProductID = 300;

update product
set Price = 2.29 where ProductID = 301;

update product
set Price = 2.89 where ProductID = 302;

update product
set Price = 2.89 where ProductID = 303;

update product
set Price = 3.09 where ProductID = 304;


update product
set Category='Snack' where Category='Snacks'

update nutritionalinfo
set SmartTag = "low fat"
where SmartTag = "zero fat";

update nutritionalinfo
set SmartTag = "low fat"
where SmartTag = "zero cholestrol";

update nutritionalinfo
set SmartTag = "low calorie"
where SmartTag = "low carb";

update nutritionalinfo
set SmartTag = "high protein"
where SmartTag = "high calorie";


