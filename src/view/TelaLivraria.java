package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.TelaLivrariaController; 

public class TelaLivraria extends JFrame{
	public static JButton btnAddBook;
	public static TableContent tabela = new TableContent();
	public static JTextField SearchTextField;
	//Select From
	public static JCheckBox isBook;
	public static JCheckBox isAuthor;
	public static JCheckBox isPublisher;
	public static JCheckBox isAll;
	//Order By
	public static JCheckBox byAz;
	public static JCheckBox byZa;
	public static JCheckBox byHprice; 
	public static JCheckBox byLprice;
	
	public TelaLivraria() {
		setSize(1024, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Livraria");
		setLayout(new BorderLayout());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setBackground(Color.decode("#E8E8E8"));
		
		//-------Header-------------------------------------------------------------------
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(800, 100));
		p1.setBackground(Color.decode("#1A535C"));
		
		JLabel l = new JLabel(); 
		//https://cdn2.iconfinder.com/data/icons/education-and-science-5/64/education_science_tree_of_knowledge-512.png
		l.setText("<html><body><h1><span style='color: white;'>Livraria<span><br>&nbsp&nbsp&nbsp&nbsp&nbsp Amazonia</h1></body></html>"); 
		p1.add(l,BorderLayout.LINE_START);
		
		//--------Sidebar------------------------------------------------------------------
		JPanel sidebar = new JPanel();
		sidebar.setPreferredSize(new Dimension(300, 300));
		sidebar.setBackground(Color.decode("#4ECDC4"));
		GridBagConstraints gbc = new GridBagConstraints();
		sidebar.setLayout(new GridBagLayout());
		
		gbc.insets = new Insets(2,10,2,5);
		SearchTextField = new JTextField(20);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		sidebar.add(SearchTextField, gbc);
	
		gbc.insets = new Insets(2,5,2,10);
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setBackground(Color.decode("#1A535C"));
		btnSearch.setForeground(Color.decode("#F7FFF7"));
		btnSearch.addActionListener(new TelaLivrariaController().new onBtBuscar());
		gbc.gridx = 2;
		gbc.gridy = 0;
		sidebar.add(btnSearch, gbc);
		//Inicializando os CheckBox Select From
		isBook = new JCheckBox("Livros");
		isAuthor = new JCheckBox("Autores");
		isPublisher = new JCheckBox("Editoras");
		isAll = new JCheckBox("Todos", true);
		//Inicializando os CheckBox Order By
		byZa = new JCheckBox("Titulo Z-A");
		byAz = new JCheckBox("Titulo A-Z", true);
		byHprice = new JCheckBox("Maior Pre�o");
		byLprice = new JCheckBox("Menor Pre�o");
		//Definindo a cor de fundo dos checkbox
		isBook.setBackground(Color.decode("#4ECDC4"));
		isAuthor.setBackground(Color.decode("#4ECDC4"));
		isPublisher.setBackground(Color.decode("#4ECDC4"));
		isAll.setBackground(Color.decode("#4ECDC4"));
		byAz.setBackground(Color.decode("#4ECDC4"));
		byZa.setBackground(Color.decode("#4ECDC4"));
		byHprice.setBackground(Color.decode("#4ECDC4"));
		byLprice.setBackground(Color.decode("#4ECDC4"));
		//Criando os textos para indicar as funcionalidades dos Checkbox
		JLabel selType = new JLabel(); 		
		selType.setText("Filtrar Por:");
		JLabel orderBy = new JLabel(); 		
		orderBy.setText("Ordenar Por:");
		JLabel filler = new JLabel(); 		
		filler.setText(" ");
		//Criando ButtonGroup para agrupar os JcheckBox
		ButtonGroup GroupSelectFrom= new ButtonGroup();
		ButtonGroup GroupOrderBY = new ButtonGroup();
		GroupSelectFrom.add(isBook);
		GroupSelectFrom.add(isAuthor);
		GroupSelectFrom.add(isPublisher);
		GroupSelectFrom.add(isAll);
		GroupOrderBY.add(byAz);
		GroupOrderBY.add(byZa);
		GroupOrderBY.add(byHprice);
		GroupOrderBY.add(byLprice);
		
		
		//Criando paineis para fixar os JCheckBox
		JPanel panelIs = new JPanel();
		JPanel panelBy = new JPanel();
		panelIs.setPreferredSize(new Dimension(30, 30));
		panelBy.setPreferredSize(new Dimension(30, 30));
		panelIs.setLayout(new BoxLayout(panelIs, BoxLayout.Y_AXIS));
		panelBy.setLayout(new BoxLayout(panelBy, BoxLayout.Y_AXIS));
		panelIs.setBackground(Color.decode("#4ECDC4"));
		panelBy.setBackground(Color.decode("#4ECDC4"));
		panelIs.add(selType);
		panelIs.add(isBook);
		panelIs.add(isAuthor);
		panelIs.add(isPublisher);
		panelIs.add(isAll);
		panelBy.add(filler);
		panelBy.add(orderBy);
		panelBy.add(byAz);
		panelBy.add(byZa);
		panelBy.add(byHprice);
		panelBy.add(byLprice);
		
		gbc.insets = new Insets(2,10,2,5);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.1;
	    sidebar.add(panelIs, gbc);
	    
	    gbc.insets = new Insets(2,5,2,10);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.weightx = 0.1;
	    sidebar.add(panelBy, gbc);
		
		
		
		//Criando outros bot�es - AddLivro, AddEditora, AddAutor, Deletar, Modificar
		gbc.insets = new Insets(2,10,2,5);
	    btnAddBook = new JButton("Adicionar Livro");
	    btnAddBook.setBackground(Color.decode("#1A535C"));
	    btnAddBook.setForeground(Color.decode("#F7FFF7"));
		btnAddBook.addActionListener(new TelaLivrariaController().new onBtCriarLivro());
	    gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0.1;
	    sidebar.add(btnAddBook, gbc);
	    
	    JButton btnAddPublisher = new JButton("Adicionar Editora");
	    btnAddPublisher.setBackground(Color.decode("#1A535C"));
	    btnAddPublisher.setForeground(Color.decode("#F7FFF7"));
		btnAddPublisher.addActionListener(new TelaLivrariaController().new onBtCriarEditora());
	    gbc.gridx = 0;
		gbc.gridy = 4;
	    sidebar.add(btnAddPublisher, gbc);
	    
	    JButton btnAddAutor = new JButton("Adicionar Autor");
	    btnAddAutor.setBackground(Color.decode("#1A535C"));
	    btnAddAutor.setForeground(Color.decode("#F7FFF7"));
		btnAddAutor.addActionListener(new TelaLivrariaController().new onBtCriarAutor());
	    gbc.gridx = 0;
		gbc.gridy = 5;
	    sidebar.add(btnAddAutor, gbc);
		
	    gbc.insets = new Insets(2,5,2,10);
	    JButton btnDelete = new JButton("Deletar");
	    btnDelete.setBackground(Color.decode("#1A535C"));
	    btnDelete.setForeground(Color.decode("#F7FFF7"));
	    btnDelete.addActionListener(new TelaLivrariaController().new onBtExcluir());
	    gbc.gridx = 2;
		gbc.gridy = 3;
	    sidebar.add(btnDelete, gbc);
	    
	    JButton btnModificar = new JButton("Modificar");
	    btnModificar.setBackground(Color.decode("#1A535C"));
	    btnModificar.setForeground(Color.decode("#F7FFF7"));
	    btnModificar.addActionListener(new TelaLivrariaController().new onBtModificar());
	    gbc.gridx = 2;
		gbc.gridy = 4;
	    sidebar.add(btnModificar, gbc);
	    
	    //--------Content---------------------------------------------------------------------
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(800, 300));
		content.setBackground(Color.decode("#F7FFF7"));
		content.setLayout(new BorderLayout());
		
		JScrollPane barraRolagem = new JScrollPane(tabela.getTabela());
		barraRolagem.getViewport().setBackground(Color.decode("#F7FFF7"));
		barraRolagem.setBorder(BorderFactory.createEmptyBorder());
		content.add(barraRolagem);
		content.add(tabela.getTabela());
		
		this.add(p1, BorderLayout.NORTH);
		this.add(sidebar, BorderLayout.EAST);
		this.add(content, FlowLayout.RIGHT);
		setVisible(true);

	}
}
