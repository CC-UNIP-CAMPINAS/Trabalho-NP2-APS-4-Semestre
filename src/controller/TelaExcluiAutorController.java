package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Autor;
import model.dataBase.dao.DaoAutor;
import view.TelaExcluiAutor;

//classe responsavel em controlar a TelaCriaAutor
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaExcluiAutorController {
	
	public class onBtBuscarAutor implements ActionListener{
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoAutor, esse método faz acesso ao banco, por isso está em dao	
			TelaExcluiAutor.dtmAutores.setNumRows(0);
			if(DaoAutor.buscarAutor(TelaExcluiAutor.tfNome)) {
				populaTabelaAutores(TelaExcluiAutor.dtmAutores);
				TelaExcluiAutor.tfNome.setText("");
			}
			else {
				TelaExcluiAutor.tfNome.setText("");
				JOptionPane.showMessageDialog(null, "Nenhum dado encontrado!");
			}
		}
	}
	
	public class onBtExcluiAutor implements ActionListener{
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoAutor, esse método faz acesso ao banco, por isso está em dao
			if(DaoAutor.excluiAutor(TelaExcluiAutor.tabelaAutoresSelecionados) > 0) {
				TelaExcluiAutor.dtmAutoresSelecionados.setNumRows(0);
			}
			else {
				JOptionPane.showMessageDialog(null, "Tabela de exclus�o est� vazia!");
			}
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
	
	public static void populaTabelaAutoresSelecionados(DefaultTableModel dtmAutoresSelecionados, Autor autor) {	//Cria um objeto que ele pega no parametro e adiciona como linha na segunda tabela
		Object[] data = new Object[2];
		data[1] = autor.getNomeAutor();
		data[0] = autor.getIdAutor();
		dtmAutoresSelecionados.addRow(data);
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
				populaTabelaAutoresSelecionados(TelaExcluiAutor.dtmAutoresSelecionados, TelaExcluiAutor.getAutorSelecionado());	
				TelaExcluiAutor.dtmAutores.removeRow(TelaExcluiAutor.tabelaAutores.getSelectedRow());
				System.out.println(TelaExcluiAutor.tabelaAutores.getRowCount());
				
			}
			else {//se não foi escolhido a outra tabela e esse else acontece
				Object[] data = new Object[2];
				data[1] = TelaExcluiAutor.getAutorSelecionadoInverso().getNomeAutor();
				data[0] = TelaExcluiAutor.getAutorSelecionadoInverso().getIdAutor();
				TelaExcluiAutor.dtmAutores.addRow(data);	
				TelaExcluiAutor.dtmAutoresSelecionados.removeRow(TelaExcluiAutor.tabelaAutoresSelecionados.getSelectedRow());
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
