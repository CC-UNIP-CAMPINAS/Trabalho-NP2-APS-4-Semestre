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

import controller.TelaExcluiEditoraController;
import model.collection.entities.Editora;

public class TelaExcluiEditora extends JFrame {
	private JButton btBuscaEditora = new JButton("Buscar");
	private JButton btExcluirEditora = new JButton("Excluir");
	private static JTextField tfNome = new JTextField();
	private static DefaultTableModel dtmEditora;
	private static DefaultTableModel dtmEditorasSelecionadas;
	private static JTable tabelaEditoras;
	private static JTable tabelaEditorasSelecionadas;
	private static TelaExcluiEditora instancia;

	static {
		instancia = new TelaExcluiEditora();
	}

	private TelaExcluiEditora() {
		setSize(1250, 550);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Exclui Autor");
		setLocationRelativeTo(null);

		// TOP
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0, 60));
		panelTop.setBackground(new Color(26, 83, 92));
		add(BorderLayout.PAGE_START, panelTop);

		JLabel labelTitulo = new JLabel();
		labelTitulo.setText("<html><body><h1><span style='color: white;'>Excluir Editora</h1></body></html>");
		panelTop.add(labelTitulo);

		// CENTER
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1, 2, 5, 0));
		add(BorderLayout.CENTER, panelCentral);

		String[] colunas = { "ID", "Editora", "URL" };
		Object[][] dados = new Object[0][3];
		dtmEditora = new DefaultTableModel(dados, colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaEditoras = new JTable(getDtmEditora());
		JScrollPane barraRolagem = new JScrollPane(getTabelaEditoras());
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		panelCentral.add(barraRolagem);
		getTabelaEditoras().addMouseListener(new TelaExcluiEditoraController().new selecionaEditora(0));// coloca um evento

		String[] colunas2 = { "ID", "Editora", "URL" };
		Object[][] dados2 = new Object[0][3];
		dtmEditorasSelecionadas = new DefaultTableModel(dados2, colunas2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaEditorasSelecionadas = new JTable(getDtmEditorasSelecionadas());
		JScrollPane barraRolagem2 = new JScrollPane(getTabelaEditorasSelecionadas());
		barraRolagem2.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem2.setBorder(BorderFactory.createEmptyBorder());
		panelCentral.add(barraRolagem2);
		getTabelaEditorasSelecionadas().addMouseListener(new TelaExcluiEditoraController().new selecionaEditora(1));

		// DIREITA
		JPanel panelDireita = new JPanel();
		panelDireita.setPreferredSize(new Dimension(300, 0));
		panelDireita.setBackground(new Color(78, 205, 196));
		panelDireita.setLayout(new FlowLayout());
		add(BorderLayout.LINE_END, panelDireita);

		getTfNome().setPreferredSize(new Dimension(200, 20));
		panelDireita.add(getTfNome());

		panelDireita.add(btBuscaEditora);
		btBuscaEditora.addActionListener(new TelaExcluiEditoraController().new onBtBuscarEditora());

		panelDireita.add(btExcluirEditora);
		btExcluirEditora.addActionListener(new TelaExcluiEditoraController().new onBtExcluiEditora());

		setVisible(true);
	}

	//GETS
	public static synchronized TelaExcluiEditora getInstance() {
		if (instancia.isDisplayable() == false) {
			instancia = new TelaExcluiEditora();
		}
		return instancia;
	}

	public static Editora getEditoraSelecionada() {// seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionada = getTabelaEditoras().getSelectedRow();
		int id = Integer.parseInt(getDtmEditora().getValueAt(linhaSelecionada, 0).toString());
		String nome = getDtmEditora().getValueAt(linhaSelecionada, 1).toString();
		String url = getDtmEditora().getValueAt(linhaSelecionada, 2).toString();
		Editora editora = new Editora(nome, url, id);
		return editora;
	}

	public static Editora getEditoraSelecionadaInverso() {// seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionada = getTabelaEditorasSelecionadas().getSelectedRow();
		int id = Integer.parseInt(getDtmEditorasSelecionadas().getValueAt(linhaSelecionada, 0).toString());
		String nome = getDtmEditorasSelecionadas().getValueAt(linhaSelecionada, 1).toString();
		String url = getDtmEditorasSelecionadas().getValueAt(linhaSelecionada, 2).toString();
		Editora editora = new Editora(nome, url, id);
		return editora;
	}

	public static DefaultTableModel getDtmEditora() {
		return dtmEditora;
	}

	public static JTextField getTfNome() {
		return tfNome;
	}

	public static JTable getTabelaEditorasSelecionadas() {
		return tabelaEditorasSelecionadas;
	}

	public static DefaultTableModel getDtmEditorasSelecionadas() {
		return dtmEditorasSelecionadas;
	}

	public static JTable getTabelaEditoras() {
		return tabelaEditoras;
	}
}
