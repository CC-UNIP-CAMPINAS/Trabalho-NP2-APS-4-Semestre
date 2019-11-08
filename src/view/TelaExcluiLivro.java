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

import controller.TelaExcluiLivroController;
import model.collection.entities.Livro;

public class TelaExcluiLivro extends JFrame{
	public JButton btBuscaLivro = new JButton("Buscar");
	public JButton btExcluirLivro = new JButton("Excluir");
	public static JTextField tfNome = new JTextField();
	public static DefaultTableModel dtmLivros;
	public static DefaultTableModel dtmLivrosSelecionados;
	public static JTable tabelaLivros;
	public static JTable tabelaLivrosSelecionados;
	private static TelaExcluiLivro instancia;
	
	static {
		instancia = new TelaExcluiLivro();
	}
	
	private TelaExcluiLivro() {
		setSize(1250, 550);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Exclui Livro");
		
		tfNome.setPreferredSize(new Dimension(200,20));
		add(tfNome);
		
		add(btBuscaLivro);
		btBuscaLivro.addActionListener(new TelaExcluiLivroController().new OnBtBuscarLivro());
		
		//tabela com todos os autores
		String [] colunas = {"Título", "ISBN", "Editora", "Preço"};
		Object [][] dados = new Object[0][4];
		dtmLivros = new DefaultTableModel(dados, colunas){
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false; //Isso faz a celula da tabela não ser editavel
			}
		};	    
		tabelaLivros = new JTable(dtmLivros);
		JScrollPane barraRolagem = new JScrollPane(tabelaLivros);
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		add(barraRolagem);
		tabelaLivros.addMouseListener(new TelaExcluiLivroController().new SelecionaLivro(0));//coloca um evento de clique de mouse sempre que clicar em uma linha
		
		
		//Tabela com os autores selecionados
		String [] colunas2 = {"Título", "ISBN", "Editora", "Preço"};
		Object [][] dados2 = new Object[0][4];
		dtmLivrosSelecionados = new DefaultTableModel(dados2, colunas2){
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false; //Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaLivrosSelecionados = new JTable(dtmLivrosSelecionados);		
		JScrollPane barraRolagem2 = new JScrollPane(tabelaLivrosSelecionados);
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		add(barraRolagem2);
		tabelaLivrosSelecionados.addMouseListener(new TelaExcluiLivroController().new SelecionaLivro(1));//coloca um evento de clique de mouse sempre que clicar em uma linha	
		
		btExcluirLivro.addActionListener(new TelaExcluiLivroController().new OnBtExcluiLivro());
		add(btExcluirLivro);
		
		setVisible(true);
	}
	
	
	//GETS
	
	public static synchronized TelaExcluiLivro getInstance() {
		if(instancia.isDisplayable() == false){//pega unica instancia da classe se ela esta criada, se n�o o programa cria
			instancia = new TelaExcluiLivro();
		}
		return instancia;
	}
	
	
	public static Livro getLivroSelecionado() {//seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionado = tabelaLivros.getSelectedRow();
		
		String editora = dtmLivros.getValueAt(linhaSelecionado, 2).toString();
		String titulo = dtmLivros.getValueAt(linhaSelecionado, 0).toString();
		String isbn = dtmLivros.getValueAt(linhaSelecionado, 1).toString();
		double preco = Double.parseDouble(dtmLivros.getValueAt(linhaSelecionado, 3).toString());
		Livro livro = new Livro(titulo, isbn, editora, preco);
		return livro;
	}
	
	public static Livro getLivroSelecionadoInverso() {//seleciona a linha da tabela e pega os dados dessa linha
		int linhaselecionado = tabelaLivrosSelecionados.getSelectedRow();
		String editora   = dtmLivrosSelecionados.getValueAt(linhaselecionado, 2).toString();
		String titulo = dtmLivrosSelecionados.getValueAt(linhaselecionado, 0).toString();
		String isbn = dtmLivrosSelecionados.getValueAt(linhaselecionado, 1).toString();
		Double preco = Double.parseDouble(dtmLivrosSelecionados.getValueAt(linhaselecionado, 3).toString());
		Livro livro = new Livro(titulo, isbn, editora, preco);
		return livro;
	}	
}
