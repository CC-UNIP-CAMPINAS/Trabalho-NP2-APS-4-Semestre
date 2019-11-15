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
import view.TelaExcluiEditora;

//classe responsavel em controlar a TelaExcluiEditora
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaExcluiEditoraController {

	public class onBtBuscarEditora implements ActionListener {
		public void actionPerformed(ActionEvent e) {// chama o metodo de criação dentro de DaoAutor, esse método faz
													// acesso ao banco, por isso está em dao
			TelaExcluiEditora.getDtmEditora().setNumRows(0);
			if (DaoEditora.buscarEditora(TelaExcluiEditora.getTfNome())) {
				populaTabelaEditora(TelaExcluiEditora.getDtmEditora());
				TelaExcluiEditora.getTfNome().setText("");
			} else {
				TelaExcluiEditora.getTfNome().setText("");
				JOptionPane.showMessageDialog(null, "Nenhum dado encontrado!");
			}
		}
	}

	public class onBtExcluiEditora implements ActionListener {
		public void actionPerformed(ActionEvent e) {// chama o metodo de criação dentro de DaoAutor, esse método faz
													// acesso ao banco, por isso está em dao
			if (JOptionPane.showConfirmDialog(null,
					"Deseja mesmo excluir uma editora?\nTodo livro associado a esta editora será excluído!", "Atenção", JOptionPane.CANCEL_OPTION) == 0) {
				if (DaoEditora.excluiEditora(TelaExcluiEditora.getTabelaEditorasSelecionadas()) > 0) {
					TelaExcluiEditora.getDtmEditorasSelecionadas().setNumRows(0);
					JOptionPane.showMessageDialog(null, "Editora excluída!");
				} else {
					JOptionPane.showMessageDialog(null, "Tabela de exclus�o est� vazia!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Operação cancelada!");
				TelaExcluiEditora.getDtmEditora().setRowCount(0);
				TelaExcluiEditora.getDtmEditorasSelecionadas().setRowCount(0);
			}
			TelaExcluiEditora.getTfNome().setText("");
		}
	}

	public static void populaTabelaEditora(DefaultTableModel dtmEditora) {
		for (Editora editora : Colecao.getEditorasTemporario()) {
			Object[] data = new Object[3];
			data[1] = editora.getNomeEditora();
			data[0] = editora.getIdEditora();
			data[2] = editora.getUrl();
			dtmEditora.addRow(data);
		}
	}

	public static void populaTabelaEditorasSelecionadas(DefaultTableModel dtmEditorasSelecionadas, Editora editora) {																									// tabela
		Object[] data = new Object[3];
		data[1] = editora.getNomeEditora();
		data[0] = editora.getIdEditora();
		data[2] = editora.getUrl();
		dtmEditorasSelecionadas.addRow(data);
	}

	public class selecionaEditora implements MouseListener {
		public int escolha;

		public selecionaEditora(int aEscolha) {
			escolha = aEscolha;
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (escolha == 0) {// se a tabela selecionada for a geral ela manda sinal 0 e esse if acontece
				populaTabelaEditorasSelecionadas(TelaExcluiEditora.getDtmEditorasSelecionadas(),
						TelaExcluiEditora.getEditoraSelecionada());
				TelaExcluiEditora.getDtmEditora().removeRow(TelaExcluiEditora.getTabelaEditoras().getSelectedRow());
			} else {// se não foi escolhido a outra tabela e esse else acontece
				Object[] data = new Object[3];
				data[1] = TelaExcluiEditora.getEditoraSelecionadaInverso().getNomeEditora();
				data[0] = TelaExcluiEditora.getEditoraSelecionadaInverso().getIdEditora();
				data[2] = TelaExcluiEditora.getEditoraSelecionadaInverso().getUrl();
				TelaExcluiEditora.getDtmEditora().addRow(data);
				TelaExcluiEditora.getDtmEditorasSelecionadas().removeRow(TelaExcluiEditora.getTabelaEditorasSelecionadas().getSelectedRow());
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
