package model.dataBase.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;

import model.dataBase.Banco;

//Essa classe vai ficar todos os tipos de acesso aos dados de Editora no postgre
public abstract class DaoEditora {
	
static PreparedStatement st = null;
	

	//Cria uma editora no banco de dados
	public static void criaEditora(JTextField tfNome, JTextField tfUrl, JTextField tfId) {
		try {
			st = Banco.getConnection().prepareStatement("INSERT INTO publishers VALUES (?, ?, ?)");
			st.setInt(1, Integer.parseInt(tfId.getText()));
			st.setString(2, tfNome.getText());
			st.setString(3, tfUrl.getText());
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
