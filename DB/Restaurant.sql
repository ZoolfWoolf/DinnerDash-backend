--Run this script in sql server to make our backend database.

Create database dinnerdash
use dinnerdash

create table Restaurant(
	RestaurantID int not null,  
	RestaurantName varchar(30) not null, 
	ColorTheme varchar(30),
	BannerUrl varchar(30), 

	Primary Key (RestaurantID)
);

create table Offering(
	OfferingID int not null IDENTITY(1,1), 
	RestaurantID int not null, 
	OfferingName varchar(30) not null, 
	OfferingDescription text, 
	Price int not null, 
	OfferingPhotoURL varchar(30),	
	
	Foreign Key (RestaurantID) References Restaurant(RestaurantID),
	Primary Key (OfferingID)
);

create table Customer(
	CustomerID int not null,  
	WalletAmount int not null,
	PhoneNumber varchar(11) not null, 

	Primary Key (CustomerID)
);

create table Cart(
	CustomerID int Foreign key references Customer(CustomerID), 
	OfferingID int Foreign key references Offering(OfferingID),
	Quantity int not null,

	Constraint	PK_Cart Primary Key (CustomerID, OfferingID)
);

create table Orders(
	OrderID int IDENTITY(1,1) Primary Key, 
	CustomerID int Foreign Key references Customer(CustomerID),
	RestaurantID int Foreign Key references Restaurant(RestaurantID) not null,
	OrderTime Time not null,
	PaymentMethod varchar(30) not null, 
	OrderStatus varchar(30) not null,
);

create table OrderItems(
	OrderID int Foreign Key references Orders(OrderID),
	OfferingID int Foreign Key references Offering(OfferingID),
	Quantity int not null, 
	Price int not null, 
		
	Constraint PK_OrderItems Primary Key (OrderID, OfferingID)
);
