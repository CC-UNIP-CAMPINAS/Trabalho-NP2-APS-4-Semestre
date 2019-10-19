package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.TelaCriaAutorController;

public class TelaCriaAutor extends JFrame{
	
	public static JButton btCriarAutor = new JButton("Criar");
	public static JTextField tfNome = new JTextField();
	public static JTextField tfSobreNome = new JTextField();
	public static JTextField tfId = new JTextField();
	
	public TelaCriaAutor() {
		setVisible(true);
		setSize(800, 600);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Autor");
		
		tfNome.setPreferredSize(new Dimension(200,20));
		add(tfNome);
		
		tfSobreNome.setPreferredSize(new Dimension(200,20));
		add(tfSobreNome);
		
		tfId.setPreferredSize(new Dimension(100,20));
		add(tfId);
		
		add(btCriarAutor);
		btCriarAutor.addActionListener(new TelaCriaAutorController().new onBtCriarAutor());
		
	}
}
