package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.TelaExcluirController;

public class TelaExcluir extends JFrame{
	private JButton btExcluirLivro;
	private JButton btExcluirAutor;
	private JButton btExcluirEditora;
	private static TelaExcluir instancia;
	
	static {
		instancia = new TelaExcluir();
		//instancia.setVisible(true);
	}
	
	private TelaExcluir() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		btExcluirAutor = new JButton("Excluir Autor");
		btExcluirLivro = new JButton("Excluir Livro");
		btExcluirEditora = new JButton("Excluir Editora");
		
		add(btExcluirAutor);
		add(btExcluirEditora);
		add(btExcluirLivro);
		
		btExcluirAutor.addActionListener(new TelaExcluirController().new OnBtExcluirAutor());
		btExcluirEditora.addActionListener(new TelaExcluirController().new OnBtExcluirEditora());
		btExcluirLivro.addActionListener(new TelaExcluirController().new OnBtExcluirLivro());
		
		pack();
		setVisible(true);
	}
	
	public static synchronized TelaExcluir getInstance() {
		if(instancia.isDisplayable() == false){
			instancia = new TelaExcluir();
		}
		instancia.setVisible(true);
		return instancia;
	}
}
