/* 10. Find out who is the actor with the highest “longevity.” 
Print the name of the actor/actress who has been playing in movies for the longest period of time 
(i.e. the time interval between their first movie and their last movie is the greatest. */


CREATE VIEW ACTOR_LONGETIVITY AS SELECT P.NAME, (MAX(M.RELEASE_YEAR) - MIN(M.RELEASE_YEAR)) AS DIFF FROM MOVIE M, PERSON P, ROLES R WHERE M.SERIAL_NO = R.MOVIE_ID AND P.PERSON_ID = R.PERSON_ID GROUP BY P.NAME ORDER BY 2 DESC;
SELECT A.NAME FROM ACTOR_LONGETIVITY A WHERE A.DIFF >= ALL (SELECT DIFF FROM ACTOR_LONGETIVITY) ORDER BY A.NAME ASC;

