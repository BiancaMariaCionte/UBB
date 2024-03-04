create database RestaurantDataBase
go

use RestaurantDataBase
go 

CREATE TABLE Restaurants
(
RId INT PRIMARY KEY IDENTITY(1,1),
Name VARCHAR(50) not null unique
)

INSERT INTO Restaurants VALUES('Restaurant1')
INSERT INTO Restaurants VALUES('Restaurant2')


CREATE TABLE Products
(
PId INT PRIMARY KEY IDENTITY(1,1),
Name VARCHAR(50) not null,
Description VARCHAR(50) not null

)

INSERT INTO Products VALUES('Product1','Description1')
INSERT INTO Products VALUES('Product2','Description2')


CREATE TABLE MenuTypes
(
MTId INT PRIMARY KEY,
Name VARCHAR(50) not null,
Description VARCHAR(50) not null
)

INSERT INTO MenuTypes VALUES(1,'MenuTypes1','Des1')
INSERT INTO MenuTypes VALUES(2,'MenuTypes2','Des2')


CREATE TABLE Menus
(
MId int PRIMARY KEY IDENTITY(1,1),
MTId INT FOREIGN KEY REFERENCES MenuTypes(MTId),
RId INT FOREIGN KEY REFERENCES Restaurants(RId),

Name VARCHAR(50) not null unique,

--CONSTRAINT pk_Menus PRIMARY KEY (MTId,RId)
)



INSERT INTO Menus VALUES(1,1,'N1')
INSERT INTO Menus VALUES(2,2,'N2')

drop table Menus


CREATE TABLE MenuProducts
(
MId INT FOREIGN KEY REFERENCES Menus(MId),
PId INT FOREIGN KEY REFERENCES Products(PId),
Weight int not null,
Price float not null,
CONSTRAINT pk_MenuProducts PRIMARY KEY(MId,PId)
)

drop table MenuProducts

INSERT INTO MenuProducts VALUES(1,2,3,5.5)
INSERT INTO MenuProducts VALUES(1,1,2,2.2)


--PROCEDURE
CREATE OR ALTER PROCEDURE sp_sp1 @MId int, @PId int, @Weight int, @Price float
AS
DECLARE @nr int;
SET @nr = 0;
SELECT @nr = COUNT(*) FROM MenuProducts WHERE MId=@MId and PId=@PId
IF(@nr<>0) BEGIN
UPDATE MenuProducts
SET Weight=@Weight, Price=@Price
WHERE MId=@MId and PId=@PId
END
ELSE BEGIN
INSERT INTO MenuProducts VALUES (@MId, @PId, @Weight,@Price)
END

GO
Select * from MenuProducts
EXEC sp_sp1 2,2,3,20


--VIEW
CREATE OR ALTER VIEW
AS
SELECT R.Name, 
FROM Restaurants R
INNER JOIN MenuProducts MP
ON MP.MId = R.RId






--FUNCTION
CREATE OR ALTER FUNCTION functionn(@R int)
RETURNS TABLE
AS
RETURN
SELECT DISTINCT P.Name, AVG(MP.Price) as avgPrice
FROM Products P
INNER JOIN MenuProducts MP
ON P.PId=MP.PId
GROUP BY P.Name
HAVING AVG(MP.Price)< @R
GO

SELECT * FROM functionn(10)












--create or alter procedure Add_Exams @Sid int, @Cid int, @d datetime, @m float
--AS
--DECLARE @nr int;
--SET @nr = 0;
--SELECT @nr = COUNT(*) FROM Exams WHERE Sid=@Sid and Cid=@Cid
--IF(@nr<>0) BEGIN
--UPDATE Exams
--SET EDate=@d, Mark=@m
--WHERE Sid=@Sid and Cid=@Cid
--END
--ELSE BEGIN
--INSERT INTO Exams VALUES (@Sid, @Cid, @d, @m)
--END
--Go


--CREATE VIEW vGroupsMax
--AS
--SELECT Gid, MAX(Mark) as maximumGrade
--FROM Students s INNER JOIN Exams e ON s.Sid = e.Sid
--GROUP BY Gid
--HAVING MAX(Mark) = (SELECT MAX(Mark) FROM Exams)

--SELECT * FROM vGroupsMax

--CREATE FUNCTION uf_ProfessorsName(@m int)
--RETURNS TABLE
--AS
--RETURN
--SELECT DISTINCT p.Pid, NameP, count(NameP) as ProfessorName
--FROM Professors p INNER JOIN CP c ON p.Pid=c.Pid
--group by p.Pid, NameP
--having count(NameP)>=@m
--go

--SELECT * FROM uf_ProfessorsName(1)