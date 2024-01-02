package main;

import java.awt.Graphics;

public class Goruntuleme {

	private Game game;

	public Goruntuleme(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {
		switch (OyunIstatislikleri.OyunDurumu) {
		case MENU:
			game.getMenu().render(g);
			break;
		case Oynaniyor:
			game.getOynaniyor().render(g);
			break;
		case DUZENLE:
			game.getDUZENLEor().render(g);
			break;
		case OYUN_BITTI:
			game.getOyunBitti().render(g);
			break;
		default:
			break;

		}

	}

}