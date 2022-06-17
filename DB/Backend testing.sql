--Run the SpringBoot code which will make the users and roles tables. After theyre made run this script 
--to add the inital roles and make some connections. After running this our api is set up.

use dinnerdash

--Adding the required roles for users.
--insert into roles (name) values('ROLE_RESTAURANT')
--insert into roles (name) values('ROLE_CUSTOMER')
--select * from roles

--Adding a link between Customer and user tables.
--alter table Customer add foreign key (CustomerID) references users(id)
--alter table Restaurant add foreign key (RestaurantID) references users(id)


-- Adding an offering and adding it to cart
select * from Cart
select * from Customer
select * from users
select * from Restaurant
select * from Orders
select * from Offering
select * from user_roles

	
-- delete from roles where id>2
--delete from users
--delete from Customer

--alter table Orders add RestaurantID int
--alter table Orders add foreign key (RestaurantID) references Restaurant(RestaurantID)
