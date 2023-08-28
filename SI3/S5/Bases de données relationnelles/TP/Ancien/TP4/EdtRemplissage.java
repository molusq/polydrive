import java.sql.*;
import java.io.*;

public class EdtRemplissage{
 
 
 public  static void main(String args[]) {
	
			// Parametres de connexion
     // Parametres de connexion
     String url = "jdbc:postgresql://localhost:5432/rueher";
     String username = "invited";
     String password = "invited";


	Statement stmt;
	String sqlTxt;
	String errMsg;
			
			// Parametres du formulaire de selection
	String reqCursus ="SI4";
	int reqSemInt=49;
			// Chargement du Driver
	try {
		Class.forName("org.postgresql.Driver");
		} 
	catch(java.lang.ClassNotFoundException e) {
		System.err.print("ClassNotFoundException: "); 
		System.err.println(e.getMessage());
		};
			// Connexion
     
     try {
         Connection conn = DriverManager.getConnection(url, username, password);
         
         
         // Execution d'une commande
         stmt = conn.createStatement();
         stmt.executeUpdate("DELETE  FROM affichage");
		int[] nbLignes = new int [5];	
	   		for (int i=1; i<=5; i++)	{
				stmt.executeUpdate(
	   			 "insert into affichage (jour, heured, duree, ligne) " +
	   			 "  values(" + Integer.toString(i) + ", 1, 8, 1) " );
				nbLignes[i-1]=1;
										};	
			// Recherche dans la base de donnees de l'ensemble des cours de SI4, semaine 49
		sqlTxt =
	   			" SELECT E.code_module, E.groupe, E.jour, E.heured, CT.duree, E.salle " +
	   			" FROM edt_sem  E, cursus_groupe CCT, creneau_type CT " +
	   			" WHERE  CCT.nomcursus = ? and E.sem= ? and " +
	   			"	 E.groupe=CCT.groupe and " +
	   			"	CT.code_module= E.code_module and CT.groupe=E.groupe " +
	   			" ORDER BY 3,4,5 " ;
		errMsg="PB SQL dans insertion des creneaux a placer : \n" + sqlTxt;	 
		PreparedStatement stmtModules = conn.prepareStatement(sqlTxt);
	   			
		stmtModules.setString(1, reqCursus);
		stmtModules.setInt(2, reqSemInt);
		ResultSet rsetModules = stmtModules.executeQuery();	
		
	   		// Exploitation des resultats		
		while (rsetModules.next())	{
			String code_module = rsetModules.getString(1);
			String groupe =  rsetModules.getString(2);
			int jour = 		rsetModules.getInt(3);
			int heured = 	rsetModules.getInt(4);
			int duree =		rsetModules.getInt(5);		
			int heuref =	heured + duree-1;
			String salle =  rsetModules.getString(6);
	   					//parametres du creneau vide a trouver
			boolean	cv_trouve=false;
			int	cv_ligne=1;
			int cv_heured=1;	
			int cv_duree=1;
			int cv_heuref=1;
					// Calcul de la ligne de placement 
					//		recherche de creneau vide 
			errMsg="PB SQL dans selection des creneaux libres";			    
			PreparedStatement  stmtSelCrenVide = conn.prepareStatement(
				" SELECT A.ligne, A.heured, A.duree " +
				" FROM affichage A " +
				" WHERE  A.code_module IS NULL and A.jour = ? and " +
	   				" A.heured <= ? and ? <= A.heured+duree-1 " +
				" ORDER BY 1, 2");		
			stmtSelCrenVide.setInt(1, jour);
			stmtSelCrenVide.setInt(2, heured);
			stmtSelCrenVide.setInt(3, heuref);
			ResultSet  rsetSelCrenVide = stmtSelCrenVide.executeQuery();
			while (rsetSelCrenVide.next())		{
				cv_trouve=true;
				cv_ligne= rsetSelCrenVide.getInt(1);
				cv_heured= rsetSelCrenVide.getInt(2);
				cv_duree= rsetSelCrenVide.getInt(3);
				cv_heuref= cv_heured + cv_duree -1;
				break;	
												}
			rsetSelCrenVide.close();
			if (! cv_trouve )	{
					// insertion d'une nouvelle ligne dans le jour
					// par creation d'un nouveau creneau vide de 8h
					// recherche de la ligne maximale pour le jour
			nbLignes[jour-1] +=1;
			cv_ligne=nbLignes[jour-1];
			errMsg="PB SQL dans ajout nouvelle ligne";			
			stmt.executeUpdate(
	   		  		"insert into affichage (jour, heured, duree, ligne) " +
	   				"  values(" + Integer.toString(jour) + ", 1, 8, " + 
                                        Integer.toString(cv_ligne) +")" );
			cv_heured = 1; cv_duree=8; cv_heuref=8;
								} //Eof (! cv_trouve )
	   				// Le creneau vide existe donc maintenant
	   				// on supprime le creneau vide
			errMsg="PB SQL dans suppresion du creneau vide";			
			stmt.executeUpdate(
	   			  	"delete from affichage " +
	   				"where jour=" + Integer.toString(jour) + " and heured=" + 
                                        Integer.toString(cv_heured) + " and " +
	   				" 	  ligne=" + Integer.toString(cv_ligne) );
	   				// Insertion du creneau a la place du creneau vide supprimee.
			errMsg="PB SQL dans insertion du creneau place";			   
			stmt.executeUpdate(
	   		  	"insert into affichage (code_module, groupe,jour, heured, duree, ligne, salle) " +
	   			"  values('" + code_module + "', '" + groupe + "', " 
				+ Integer.toString(jour) + ", " + Integer.toString(heured) + 
	   			  ", " + Integer.toString(duree)  + ", " + Integer.toString(cv_ligne) 
                                +",'" + salle + "')"
                                ); 
			if  (cv_heured < heured )		{
	   				// insertion d'un creneau vide a gauche
			errMsg="PB SQL dans insertion du creneau vide gauche";			     
			stmt.executeUpdate(
				"insert into affichage (jour, heured, duree, ligne) " +
				"  values(" + Integer.toString(jour) + ", " + Integer.toString(cv_heured) + ", " 
				+ Integer.toString(heured - cv_heured)  + ", " + Integer.toString(cv_ligne) +")"
                                        );	
											}
			if  (heuref < cv_heuref ) {
	   				// insertion d'un creneau vide a droite
			errMsg="PB SQL dans insertion du creneau vide droite";			   
			stmt.executeUpdate(
	   		  		"insert into affichage (jour, heured, duree, ligne) " +
	   				"  values(" + Integer.toString(jour) + ", " + Integer.toString(heuref+1) + 
					", "  + Integer.toString(cv_heuref-heuref)  + ", "  
					+ Integer.toString(cv_ligne) +")"
								);
	   		} 
	   		}	
	   		rsetModules.close();
	
		stmt.close();
		conn.close();
	
		} 
		catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
							
	}
};

 
	 

	 

 


 
	
	

 
