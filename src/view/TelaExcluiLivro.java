package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.TelaExcluiLivroController;
import model.collection.entities.Livro;

public class TelaExcluiLivro extends JFrame {
	private JButton btBuscaLivro = new JButton("Buscar");
	private JButton btExcluirLivro = new JButton("Excluir");
	private static JTextField tfNome = new JTextField();
	private static DefaultTableModel dtmLivros;
	private static DefaultTableModel dtmLivrosSelecionados;
	private static JTable tabelaLivros;
	private static JTable tabelaLivrosSelecionados;
	private static TelaExcluiLivro instancia;

	static {
		instancia = new TelaExcluiLivro();
	}

	private TelaExcluiLivro() {
		setSize(1250, 550);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Excluir Livro");
		setLocationRelativeTo(null);

		//TOP
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0, 60));
		panelTop.setBackground(new Color(26, 83, 92));
		add(BorderLayout.PAGE_START, panelTop);

		JLabel labelTitulo = new JLabel();
		labelTitulo.setText("<html><body><h1><span style='color: white;'>Excluir Livro</h1></body></html>");
		panelTop.add(labelTitulo);

		//CENTER
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1, 2, 5, 0));
		add(BorderLayout.CENTER, panelCentral);

		String[] colunas = { "Título", "ISBN", "Editora", "Preço" };
		Object[][] dados = new Object[0][4];
		dtmLivros = new DefaultTableModel(dados, colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaLivros = new JTable(getDtmLivros());
		JScrollPane barraRolagem = new JScrollPane(getTabelaLivros());
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		panelCentral.add(barraRolagem);
		getTabelaLivros().addMouseListener(new TelaExcluiLivroController().new SelecionaLivro(0));

		String[] colunas2 = { "Título", "ISBN", "Editora", "Preço" };
		Object[][] dados2 = new Object[0][4];
		dtmLivrosSelecionados = new DefaultTableModel(dados2, colunas2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaLivrosSelecionados = new JTable(getDtmLivrosSelecionados());
		JScrollPane barraRolagem2 = new JScrollPane(getTabelaLivrosSelecionados());
		barraRolagem2.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem2.setBorder(BorderFactory.createEmptyBorder());
		panelCentral.add(barraRolagem2);
		getTabelaLivrosSelecionados().addMouseListener(new TelaExcluiLivroController().new SelecionaLivro(1));
		

		//DIREITA
		JPanel panelDireita = new JPanel();
		panelDireita.setPreferredSize(new Dimension(300, 0));
		panelDireita.setBackground(new Color(78, 205, 196));
		panelDireita.setLayout(new FlowLayout());
		add(BorderLayout.LINE_END, panelDireita);
		
		JLabel labelNomeLivro = new JLabel("Nome do Livro:");
		panelDireita.add(labelNomeLivro);
		
		getTfNome().setPreferredSize(new Dimension(250, 20));
		panelDireita.add(getTfNome());

		panelDireita.add(btBuscaLivro);
		btBuscaLivro.addActionListener(new TelaExcluiLivroController().new OnBtBuscarLivro());

		btExcluirLivro.addActionListener(new TelaExcluiLivroController().new OnBtExcluiLivro());
		panelDireita.add(btExcluirLivro);

		setVisible(true);
	}

	//GETS
	public static synchronized TelaExcluiLivro getInstance() {
		if (instancia.isDisplayable() == false) {
			instancia = new TelaExcluiLivro();
		}
		return instancia;	
	}

	public static Livro getLivroSelecionado() {// seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionado = getTabelaLivros().getSelectedRow();

		String editora = getDtmLivros().getValueAt(linhaSelecionado, 2).toString();
		String titulo = getDtmLivros().getValueAt(linhaSelecionado, 0).toString();
		String isbn = getDtmLivros().getValueAt(linhaSelecionado, 1).toString();
		double preco = Double.parseDouble(getDtmLivros().getValueAt(linhaSelecionado, 3).toString());
		Livro livro = new Livro(titulo, isbn, editora, preco);
		return livro;
	}

	public static Livro getLivroSelecionadoInverso() {// seleciona a linha da tabela e pega os dados dessa linha
		int linhaselecionado = getTabelaLivrosSelecionados().getSelectedRow();
		String editora = getDtmLivrosSelecionados().getValueAt(linhaselecionado, 2).toString();
		String titulo = getDtmLivrosSelecionados().getValueAt(linhaselecionado, 0).toString();
		String isbn = getDtmLivrosSelecionados().getValueAt(linhaselecionado, 1).toString();
		Double preco = Double.parseDouble(getDtmLivrosSelecionados().getValueAt(linhaselecionado, 3).toString());
		Livro livro = new Livro(titulo, isbn, editora, preco);
		return livro;
	}

	public static DefaultTableModel getDtmLivros() {
		return dtmLivros;
	}

	public static JTextField getTfNome() {
		return tfNome;
	}

	public static JTable getTabelaLivrosSelecionados() {
		return tabelaLivrosSelecionados;
	}

	public static DefaultTableModel getDtmLivrosSelecionados() {
		return dtmLivrosSelecionados;
	}

	public static JTable getTabelaLivros() {
		return tabelaLivros;
	}
}
