package Sahneler;

import java.awt.image.BufferedImage;

import main.Game;

public class OyunEkrani {

	protected Game game;
	protected int animasyonIndex;
	protected int animasyon_Hız = 25;
	protected int tick;
	public OyunEkrani(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}
	protected boolean animasyonkontrolu(int karakterID) {
		return game.getKaroYoneticisi().karakteranimasyonukontrolu(karakterID);
	}

	protected void YukseltmeTick() {
		tick++;
		if (tick >= animasyon_Hız) {
			tick = 0;
			animasyonIndex++;
			if (animasyonIndex >= 4)
				animasyonIndex = 0;
		}
	}
	protected BufferedImage getkarakter(int karakterID) {
		return game.getKaroYoneticisi().getkarakter(karakterID);
	}
	protected BufferedImage getkarakter(int karakterID, int animasyonIndex) {
		return game.getKaroYoneticisi().getAnikarakter(karakterID, animasyonIndex);
	}

}
