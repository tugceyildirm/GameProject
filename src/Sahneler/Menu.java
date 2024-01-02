package Sahneler;

import java.awt.Graphics;
import main.Game;
import ui.Buton;
import static main.OyunIstatislikleri.*;

public class Menu extends OyunEkrani implements SahneMethotlari {

	private Buton bOynaniyor, bDUZENLE, bayarlar, bQuit;

	public Menu(Game game) {
		super(game);
		initButonlar();
	}

	private void initButonlar() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bOynaniyor = new Buton("Play", x, y, w, h);
		bDUZENLE = new Buton("DUZENLE", x, y + yOffset, w, h);
		bayarlar = new Buton("ayarlar", x, y + yOffset * 2, w, h);
		bQuit = new Buton("Quit", x, y + yOffset * 3, w, h);
	}

	@Override
	public void render(Graphics g) {

		Butonciz(g);
	}

	private void Butonciz(Graphics g) {
		bOynaniyor.draw(g);
		bDUZENLE.draw(g);
		bayarlar.draw(g);
		bQuit.draw(g);

	}

	@Override
	public void mouseClicked(int x, int y) {

		if (bOynaniyor.getSinirlar().contains(x, y))
			SetOyunDurumu(Oynaniyor);
		else if (bDUZENLE.getSinirlar().contains(x, y))
			SetOyunDurumu(DUZENLE);
		else if (bQuit.getSinirlar().contains(x, y))
			System.exit(0);
	}

	@Override
	public void mouseMoved(int x, int y) {
		bOynaniyor.MouseUzerinde(false);
		bDUZENLE.MouseUzerinde(false);
		bayarlar.MouseUzerinde(false);
		bQuit.MouseUzerinde(false);

		if (bOynaniyor.getSinirlar().contains(x, y))
			bOynaniyor.MouseUzerinde(true);
		else if (bDUZENLE.getSinirlar().contains(x, y))
			bDUZENLE.MouseUzerinde(true);
		else if (bayarlar.getSinirlar().contains(x, y))
			bayarlar.MouseUzerinde(true);
		else if (bQuit.getSinirlar().contains(x, y))
			bQuit.MouseUzerinde(true);

	}


	@Override
	public void mouseReleased(int x, int y) {
		ButonSıfırla();
	}

	private void ButonSıfırla() {
		bOynaniyor.SıfırlaBooleans();
		bDUZENLE.SıfırlaBooleans();
		bayarlar.SıfırlaBooleans();
		bQuit.SıfırlaBooleans();

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