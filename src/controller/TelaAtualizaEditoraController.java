package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Editora;
import model.dataBase.dao.DaoEditora;
import view.TelaAtualizaAutor;
import view.TelaAtualizaEditora;

public class TelaAtualizaEditoraController {

	public class onBtAtualizarEditora implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JOptionPane.showConfirmDialog(null, "Deseja mesmo atualizar essa editora?", "Aten��o", JOptionPane.CANCEL_OPTION) == 0) {
				if (!(TelaAtualizaEditora.getTfId().getText().isEmpty())) {
					DaoEditora.atualizaEditora(TelaAtualizaEditora.getTfNome(), TelaAtualizaEditora.getTfId(), TelaAtualizaEditora.getTfUrl());
					populaTabelaEditoras(TelaAtualizaEditora.getDtmEditoras());
					JOptionPane.showMessageDialog(null, "Editora atualizada!!");
				} 
				else {
					JOptionPane.showMessageDialog(null, "Selecione uma editora primeiro!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Operação cancelada!");
			}
			TelaAtualizaEditora.getTfId().setText("");
			TelaAtualizaEditora.getTfNome().setText("");
			TelaAtualizaEditora.getTfUrl().setText("");
			}
	}
	
	public static void populaTabelaEditoras(DefaultTableModel dtmEditoras) {
		dtmEditoras.setNumRows(0);
		for(Editora editora : Colecao.getEditoras()) { //percorre a coleção e para cada autor cria um objeto e adiciona na tabela geral
			Object[] data = new Object[3];
			data[1] = editora.getNomeEditora();
			data[2] = editora.getUrl();
			data[0] = editora.getIdEditora();
			dtmEditoras.addRow(data);
		}
	}
	
	public class selecionaEditora implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Editora editora = TelaAtualizaEditora.getEditoraSelecionada();
			TelaAtualizaEditora.getTfUrl().setText(editora.getUrl().replaceAll(" ", ""));
			TelaAtualizaEditora.getTfNome().setText(editora.getNomeEditora().replaceAll("  ", ""));	
			TelaAtualizaEditora.getTfId().setText(String.valueOf(editora.getIdEditora()));	
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
