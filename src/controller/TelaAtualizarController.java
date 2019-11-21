package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TelaAtualizaAutor;
import view.TelaAtualizar;

public class TelaAtualizarController {
	// Ação do botão para criar autor
	public class OnBtAtualizarAutor implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaAtualizaAutor.getInstance().show();
			TelaAtualizar.getInstance().setVisible(false);
		}
	}

	// Ação do botão para criar Editora
	public class OnBtAtualizarEditora implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			TelaExcluiEditora.getInstance().show();
//			TelaExcluir.getInstance().setVisible(false);
		}
	}

	// Ação do botão para criar Livro
	public class OnBtAtualizarLivro implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			TelaExcluiLivro.getInstance().show();
//			TelaExcluir.getInstance().setVisible(false);
		}
	}
}
