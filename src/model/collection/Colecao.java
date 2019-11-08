package model.collection;

import java.util.ArrayList;
import java.util.TreeSet;

import model.collection.entities.Autor;
import model.collection.entities.Editora;
import model.collection.entities.Livro;

public abstract class Colecao {
	//Coleções de objetos
	private static TreeSet<Autor> autores = new TreeSet<>();//treeset precisa de métodos a mais que estarão nas entidades Autor
	private static TreeSet<Editora> editoras = new TreeSet<>();//treeset precisa de métodos a mais que estarão nas entidades Editora
	private static TreeSet<Livro> livros = new TreeSet<>();//treeset precisa de métodos a mais que estarão nas entidades Livro
	private static ArrayList<Autor> autoresTemporario = new ArrayList<>();//treeset precisa de métodos a mais que estarão nas entidades Livro
	private static ArrayList<Editora> editorasTemporario = new ArrayList<>();//treeset precisa de métodos a mais que estarão nas entidades Livro
	private static ArrayList<Livro> livrosTemporario = new ArrayList<>();//treeset precisa de métodos a mais que estarão nas entidades Livro
	
	public static TreeSet<Autor> getAutores() {
		return autores;
	}
	
	public static TreeSet<Editora> getEditoras() {
		return editoras;
	}
	
	public static TreeSet<Livro> getLivros() {
		return livros;
	}

	public static ArrayList<Autor> getAutoresTemporario() {
		return autoresTemporario;
	}

	public static ArrayList<Editora> getEditorasTemporario() {
		return editorasTemporario;
	}

	public static ArrayList<Livro> getLivrosTemporario() {
		return livrosTemporario;
	}
}
