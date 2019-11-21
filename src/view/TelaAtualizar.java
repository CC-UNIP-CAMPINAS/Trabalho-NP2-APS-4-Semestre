package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.TelaAtualizarController;

public class TelaAtualizar extends JFrame{
	private JButton btAtualizarLivro;
	private JButton btAtualizarAutor;
	private JButton btAtualizarEditora;
	private static TelaAtualizar instancia;
	
	static {
		instancia = new TelaAtualizar();
	}
	
	private TelaAtualizar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		btAtualizarAutor = new JButton("Atualizar Autor");
		btAtualizarLivro = new JButton("Atualizar Livro");
		btAtualizarEditora = new JButton("Atualizar Editora");
		
		add(btAtualizarAutor);
		add(btAtualizarEditora);
		add(btAtualizarLivro);
		
		btAtualizarAutor.addActionListener(new TelaAtualizarController().new OnBtAtualizarAutor());
		btAtualizarEditora.addActionListener(new TelaAtualizarController().new OnBtAtualizarEditora());
		btAtualizarLivro.addActionListener(new TelaAtualizarController().new OnBtAtualizarLivro());
		
		pack();
		setVisible(true);
	}
	
	public static synchronized TelaAtualizar getInstance() {
		if(instancia.isDisplayable() == false){
			instancia = new TelaAtualizar();
		}
		instancia.setVisible(true);
		return instancia;
	}
}
