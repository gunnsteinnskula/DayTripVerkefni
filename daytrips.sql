-- PRAGMA foreign_keys=ON;
-- .mode column
-- .headers on

-- drop table if exists bookings;
-- drop table if exists hosts;
-- drop table if exists dates;
-- drop table if exists tourists;
-- drop table if exists trips;
-- drop table if exists dayTrips;
-- drop table if exists travelAgencies;
-- drop table if exists festivals;

-- create table if not exists travelAgencies(
-- 	id int primary key,
-- 	rating int,
-- 	name varchar(30)
-- 	);

-- create table if not exists dayTrips(
-- 	id int primary key,
-- 	name varchar(40),
-- 	price int,
-- 	length int,
-- 	type varchar(10),
-- 	location varchar(20),
-- 	rating int,
-- 	startDate Date,
-- 	endDate Date,
-- 	travelAgency int references travelAgencies(id),
-- 	size int,
-- 	extraInfo varchar(255)
-- 	);

-- create table if not exists trips(
-- 	id int primary key,
-- 	dayTrip int references dayTrips(id),
-- 	maxsize int,
-- 	bookings int,
-- 	startdate Date,
-- 	enddate Date
-- );

-- create table if not exists tourists(
-- 	email varchar(50) primary key,
-- 	name varchar(40),
-- 	country varchar(30),
-- 	age int
-- );

-- create table if not exists dates(
-- 	dayTrip int references dayTrips(id),
-- 	trips int references trips(id)
-- );

-- create table if not exists hosts(
-- 	dayTrip int references dayTrips(id),
-- 	travelAgency int references travelAgencies(id)
-- );

-- create table if not exists bookings(
-- 	tourist varchar(50) references tourists(email),
-- 	trip int references trips(id),
-- 	bookingnumber int primary key
-- );

-- create table if not exists festivals(
-- 	length int,
-- 	name varchar(40),
-- 	type varchar(40),
-- 	startDate Date,
-- 	endDate Date,
-- 	location varchar(40),
-- 	price int
-- );

-- insert into travelAgencies values(1,3,"Reykjavik Excursions");
-- insert into dayTrips values(1,"Golden Circle", 10500, 1, "Sightseeing", "Sudur", 4, '2016-01-01', '2016-12-31', 1, null, "bus fare included");
-- insert into dayTrips values(2,"Geysir", 10500, 1, "Wetting", "Sudur", 4, '2016-01-01', '2016-12-31', 1, null, "bus fare not included");
-- insert into trips values(1,1,50,0,'2016-02-06', '2016-03-06');
-- insert into dates values(1,1);
-- insert into festivals values(3, "Thjodhatid", "Outside concert", '2016-07-28', '2016-08-01', "Vestmannaeyjar", 19500);
-- insert into festivals values(7, "Besta innihatidin", "Heimaparty", '2016-07-28', '2016-08-01', "Aratun 40", 1);
-- insert into festivals values(5, "Iceland Airwaves", "Music festival", '2016-11-02', '2016-11-06', 'Reykjavik', 19900);

SELECT * FROM festivals;
SELECT * FROM trips;
SELECT * FROM dayTrips WHERE startDate <= '2016-07-01' AND endDate >= '2016-12-01' AND name = 'Golden Circle';
SELECT * FROM festivals WHERE startDate >='2016-01-01' AND endDate <='2016-01-31';