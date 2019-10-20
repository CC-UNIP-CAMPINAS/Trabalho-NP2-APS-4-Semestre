package program;

import model.collection.Colecao;
import model.collection.entities.Autor;
import model.dataBase.dao.DaoAutor;
import view.TelaLivraria;

public class Main {

	public static void main(String[] args) {
		new TelaLivraria();
		DaoAutor.carregaAutor();
		
		//to testando pra ver se os autores sãoa dicionados na coleção
		for(Autor a : Colecao.getAutores()) {
			System.out.println(a);
		}
	}

}
