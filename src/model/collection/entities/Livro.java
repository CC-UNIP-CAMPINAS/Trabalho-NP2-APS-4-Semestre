package model.collection.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Livro implements Comparable<Livro>{
	private String titulo;
	private String isbn;
	private String editora;
	private double preco; 
	private ArrayList<String> autores;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}
	
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
		}
		return "Nome: "+titulo+"\nIsbn: "+isbn+"\nEditora: "+editora+"\nPre�o: "+preco+"\nAutores: "+listaAutores+"\n";
	}
	
	public static Comparator<String> sortBySequ_no = new Comparator<String>() {
		@Override
		public int compare(String obj1, String obj2) {
			//sort in ascending order
			return obj1.compareTo(obj2);
		}
	}; 
}
