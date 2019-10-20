package model.collection.entities;

import java.util.Objects;

public class Autor implements Comparable<Autor>{//agora implementa comparable para usar o método compareTo
	private String nome;
	private String sobreNome;
	private int idAutor;
	
	public Autor(String nome, String sobreNome, int idAutor) {
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.idAutor = idAutor;
	}
	
	public int getIdAutor() {
		return idAutor;
	}

	@Override//TreeSet pede esse método, ele que vai identificar oq são Autores iguais
	public boolean equals(Object outroObjeto) {
		if(this==outroObjeto)
			return true;
		if(outroObjeto == null || !(outroObjeto instanceof Autor))
			return false;
		Autor outroAutor = (Autor) outroObjeto;
		
		return(Objects.equals(this.idAutor, outroAutor.idAutor));
	}
	
	@Override//TreeSet pede esse método para saber a ordem em que vai ordenar na coleção
	public int compareTo(Autor outroAutor) {
		if(this.idAutor != outroAutor.idAutor)
			return Integer.compare(this.idAutor, outroAutor.idAutor);
		return 0;	
	}
	
	@Override
	public String toString() {//usado para testar
		return "nome=" + nome + "\n";
	}
}
