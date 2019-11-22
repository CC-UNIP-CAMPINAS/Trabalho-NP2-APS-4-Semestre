package program;

import model.dataBase.dao.DaoAutor;
import model.dataBase.dao.DaoEditora;
import model.dataBase.dao.DaoLivro;
import view.TelaLivraria;

public class Main {

	public static void main(String[] args) {
		
		DaoAutor.carregaAutor();
		DaoEditora.carregaEditora();
		DaoLivro.carregaLivro();
		new TelaLivraria();
	}
}
