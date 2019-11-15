package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Livro;
import model.dataBase.dao.DaoLivro;
import view.TelaExcluiLivro;

//classe responsavel em controlar a TelaCriaAutor
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaExcluiLivroController {

	public class OnBtBuscarLivro implements ActionListener {
		public void actionPerformed(ActionEvent e) {// chama o metodo de criação dentro de DaoAutor, esse método faz
													// acesso ao banco, por isso está em dao
			TelaExcluiLivro.getDtmLivros().setNumRows(0);
			if (DaoLivro.buscarLivro(TelaExcluiLivro.getTfNome())) {
				populaTabelaLivros(TelaExcluiLivro.getDtmLivros());
				TelaExcluiLivro.getTfNome().setText("");
			} else {
				TelaExcluiLivro.getTfNome().setText("");
				JOptionPane.showMessageDialog(null, "Nenhum dado encontrado!");
			}
		}
	}

	public class OnBtExcluiLivro implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir um livro?", "Atenção", JOptionPane.CANCEL_OPTION) == 0) {
				if (DaoLivro.excluiLivro(TelaExcluiLivro.getTabelaLivrosSelecionados()) > 0) {
					TelaExcluiLivro.getDtmLivrosSelecionados().setNumRows(0);
					JOptionPane.showMessageDialog(null, "Livro excluído!");
				} else {
					JOptionPane.showMessageDialog(null, "Tabela de exclus�o est� vazia!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Operação cancelada!");
				TelaExcluiLivro.getDtmLivrosSelecionados().setRowCount(0);
				TelaExcluiLivro.getDtmLivros().setRowCount(0);				
			}
			TelaExcluiLivro.getTfNome().setText("");
		}	
	}

	public static void populaTabelaLivros(DefaultTableModel dtmLivros) {
		for (Livro livro : Colecao.getLivrosTemporario()) { 
			Object[] data = new Object[4];
			data[0] = livro.getTitulo();
			data[1] = livro.getIsbn();
			data[2] = livro.getEditora();
			data[3] = livro.getPreco();
			dtmLivros.addRow(data);
		}
	}

	public static void populaTabelaLivrosSelecionados(DefaultTableModel dtmLivrosSelecionados, Livro livro) {
		Object[] data = new Object[4];
		data[0] = livro.getTitulo();
		data[1] = livro.getIsbn();
		data[2] = livro.getEditora();
		data[3] = livro.getPreco();
		dtmLivrosSelecionados.addRow(data);
	}

	public class SelecionaLivro implements MouseListener {
		public int escolha;

		public SelecionaLivro(int aEscolha) {
			escolha = aEscolha;
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (escolha == 0) {// se a tabela selecionada for a geral ela manda sinal 0 e esse if acontece
				populaTabelaLivrosSelecionados(TelaExcluiLivro.getDtmLivrosSelecionados(),
						TelaExcluiLivro.getLivroSelecionado());
				TelaExcluiLivro.getDtmLivros().removeRow(TelaExcluiLivro.getTabelaLivros().getSelectedRow());
				System.out.println(TelaExcluiLivro.getTabelaLivros().getRowCount());

			} else {// se não foi escolhido a outra tabela e esse else acontece
				Object[] data = new Object[4];
				data[0] = TelaExcluiLivro.getLivroSelecionadoInverso().getTitulo();
				data[1] = TelaExcluiLivro.getLivroSelecionadoInverso().getIsbn();
				data[2] = TelaExcluiLivro.getLivroSelecionadoInverso().getEditora();
				data[3] = TelaExcluiLivro.getLivroSelecionadoInverso().getPreco();
				TelaExcluiLivro.getDtmLivros().addRow(data);
				TelaExcluiLivro.getDtmLivrosSelecionados().removeRow(TelaExcluiLivro.getTabelaLivrosSelecionados().getSelectedRow());
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
