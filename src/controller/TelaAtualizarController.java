package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TelaAtualizaAutor;
import view.TelaAtualizaEditora;
import view.TelaAtualizaLivro;
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
			TelaAtualizaEditora.getInstance().show();
			TelaAtualizar.getInstance().setVisible(false);
		}
	}

	// Ação do botão para criar Livro
	public class OnBtAtualizarLivro implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaAtualizaLivro.getInstance().show();
			TelaAtualizar.getInstance().setVisible(false);
		}
	}
}
