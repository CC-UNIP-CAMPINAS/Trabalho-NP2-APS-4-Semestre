package view;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Livro;

@SuppressWarnings("serial")
public class TableContent extends DefaultTableModel{
	
	private JTable tabela;
	private DefaultTableModel modelo = this;
	private static String autoresTemp;
	
	public JTable getTabela() {
		return tabela;
	}
	
	
    public TableContent() {
        tabela = new JTable(getModelo());
        String[] colunas = {"ISDB","Titulo","Editora","Preço"};
        this.setColumnIdentifiers(colunas);
        pesquisar(getModelo());
        
        for (Livro l : Colecao.getLivrosTemporario()) {
            modelo.addRow(new Object[]{
            		l.getIsbn(),
            		l.getTitulo(),
            		l.getEditora(),
            		l.getPreco()
            });
        }
    }
    
    public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        modelo.addRow(new Object[]{"<html><strong>ISDB<strong></html>", "<html><strong>Titulo<strong></html>", "<html><strong>Autores<strong></html>","<html><strong>Editora<strong></html>","<html><strong>Preço<strong></html>"});
        
        for (Livro l : Colecao.getLivrosTemporario()) {
        	autoresTemp="";
        	l.getAutores().sort(Livro.sortBySequ_no);
        	for (String s : l.getAutores()) {
        		s = s.replaceAll("[^a-zA-Z ]", "");
        		autoresTemp = s + ", " + autoresTemp;
            }
        	System.out.println(autoresTemp);
            modelo.addRow(new Object[]{
            		l.getIsbn(),
            		l.getTitulo(),
            		autoresTemp,
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
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false; // Isso faz a celula da tabela nÃ£o ser editavel
	}
}
