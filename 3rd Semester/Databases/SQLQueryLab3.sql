use Bakery1
go

CREATE TABLE DatabaseVersion
(
	Version INT NOT NULL
);

GO

INSERT INTO DatabaseVersion
VALUES (1)

GO


-- Stored procedure to add a column
CREATE OR ALTER PROCEDURE AddColumn_Version1
AS
BEGIN
    ALTER TABLE Customers
    ADD NoOfBenefits INT;
    
    -- Update version in the versioning table
    UPDATE DatabaseVersion
    SET Version = 2;
END;

GO

EXEC AddColumn_Version1

GO

-- Stored procedure to remove a column
CREATE OR ALTER PROCEDURE RemoveColumn_Version1
AS
BEGIN
    ALTER TABLE Customers
    DROP COLUMN NoOfBenefits;
    
    -- Update version in the versioning table
    UPDATE DatabaseVersion
    SET Version = 1;
END;

GO

EXEC RemoveColumn_Version1

GO

-- Stored procedure to add a DEFAULT constraint
CREATE OR ALTER PROCEDURE AddDefaultConstraint_Version2
AS
BEGIN
    ALTER TABLE Customers
    ADD CONSTRAINT DF_NoOfBenefits DEFAULT 1 FOR NoOfBenefits;
    
    -- Update version in the versioning table
    UPDATE DatabaseVersion
    SET Version = 3;
END;
GO

EXEC  AddDefaultConstraint_Version2

GO

-- Stored procedure to remove a DEFAULT constraint
CREATE OR ALTER PROCEDURE RemoveDefaultConstraint_Version2
AS
BEGIN
    ALTER TABLE Customers
    DROP CONSTRAINT DF_NoOfBenefits;
    
    -- Update version in the versioning table
    UPDATE DatabaseVersion
    SET Version = 2;
END;
GO

EXEC RemoveDefaultConstraint_Version2
GO

-- Stored procedure to create a table
CREATE OR ALTER PROCEDURE CreateTable_Version3
AS
BEGIN
    CREATE TABLE Parteners
    (
        PartenersID INT PRIMARY KEY IDENTITY,
		PName VARCHAR(50) NOT NULL,
		BakeryID INT NOT NULL
    );
    
    -- Update version in the versioning table
    UPDATE DatabaseVersion
    SET Version = 4;
END;
GO

EXEC CreateTable_Version3

GO
-- Stored procedure to drop a table
CREATE OR ALTER PROCEDURE DropTable_Version3
AS
BEGIN
    DROP TABLE Parteners;
    
    -- Update version in the versioning table
    UPDATE DatabaseVersion
    SET Version = 3;
END;

GO

EXEC DropTable_Version3

GO

-- Stored procedure to add a foreign key
CREATE OR ALTER PROCEDURE AddForeignKey_Version4
AS
BEGIN
    ALTER TABLE Parteners
    ADD CONSTRAINT FK_PartenersBakeries
    FOREIGN KEY (BakeryID)
    REFERENCES Bakeries(BakeryID);
    
    -- Update version in the versioning table
    UPDATE DatabaseVersion
    SET Version = 5;
END;
GO

EXEC AddForeignKey_Version4

GO
-- Stored procedure to remove a foreign key
CREATE OR ALTER PROCEDURE RemoveForeignKey_Version4
AS
BEGIN
    ALTER TABLE Parteners
    DROP CONSTRAINT FK_PartenersBakeries;
    
    -- Update version in the versioning table
    UPDATE DatabaseVersion
    SET Version = 4;
END;

GO

EXEC RemoveForeignKey_Version4

GO

-- Stored procedure to revert to a specific version
CREATE OR ALTER PROCEDURE revertToVersion(@TargetVersion INT)
AS
BEGIN
    -- Add logic to revert changes made in versions beyond @TargetVersion
    DECLARE @CurrentVersion INT;

	-- Get current version
	SELECT @CurrentVersion = Version FROM DatabaseVersion;
		-- Apply each operation
		WHILE @CurrentVersion < @TargetVersion
		BEGIN 
			SELECT @CurrentVersion = Version FROM DatabaseVersion;

			IF @CurrentVersion = 1
			BEGIN
				EXEC AddColumn_Version1;
			END
			ELSE IF @CurrentVersion = 2
			BEGIN
				EXEC AddDefaultConstraint_Version2;
			END
			ELSE IF @CurrentVersion = 3
			BEGIN
				EXEC CreateTable_Version3;
			END
			ELSE IF @CurrentVersion = 4
			BEGIN
				EXEC AddForeignKey_Version4;
			END

			-- UPDATE @CurrentVersion
		
			SELECT @CurrentVersion = Version FROM DatabaseVersion;
		END;
		WHILE @CurrentVersion > @TargetVersion
		BEGIN
			SELECT @CurrentVersion = Version FROM DatabaseVersion;
			IF @CurrentVersion = 2
			BEGIN
				EXEC RemoveColumn_Version1;
			END
			ELSE IF @CurrentVersion = 3
			BEGIN
				EXEC RemoveDefaultConstraint_Version2;
			END
			ELSE IF @CurrentVersion = 4
			BEGIN
				EXEC DropTable_Version3;
			END
			ELSE IF @CurrentVersion = 5
			BEGIN
				EXEC  RemoveForeignKey_Version4;
			END
			
			SELECT @CurrentVersion = Version FROM DatabaseVersion;
		END;

    
END;

GO


EXEC revertToVersion 2;

SELECT * FROM DatabaseVersion
