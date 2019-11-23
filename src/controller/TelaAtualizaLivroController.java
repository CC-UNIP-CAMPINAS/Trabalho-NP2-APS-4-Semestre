package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Autor;
import model.collection.entities.Livro;
import model.dataBase.dao.DaoLivro;
import view.TelaAtualizaLivro;

public class TelaAtualizaLivroController {

	public static int count = 0;
	public class onBtAtualizarLivro implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JOptionPane.showConfirmDialog(null, "Deseja mesmo atualizar esse livro?", "Aten��o", JOptionPane.CANCEL_OPTION) == 0) {
				if (!(TelaAtualizaLivro.getTfIsbn().getText().isEmpty())) {
					DaoLivro.atualizaLivro(TelaAtualizaLivro.getTfTitle(), TelaAtualizaLivro.getTfIsbn(), TelaAtualizaLivro.getTfPrice(), TelaAtualizaLivro.getCbEditora(), TelaAtualizaLivro.getTabelaAutores());
					populaTabelaLivros(TelaAtualizaLivro.tabela);
				} 
				else {
					JOptionPane.showMessageDialog(null, "Selecione um livro primeiro!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Opera��o cancelada!");
			}
			TelaAtualizaLivro.getTfIsbn().setText("");
			TelaAtualizaLivro.getTfPrice().setText("");
			TelaAtualizaLivro.getTfTitle().setText("");
			}
	}
	
	public static void populaTabelaLivros(DefaultTableModel dtmLivros) {
		dtmLivros.setRowCount(0);
		TelaAtualizaLivro.getTfTitle().setText("");
		DaoLivro.buscarLivro(TelaAtualizaLivro.getTfTitle(), "AZ", "ALL");
		for(Livro liv : Colecao.getLivrosTemporario()) { //percorre a coleção e para cada autor cria um objeto e adiciona na tabela geral
			Object[] data = new Object[4];
			data[0] = liv.getIsbn();
			data[1] = liv.getTitulo();
			data[2] = liv.getEditora();
			data[3] = liv.getPreco();
			dtmLivros.addRow(data);
		}
	}
	
	public static void populaTabelaAutores(DefaultTableModel dtmAutores, Autor autor) { 																								// tabela
		Object[] data = new Object[3];
		data[0] = autor.getIdAutor();
		data[1] = autor.getNomeAutor();
		data[2] = autor.getSobreNome();
		dtmAutores.addRow(data);
	}
	
	//Overload
	public static void populaTabelaAutores(DefaultTableModel dtmAutores) {
		for (Autor autor : Colecao.getAutores()) {
			Object[] data = new Object[3];
			data[0] = autor.getIdAutor();
			data[1] = autor.getNomeAutor();
			data[2] = autor.getSobreNome();
			dtmAutores.addRow(data);
		}
	}
	
	public static void populaTabelaAutoresSelecionados(DefaultTableModel dtmAutoresSelecionados, Autor autor) { 																								// tabela
		Object[] data = new Object[3];
		data[0] = autor.getIdAutor();
		data[1] = autor.getNomeAutor();
		data[2] = autor.getSobreNome();
		dtmAutoresSelecionados.addRow(data);
	}
	
	public class SelecionaLivro implements MouseListener {
		
		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			TelaAtualizaLivroController.count = 0;
			TelaAtualizaLivro.getDtmAutoresSelecionados().setRowCount(0);
			populaTabelaAutores(TelaAtualizaLivro.getDtmAutoresSelecionados());
			TelaAtualizaLivro.getDtmAutores().setRowCount(0);
			Livro livro2 = TelaAtualizaLivro.getLivroSelecionado();
			
			TelaAtualizaLivro.getTfIsbn().setText(livro2.getIsbn());
			TelaAtualizaLivro.getTfPrice().setText(String.valueOf(livro2.getPreco()));
			TelaAtualizaLivro.getTfTitle().setText(livro2.getTitulo().replaceAll("  ", ""));
			for (Livro liv : Colecao.getLivros()) {
				if(liv.getIsbn().equals(livro2.getIsbn())) {
					livro2 = liv;
	
				}
			}
			
			for (String autor : livro2.getAutores()) {
				for(Autor autor2 : Colecao.getAutores()) {
					if(autor.replaceAll(" ", "").equals(autor2.getNomeCompleto().replaceAll(" ", ""))) {
						populaTabelaAutores(TelaAtualizaLivro.getDtmAutores(), autor2);	
						TelaAtualizaLivroController.count += 1;
					}	
				}
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
	
	public class selecionaAutor implements MouseListener {
		public int escolha;

		public selecionaAutor(int aEscolha) {
			escolha = aEscolha;
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (escolha == 0) {// se a tabela selecionada for a geral ela manda sinal 0 e esse if acontece
				populaTabelaAutoresSelecionados(TelaAtualizaLivro.getDtmAutoresSelecionados(),
						TelaAtualizaLivro.getAutorSelecionado());
				TelaAtualizaLivro.getDtmAutores().removeRow(TelaAtualizaLivro.getTabelaAutores().getSelectedRow());

			} else {// se não foi escolhido a outra tabela e esse else acontece
				Object[] data = new Object[3];
				data[1] = TelaAtualizaLivro.getAutorSelecionadoInverso().getNomeAutor();
				data[2] = TelaAtualizaLivro.getAutorSelecionadoInverso().getSobreNome();
				data[0] = TelaAtualizaLivro.getAutorSelecionadoInverso().getIdAutor();
				TelaAtualizaLivro.getDtmAutores().addRow(data);
				TelaAtualizaLivro.getDtmAutoresSelecionados().removeRow(TelaAtualizaLivro.getTabelaAutoresSelecionados().getSelectedRow());
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
