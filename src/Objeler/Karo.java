package Objeler;

import java.awt.image.BufferedImage;

public class Karo {

	private BufferedImage[] karakter;
	private int id, KaroTipi;

	public Karo(BufferedImage karakter, int id, int KaroTipi) {
		this.karakter = new BufferedImage[1];
		this.karakter[0] = karakter;
		this.id = id;
		this.KaroTipi = KaroTipi;
	}

	public Karo(BufferedImage[] karakter, int id, int KaroTipi) {
		this.karakter = karakter;
		this.id = id;
		this.KaroTipi = KaroTipi;
	}

	public int getKaroTipi() {
		return KaroTipi;
	}

	public BufferedImage getkarakter(int animasyonIndex) {
		return karakter[animasyonIndex];
	}

	public BufferedImage getkarakter() {
		return karakter[0];
	}

	public boolean animasyonkontrolu() {
		return karakter.length > 1;
	}

	public int getId() {
		return id;
	}

}
