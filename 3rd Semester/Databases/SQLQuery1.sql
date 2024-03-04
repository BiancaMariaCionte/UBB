Create database Bakery1
go
use Bakery1
go

--create table CUSTOMERS
CREATE TABLE Customers
(CustomerID INT PRIMARY KEY IDENTITY,
Name varchar(50) NOT NULL,
Email varchar(50) NOT NULL,
PhoneNo bigint NOT NULL,
Address varchar(70) NOT NULL
)

DROP TABLE Customers

--create table EMPLOYEES
CREATE TABLE Employees
(EmployeeID INT PRIMARY KEY IDENTITY,
Name varchar(50) NOT NULL,
Position varchar(50) NOT NULL,
HireDate date NOT NULL,
Salary money NOT NULL
)

DROP TABLE Employees

--create table INGREDIENTS
CREATE TABLE Ingredients
(IngredientId INT PRIMARY KEY IDENTITY,
Name varchar(50) NOT NULL,
QuantityInStock int NOT NULL,
UnitOfMeasure char(10) NOT NULL,
PricePerUnit money NOT NULL
)

--create table DELIVERIES
CREATE TABLE Deliveries
(DeliveryID INT PRIMARY KEY IDENTITY,
CustomerID int FOREIGN KEY REFERENCES Customers(CustomerID),
DeliveryDate date NOT NULL,
DescriptionDelivery varchar(50) NOT NULL
)
DROP TABLE Deliveries

--create table PRODUCTS
CREATE TABLE Products
(ProductID INT PRIMARY KEY IDENTITY,
Name varchar(50) NOT NULL,
Price money NOT NULL,
NutritionalInfo varchar(50) NOT NULL,
QuantityInStock int NOT NULL
)

--DELETE -- IN -- BETWEEN -- ??
DELETE FROM Products
WHERE Name IN ('chocolate croissant', 'pistachio croissant')
  AND Price BETWEEN 1 AND 10;

DROP TABLE Products

--create INTERMEDIATE table PRODUCTiNGREDIENTS
CREATE TABLE ProductIngredients
(ProductID INT FOREIGN KEY REFERENCES Products(ProductID),
IngredientID INT FOREIGN KEY REFERENCES Ingredients(IngredientID)
)

DROP TABLE ProductIngredients

--create table CustomerProducts
CREATE TABLE CustomerProducts
(CustomerID INT FOREIGN KEY REFERENCES  Customers(CustomerID),
ProductID INT FOREIGN KEY REFERENCES Products(ProductID)
)

--create table Bakeries
CREATE TABLE Bakeries
(
BakeryID INT PRIMARY KEY IDENTITY,
Address VARCHAR(30),
City VARCHAR(30),
NoOfEmployees INT
)

ALTER TABLE Bakeries
ALTER COLUMN Address VARCHAR(30) NOT NULL;

ALTER TABLE Bakeries
ALTER COLUMN City VARCHAR(30) NOT NULL;

ALTER TABLE Bakeries
ALTER COLUMN NoOfEmployees INT NOT NULL;


DROP TABLE Bakeries

--create table Managers
CREATE TABLE Managers
(
ManagerID INT FOREIGN KEY REFERENCES Bakeries(BakeryID),
Name VARCHAR(30) NOT NULL,
Surname VARCHAR(30) NOT NULL,
Experience INT NOT NULL,
CONSTRAINT pk_BakeriesManagers PRIMARY KEY(ManagerID)
)

DROP TABLE Managers

--create INTERMEDIATE table BakeriesProducts
CREATE TABLE BakeriesProducts
(
BakeryID INT FOREIGN KEY REFERENCES Bakeries(BakeryID),
ProductID INT FOREIGN KEY REFERENCES Products(ProductID)
)

ALTER TABLE BakeriesProducts
ADD Products VARCHAR(50);


--inserting information
INSERT INTO Customers(Name, Email, PhoneNo, Address)
VALUES('Cionte Bianca', 'cionte.bianca@gmail.com', 0756783447, 'Dorobantilor Street no.74');

INSERT INTO Customers(Name, Email, PhoneNo, Address)
VALUES('Buruian Amalia', 'buruian.amalia@gmail.com', 0743789226, 'Dorobantilor Street no.74');

INSERT INTO Customers(Name, Email, PhoneNo, Address)
VALUES('Buda Darius', 'buda.darius@gmail.com', 0723668903, 'M. Kogalniceanu Street no.15');

INSERT INTO Customers(Name, Email, PhoneNo, Address)
VALUES('Almasan Mara', 'almasan.mara@gmail.com', 0756442167, 'Paris Street no.20');

INSERT INTO Customers(Name, Email, PhoneNo, Address)
VALUES('Costea Madalina', 'costea.madalina@gmail.com', 0757845167, 'Dezmir Street no.18');

INSERT INTO Customers(Name, Email, PhoneNo, Address)
VALUES('Oros Mihaela', 'oros.mihaela@gmail.com', 0776546781, 'Matei Basarab Street no.100');

INSERT INTO Customers(Name, Email, PhoneNo, Address)
VALUES('Pop Cristian', 'pop.cristian@gmail.com', 0732457197, 'Bucovinei Street no.10');

INSERT INTO Deliveries(CustomerID, DeliveryDate, DescriptionDelivery)
VALUES (2,'2023-12-10', 'Delivery of 1 chocolate croissont');

INSERT INTO Deliveries(CustomerID, DeliveryDate, DescriptionDelivery)
VALUES (3,'2023-01-03', 'Delivery of 1 pistachos croissont');

INSERT INTO Deliveries(CustomerID, DeliveryDate, DescriptionDelivery)
VALUES (4,'2023-10-08', 'Delivery of 2 cheese croissonts');

INSERT INTO Deliveries(CustomerID, DeliveryDate, DescriptionDelivery)
VALUES (5,'2022-11-01', 'Delivery of 3 loafs of bread');

INSERT INTO Employees(Name,Position, HireDate,Salary)
VALUES ('Pop Simona', 'manager', '2010-11-09', 6000);

INSERT INTO Employees(Name,Position, HireDate,Salary)
VALUES ('Campeanu Mircea', 'full time employee', '2018-03-02', 4500);

INSERT INTO Employees(Name,Position, HireDate,Salary)
VALUES ('Pascu Romana', 'part-time employee', '2021-04-12', 3000);

INSERT INTO Employees(Name,Position, HireDate,Salary)
VALUES ('Chiorean Diana', 'full-time employee', '2017-02-18', 4600);

INSERT INTO Ingredients(Name,QuantityInStock, UnitOfMeasure,PricePerUnit)
VALUES ('flour', '100', 'kg', 1);

INSERT INTO Ingredients(Name,QuantityInStock, UnitOfMeasure,PricePerUnit)
VALUES ('eggs', '200', 'kg', 2);

INSERT INTO Ingredients(Name,QuantityInStock, UnitOfMeasure,PricePerUnit)
VALUES ('milk', '250', 'liter',2.5);

INSERT INTO Ingredients(Name,QuantityInStock, UnitOfMeasure,PricePerUnit)
VALUES ('chocolate', '150', 'kg', 4);

INSERT INTO Ingredients(Name,QuantityInStock, UnitOfMeasure,PricePerUnit)
VALUES ('pistachios', '50', 'kg', 10);

INSERT INTO Products(Name, Price, NutritionalInfo, QuantityInStock)
VALUES ('pistachio croissant', 5, '350 kal', 30);

INSERT INTO Products(Name, Price, NutritionalInfo, QuantityInStock)
VALUES ('chocolate croissant', 4, '400 kal', 50);

INSERT INTO Products(Name, Price, NutritionalInfo, QuantityInStock)
VALUES ('cheese croissant', 3, '300 kal', 60);

INSERT INTO Products(Name, Price, NutritionalInfo, QuantityInStock)
VALUES ('integral bread', 2, '350 kal', 70);

INSERT INTO Products(Name, Price, NutritionalInfo, QuantityInStock)
VALUES ('white bread', 1.5, '400 kal', 100);

INSERT INTO ProductIngredients(ProductID, IngredientID)
VALUES (1,2);

INSERT INTO ProductIngredients(ProductID, IngredientID)
VALUES (2,2);

INSERT INTO ProductIngredients(ProductID, IngredientID)
VALUES (3,3);

INSERT INTO CustomerProducts(CustomerID, ProductID)
VALUES (1,3);

INSERT INTO CustomerProducts(CustomerID, ProductID)
VALUES (2,3);

INSERT INTO CustomerProducts(CustomerID, ProductID)
VALUES (4,3);

INSERT INTO CustomerProducts(CustomerID, ProductID)
VALUES (4,4);

INSERT INTO Bakeries (Address, City, NoOfEmployees)
VALUES ('123 Main Street', 'City1', 10);

INSERT INTO Bakeries (Address, City, NoOfEmployees)
VALUES ('456 Elm Street', 'Cluj-Napoca', 15);

INSERT INTO Bakeries (Address, City, NoOfEmployees)
VALUES ('789 Oak Street', 'Baia Mare', 20);

INSERT INTO Bakeries (Address, City, NoOfEmployees)
VALUES ('86 M. Sadoveanu Street', 'Oradea', 25);

INSERT INTO Managers (ManagerID, Name, Surname, Experience)
VALUES (1, 'Mihaela', 'Popescu', 6);

INSERT INTO Managers (ManagerID, Name, Surname, Experience)
VALUES (2, 'Cristina', 'Albu', 2);

INSERT INTO Managers (ManagerID, Name, Surname, Experience)
VALUES (3, 'Paul', 'Neagu', 1);

INSERT INTO Managers (ManagerID, Name, Surname, Experience)
VALUES (4, 'Marian', 'Teodorescu', 3);

INSERT INTO BakeriesProducts(BakeryID, ProductID)
VALUES (1,2);

INSERT INTO BakeriesProducts(BakeryID, ProductID)
VALUES (2,2);

INSERT INTO BakeriesProducts(BakeryID, ProductID)
VALUES (3,2);

INSERT INTO BakeriesProducts(BakeryID, ProductID)
VALUES (3,4);

ALTER TABLE Products
DROP COLUMN QuantityInStock;

ALTER TABLE BakeriesProducts
ADD QuantityInStock INT;

EXEC sp_rename 'Deliveries', 'CustomersType';

EXEC sp_rename 'CustomersType.DeliveryID', 'CustomerTypeID', 'COLUMN';

EXEC sp_rename 'CustomersType.DeliveryDate', 'Benefits', 'COLUMN';

ALTER TABLE CustomersType
DROP COLUMN DescriptionDelivery;

ALTER TABLE CustomersType
ALTER COLUMN Benefits VARCHAR(100);

UPDATE CustomersType
SET Benefits = '20% discount at the next purchase of a Chocolate croissant.'
WHERE CustomerTypeID = 1;

UPDATE CustomersType
SET Benefits = 'Buy 1 and get 1 for free for any product.'
WHERE CustomerTypeID = 2;

UPDATE CustomersType
SET Benefits = '50% discount at the next purchase of a Integral Bread.'
WHERE CustomerTypeID = 3;

UPDATE CustomersType
SET Benefits = 'Buy 2 Pistachio croissants and get 1 for free.'
WHERE CustomerTypeID = 4;

--LAB 2

-- DELETE -- AND -- = --
DELETE FROM CustomerProducts
WHERE CustomerID = 1 AND ProductID = 3; 

--DELETE -- IN -- BETWEEN -- 
DELETE FROM Managers
WHERE Name IN ('Mihaela', 'Bianca')
  AND Experience BETWEEN 1 AND 10;

-- UPDATE -- IS NOT NULL -- AND --
UPDATE Bakeries
SET 
	City = 'Brasov'
WHERE 
	City IS NOT NULL AND City = 'City1';  

-- UPDATE -- LIKE --
UPDATE Ingredients
SET 
	Name = 'Vanilla milk'
WHERE
	Name LIKE 'm%';

-- QUERIES
-- a)
-- UNION
SELECT Name FROM Ingredients
UNION  
SELECT Name FROM Products;

-- INTERSECT
SELECT CustomerID FROM Customers
INTERSECT 
SELECT CustomerId FROM CustomersType;

-- EXCEPT
SELECT Name FROM Products
EXCEPT 
SELECT Products FROM BakeriesProducts;

-- b)
UPDATE BP
SET BP.Products = P.Name
FROM BakeriesProducts AS BP
INNER JOIN Products AS P ON BP.ProductID = P.ProductID;


ALTER TABLE Customers
ADD Products VARCHAR(255);

UPDATE Customers
SET Products = 'chocolate croissant'
WHERE CustomerID = 1;

UPDATE Customers
SET Products = 'pistachio croissant'
WHERE CustomerID = 2;

UPDATE Customers
SET Products = 'cheese croissant'
WHERE CustomerID = 3;

UPDATE Customers
SET Products = 'chocolate croissant'
WHERE CustomerID = 4;

-- INNER JOIN
SELECT P.Name AS ProductsName, C.Name AS Customer
FROM Products P
INNER JOIN CustomerProducts CP ON P.ProductID = CP.ProductID
INNER JOIN Customers C ON CP.CustomerID = C.CustomerID;

--LEFT JOIN
SELECT P.Name AS ProductsName, C.Name AS Customer
FROM Products P
LEFT JOIN CustomerProducts CP ON P.ProductID = CP.ProductID
LEFT JOIN Customers C ON CP.CustomerID = C.CustomerID;

--RIGHT JOIN
SELECT P.Name AS ProductsName, C.Name AS Customer
FROM Products P
RIGHT JOIN CustomerProducts CP ON P.ProductID = CP.ProductID
RIGHT JOIN Customers C ON CP.CustomerID = C.CustomerID;

-- FULL JOIN: Get all products and customers, including those without matches in either table
SELECT P.Name AS ProductsName, C.Name AS Customer
FROM Products P
FULL JOIN CustomerProducts CP ON P.ProductID = CP.ProductID
FULL JOIN Customers C ON CP.CustomerID = C.CustomerID;

--c)

-- IN
-- Find customers who have purchased "pistachio croissant" or "chocolate croissant"
SELECT Name
FROM Customers
WHERE Products IN (SELECT Products FROM Customers WHERE Products IN ('pistachio croissant', 'chocolate croissant'));

--EXISTS
SELECT Name 
FROM Products P
WHERE EXISTS(SELECT 1 FROM ProductIngredients PI WHERE PI.ProductID = P.ProductID);


--d)-- Subquery in the FROM clause
SELECT C.Name
FROM (
    SELECT DISTINCT D.CustomerID
    FROM CustomersType D
	WHERE Benefits LIKE('B%')
) AS Subquery
INNER JOIN Customers C ON Subquery.CustomerID = C.CustomerID;

--e)
-- Query with GROUP BY and SUM
SELECT BakeryID, COUNT(ProductID) AS TotalProducts
FROM BakeriesProducts
GROUP BY BakeryID;

--Query with GROUP BY, HAVING, and AVG
SELECT BakeryID, AVG(QuantityInStock * 1) AS AverageQuantity
FROM BakeriesProducts
JOIN Products ON BakeriesProducts.ProductID = Products.ProductID
GROUP BY BakeryID
HAVING AVG(QuantityInStock) > 50;


--Query with GROUP BY, HAVING, and Subquery in HAVING clause
SELECT CustomerID, COUNT(ProductID) AS TotalPurchases
FROM CustomerProducts
GROUP BY CustomerID
HAVING COUNT(ProductID) > (
    SELECT AVG(TotalPurchases)
    FROM (
        SELECT CustomerID, COUNT(ProductID) AS TotalPurchases
        FROM CustomerProducts
        GROUP BY CustomerID
    ) AS Subquery
);


UPDATE BakeriesProducts
SET 
	QuantityInStock = 300
WHERE BakeryID = 3;

--TOP
SELECT TOP 3 Name, PricePerUnit
FROM Ingredients
ORDER BY PricePerUnit DESC;

--AND -- IS NULL
UPDATE Customers
SET Products = 'homemade chocolate'
WHERE CustomerID = 5 AND Products IS NULL

-- Query with ORDER BY
SELECT CustomerID, Name, Email, PhoneNo, Address
FROM Customers
ORDER BY Name ASC;

--TOP--ORDER BY
SELECT DISTINCT TOP 4 Name, NutritionalInfo
FROM Products
ORDER BY NutritionalInfo ASC;
