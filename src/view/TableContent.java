package view;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import model.collection.Colecao;
import model.collection.entities.Livro;

@SuppressWarnings("serial")
public class TableContent extends DefaultTableModel{
	
	private JTable tabela;
	private DefaultTableModel modelo = this;
	
	public JTable getTabela() {
		return tabela;
	}
	
	
    public TableContent() {
        tabela = new JTable(getModelo());
        String[] colunas = {"ISDB","Titulo","Autores","Editora","Pre�o"};
        this.setColumnIdentifiers(colunas);
        pesquisar(getModelo());
        
        for (Livro l : Colecao.getLivrosTemporario()) {
            modelo.addRow(new Object[]{
            		l.getIsbn(),
            		l.getTitulo(),
            		l.getAutores(),
            		l.getEditora(),
            		l.getPreco()
            });
        }
    }
    
    public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        modelo.addRow(new String[] {"<html><b>ISDB</b></html>","<html><b>Titulo</b></html>","<html><b>Autores</b></html>","<html><b>Editora</b></html>","<html><b>Pre�o</b></html>"});
        
        for (Livro l : Colecao.getLivrosTemporario()) {
        	l.getAutores().sort(Livro.sortBySequ_no);
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
