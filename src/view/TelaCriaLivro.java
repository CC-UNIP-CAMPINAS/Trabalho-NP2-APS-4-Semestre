package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.TelaCriaLivroController;

public class TelaCriaLivro extends JFrame{
	public static JButton btCriarLivro = new JButton("Criar");
	public static JTextField tfTitle = new JTextField();
	public static JTextField tfIsbn = new JTextField();
	public static JTextField tfPublisherId = new JTextField();
	public static JTextField tfPrice = new JTextField();
	
	public TelaCriaLivro() {
		setVisible(true);
		setSize(800, 600);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Livro");
		
		tfTitle.setPreferredSize(new Dimension(200,20));
		add(tfTitle);
		
		tfIsbn.setPreferredSize(new Dimension(200,20));
		add(tfIsbn);
		
		tfPublisherId.setPreferredSize(new Dimension(100,20));
		add(tfPublisherId);
		
		tfPrice.setPreferredSize(new Dimension(100,20));
		add(tfPrice);
		
		add(btCriarLivro);
		btCriarLivro.addActionListener(new TelaCriaLivroController().new onBtCriarLivro());
		
	}
}
