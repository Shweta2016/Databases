package HW3Sol;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class populate {
	String movieFile;
	String genreFile, LocationFile, CountryFile, TagFile, MovieTagFile;

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub

			// TODO Auto-generated method stub
		
		/*for(String s: args) {
			System.out.println(s);
		}*/
		
		String movieFile = args[0].toString();
		String genreFile = args[1].toString();
		String LocationFile = args[2].toString();
		String CountryFile = args[3].toString();
		String TagFile = args[4].toString();
		String MovieTagFile = args[5].toString();
		//System.out.println("File names : "+movieFile+genreFile+LocationFile+CountryFile+TagFile+MovieTagFile);
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// *********Connect to Oracle database
			String host = "localhost";
			String dbName = "shwetadb";
			int port = 1521;
			String oracleURL = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName;
			//String URL = "jdbc:oracle:thin:@localhost:1521:shwetadb";
			String userName = "shweta";
			String pwd = "Carpet";
			Connection conn = DriverManager.getConnection(oracleURL, userName, pwd);
			Statement statement = conn.createStatement();
			PreparedStatement ps;
			BufferedReader br;
			
			
			
			//	************Read data from movies.dat
			String file1 = "C:/Users/admin/Desktop/COEN280HW3/hetrec2011-movielens-2k-v2/"+movieFile;
			br = new BufferedReader(new FileReader(file1));
			String line1 = null;
			line1 = br.readLine();
			
			// ************Insert data in MOVIES table
			while((line1 = br.readLine()) !=null) {
				System.out.println(line1);
				String temp1[] = line1.split("\t");
				for(int i=0;i<temp1.length;i++) {
					System.out.println("temp[" + i + "]" + temp1[i]);
				}
				
				String sql1 = "INSERT INTO MOVIES (id, title, imdbID, spanishTitle, imdbPictureURL, year, "
						+ "rtID, rtAllCriticsRating, rtAllCriticsNumReviews, rtAllCriticsNumFresh, "
						+ "rtAllCriticsNumRotten, rtAllCriticsScore, rtTopCriticsRating, "
						+ "rtTopCriticsNumReviews, rtTopCriticsNumFresh, rtTopCriticsNumRotten, "
						+ "rtTopCriticsScore, rtAudienceRating, rtAudienceNumRatings, "
						+ "rtAudienceScore, rtPictureURL) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				ps = conn.prepareStatement(sql1);
				//int id = Integer.parseInt(temp[0]);
				ps.setInt(1, Integer.parseInt(temp1[0]));
				ps.setString(2, temp1[1]);
				ps.setInt(3, Integer.parseInt(temp1[2]));
				ps.setString(4, temp1[3]);
				ps.setString(5, temp1[4]);
				ps.setString(6, temp1[5]);
				ps.setString(7, temp1[6]);
			    Float d[] = new Float[20];
				for(int i = 7; i<20; i++) {
					try {
				    d[i] = Float.parseFloat(temp1[i]);
				    } catch (NumberFormatException | NullPointerException nfe) {
				        d[i] = 0f;
				    }
				}
				ps.setFloat(8, d[7]);
				ps.setFloat(9, d[8]);
				ps.setFloat(10, d[9]);
				ps.setFloat(11, d[10]);
				ps.setFloat(12, d[11]);
				ps.setFloat(13, d[12]);
				ps.setFloat(14, d[13]);
				ps.setFloat(15, d[14]);
				ps.setFloat(16, d[15]);
				ps.setFloat(17, d[16]);
				ps.setFloat(18, d[17]);
				ps.setFloat(19, d[18]);
				ps.setFloat(20, d[19]);
				ps.setString(21, temp1[20]);
				
				ps.executeUpdate();
				ps.close();
			}
			br.close();
			
			// ********** Display data in MOVIES table
			System.out.println("Data inserted in MOVIES");
			String query = "select * from MOVIES";
			ResultSet rs = statement.executeQuery(query);
			//System.out.println("after rs");
			while(rs.next()) {
				System.out.println(rs.getInt(1) + "   " + rs.getString(2)+ "   " + rs.getString(3)
				+ "   " + rs.getString(4)+ "   " + rs.getString(5)+ "   " + rs.getString(6)
				+ "   " + rs.getString(7)+ "   " + rs.getString(8)
				+ "   " + rs.getString(9)+ "   " + rs.getString(10)+ "   " + rs.getString(11)
				+ "   " + rs.getString(12)+ "   " + rs.getString(13)+ "   " + rs.getString(14)
				+ "   " + rs.getString(15)+ "   " + rs.getString(16)+ "   " + rs.getString(17)
				+ "   " + rs.getString(18)+ "   " + rs.getString(19)+ "   " + rs.getString(20)
				+ "   " + rs.getString(21));
			}
			
			
			// ************Read data from movie_genres.dat
			String file2 = "C:/Users/admin/Desktop/COEN280HW3/hetrec2011-movielens-2k-v2/"+genreFile;
			br = new BufferedReader(new FileReader(file2));
			String line2 = null;
			line2 = br.readLine();
			
			// ************Insert data in MOVIE_GENRES table
			while((line2 = br.readLine()) !=null) {
				System.out.println(line2);
				String temp2[] = line2.split("\t");
				for(int i=0;i<temp2.length;i++) {
					System.out.println("temp[" + i + "]" + temp2[i]);
				}
				
				String sql2 = "INSERT INTO MOVIE_GENRES (movieID, genre) "
						+ "VALUES(?,?)";
				
				ps = conn.prepareStatement(sql2);
				ps.setInt(1, Integer.parseInt(temp2[0]));
				ps.setString(2, temp2[1]);
				
				ps.executeUpdate();
				ps.close();	
			}
			br.close();
			
			// ********** Display data in MOVIE_GENRES table
			System.out.println("Data inserted in MOVIE_GENRES");
			String query2 = "select * from MOVIE_GENRES";
			ResultSet rs2 = statement.executeQuery(query2);
			while(rs2.next()) {
				System.out.println(rs2.getInt(1) + "   " + rs2.getString(2));
			}
			
			
			
							
			// ************Read data from movie_countries.dat
			String file5 = "C:/Users/admin/Desktop/COEN280HW3/hetrec2011-movielens-2k-v2/"+CountryFile;
			br = new BufferedReader(new FileReader(file5));
			String line5 = null;
			line5 = br.readLine();
			
			// ************Insert data in MOVIE_COUNTRIES table
			while((line5 = br.readLine()) !=null) {
				System.out.println(line5);
				String temp5[] = line5.split("\t");
				for(int i=0;i<temp5.length;i++) {
					System.out.println("temp[" + i + "]" + temp5[i]);
				}
				
				String sql5 = "INSERT INTO MOVIE_COUNTRIES (movieID, country) "
						+ "VALUES(?,?)";
				
				ps = conn.prepareStatement(sql5);
				ps.setInt(1, Integer.parseInt(temp5[0]));
				String tempStr = "";
				try {
					tempStr = temp5[1];
				    } catch (ArrayIndexOutOfBoundsException | NullPointerException nfe) {
				    	tempStr = "";
				    }
				ps.setString(2, tempStr);
				
				ps.executeUpdate();
				ps.close();	
			}
			br.close();
			
			// ********** Display data in MOVIE_COUNTRIES table
			System.out.println("Data inserted in MOVIE_COUNTRIES");
			String query5 = "select * from MOVIE_COUNTRIES";
			ResultSet rs5 = statement.executeQuery(query5);
			while(rs5.next()) {
				System.out.println(rs5.getInt(1) + "   " + rs5.getString(2) );
			}
							
			
			// ************Read data from movie_locations.dat
			String file6 = "C:/Users/admin/Desktop/COEN280HW3/hetrec2011-movielens-2k-v2/"+LocationFile;
			br = new BufferedReader(new FileReader(file6));
			String line6 = null;
			line6 = br.readLine();
			
			// ************Insert data in MOVIE_LOCATIONS table
			while((line6 = br.readLine()) !=null) {
				System.out.println(line6);
				String temp6[] = line6.split("\t");
				for(int i=0;i<temp6.length;i++) {
					System.out.println("temp[" + i + "]" + temp6[i]);
				}
				
				String sql6 = "INSERT INTO MOVIE_LOCATIONS (movieID, location1, location2, location3, location4) "
						+ "VALUES(?,?,?,?,?)";
				
				ps = conn.prepareStatement(sql6);
				ps.setInt(1, Integer.parseInt(temp6[0]));
				
				String tempStr1[] = new String[4];
				for(int i=0; i<4 ; i++) {
					try {
						tempStr1[i] = temp6[i+1];
					    } catch (ArrayIndexOutOfBoundsException | NullPointerException nfe) {
					    	tempStr1[i] = "";
					    }
				}
				ps.setString(2, tempStr1[0]);
				ps.setString(3, tempStr1[1]);
				ps.setString(4, tempStr1[2]);
				ps.setString(5, tempStr1[3]);
				
				ps.executeUpdate();
				ps.close();	
			}
			br.close();
			
			// ********** Display data in MOVIE_LOCATIONS table
			System.out.println("Data inserted in MOVIE_LOCATIONS");
			String query6 = "select * from MOVIE_LOCATIONS";
			ResultSet rs6 = statement.executeQuery(query6);
			while(rs6.next()) {
				System.out.println(rs6.getInt(1) + "   " + rs6.getString(2) 
				 + "   " + rs6.getString(3) + "   " + rs6.getString(4)
				 + "   " + rs6.getString(5) );
			}
						
			// ************Read data from tags.dat
			String file7 = "C:/Users/admin/Desktop/COEN280HW3/hetrec2011-movielens-2k-v2/"+TagFile;
			br = new BufferedReader(new FileReader(file7));
			String line7 = null;
			line7 = br.readLine();
			
			// ************Insert data in TAGS table
			while((line7 = br.readLine()) !=null) {
				System.out.println(line7);
				String temp7[] = line7.split("\t");
				for(int i=0;i<temp7.length;i++) {
					System.out.println("temp[" + i + "]" + temp7[i]);
				}
				
				String sql7 = "INSERT INTO TAGS (id, value) "
						+ "VALUES(?,?)";
				
				ps = conn.prepareStatement(sql7);
				ps.setInt(1, Integer.parseInt(temp7[0]));
				String tempStr7 = "";
				try {
					tempStr7 = temp7[1];
				    } catch (ArrayIndexOutOfBoundsException | NullPointerException nfe) {
				    	tempStr7 = "";
				    }
				ps.setString(2, tempStr7);
				
				ps.executeUpdate();
				ps.close();	
			}
			br.close();
			
			// ********** Display data in TAGS table
			System.out.println("Data inserted in TAGS");
			String query7 = "select * from TAGS";
			ResultSet rs7 = statement.executeQuery(query7);
			while(rs7.next()) {
				System.out.println(rs7.getInt(1) + "   " + rs7.getString(2) );
			}
												
			
			// ************Read data from movie_tags.dat
			String file8 = "C:/Users/admin/Desktop/COEN280HW3/hetrec2011-movielens-2k-v2/"+MovieTagFile;
			br = new BufferedReader(new FileReader(file8));
			String line8 = null;
			line8 = br.readLine();
			
			// ************Insert data in MOVIE_TAGS table
			while((line8 = br.readLine()) !=null) {
				System.out.println(line8);
				String temp8[] = line8.split("\t");
				for(int i=0;i<temp8.length;i++) {
					System.out.println("temp[" + i + "]" + temp8[i]);
				}
				
				String sql8 = "INSERT INTO MOVIE_TAGS (movieID, tagID, tagWeight) "
						+ "VALUES(?,?,?)";
				
				ps = conn.prepareStatement(sql8);
				ps.setInt(1, Integer.parseInt(temp8[0]));
				ps.setInt(2, Integer.parseInt(temp8[1]));
				ps.setInt(3, Integer.parseInt(temp8[2]));
				/*Float d8 = 0f;
				try {
				    d8 = Float.parseFloat(temp8[2]);
				    } catch (NumberFormatException | NullPointerException nfe) {
				        d8 = 0f;
				    }
				ps.setFloat(3, d8);*/
				
				ps.executeUpdate();
				ps.close();	
			}
			br.close();
			
			// ********** Display data in MOVIE_TAGS table
			System.out.println("Data inserted in MOVIE_TAGS");
			String query8 = "select * from MOVIE_TAGS";
			ResultSet rs8 = statement.executeQuery(query8);
			while(rs8.next()) {
				System.out.println(rs8.getInt(1) + "   " + rs8.getInt(2) + "   " + rs8.getInt(3));
			}
												
													
			
			
			conn.close();
	}

}
