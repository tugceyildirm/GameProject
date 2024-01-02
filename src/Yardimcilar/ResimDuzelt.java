package Yardimcilar;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ResimDuzelt {
	public static BufferedImage getResimAcisi(BufferedImage foto, int YonAcisi) {
		int w = foto.getWidth();
		int h = foto.getHeight();

		BufferedImage newfoto = new BufferedImage(w, h, foto.getType());
		Graphics2D g2d = newfoto.createGraphics();
		g2d.rotate(Math.toRadians(YonAcisi), w / 2, h / 2);
		g2d.drawImage(foto, 0, 0, null);
		g2d.dispose();
		return newfoto;
	}	
	public static BufferedImage Yapfoto(BufferedImage[] fotos) {
		int w = fotos[0].getWidth();
		int h = fotos[0].getHeight();

		BufferedImage newfoto = new BufferedImage(w, h, fotos[0].getType());
		Graphics2D g2d = newfoto.createGraphics();

		for (BufferedImage foto : fotos) {
			g2d.drawImage(foto, 0, 0, null);
		}
		g2d.dispose();
		return newfoto;
	}
	public static BufferedImage getYapResimAcisi(BufferedImage[] fotos, int YonAcisi, int IndexYonu) {
		int w = fotos[0].getWidth();
		int h = fotos[0].getHeight();

		BufferedImage newfoto = new BufferedImage(w, h, fotos[0].getType());
		Graphics2D g2d = newfoto.createGraphics();
		for (int i = 0; i < fotos.length; i++) {
			if (IndexYonu == i)
				g2d.rotate(Math.toRadians(YonAcisi), w / 2, h / 2);
			g2d.drawImage(fotos[i], 0, 0, null);
			if (IndexYonu == i)
				g2d.rotate(Math.toRadians(-YonAcisi), w / 2, h / 2);
		}
		g2d.dispose();
		return newfoto;
	}
	public static BufferedImage[] getYapResimAcisi(BufferedImage[] fotos, BufferedImage secondImage, int YonAcisi) {
		int w = fotos[0].getWidth();
		int h = fotos[0].getHeight();

		BufferedImage[] dizi = new BufferedImage[fotos.length];

		for (int i = 0; i < fotos.length; i++) {

			BufferedImage newfoto = new BufferedImage(w, h, fotos[0].getType());
			Graphics2D g2d = newfoto.createGraphics();

			g2d.drawImage(fotos[i], 0, 0, null);
			g2d.rotate(Math.toRadians(YonAcisi), w / 2, h / 2);
			g2d.drawImage(secondImage, 0, 0, null);
			g2d.dispose();

			dizi[i] = newfoto;
		}

		return dizi;

	}

}
