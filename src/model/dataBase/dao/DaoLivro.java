package model.dataBase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.collection.Colecao;
import model.collection.entities.Autor;
import model.collection.entities.Editora;
import model.collection.entities.Livro;
import model.dataBase.Banco;
import view.TelaCriaLivro;

//Essa classe vai ficar todos os tipos de acesso aos dados do livro no postgre
public abstract class DaoLivro {
	
static PreparedStatement st = null;
static ResultSet rs = null;
	
	//Cria uma editora no banco de dados
	public static void criaLivro(JTextField tfTitle, JTextField tfIsbn, JTextField tfPrice, JComboBox cbEditora, JTable tabelaAutores) {
		try {
			Editora editora = (Editora) cbEditora.getSelectedItem();//Pega o objeto selecionado na combobox e associa ele a uma nova editora
			st = Banco.getConnection().prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?)");
			st.setString(1, tfTitle.getText());
			st.setString(2, tfIsbn.getText());
			st.setInt(3, editora.getIdEditora());//da editora selecionada, pega o id pra associar no banco
			st.setDouble(4, Double.parseDouble(tfPrice.getText()));
			st.execute();
			
			st = Banco.getConnection().prepareStatement("INSERT INTO booksauthors VALUES (?, ?, ?)");
			int i;
			int count = (tabelaAutores.getRowCount()-1);
			int j = 1;
			Livro livro = new Livro(tfTitle.getText(), tfIsbn.getText(), editora.getNomeEditora(), Double.parseDouble(tfPrice.getText()));
			for(i=0; i<=count; i++) {
				st.setString(1, tfIsbn.getText());
				st.setInt(2, Integer.parseInt((tabelaAutores.getValueAt(i, 0).toString())));
				st.setInt(3, j);
				st.execute();
				livro.getAutores().add(tabelaAutores.getValueAt(i, 1).toString());
				j++;
			}
			Colecao.getLivros().add(livro);
			JOptionPane.showMessageDialog(null, "Livro criado!");

			System.out.println("\n--------------------------------------------\n");
			for (Livro liv : Colecao.getLivros()) {
				System.out.println(liv);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(NumberFormatException a) {
			JOptionPane.showMessageDialog(null, "Valor incorreto para preço!\n''"+TelaCriaLivro.getTfPrice().getText()+"'' não é um número!");
		}
		finally{//Fecha o st e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
		}
	}
	
	public static void carregaLivro() {
		try {
			String query = 
					"select * from books b inner join booksauthors ba on(b.isbn = ba.isbn) " + 
					"inner join publishers p on(p.publisher_id = b.publisher_id) " + 
					"inner join authors a on(a.author_id = ba.author_id);";
			st = Banco.getConnection().prepareStatement(query);
			rs = st.executeQuery();			
			while(rs.next()) {
				Livro livro = new Livro(rs.getString(1), rs.getString(2), rs.getString(9), rs.getDouble(4));
				String nomeAutor= Autor.juntaNomeAutor(rs.getString(12), rs.getString(13));
				if(Colecao.getLivros().contains(livro)){
					for(Livro liv : Colecao.getLivros()){
						if(liv.equals(livro)){
							liv.getAutores().add(nomeAutor);
						}
					}
				}	
				else{
					livro.getAutores().add(nomeAutor);
					Colecao.getLivros().add(livro);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{//Fecha o st e o connection
			Banco.closeConnection();
			Banco.closeStatement(st);
			Banco.closeResultSet(rs);
		}
	}
	
	//mÃ©todo que busca no banco e adiciona os autores na devida coleÃ§Ã£o
		public static boolean buscarLivro(JTextField tfLivro) {
			boolean status = false;
			try {
				Colecao.getLivrosTemporario().clear();
				st = Banco.getConnection().prepareStatement(
						"select * from books b inner join publishers p on(p.publisher_id = b.publisher_id) "
						+ "where b.title ilike ? or p.name ilike ?;");
				st.setString(1, "%"+tfLivro.getText()+"%");
				st.setString(2, "%"+tfLivro.getText()+"%");
				rs = st.executeQuery();
				if(rs.next()) {
					do {
						Livro livro = new Livro(rs.getString(1), rs.getString(2), rs.getString(6), rs.getDouble(4));
						Colecao.getLivrosTemporario().add(livro);	
					}
					while(rs.next());
					status = true;
				}
				else {
					status = false;
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
			return status;
		}

		public static int excluiLivro(JTable tabelaLivros) {
			int count = (tabelaLivros.getRowCount());
			try {
				st = Banco.getConnection().prepareStatement("delete from books where isbn = ?");
				int i;	
				for(i=0; i<count; i++) {
					String isbn = tabelaLivros.getValueAt(i, 1).toString();
					st.setString(1, isbn);
					st.execute();
					Colecao.getLivros().removeIf(livro -> livro.getIsbn().equals(isbn));
				}
				return count;
			}
			catch(SQLException e) {
				e.printStackTrace();
			}	
			finally{//Fecha o st, rs e o connection
				Banco.closeConnection();
				Banco.closeStatement(st);
			}
			return count;
		}
}
