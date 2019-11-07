package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.TelaCriaEditoraController;

public class TelaCriaEditora extends JFrame{
	public JButton btCriarEditora = new JButton("Criar");
	public static JTextField tfNome = new JTextField();
	public static JTextField tfUrl = new JTextField();
	public static JTextField tfId = new JTextField();
	
	public TelaCriaEditora() {
		setVisible(true);
		setSize(800, 600);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Editora");
		
		tfNome.setPreferredSize(new Dimension(200,20));
		add(tfNome);
		
		tfUrl.setPreferredSize(new Dimension(200,20));
		add(tfUrl);
		
		tfId.setPreferredSize(new Dimension(100,20));
		add(tfId);
		
		add(btCriarEditora);
		btCriarEditora.addActionListener(new TelaCriaEditoraController().new onBtCriarEditora());
	}
}
