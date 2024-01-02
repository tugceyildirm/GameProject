package Sahneler;

import static Yardimcilar.Sabitler.Karo.YOL_KAROSU;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Objeler.Yol;
import Objeler.Karo;
import Yardimcilar.YukleKaydet;
import main.Game;
import ui.EsyaBari;

public class Duzenleme extends OyunEkrani implements SahneMethotlari {

	private int[][] lvl;
	private Karo SeciliKaro;
	private int mouseX, mouseY;
	private int SonKaroX, SonKaroY, SonKaroId;
	private boolean drawSelect;
	private EsyaBari esyaBari;
	private Yol Baslangic, end;

	public Duzenleme(Game game) {
		super(game);
		yukleStandartLevel();
		esyaBari = new EsyaBari(0, 640, 640, 160, this);
	}

	private void yukleStandartLevel() {
		lvl = YukleKaydet.GetLevelData("new_level");
		ArrayList<Yol> puanlar = YukleKaydet.GetLevelyolpuanlari("new_level");
		Baslangic = puanlar.get(0);
		end = puanlar.get(1);
	}

	public void Yukseltme() {
		YukseltmeTick();
	}

	@Override
	public void render(Graphics g) {

		drawLevel(g);
		esyaBari.draw(g);
		drawSeciliKaro(g);
		drawyolpuanlari(g);

	}

	private void drawyolpuanlari(Graphics g) {
		if (Baslangic != null)
			g.drawImage(esyaBari.getBaslangicPathfoto(), Baslangic.getxKord() * 32, Baslangic.getyKord() * 32, 32, 32, null);

		if (end != null)
			g.drawImage(esyaBari.getendPathfoto(), end.getxKord() * 32, end.getyKord() * 32, 32, 32, null);

	}

	private void drawLevel(Graphics g) {
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				if (animasyonkontrolu(id)) {
					g.drawImage(getkarakter(id, animasyonIndex), x * 32, y * 32, null);
				} else
					g.drawImage(getkarakter(id), x * 32, y * 32, null);
			}
		}
	}

	private void drawSeciliKaro(Graphics g) {
		if (SeciliKaro != null && drawSelect) {
			g.drawImage(SeciliKaro.getkarakter(), mouseX, mouseY, 32, 32, null);
		}
	}

	public void kaydetLevel() {

		YukleKaydet.kaydetLevel("new_level", lvl, Baslangic, end);
		game.getOynaniyor().setLevel(lvl);

	}

	public void setSeciliKaro(Karo karo) {
		this.SeciliKaro = karo;
		drawSelect = true;
	}

	private void KaroDegistir(int x, int y) {
		if (SeciliKaro != null) {
			int tileX = x / 32;
			int tileY = y / 32;

			if (SeciliKaro.getId() >= 0) {
				if (SonKaroX == tileX && SonKaroY == tileY && SonKaroId == SeciliKaro.getId())
					return;

				SonKaroX = tileX;
				SonKaroY = tileY;
				SonKaroId = SeciliKaro.getId();

				lvl[tileY][tileX] = SeciliKaro.getId();
			} else {
				int id = lvl[tileY][tileX];
				if (game.getKaroYoneticisi().getTile(id).getKaroTipi() == YOL_KAROSU) {
					if (SeciliKaro.getId() == -1)
						Baslangic = new Yol(tileX, tileY);
					else
						end = new Yol(tileX, tileY);
				}
			}
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640) {
			esyaBari.mouseClicked(x, y);
		} else {
			KaroDegistir(mouseX, mouseY);
		}

	}

	@Override
	public void mouseMoved(int x, int y) {

		if (y >= 640) {
			esyaBari.mouseMoved(x, y);
			drawSelect = false;
		} else {
			drawSelect = true;
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}

	}

	@Override
	public void mouseClicked1 (int x, int y) {
		if (y >= 640)
			esyaBari.mouseClicked(x, y);

	} 

	@Override
	public void mouseReleased(int x, int y) {
		esyaBari.mouseReleased(x, y);

	}

	@Override
	public void mouseDragged(int x, int y) {
		if (y >= 640) {

		} else {
			KaroDegistir(x, y);
		}

	}

	public void TusaBasildi(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_R)
			esyaBari.rotatekarakter();
	}


	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
