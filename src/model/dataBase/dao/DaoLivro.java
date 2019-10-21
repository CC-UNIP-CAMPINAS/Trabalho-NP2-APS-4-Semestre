package model.dataBase.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.collection.entities.Editora;
import model.dataBase.Banco;

//Essa classe vai ficar todos os tipos de acesso aos dados do livro no postgre
public abstract class DaoLivro {
	
static PreparedStatement st = null;
	
	//Cria uma editora no banco de dados
	public static void criaLivro(JTextField tfTitle, JTextField tfIsbn, JTextField tfPrice, JComboBox cbEditora) {
		try {
			Editora editora = (Editora) cbEditora.getSelectedItem();//Pega o objeto selecionado na combobox e associa ele a uma nova editora
			st = Banco.getConnection().prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?)");
			st.setString(1, tfTitle.getText());
			st.setString(2, tfIsbn.getText());
			st.setInt(3, editora.getIdEditora());//da editora selecionada, pega o id pra associar no banco
			st.setInt(4, Integer.parseInt(tfPrice.getText()));
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
