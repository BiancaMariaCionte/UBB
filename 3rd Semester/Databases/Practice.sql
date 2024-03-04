USE Faculty
go

CREATE TABLE Student
(
SId INT PRIMARY KEY IDENTITY,
Name VARCHAR(50) NOT NULL,
CityFaculty VARCHAR(50) CHECK(CityFaculty='Cluj Napoca' OR CityFaculty='Baia Mare')
)


CREATE TABLE Category
(
CId INT PRIMARY KEY IDENTITY(1,1),
Name VARCHAR(50)
)

CREATE TABLE Group821
(
GId INT PRIMARY KEY IDENTITY,
GName VARCHAR(50) NOT NULL,
)

ALTER TABLE Group821
ADD GroupNo INT 


UPDATE Group821
SET GroupNo = 821
WHERE GName='Mara'


INSERT INTO Group821 VALUES('Bianca')
INSERT INTO Group821 VALUES('Mara')

CREATE TABLE Group822
(
GId INT PRIMARY KEY IDENTITY,
GName VARCHAR(50) NOT NULL,
)

INSERT INTO Group822 VALUES('Andra')
INSERT INTO Group822 VALUES('Matei')
INSERT INTO Group822 VALUES('Ana')
INSERT INTO Group822 VALUES('Doru')

ALTER TABLE Group822
ADD GroupNo INT 

UPDATE Group822
SET GroupNo = 822
WHERE GName='Doru'

CREATE TABLE Product
(
PId INT PRIMARY KEY IDENTITY(1,1),
NameP VARCHAR(50) NOT NULL,
Price INT CHECK(Price >0) NOT NULL,
CId INT FOREIGN KEY REFERENCES Category(CId)
)

CREATE TABLE Client
(
ClId INT PRIMARY KEY IDENTITY(1,1),
NameC VARCHAR(50) NOT NULL,
MobilePhone CHAR(11) DEFAULT '40700000000'
)

CREATE TABLE Shopping
(
ClientId INT FOREIGN KEY REFERENCES Client(ClId),
ProductId INT FOREIGN KEY REFERENCES Product(PId),
ShoppingId INT PRIMARY KEY(Clientid, ProductId),
)

CREATE TABLE Review1
(
RId INT PRIMARY KEY IDENTITY,
ClId INT REFERENCES Client
ON DELETE CASCADE
ON UPDATE NO ACTION
)

go
ALTER TABLE Student
DROP CONSTRAINT FK_Student
ADD CONSTRAINT FK_Student FOREIGN KEY(SId) REFERENCES Group821(GId)
go

DROP TABLE Student

INSERT INTO Client VALUES(2,'Ana')

UPDATE Client
SET MobilePhone='08345678', NameC='Ana'
WHERE MobilePhone='Ana'

DELETE FROM Client
WHERE NameC LIKE 'A%'

CREATE TABLE Test
(
NameTest VARCHAR(50)
)

ALTER TABLE Test
ALTER COLUMN NameTest VARCHAR(50) NOT NULL

CREATE TABLE Prices
(
NameP VARCHAR(50) NOT NULL,
Price INT NOT NULL,
PId INT PRIMARY KEY
)

SELECT NameP
FROM Prices
WHERE NameP LIKE 'C%'

INSERT INTO Prices VALUES('Canon d5K',100,1,1)
INSERT INTO Prices VALUES('Nikon D850', 200,2,2)
INSERT INTO Prices VALUES('Sony D0', 300,3,2)
INSERT INTO Prices VALUES('DSLR D0', 400,4,1)
INSERT INTO Prices VALUES('Canon d5K', 500,7,4)
INSERT INTO Prices VALUES('Sony d5K',100,8,1, 'Amazingg')

SELECT P.NameP, P.Price AS NEW_Price,
OLD_Price = P.Price*2, DiscountPrice= P.Price/5
FROM Prices P

ALTER TABLE Prices
ADD PId INT NOT NULL

ALTER TABLE Prices
ALTER COLUMN PId INT NOT NULL

DROP TABLE Prices

ALTER TABLE Prices
ADD CONSTRAINT PK_Prices PRIMARY KEY(PId)

CREATE TABLE Category1
(
CId INT PRIMARY KEY IDENTITY,
Name VARCHAR(50) NOT NULL
)

ALTER TABLE Prices
ADD CId INT NOT NULL

ALTER TABLE Prices
ADD CONSTRAINT FK_Prices FOREIGN KEY(CId) REFERENCES Category1(CId)

INSERT INTO Category1 VALUES('Professional')
INSERT INTO Category1 VALUES('Usual')
INSERT INTO Category1 VALUES('Canon d5K')


-- INNER JOIN with WHER clause
SELECT C.CId, P.NameP, P.Price, C.Name
FROM Category1 C, Prices P
WHERE C.CId = P.CId

--INNER JOIN
SELECT C.CId, P.NameP, P.Price
FROM Category1 C INNER JOIN Prices P ON C.CId = P.CId

--CROSS JOIN
SELECT C.CId, P.PId
FROM Prices P CROSS JOIN Category1 C

--RIGHT JOIN
SELECT * FROM Prices
SELECT * FROM Category1

SELECT P.NameP, P.Price, C.CId, C.Name
FROM Prices P
RIGHT JOIN Category1 C
ON P.CId = C.CId

--LEFT JOIN
SELECT P.NameP, P.Price, C.CId, C.Name
FROM Prices P
LEFT JOIN Category1 C
ON P.NameP = C.Name

--FULL JOIN
SELECT P.NameP, P.Price, C.CId, C.Name
FROM Prices P
FULL JOIN Category1 C
ON  P.NameP = C.Name

--NESTED QUERIES 
SELECT P.NameP, C.Name, P.Price
FROM Prices P INNER JOIN Category1 C ON P.CId = C.CId
WHERE P.Price>100

--or:
SELECT P.NameP, P.Price
FROM Prices P
WHERE P.Price>100 AND P.CId IN(SELECT C.CId FROM Category1 C)

--or:
SELECT P.NameP, P.CId, P.Price
FROM Prices P
WHERE P.Price>100 AND EXISTS( SELECT * FROM Category1 C WHERE C.CId = P.CId)

--Insert Table
SELECT NameP, Price
INTO dbo.InsertTable
FROM Prices
WHERE NameP LIKE 'C%'

SELECT * FROM InsertTable

--UNION
SELECT P.NameP, P.Price
FROM Prices P
UNION ALL
SELECT C.Name, C.CId
FROM Category1 C

--INTERSECT
SELECT P.CId, P.NameP
FROM Prices P
INTERSECT 
SELECT C.CId, C.Name
FROM Category1 C

--EXCEPT
SELECT P.NameP
FROM Prices P
EXCEPT
SELECT C.Name
FROM Category1 C

--GROUP BY
SELECT P.NameP, COUNT(*) AS NumberOfProducts
FROM Prices P 
GROUP BY P.NameP
HAVING COUNT(*)>1
ORDER BY COUNT(*) DESC

--------------------------------------------------------------------------------------------------------------------------------------
create table M(
PK1 int not null,
PK2 int not null,
M1 varchar(100) not null,
M2 varchar(50) not null,
M3 int,
M4 date,
M5 varchar(20),
CONSTRAINT pk_M PRIMARY KEY(PK1, PK2))


insert into M(PK1, PK2, M1, M2, M3, M4, M5) values
(11, 1, 'Marin Preda', 'Editura Didactica', 15, '12/01/2000', 'FB'),
(11, 22, 'Ion Agarbiceanu', 'Humanitas', 5, '01/01/1990', 'B'),
(11, 3, 'Mihai Eminescu', 'Editura Didactica', 8, '4/6/1890', 'FB'),
(12, 1, 'Marin Preda', 'Litera', 10, '4/9/1900', 'B'),
(12, 22, 'Camil Petrescu', 'Carturesti', 1, '6/7/1987', 'B'),
(12, 3, 'Mihai Eminescu', 'Carturesti', 6, '3/5/2002', 'S'),
(13, 1, 'Mircea Eliade', 'Litera', 20, '1/2/2010', 'S'),
(13, 22, 'Mircea Cartrescu', 'Editura Didactica', 10, '3/14/2000', 'E'),
(13, 3, 'Lucian Blaga', 'Litera', 15, '11/25/2000', 'FB'),
(14, 1, 'Lucian Blaga', 'Humanitas', 15, '12/01/2000', 'E'),
(14, 22, 'Mircea Eliade', 'Humanitas', 5, '3/5/2002', 'B'),
(14, 3, 'Dan Lungu', 'Polirom', 10, '4/6/1890', 'S'),
(14, 44, 'Dan Lungu', 'Polirom', 2, '01/01/1990', 'E')

SELECT * FROM M

SELECT M2, SUM(M3) TotalM3, COUNT(M3)
FROM M
WHERE YEAR(M4)>=2000 OR M1 LIKE '%escu%'
GROUP BY M2
HAVING SUM(M3)>10

CREATE OR ALTER TRIGGER TrOnUpdate
ON M
FOR UPDATE AS
DECLARE @no INT = 0
SELECT @no = AVG(d.M3 - i.M3)
FROM deleted d INNER JOIN inserted i ON d.PK1 = i.PK1 AND d.PK2 = i.PK2 WHERE d.M3 > i.M3
PRINT @no

UPDATE M
SET M3 = 3
WHERE PK1 > PK2

DROP TRIGGER TrOnUpdate



----------------------------------------------------------------------------------------------------------------------------------
--PROCEDURES
CREATE PROCEDURE sp_Products
AS
BEGIN
	SELECT P.NameP, P.Price
	FROM Prices P
	WHERE P.Price=500
END
GO

EXEC sp_Products

CREATE PROCEDURE sp_Products_add_column
AS
BEGIN
	ALTER TABLE Prices
	ADD Description VARCHAR(50) 
END
GO

DROP PROCEDURE sp_Products_add_column

EXEC sp_Products_add_column

CREATE OR ALTER PROCEDURE sp_Products_insertIntoDescription(@price INT)
AS
BEGIN
	UPDATE Prices
	SET Description = 'Verry good, I recommand'
	WHERE Price =@price
END
GO

EXEC sp_Products_insertIntoDescription 200

CREATE OR ALTER PROCEDURE sp_Products_getProductNameByPrice(@price INT, @product_name VARCHAR(50) OUTPUT)
AS
BEGIN
	SELECT @product_name= P.NameP 
	FROM Prices P
	WHERE P.Price = @price

	IF @price < 0
		RAISERROR('The price cannot be negative',10,1)
END
GO

DROP PROCEDURE sp_Products_getProductNameByPrice

DECLARE @product_name VARCHAR(50)
EXEC sp_Products_getProductNameByPrice 500, @product_name OUTPUT
PRINT @product_name

SET NOCOUNT OFF

SELECT P.NameP, P.Description
INTO dbo.IntermediateTable
FROM Prices P

SELECT * FROM IntermediateTable

--STORED PROCEDURE TO RETURN STATUS CODE
CREATE OR ALTER PROCEDURE usp_check_status(@PriceId INT)
AS
BEGIN
	IF(SELECT TOP 1 NameP FROM Prices WHERE PId= @PriceId) = 'Canon d5K'
		RETURN 1
	ELSE
		RETURN 2
END
GO

--RETURN 1
DECLARE @status INT
EXEC @status=usp_check_status 1
SELECT 'Status' = @status

--RETURN 2
DECLARE @status2 INT
EXEC @status2 = usp_check_status 2
SELECT 'Status' = @status2


EXEC usp_check_status 1

--WHILE LOOP-- with BREAK
DECLARE @i INT 
SET @i = 0
WHILE @i<=5
BEGIN
	SET @i = @i +1;
	IF @i = 4
		BREAK;
	PRINT @i;
END
GO

--with BREAK and CONTINUE
DECLARE @i INT = 0;
WHILE @i<=6
BEGIN
	SET @i = @i+1;
	IF @i=4
		CONTINUE; --it goes back to the begining of the code without executing what comes next after CONTINUE
	IF @i=7
		BREAK;
	PRINT @i;
END

--TRY CATCH
BEGIN TRY
	SELECT * FROM Prices
	WHERE NameP = 'Canon d5K'
END TRY
BEGIN CATCH
	SELECT 
		ERROR_MESSAGE() AS ErrorMessage;
END CATCH

--FUNCTIONS
CREATE FUNCTION noOfStudentsPerGroup(@GroupNo INT)
RETURNS INT AS
BEGIN
	DECLARE @no INT
	SET @no = 0
	SELECT @no = COUNT(*)
	FROM Group822
	WHERE GroupNo = @GroupNo
	RETURN @no
END
GO

DROP FUNCTION noOfStudentsPerGroup

PRINT dbo.noOfStudentsPerGroup(822)

--FUNCTIONS THAT RETURN A TABLE
CREATE FUNCTION studentsNames821(@GroupNo INT)
RETURNS TABLE AS
	RETURN 
		SELECT GName, GroupNo
		FROM Group821
		WHERE GroupNo= @GroupNo
GO

SELECT * FROM studentsNames821(821)

--MULTI-STATEMENT FUNCTIONS THAT RETURN A TABLE

CREATE FUNCTION multiStatementStudentsNames821(@GroupNo INT)
RETURNS @myTable TABLE(GName VARCHAR(50), GroupNo INT)
AS
BEGIN
	INSERT INTO @myTable
		SELECT GName, GroupNo
		FROM Group821
		WHERE GroupNo = @GroupNo
RETURN  
END
GO

SELECT * FROM multiStatementStudentsNames821(821)

--VIEWS
CREATE OR ALTER VIEW vSEC
AS
	SELECT C.CId, P.NameP, P.Price
	FROM Category1 C INNER JOIN Prices P ON C.CId = P.CId
GO	

SELECT * FROM vSEC


SELECT NameP, Price FROM vSEC

--SYSTEM CATALOG
SELECT * FROM sys.tables --all the tables from the db
SELECT * FROM sys.objects

--TRIGGERS
create table Products
(Pid int primary key identity,
Name varchar(50),
OperationDate varchar(50),
Quantity int)

DROP TABLE Products

insert into Products Values ('cherries', '2018/11/11', 5), ('oranges', '2018/12/11', 6)

create table BuyLog(
Bid int primary key identity,
Name varchar(50),
OperationDate varchar(50),
Quantity int)

DROP TABLE BuyLog

create table SellLog(
Sid int primary key identity, 
Name varchar(50), 
OperationDate datetime,
Quantity int)

--TRIGGER ON ADD
CREATE TRIGGER Add_product
ON Products
FOR INSERT
AS
BEGIN
	INSERT INTO BuyLog(Name, OperationDate, Quantity)
	SELECT Name, OperationDate, Quantity
	FROM inserted
END
GO

SELECT * FROM Products
SELECT * FROM BuyLog

insert into Products Values ('tomatoes', '2018-11-11', 5), ('potatoes', '2018-12-11', 6)

--TRIGGER ON DELETE
CREATE TRIGGER Delete_Products
ON Products
FOR DELETE
AS
BEGIN
	INSERT INTO BuyLog(Name, OperationDate, Quantity)
	SELECT Name, GETDATE(), Quantity
	FROM deleted
END
GO

DROP TRIGGER Delete_Products

SELECT * FROM Products
SELECT * FROM BuyLog
delete from Products where Quantity=6 and Name LIKE 'o%'

UPDATE Products
SET Quantity=8
WHERE Quantity>5

CREATE TRIGGER Update_Products
ON Products
FOR UPDATE
AS
BEGIN
	SET NOCOUNT ON;
INSERT INTO SellLog(Name, OperationDate, Quantity)
SELECT d.Name, GETDATE(), d.Quantity - i.Quantity
FROM deleted d INNER JOIN inserted i ON d.Pid = i.Pid
WHERE i.Quantity < d.Quantity
INSERT INTO BuyLog(Name, OperationDate, Quantity)
SELECT i.Name, GETDATE(), i.Quantity - d.Quantity
FROM deleted d INNER JOIN inserted i ON d.Pid = i.Pid
WHERE i.Quantity > d.Quantity
END
GO

DROP TRIGGER Update_Products

--LOG ANY OPERATION PERFORMED ON A TABLE

CREATE TABLE Log(
Lid INT PRIMARY KEY IDENTITY, 
TableName VARCHAR(30), 
OperationType CHAR(1),
NoAffectedRows INT, 
ExecuteDate DATETIME)

CREATE TRIGGER ChangeProducts
ON Products
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	DECLARE @operation CHAR(1)
	SET @operation = CASE
	WHEN EXISTS(SELECT * FROM inserted) AND EXISTS(SELECT * FROM deleted)
		THEN 'U'
	WHEN EXISTS(SELECT * FROM inserted)
		THEN 'I'
	WHEN EXISTS(SELECT * FROM deleted)
		THEN 'D'
END
IF @operation IS NULL
	RETURN
INSERT INTO Log(TableName,OperationType, NoAffectedRows, ExecuteDate)
SELECT 'Products', @operation,@@ROWCOUNT,GETDATE()
END
GO

DROP TRIGGER ChangeProducts

SELECT * FROM Log
INSERT INTO Products VALUES ('chocolate', '2500-11-11', 9)
DELETE FROM Products WHERE Name='chocolate'
UPDATE Products
SET Quantity=8
WHERE Quantity>5

--CURSORS
DECLARE @NameP varchar(50), @Price int, @PId int, @CId int, @Description varchar(50)
DECLARE cursor_name CURSOR
FOR
SELECT NameP, Price, PId, CId, Description
FROM Prices
OPEN cursor_name
FETCH cursor_name
INTO @NameP, @Price, @PId , @CId, @Description
WHILE @@FETCH_STATUS = 0
BEGIN
	print 'The record with the Sid=' + CAST(@PId AS varchar(50)) +
  ' has the name ' + CONVERT(varchar(50), @NameP) +
   ' and the price ' + CAST(@Price AS varchar(50))
    FETCH cursor_name
    INTO @NameP, @Price, @PId , @CId, @Description

END
CLOSE cursor_name
DEALLOCATE cursor_name

--INDEXES
CREATE INDEX IX_PricesNew
ON Prices (Price ASC)

DROP INDEX IX_PricesNew
ON Prices

sp_Helpindex Prices

CREATE TABLE Employee
(
	ID INT PRIMARY KEY,
	Name VARCHAR(50),
	Salary INT
)

INSERT INTO Employee VALUES(3, 'Bibi', 7500)
INSERT INTO Employee VALUES(1, 'Ana', 10000)
INSERT INTO Employee VALUES(4, 'Miha', 4500)
INSERT INTO Employee VALUES(5, 'Dia', 9000)
INSERT INTO Employee VALUES(2, 'Ina', 9000)
INSERT INTO Employee VALUES(6, 'Ionut', 8000, 'Male')

CREATE CLUSTERED INDEX IX_Employee 
ON Employee (Gender DESC, Salary ASC)

select * from Employee


ALTER TABLE Employee
ADD Gender VARCHAR(20)

UPDATE Employee
SET Gender = 'Female'
WHERE Salary = 4500

CREATE NONCLUSTERED INDEX IX_Employee_nonClustered
ON Employee(Name)

CREATE NONCLUSTERED INDEX IX_PricesNonClustered
ON Prices(CId)

select * from Prices

ALTER TABLE Employee
ADD CONSTRAINT UQ_Employee_Constraint
UNIQUE CLUSTERED (Name)

