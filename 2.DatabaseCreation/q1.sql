/* 1. Print names of the cast of the movie “The Da Vinci Code” in ascending alpha order. */

SELECT P.NAME FROM PERSON P, ROLES R, MOVIE M WHERE P.PERSON_ID = R.PERSON_ID AND R.MOVIE_ID = M.SERIAL_NO AND M.TITLE = 'The Da Vinci Code' ORDER BY P.NAME ASC;



 
