package model.collection.entities;

import java.util.Objects;

public class Editora implements Comparable<Editora>{
	private String nome;
	private String url;
	private int idEditora;
	
	public Editora(String nome, String url, int idEditora) {
		this.nome = nome;
		this.idEditora = idEditora;
		this.url = url;
	}
	
	public int getIdEditora() {
		return idEditora;
	}
	
	public String getNomeEditora() {
		return nome;
	}

	public String getUrl() {
		return url;
	}

	@Override//TreeSet pede esse método, ele que vai identificar oq são Autores iguais
	public boolean equals(Object outroObjeto) {
		if(this==outroObjeto)
			return true;
		if(outroObjeto == null || !(outroObjeto instanceof Editora))
			return false;
		Editora outraEditora = (Editora) outroObjeto;
		
		return(Objects.equals(this.idEditora, outraEditora.idEditora));
	}
	
	@Override//TreeSet pede esse método para saber a ordem em que vai ordenar na coleção
	public int compareTo(Editora outraEditora) {
		if(this.idEditora != outraEditora.idEditora)
			return Integer.compare(this.idEditora, outraEditora.idEditora);
		return 0;	
	}
	
	@Override
	public String toString() {//usado para testar e no comboBox
		return nome;
	}
}
