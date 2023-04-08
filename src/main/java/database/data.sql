CREATE DATABASE STUDENT_MANAGEMENT;
GO 
USE STUDENT_MANAGEMENT;
GO 

CREATE TABLE STUDENT
(
    IDSTU CHAR(8),
    NAME NVARCHAR(40),
    GRADE FLOAT CHECK (GRADE >= 0 AND GRADE <= 10),
    BIRTHDAY DATE,
    ADDRESS NVARCHAR(100),
    NOTES NVARCHAR(100)

    CONSTRAINT PK_STUDENT PRIMARY KEY (IDSTU)
)

CREATE TABLE COURSE
(
    IDCOURSE CHAR(8),
    NAME NVARCHAR(40),
    LECTURE NVARCHAR(40),
    YEAR INT,
    SEMESTER INT CHECK (SEMESTER >= 1 AND SEMESTER <= 3),
    NOTES NVARCHAR(100),
    CREDIT INT CHECK (CREDIT >= 0 AND CREDIT <= 20),
    NOTES NVARCHAR(100)

    CONSTRAINT PK_COURSE PRIMARY KEY (IDCOURSE, LECTURE, YEAR, SEMESTER)
)

CREATE TABLE TAKEPARTINCOURSE
(
    STUID CHAR(8),
    COURSEID CHAR(8),
    LECTURE NVARCHAR(40),
    YEAR INT,
    SEMESTER INT CHECK (SEMESTER >= 1 AND SEMESTER <= 3),
    GRADE FLOAT CHECK (GRADE >= 0 AND GRADE <= 10)

    CONSTRAINT PK_TAKEPARTINCOURSE PRIMARY KEY (STUID, COURSEID, LECTURE, YEAR, SEMESTER)
)

ALTER TABLE TAKEPARTINCOURSE ADD 
    CONSTRAINT FK_TAKEPARTINCOURSE_STUDENT FOREIGN KEY (STUID) REFERENCES STUDENT(IDSTU),
    CONSTRAINT FK_TAKEPARTINCOURSE_COURSE FOREIGN KEY (COURSEID, LECTURE, YEAR, SEMESTER) REFERENCES COURSE(IDCOURSE, LECTURE, YEAR, SEMESTER)

INSERT INTO STUDENT VALUES('21120553', N'Trần Thái Tân',  0, '2003-08-30', N'Kí túc xá khu B', N'Học khoa CNTT')
INSERT INTO STUDENT VALUES('21120554', N'Nguyễn Văn A',  0, '2003-08-30', N'Kí túc xá khu B', N'Học khoa CNTT')
INSERT INTO STUDENT VALUES('21120555', N'Nguyễn Văn B',  0, '2003-08-30', N'Kí túc xá khu B', N'Học khoa CNTT')
INSERT INTO STUDENT VALUES('21120556', N'Nguyễn Văn C',  0, '2003-08-30', N'Kí túc xá khu B', N'Học khoa CNTT')
INSERT INTO STUDENT VALUES('21120557', N'Nguyễn Văn D',  0, '2003-08-30', N'Kí túc xá khu B', N'Học khoa CNTT')

