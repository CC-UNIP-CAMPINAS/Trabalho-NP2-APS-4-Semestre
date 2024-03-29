package model.dataBase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.collection.Colecao;
import model.collection.entities.Autor;
import model.dataBase.Banco;

//Essa classe vai ficar todos os tipos de acesso aos dados de autor no postgre
public abstract class DaoAutor {
	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	
	//Cria um autor no banco de dados
	public static void criaAutor(JTextField tfNome, JTextField tfSobreNome) {
		try {
			st = Banco.getConnection().prepareStatement("Select max(author_id) from authors");
			rs = st.executeQuery();
			rs.next();
			st = Banco.getConnection().prepareStatement("INSERT INTO authors VALUES (?, ?, ?)");
			st.setInt(1, (rs.getInt(1)+1));
			st.setString(2, tfNome.getText());
			st.setString(3, tfSobreNome.getText());
			st.execute();
			
			Autor autor = new Autor(tfNome.getText(), tfSobreNome.getText(), (rs.getInt(1)+1));
			Colecao.getAutores().add(autor);
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		finally{//Fecha o st e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
			Banco.closeResultSet(rs);
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
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally{//Fecha o st, rs e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
			Banco.closeResultSet(rs);
		}
	}
	
	//método que busca no banco e adiciona os autores na devida coleção
	public static boolean buscarAutor(JTextField tfAutor) {
		boolean status = false;
		try {
			Colecao.getAutoresTemporario().clear();
			st = Banco.getConnection().prepareStatement("Select * from authors where name ilike ? or fname ilike ?");
			st.setString(1, "%"+tfAutor.getText()+"%");
			st.setString(2, "%"+tfAutor.getText()+"%");
			rs = st.executeQuery();
			if(rs.next()) {
				do {
					Autor autor = new Autor(rs.getString(2), rs.getString(3), rs.getInt(1));
					Colecao.getAutoresTemporario().add(autor);	
				}
				while(rs.next());
				status = true;
			}
			else {
				status = false;
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally{//Fecha o st, rs e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
			Banco.closeResultSet(rs);
		}
		return status;
	}

	public static int excluiAutor(JTable tabelaAutores) {
		int count = (tabelaAutores.getRowCount());
		try {
			st = Banco.getConnection().prepareStatement("delete from authors where author_id = ?");
			int i;	
			for(i=0; i<count; i++) {
				int idAutor = Integer.parseInt((tabelaAutores.getValueAt(i, 0).toString()));
				st.setInt(1, idAutor);
				st.execute();
				Colecao.getAutores().removeIf(autor ->autor.getIdAutor() == idAutor);
			}
			return count;
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally{//Fecha o st, rs e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
		}
		return count;
	}
	
	public static void atualizaAutor(JTextField tfNome, JTextField tfId, JTextField tfSobreNome) {
		try {
			st = Banco.getConnection().prepareStatement("update authors set fname = ?, name = ? where author_id = ?");
			st.setInt(3, Integer.parseInt(tfId.getText()));
			st.setString(2, tfNome.getText());
			st.setString(1, tfSobreNome.getText());
			st.execute();
			for (Autor autor : Colecao.getAutores()) {
				if(autor.getIdAutor() == Integer.parseInt(tfId.getText())) {
					autor.setNome(tfNome.getText());
					autor.setSobreNome(tfSobreNome.getText());
					autor.setNomeCompleto(Autor.juntaNomeAutor(tfNome.getText(), tfSobreNome.getText()));
				}
			}		
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally{//Fecha o st, rs e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
		}
	}
}
