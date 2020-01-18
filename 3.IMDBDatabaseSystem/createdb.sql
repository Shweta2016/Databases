/* CREATE TABLE QUERIES  */
CREATE TABLE MOVIES (id int not null primary key, title varchar2(500), imdbID number(7), spanishTitle varchar2(500), 
	imdbPictureURL varchar2(500), year varchar2(4), rtID varchar2(500), rtAllCriticsRating int, 
	rtAllCriticsNumReviews int, rtAllCriticsNumFresh int, rtAllCriticsNumRotten int, 
	rtAllCriticsScore int, rtTopCriticsRating int, rtTopCriticsNumReviews int, rtTopCriticsNumFresh int,
	rtTopCriticsNumRotten int, rtTopCriticsScore int, rtAudienceRating int, rtAudienceNumRatings int,
	rtAudienceScore int, rtPictureURL varchar2(500));

CREATE TABLE MOVIE_GENRES (movieID int not null, genre varchar2(15), foreign key (movieID) references MOVIES(id));

CREATE TABLE MOVIE_DIRECTORS (movieID int not null, directorID varchar2(50) not null, 
	directorName varchar2(50), foreign key (movieID) references MOVIES(id));

CREATE TABLE MOVIE_ACTORS (movieID int not null, actorID VARCHAR2(50), actorName varchar2(50), 
	ranking int, foreign key (movieID) references MOVIES(id));

CREATE TABLE MOVIE_COUNTRIES (movieID int, country varchar2(200), foreign key (movieID) references MOVIES(id));

CREATE TABLE MOVIE_LOCATIONS (movieID int, location1 varchar2(200), location2 varchar2(500), 
	location3 varchar2(500), location4 varchar2(500), foreign key (movieID) references MOVIES(id));

CREATE TABLE TAGS (id int not null primary key, value varchar2(500));

CREATE TABLE MOVIE_TAGS (movieID int, tagID int, tagWeight int, foreign key (movieID) references MOVIES(id), 
	foreign key (tagID) references TAGS(id));

CREATE TABLE USER_TAGGEDMOVIES_TIMESTAMPS1 (userID int NOT NULL, movieID int NOT NULL, tagID int NOT NULL,	
	timestamp1 varchar2(20), foreign key (movieID) references MOVIES(id), foreign key (tagID) references TAGS(id));

CREATE TABLE USER_TAGGEDMOVIES (userID int not null, movieID int not null, tagID int not null, date_day varchar2(10), 
	date_month varchar2(10), date_year varchar2(10), date_hour varchar2(10), 
	date_minute varchar2(10), date_second varchar2(10), foreign key (movieID) references MOVIES(id), 
	foreign key (tagID) references TAGS(id));

CREATE TABLE USER_RATEDMOVIES_TIMESTAMPS (userID int not null, movieID int not null, rating int, 
	timestamp1 varchar2(20), foreign key (movieID) references MOVIES(id));

CREATE TABLE USER_RATEDMOVIES (userID int not null, movieID int not null, rating int, 
	date_day varchar2(10), date_month varchar2(10), date_year varchar2(10), date_hour varchar2(10), 
	date_minute  varchar2(10), date_second varchar2(10), foreign key (movieID) references MOVIES(id));


/* CREATE INDEX QUERIES */

CREATE INDEX INDEX_GENRE ON MOVIE_GENRES(genre);
CREATE INDEX INDEX_TAGID ON MOVIE_TAGS(tagID);
CREATE INDEX INDEX_MOVIE_LOCATIONS ON MOVIE_LOCATIONS(location1);


/* CREATE VIEW QUERIES */

CREATE VIEW MOVIE_RATING AS SELECT id, rtAllCriticsRating+rtTopCriticsRating+rtAudienceRating/3 AS AVGRATING FROM MOVIES;
CREATE VIEW MOVIE_REVIEW AS SELECT id, rtAllCriticsNumReviews+rtTopCriticsNumReviews+rtAudienceNumRatings/3 AS NOREVIEWS FROM MOVIES;

