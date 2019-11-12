package view;

import java.util.TreeSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Livro;

@SuppressWarnings("serial")
public class TableContent extends JTable {
	
	private JTable tabela;
	
	public JTable getTabela() {
		return tabela;
	}

	private DefaultTableModel modelo = new DefaultTableModel();
	
	
	
    public TableContent() {
        tabela = new JTable(getModelo());
        getModelo().addColumn("ISDB");
        getModelo().addColumn("Titulo");
        getModelo().addColumn("Autores");        
        getModelo().addColumn("Editora");
        getModelo().addColumn("Preco");
        pesquisar(getModelo());
    }
	
	
    public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        TreeSet<Livro> livros = new TreeSet<>();
        livros = Colecao.getLivros();	
        
        
        
        for (Livro l : Colecao.getLivros()) {
            modelo.addRow(new Object[]{
            		l.getIsbn(),
            		l.getTitulo(),
            		l.getAutores(),
            		l.getEditora(),
            		l.getPreco()
            });
        }
    }


	public DefaultTableModel getModelo() {
		return modelo;
	}


	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}
	
}
