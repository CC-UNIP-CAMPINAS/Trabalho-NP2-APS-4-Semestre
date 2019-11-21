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

import controller.TelaExcluiAutorController;
import model.collection.entities.Autor;

public class TelaExcluiAutor extends JFrame {
	private JButton btBuscaAutor = new JButton("Buscar");
	private JButton btExcluirAutor = new JButton("Excluir");
	private static JTextField tfNome = new JTextField();
	private static JTextField tfsobreNome = new JTextField();
	private static DefaultTableModel dtmAutores;
	private static DefaultTableModel dtmAutoresSelecionados;
	private static JTable tabelaAutores;
	private static JTable tabelaAutoresSelecionados;
	private static TelaExcluiAutor instancia;

	static {
		instancia = new TelaExcluiAutor();
	}

	private TelaExcluiAutor() {
		setSize(1250, 550);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Excluir Autor");
		setLocationRelativeTo(null);

		//TOP
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0, 60));
		panelTop.setBackground(new Color(26, 83, 92));
		add(BorderLayout.PAGE_START, panelTop);

		JLabel labelTitulo = new JLabel();
		labelTitulo.setText("<html><body><h1><span style='color: white;'>Excluir Autor</h1></body></html>");
		panelTop.add(labelTitulo);

		//CENTER
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1, 2, 5, 0));
		add(BorderLayout.CENTER, panelCentral);

		String[] colunas = { "ID", "Nome", "Sobre Nome" };
		Object[][] dados = new Object[0][3];
		dtmAutores = new DefaultTableModel(dados, colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaAutores = new JTable(getDtmAutores());
		JScrollPane barraRolagem = new JScrollPane(getTabelaAutores());
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		panelCentral.add(barraRolagem);
		getTabelaAutores().addMouseListener(new TelaExcluiAutorController().new selecionaAutor(0));

		String[] colunas2 = { "ID", "Nome", "Sobre Nome" };
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
		getTabelaAutoresSelecionados().addMouseListener(new TelaExcluiAutorController().new selecionaAutor(1));

		// DIREITA
		JPanel panelDireita = new JPanel();
		panelDireita.setPreferredSize(new Dimension(300, 0));
		panelDireita.setBackground(new Color(78, 205, 196));
		panelDireita.setLayout(new FlowLayout());
		add(BorderLayout.LINE_END, panelDireita);

		JLabel labelNomeAutor = new JLabel("Nome do autor:");
		panelDireita.add(labelNomeAutor);	
		getTfNome().setPreferredSize(new Dimension(250, 20));
		panelDireita.add(getTfNome());
		JLabel labelSobreNomeAutor = new JLabel("Nome do autor:");
		panelDireita.add(labelSobreNomeAutor);	
		getTfsobreNome().setPreferredSize(new Dimension(250, 20));
		panelDireita.add(getTfNome());

		panelDireita.add(btBuscaAutor);
		btBuscaAutor.addActionListener(new TelaExcluiAutorController().new onBtBuscarAutor());
		
		panelDireita.add(btExcluirAutor);
		btExcluirAutor.addActionListener(new TelaExcluiAutorController().new onBtExcluiAutor());
		
		setVisible(true);
	}

	// GETS

	public static synchronized TelaExcluiAutor getInstance() {
		if (instancia.isDisplayable() == false) {
			instancia = new TelaExcluiAutor();
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

	public static DefaultTableModel getDtmAutores() {
		return dtmAutores;
	}

	public static JTextField getTfNome() {
		return tfNome;
	}

	public static JTable getTabelaAutoresSelecionados() {
		return tabelaAutoresSelecionados;
	}

	public static DefaultTableModel getDtmAutoresSelecionados() {
		return dtmAutoresSelecionados;
	}

	public static JTable getTabelaAutores() {
		return tabelaAutores;
	}

	public static JTextField getTfsobreNome() {
		return tfsobreNome;
	}
}
