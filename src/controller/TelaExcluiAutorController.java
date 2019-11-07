package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Autor;
import model.dataBase.dao.DaoAutor;
import view.TelaCriaAutor;
import view.TelaCriaLivro;
import view.TelaExcluiAutor;

//classe responsavel em controlar a TelaCriaAutor
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaExcluiAutorController {
	
	public class onBtBuscarAutor implements ActionListener{
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoAutor, esse método faz acesso ao banco, por isso está em dao
			TelaExcluiAutor.dtmAutores.setNumRows(0);
			DaoAutor.buscarAutor(TelaExcluiAutor.tfNome);
			populaTabelaAutores(TelaExcluiAutor.dtmAutores);
		}
	}
	
	public static void populaTabelaAutores(DefaultTableModel dtmAutores) {
		for(Autor autor : Colecao.getAutoresTemporario()) { //percorre a coleção e para cada autor cria um objeto e adiciona na tabela geral
			Object[] data = new Object[2];
			data[1] = autor.getNomeAutor();
			data[0] = autor.getIdAutor();
			dtmAutores.addRow(data);
			System.out.println(autor);
		}
	}
	
	public class selecionaAutor implements MouseListener{
		public int escolha;
		public selecionaAutor(int aEscolha) {
			escolha = aEscolha;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(escolha == 0) {// se a tabela selecionada for a geral ela manda sinal 0 e esse if acontece
				populaTabelaAutoresSelecionados(TelaCriaLivro.dtmAutoresSelecionados, TelaCriaLivro.getAutorSelecionado());	
				TelaCriaLivro.dtmAutores.removeRow(TelaCriaLivro.tabelaAutores.getSelectedRow());
				System.out.println(TelaCriaLivro.tabelaAutores.getRowCount());
				
			}
			else {//se não foi escolhido a outra tabela e esse else acontece
				Object[] data = new Object[2];
				data[1] = TelaCriaLivro.getAutorSelecionadoInverso().getNomeAutor();
				data[0] = TelaCriaLivro.getAutorSelecionadoInverso().getIdAutor();
				TelaCriaLivro.dtmAutores.addRow(data);	
				TelaCriaLivro.dtmAutoresSelecionados.removeRow(TelaCriaLivro.tabelaAutoresSelecionados.getSelectedRow());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
	}
}
