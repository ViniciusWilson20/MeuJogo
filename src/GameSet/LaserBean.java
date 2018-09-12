package GameSet;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class LaserBean {

	private int x, y;
	private int largura, altura;
	private Image imagem;
	private boolean isVisible;
	private static final int LARGURA_TELA = 640;
	private static final int VELOCIDADE = 2;

	public LaserBean(int x, int y) {

		this.x = x;
		this.y = y;

		ImageIcon ref = new ImageIcon("Img\\laser.png");
		imagem = ref.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
		isVisible = true;
	}

	public void movimentacao() {

		this.x += VELOCIDADE;
		if (this.x > LARGURA_TELA) {

			isVisible = false;
		}
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
}
