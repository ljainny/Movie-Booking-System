create sequence movieSeq start with 100;

create sequence theaterSeq start with 1000;

create sequence bookSeq start with 2000;

create table movies(movieId number(4) primary key, movie_Name varchar(30) unique);

insert into movies values(movieSeq.nextval,'Harry Potter');

insert into movies values(movieSeq.nextval,'IT');

insert into movies values(movieSeq.nextval,'ANNA BELLE');

commit;

select * from movies;

create table theaters(theaterId number(5) primary key, theaterName varchar(30) unique, movieId number(4) references movies(movieId),availableTickets number(3), moviePlayTime timestamp,price number(5,2));

insert into theaters values(theaterSeq.nextval,'Big Cinemas',100,70,'09-SEP-17 5:30:05 PM',245.74);

insert into theaters values(theaterSeq.nextval,'PVR Cinemas',100,20,'09-SEP-17 2:30:05 PM',200.74);

insert into theaters values(theaterSeq.nextval,'INOX',101,30,'09-SEP-17 10:30:05 AM',100);

insert into theaters values(theaterSeq.nextval,'City Pride Satara Road',101,2,'08-SEP-17 10:30:05 PM',175.50);

insert into theaters values(theaterSeq.nextval,'ESquare',102,0,'09-SEP-17 11:30:00 AM',195.50);

insert into theaters values(theaterSeq.nextval,'City Pride Kothrud',102,0,'09-SEP-17 01:30:00 PM',195.50);

commit;
create table moviebooking(bid number(4) primary key, theaterName varchar(30
), ticketsBooked number(3), price number(5,2),movieTime timestamp, 
bookingdate date);



select * from movies;

select * from theaters;

SELECT bookSeq.nextval FROM DUAL