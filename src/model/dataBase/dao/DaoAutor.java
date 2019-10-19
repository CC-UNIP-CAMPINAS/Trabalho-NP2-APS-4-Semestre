package model.dataBase.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;

import model.dataBase.Banco;

//Essa classe vai ficar todos os tipos de acesso aos dados de autor no postgre
public class DaoAutor {
	static PreparedStatement st = null;
	
	
	//Cria um autor no banco de dados
	public static void criaAutor(JTextField tfNome, JTextField tfSobreNome, JTextField tfId) {
		try {
			st = Banco.getConnection().prepareStatement("INSERT INTO authors VALUES (?, ?, ?)");
			st.setInt(1, Integer.parseInt(tfId.getText()));
			st.setString(2, tfNome.getText());
			st.setString(3, tfSobreNome.getText());
			st.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{//Fecha o st e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
		}
	}
}
