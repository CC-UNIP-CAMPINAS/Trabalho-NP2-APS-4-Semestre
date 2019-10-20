package model.collection;

import java.util.ArrayList;
import java.util.TreeSet;

import model.collection.entities.Autor;
import model.collection.entities.Editora;

public abstract class Colecao {
	//Coleções de objetos
	private static TreeSet<Autor> autores = new TreeSet<>();//treeset precisa de métodos a mais que estarão nos autores
	private static ArrayList<Editora> editoras = new ArrayList<>();
	
	
	public static TreeSet<Autor> getAutores() {
		return autores;
	}
	
	public static ArrayList<Editora> getEditoras() {
		return editoras;
	}
}
