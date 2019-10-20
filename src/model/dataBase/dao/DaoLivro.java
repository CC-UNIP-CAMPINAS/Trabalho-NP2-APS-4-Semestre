package model.dataBase.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;

import model.dataBase.Banco;

//Essa classe vai ficar todos os tipos de acesso aos dados do livro no postgre
public class DaoLivro {
	
static PreparedStatement st = null;
	
	//Cria uma editora no banco de dados
	public static void criaLivro(JTextField tfTitle, JTextField tfIsbn, JTextField tfPublisherId, JTextField tfPrice) {
		try {
			st = Banco.getConnection().prepareStatement("INSERT INTO publishers VALUES (?, ?, ?, ?)");
			st.setInt(1, Integer.parseInt(tfTitle.getText()));
			st.setString(2, tfIsbn.getText());
			st.setString(3, tfPublisherId.getText());
			st.setString(4, tfPrice.getText());
			st.execute();
			
			//title isbn publisher_id price
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
