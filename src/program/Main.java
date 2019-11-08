package program;

import model.collection.Colecao;
import model.collection.entities.Autor;
import model.collection.entities.Editora;
import model.collection.entities.Livro;
import model.dataBase.dao.DaoAutor;
import model.dataBase.dao.DaoEditora;
import model.dataBase.dao.DaoLivro;
import view.TelaLivraria;

public class Main {

	public static void main(String[] args) {
		new TelaLivraria();
		DaoAutor.carregaAutor();
		DaoEditora.carregaEditora();
		DaoLivro.carregaLivro();
		
		//to testando pra ver se os autores são adicionados na coleção
		for(Autor a : Colecao.getAutores()) {
			System.out.println(a);
		}
		System.out.println("\n--------------------------------------------\n");
		//to testando pra ver se as editoras são adicionadas na coleção
		for(Editora a : Colecao.getEditoras()) {
			System.out.println(a);
		}
		System.out.println("\n--------------------------------------------\n");
		for (Livro liv : Colecao.getLivros()) {
			System.out.println(liv);
		}
	}
}
