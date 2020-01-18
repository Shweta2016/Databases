/* 5. Find the film(s) with the largest cast. Return the movie title and the size of the cast.
 By "cast size" we mean the number of distinct actors that played in that movie:
 if an actor played multiple roles, or
 if the actor is simply listed more than once in CASTS, 
 we still count her/him only once. */


SELECT M.TITLE, COUNT(DISTINCT(R.PERSON_ID)) AS CAST_SIZE FROM MOVIE M, ROLES R WHERE M.SERIAL_NO = R.MOVIE_ID GROUP BY M.TITLE HAVING COUNT(DISTINCT(R.PERSON_ID)) = (SELECT MAX(COUNTED) FROM( SELECT COUNT(DISTINCT(R.PERSON_ID)) AS COUNTED FROM ROLES R GROUP BY MOVIE_ID));







