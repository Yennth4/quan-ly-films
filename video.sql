CREATE DATABASE ASM2_J4
USE ASM2_J4
CREATE TABLE USERS(
    ID VARCHAR(100) PRIMARY KEY,
    PASSWORD VARCHAR(100),
    FULLNAME NVARCHAR(100),
    EMAIL NVARCHAR(100),
    PHOTO NVARCHAR(500),
    ACTIVATED TINYINT,
    ADMIN TINYINT
);


CREATE TABLE VIDEOS(
    ID VARCHAR(100) PRIMARY KEY,
    TITLE NVARCHAR(200) ,
    POSTER NVARCHAR(200),
    DESCRIPTION NVARCHAR(200),
    VIEWS INT,
    ACTIVE TINYINT
);

CREATE TABLE FAVORITES(
    ID BIGINT PRIMARY KEY IDENTITY (1, 1),
    USERID VARCHAR(100),
    VIDEOID VARCHAR(100),
    LIKEDATE DATE,
    FOREIGN KEY (USERID) REFERENCES USERS(ID),
    FOREIGN KEY (VIDEOID) REFERENCES VIDEOS(ID)
);

-- Insert into USERS table
INSERT INTO USERS (ID, PASSWORD, FULLNAME, EMAIL, PHOTO, ACTIVATED, ADMIN)
VALUES
('admin', '123', N'John Doe', N'john@example.com', N'user1.jpg', 1, 0),
('1', '1', N'Jane Smith', N'jane@example.com', N'user2.jpg', 1, 0),
('haiyen', '123', N'Michael Johnson', N'michael@example.com', N'user3.jpg', 1, 0),
('bray', '123', N'Sarah Brown', N'sarah@example.com', N'user4.jpg', 1, 0),
('amee', '123', N'Emily Davis', N'emily@example.com', N'user5.jpg', 1, 0);

-- Insert into VIDEOS table
INSERT INTO VIDEOS (ID, TITLE, POSTER, DESCRIPTION, VIEWS, ACTIVE)
VALUES
('uv7q1Dlbhc4', '', N'poster1.jpg', N'Description of Video 1', 100, 1),
('Rz2pVLYWQDI', N'Title of Video 2', N'poster2.jpg', N'Description of Video 2', 150, 1),
('usHkU9tGPeo', N'Title of Video 3', N'poster3.jpg', N'Description of Video 3', 200, 1),
('Hgd-F2sQHos', N'Title of Video 4', N'poster4.jpg', N'Description of Video 4', 80, 1),
('WL11bwvAYWI', N'NGÀY MAI NGƯỜI TA LẤY CHỒNG - THÀNH ĐẠT ', N'poster5.jpg', N'Description of Video 5', 120, 1);

-- Insert into FAVORITES table
INSERT INTO FAVORITES (USERID, VIDEOID, LIKEDATE)
VALUES
('user1', 'video1', '2024-04-01'),
('user2', 'video1', '2024-04-02'),
('user3', 'video2', '2024-04-03'),
('user4', 'video2', '2024-04-04'),
('user5', 'video3', '2024-04-05');

drop TABLE USERS