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
import view.TelaAtualizaAutor;

public class TelaAtualizaAutorController {

	public class onBtAtualizarAutor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JOptionPane.showConfirmDialog(null, "Deseja mesmo atualizar esse autor?", "Atenção", JOptionPane.CANCEL_OPTION) == 0) {
				if (!(TelaAtualizaAutor.getTfId().getText().isEmpty())) {
					DaoAutor.atualizaAutor(TelaAtualizaAutor.getTfNome(), TelaAtualizaAutor.getTfId(), TelaAtualizaAutor.getTfSobreNome());
					populaTabelaAutores(TelaAtualizaAutor.getDtmAutores());
					JOptionPane.showMessageDialog(null, "Autor atualizado!!");
				} 
				else {
					JOptionPane.showMessageDialog(null, "Selecione um autor primeiro!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Operação cancelada!");
			}
			TelaAtualizaAutor.getTfId().setText("");
			TelaAtualizaAutor.getTfNome().setText("");
			TelaAtualizaAutor.getTfSobreNome().setText("");
			}
	}
	
	public static void populaTabelaAutores(DefaultTableModel dtmAutores) {
		dtmAutores.setNumRows(0);
		for(Autor autor : Colecao.getAutores()) { //percorre a coleção e para cada autor cria um objeto e adiciona na tabela geral
			Object[] data = new Object[3];
			data[1] = autor.getNomeAutor();
			data[2] = autor.getSobreNome();
			data[0] = autor.getIdAutor();
			dtmAutores.addRow(data);
		}
	}
	
	public class selecionaAutor implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Autor autor = TelaAtualizaAutor.getAutorSelecionado();
			TelaAtualizaAutor.getTfSobreNome().setText(autor.getSobreNome().replaceAll("  ", ""));
			TelaAtualizaAutor.getTfNome().setText(autor.getNomeAutor().replaceAll("  ", ""));	
			TelaAtualizaAutor.getTfId().setText(String.valueOf(autor.getIdAutor()));	
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
