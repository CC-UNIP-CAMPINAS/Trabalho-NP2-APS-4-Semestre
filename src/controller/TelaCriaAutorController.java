package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.collection.Colecao;
import model.collection.entities.Autor;
import model.dataBase.dao.DaoAutor;
import view.TelaCriaAutor;

//classe responsavel em controlar a TelaCriaAutor
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaCriaAutorController {
	
	public class onBtCriarAutor implements ActionListener{
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoAutor, esse método faz acesso ao banco, por isso está em dao
			if(TelaCriaAutor.getTfNome().getText().isEmpty() || TelaCriaAutor.getTfSobreNome().getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			}
			else {
				if(JOptionPane.showConfirmDialog(null, "Deseja mesmo criar um autor?", "Atenção", JOptionPane.CANCEL_OPTION) == 0){
					DaoAutor.criaAutor(TelaCriaAutor.getTfNome(), TelaCriaAutor.getTfSobreNome());
					populaTabelaAutores(TelaCriaAutor.getDtmAutores());
					JOptionPane.showMessageDialog(null, "Autor criado!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
				}
			}
			TelaCriaAutor.getTfNome().setText("");
			TelaCriaAutor.getTfSobreNome().setText("");
		}
	}
	
	public static void populaTabelaAutores(DefaultTableModel dtmAutores) {
		dtmAutores.setNumRows(0);
		for(Autor autor : Colecao.getAutores()) { //percorre a coleção e para cada autor cria um objeto e adiciona na tabela geral
			Object[] data = new Object[2];
			data[1] = autor.getNomeCompleto();
			data[0] = autor.getIdAutor();
			dtmAutores.addRow(data);
		}
	}
}
