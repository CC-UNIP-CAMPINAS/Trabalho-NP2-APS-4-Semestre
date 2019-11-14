package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TelaCriaAutor;
import view.TelaCriaEditora;
import view.TelaCriaLivro;
import view.TelaExcluir;

//classe responsavel em controlar a TelaLivraria(Tela principal)
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaLivrariaController{
	
	

	//Ação do botão para criar autor
	public class onBtCriarAutor implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaCriaAutor.getInstance().show();//chama outra tela
		}
	}
	
	//Ação do botão para criar Editora
	public class onBtCriarEditora implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaCriaEditora.getInstance().show();//chama outra tela
		}
	}
	
	//Ação do botão para criar Livro
	public class onBtCriarLivro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				TelaCriaLivro.getInstance().show();
				
		}
	}
	
	public class onBtExcluir implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaExcluir.getInstance();//chama outra tela
		}
	}
}
