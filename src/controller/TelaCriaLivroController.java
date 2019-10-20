package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.dataBase.dao.DaoLivro;
import view.TelaCriaEditora;
import view.TelaCriaLivro;

//classe responsavel em controlar a TelaCriaLivro
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaCriaLivroController{
	
	//ação do botão
	public class onBtCriarLivro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoLivro, esse método faz acesso ao banco, por isso está em dao
			DaoLivro.criaLivro(TelaCriaLivro.tfTitle, TelaCriaLivro.tfIsbn, TelaCriaLivro.tfPublisherId, TelaCriaLivro.tfPrice);
		}
		
	}
	
}