package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.DaoException;
import com.mycompany.beans.Tournoi;

public class TournoiDaoImpl implements TournoiDao{
	private DaoFactory dao;
	
	public TournoiDaoImpl(DaoFactory dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	public void ajouter(Tournoi tournoi) throws DaoException{
		Connection conn =null;
        try {
      
            conn=dao.getConnection();
            PreparedStatement preparedstatement= conn.prepareStatement("INSERT INTO tournoi(nom,code) values(?,?)");
            preparedstatement.setString(1, tournoi.getNom());
            preparedstatement.setString(2, tournoi.getCode());
            
            preparedstatement.executeUpdate();
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
	}
	
	@Override
	public List<Tournoi> lister() {
		List<Tournoi> tournois=new ArrayList<Tournoi>();
		Connection conn =null;
        try {
      
            conn=dao.getConnection();
            String query = "select * from tournoi;";

            Statement st = conn.createStatement();
      
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
              int id = rs.getInt("id");
              String Nom = rs.getString("Nom");
              String Code= rs.getString("Code");

              tournois.add(new Tournoi(id,Nom,Code));
            
            }
            return tournois;
        
        }catch(SQLException e) {
               e.printStackTrace();}
		
		return tournois;
	}
	
	public Tournoi lecture (int id) {
		Connection conn =null;
		Tournoi j = new Tournoi();
        try {
      
            conn=dao.getConnection();
            String query = "select * from tournoi where id="+id+";";

            Statement st = conn.createStatement();
      
            ResultSet rs = st.executeQuery(query);
            if(rs.next()) {
				return new Tournoi(
						rs.getInt("id"),
						rs.getString("nom"),
						rs.getString("code")
						);
			}else {
				return null;
			}    
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return j;
        
	}
	
	public void modifier (Tournoi tournoi) throws DaoException {
		Connection conn = null;
        try{
         conn = dao.getConnection();
         PreparedStatement preparedstatement = conn.prepareStatement("UPDATE tournoi set nom=? ,code=? where id=?");
        
         preparedstatement.setInt(3, tournoi.getId());
         preparedstatement.setString(1, tournoi.getNom());
         preparedstatement.setString(2, tournoi.getCode());
         
         preparedstatement.executeUpdate();
        }catch (SQLException e) {
        	e.printStackTrace();
        }        
	}
	
	public void supprimer(int id) {
		Connection conn = null;
        try{
            conn=dao.getConnection();
            
            PreparedStatement preparedstatement= conn.prepareStatement("DELETE from tournoi where id="+id+";");
            preparedstatement.executeUpdate();
            
            
        } catch (SQLException e) {
           e.printStackTrace();
        }          
	}
	
	public List<Tournoi> rechercher(String nom){
		List<Tournoi> tournois=new ArrayList<Tournoi>();
		Connection conn =null;
        try {
      
            conn=dao.getConnection();
            String query = "select * from tournoi where nom like '%"+nom+"%';";

            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
              int id = rs.getInt("id");
              String Nom = rs.getString("Nom");
              String Code = rs.getString("Code");
              tournois.add(new Tournoi(id,Nom,Code));
            }
        }catch(SQLException e) {
                e.printStackTrace();
            
        }return tournois;
	}
}
