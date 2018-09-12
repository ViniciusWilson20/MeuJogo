package GameSet;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigos {

	private int x, y;
	// private int dx, dy;
	private Image imagem;
	private boolean isVisible;
	private int largura, altura;
	private static final int LARGURA_TELA = 640;
	private static final int VELOCIDADE = 1;
	private static 
	int contador = 0;

	public Inimigos(int x, int y) {

		this.x = x;
		this.y = y;

		ImageIcon ref;
		if (contador++ % 2 == 0) {
			ref = new ImageIcon("Img\\inimigo_4.gif");
			
		} else if (contador++ % 2 == 0 && ++ contador % 3 == 0) {
			
			ref = new ImageIcon("Img\\nave.gif");
		}       
		else {
			ref = new ImageIcon("Img\\inimigo_3.gif");
		}

		imagem = ref.getImage();
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		isVisible = true;
	}

	public void movimentacao() {

		if (this.x < 0) {
			this.x = LARGURA_TELA;
		} else {
			x -= VELOCIDADE;
		}
	}

	public Image getImagem() {
		return imagem;
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

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

}
