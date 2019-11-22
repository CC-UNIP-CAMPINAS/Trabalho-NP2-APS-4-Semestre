package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.TelaCriaEditoraController;

public class TelaCriaEditora extends JFrame{
	private JButton btCriarEditora = new JButton("Criar");
	private static JTextField tfNome = new JTextField();
	private static JTextField tfUrl = new JTextField();
	private static DefaultTableModel dtmEditoras;
	private static JTable tabelaEditoras;
	private static TelaCriaEditora instancia;
	
	static {
		instancia = new TelaCriaEditora();
	}
	
	private TelaCriaEditora() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Editora");
		
		//TOP
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0, 60));
		panelTop.setBackground(new Color(26, 83, 92));
		add(BorderLayout.PAGE_START, panelTop);
		
		JLabel labelTitulo = new JLabel();
		labelTitulo.setText("<html><body><h1><span style='color: white;'>Criar Editora</h1></body></html>");
		panelTop.add(labelTitulo);
		
		//CENTER
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.GREEN);
		panelCentral.setLayout(new BorderLayout());
		add(BorderLayout.CENTER, panelCentral);
		
		String[] colunas = { "ID", "Editoras", "Site" };
		Object[][] dados = new Object[0][3];
		dtmEditoras = new DefaultTableModel(dados, colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Isso faz a celula da tabela não ser editavel
			}
		};
		tabelaEditoras = new JTable(dtmEditoras);
		JScrollPane barraRolagem = new JScrollPane(tabelaEditoras);
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Editoras Cadastradas"));
		panelCentral.add(barraRolagem);
		TelaCriaEditoraController.populaTabelaEditora(dtmEditoras);
		
		//DIREITA
		JPanel panelDireita = new JPanel();
		panelDireita.setPreferredSize(new Dimension(250, 0));
		panelDireita.setBackground(new Color(78, 205, 196));
		add(BorderLayout.LINE_END, panelDireita);
		
		JLabel labelNome = new JLabel(); 		
		labelNome.setText("Nome Editora:");
		tfNome.setPreferredSize(new Dimension(200,20));
		panelDireita.add(labelNome);
		panelDireita.add(tfNome);
		
		JLabel labelSite = new JLabel(); 		
		labelSite.setText("Site da Editora:");
		tfUrl.setPreferredSize(new Dimension(200,20));
		panelDireita.add(labelSite);
		panelDireita.add(tfUrl);
		
		panelDireita.add(btCriarEditora);
		btCriarEditora.addActionListener(new TelaCriaEditoraController().new OnBtCriarEditora());
	
		setVisible(true);
	}

	//GETS
	
	public static TelaCriaEditora getInstance() {
		if(instancia.isDisplayable() == false){
			instancia = new TelaCriaEditora();
		}
		return instancia;
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
}

