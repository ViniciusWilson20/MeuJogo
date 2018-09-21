package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import GameSet.Fase;

public class ContainerDeJanelas extends JFrame {

	public ContainerDeJanelas() {

		add(new Fase());
		setTitle("Meu Primeiro Jogo");
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		JMenuBar MyMenu = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem sobre = new JMenuItem("Sobre");

		sobre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Desenvolvido por: Vinicius Wilson \n "
					  + "Créditos: "
					  + "Desenvolvimento Lecionado por Yann Braga",
						"Créditos", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		JMenuItem sair = new JMenuItem("sair");

		sair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

		menu.add(sobre);
		menu.add(new JSeparator());
		menu.add(sair);
		MyMenu.add(menu);
		setJMenuBar(MyMenu);

	}

	public static void main(String[] args) {

		try {

			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if("Nimbus".equals(info.getName())){
					
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		new ContainerDeJanelas();
	}

}
