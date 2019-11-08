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
	}
	
	private TelaExcluir() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		btExcluirAutor = new JButton("Excluir Autor");
		btExcluirLivro = new JButton("Excluir Livro");
		btExcluirEditora = new JButton("Excluir Editora");
		
		add(btExcluirAutor);
		add(btExcluirEditora);
		add(btExcluirLivro);
		
		btExcluirAutor.addActionListener(new TelaExcluirController().new onBtExcluirAutor());
		btExcluirEditora.addActionListener(new TelaExcluirController().new onBtExcluirEditora());
		
		pack();
		setVisible(true);
	}
	
	public static synchronized TelaExcluir getInstance() {
		if(instancia.isDisplayable() == false){
			instancia = new TelaExcluir();
		}
		return instancia;
	}
}
