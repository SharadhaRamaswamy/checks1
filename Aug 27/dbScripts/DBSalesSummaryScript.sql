use smartcals;

CREATE TABLE `salessummary` (
 `VendingMachineID` int(11) DEFAULT NULL,
 -- `DailySalesAmount` double DEFAULT NULL,
 `TotalSalesUSD` double DEFAULT NULL,
 `LastModifiedTimestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `VendingMachineID_idx` (`VendingMachineID`),
  CONSTRAINT `VendingMachineID` FOREIGN KEY (`VendingMachineID`) REFERENCES `vendingmachine` (`VendingMachineID`) ON DELETE CASCADE
);

