package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TelaCriaAutor;
import view.TelaCriaEditora;
import view.TelaCriaLivro;
import view.TelaExcluiAutor;

//classe responsavel em controlar a TelaLivraria(Tela principal)
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaLivrariaController{
	
	

	//Ação do botão para criar autor
	public class onBtCriarAutor implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			new TelaCriaAutor();//chama outra tela
		}
	}
	
	//Ação do botão para criar Editora
	public class onBtCriarEditora implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			new TelaCriaEditora();//chama outra tela
		}
	}
	
	//Ação do botão para criar Livro
	public class onBtCriarLivro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				TelaCriaLivro.getInstance();
				
		}
	}
	
	public class onBtExcluirAutor implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			new TelaExcluiAutor();//chama outra tela
		}
	}
}
