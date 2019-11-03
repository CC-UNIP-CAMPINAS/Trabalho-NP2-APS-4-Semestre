package model.collection.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Livro implements Comparable<Livro>{
	private String titulo;
	private String isbn;
	private String editora;
	private double preco; 
	private ArrayList<String> autores;
	
	public Livro(String titulo, String isbn, String editora, double preco) {
		autores = new ArrayList<>();
		this.titulo = titulo;
		this.isbn = isbn;
		this.editora = editora;
		this.preco = preco;
	}

	public ArrayList<String> getAutores() {
		return autores;
	}
	
	@Override//TreeSet pede esse método, ele que vai identificar oq são Livros iguais
	public boolean equals(Object outroObjeto) {
		if(this==outroObjeto)
			return true;
		if(outroObjeto == null || !(outroObjeto instanceof Livro))
			return false;
		Livro outroLivro = (Livro) outroObjeto;
		
		return(Objects.equals(this.isbn, outroLivro.isbn));
	}
	
	@Override//TreeSet pede esse método para saber a ordem em que vai ordenar na coleção
	public int compareTo(Livro outroLivro) {
		if(this.isbn != outroLivro.isbn)
			return this.isbn.compareTo(outroLivro.isbn);
		return 0;	
	}
	
	@Override
	public String toString() {
		String listaAutores="";
		for (String autor : autores) {
			listaAutores = listaAutores+autor+"; ";
			System.out.println(autor);
		}
		return "Nome: "+titulo+"\nIsbn: "+isbn+"\nEditora: "+editora+"\nPreço: "+preco+"\nAutores: "+listaAutores;
	}
}
