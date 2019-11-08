package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TelaCriaLivro;
import view.TelaExcluiAutor;
import view.TelaExcluiEditora;

//classe responsavel em controlar a TelaLivraria(Tela principal)
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaExcluirController{
	
	//Ação do botão para criar autor
	public class onBtExcluirAutor implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaExcluiAutor.getInstance();
		}
	}
	
	//Ação do botão para criar Editora
	public class onBtExcluirEditora implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaExcluiEditora.getInstance();
		}
	}
	
	//Ação do botão para criar Livro
	public class onBtCriarLivro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				TelaCriaLivro.getInstance();
				
		}
	}
}
