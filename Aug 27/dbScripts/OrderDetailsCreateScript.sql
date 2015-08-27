
CREATE TABLE `orderdetails` (
  `OrderNumber` int(11) NOT NULL,
  `SmartCalCardNumber` int(11) DEFAULT NULL,
  `PaymentType` varchar(100) DEFAULT NULL,
  `SKU` int(11) DEFAULT NULL,
  `LineItemPrice` double DEFAULT NULL,
  `LineItemQuantity` int(11) DEFAULT NULL,
  `TotalAmount` double DEFAULT NULL,
  `SalesTax` double DEFAULT NULL,
  `OrderTimeStamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `OrderStatus` varchar(100) DEFAULT NULL,
  `Currency` varchar(3) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `vm_id` int(11) NOT NULL,
  PRIMARY KEY (`OrderNumber`),
  KEY `SmartCalCardNumber` (`SmartCalCardNumber`),
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`SmartCalCardNumber`) REFERENCES `smartcalcarddetails` (`SmartCalCardNumber`) ON DELETE CASCADE
);

