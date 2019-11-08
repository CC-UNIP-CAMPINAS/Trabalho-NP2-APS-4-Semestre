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
	
	public class onBtBuscarEditora implements ActionListener{
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoAutor, esse método faz acesso ao banco, por isso está em dao	
			TelaExcluiEditora.dtmEditora.setNumRows(0);
			if(DaoEditora.buscarEditora(TelaExcluiEditora.tfNome)) {
				populaTabelaEditora(TelaExcluiEditora.dtmEditora);
				TelaExcluiEditora.tfNome.setText("");
			}
			else {
				TelaExcluiEditora.tfNome.setText("");
				JOptionPane.showMessageDialog(null, "Nenhum dado encontrado!");
			}
		}
	}
	
	public class onBtExcluiEditora implements ActionListener{
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoAutor, esse método faz acesso ao banco, por isso está em dao
			if(DaoEditora.excluiEditora(TelaExcluiEditora.tabelaEditorasSelecionadas) > 0) {
				TelaExcluiEditora.dtmEditorasSelecionadas.setNumRows(0);
			}
			else {
				JOptionPane.showMessageDialog(null, "Tabela de exclus�o est� vazia!");
			}
		}
	}
	
	public static void populaTabelaEditora(DefaultTableModel dtmEditora) {
		for(Editora editora : Colecao.getEditorasTemporario()) { //percorre a coleção e para cada autor cria um objeto e adiciona na tabela geral
			Object[] data = new Object[3];
			data[1] = editora.getNomeEditora();
			data[0] = editora.getIdEditora();
			data[2] = editora.getUrl();
			dtmEditora.addRow(data);
		}
	}
	
	public static void populaTabelaEditorasSelecionadas(DefaultTableModel dtmEditorasSelecionadas, Editora editora) {	//Cria um objeto que ele pega no parametro e adiciona como linha na segunda tabela
		Object[] data = new Object[3];
		data[1] = editora.getNomeEditora();
		data[0] = editora.getIdEditora();
		data[2] = editora.getUrl();
		dtmEditorasSelecionadas.addRow(data);
	}
	
	public class selecionaEditora implements MouseListener{
		public int escolha;
		public selecionaEditora(int aEscolha) {
			escolha = aEscolha;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(escolha == 0) {// se a tabela selecionada for a geral ela manda sinal 0 e esse if acontece
				populaTabelaEditorasSelecionadas(TelaExcluiEditora.dtmEditorasSelecionadas, TelaExcluiEditora.getEditoraSelecionada());	
				TelaExcluiEditora.dtmEditora.removeRow(TelaExcluiEditora.tabelaEditoras.getSelectedRow());	
			}
			else {//se não foi escolhido a outra tabela e esse else acontece
				Object[] data = new Object[3];
				data[1] = TelaExcluiEditora.getEditoraSelecionadaInverso().getNomeEditora();
				data[0] = TelaExcluiEditora.getEditoraSelecionadaInverso().getIdEditora();
				data[2] = TelaExcluiEditora.getEditoraSelecionadaInverso().getUrl();
				TelaExcluiEditora.dtmEditora.addRow(data);	
				TelaExcluiEditora.dtmEditorasSelecionadas.removeRow(TelaExcluiEditora.tabelaEditorasSelecionadas.getSelectedRow());
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
