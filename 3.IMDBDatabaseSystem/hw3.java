package HW3new;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.ScrollPane;

public class hw3 {

	private JFrame frame;
	
	private static JLabel comp;
	private JTextField textField_1;
	private JTextField textField_2;
	String ratingValue;
	String reviewsValue;
	String selectedRating;
	String selectedReviews;
	String movieYrFrom;
	String movieYrTo;
	String tagWeight;
	String tagValue;
	String queryMovies;
	String valueANDOR;
	String mvId;
	String mvTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw3 window = new hw3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public hw3() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		//Db connection
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String host = "localhost";
		String dbName = "shwetadb";
		int port = 1521;
		String oracleURL = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName;
		String userName = "shweta";
		String pwd = "Carpet";
		Connection connection = DriverManager.getConnection(oracleURL, userName, pwd);
		//Statement statement = connection.createStatement();
		String genre_query = "select distinct genre from MOVIE_GENRES";
		//ResultSet rs = statement.executeQuery(query);
		PreparedStatement ps = connection.prepareStatement(genre_query);
		ResultSet rs = ps.executeQuery();
		//ps.close();
		//*************
		
		List<String> selected_genre = new ArrayList<String>();
		List<String> selected_country = new ArrayList<String>();
		List<String> selected_location = new ArrayList<String>();

		
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 204, 204));
		frame.setBounds(0, 0, 1200, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel.setBounds(55, 72, 180, 191);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_1.setBounds(238, 72, 180, 191);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_2.setBounds(421, 72, 169, 191);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_3.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_3.setBounds(55, 266, 535, 88);
		frame.getContentPane().add(panel_3);
		
		JPanel panel_6 = new JPanel(new GridLayout(0,1));
		panel_6.setBackground(new Color(0, 0, 0));
		panel_6.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_6.setBounds(804, 72, 216, 230);
		frame.getContentPane().add(panel_6);
		
		JPanel panel_9 = new JPanel(new GridLayout(0,1));
		panel_9.setBackground(new Color(0, 0, 0));
		panel_9.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_9.setBounds(594, 424, 426, 174);
		frame.getContentPane().add(panel_9);
		
		JPanel panel_11 = new JPanel((LayoutManager) null);
		panel_11.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_11.setBackground(Color.BLACK);
		panel_11.setBounds(778, 423, 242, 175);
		frame.getContentPane().add(panel_11);
		panel_11.setLayout(new GridLayout(0, 1));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 0, 0));
		panel_4.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_4.setBounds(55, 356, 535, 242);
		frame.getContentPane().add(panel_4);
		
		JTextArea txtrAbc = new JTextArea();
		txtrAbc.setRows(5);
		txtrAbc.setBackground(new Color(0, 153, 204));
		txtrAbc.setForeground(new Color(255, 204, 0));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(0, 153, 204));
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"OR", "AND"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setMaximumRowCount(15);
		
		JLabel lblNewLabel_1 = new JLabel("SEARCH BETWEEN ATTRIBUTES' VALUES\r\n");
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setForeground(new Color(255, 204, 0));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(46))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				valueANDOR = comboBox.getSelectedItem().toString(); 
				System.out.println("Selected valueANDOR = "+valueANDOR);
			}
		});
	
		
		//Genre display code
		List<JCheckBox> cbList = new ArrayList<JCheckBox>();
		int count1 = 0;
		String str1[] = new String[100];
		//List<String> selected_genre = new ArrayList<String>();
		while(rs.next()) {
			str1[count1] = rs.getString(1);
			System.out.println(str1[count1]);
			JCheckBox cb = new JCheckBox(str1[count1]);
			cb.setBackground(new Color(0, 0, 0));
			cb.setForeground(new Color(255, 204, 0));
			cbList.add(cb);
			panel.add(cb);
			cb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if(cb.isSelected() == true) {
						System.out.println("selected Genre:"+cb.getText());
						selected_genre.add(cb.getText());
						
					}else {
						System.out.println("Unselected Genre:"+cb.getText());
						selected_genre.remove(cb.getText());
					}
					ListIterator<String> selected_genre_itr = selected_genre.listIterator();
					String genre_values = "";
					while(selected_genre_itr.hasNext()) {
						String s = selected_genre_itr.next();
						System.out.println("********"+s);
						genre_values = genre_values+"'"+s+"',";
					}
					if (genre_values != null && genre_values.length() > 0) {
						genre_values = genre_values.substring(0, genre_values.length() - 1);
				    }
					System.out.println("selected genre for Query :"+genre_values);
					String country_query = "select distinct country from MOVIE_COUNTRIES mc, MOVIE_GENRES mg "
								+ "where mc.movieID = mg.movieID and mg.genre IN ("+genre_values+")";
					System.out.println(country_query);
					//**************
					// Country display code
					try {
						panel_1.removeAll();
						panel_1.revalidate();
						panel_1.repaint();
						panel_2.removeAll();
						panel_2.revalidate();
						panel_2.repaint();
						panel_6.removeAll();
						panel_6.revalidate();
						panel_6.repaint();
						panel_9.removeAll();
						panel_9.revalidate();
						panel_9.repaint();
						panel_11.removeAll();
						panel_11.revalidate();
				        panel_11.repaint();
				        txtrAbc.removeAll();
				        txtrAbc.repaint();
						txtrAbc.revalidate();
						panel_4.revalidate();
						panel_4.repaint();
						PreparedStatement ps_country = connection.prepareStatement(country_query);
						ResultSet rs_country = ps_country.executeQuery();
						List<JCheckBox> countryList = new ArrayList<JCheckBox>();
						int countCountries = 0;
						String strCountry[] = new String[20000];
						
						while(rs_country.next()) {
							strCountry[countCountries] = rs_country.getString(1);
							System.out.println(strCountry[countCountries]);
							JCheckBox cbCountry = new JCheckBox(strCountry[countCountries]);
							cbCountry.setBackground(new Color(0, 0, 0));
							cbCountry.setForeground(new Color(255, 204, 0));
							countryList.add(cbCountry);
							panel_1.add(cbCountry);
							panel_1.revalidate();
							panel_1.repaint();
							//
							cbCountry.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									
									if(cbCountry.isSelected() == true) {
										System.out.println("Selected Country: "+cbCountry.getText());
										selected_country.add(cbCountry.getText());
										
									}else {
										System.out.println("Unselected Country: "+cbCountry.getText());
										selected_country.remove(cbCountry.getText());
									}
									ListIterator<String> selected_country_itr = selected_country.listIterator();
									String country_values = "";
									while(selected_country_itr.hasNext()) {
										String s1 = selected_country_itr.next();
										System.out.println("********"+s1);
										country_values = country_values+"'"+s1+"',";
									}
									if (country_values != null && country_values.length() > 0) {
										country_values = country_values.substring(0, country_values.length() - 1);
								    }
									System.out.println("Selected countries for Query: "+country_values);
									String location_query = "select distinct location1 from MOVIE_COUNTRIES mc, MOVIE_LOCATIONS ml "
											+ "where mc.movieID = ml.movieID and mc.country IN ("+country_values+")";
									System.out.println(location_query);
									//**************
									//Location display code
									try {
										panel_2.removeAll();
										panel_2.revalidate();
										panel_2.repaint();
										panel_6.removeAll();
										panel_6.revalidate();
										panel_6.repaint();
										panel_9.removeAll();
										panel_9.revalidate();
										panel_9.repaint();
										panel_11.removeAll();
										panel_11.revalidate();
								        panel_11.repaint();
								        txtrAbc.removeAll();
								        txtrAbc.repaint();
										txtrAbc.revalidate();
										panel_4.revalidate();
										panel_4.repaint();
										PreparedStatement ps_location = connection.prepareStatement(location_query);
										ResultSet rs_location = ps_location.executeQuery();
										List<JCheckBox> locationList = new ArrayList<JCheckBox>();
										int countLocations = 0;
										String strLocation[] = new String[50000];
										
										while(rs_location.next()) {
											strLocation[countLocations] = rs_location.getString(1);
											System.out.println(strLocation[countLocations]);
											JCheckBox cbLocation = new JCheckBox(strLocation[countLocations]);
											cbLocation.setBackground(new Color(0, 0, 0));
											cbLocation.setForeground(new Color(255, 204, 0));
											locationList.add(cbLocation);
											panel_2.add(cbLocation);
											panel_2.revalidate();
											panel_2.repaint();
											
											cbLocation.addActionListener(new ActionListener() {
												
												@Override
												public void actionPerformed(ActionEvent e) {
													// TODO Auto-generated method stub
													panel_6.removeAll();
													panel_6.revalidate();
													panel_6.repaint();
													panel_9.removeAll();
													panel_9.revalidate();
													panel_9.repaint();
													panel_11.removeAll();
													panel_11.revalidate();
											        panel_11.repaint();
											        txtrAbc.removeAll();
											        txtrAbc.repaint();
													txtrAbc.revalidate();
													panel_4.revalidate();
													panel_4.repaint();
													if(cbLocation.isSelected() == true) {
														System.out.println("Selected Location:"+cbLocation.getText());
														selected_location.add(cbLocation.getText());
														
													}else {
														System.out.println("Unselected Location:"+cbLocation.getText());
														selected_location.remove(cbLocation.getText());
													}
													ListIterator<String> selected_location_itr = selected_location.listIterator();
													String location_values = "";
													while(selected_location_itr.hasNext()) {
														String s1 = selected_location_itr.next();
														System.out.println("********"+s1);
														location_values = location_values+"'"+s1+"',";
													}
													if (location_values != null && location_values.length() > 0) {
														location_values = location_values.substring(0, location_values.length() - 1);
												    }
													System.out.println("Selected Locations for query :"+location_values);
												}
											});
											
											//
											} 
										}catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									
									//**************
								}
							});
							//
							countCountries++;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//**************
					
				}
			});
			count1++; 
		}
		//***********
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBackground(new Color(0, 0, 0));
		scrollPane.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		scrollPane.setBounds(55, 72, 180, 191);
		frame.getContentPane().add(scrollPane);
		// End of Genre code.
		 
		/*JEditorPane dtrpnGenres = new JEditorPane();
		dtrpnGenres.setBackground(new Color(0, 0, 0));
		dtrpnGenres.setForeground(new Color(255, 204, 0));
		dtrpnGenres.setText("GENRES");
		panel.add(dtrpnGenres);*/
		
		//********************
		
		JScrollPane scrollPaneCountry = new JScrollPane(panel_1);
		scrollPaneCountry.setBackground(new Color(0, 0, 0));
		scrollPaneCountry.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		scrollPaneCountry.setBounds(241, 72, 174, 191);
		frame.getContentPane().add(scrollPaneCountry);
		//JCheckBox a = new JCheckBox("abc");
		//panel_1.add(a);

		
		//JScrollPane scrollPane_1 = new JScrollPane(panel_1);
		//panel_1.add(scrollPane_1);
		//JScrollPane scrollPane1 = new JScrollPane(panel_1);
		//frame.getContentPane().add(scrollPane1);
		
		/*JEditorPane dtrpnCountries = new JEditorPane();
		dtrpnCountries.setBackground(new Color(0, 0, 0));
		dtrpnCountries.setText("COUNTRIES");
		dtrpnCountries.setForeground(new Color(255, 204, 0));
		panel_1.add(dtrpnCountries);*/
		
		JScrollPane scrollPaneLocation = new JScrollPane(panel_2);
		scrollPaneLocation.setBackground(new Color(0, 0, 0));
		scrollPaneLocation.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		scrollPaneLocation.setBounds(418, 72, 172, 191);
		frame.getContentPane().add(scrollPaneLocation);
		//frame.getContentPane().add(panel_2);
		
		/*JEditorPane dtrpnLocations = new JEditorPane();
		dtrpnLocations.setBackground(new Color(0, 0, 0));
		dtrpnLocations.setForeground(new Color(255, 204, 0));
		dtrpnLocations.setText("LOCATIONS");
		panel_2.add(dtrpnLocations);*/
		

		
		
		
		JEditorPane dtrpnQuery = new JEditorPane();
		dtrpnQuery.setText("QUERY");
		dtrpnQuery.setBackground(new Color(0, 0, 0));
		dtrpnQuery.setForeground(new Color(255, 204, 0));
		
		
		
		JButton btnExecuteQuery = new JButton("Execute Query");
		btnExecuteQuery.setForeground(new Color(204, 0, 102));
		btnExecuteQuery.setBackground(new Color(204, 204, 204));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap(195, Short.MAX_VALUE)
					.addComponent(dtrpnQuery, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(214))
				.addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup()
					.addGap(152)
					.addComponent(btnExecuteQuery)
					.addContainerGap(228, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup()
					.addGap(38)
					.addComponent(txtrAbc, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(42, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(dtrpnQuery, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(txtrAbc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnExecuteQuery)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 0, 0));
		panel_5.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_5.setBounds(594, 72, 207, 230);
		frame.getContentPane().add(panel_5);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"=", "<", "<=", ">", ">="}));
		comboBox_4.setBackground(new Color(0, 153, 204));
/*		comboBox_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectedRating = comboBox_4.getSelectedItem().toString();
				System.out.println(selectedRating);
			}
		});*/
		
		JLabel lblNewLabel_4 = new JLabel("RATING");
		lblNewLabel_4.setBackground(new Color(0, 0, 0));
		lblNewLabel_4.setForeground(new Color(255, 204, 0));
		
		JLabel lblNewLabel_5 = new JLabel("VALUE");
		lblNewLabel_5.setBackground(new Color(0, 0, 0));
		lblNewLabel_5.setForeground(new Color(255, 204, 0));
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLACK);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(0, 153, 204));
		
		
		JLabel lblNewLabel_6 = new JLabel("NUMBER OF REVIEWS");
		lblNewLabel_6.setForeground(new Color(255, 204, 0));
		lblNewLabel_6.setBackground(new Color(0, 0, 0));
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"=", "<", "<=", ">", ">="}));
		comboBox_5.setBackground(new Color(0, 153, 204));
//		comboBox_5.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//				
//			}
//		});
		
		JLabel label = new JLabel("VALUE");
		label.setForeground(new Color(255, 204, 0));
		label.setBackground(Color.BLACK);
		
		textField_2 = new JTextField();
		textField_2.setForeground(Color.BLACK);
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(0, 153, 204));
		
		
		//panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		/*JEditorPane dtrpnMovieTags = new JEditorPane();
		dtrpnMovieTags.setForeground(new Color(255, 204, 0));
		dtrpnMovieTags.setBackground(new Color(0, 0, 0));
		dtrpnMovieTags.setText("MOVIE TAG VALUES");
		panel_6.add(dtrpnMovieTags);*/
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_6.removeAll();
				panel_6.revalidate();
				panel_6.repaint();
				panel_9.removeAll();
				panel_9.revalidate();
				panel_9.repaint();
				panel_11.removeAll();
				panel_11.revalidate();
		        panel_11.repaint();
		        txtrAbc.removeAll();
		        txtrAbc.repaint();
				txtrAbc.revalidate();
				panel_4.revalidate();
				panel_4.repaint();
				ratingValue = textField_1.getText();
				reviewsValue = textField_2.getText();
				selectedRating = comboBox_4.getSelectedItem().toString();
				selectedReviews = comboBox_5.getSelectedItem().toString();
				
				// Iterate over selected genre array to get those values and form string on submit btn click
				ListIterator<String> selected_genre_itr = selected_genre.listIterator();
				String genre_values = "";
				while(selected_genre_itr.hasNext()) {
					String s = selected_genre_itr.next();
					//System.out.println("********"+s);
					genre_values = genre_values+"'"+s+"',";
				}
				if (genre_values != null && genre_values.length() > 0) {
					genre_values = genre_values.substring(0, genre_values.length() - 1);
			    }
				System.out.println("SUBMIT BTN: Selected Genre's for Query: "+genre_values);
				
				// Iterate over selected country array to get those values and form string on submit btn click
				ListIterator<String> selected_country_itr = selected_country.listIterator();
				String country_values = "";
				while(selected_country_itr.hasNext()) {
					String s1 = selected_country_itr.next();
					//System.out.println("********"+s1);
					country_values = country_values+"'"+s1+"',";
				}
				if (country_values != null && country_values.length() > 0) {
					country_values = country_values.substring(0, country_values.length() - 1);
			    }
				System.out.println("SUBMIT BTN: Selected Countries for Query: "+country_values);
				
				// Iterate over selected location array to get those values and form string on submit btn click
				ListIterator<String> selected_location_itr = selected_location.listIterator();
				String location_values = "";
				while(selected_location_itr.hasNext()) {
					String s1 = selected_location_itr.next();
					//System.out.println("********"+s1);
					location_values = location_values+"'"+s1+"',";
				}
				if (location_values != null && location_values.length() > 0) {
					location_values = location_values.substring(0, location_values.length() - 1);
			    }
				System.out.println("SUBMIT BTN: Selected Locations for Query :"+location_values);
				
				System.out.println("SUBMIT BTN: "+"SelectedRating: "+selectedRating + " ratingValue:"+ratingValue);
				System.out.println("SUBMIT BTN: "+"SelectedReviews: "+selectedReviews +" reviewsValue:" + reviewsValue);
				//**********
				/*String tags_query = "select distinct t.value from TAGS t, MOVIE_TAGS mt, MOVIE_GENRES mg, MOVIE_COUNTRIES mc, "
						+ "MOVIE_LOCATIONS ml, MOVIES m, m.rtAllCriticsRating + m.rtTopCriticsRating + m.rtAudienceRating/3 AS AVGRATING,"
						+ " m.rtAllCriticsNumReviews + m.rtTopCriticsNumReviews + m.rtAudienceNumRatings/3 AS NOREVIEWS"
						+ " where t.id = mt.tagID AND mt.movieID = mg.movieID AND "
						+ " mt.movieID = mc.movieID AND mt.movieID = ml.movieID AND mt.movieID = m.id AND "
						+ " AVGRATING" + selectedRating+ " " + ratingValue
						+ " AND NOREVIEWS " +selectedReviews + " " + reviewsValue + " AND mg.genre IN (" + genre_values + ")"
						+ " AND mc.country IN (" + country_values + ")" + " AND ml.location1 IN (" + location_values + ")" ;*/
				String tags_query = "select distinct t.value from TAGS t, MOVIE_TAGS mt, MOVIE_GENRES mg, MOVIE_COUNTRIES mc, " 
						+"MOVIE_LOCATIONS ml, MOVIES m "
						+"where t.id = mt.tagID AND mt.movieID = mg.movieID AND  mt.movieID = mc.movieID " 
						+"AND mt.movieID = ml.movieID AND mt.movieID = m.id AND "
						+"(m.rtAllCriticsRating + m.rtTopCriticsRating + m.rtAudienceRating)/3 "+ selectedRating + " "+ratingValue + " AND "
						+"(m.rtAllCriticsNumReviews + m.rtTopCriticsNumReviews + m.rtAudienceNumRatings)/3 " + selectedReviews + " " +reviewsValue + " "
						+"AND mg.genre IN ('Comedy') AND mc.country IN ('USA') AND ml.location1 IN ('USA')";
				System.out.println(tags_query);
				try {
					
					PreparedStatement ps_tags = connection.prepareStatement(tags_query);
					ResultSet rs_tags = ps_tags.executeQuery();
					List<JLabel> tagsList = new ArrayList<JLabel>();
					int countTags = 0;
					String strTags[] = new String[14000];
					
					while(rs_tags.next()) {
						strTags[countTags] = rs_tags.getString(1);
						System.out.println(strTags[countTags]);
						JLabel labelTags = new JLabel(strTags[countTags]);
						labelTags.setBackground(new Color(0, 0, 0));
						labelTags.setForeground(new Color(255, 204, 0));
						tagsList.add(labelTags);
						panel_6.add(labelTags);
						panel_6.revalidate();
						panel_6.repaint();
						countTags++;
					}
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//***********
			}
		});
		
		JScrollPane scrollPaneTags = new JScrollPane(panel_6);
		scrollPaneTags.setBackground(new Color(0, 0, 0));
		scrollPaneTags.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		scrollPaneTags.setBounds(804, 72, 216, 230);
		frame.getContentPane().add(scrollPaneTags);
		
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_5))
							.addGap(18)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNewLabel_6)
							.addGroup(gl_panel_5.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
					.addContainerGap(98, Short.MAX_VALUE)
					.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSubmit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(22))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_6)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(46)
							.addComponent(label)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(12))
		);
		panel_5.setLayout(gl_panel_5);
		
		
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(0, 0, 0));
		panel_7.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_7.setBounds(594, 306, 180, 114);
		frame.getContentPane().add(panel_7);
		
		JLabel lblNewLabel_2 = new JLabel("FROM");
		lblNewLabel_2.setBackground(new Color(0, 153, 204));
		lblNewLabel_2.setForeground(new Color(255, 204, 0));
		
		JEditorPane dtrpnMovieYear = new JEditorPane();
		dtrpnMovieYear.setForeground(new Color(255, 204, 0));
		dtrpnMovieYear.setBackground(new Color(0, 0, 0));
		dtrpnMovieYear.setText("MOVIE YEAR");
		
		JLabel lblNewLabel_3 = new JLabel("TO");
		lblNewLabel_3.setBackground(new Color(0, 0, 0));
		lblNewLabel_3.setForeground(new Color(255, 204, 0));
		
		//Get year from MOVIES table
		String year_query = "select distinct year from MOVIES order by year asc";
		//ResultSet rs = statement.executeQuery(query);
		PreparedStatement psYear = connection.prepareStatement(year_query);
		ResultSet rsYear = psYear.executeQuery();
		String []yearList = new String[98];
		int yearCount = 0;
		while(rsYear.next()) {
			yearList[yearCount] = rsYear.getString(1);
			yearCount++;
		}
		//*********************
		
		JComboBox comboBox_2 = new JComboBox(yearList);
		//comboBox_2.setModel(new DefaultComboBoxModel(yearList));
		comboBox_2.setBackground(new Color(0, 153, 204));
		
		JComboBox comboBox_3 = new JComboBox(yearList);
		comboBox_3.setBackground(new Color(0, 153, 204));
		
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addComponent(dtrpnMovieYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_2))
					.addGap(26)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_3, 0, 77, Short.MAX_VALUE)
						.addComponent(comboBox_2, 0, 77, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addComponent(dtrpnMovieYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(0, 0, 0));
		panel_8.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_8.setBounds(778, 306, 242, 114);
		frame.getContentPane().add(panel_8);
		
		JLabel lblTagWeight = new JLabel("TAG WEIGHT");
		lblTagWeight.setForeground(new Color(255, 204, 0));
		lblTagWeight.setBackground(new Color(0, 0, 0));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(new Color(0, 153, 204));
		comboBox_1.setForeground(new Color(0, 0, 0));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"=", "<", ">"}));
		
		JLabel lblNewLabel = new JLabel("VALUE");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(255, 204, 0));
		
		JTextField textField = new JTextField();
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(new Color(0, 153, 204));
		textField.setColumns(10);
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(31))
						.addGroup(gl_panel_8.createSequentialGroup()
							.addComponent(lblTagWeight)
							.addGap(18)))
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField, 0, 0, Short.MAX_VALUE)
						.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(48))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTagWeight))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		panel_8.setLayout(gl_panel_8);
		
		
		
		/*JEditorPane dtrpnFinalResult = new JEditorPane();
		dtrpnFinalResult.setForeground(new Color(255, 204, 0));
		dtrpnFinalResult.setBackground(new Color(0, 0, 0));
		dtrpnFinalResult.setText("FINAL \r\nRESULT");
		panel_9.add(dtrpnFinalResult);*/
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(0, 0, 0));
		panel_10.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		panel_10.setBounds(55, 0, 965, 68);
		frame.getContentPane().add(panel_10);
		
		JEditorPane dtrpnMovie = new JEditorPane();
		dtrpnMovie.setFont(new Font("Tahoma", Font.BOLD, 28));
		dtrpnMovie.setForeground(new Color(255, 51, 0));
		dtrpnMovie.setBackground(new Color(0, 0, 0));
		dtrpnMovie.setText("MOVIE");
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(419)
					.addComponent(dtrpnMovie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(5)
					.addComponent(dtrpnMovie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_10.setLayout(gl_panel_10);
		
		JScrollPane scrollPane_1 = new JScrollPane(panel_9);
		scrollPane_1.setBackground(new Color(0, 0, 0));
		scrollPane_1.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		scrollPane_1.setBounds(594, 423, 180, 175);
		frame.getContentPane().add(scrollPane_1);
		
		
		
		JScrollPane scrollPane_2 = new JScrollPane(panel_11);
		scrollPane_2.setBackground(new Color(0, 0, 0));
		scrollPane_2.setBorder(new LineBorder(new Color(204, 0, 102), 10));
		scrollPane_2.setBounds(788, 423, 232, 175);
		frame.getContentPane().add(scrollPane_2);
		
		
		
		
		btnExecuteQuery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				// Iterate over selected genre array to get those values and form string on submit btn click
				ListIterator<String> selected_genre_itr = selected_genre.listIterator();
				String genre_values = "";
				while(selected_genre_itr.hasNext()) {
					String s = selected_genre_itr.next();
					//System.out.println("********"+s);
					genre_values = genre_values+"'"+s+"',";
				}
				if (genre_values != null && genre_values.length() > 0) {
					genre_values = genre_values.substring(0, genre_values.length() - 1);
			    }
				System.out.println("SUBMIT BTN: Selected Genre's for Query: "+genre_values);
				
				// Iterate over selected country array to get those values and form string on submit btn click
				ListIterator<String> selected_country_itr = selected_country.listIterator();
				String country_values = "";
				while(selected_country_itr.hasNext()) {
					String s1 = selected_country_itr.next();
					//System.out.println("********"+s1);
					country_values = country_values+"'"+s1+"',";
				}
				if (country_values != null && country_values.length() > 0) {
					country_values = country_values.substring(0, country_values.length() - 1);
			    }
				System.out.println("SUBMIT BTN: Selected Countries for Query: "+country_values);
				
				// Iterate over selected location array to get those values and form string on submit btn click
				ListIterator<String> selected_location_itr = selected_location.listIterator();
				String location_values = "";
				while(selected_location_itr.hasNext()) {
					String s1 = selected_location_itr.next();
					//System.out.println("********"+s1);
					location_values = location_values+"'"+s1+"',";
				}
				if (location_values != null && location_values.length() > 0) {
					location_values = location_values.substring(0, location_values.length() - 1);
			    }
				System.out.println("SUBMIT BTN: Selected Locations for Query :"+location_values);
				
				System.out.println("SUBMIT BTN: "+"SelectedRating: "+selectedRating + " ratingValue:"+ratingValue);
				System.out.println("SUBMIT BTN: "+"SelectedReviews: "+selectedReviews +" reviewsValue:" + reviewsValue);
				
				movieYrFrom = comboBox_2.getSelectedItem().toString();
				movieYrTo = comboBox_3.getSelectedItem().toString();
				tagWeight = comboBox_1.getSelectedItem().toString();
				tagValue = textField.getText();
				System.out.println("movieYrFrom = " + movieYrFrom);
				System.out.println("movieYrTo = " + movieYrTo);
				System.out.println("tagWeight = " + tagWeight);
				System.out.println("tagValue = " + tagValue);
				
				queryMovies = "select distinct m.title, m.id from MOVIES m, TAGS t, MOVIE_TAGS mt, MOVIE_GENRES mg, MOVIE_COUNTRIES mc, "
						+ "MOVIE_LOCATIONS ml where t.id = mt.tagID AND mt.movieID = mg.movieID "
						+ "AND mt.movieID = mc.movieID AND mt.movieID = ml.movieID AND mt.movieID = m.id AND "
						+ " (m.rtAllCriticsRating + m.rtTopCriticsRating + m.rtAudienceRating)/3" + selectedRating+ " " + ratingValue
						+ " AND (m.rtAllCriticsNumReviews + m.rtTopCriticsNumReviews + m.rtAudienceNumRatings)/3 " +selectedReviews + " " + reviewsValue + " AND mg.genre IN (" + genre_values + ")"
						+ " AND mc.country IN (" + country_values + ")" + " AND ml.location1 IN (" + location_values + ")" 
						+ " AND m.year between " + movieYrFrom + " AND " + movieYrTo + " AND mt.tagWeight " + tagWeight + tagValue;
				
				int cntCountryVal = 0;
				String [] countries_num = new String[100];
				while(selected_country_itr.hasNext()){
					countries_num[cntCountryVal] = selected_country_itr.next();
					cntCountryVal++;
				}

				String Con = "select distinct m.id from MOVIES m, MOVIE_COUNTRIES mc where m.id = mc.movieID AND mc.country =";
				String qCon = "select distinct m.id from MOVIES m, MOVIE_COUNTRIES mc where m.id = mc.movieID AND mc.country =";

				for(int i=0;i<cntCountryVal;i++){
					qCon = qCon + " '" +countries_num[i] + "' INTERSECT" ;
				}

				if (qCon != null && qCon.length() > 0) {
									qCon = qCon.substring(0, qCon.length() - 1);
							    }
				
				System.out.println(qCon);
				
				///
				int cntGenreVal = 0;
				String [] genre_num = new String[100];
				while(selected_genre_itr.hasNext()){
					genre_num[cntGenreVal] = selected_genre_itr.next();
					cntGenreVal++;
				}

				String Gen = "select distinct m.id from MOVIES m, MOVIE_GENRE mg where m.id = mg.movieID AND mg.genre =";
				String qGen = "select distinct m.id from MOVIES m, MOVIE_GENRE mg where m.id = mg.movieID AND mg.genre =";

				for(int i=0;i<cntGenreVal;i++){
					qGen = qGen + " '" +genre_num[i] + "' INTERSECT" ;
				}

				if (qGen != null && qGen.length() > 0) {
					qGen = qGen.substring(0, qGen.length() - 1);
							    }
				
				System.out.println(qGen);
				///
				
				///
				int cntLocVal = 0;
				String [] loc_num = new String[100];
				while(selected_location_itr.hasNext()){
					loc_num[cntLocVal] = selected_location_itr.next();
					cntLocVal++;
				}

				String Loc = "select distinct m.id from MOVIES m, MOVIE_LOCATIONS ml where m.id = ml.movieID AND ml.location1 =";
				String qLoc = "select distinct m.id from MOVIES m, MOVIE_LOCATIONS ml where m.id = ml.movieID AND ml.location1 =";

				for(int i=0;i<cntLocVal;i++){
					qLoc = qLoc + " '" +loc_num[i] + "' INTERSECT" ;
				}

				if (qLoc != null && qLoc.length() > 0) {
					qLoc = qLoc.substring(0, qLoc.length() - 1);
							    }
				
				System.out.println(qLoc);
				///
				
				System.out.println(queryMovies);
				txtrAbc.setText(queryMovies);
				txtrAbc.setLineWrap(true);
				
				//JLabel labelQuery = new JLabel(queryMovies);
				//panel_4.add(labelQuery);
				panel_4.revalidate();
				panel_4.repaint();
				
				try {
					panel_9.removeAll();
					panel_9.revalidate();
					panel_9.repaint();
					panel_11.removeAll();
					panel_11.revalidate();
			        panel_11.repaint();
			        
					PreparedStatement ps_movies = connection.prepareStatement(queryMovies);
					ResultSet rs_movies = ps_movies.executeQuery();
					List<JButton> movieList = new ArrayList<JButton>();
					int countMovies = 0;
					String strMovies[] = new String[10200];
					String strMoviesId[] = new String[10200];
					
					while(rs_movies.next()) {
						strMovies[countMovies] = rs_movies.getString(1);
						strMoviesId[countMovies] = rs_movies.getString(2);
						System.out.println(strMovies[countMovies]);
						JButton movieButton = new JButton(strMoviesId[countMovies]+"."+strMovies[countMovies]);
						movieButton.setBackground(new Color(0, 0, 0));
						movieButton.setForeground(new Color(255, 204, 0));
						movieButton.setHorizontalAlignment(SwingConstants.LEFT);
						movieList.add(movieButton);
						panel_9.add(movieButton);
						panel_9.revalidate();
						panel_9.repaint();
						countMovies++;
						movieButton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								
									System.out.println("In MOVIE BTN action listener: Selected Movie: " + movieButton.getText());
									try {
										panel_11.removeAll();
										panel_11.revalidate();
								        panel_11.repaint();
										String movieId_Title = movieButton.getText();
										
								        mvId = movieId_Title.split("\\.")[0]; 
								        mvTitle = movieId_Title.split("\\.")[1];
								        System.out.println(mvId + " **  " + mvTitle);
										
								        String movQ = "select m.title, m.year, "
								        		+ "m.rtAllCriticsRating + m.rtTopCriticsRating + m.rtAudienceRating/3 AS avgRating, "
								        		+ "m.rtAllCriticsNumReviews + m.rtTopCriticsNumReviews + m.rtAudienceNumRatings/3 AS avgReview "
								        		+ "from MOVIES m where id = "+mvId;
								        String genreQ = "select distinct genre from MOVIE_GENRES where movieId = "+mvId;
								        String countryQ = "select distinct country from MOVIE_COUNTRIES where movieId = "+mvId;
								        String locationQ = "select distinct location1 from MOVIE_LOCATIONS where movieId = "+mvId;
										
								        //String movieDetailQuery = "select * from MOVIES where id = "+mvId;
										
								        PreparedStatement ps_movQ = connection.prepareStatement(movQ);
										ResultSet rs_movQ = ps_movQ.executeQuery();
										rs_movQ.next();
										String mTitle = rs_movQ.getString(1);
										String myear = rs_movQ.getString(2);
										String avgRating = rs_movQ.getString(3);
										String avgReview = rs_movQ.getString(4);
										
										JLabel jId = new JLabel("Id : "+mvId);
								        JLabel jTitle = new JLabel("Title : "+mTitle);
								        JLabel jYear = new JLabel("Year : "+myear);
								        
								        jId.setBackground(new Color(0, 0, 0));
								        jId.setForeground(new Color(255, 204, 0));
								        
								        jTitle.setBackground(new Color(0, 0, 0));
								        jTitle.setForeground(new Color(255, 204, 0));
								        
								        jYear.setBackground(new Color(0, 0, 0));
								        jYear.setForeground(new Color(255, 204, 0));
								        
								        panel_11.add(jId);
								        panel_11.add(jTitle);
								        panel_11.add(jYear);
										
										PreparedStatement ps_genreQ = connection.prepareStatement(genreQ);
										ResultSet rs_genreQ = ps_genreQ.executeQuery();
										System.out.println("Genre Query "+ genreQ );
										String strGenreQ[] = new String[100];
										int cGenreQ=0;
										while(rs_genreQ.next()) {
											strGenreQ[cGenreQ] = rs_genreQ.getString(1);
											JLabel jGenre = new JLabel("Genre : "+strGenreQ[cGenreQ]);
											jGenre.setBackground(new Color(0, 0, 0));
											jGenre.setForeground(new Color(255, 204, 0));
											panel_11.add(jGenre);
											cGenreQ++;
										}
										
										PreparedStatement ps_countryQ = connection.prepareStatement(countryQ);
										ResultSet rs_countryQ = ps_countryQ.executeQuery();
										
										while(rs_countryQ.next()) {
											JLabel jcountryQ = new JLabel("Country : "+rs_countryQ.getString(1));
											jcountryQ.setBackground(new Color(0, 0, 0));
											jcountryQ.setForeground(new Color(255, 204, 0));
											panel_11.add(jcountryQ);
										}
										
										PreparedStatement ps_locationQ = connection.prepareStatement(locationQ);
										ResultSet rs_locationQ = ps_locationQ.executeQuery();
										
										while(rs_locationQ.next()) {
											JLabel jlocationQ = new JLabel("Location : "+rs_locationQ.getString(1));
											jlocationQ.setBackground(new Color(0, 0, 0));
											jlocationQ.setForeground(new Color(255, 204, 0));
											panel_11.add(jlocationQ);
										}
										
										JLabel jAvgRating = new JLabel("Avg. Rating : "+avgRating);
										jAvgRating.setBackground(new Color(0, 0, 0));
										jAvgRating.setForeground(new Color(255, 204, 0));
								        panel_11.add(jAvgRating);
								        
								        JLabel jAvgReview = new JLabel("Avg. Reviews : "+avgReview);
								        jAvgReview.setBackground(new Color(0, 0, 0));
								        jAvgReview.setForeground(new Color(255, 204, 0));
								        panel_11.add(jAvgReview);
								        
								        panel_11.revalidate();
								        panel_11.repaint();
								        
								        
										
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								
							
							}
						});
						
					}
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		
	}
}
