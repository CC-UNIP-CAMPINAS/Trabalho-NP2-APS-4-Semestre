package model.dataBase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JTextField;

import model.collection.Colecao;
import model.collection.entities.Editora;
import model.dataBase.Banco;

//Essa classe vai ficar todos os tipos de acesso aos dados de Editora no postgre
public abstract class DaoEditora {

	static PreparedStatement st = null;
	static ResultSet rs = null;

	// Cria uma editora no banco de dados
	public static void criaEditora(JTextField tfNome, JTextField tfUrl) {
		try {
			st = Banco.getConnection().prepareStatement("Select max(publisher_id) from publishers");
			rs = st.executeQuery();
			rs.next();
			
			st = Banco.getConnection().prepareStatement("INSERT INTO publishers VALUES (?, ?, ?)");			
			st.setInt(1, rs.getInt(1)+1);
			st.setString(2, tfNome.getText());
			st.setString(3, tfUrl.getText());
			st.execute();
			
			Editora editora = new Editora(tfNome.getText(), tfUrl.getText(), (rs.getInt(1)+1));
			Colecao.getEditoras().add(editora);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// Fecha o st e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
		}
	}

	// método que busca no banco e adiciona as editoras na devida coleção
	public static void carregaEditora() {
		try {
			st = Banco.getConnection().prepareStatement("Select * from publishers");
			rs = st.executeQuery();

			while (rs.next()) {
				Editora editora = new Editora(rs.getString(2), rs.getString(3), rs.getInt(1));
				Colecao.getEditoras().add(editora);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// Fecha o st, rs e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
			Banco.closeResultSet(rs);
		}
	}

	// método que busca no banco e adiciona os autores na devida coleção
	public static boolean buscarEditora(JTextField tfEditora) {
		boolean status = false;
		try {
			Colecao.getEditorasTemporario().clear();
			st = Banco.getConnection().prepareStatement("Select * from publishers where name ilike ? or url ilike ?");
			st.setString(1, "%" + tfEditora.getText() + "%");
			st.setString(2, "%" + tfEditora.getText() + "%");
			rs = st.executeQuery();
			if (rs.next()) {
				do {
					Editora editora = new Editora(rs.getString(2), rs.getString(3), rs.getInt(1));
					Colecao.getEditorasTemporario().add(editora);
				} while (rs.next());
				status = true;
			} else {
				status = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// Fecha o st, rs e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
			Banco.closeResultSet(rs);
		}
		return status;
	}

	public static int excluiEditora(JTable tabelaEditora) {
		int count = (tabelaEditora.getRowCount());
		try {
			st = Banco.getConnection().prepareStatement("delete from publishers where publisher_id = ?");
			int i;
			for (i = 0; i < count; i++) {
				int idEditora = Integer.parseInt((tabelaEditora.getValueAt(i, 0).toString()));
				st.setInt(1, idEditora);
				st.execute();
				Colecao.getEditoras().removeIf(editora -> editora.getIdEditora() == idEditora);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// Fecha o st, rs e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
		}
		return count;
	}

	public static void atualizaEditora(JTextField tfNome, JTextField tfId, JTextField tfUrl) {
		try {
			st = Banco.getConnection().prepareStatement("update publishers set name = ?, url = ? where publisher_id = ?");
			st.setInt(3, Integer.parseInt(tfId.getText()));
			st.setString(1, tfNome.getText());
			st.setString(2, tfUrl.getText());
			st.execute();
			for (Editora editora : Colecao.getEditoras()) {
				if(editora.getIdEditora() == Integer.parseInt(tfId.getText())) {
					editora.setNome(tfNome.getText());
					editora.setUrl(tfUrl.getText());
				}
			}		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{//Fecha o st, rs e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
		}
	}
}
