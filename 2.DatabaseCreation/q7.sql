/* 7. Print the movie year, title and rating of the highest rated movie
 for each years in the period 2005-present, inclusive, in ascending year order.
  In case of a tie, print all, sorted in ascending alpha order on the title. */


SELECT M.SERIAL_NO, M.TITLE, M.RELEASE_YEAR, M.DIRECTOR, MAX(R.RATING) FROM REVIEW R, MOVIE M WHERE M.SERIAL_NO = R.MOVIE_ID AND M.RELEASE_YEAR >= 2005 GROUP BY M.SERIAL_NO, M.TITLE, M.RELEASE_YEAR, M.DIRECTOR ORDER BY M.RELEASE_YEAR ASC ;















