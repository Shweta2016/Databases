/* 8. Find out who are the “no flop” actors: we will define a 
“no flop” actor as one who has played only in movies which have a rating greater than or equal to 8.
 Split this problem into the following steps.
a. Create a view called high ratings which contains the distinct names of 
all actors who have played in movies with a rating greater than or equal to 8.
 Similarly, create a view called low ratings which contains the distinct names
  of all actors who have played in movies with a rating less than 8. 
  Print a) the number of rows in the view high ratings and 
  b) the number of rows in the view low ratings
b. Use the above views to print the number of “no flop” actors in the database.
c. Finally, use the above view to print the names of these “no flop” actors, 
along with the number M of movies they have played in, sorted by descending M 
and then by ascending name, and print only the top 10. */

/* A. CREATING VIEW HIGH RATING */
CREATE VIEW HIGH_RATING AS SELECT DISTINCT P.NAME FROM PERSON P, ROLES R, MOVIE M, REVIEW RA WHERE P.PERSON_ID = R.PERSON_ID AND M.SERIAL_NO = R.MOVIE_ID AND RA.MOVIE_ID = M.SERIAL_NO AND RA.RATING >= 8;
/* CREATING VIEW LOW RATING */
CREATE VIEW LOW_RATING AS SELECT DISTINCT P.NAME FROM PERSON P, ROLES R, MOVIE M, REVIEW RA WHERE P.PERSON_ID = R.PERSON_ID AND M.SERIAL_NO = R.MOVIE_ID AND RA.MOVIE_ID = M.SERIAL_NO AND RA.RATING < 8;
/* PRINT NUM OF ROWS IN HIGH RATING */
SELECT COUNT(*) FROM HIGH_RATING;
/* PRINT NUM OF ROWS IN LOW RATING */
SELECT COUNT(*) FROM LOW_RATING;

/* B. PRINT NUMBER OF NO FLOP ACTORS */
SELECT COUNT(*) FROM HIGH_RATING WHERE NAME NOT IN(SELECT NAME FROM LOW_RATING);

/* C. FLOP ACTORS WITH NUMBER OF MOVIES */
CREATE VIEW NO_FLOP AS SELECT * FROM HIGH_RATING WHERE NAME NOT IN(SELECT NAME FROM LOW_RATING);
SELECT NF.NAME, COUNT(M.SERIAL_NO) AS MCOUNT FROM NO_FLOP NF, MOVIE M, ROLES R, PERSON P WHERE M.SERIAL_NO = R.MOVIE_ID AND R.PERSON_ID = P.PERSON_ID AND P.NAME = NF.NAME AND ROWNUM<=10 GROUP BY NF.NAME ORDER BY MCOUNT DESC, NF.NAME ASC;



