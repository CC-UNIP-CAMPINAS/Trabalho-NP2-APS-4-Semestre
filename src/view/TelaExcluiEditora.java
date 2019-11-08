package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.TelaExcluiEditoraController;
import model.collection.entities.Editora;

public class TelaExcluiEditora extends JFrame{
	public JButton btBuscaEditora = new JButton("Buscar");
	public JButton btExcluirEditora = new JButton("Excluir");
	public static JTextField tfNome = new JTextField();
	public static DefaultTableModel dtmEditora;
	public static DefaultTableModel dtmEditorasSelecionadas;
	public static JTable tabelaEditoras;
	public static JTable tabelaEditorasSelecionadas;
	private static TelaExcluiEditora instancia;
	
	static {
		instancia = new TelaExcluiEditora();
	}
	
	private TelaExcluiEditora() {
		setSize(1250, 550);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Exclui Autor");
		
		tfNome.setPreferredSize(new Dimension(200,20));
		add(tfNome);
		
		add(btBuscaEditora);
		btBuscaEditora.addActionListener(new TelaExcluiEditoraController().new onBtBuscarEditora());
		
		//tabela com todos os autores
		String [] colunas = {"ID", "Editora", "URL"};
		Object [][] dados = new Object[0][3];
		dtmEditora = new DefaultTableModel(dados, colunas){
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false; //Isso faz a celula da tabela não ser editavel
			}
		};	    
		tabelaEditoras = new JTable(dtmEditora);
		JScrollPane barraRolagem = new JScrollPane(tabelaEditoras);
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		add(barraRolagem);
		tabelaEditoras.addMouseListener(new TelaExcluiEditoraController().new selecionaEditora(0));//coloca um evento de clique de mouse sempre que clicar em uma linha
		
		
		//Tabela com os autores selecionados
		String [] colunas2 = {"ID", "Editora", "URL"};
		Object [][] dados2 = new Object[0][3];
		dtmEditorasSelecionadas = new DefaultTableModel(dados2, colunas2){
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false; //Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaEditorasSelecionadas = new JTable(dtmEditorasSelecionadas);		
		JScrollPane barraRolagem2 = new JScrollPane(tabelaEditorasSelecionadas);
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		add(barraRolagem2);
		tabelaEditorasSelecionadas.addMouseListener(new TelaExcluiEditoraController().new selecionaEditora(1));//coloca um evento de clique de mouse sempre que clicar em uma linha	
		
		btExcluirEditora.addActionListener(new TelaExcluiEditoraController().new onBtExcluiEditora());
		add(btExcluirEditora);
		
		setVisible(true);
	}
	
	
	//GETS
	
	public static synchronized TelaExcluiEditora getInstance() {
		if(instancia.isDisplayable() == false){//pega unica instancia da classe se ela esta criada, se n�o o programa cria
			instancia = new TelaExcluiEditora();
		}
		return instancia;
	}
	
	
	public static Editora getEditoraSelecionada() {//seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionada = tabelaEditoras.getSelectedRow();
		int id   = Integer.parseInt(dtmEditora.getValueAt(linhaSelecionada, 0).toString());
		String nome = dtmEditora.getValueAt(linhaSelecionada, 1).toString();
		String url = dtmEditora.getValueAt(linhaSelecionada, 2).toString();
		Editora editora = new Editora(nome, url, id);
		return editora;
	}
	
	public static Editora getEditoraSelecionadaInverso() {//seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionada = tabelaEditorasSelecionadas.getSelectedRow();
		int id   = Integer.parseInt(dtmEditorasSelecionadas.getValueAt(linhaSelecionada, 0).toString());
		String nome = dtmEditorasSelecionadas.getValueAt(linhaSelecionada, 1).toString();
		String url = dtmEditorasSelecionadas.getValueAt(linhaSelecionada, 2).toString();
		Editora editora = new Editora(nome, url, id);
		return editora;
	}	
}
