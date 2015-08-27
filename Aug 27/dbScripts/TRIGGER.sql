use smartcals;

-- create trigger to update salessummary table when an insert is performed in Orderdetails table
DELIMITER $$
CREATE TRIGGER order_after_insert
AFTER INSERT
	ON OrderDetails FOR EACH ROW

BEGIN
	
	IF NOT EXISTS (SELECT 1 FROM SalesSummary WHERE VendingMachineID = NEW.VendingMachineID) THEN
		 INSERT INTO SalesSummary (VendingMachineID, TotalSalesUSD) VALUES (NEW.VendingMachineID, NEW.TotalAmount);
	ELSE
		UPDATE SalesSummary SET TotalSalesUSD = TotalSalesUSD + NEW.TotalAmount
			WHERE VendingMachineID = NEW.VendingMachineID;
	END IF;
    END $$
DELIMITER ;


-- DROP TRIGGER IF EXISTS order_after_insert;