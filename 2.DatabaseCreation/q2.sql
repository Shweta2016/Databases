/* 2. Print all information (mid, title, year, num ratings, rating) for the movie(s) 
with the highest rating (include all that tie for first place). Order by ascending mid. */


SELECT M.SERIAL_NO, M.TITLE, M.RELEASE_YEAR, R.RATING, R.VOTES FROM MOVIE M, REVIEW R WHERE R.RATING >= ALL(SELECT MAX(RATING) FROM REVIEW) AND R.MOVIE_ID=M.SERIAL_NO ORDER BY M.SERIAL_NO ASC;





