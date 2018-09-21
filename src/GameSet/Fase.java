package GameSet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.management.openmbean.KeyAlreadyExistsException;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Timer;

import view.ContainerDeJanelas;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Nave personagem;
	private Timer timer;
	private boolean inGame;
	private List<Inimigos> inimigos;
	Random r = new Random();
	private static int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 780, 109 }, { 580, 139 },
	{ 880, 239 }, { 790, 259 }, { 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
	{ 930, 159 }, { 590, 80 }, { 520, 160 }, { 940, 59 }, { 990, 30 }, { 920, 200 }, { 900, 259 }, { 660, 50 },
	{ 540, 90 }, { 810, 220 }, { 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 }, { 920, 300 },
	{ 856, 328 }, { 456, 320 } };

	public Fase() {

		setDoubleBuffered(true);
		setFocusable(true);
		addKeyListener(new TecladoAdepter());
		ImageIcon ref = new ImageIcon("Img\\frame3.gif");

		fundo = ref.getImage();
		personagem = new Nave();

		inGame = true;

		inicializaInimigos();

		timer = new Timer(5, this);
		timer.start();
	}

	public void inicializaInimigos() {

		inimigos = new ArrayList<Inimigos>();

		for (int i = 0; i < coordenadas.length; i++) {

			inimigos.add(new Inimigos(coordenadas[i][0], coordenadas[i][1]));

		}
	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, 640, 480, null);

		if (inGame) {

			graficos.drawImage(personagem.getImagem_nave(), personagem.getX(), personagem.getY(), 60, 60, this);

			List<LaserBean> tiros = personagem.getTiros();

			for (int i = 0; i < tiros.size(); i++) {
				LaserBean l = (LaserBean) tiros.get(i);
				graficos.drawImage(l.getImagem(), l.getX(), l.getY(), 80, 10, this);
			}

			for (int i = 0; i < inimigos.size(); i++) {
				Inimigos in = inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), 40, 30, this);
			}

			graficos.setColor(Color.WHITE);
			graficos.drawString("Inimigos: " + inimigos.size(), 5, 15);

		} else {

			ImageIcon fimJogo = new ImageIcon("Img\\game_over.jpg");
			graficos.drawImage(fimJogo.getImage(), 50, 0, 540, fimJogo.getIconHeight(), null);
		}
		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (inimigos.size() == 0) {

			inGame = false;
		}

		List<LaserBean> tiros = personagem.getTiros();

		for (int i = 0; i < tiros.size(); i++) {
			
			LaserBean l = (LaserBean) tiros.get(i);
			
			if (l.isVisible()) {

				l.movimentacao();
				
			} else {

				tiros.remove(i);
			}
		}

		for (int i = 0; i < inimigos.size(); i++) {
			Inimigos in = (Inimigos) inimigos.get(i);
			if (in.isVisible()) {

				in.movimentacao();
			} else {

				inimigos.remove(i);
			}
		}

		personagem.movimentacao();
		checar_Colisoes();
		repaint();
	}

	public void checar_Colisoes() {

		Rectangle formaNave = personagem.getBounds();
		Rectangle formaInimigo;
		Rectangle formaLaser;

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigos tempInimigo = inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();

			if (formaNave.intersects(formaInimigo)) {

				personagem.setVisible(false);
				tempInimigo.setVisible(false);
				inGame = false;

			}
		}

		List<LaserBean> tiros = personagem.getTiros();
		for (int i = 0; i < tiros.size(); i++) {

			LaserBean tempTiro = tiros.get(i);
			formaLaser = tempTiro.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {

				Inimigos tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();

				if (formaLaser.intersects(formaInimigo)) {

					tempInimigo.setVisible(false);
					tempTiro.setVisible(false);
				}
			}
		}
	}

	private class TecladoAdepter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				inGame = true;
				personagem = new Nave();
				inicializaInimigos();
			}
			personagem.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {

			personagem.keyReleased(e);
		}
	}
}
