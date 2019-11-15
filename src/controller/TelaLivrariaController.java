package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.collection.Colecao;
import model.dataBase.dao.DaoLivro;
import view.TableContent;
import view.TelaCriaAutor;
import view.TelaCriaEditora;
import view.TelaCriaLivro;
import view.TelaExcluir;
import view.TelaLivraria;

//classe responsavel em controlar a TelaLivraria(Tela principal)
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaLivrariaController{
	
	public class onBtBuscar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {		
			if(TelaLivraria.isBook.isSelected()) {
				DaoLivro.buscarLivro(TelaLivraria.SearchTextField,"BOOKS", orderByType());
				TableContent.pesquisar(TelaLivraria.tabela.getModelo());
			}
			if(TelaLivraria.isAuthor.isSelected()) {
				DaoLivro.buscarLivro(TelaLivraria.SearchTextField,"AUTHOR",orderByType());
				TableContent.pesquisar(TelaLivraria.tabela.getModelo());
			}
			if(TelaLivraria.isPublisher.isSelected()) {
				DaoLivro.buscarLivro(TelaLivraria.SearchTextField,"PUBLISHER", orderByType());
				TableContent.pesquisar(TelaLivraria.tabela.getModelo());
			}
			if(TelaLivraria.isAll.isSelected()) {
				DaoLivro.buscarLivro(TelaLivraria.SearchTextField,"ALL", orderByType());
				TableContent.pesquisar(TelaLivraria.tabela.getModelo());
			}
		}
	}
	

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
	
	public String orderByType() {
		if(TelaLivraria.byAz.isSelected()) {
			return "AZ";
		}
		if(TelaLivraria.byZa.isSelected()) {
			return "ZA";
		}
		if(TelaLivraria.byHprice.isSelected()) {
			return "HPRICE";
		}
		if(TelaLivraria.byLprice.isSelected()) {
			return "LPRICE";
		}		
		return "AZ";
	}
}
