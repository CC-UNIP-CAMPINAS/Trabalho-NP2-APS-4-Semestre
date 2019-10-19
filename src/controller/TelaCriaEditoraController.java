package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.dataBase.dao.DaoEditora;
import view.TelaCriaEditora;

//classe responsavel em controlar a TelaCriaEditora
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaCriaEditoraController{
	
	//ação do botão
	public class onBtCriarEditora implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoEditora, esse método faz acesso ao banco, por isso está em dao
			DaoEditora.criaEditora(TelaCriaEditora.tfNome, TelaCriaEditora.tfUrl, TelaCriaEditora.tfId);
		}
		
	}
	
}
