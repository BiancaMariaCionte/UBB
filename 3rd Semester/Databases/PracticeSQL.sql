create database Practice
go

use Practice
go

CREATE TABLE A
(
Aid INT PRIMARY KEY IDENTITY,
B INT,
C INT,
D INT,
E INT,
F INT,
G INT
)

INSERT INTO A VALUES(10,20,5,20,2,1)
INSERT INTO A VALUES(11,5,1,20,5,2)
INSERT INTO A VALUES(10,10,7,10,5,3)
INSERT INTO A VALUES(20,20,6,20,2,4)
INSERT INTO A VALUES(20,10,2,20,5,9)
INSERT INTO A VALUES(30,5,1,20,5,1)

SELECT a1.Aid, a1.B+a2.B adding, a1.B*a2.D multiplication
FROM A a1 RIGHT JOIN A a2 ON a1.Aid=a2.Aid
WHERE a1.B>ANY(SELECT C FROM A WHERE C<6)

SELECT a1.Aid, a2.E FROM
(SELECT * FROM A WHERE F<5) a1 INNER JOIN A a2 ON a1.G=a2.G

CREATE TABLE Groups (
    GroupId INT PRIMARY KEY,
    Year INT,
    Specialization VARCHAR(255)
);

CREATE TABLE Students (
    StudentId INT PRIMARY KEY,
    StudentName VARCHAR(255),
    Age INT,
    SGroup INT,
    FOREIGN KEY (SGroup) REFERENCES Groups(GroupId)
);

CREATE TABLE Professor (
    ProfessorId INT PRIMARY KEY,
    ProfessorName VARCHAR(255),
    Dob DATE
);

SELECT SGroup, COUNT(*) numberOfSTudents FROM
(SELECT * FROM Students WHERE SGroup IN
(SELECT GroupId FROM Groups
WHERE Specialization = 'Computer Science'))B
GROUP BY SGroup

SELECT SGroup, COUNT(*) FROM
 Students WHERE SGroup IN
(SELECT GroupId FROM Groups
WHERE Specialization = 'Computer Science')
GROUP BY SGroup

INSERT INTO Groups (GroupId, Year, Specialization) VALUES
(1, 2023, 'Computer Science'),
(2, 2023, 'Electrical Engineering'),
(3, 2023, 'Mechanical Engineering');

-- Insert values into the Student table
INSERT INTO Students (StudentId, StudentName, Age, SGroup) VALUES
(101, 'John Doe', 21, 1),
(102, 'Jane Smith', 22, 1),
(103, 'Bob Johnson', 20, 2),
(104, 'Alice Williams', 23, 3);

CREATE TABLE R
(
    A1 INT,
    A2 INT,
    X VARCHAR(50),
    Y VARCHAR(50),
    Z VARCHAR(50),
    V INT,
    W INT,
    PRIMARY KEY (A1, A2)
);

INSERT INTO R VALUES(11,11,'x1','y1','z1',10,4)
INSERT INTO R VALUES(11,12,'x','y1','z2',8,4)
INSERT INTO R VALUES(11,13,'x2','y','z1', null,4)
INSERT INTO R VALUES(12,11,'x3','y3','x', null,20)
INSERT INTO R VALUES(12,13,'x2','y','z3',null ,20)

SELECT * FROM R r1 RIGHT OUTER JOIN R r2 ON r1.A1=r2.A1
WHERE r1.Z LIKE '%1'

SELECT DISTINCT * FROM R r1 RIGHT OUTER JOIN R r2 ON r1.A2=r2.A2
WHERE r1.Y LIKE '%1'

 SELECT * FROM R r1 RIGHT OUTER JOIN R r2 ON r1.A1=r2.A2
WHERE r1.X LIKE '%1'

drop table R1

SELECT * FROM R r1 LEFT JOIN R r2 ON r1.A1=r2.A2

SELECT DISTINCT * FROM R2 r1 INNER JOIN R2 r2 ON r1.A1=r2.A2

SELECT A1, X, COUNT(DISTINCT Y)
FROM R
GROUP BY A1, X
HAVING MIN(V)>5

SELECT A1, X, COUNT(Y)
FROM R
GROUP BY A1, X
HAVING MAX(W)>0

CREATE TABLE R2
(
    A1 INT,
    A2 INT,
    X VARCHAR(50),
    Y VARCHAR(50),
    Z VARCHAR(50),
    V INT,
    W INT,
  
);

DROP TABLE R1

INSERT INTO R2 VALUES(11,11,'x1','y1','z1',10,4)
INSERT INTO R2 VALUES(11,12,'x','y1','z2',8,4)
INSERT INTO R2 VALUES(11,13,'x2','y','z1', null,4)
INSERT INTO R2 VALUES(12,11,'x3','y3','x', null,20)
INSERT INTO R2 VALUES(12,13,'x2','y','z3',null ,20)

SELECT * FROM R r1 RIGHT JOIN R r2 ON r1.A1=r2.A1
WHERE r1.Z LIKE '%1*'


SELECT DISTINCT * FROM R r1 RIGHT JOIN R r2 ON r1.A2=r2.A2

SELECT * FROM R r1 RIGHT JOIN R r2 ON r1.A1=r2.A2

SELECT * FROM R r1 WHERE r1.Z LIKE '%1*'

CREATE TABLE Traveller 
(
    Tid INT PRIMARY KEY,
    Name VARCHAR(50),
    Dob DATE
);

drop table Traveller

CREATE TABLE Place (
    Pid INT PRIMARY KEY,
    PName VARCHAR(255),
    Country VARCHAR(255),
    Area DECIMAL(10, 2)
);

CREATE TABLE Trip (
    
    Budget DECIMAL(10, 2),
    TripDate DATE,
    TravellerId INT FOREIGN KEY REFERENCES Traveller(Tid),
    PlaceId INT FOREIGN KEY (PlaceId) REFERENCES Place(Pid)
	CONSTRAINT pk_Trip PRIMARY KEY(TravellerId,PlaceId)
);

drop table Trip

INSERT INTO Traveller (Tid, Name, Dob) VALUES
(1, 'John Doe', '1990-05-15'),
(2, 'Jane Smith', '1985-11-28'),
(3, 'Alice Johnson', '1998-03-20');

-- Inserting values into the Place table
INSERT INTO Place (Pid, PName, Country, Area) VALUES
(101, 'Paris', 'France', 105.4),
(102, 'Tokyo', 'Japan', 2187.6),
(103, 'New York City', 'USA', 789.5);

-- Inserting values into the Trip table
INSERT INTO Trip (TravellerId, PlaceId, Budget, TripDate) VALUES
(1, 101, 2500.00, '2023-07-10'),
(1, 102, 4000.00, '2023-08-20'),
(2, 101, 3000.00, '2023-06-05'),
(3, 103, 3500.00, '2023-09-15'),
(3, 101, 2800.00, '2023-10-25');


SELECT T.Name, P.Country
FROM Traveller T
INNER JOIN Trip Tr ON Tr.TravellerId= T.Tid
INNER JOIN Place P ON P.Pid= Tr.PlaceId
WHERE P.PName LIKE 'D%' AND Tr.Budget>1000.00

--Display the name of the travellers that had a trip in the same place and date.
SELECT DISTINCT T.Name
FROM Traveller T
INNER JOIN Trip Tr1 ON Tr1.TravellerId = T.Tid
INNER JOIN Trip Tr2 ON Tr2.TravellerId = T.Tid
WHERE Tr1.PlaceId= Tr2.PlaceId AND Tr1.TripDate<> Tr2.TripDate
--the fact that we use INNER JOIN on the Trip Table two times
--and check the equality with the same thing(T.Tid)
--allows us to compare two trips for the same traveller

--Display pairs of two different travellers that had a trip 
--in the same place and in different dates.


SELECT T1.Name, T2.Name, Tr1.TripDate
FROM Traveller T1
INNER JOIN Trip Tr1 ON T1.Tid = Tr1.TravellerId
INNER JOIN Place P1 ON P1.Pid = Tr1.PlaceId
INNER JOIN Trip Tr2 ON Tr1.PlaceId = Tr2.PlaceId
INNER JOIN Traveller T2 ON Tr2.TravellerId = T2.Tid
INNER JOIN Place P2 ON P2.Pid = Tr2.PlaceId
WHERE Tr1.TripDate <> Tr2.TripDate
AND T1.Tid <> T2.Tid;


SELECT T1.Name, T2.Name, Tr1.TripDate
FROM Traveller T1
INNER JOIN Trip Tr1 ON T1.Tid = Tr1.TravellerId
INNER JOIN Trip Tr2 ON T1.Tid = Tr2.TravellerId
INNER JOIN Traveller T2 ON T2.Tid = Tr1.TravellerId AND T2.Tid = Tr2.TravellerId
WHERE Tr1.PlaceId = Tr2.PlaceId AND Tr1.TripDate <> Tr2.TripDate AND T1.Tid <> T2.Tid








SELECT T1.Name, T2.Name
FROM Traveller T1, Traveller T2
INNER JOIN Trip Tr1 ON Tr1.TravellerId=T1.Tid
INNER JOIN Trip Tr2 ON Tr2.TravellerId=T2.Tid


CREATE TABLE S2
(
B1 INT NOT NULL,
B2 INT NOT NULL,
X VARCHAR(50) NOT NULL,
Y VARCHAR(50) NOT NULL,
Z VARCHAR(50) NOT NULL,
V INT,
W VARCHAR(50),
PRIMARY KEY(B1,B2)
)
drop table S2

CREATE TABLE CofeeDistribution
(
CofeeId INT NOT NULL,
DistributionId INT NOT NULL,
DDate Date,
Description VARCHAR(50),
Quantity INT,
Quality VARCHAR(50),
Price INT,
ExtraCharge INT,
Performed VARCHAR(50),
PRIMARY KEY (CofeeId,DistributionId)
)

INSERT INTO CofeeDistribution (CofeeId,DistributionId, DDate, Description, Quantity, Quality, Price, ExtraCharge, Performed)
VALUES
(1,1, '2020-02-01', 'cofee deliver', 20, 'best', 120, 0, 'success'),
(1,2, '2020-06-08', 'brazilian coffee', 100, 'the right one', 200, 20, 'in progress'),
(1,3, '2020-12-23', 'columbian coffee', 250, 'best', 300, 10, 'not known'),
(2,2, '2020-11-18', 'arabica coffee', 50, 'medium', 80, 0, 'delivered'),
(2,3, '2020-04-19', 'coffee with sugar', 80, 'low', 80, 5, 'in progress'),
(2,5, '2020-10-25', 'coffee deliver', 120, 'medium', 160, 10, 'not known'),
(3,1, '2020-01-07', 'brazilian coffee', 467, 'best', 1000, 25, 'not delivered'),
(3,3, '2020-08-28', 'coffee with milk', 23, 'the right one', 45, 19, 'on time'),
(3,4, '2020-06-30', 'milk and sugar coffee', 300, 'low', 350, 19, 'not delivered'),
(4,1, '2020-11-04', 'columbian coffee', 480, 'the right one', 500, 20, 'delivered'),
(4,2, '2020-07-15', 'coffee with milk', 70, 'best', 400, 0, 'not known'),
(4,3, '2020-01-04', 'milk and sugar coffee', 56, 'low', 98, 25, 'on time'),
(4,4, '2020-12-17', 'arabica coffee', 68, 'high', 98, 50, 'delivered'),
(4,5, '2020-07-23', 'coffee with sugar', 347, 'the right one', 500, 0, 'on time');

SELECT Description, AVG(Quantity) AverageQuantity, AVG(Price) AveragePrice
FROM CofeeDistribution
WHERE ExtraCharge> 5 or Performed like '%delivered%'
GROUP BY Description
HAVING Count(ExtraCharge) between 1 and 3

SELECT * FROM
(SELECT CofeeId, DistributionId, DDate, Quantity FROM
CofeeDistribution
WHERE Day(DDate) between 15 and 31) p1
Right JOIN (SELECT CofeeId, DistributionId, Performed FROM CofeeDistribution
WHERE Performed like '%e%') p2
ON p1.CofeeId = p2.CofeeId and p1.DistributionId=p2.DistributionId
WHERE Quantity>30

CREATE OR ALTER TRIGGER TrOnUpdate
ON CofeeDistribution
FOR UPDATE AS
DECLARE @no INT = 0
SELECT @no = SUM(d.Price - i.Price)
FROM deleted d INNER JOIN inserted i
ON d.CofeeId = i.CofeeId and
d.Distributionid=i.Distributionid
WHERE d.Price >= i.Price
PRINT @no


UPDATE CofeeDistribution
SET Price = Price - 20
WHERE ExtraCharge>0

-- ASTRONOMICAL OBSERVATORY --

CREATE TABLE Star
(
Sid int primary key,
Name varchar(20),
Powerr varchar(20)
)

insert into Star values(1,'Star1','powerful1')
insert into Star values(2,'Star1','powerful2')
insert into Star values(3,'Star2','powerful')
insert into Star values(4,'Star3','powerful')

CREATE TABLE AstronomicalObservatory
(
Aid int primary key,
Name varchar(20)
)

insert into AstronomicalObservatory values(1,'A1')
insert into AstronomicalObservatory values(2,'A2')
insert into AstronomicalObservatory values(3,'A3')
insert into AstronomicalObservatory values(4,'A4')

CREATE TABLE Vieww
(
Sid int foreign key references Star(Sid),
Aid int foreign key references AstronomicalObservatory(Aid),
CONSTRAINT pk_Vieww PRIMARY KEY (Sid,Aid)
)

insert into Vieww values(1,1)
insert into Vieww values(2,1)
insert into Vieww values(3,1)
insert into Vieww values(1,2)
insert into Vieww values(2,2)

SELECT A.Name, count(S.Sid) as noOFSTARS
FROM AstronomicalObservatory A
INNER JOIN Vieww V on A.Aid = V.Aid
INNER JOIN Star S on S.Sid= V.Sid
group by A.Name
having count(S.Sid) = 
(select max(numStars) from (
							select count(S.Sid) as numStars 
							from AstronomicalObservatory A
inner join Vieww V on V.Aid = A.Aid
inner join Star S on S.Sid = V.Sid
group by A.Aid) as sth
)

SELECT AO.Name, COUNT(S.Sid) AS NoOfStarsObserved
FROM AstronomicalObservatory AO
INNER JOIN Vieww V ON
V.Aid = AO.Aid
INNER JOIN Star S
ON S.Sid=V.Sid
GROUP BY AO.Name
HAVING COUNT(S.Sid) = 
(SELECT MAX(NumStars)
FROM (SELECT COUNT(S.Sid AS NumStars)
FROM AstronomicalObservatory AO
INNER JOIN Vieww V ON
V.Aid = AO.Aid
INNER JOIN Star S
ON S.Sid=V.Sid
))














----- EXAMEN ANDRA ---------
CREATE TABLE S2
(
B1 int not null,
B2 int not null,
X varchar(50) not null,
Y varchar(50) not null,
Z varchar(50),
V int,
W int
CONSTRAINT pk_S2 PRIMARY KEY (B1,B2)
)

insert into S2 values(1,2,'Audi','andre','ggggggggg',9,91)
insert into S2 values(1,3,'Wols','Mercedes','good',8,34)
insert into S2 values(2,8,'Texas','md',null,null,91)
insert into S2 values(2,9,'Dalas','german','emanuel',null,60)
insert into S2 values(7,4,'Ferari','german','proffesional',null,85)

select * from S2 s11 LEFT JOIN S2 s22 ON s11.B1=s22.B2
select distinct * from S2 s11 INNER JOIN S2 s22 ON s11.B1=s22.B2
select * from S2 s11 right outer join S2 s22 ON s11.B1=s22.B1
where s11.Y LIKE '%e%'

select B2, Z,V
from S2
where V is not null or Y like '%c_%'


select B1, Y, count(distinct Y)
from S2
group by B1, Y
having max(W)>=5

select B2, X, count(Y)
from S2
group by B2, X
having min(W)<1

create table A1
(
X int,
Y int,
Z int
)


create table B1
(
Z int,
T int,
V int
)

SELECT Y, T FROM A1 INNER JOIN B1 ON A1.Z=B1.Z
SELECT T, Y FROM A1, B1 WHERE A1.Z=B1.Z 

insert into A1 values(1,2,3)
insert into A1 values(1,2,3)
insert into A1 values(4,5,6)

insert into B1 values(1,2,3)
insert into B1 values(2,6,7)
insert into B1 values(1,6,3)
insert into B1 values(3,7,8)
insert into B1 values(3,6,3)
insert into B1 values(3,2,8)
insert into B1 values(3,6,3)

--------------------------------------------------------------------------------------------------------------------------------------------
create table Groups
(
Gid int primary key,
NoOfStudents int not null,
TutorName varchar(50) not null,
LeaderName varchar(50) not null
)

create table Students
(
SId int primary key identity(1,1),
Name varchar(50) not null,
Surname varchar(50) not null,
Dob date not null,
GroupId int foreign key references Group(GId)
)

create table Courses
(
CId int primary key identity(1,1),
Name varchar(50) not null
)

create table Professors
(
Pid int primary key identity(1,1),
Name varchar(50) not null,
Surname varchar(50) not null,
FunctionP varchar(50) not null
)

create table Exams
(
SId int foreign key references Students(SId),
CId int foreign key references Courses(CId),
EDate date not null,
Mark float not null
constraint pk_Exams primary key (SId,CId)
)

create table ProfessorsCourses
(
PId int foreign key references Professors(PId),
CId int foreign key references Courses(CId),
Specialisation varchar(50) not null,
NoOfCredits varchar(50) not null
)

create or alter procedure sp_StudentCourse @SId int, @CId int, @EDate date, @Mark float
as
	declare @no int
	set @no =0
	select @no = count(*)
	from Exams E
	where E.SId = @SId and E.CId = @CId

	if (@no<>0)
	begin
		update Exams
		set EDate = @Edate, Mark = @Mark
		where SId = @SId and CId = @CId
	end
	else
	begin
		insert into Exams values(@SId, @CId,@EDate, @Mark)
	end
go

exec sp_StudentCourse 1,2,'2022.10.10',9.5

create or alter view vw_maxMark
as
	select S.GroupId, max(E.Mark)
	from Students S
	inner join Exams E
	on E.SId = S.SId
	group by S.GroupId
	having max(E.Mark) = (select max(Mark) from Exams)
go

create or alter function f_ProfessorsNames(@M int)
returns table
as
return
	select distinct P.PId, P.Name, count(PC.CId)
	from Professors P
	Inner join ProfessorCourses PC
	on P.PId = PC.PId
	group by P.Name
	having count(PC.CId)>=@m