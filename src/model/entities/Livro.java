package model.entities;

import java.util.ArrayList;

public class Livro {
	private String titulo;
	private String isbn;
	private String editora;
	private double preco; 
	private ArrayList<Autor> autores;
	
	public Livro(String titulo, String isbn, String editora, double preco) {
		autores = new ArrayList<>();
		this.titulo = titulo;
		this.isbn = isbn;
		this.editora = editora;
		this.preco = preco;
	}

}
