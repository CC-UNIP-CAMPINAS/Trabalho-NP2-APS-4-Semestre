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
import model.collection.entities.Autor;

public class TelaAtualizaAutor extends JFrame {
	private JButton btAtualizaAutor = new JButton("Atualizar");
	private static JTextField tfId = new JTextField();
	private static JTextField tfNome = new JTextField();
	private static JTextField tfSobreNome = new JTextField();
	private static DefaultTableModel dtmAutores;
	private static JTable tabelaAutores;
	private static TelaAtualizaAutor instancia;

	static {
		instancia = new TelaAtualizaAutor();
	}
	
	private TelaAtualizaAutor() {
		setSize(1250, 550);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Atualiza Autor");
		setLocationRelativeTo(null);

		// TOP
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0, 60));
		panelTop.setBackground(new Color(26, 83, 92));
		add(BorderLayout.PAGE_START, panelTop);

		JLabel labelTitulo = new JLabel();
		labelTitulo.setText("<html><body><h1><span style='color: white;'>Atualizar Autor</h1></body></html>");
		panelTop.add(labelTitulo);

		// CENTER
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1, 1));
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
		getTabelaAutores().addMouseListener(new TelaAtualizaAutorController().new selecionaAutor());
		TelaAtualizaAutorController.populaTabelaAutores(dtmAutores);

		// DIREITA
		JPanel panelDireita = new JPanel();
		panelDireita.setPreferredSize(new Dimension(300, 0));
		panelDireita.setBackground(new Color(78, 205, 196));
		panelDireita.setLayout(new FlowLayout());
		add(BorderLayout.LINE_END, panelDireita);
		
		JLabel labelIdAutor = new JLabel("ID do autor:");
		panelDireita.add(labelIdAutor);
		getTfId().setPreferredSize(new Dimension(250, 20));
		panelDireita.add(getTfId());
		
		JLabel labelNomeAutor = new JLabel("Nome do autor:");
		panelDireita.add(labelNomeAutor);
		getTfNome().setPreferredSize(new Dimension(250, 20));
		panelDireita.add(getTfNome());
		
		JLabel labelSobreNomeAutor = new JLabel("Sobrenome do autor:");
		panelDireita.add(labelSobreNomeAutor);
		getTfSobreNome().setPreferredSize(new Dimension(250, 20));
		panelDireita.add(getTfSobreNome());
		
		tfId.setEditable(false);

		panelDireita.add(btAtualizaAutor);
		btAtualizaAutor.addActionListener(new TelaAtualizaAutorController().new onBtAtualizarAutor());

		setVisible(true);
	}
	
	//GETS
	public static synchronized TelaAtualizaAutor getInstance() {
		if (instancia.isDisplayable() == false) {
			instancia = new TelaAtualizaAutor();
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

	public static JTextField getTfId() {
		return tfId;
	}

	public static JTextField getTfNome() {
		return tfNome;
	}

	public static JTextField getTfSobreNome() {
		return tfSobreNome;
	}

	public static DefaultTableModel getDtmAutores() {
		return dtmAutores;
	}

	public static JTable getTabelaAutores() {
		return tabelaAutores;
	}

	
}
