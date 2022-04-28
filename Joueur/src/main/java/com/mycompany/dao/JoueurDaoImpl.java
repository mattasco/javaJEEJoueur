package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.DaoException;
import com.mycompany.beans.Joueur;

public class JoueurDaoImpl implements JoueurDao {
	private DaoFactory dao;
	@Override
	public void ajouter(Joueur joueur) throws DaoException{
		Connection conn =null;
        try {
      
            conn=dao.getConnection();
            PreparedStatement preparedstatement= conn.prepareStatement("INSERT INTO joueur (nom,prenom,sexe) values(?,?,?)");
            preparedstatement.setString(1, joueur.getNom());
            preparedstatement.setString(2, joueur.getPrenom());
            preparedstatement.setString(3, joueur.getSexe());
            
            preparedstatement.executeUpdate();
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
	}

	@Override
	public List<Joueur> lister() {
		List<Joueur> joueurs=new ArrayList<Joueur>();
		Connection conn =null;
        try {
      
            conn=dao.getConnection();
            String query = "select * from joueur;";

            Statement st = conn.createStatement();
      
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
              int id = rs.getInt("id");
              String Nom = rs.getString("Nom");
              String Prenom = rs.getString("Prenom");
              String Sexe = rs.getString("Sexe");
              joueurs.add(new Joueur(id,Nom,Prenom,Sexe));
            
            }
            return joueurs;
        
        }catch(SQLException e) {
               e.printStackTrace();}
		
		return joueurs;
	}

	public JoueurDaoImpl(DaoFactory dao) {
		super();
		this.dao = dao;
	}
	
	public Joueur lecture (int id) {
		Connection conn =null;
		Joueur j = new Joueur();
        try {
      
            conn=dao.getConnection();
            String query = "select * from joueur where id="+id+";";

            Statement st = conn.createStatement();
      
            ResultSet rs = st.executeQuery(query);
            if(rs.next()) {
				return new Joueur(
						rs.getInt("id"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("sexe")
						);
			}else {
				return null;
			}    
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return j;
        
	}
	
	public void modifier (Joueur joueur) throws DaoException {
		Connection conn = null;
        try{
         conn = dao.getConnection();
         PreparedStatement preparedstatement = conn.prepareStatement("UPDATE joueur set id=? ,nom=? ,prenom=?,sexe=? where id=?");
        
         preparedstatement.setString(1, Integer.toString(joueur.getId()));
         preparedstatement.setString(5, Integer.toString(joueur.getId()));
         preparedstatement.setString(2, joueur.getNom());
         preparedstatement.setString(3, joueur.getPrenom());
         preparedstatement.setString(4, joueur.getSexe());
         
         preparedstatement.executeUpdate();
        }catch (SQLException e) {
        	e.printStackTrace();
        }        
	}
	
	public void supprimer(int id) {
		Connection conn = null;
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root", "1234567");
            
            PreparedStatement preparedstatement= conn.prepareStatement("DELETE from joueur where id="+id+";");
            preparedstatement.executeUpdate();
            
            
        } catch (SQLException e) {
           e.printStackTrace();
        }          
	}
	
	public List<Joueur> rechercher(String nom){
		List<Joueur> joueurs=new ArrayList<Joueur>();
		Connection conn =null;
        try {
      
            conn=dao.getConnection();
            String query = "select * from joueur where nom like '%"+nom+"%' or prenom like '%"+nom+"%';";

            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
              int id = rs.getInt("id");
              String Nom = rs.getString("Nom");
              String Prenom = rs.getString("Prenom");
              String Sexe = rs.getString("Sexe");
              joueurs.add(new Joueur(id,Nom,Prenom,Sexe));
            }
        }catch(SQLException e) {
                e.printStackTrace();
            
        }return joueurs;
	}
	public List<Joueur> rechercherFinalistes(String nom){
		List<Joueur> joueurs=new ArrayList<Joueur>();
		Connection conn =null;
        try {
      
            conn=dao.getConnection();
            String query = "select * from joueur inner join match_tennis on match_tennis.ID_FINALISTE=joueur.id where nom like '"+nom+"%'";

            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
              int id = rs.getInt("id");
              String Nom = rs.getString("Nom");
              String Prenom = rs.getString("Prenom");
              String Sexe = rs.getString("Sexe");
              joueurs.add(new Joueur(id,Nom,Prenom,Sexe));
            }
        }catch(SQLException e) {
                e.printStackTrace();
            
        }return joueurs;
	}
	public List<Joueur> rechercherVainqueurs(String nom){
		List<Joueur> joueurs=new ArrayList<Joueur>();
		Connection conn =null;
        try {
      
            conn=dao.getConnection();
            String query = "select * from joueur inner join match_tennis on match_tennis.ID_VAINQUEUR=joueur.id where nom like '"+nom+"%'";

            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
              int id = rs.getInt("id");
              String Nom = rs.getString("Nom");
              String Prenom = rs.getString("Prenom");
              String Sexe = rs.getString("Sexe");
              joueurs.add(new Joueur(id,Nom,Prenom,Sexe));
            }
        }catch(SQLException e) {
                e.printStackTrace();
            
        }return joueurs;
	}
	
	
	
}