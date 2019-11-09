package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.TelaCriaAutorController;

public class TelaCriaAutor extends JFrame{
	
	public JButton btCriarAutor = new JButton("Criar");
	public static JTextField tfNome = new JTextField();
	public static JTextField tfSobreNome = new JTextField();
	public static JTextField tfId = new JTextField();
	
	public TelaCriaAutor() {
		setVisible(true);
		setSize(800, 600);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Autor");
		
		JLabel selType = new JLabel(); 		
		selType.setText("Nome:");
		tfNome.setPreferredSize(new Dimension(200,20));
		add(selType);
		add(tfNome);
		
		JLabel selType2 = new JLabel(); 		
		selType2.setText("SobreNome:");
		tfSobreNome.setPreferredSize(new Dimension(200,20));
		add(selType2);
		add(tfSobreNome);
		
		JLabel selType3 = new JLabel(); 		
		selType3.setText("Id:");
		tfId.setPreferredSize(new Dimension(100,20));
		add(selType3);
		add(tfId);
		
		add(btCriarAutor);
		btCriarAutor.addActionListener(new TelaCriaAutorController().new onBtCriarAutor());
		
	}
}
