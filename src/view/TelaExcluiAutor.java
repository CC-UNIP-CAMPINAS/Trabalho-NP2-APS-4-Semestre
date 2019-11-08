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

import controller.TelaExcluiAutorController;
import model.collection.entities.Autor;

public class TelaExcluiAutor extends JFrame{
	public JButton btBuscaAutor = new JButton("Buscar");
	public JButton btExcluirAutor = new JButton("Excluir");
	public static JTextField tfNome = new JTextField();
	public static DefaultTableModel dtmAutores;
	public static DefaultTableModel dtmAutoresSelecionados;
	public static JTable tabelaAutores;
	public static JTable tabelaAutoresSelecionados;
	private static TelaExcluiAutor instancia;
	
	static {
		instancia = new TelaExcluiAutor();
	}
	
	private TelaExcluiAutor() {
		setSize(1250, 550);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Exclui Autor");
		
		tfNome.setPreferredSize(new Dimension(200,20));
		add(tfNome);
		
		add(btBuscaAutor);
		btBuscaAutor.addActionListener(new TelaExcluiAutorController().new onBtBuscarAutor());
		
		//tabela com todos os autores
		String [] colunas = {"ID", "Autor"};
		Object [][] dados = new Object[0][2];
		dtmAutores = new DefaultTableModel(dados, colunas){
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false; //Isso faz a celula da tabela não ser editavel
			}
		};	    
		tabelaAutores = new JTable(dtmAutores);
		JScrollPane barraRolagem = new JScrollPane(tabelaAutores);
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		add(barraRolagem);
		tabelaAutores.addMouseListener(new TelaExcluiAutorController().new selecionaAutor(0));//coloca um evento de clique de mouse sempre que clicar em uma linha
		
		
		//Tabela com os autores selecionados
		String [] colunas2 = {"ID", "Autor"};
		Object [][] dados2 = new Object[0][2];
		dtmAutoresSelecionados = new DefaultTableModel(dados2, colunas2){
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false; //Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaAutoresSelecionados = new JTable(dtmAutoresSelecionados);		
		JScrollPane barraRolagem2 = new JScrollPane(tabelaAutoresSelecionados);
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		add(barraRolagem2);
		tabelaAutoresSelecionados.addMouseListener(new TelaExcluiAutorController().new selecionaAutor(1));//coloca um evento de clique de mouse sempre que clicar em uma linha	
		
		btExcluirAutor.addActionListener(new TelaExcluiAutorController().new onBtExcluiAutor());
		add(btExcluirAutor);
		
		setVisible(true);
	}
	
	
	//GETS
	
	public static synchronized TelaExcluiAutor getInstance() {
		if(instancia.isDisplayable() == false){//pega unica instancia da classe se ela esta criada, se n�o o programa cria
			instancia = new TelaExcluiAutor();
		}
		return instancia;
	}
	
	
	public static Autor getAutorSelecionado() {//seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionada = tabelaAutores.getSelectedRow();
		int id   = Integer.parseInt(dtmAutores.getValueAt(linhaSelecionada, 0).toString());
		String nome = dtmAutores.getValueAt(linhaSelecionada, 1).toString();
		Autor autor = new Autor(nome, id);
		return autor;
	}
	
	public static Autor getAutorSelecionadoInverso() {//seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionada = tabelaAutoresSelecionados.getSelectedRow();
		int id   = Integer.parseInt(dtmAutoresSelecionados.getValueAt(linhaSelecionada, 0).toString());
		String nome = dtmAutoresSelecionados.getValueAt(linhaSelecionada, 1).toString();
		Autor autor = new Autor(nome, id);
		return autor;
	}	
}
