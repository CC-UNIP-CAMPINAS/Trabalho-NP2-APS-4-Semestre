package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.dataBase.dao.DaoAutor;
import view.TelaCriaAutor;

//classe responsavel em controlar a TelaCriaAutor
//aqui vai ter todas as chamadas de ações para aquela tela
public class TelaCriaAutorController {
	
	public class onBtCriarAutor implements ActionListener{
		public void actionPerformed(ActionEvent e) {//chama o metodo de criação dentro de DaoAutor, esse método faz acesso ao banco, por isso está em dao
			DaoAutor.criaAutor(TelaCriaAutor.tfNome, TelaCriaAutor.tfSobreNome, TelaCriaAutor.tfId);
		}
	}
	
}
