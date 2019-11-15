package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TelaCriaLivro;
import view.TelaExcluiAutor;
import view.TelaExcluiEditora;
import view.TelaExcluiLivro;
import view.TelaExcluir;

//classe responsavel em controlar a TelaLivraria(Tela principal)
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaExcluirController{
	
	//Ação do botão para criar autor
	public class OnBtExcluirAutor implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaExcluiAutor.getInstance().show();
			TelaExcluir.getInstance().setVisible(false);
		}
	}
	
	//Ação do botão para criar Editora
	public class OnBtExcluirEditora implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaExcluiEditora.getInstance().show();
			TelaExcluir.getInstance().setVisible(false);
		}
	}
	
	//Ação do botão para criar Livro
	public class OnBtExcluirLivro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				TelaExcluiLivro.getInstance().show();
				TelaExcluir.getInstance().setVisible(false);
		}
	}
}
