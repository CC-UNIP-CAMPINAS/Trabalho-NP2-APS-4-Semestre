package model.dataBase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.collection.Colecao;
import model.collection.entities.Editora;
import model.dataBase.Banco;

//Essa classe vai ficar todos os tipos de acesso aos dados de Editora no postgre
public abstract class DaoEditora {
	
static PreparedStatement st = null;
static ResultSet rs = null;
	

	//Cria uma editora no banco de dados
	public static void criaEditora(JTextField tfNome, JTextField tfUrl, JTextField tfId, JComboBox cbEditora) {
		try {
			st = Banco.getConnection().prepareStatement("INSERT INTO publishers VALUES (?, ?, ?)");
			st.setInt(1, Integer.parseInt(tfId.getText()));
			st.setString(2, tfNome.getText());
			st.setString(3, tfUrl.getText());
			st.execute();
			Editora editora = new Editora(tfNome.getText(), tfUrl.getText(), Integer.parseInt(tfId.getText()));
			Colecao.getEditoras().add(editora);
			cbEditora.addItem(editora);//Adiciona o objeto no combobox
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{//Fecha o st e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
		}
	}
	
	//método que busca no banco e adiciona as editoras na devida coleção
	public static void carregaEditora() {
		try {
			st = Banco.getConnection().prepareStatement("Select * from publishers");
			rs = st.executeQuery();
			
			while(rs.next()) {
				Editora editora = new Editora(rs.getString(2), rs.getString(3), rs.getInt(1));
				Colecao.getEditoras().add(editora);
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
