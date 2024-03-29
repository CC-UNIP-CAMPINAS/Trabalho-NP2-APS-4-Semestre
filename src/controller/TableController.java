package controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Livro;

@SuppressWarnings("serial")
public class TableController extends DefaultTableModel{
	
	private JTable tabela;
	private DefaultTableModel modelo = this;
	private static String autoresTemp;
	
	public JTable getTabela() {
		return tabela;
	}
	
	
    public TableController() {
        tabela = new JTable(getModelo());
        String[] colunas = {"ISDB","Titulo","Autores","Editora","Preço"};
        this.setColumnIdentifiers(colunas);
        pesquisar(getModelo());
        
        for (Livro l : Colecao.getLivrosTemporario()) {
            modelo.addRow(new Object[]{
            		l.getIsbn(),
            		l.getTitulo(),
            		autoresTemp,
            		l.getEditora(),
            		l.getPreco()
            });
        }
    }
    
    public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        
        for (Livro l : Colecao.getLivrosTemporario()) {
        	autoresTemp="";
        	l.getAutores().sort(Livro.sortBySequ_no);
        	int count = 0;
        	for (String s : l.getAutores()) {
        		s = s.replaceAll("[^a-zA-Z ]", "");
        		if(count==0) {
        			autoresTemp = s;
        		}else {
        			autoresTemp = s + ", " + autoresTemp;
        		}
        		count++;
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
		return false; // Isso faz a celula da tabela n�o ser editavel
	}
}
