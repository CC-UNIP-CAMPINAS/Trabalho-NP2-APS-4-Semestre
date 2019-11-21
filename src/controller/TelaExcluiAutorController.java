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

	public class onBtBuscarAutor implements ActionListener {
		public void actionPerformed(ActionEvent e) {// chama o metodo de criação dentro de DaoAutor, esse método faz
													// acesso ao banco, por isso está em dao
			TelaExcluiAutor.getDtmAutores().setNumRows(0);
			if (DaoAutor.buscarAutor(TelaExcluiAutor.getTfNome())) {
				populaTabelaAutores(TelaExcluiAutor.getDtmAutores());
				TelaExcluiAutor.getTfNome().setText("");
			} else {
				TelaExcluiAutor.getTfNome().setText("");
				JOptionPane.showMessageDialog(null, "Nenhum dado encontrado!");
			}
		}
	}

	public class onBtExcluiAutor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir um autor?\nLivros associados a esse autor serão excluídos!", "Atenção", JOptionPane.CANCEL_OPTION) == 0) {
				if (DaoAutor.excluiAutor(TelaExcluiAutor.getTabelaAutoresSelecionados()) > 0) {
					TelaExcluiAutor.getDtmAutoresSelecionados().setNumRows(0);
					JOptionPane.showMessageDialog(null, "Autor excluído!");
				} 
				else {
					JOptionPane.showMessageDialog(null, "Tabela de exclus�o est� vazia!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Operação cancelada!");
				TelaExcluiAutor.getDtmAutoresSelecionados().setRowCount(0);
				TelaExcluiAutor.getDtmAutores().setRowCount(0);
			}
			TelaExcluiAutor.getTfNome().setText("");		}
	}

	public static void populaTabelaAutores(DefaultTableModel dtmAutores) {
		for (Autor autor : Colecao.getAutoresTemporario()) {
			Object[] data = new Object[3];
			data[1] = autor.getNomeAutor();
			data[2] = autor.getSobreNome();
			data[0] = autor.getIdAutor();
			dtmAutores.addRow(data);
			System.out.println(autor);
		}
	}

	public static void populaTabelaAutoresSelecionados(DefaultTableModel dtmAutoresSelecionados, Autor autor) { 																								// tabela
		Object[] data = new Object[3];
		data[1] = autor.getNomeAutor();
		data[2] = autor.getSobreNome();
		data[0] = autor.getIdAutor();
		dtmAutoresSelecionados.addRow(data);
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
				populaTabelaAutoresSelecionados(TelaExcluiAutor.getDtmAutoresSelecionados(),
						TelaExcluiAutor.getAutorSelecionado());
				TelaExcluiAutor.getDtmAutores().removeRow(TelaExcluiAutor.getTabelaAutores().getSelectedRow());
				System.out.println(TelaExcluiAutor.getTabelaAutores().getRowCount());

			} else {// se não foi escolhido a outra tabela e esse else acontece
				Object[] data = new Object[3];
				data[1] = TelaExcluiAutor.getAutorSelecionadoInverso().getNomeAutor();
				data[2] = TelaExcluiAutor.getAutorSelecionadoInverso().getSobreNome();
				data[0] = TelaExcluiAutor.getAutorSelecionadoInverso().getIdAutor();
				TelaExcluiAutor.getDtmAutores().addRow(data);
				TelaExcluiAutor.getDtmAutoresSelecionados().removeRow(TelaExcluiAutor.getTabelaAutoresSelecionados().getSelectedRow());
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
