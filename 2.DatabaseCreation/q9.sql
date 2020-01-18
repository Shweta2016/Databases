/* 9. Print the names of all actors who have starred in all movies in which 
Al pacino has starred in (it’s ok to report the name of Al pacino in the result; 
also, it is ok if these actors have starred in more movies than Al pacino has played in). */

/* List of all the movies of Al Pacino */
CREATE VIEW ALPACINO_MOVIES AS SELECT M.SERIAL_NO FROM MOVIE M, ROLES R, PERSON P WHERE M.SERIAL_NO = R.MOVIE_ID AND R.PERSON_ID = P.PERSON_ID AND P.NAME = 'Al Pacino';

SELECT DISTINCT P.NAME FROM PERSON P, ALPACINO_MOVIES A, ROLES R WHERE A.SERIAL_NO = R.MOVIE_ID AND P.PERSON_ID = R.PERSON_ID;

















