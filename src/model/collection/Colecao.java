package model.collection;

import java.util.TreeSet;

import model.collection.entities.Autor;
import model.collection.entities.Editora;

public abstract class Colecao {
	//Coleções de objetos
	private static TreeSet<Autor> autores = new TreeSet<>();//treeset precisa de métodos a mais que estarão nas entidades Autor
	private static TreeSet<Editora> editoras = new TreeSet<>();//treeset precisa de métodos a mais que estarão nas entidades Editora
	
	public static TreeSet<Autor> getAutores() {
		return autores;
	}
	
	public static TreeSet<Editora> getEditoras() {
		return editoras;
	}
}
