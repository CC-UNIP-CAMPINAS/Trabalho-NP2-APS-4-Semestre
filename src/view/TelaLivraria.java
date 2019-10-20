package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.TelaLivrariaController;

public class TelaLivraria extends JFrame{
	
	JButton btCriarAutor = new JButton("Criar Autor");
	JButton btCriarEditora = new JButton("Criar Editora");
	JButton btCriarLivro = new JButton("Criar Livro");
	
	public TelaLivraria() {
		setVisible(true);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Livraria");
		setLayout(new FlowLayout());
		
		add(btCriarAutor);
		btCriarAutor.addActionListener(new TelaLivrariaController().new onBtCriarAutor());
		
		add(btCriarEditora);
		btCriarEditora.addActionListener(new TelaLivrariaController().new onBtCriarEditora());
		
		add(btCriarLivro);
		btCriarLivro.addActionListener(new TelaLivrariaController().new onBtCriarLivro());
	}
}
