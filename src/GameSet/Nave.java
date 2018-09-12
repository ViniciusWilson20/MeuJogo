package GameSet;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Nave {

	private int x, y;
	private int dx, dy;
	private int altura, largura;
	private Image imagem_nave;
	private List<LaserBean> tiros;
	private boolean isVisible;

	public Nave() {

		ImageIcon ref = new ImageIcon("Img\\ship3.gif");
		imagem_nave = ref.getImage();

		largura = imagem_nave.getWidth(null);
		altura = 40;

		tiros = new ArrayList<LaserBean>();

		this.x = 60;
		this.y = 180;

	}

	public void movimentacao() {

		x += dx;
		y += dy;

		area_restriction();

	}

	public List<LaserBean> getTiros() {
		return tiros;
	}

	public void atira() {
		this.tiros.add(new LaserBean(x + largura, y + altura / 2));
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public int getX() {
		return x;
	}

	public int getY() {

		return y;
	}

	public Image getImagem_nave() {

		return imagem_nave;
	}


	
	public void keyPressed(KeyEvent tecla) {

		int codigo = tecla.getKeyCode();
		
		if (codigo == KeyEvent.VK_P) {

			atira();
		}

		if (codigo == KeyEvent.VK_W) {

			dy = -2;
		}
		if (codigo == KeyEvent.VK_S) {

			dy = 2;
		}
		if (codigo == KeyEvent.VK_A) {

			dx = -2;
		}

		if (codigo == KeyEvent.VK_D) {

			dx = 2;

		}

	}

	public void keyReleased(KeyEvent tecla) {

		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_W) {

			dy = 0;
		}
		if (codigo == KeyEvent.VK_S) {

			dy = 0;

		}
		if (codigo == KeyEvent.VK_A) {

			dx = 0;
		}
		if (codigo == KeyEvent.VK_D) {

			dx = 0;
		}
	}

	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public void area_restriction() {

		if (this.x < 4) {
			x = 4;
		}
		if (this.x > 574) {

			x = 574;
		}
		if (this.y < -2) {

			y = -2;
		}
		if (this.y > 396) {

			y = 396;
		}
	}

}
