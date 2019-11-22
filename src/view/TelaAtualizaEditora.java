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

import controller.TelaAtualizaAutorController;
import controller.TelaAtualizaEditoraController;
import model.collection.entities.Editora;

public class TelaAtualizaEditora extends JFrame {
	private JButton btAtualizaEditora = new JButton("Atualizar");
	private static JTextField tfId = new JTextField();
	private static JTextField tfNome = new JTextField();
	private static JTextField tfUrl = new JTextField();
	private static DefaultTableModel dtmEditoras;
	private static JTable tabelaEditoras;
	private static TelaAtualizaEditora instancia;

	static {
		instancia = new TelaAtualizaEditora();
	}
	
	private TelaAtualizaEditora() {
		setSize(1250, 550);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Atualiza Editora");
		setLocationRelativeTo(null);

		// TOP
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0, 60));
		panelTop.setBackground(new Color(26, 83, 92));
		add(BorderLayout.PAGE_START, panelTop);

		JLabel labelTitulo = new JLabel();
		labelTitulo.setText("<html><body><h1><span style='color: white;'>Atualizar Editora</h1></body></html>");
		panelTop.add(labelTitulo);

		// CENTER
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1, 1));
		add(BorderLayout.CENTER, panelCentral);

		String[] colunas = { "ID", "Editora", "URL" };
		Object[][] dados = new Object[0][3];
		dtmEditoras = new DefaultTableModel(dados, colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaEditoras = new JTable(getDtmEditoras());
		JScrollPane barraRolagem = new JScrollPane(getTabelaEditoras());
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		panelCentral.add(barraRolagem);
		getTabelaEditoras().addMouseListener(new TelaAtualizaEditoraController().new selecionaEditora());
		TelaAtualizaEditoraController.populaTabelaEditoras(getDtmEditoras());

		// DIREITA
		JPanel panelDireita = new JPanel();
		panelDireita.setPreferredSize(new Dimension(300, 0));
		panelDireita.setBackground(new Color(78, 205, 196));
		panelDireita.setLayout(new FlowLayout());
		add(BorderLayout.LINE_END, panelDireita);
		
		JLabel labelIdEditora = new JLabel("ID da editora:");
		panelDireita.add(labelIdEditora);
		getTfId().setPreferredSize(new Dimension(250, 20));
		panelDireita.add(getTfId());
		
		JLabel labelNomeEditora = new JLabel("Nome da Editora:");
		panelDireita.add(labelNomeEditora);
		getTfNome().setPreferredSize(new Dimension(250, 20));
		panelDireita.add(getTfNome());
		
		JLabel labelUrlEditora = new JLabel("URL da editora:");
		panelDireita.add(labelUrlEditora);
		getTfUrl().setPreferredSize(new Dimension(250, 20));
		panelDireita.add(getTfUrl());
		
		tfId.setEditable(false);

		panelDireita.add(btAtualizaEditora);
		btAtualizaEditora.addActionListener(new TelaAtualizaEditoraController().new onBtAtualizarEditora());

		setVisible(true);
	}
	
	//GETS
	public static synchronized TelaAtualizaEditora getInstance() {
		if (instancia.isDisplayable() == false) {
			instancia = new TelaAtualizaEditora();
		}
		return instancia;
	}
	
	public static Editora getEditoraSelecionada() {// seleciona a linha da tabela e pega os dados dessa linha
		int linhaSelecionada = getTabelaEditoras().getSelectedRow();
		int id = Integer.parseInt(getDtmEditoras().getValueAt(linhaSelecionada, 0).toString());
		String nome = getDtmEditoras().getValueAt(linhaSelecionada, 1).toString();
		String url = getDtmEditoras().getValueAt(linhaSelecionada, 2).toString();
		Editora editora = new Editora(nome, url, id);
		return editora;
	}

	public static JTextField getTfId() {
		return tfId;
	}

	public static JTextField getTfNome() {
		return tfNome;
	}

	public static JTextField getTfUrl() {
		return tfUrl;
	}

	public static DefaultTableModel getDtmEditoras() {
		return dtmEditoras;
	}

	public static JTable getTabelaEditoras() {
		return tabelaEditoras;
	}
}
