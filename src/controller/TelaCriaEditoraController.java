package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Editora;
import model.dataBase.dao.DaoEditora;
import view.TelaCriaAutor;
import view.TelaCriaEditora;
import view.TelaCriaLivro;

//classe responsavel em controlar a TelaCriaEditora
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaCriaEditoraController{
	
	//ação do botão
	public class OnBtCriarEditora implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoEditora, esse método faz acesso ao banco, por isso está em dao
			if(TelaCriaEditora.getTfNome().getText().isEmpty() || TelaCriaEditora.getTfUrl().getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			}
			else {
				if(JOptionPane.showConfirmDialog(null, "Deseja mesmo criar uma editora?", "Atenção", JOptionPane.CANCEL_OPTION) == 0) {
					DaoEditora.criaEditora(TelaCriaEditora.getTfNome(), TelaCriaEditora.getTfUrl());
					populaTabelaEditora(TelaCriaEditora.getDtmEditoras());
					JOptionPane.showMessageDialog(null, "Editora criada!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
				}
			}
			TelaCriaEditora.getTfNome().setText("");
			TelaCriaEditora.getTfUrl().setText("");	
		}
	}
	
	public static void populaTabelaEditora(DefaultTableModel dtmEditoras) {
		dtmEditoras.setNumRows(0);
		for(Editora editora : Colecao.getEditoras()) { //percorre a coleção e para cada autor cria um objeto e adiciona na tabela geral
			Object[] data = new Object[3];
			data[0] = editora.getIdEditora();
			data[1] = editora.getNomeEditora();
			data[2] = editora.getUrl();
			dtmEditoras.addRow(data);
		}
	}
	
}
