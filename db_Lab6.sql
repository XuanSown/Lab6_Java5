CREATE DATABASE db_Lab6
GO
USE db_Lab6
GO

DELETE FROM OrderDetails;
DBCC CHECKIDENT ('OrderDetails', RESEED, 0);
DELETE FROM Products;
DBCC CHECKIDENT ('Products', RESEED, 0);

SELECT *
FROM Categories
SELECT *
FROM Products
SELECT *
FROM Orders
SELECT *
FROM OrderDetails
SELECT *
FROM Accounts

CREATE TABLE Categories (
    Id char(4) PRIMARY KEY,
    Name nvarchar(50) NOT NULL
)

CREATE TABLE Accounts (
    Username nvarchar(50) PRIMARY KEY,
    Password nvarchar(50) NOT NULL,
    Fullname nvarchar(50),
    Email nvarchar(50),
    Photo nvarchar(50),
    Activated bit,
    Admin bit
)

CREATE TABLE Products (
    Id int IDENTITY(1,1) PRIMARY KEY,
    Name nvarchar(50) NOT NULL,
    Image nvarchar(50),
    Price float,
    CreateDate date,
    Available bit,
    CategoryId char(4) REFERENCES Categories(Id)
)

CREATE TABLE Orders (
    Id bigint IDENTITY(1,1) PRIMARY KEY,
    Username nvarchar(50) REFERENCES Accounts(Username),
    CreateDate datetime,
    Address nvarchar(100)
)

CREATE TABLE OrderDetails (
    Id bigint IDENTITY(1,1) PRIMARY KEY,
    OrderId bigint REFERENCES Orders(Id),
    ProductId int REFERENCES Products(Id),
    Price float,
    Quantity int
)

-- 1. Insert Categories (No dependencies)
INSERT INTO Categories (Id, Name) VALUES
('1000', N'Đồng hồ đeo tay'),
('1001', N'Máy tính xách tay'),
('1002', N'Máy ảnh'),
('1003', N'Điện thoại'),
('1004', N'Nước hoa'),
('1005', N'Nữ trang'),
('1006', N'Nón thời trang'),
('1007', N'Túi xách du lịch');

-- 2. Insert Accounts (No dependencies)
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo, Activated, Admin) VALUES
('admin', '123', N'Nguyễn Văn Quản Trị', 'admin@gmail.com', 'user.png', 1, 1),
('user1', '123', N'Trần Thị Người Dùng', 'user1@gmail.com', 'user.png', 1, 0),
('user2', '123', N'Lê Văn Khách', 'user2@gmail.com', 'user.png', 1, 0),
('teolv', '123', N'Lý Văn Tèo', 'teolv@gmail.com', 'teo.jpg', 0, 0),
('pheonv', '123', N'Nguyễn Văn Phèo', 'pheonv@fpt.edu.vn', 'pheo.jpg', 1, 1);

-- 3. Insert Products
INSERT INTO Products (Name, Price, CreateDate, Available, CategoryId, Image) VALUES
(N'Aniseed Syrup', 190.0, '2025-03-29', 1, '1000', 'p1.jpg'),
(N'Cate de Blaye', 263.5, '2025-07-12', 1, '1000', 'p2.jpg'),
(N'Tharinger Rostbratwurst', 123.79, '2025-12-21', 1, '1001', 'p3.jpg'),
(N'Mishi Kobe Niku', 97.0, '2025-12-10', 1, '1001', 'p4.jpg'),
(N'Sir Rodneys Marmalade', 81.0, '2025-11-01', 1, '1002', 'p5.jpg'),
(N'Sir Rodneys Scones', 10.0, '2025-07-29', 1, '1002', 'p6.jpg'),
(N'Gustaf flower', 21.0, '2025-12-01', 1, '1003', 'p7.jpg'),
(N'Tunnbr Korea', 9.0, '2025-08-31', 1, '1003', 'p8.jpg'),
(N'Guaranaj Fantajstica', 4.5, '2025-03-13', 1, '1004', 'p9.jpg'),
(N'NuNuCa Nuaa-Nougat-Creme', 14.0, '2025-07-20', 1, '1004', 'p10.jpg');

-- 4. Insert Orders
-- Since ID is Identity, it will start at 1 if the table was just created/truncated
INSERT INTO Orders (Address, CreateDate, Username) VALUES
(N'123 Đường Lê Lợi, TP.HCM', '2025-01-15', 'user1'),
(N'456 Đường Nguyễn Huệ, Hà Nội', '2025-01-16', 'user2'),
(N'789 Đường 3/2, Cần Thơ', '2025-01-17', 'user1');

INSERT INTO OrderDetails (OrderId, ProductId, Price, Quantity) VALUES
(1, 1, 100, 5),
(1, 2, 500, 2),
(2, 3, 300, 1);