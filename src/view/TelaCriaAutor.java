package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.TelaCriaAutorController;

public class TelaCriaAutor extends JFrame {

	private JButton btCriarAutor = new JButton("Criar");
	private static JTextField tfNome = new JTextField();
	private static JTextField tfSobreNome = new JTextField();
	private static DefaultTableModel dtmAutores;
	private static JTable tabelaAutores;
	private static TelaCriaAutor instancia;

	static {
		instancia = new TelaCriaAutor();
	}

	private TelaCriaAutor() {
		setVisible(true);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		setLocationRelativeTo(null);
		setTitle("Autor");

		//TOP
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0, 60));
		panelTop.setBackground(new Color(26, 83, 92));
		add(BorderLayout.PAGE_START, panelTop);

		JLabel labelTitulo = new JLabel();
		labelTitulo.setText("<html><body><h1><span style='color: white;'>Criar Autor</h1></body></html>");
		panelTop.add(labelTitulo);

		//CENTER
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.GREEN);
		panelCentral.setLayout(new BorderLayout());
		add(BorderLayout.CENTER, panelCentral);

		String[] colunas = { "ID", "Autor" };
		Object[][] dados = new Object[0][2];
		dtmAutores = new DefaultTableModel(dados, colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaAutores = new JTable(dtmAutores);
		JScrollPane barraRolagem = new JScrollPane(tabelaAutores);
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Autores Cadastrados"));
		panelCentral.add(barraRolagem);
		TelaCriaAutorController.populaTabelaAutores(dtmAutores);

		//DIREITA
		JPanel panelDireita = new JPanel();
		panelDireita.setPreferredSize(new Dimension(250, 0));
		panelDireita.setBackground(new Color(78, 205, 196));
		add(BorderLayout.LINE_END, panelDireita);

		JLabel labelNome = new JLabel("Nome do autor:");
		tfNome.setPreferredSize(new Dimension(200, 20));
		panelDireita.add(labelNome);
		panelDireita.add(tfNome);

		JLabel labelSobreNome = new JLabel("Segundo nome do autor:");
		tfSobreNome.setPreferredSize(new Dimension(200, 20));
		panelDireita.add(labelSobreNome);
		panelDireita.add(tfSobreNome);

		panelDireita.add(btCriarAutor);
		btCriarAutor.addActionListener(new TelaCriaAutorController().new onBtCriarAutor());
	}

	//GETS

	public static synchronized TelaCriaAutor getInstance() {
		if (instancia.isDisplayable() == false) {
			instancia = new TelaCriaAutor();
		}
		return instancia;
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
}
