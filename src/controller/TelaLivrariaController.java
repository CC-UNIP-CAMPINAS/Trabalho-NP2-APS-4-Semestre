package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.collection.Colecao;
import model.dataBase.dao.DaoLivro;
import view.TelaAtualizar;
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
				if(DaoLivro.buscarLivro(TelaLivraria.SearchTextField,"BOOKS", orderByType())) {
					TableController.pesquisar(TelaLivraria.tabela.getModelo());
				}
				else {
					JOptionPane.showMessageDialog(null, "Nenhum dado encontrado!");
				}
				
			}
			if(TelaLivraria.isAuthor.isSelected()) {
				if(DaoLivro.buscarLivro(TelaLivraria.SearchTextField,"AUTHOR",orderByType())) {
					TableController.pesquisar(TelaLivraria.tabela.getModelo());
				}
				else {
					JOptionPane.showMessageDialog(null, "Nenhum dado encontrado!");
				}
			}
			if(TelaLivraria.isPublisher.isSelected()) {
				if(DaoLivro.buscarLivro(TelaLivraria.SearchTextField,"PUBLISHER", orderByType())) {
					TableController.pesquisar(TelaLivraria.tabela.getModelo());
				}
				else {
					JOptionPane.showMessageDialog(null, "Nenhum dado encontrado!");
				}	
			}
			if(TelaLivraria.isAll.isSelected()) {
				if(DaoLivro.buscarLivro(TelaLivraria.SearchTextField,"ALL", orderByType())) {
					TableController.pesquisar(TelaLivraria.tabela.getModelo());
				}
				else {
					JOptionPane.showMessageDialog(null, "Nenhum dado encontrado!");
				}
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
			TelaExcluir.getInstance().show();;//chama outra tela
		}
	}
	
	public class onBtModificar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaAtualizar.getInstance().show();//chama outra tela
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
