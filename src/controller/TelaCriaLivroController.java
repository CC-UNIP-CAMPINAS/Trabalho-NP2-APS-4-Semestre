package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Autor;
import model.dataBase.dao.DaoLivro;
import view.TelaCriaLivro;

//classe responsavel em controlar a TelaCriaLivro
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaCriaLivroController{
	
	//ação do botão
	public class OnBtCriarLivro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoLivro, esse método faz acesso ao banco, por isso está em dao
			if(TelaCriaLivro.getTfIsbn().getText().isEmpty() || TelaCriaLivro.getTfPrice().getText().isEmpty() || TelaCriaLivro.getTfTitle().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			}
			else {
				if(JOptionPane.showConfirmDialog(null, "Deseja mesmo criar uma livro?", "Atenção", JOptionPane.CANCEL_OPTION) == 0) {
					if(TelaCriaLivro.getTabelaAutoresSelecionados().getRowCount()>0) {
							DaoLivro.criaLivro(TelaCriaLivro.getTfTitle(), TelaCriaLivro.getTfIsbn(), TelaCriaLivro.getTfPrice(), TelaCriaLivro.getCbEditora(), TelaCriaLivro.getTabelaAutoresSelecionados());	
					}
					else {
						JOptionPane.showMessageDialog(null, "Nenhum autor selecionado!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
				}
			}
			TelaCriaLivro.getTfIsbn().setText("");
			TelaCriaLivro.getTfTitle().setText("");
			TelaCriaLivro.getTfPrice().setText("");
			TelaCriaLivro.getDtmAutoresSelecionados().setRowCount(0);
			TelaCriaLivro.getDtmAutores().setRowCount(0);
			TelaCriaLivroController.populaTabelaAutores(TelaCriaLivro.getDtmAutores());
		}
	}
	
	//ação do mouse
	public class SelecionaAutor implements MouseListener{
		public int escolha;
		public SelecionaAutor(int aEscolha) {
			escolha = aEscolha;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(escolha == 0) {// se a tabela selecionada for a geral ela manda sinal 0 e esse if acontece
				populaTabelaAutoresSelecionados(TelaCriaLivro.getDtmAutoresSelecionados(), TelaCriaLivro.getAutorSelecionado());	
				TelaCriaLivro.getDtmAutores().removeRow(TelaCriaLivro.getTabelaAutores().getSelectedRow());
				System.out.println(TelaCriaLivro.getTabelaAutores().getRowCount());
				
			}
			else {//se não foi escolhido a outra tabela e esse else acontece
				Object[] data = new Object[3];
				data[1] = TelaCriaLivro.getAutorSelecionadoInverso().getNomeAutor();
				data[2] = TelaCriaLivro.getAutorSelecionadoInverso().getSobreNome();
				data[0] = TelaCriaLivro.getAutorSelecionadoInverso().getIdAutor();
				TelaCriaLivro.getDtmAutores().addRow(data);	
				TelaCriaLivro.getDtmAutoresSelecionados().removeRow(TelaCriaLivro.getTabelaAutoresSelecionados().getSelectedRow());
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
	
	public static void populaTabelaAutores(DefaultTableModel dtmAutores) {
		for(Autor autor : Colecao.getAutores()) { //percorre a coleção e para cada autor cria um objeto e adiciona na tabela geral
			Object[] data = new Object[3];
			data[1] = autor.getNomeAutor();
			data[2] = autor.getSobreNome();
			data[0] = autor.getIdAutor();
			dtmAutores.addRow(data);
		}
	}
	
	public static void populaTabelaAutoresSelecionados(DefaultTableModel dtmAutoresSelecionados, Autor autor) {	//Cria um objeto que ele pega no parametro e adiciona como linha na segunda tabela
			Object[] data = new Object[3];
			data[1] = autor.getNomeAutor();
			data[2] = autor.getSobreNome();
			data[0] = autor.getIdAutor();
			dtmAutoresSelecionados.addRow(data);
	}
}