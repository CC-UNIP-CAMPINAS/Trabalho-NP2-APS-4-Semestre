package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.TableController;
import controller.TelaAtualizaLivroController;
import controller.TelaCriaLivroController;
import model.collection.Colecao;
import model.collection.entities.Autor;
import model.collection.entities.Livro;

public class TelaAtualizaLivro extends JFrame {
	private JButton btAtualizaLivro = new JButton("Atualizar");
	private static JComboBox cbEditora;// Recebe o treeSet que foi transformado em array de objetos
	private static JTextField tfTitle = new JTextField();
	private static JTextField tfIsbn = new JTextField();
	private static JTextField tfPrice = new JTextField();
	public static TableController tabela = new TableController();
	private static DefaultTableModel dtmAutores;
	private static DefaultTableModel dtmAutoresSelecionados;
	private static JTable tabelaAutores;
	private static JTable tabelaAutoresSelecionados;
	private static TelaAtualizaLivro instancia;

	static {
		instancia = new TelaAtualizaLivro();
	}
	
	private TelaAtualizaLivro() {
		setVisible(true);
		setSize(1200, 550);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Atualiza Livro");
		setLocationRelativeTo(null);

		// TOP
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0, 60));
		panelTop.setBackground(new Color(26, 83, 92));
		add(BorderLayout.PAGE_START, panelTop);

		JLabel labelTitulo = new JLabel();
		labelTitulo.setText("<html><body><h1><span style='color: white;'>Atualiza Livro</h1></body></html>");
		panelTop.add(labelTitulo);

		// CENTER
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1, 3, 5, 0));
		add(BorderLayout.CENTER, panelCentral);
		
		JScrollPane barraRolagem = new JScrollPane(tabela.getTabela());
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		panelCentral.add(barraRolagem);
		TelaAtualizaLivroController.populaTabelaLivros(tabela);
		String[] colunasTabela = {"ISDB","Titulo","Editora","Preço"};
        tabela.setColumnIdentifiers(colunasTabela);
		tabela.getTabela().addMouseListener(new TelaAtualizaLivroController().new SelecionaLivro());

		String[] colunas = {"ID", "Nome", "Sobrenome"};
		Object[][] dados = new Object[0][3];
		dtmAutores = new DefaultTableModel(dados, colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaAutores = new JTable(getDtmAutores());
		JScrollPane barraRolagem1 = new JScrollPane(getTabelaAutores());
		barraRolagem1.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem1.setBorder(BorderFactory.createEmptyBorder());
		panelCentral.add(barraRolagem1);

		getTabelaAutores().addMouseListener(new TelaAtualizaLivroController().new selecionaAutor(0));

		String[] colunas2 = {"ID", "Nome", "Sobrenome" };
		Object[][] dados2 = new Object[0][3];
		dtmAutoresSelecionados = new DefaultTableModel(dados2, colunas2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaAutoresSelecionados = new JTable(getDtmAutoresSelecionados());
		JScrollPane barraRolagem2 = new JScrollPane(getTabelaAutoresSelecionados());
		barraRolagem2.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem2.setBorder(BorderFactory.createEmptyBorder());

		panelCentral.add(barraRolagem2);
		getTabelaAutoresSelecionados().addMouseListener(new TelaAtualizaLivroController().new selecionaAutor(1));
		TelaAtualizaLivroController.populaTabelaAutores(dtmAutoresSelecionados);

		// DIREITA
		JPanel panelDireita = new JPanel();
		panelDireita.setPreferredSize(new Dimension(300, 0));
		panelDireita.setBackground(new Color(78, 205, 196));
		panelDireita.setLayout(new FlowLayout());
		add(BorderLayout.LINE_END, panelDireita);

		JPanel panelDireitaInterno = new JPanel();
		panelDireitaInterno.setPreferredSize(new Dimension(280, 150));
		panelDireitaInterno.setBackground(new Color(78, 205, 196));
		panelDireitaInterno.setLayout(new GridLayout(5, 2, 5, 5));
		panelDireita.add(panelDireitaInterno);

		JLabel labelTituloDoLivro = new JLabel("Título:");
		panelDireitaInterno.add(labelTituloDoLivro);
		panelDireitaInterno.add(tfTitle);

		JLabel labelIsbn = new JLabel("ISBN:");
		panelDireitaInterno.add(labelIsbn);
		panelDireitaInterno.add(tfIsbn);

		JLabel labelPreco = new JLabel("Preço:");
		panelDireitaInterno.add(labelPreco);
		panelDireitaInterno.add(tfPrice);

		JLabel labelEditora = new JLabel("Editora:");
		cbEditora = new JComboBox(Colecao.getEditoras().toArray());
		panelDireitaInterno.add(labelEditora);
		panelDireitaInterno.add(cbEditora);

		panelDireitaInterno.add(btAtualizaLivro);
		btAtualizaLivro.addActionListener(new TelaAtualizaLivroController().new onBtAtualizarLivro());
	}
	
	//GETS
	public static synchronized TelaAtualizaLivro getInstance() {
		if (instancia.isDisplayable() == false) {
			instancia = new TelaAtualizaLivro();
		}
		return instancia;
	}
	
	public static Livro getLivroSelecionado() {// seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionada = tabela.getTabela().getSelectedRow();
		String isbn = tabela.getValueAt(linhaSelecionada, 0).toString();
		String titulo = tabela.getValueAt(linhaSelecionada, 1).toString();
		String editora = tabela.getValueAt(linhaSelecionada, 2).toString();
		Double preco = Double.parseDouble(tabela.getValueAt(linhaSelecionada, 3).toString());
		Livro livro = new Livro(titulo, isbn, editora, preco);
		return livro;
	}
	
	public static Autor getAutorSelecionado() {// seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionada = getTabelaAutores().getSelectedRow();
		int id = Integer.parseInt(getDtmAutores().getValueAt(linhaSelecionada, 0).toString());
		String nome = getDtmAutores().getValueAt(linhaSelecionada, 1).toString();
		String sobreNome = getDtmAutores().getValueAt(linhaSelecionada, 2).toString();
		Autor autor = new Autor(nome, sobreNome, id);
		return autor;
	}
	
	public static Autor getAutorSelecionadoInverso() {// seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionada = getTabelaAutoresSelecionados().getSelectedRow();
		int id = Integer.parseInt(getDtmAutoresSelecionados().getValueAt(linhaSelecionada, 0).toString());
		String nome = getDtmAutoresSelecionados().getValueAt(linhaSelecionada, 1).toString();
		String sobreNome = getDtmAutoresSelecionados().getValueAt(linhaSelecionada, 2).toString();
		Autor autor = new Autor(nome, sobreNome, id);
		return autor;
	}

	public static DefaultTableModel getDtmAutores() {
		return dtmAutores;
	}

	public static DefaultTableModel getDtmAutoresSelecionados() {
		return dtmAutoresSelecionados;
	}

	public static JTable getTabelaAutores() {
		return tabelaAutores;
	}

	public static JTable getTabelaAutoresSelecionados() {
		return tabelaAutoresSelecionados;
	}

	public static JComboBox getCbEditora() {
		return cbEditora;
	}

	public static JTextField getTfTitle() {
		return tfTitle;
	}

	public static JTextField getTfIsbn() {
		return tfIsbn;
	}

	public static JTextField getTfPrice() {
		return tfPrice;
	}

	public static TableController getTabela() {
		return tabela;
	}
}
