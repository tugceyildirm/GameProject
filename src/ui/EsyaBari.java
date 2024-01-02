package ui;

import static main.OyunIstatislikleri.MENU;
import static main.OyunIstatislikleri.SetOyunDurumu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Objeler.Karo;
import Sahneler.Duzenleme;
import Yardimcilar.YukleKaydet;

public class EsyaBari extends Bar {
	private Duzenleme duzenleme;
	private Buton bMenu, bkaydet;
	private Buton bPathBaslangic, bPathend;
	private BufferedImage pathBaslangic, pathend;
	private Karo SeciliKaro;

	private Map<Buton, ArrayList<Karo>> map = new HashMap<Buton, ArrayList<Karo>>();

	private Buton bGrass, bWater, bRoadS, bRoadC, bWaterC, bWaterB, bWaterI;
	private Buton currentButton;
	private int currentIndex = 0;

	public EsyaBari(int x, int y, int width, int height, Duzenleme duzenleme) {
		super(x, y, width, height);
		this.duzenleme = duzenleme;
		initPathfotos();
		initButonlar();
	}

	private void initPathfotos() {
		pathBaslangic = YukleKaydet.resimal().getSubimage(7 * 32, 2 * 32, 32, 32);
		pathend = YukleKaydet.resimal().getSubimage(8 * 32, 2 * 32, 32, 32);
	}

	private void initButonlar() {

		bMenu = new Buton("Menu", 2, 642, 100, 30);
		bkaydet = new Buton("kaydet", 2, 674, 100, 30);

		int w = 50;
		int h = 50;
		int xBaslangic = 110;
		int yBaslangic = 650;
		int xOfset = (int) (w * 1.1f);
		int i = 0;

		bGrass = new Buton("Grass", xBaslangic, yBaslangic, w, h, i++);
		bWater = new Buton("Water", xBaslangic + xOfset, yBaslangic, w, h, i++);

		initMapButton(bRoadS, duzenleme.getGame().getKaroYoneticisi().getRoadsS(), xBaslangic, yBaslangic, xOfset, w, h, i++);
		initMapButton(bRoadC, duzenleme.getGame().getKaroYoneticisi().getRoadsC(), xBaslangic, yBaslangic, xOfset, w, h, i++);
		initMapButton(bWaterC, duzenleme.getGame().getKaroYoneticisi().getCorners(), xBaslangic, yBaslangic, xOfset, w, h, i++);
		initMapButton(bWaterB, duzenleme.getGame().getKaroYoneticisi().getBeaches(), xBaslangic, yBaslangic, xOfset, w, h, i++);
		initMapButton(bWaterI, duzenleme.getGame().getKaroYoneticisi().getIslands(), xBaslangic, yBaslangic, xOfset, w, h, i++);

		bPathBaslangic = new Buton("PathBaslangic", xBaslangic, yBaslangic + xOfset, w, h, i++);
		bPathend = new Buton("Pathend", xBaslangic + xOfset, yBaslangic + xOfset, w, h, i++);

	}

	private void initMapButton(Buton b, ArrayList<Karo> liste, int x, int y, int xOff, int w, int h, int id) {
		b = new Buton("", x + xOff * id, y, w, h, id);
		map.put(b, liste);
	}

	private void kaydetLevel() {
		duzenleme.kaydetLevel();
	}

	public void rotatekarakter() {

		currentIndex++;
		if (currentIndex >= map.get(currentButton).size())
			currentIndex = 0;
		SeciliKaro = map.get(currentButton).get(currentIndex);
		duzenleme.setSeciliKaro(SeciliKaro);

	}

	public void draw(Graphics g) {

		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		
		Butonciz(g);
	}

	private void Butonciz(Graphics g) {
		bMenu.draw(g);
		bkaydet.draw(g);

		drawPathButton(g, bPathBaslangic, pathBaslangic);
		drawPathButton(g, bPathend, pathend);



		drawNormalButton(g, bGrass);
		drawNormalButton(g, bWater);
		drawSeciliKaro(g);
		drawMapButonlar(g);

	}

	private void drawPathButton(Graphics g, Buton b, BufferedImage foto) {

		g.drawImage(foto, b.x, b.y, b.width, b.height, null);
		drawButtonFeedback(g, b);

	}

	private void drawNormalButton(Graphics g, Buton b) {
		g.drawImage(getButtfoto(b.getId()), b.x, b.y, b.width, b.height, null);
		drawButtonFeedback(g, b);

	}

	private void drawMapButonlar(Graphics g) {
		for (Map.Entry<Buton, ArrayList<Karo>> entry : map.entrySet()) {
			Buton b = entry.getKey();
			BufferedImage foto = entry.getValue().get(0).getkarakter();

			g.drawImage(foto, b.x, b.y, b.width, b.height, null);
			drawButtonFeedback(g, b);
		}

	}

	

	private void drawSeciliKaro(Graphics g) {

		if (SeciliKaro != null) {
			g.drawImage(SeciliKaro.getkarakter(), 550, 650, 50, 50, null);
			g.setColor(Color.black);
			g.drawRect(550, 650, 50, 50);
		}

	}

	public BufferedImage getButtfoto(int id) {
		return duzenleme.getGame().getKaroYoneticisi().getkarakter(id);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getSinirlar().contains(x, y))
			SetOyunDurumu(MENU);
		else if (bkaydet.getSinirlar().contains(x, y))
			kaydetLevel();
		else if (bWater.getSinirlar().contains(x, y)) {
			SeciliKaro = duzenleme.getGame().getKaroYoneticisi().getTile(bWater.getId());
			duzenleme.setSeciliKaro(SeciliKaro);
			return;
		} else if (bGrass.getSinirlar().contains(x, y)) {
			SeciliKaro = duzenleme.getGame().getKaroYoneticisi().getTile(bGrass.getId());
			duzenleme.setSeciliKaro(SeciliKaro);
			return;

		} else if (bPathBaslangic.getSinirlar().contains(x, y)) {
			SeciliKaro = new Karo(pathBaslangic, -1, -1);
			duzenleme.setSeciliKaro(SeciliKaro);

		} else if (bPathend.getSinirlar().contains(x, y)) {
			SeciliKaro = new Karo(pathend, -2, -2);
			duzenleme.setSeciliKaro(SeciliKaro);
		} else {
			for (Buton b : map.keySet())
				if (b.getSinirlar().contains(x, y)) {
					SeciliKaro = map.get(b).get(0);
					duzenleme.setSeciliKaro(SeciliKaro);
					currentButton = b;
					currentIndex = 0;
					return;
				}
		}

	}

	public void mouseMoved(int x, int y) {
		bMenu.MouseUzerinde(false);
		bkaydet.MouseUzerinde(false);
		bWater.MouseUzerinde(false);
		bGrass.MouseUzerinde(false);
		bPathBaslangic.MouseUzerinde(false);
		bPathend.MouseUzerinde(false);

		for (Buton b : map.keySet())
			b.MouseUzerinde(false);

		if (bMenu.getSinirlar().contains(x, y))
			bMenu.MouseUzerinde(true);
		else if (bkaydet.getSinirlar().contains(x, y))
			bkaydet.MouseUzerinde(true);
		else if (bWater.getSinirlar().contains(x, y))
			bWater.MouseUzerinde(true);
		else if (bGrass.getSinirlar().contains(x, y))
			bGrass.MouseUzerinde(true);
		else if (bPathBaslangic.getSinirlar().contains(x, y))
			bPathBaslangic.MouseUzerinde(true);
		else if (bPathend.getSinirlar().contains(x, y))
			bPathend.MouseUzerinde(true);
		else {
			for (Buton b : map.keySet())
				if (b.getSinirlar().contains(x, y)) {
					b.MouseUzerinde(true);
					return;
				}
		}

	}

	public void mouseClicked1 (int x, int y) {
		if (bMenu.getSinirlar().contains(x, y))
			bMenu.setmouseClicked(true);
		else if (bkaydet.getSinirlar().contains(x, y))
			bkaydet.setmouseClicked(true);
		else if (bWater.getSinirlar().contains(x, y))
			bWater.setmouseClicked(true);
		else if (bGrass.getSinirlar().contains(x, y))
			bGrass.setmouseClicked(true);
		else if (bPathBaslangic.getSinirlar().contains(x, y))
			bPathBaslangic.setmouseClicked(true);
		else if (bPathend.getSinirlar().contains(x, y))
			bPathend.setmouseClicked(true);
		else {
			for (Buton b : map.keySet())
				if (b.getSinirlar().contains(x, y)) {
					b.setmouseClicked(true);
					return;
				}
		}
	}

	public void mouseReleased(int x, int y) {
		bMenu.SıfırlaBooleans();
		bkaydet.SıfırlaBooleans();
		bGrass.SıfırlaBooleans();
		bWater.SıfırlaBooleans();
		bPathBaslangic.SıfırlaBooleans();
		bPathend.SıfırlaBooleans();
		for (Buton b : map.keySet())
			b.SıfırlaBooleans();

	}

	public BufferedImage getBaslangicPathfoto() {
		return pathBaslangic;
	}

	public BufferedImage getendPathfoto() {
		return pathend;
	}

}
