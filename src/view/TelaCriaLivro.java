package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.TelaCriaLivroController;
import model.collection.Colecao;
import model.collection.entities.Autor;

public class TelaCriaLivro extends JFrame{
	public JButton btCriarLivro = new JButton("Criar");
	public static JComboBox cbEditora = new JComboBox(Colecao.getEditoras().toArray());//Recebe o treeSet que foi transformado em array de objetos
	public static JTextField tfTitle = new JTextField();
	public static JTextField tfIsbn = new JTextField();
	public static JTextField tfPrice = new JTextField();
	public static DefaultTableModel dtmAutores;
	public static DefaultTableModel dtmAutoresSelecionados;
	public static JTable tabelaAutores;
	public static JTable tabelaAutoresSelecionados;
	private static TelaCriaLivro instancia;
	
	static {
		instancia = new TelaCriaLivro();
	}
	
	private TelaCriaLivro() {
		setVisible(true);
		setSize(1000, 550);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Livro");
		
		tfTitle.setPreferredSize(new Dimension(200,20));
		JLabel selType = new JLabel(); 		
		selType.setText("Titulo:");
		add(selType);
		add(tfTitle);
		
		tfIsbn.setPreferredSize(new Dimension(200,20));
		JLabel selType1 = new JLabel(); 		
		selType1.setText("Isbn:");
		add(selType1);
		add(tfIsbn);
		
		tfPrice.setPreferredSize(new Dimension(100,20));
		JLabel selType2 = new JLabel(); 		
		selType2.setText("Price:");
		add(selType2);
		add(tfPrice);
		
		add(btCriarLivro);
		btCriarLivro.addActionListener(new TelaCriaLivroController().new onBtCriarLivro());
		
		add(cbEditora);
		
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
		TelaCriaLivroController.populaTabelaAutores(dtmAutores);//popula a tabela de autores
		tabelaAutores.addMouseListener(new TelaCriaLivroController().new selecionaAutor(0));//coloca um evento de clique de mouse sempre que clicar em uma linha
		
		
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
		tabelaAutoresSelecionados.addMouseListener(new TelaCriaLivroController().new selecionaAutor(1));//coloca um evento de clique de mouse sempre que clicar em uma linha
		
		
	}
	
	public static synchronized TelaCriaLivro getInstance() {
		if(instancia.isDisplayable() == false){
			instancia = new TelaCriaLivro();
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
