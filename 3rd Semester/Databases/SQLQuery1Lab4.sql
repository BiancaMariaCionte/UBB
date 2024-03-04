
use Bakery1

go
--a) 1-n relationship
--takes an email address as input and returns a BIT (boolean) value
--User-defined Function:
CREATE FUNCTION
dbo.IsValidEmail(@Email varchar(50))
RETURNS BIT
AS
BEGIN --mark the beginning of the function's body
    DECLARE @IsValid BIT = 1; -- declare a local variable @IsValid and initialize it to 1

    -- let's assume any non-empty string is valid
    IF LEN(@Email) = 0
        SET @IsValid = 0;

    RETURN @IsValid;-- return the final value of @IsValid

END;--mark the end of the function

GO

CREATE OR ALTER PROCEDURE
--declare a stored procedure named InsertCustomerProduct that takes several input parameters
InsertCustomerProduct
    @CustomerName varchar(50),
    @CustomerEmail varchar(50),
	@PhoneNumber int,
	@Address varchar(50),
    @ProductName varchar(50),
    @ProductPrice money,
    @ProductNutritionalInfo varchar(50),
    @ProductQuantityInStock int
	
AS --mark the beginning of the stored procedure's body
BEGIN
    -- Validate email using the user-defined function
    IF dbo.IsValidEmail(@CustomerEmail) = 0
    BEGIN
        RETURN 1; --if the email is invalid, return 1
    END;
	--otherwise insert data into the Customers, Products, and CustomerProducts tables

    -- Insert into Customers table
    INSERT INTO Customers (Name, Email, PhoneNo, Address)
    VALUES (@CustomerName, @CustomerEmail, @PhoneNumber, @Address);

    -- Get the CustomerID of the newly inserted customer
    DECLARE @CustomerID INT;
    SET @CustomerID = SCOPE_IDENTITY();
	--Retrieves the last identity value inserted into an identity
	--column in the same scope (Customers table)
	--and assigns it to the variable @CustomerID.
	--USED to capture the identity of the customer that was just inserted

    -- Insert into Products table
    INSERT INTO Products (Name, Price, NutritionalInfo)
    VALUES (@ProductName, @ProductPrice, @ProductNutritionalInfo);

    -- Get the ProductID of the newly inserted product
    DECLARE @ProductID INT;
    SET @ProductID = SCOPE_IDENTITY();

    -- Insert into CustomerProducts table
    INSERT INTO CustomerProducts (CustomerID, ProductID)
    VALUES (@CustomerID, @ProductID);
END;

GO

--DROP PROCEDURE InsertCustomerProduct

--GO
-- Execute the procedure with sample values
DECLARE @ResultCode INT; --store the result of the stored procedure

EXEC InsertCustomerProduct -- Executes the stored procedure with sample values and stores the result code in @ResultCode
    @CustomerName = 'Joooohn Doe',
    @CustomerEmail = 'john.doe@example.com',
	@PhoneNumber = 0756456288,
	@Address = 'Matei Basarab Street',
    @ProductName = 'Saaample Product',
    @ProductPrice = 10.99,
    @ProductNutritionalInfo = 'Sample Nutrition',
    @ProductQuantityInStock = 100;
	

-- Check the result code
IF @ResultCode = 0
BEGIN 	
	PRINT 'Success: Data inserted successfully.';
END
ELSE IF @ResultCode = 1
BEGIN
	PRINT 'Error: Invalid email address.';
END
ELSE
BEGIN
	PRINT 'Unknown error occurred.';
END;

-- Check the data in the relevant tables
SELECT * FROM Customers;
SELECT * FROM Products;
SELECT * FROM CustomerProducts;


GO

--b)
-- Declares a view named BakeryInfo that combines data from the Customers,
--CustomerProducts, Products, BakeriesProducts, and Bakeries tables.
CREATE VIEW BakeryInfo AS
SELECT
    C.Name AS CustomerName,
    P.Name AS ProductName,
    B.Address AS BakeryAddress
FROM
    Customers C
    JOIN CustomerProducts CP ON C.CustomerID = CP.CustomerID
    JOIN Products P ON CP.ProductID = P.ProductID
    JOIN BakeriesProducts BP ON P.ProductID = BP.ProductID
    JOIN Bakeries B ON BP.BakeryID = B.BakeryID;

GO

SELECT * FROM BakeryInfo;

GO

--c)
-- Declares a table named CustomerProductsLog
--to log changes in the CustomerProducts table.
CREATE TABLE CustomerProductsLog
(
    LogID INT PRIMARY KEY IDENTITY,
    LogDate DATETIME,
    TriggerType NVARCHAR(10),
    TableName NVARCHAR(50),
    AffectedRecords INT
);

GO

CREATE TRIGGER CustomerProductsTrigger
ON CustomerProducts
AFTER INSERT, UPDATE, DELETE
-- Declares a trigger named CustomerProductsTrigger 
--on the CustomerProducts table. It fires after INSERT, 
--UPDATE, or DELETE operations.
AS
BEGIN
    DECLARE @TriggerType NVARCHAR(10);
    SET @TriggerType = CASE
	--The trigger determines the type of operation (insert, update, delete)
	--and logs relevant information in the CustomerProductsLog table.
        WHEN EXISTS (SELECT * FROM INSERTED) AND EXISTS (SELECT * FROM DELETED) THEN 'UPDATE'
        WHEN EXISTS (SELECT * FROM INSERTED) THEN 'INSERT'
        WHEN EXISTS (SELECT * FROM DELETED) THEN 'DELETE'
    END;

    INSERT INTO CustomerProductsLog (LogDate, TriggerType, TableName, AffectedRecords)
    VALUES (GETDATE(), @TriggerType, 'CustomerProducts', );
END;

SELECT * FROM CustomerProductsLog
UPDATE CustomerProducts
SET ProductID = ProductID
WHERE CustomerID IS NULL
--d)
--  query with WHERE and JOIN clauses
--Retrieves data from the Customers and Products tables
--using JOIN clauses and applies a WHERE clause to filter
--products with a price between 2 and 5. 
--The result is ordered by customer name.
SELECT C.Name AS CustomerName, P.Name AS ProductName
FROM Customers C
JOIN CustomerProducts CP ON C.CustomerID = CP.CustomerID
JOIN Products P ON CP.ProductID = P.ProductID
WHERE P.Price BETWEEN 2 AND 5
ORDER BY C.Name;
