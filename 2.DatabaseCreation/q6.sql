/* 6. Find actors that played five or more distinct roles in the same movie during the year 2010.
 Write a query that returns the actors' names, the movie name, 
 and the number of distinct roles that they played in that movie (which will be ≥ 5). */


SELECT P.NAME, M.TITLE, COUNT(R.MOVIE_ID) AS NUM_DISTINCT_ROLES FROM PERSON P, MOVIE M, ROLES R WHERE P.PERSON_ID = R.PERSON_ID AND M.SERIAL_NO = R.MOVIE_ID GROUP BY P.NAME, M.TITLE HAVING COUNT(R.MOVIE_ID) >=5;
