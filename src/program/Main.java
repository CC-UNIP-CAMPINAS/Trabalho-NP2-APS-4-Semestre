package program;

import model.dataBase.Banco;
import view.TelaLivraria;

public class Main {

	public static void main(String[] args) {
		new TelaLivraria();
		Banco.getConnection();
	}

}
