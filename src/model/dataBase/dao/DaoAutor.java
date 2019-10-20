package model.dataBase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import model.collection.Colecao;
import model.collection.entities.Autor;
import model.dataBase.Banco;

//Essa classe vai ficar todos os tipos de acesso aos dados de autor no postgre
public abstract class DaoAutor {
	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	
	//Cria um autor no banco de dados
	public static void criaAutor(JTextField tfNome, JTextField tfSobreNome, JTextField tfId) {
		try {
			st = Banco.getConnection().prepareStatement("INSERT INTO authors VALUES (?, ?, ?)");
			st.setInt(1, Integer.parseInt(tfId.getText()));
			st.setString(2, tfNome.getText());
			st.setString(3, tfSobreNome.getText());
			st.execute();
			
			Autor autor = new Autor(tfNome.getText(), tfSobreNome.getText(), Integer.parseInt(tfId.getText()));
			Colecao.getAutores().add(autor);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{//Fecha o st e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
		}
	}
	
	//método que busca no banco e adiciona os autores na devida coleção
	public static void carregaAutor() {
		try {
			st = Banco.getConnection().prepareStatement("Select * from authors");
			rs = st.executeQuery();
			
			while(rs.next()) {
				Autor autor = new Autor(rs.getString(2), rs.getString(3), rs.getInt(1));
				Colecao.getAutores().add(autor);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{//Fecha o st, rs e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
			Banco.closeResultSet(rs);
		}
	}
	
}
