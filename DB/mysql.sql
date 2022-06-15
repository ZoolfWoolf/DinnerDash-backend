
drop database dinnerdash;
Create database dinnerdash;
use dinnerdash;


CREATE TABLE Restaurant (
    RestaurantID INT NOT NULL,
    RestaurantName VARCHAR(30) NOT NULL,
    ColorTheme VARCHAR(30),
    BannerUrl VARCHAR(30),
    PRIMARY KEY (RestaurantID)
);


CREATE TABLE Offering (
    OfferingID INT NOT NULL AUTO_INCREMENT,
    RestaurantID INT NOT NULL,
    OfferingName VARCHAR(30) NOT NULL,
    OfferingDescription LONGTEXT,
    Price INT NOT NULL,
    OfferingPhotoURL VARCHAR(30),
    FOREIGN KEY (RestaurantID)
        REFERENCES Restaurant (RestaurantID),
    PRIMARY KEY (OfferingID)
);


CREATE TABLE Customer (
    CustomerID INT NOT NULL,
    WalletAmount INT NOT NULL,
    PhoneNumber VARCHAR(11) NOT NULL,
    PRIMARY KEY (CustomerID)
);


CREATE TABLE Cart (
    CustomerID INT,
    OfferingID INT,
    Quantity INT NOT NULL,
    FOREIGN KEY (CustomerID)
        REFERENCES Customer (CustomerID),
    FOREIGN KEY (OfferingID)
        REFERENCES Offering (OfferingID),
    CONSTRAINT PK_Cart PRIMARY KEY (CustomerID , OfferingID)
);


CREATE TABLE Orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    RestaurantID INT NOT NULL,
    OrderTime TIME NOT NULL,
    PaymentMethod VARCHAR(30) NOT NULL,
    OrderStatus VARCHAR(30) NOT NULL,
    FOREIGN KEY (CustomerID)
        REFERENCES Customer (CustomerID),
    FOREIGN KEY (RestaurantID)
        REFERENCES Restaurant (RestaurantID)
);


CREATE TABLE OrderItems (
    OrderID INT,
    OfferingID INT,
    Quantity INT NOT NULL,
    Price INT NOT NULL,
    FOREIGN KEY (OrderID)
        REFERENCES Orders (OrderID),
    FOREIGN KEY (OfferingID)
        REFERENCES Offering (OfferingID),
    CONSTRAINT PK_OrderItems PRIMARY KEY (OrderID , OfferingID)
);
