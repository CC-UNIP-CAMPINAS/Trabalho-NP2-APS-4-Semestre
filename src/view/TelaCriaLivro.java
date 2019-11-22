package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

import controller.TelaCriaLivroController;
import model.collection.Colecao;
import model.collection.entities.Autor;

public class TelaCriaLivro extends JFrame {
	private JButton btCriarLivro = new JButton("Criar");
	private static JComboBox cbEditora;// Recebe o treeSet que foi transformado em array de objetos
	private static JTextField tfTitle = new JTextField();
	private static JTextField tfIsbn = new JTextField();
	private static JTextField tfPrice = new JTextField();
	private static DefaultTableModel dtmAutores;
	private static DefaultTableModel dtmAutoresSelecionados;
	private static JTable tabelaAutores;
	private static JTable tabelaAutoresSelecionados;
	private static TelaCriaLivro instancia;

	static {
		instancia = new TelaCriaLivro();
	}

	private TelaCriaLivro() {
		setVisible(true);
		setSize(1100, 550);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Livro");
		setLocationRelativeTo(null);

		// TOP
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0, 60));
		panelTop.setBackground(new Color(26, 83, 92));
		add(BorderLayout.PAGE_START, panelTop);

		JLabel labelTitulo = new JLabel();
		labelTitulo.setText("<html><body><h1><span style='color: white;'>Criar Livro</h1></body></html>");
		panelTop.add(labelTitulo);

		// CENTER
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1, 2, 5, 0));
		add(BorderLayout.CENTER, panelCentral);

		String[] colunas = { "ID", "Autor", "Sobre Nome"};
		Object[][] dados = new Object[0][3];
		dtmAutores = new DefaultTableModel(dados, colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela n√£o ser editavel
			}
		};
		tabelaAutores = new JTable(getDtmAutores());
		JScrollPane barraRolagem = new JScrollPane(getTabelaAutores());
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Autores Cadastrados"));
		panelCentral.add(barraRolagem);
		

		TelaCriaLivroController.populaTabelaAutores(getDtmAutores());// popula a tabela de autores
		getTabelaAutores().addMouseListener(new TelaCriaLivroController().new SelecionaAutor(0));

		String[] colunas2 = { "ID", "Nome", "Sobre Nome" };
		Object[][] dados2 = new Object[0][3];
		dtmAutoresSelecionados = new DefaultTableModel(dados2, colunas2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela n√£o ser editavel
			}
		};
		tabelaAutoresSelecionados = new JTable(getDtmAutoresSelecionados());
		JScrollPane barraRolagem2 = new JScrollPane(getTabelaAutoresSelecionados());
		barraRolagem2.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem2.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Autores Selecionados"));

		panelCentral.add(barraRolagem2);
		getTabelaAutoresSelecionados().addMouseListener(new TelaCriaLivroController().new SelecionaAutor(1));

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

		JLabel labelTituloDoLivro = new JLabel("TÌtulo:");
		panelDireitaInterno.add(labelTituloDoLivro);
		panelDireitaInterno.add(tfTitle);

		JLabel labelIsbn = new JLabel("ISBN:");
		panelDireitaInterno.add(labelIsbn);
		panelDireitaInterno.add(tfIsbn);

		JLabel labelPreco = new JLabel("PreÁo:");
		panelDireitaInterno.add(labelPreco);
		panelDireitaInterno.add(tfPrice);

		JLabel labelEditora = new JLabel("Editora:");
		cbEditora = new JComboBox(Colecao.getEditoras().toArray());
		panelDireitaInterno.add(labelEditora);
		panelDireitaInterno.add(cbEditora);

		panelDireitaInterno.add(btCriarLivro);
		btCriarLivro.addActionListener(new TelaCriaLivroController().new OnBtCriarLivro());

	}

	// GETS
	public static synchronized TelaCriaLivro getInstance() {
		if (instancia.isDisplayable() == false) {
			instancia = new TelaCriaLivro();
		}
		return instancia;
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

	public JButton getBtCriarLivro() {
		return btCriarLivro;
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

	public static JTable getTabelaAutoresSelecionados() {
		return tabelaAutoresSelecionados;
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
}
