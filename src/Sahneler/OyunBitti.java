package Sahneler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import ui.Buton;
import static main.OyunIstatislikleri.*;

public class OyunBitti extends OyunEkrani implements SahneMethotlari {

	private Buton bReplay, bMenu;

	public OyunBitti(Game game) {
		super(game);
		initButonlar();
	}

	private void initButonlar() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 300;
		int yOffset = 100;
		bMenu = new Buton("Menu", x, y, w, h);
		bReplay = new Buton("Replay", x, y + yOffset, w, h);
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("LucidaSans", Font.BOLD, 50));
		g.setColor(Color.red);
		g.drawString("Game Over!", 160, 80);
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		bMenu.draw(g);
		bReplay.draw(g);
	}

	private void OyunuBastanOyna() {
		SıfırlaAll();
		SetOyunDurumu(Oynaniyor);

	}
	private void SıfırlaAll() {
		game.getOynaniyor().SıfırlaHersey();
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getSinirlar().contains(x, y)) {
			SetOyunDurumu(MENU);
			SıfırlaAll();
		} else if (bReplay.getSinirlar().contains(x, y))
			OyunuBastanOyna();
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.MouseUzerinde(false);
		bReplay.MouseUzerinde(false);

		if (bMenu.getSinirlar().contains(x, y))
			bMenu.MouseUzerinde(true);
		else if (bReplay.getSinirlar().contains(x, y))
			bReplay.MouseUzerinde(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.SıfırlaBooleans();
		bReplay.SıfırlaBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked1(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
