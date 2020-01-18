HW 3
**********************************
Shweta Kharat

**********************************

Problem Statement: Develop a data analysis application for imdb.com’s user review data

You are required to implement two Java programs:
1. populate.java: This program should get the names of the input files as command line parameters and populate them into your database. It should be executed as:“> java populate <filename1.dat> <filename2.dat>…..<filename.dat>”.Note that every time you run this program, it should remove the previous data in your tables; otherwise the tables will have redundant data.2. hw3.java: This program should provide a GUI, similar to figure 1, to query your database. The GUI should include:a. List of movie genres.b. Countries where the movies are produced.c. Filming location country where movies are filmedd. Critic’s rating which is Rotten Tomato all critics rating (rtAllCrtiticsRating)e. No. of Reviews, which is the Rotten Tomatoes all critics' number of reviewsf. Movie year.g. Movie tags valuesh. List of resultsi. Results should include movie title, genre, year, country, all filming locations, average of Rotten Tomato All Critics rating and Rotten Tomato top critics rating and Rotten tomato audience rating, average of Rotten Tomato all Critics number of reviews and Rotten Tomato Top critics number of reviews and Rotten Tomato Audience number of ratings.

---------------------------------
COMPILE and RUN:
----------------------------------


********** To run dropdb.sql
 start C:\Users\admin\Desktop\COEN280HW3\shweta_kharat_hw3\dropdb.sql


********** To run createdb.sql
start C:\Users\admin\Desktop\COEN280HW3\shweta_kharat_hw3\createdb.sql


********** To run populate.java (NOTE : you need to change path of files in pupulate.java)
javac populate.java
java populate movies.dat movie_genres.dat movie_countries.dat movie_locations.dat tags.dat movie_tags.dat

********** File Names Sequence
movies.dat movie_genres.dat movie_countries.dat movie_locations.dat tags.dat movie_tags.dat








