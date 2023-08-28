import java.sql.*;
import java.io.*;

public class test{
 
 
 public  static void main(String args[]) {
	


			// Chargement du Driver
	try {
		Class.forName("org.postgresql.Driver");
		} 
	catch(java.lang.ClassNotFoundException e) {
		System.err.print("ClassNotFoundException: "); 
		System.err.println(e.getMessage());
		};

	 
	 // Parametres de connexion
	 String url = "jdbc:postgresql://localhost:5432/tp4";
	 String username = "florian";
	 String password = "pns";
	 
			// Connexion   
	 
	try {
		Connection conn = DriverManager.getConnection(url, username, password);
	
		
		// Execution d'une commande
		System.out.println("Test passed");
		/*Statement stmt;
		stmt = conn.createStatement();                          
		stmt.executeUpdate("DELETE  FROM tp4"); */
			}   
	 catch(SQLException ex) {
		 System.err.println("SQLException: " + ex.getMessage());                        
	 }
 }
};