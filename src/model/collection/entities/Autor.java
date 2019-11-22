package model.collection.entities;

import java.util.Objects;

public class Autor implements Comparable<Autor>{//agora implementa comparable para usar o método compareTo
	private String nome;
	private String sobreNome;
	private String nomeCompleto;
	private int idAutor;
	
	public Autor(String nome, String sobreNome, int idAutor) {
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.idAutor = idAutor;
		nomeCompleto = juntaNomeAutor(nome, sobreNome);
	}
	
	public int getIdAutor() {
		return idAutor;
	}
	
	public String getNomeAutor() {
		return nome;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public String getSobreNome() {
		return sobreNome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public static String juntaNomeAutor(String aNome, String aSobreNome){
         String nome = aNome;
         String sobreNome = aSobreNome;
         nome = nome.replaceAll("  ","");
         sobreNome = sobreNome.replaceAll("  ","");
         return nome+" "+sobreNome;
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
		return "nome=" + nome + " ID=" + idAutor +"\n";
	}
}
