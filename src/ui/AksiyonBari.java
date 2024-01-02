package ui;

import static main.OyunIstatislikleri.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import Objeler.Kule;
import Sahneler.Oynaniyor;
import Yardimcilar.Sabitler.Kuleler;

public class AksiyonBari extends Bar {

	private Oynaniyor oynaniyor;
	private Buton bMenu, bPause;
	private Buton[] KuleButonlar;
	private Kule seciliKule;
	private Kule gosterlimisKule;
	private Buton satKule, YukseltmeKule;
	private DecimalFormat formatter;
	private int altın = 100;
	private boolean KuleFiyatiGoster;
	private int KuleFiyatTipi;
	private int canlar = 25;

	public AksiyonBari(int x, int y, int width, int height, Oynaniyor oynaniyor) {
		super(x, y, width, height);
		this.oynaniyor = oynaniyor;
		formatter = new DecimalFormat("0.0");

		initButonlar();
	}

	public void SıfırlaHersey() {
		canlar = 25;
		KuleFiyatTipi = 0;
		KuleFiyatiGoster = false;
		altın = 100;
		seciliKule = null;
		gosterlimisKule = null;
	}

	private void initButonlar() {

		bMenu = new Buton("Menu", 2, 642, 100, 30);
		bPause = new Buton("Pause", 2, 682, 100, 30);

		KuleButonlar = new Buton[3];

		int w = 50;
		int h = 50;
		int xBaslangic = 110;
		int yBaslangic = 650;
		int xOfset = (int) (w * 1.1f);

		for (int i = 0; i < KuleButonlar.length; i++)
			KuleButonlar[i] = new Buton("", xBaslangic + xOfset * i, yBaslangic, w, h, i);

		satKule = new Buton("sat", 420, 702, 80, 25);
		YukseltmeKule = new Buton("Yukselt", 545, 702, 80, 25);

	}

	public void canidusur() {
		canlar--;
		if (canlar <= 0)
			SetOyunDurumu(OYUN_BITTI);
	}

	private void Butonciz(Graphics g) {
		bMenu.draw(g);
		bPause.draw(g);

		for (Buton b : KuleButonlar) {
			g.setColor(Color.gray);
			g.fillRect(b.x, b.y, b.width, b.height);
			g.drawImage(oynaniyor.getKuleManager().getKulefotos()[b.getId()], b.x, b.y, b.width, b.height, null);
			drawButtonFeedback(g, b);
		}
	}

	public void draw(Graphics g) {

		
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		
		Butonciz(g);

		
		drawgosterlimisKule(g);

		
		drawDalgaBilgisi(g);

		drawAltinMiktari(g);

		
		if (KuleFiyatiGoster)
			drawKuleFiyat(g);

		
		if (oynaniyor.isoyunDurdu()) {
			g.setColor(Color.black);
			g.drawString("Game is Paused!", 110, 790);
		}

		
		g.setColor(Color.black);
		g.drawString("canlar: " + canlar, 110, 750);

	}

	private void drawKuleFiyat(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(280, 650, 120, 50);
		g.setColor(Color.black);
		g.drawRect(280, 650, 120, 50);

		g.drawString("" + getKuleFiyatIsim(), 285, 670);
		g.drawString("Fiyat: " + getKuleFiyatFiyat() + "g", 285, 695);

		
		if (KuleFiyatiAltindanFazlaMi()) {
			g.setColor(Color.RED);
			g.drawString("Can't Afford", 270, 725);

		}

	}

	private boolean KuleFiyatiAltindanFazlaMi() {
		return getKuleFiyatFiyat() > altın;
	}

	private String getKuleFiyatIsim() {
		return Yardimcilar.Sabitler.Kuleler.GetIsim(KuleFiyatTipi);
	}

	private int getKuleFiyatFiyat() {
		return Yardimcilar.Sabitler.Kuleler.GetKuleFiyat(KuleFiyatTipi);
	}

	private void drawAltinMiktari(Graphics g) {
		g.drawString("altın: " + altın + "g", 110, 725);

	}

	private void drawDalgaBilgisi(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		drawDalgaZamanlayiciInfo(g);
		drawDusmanlarSOLInfo(g);
		drawWavesSOLInfo(g);

	}

	private void drawWavesSOLInfo(Graphics g) {
		int current = oynaniyor.getWaveManager().getWaveIndex();
		int Boyut = oynaniyor.getWaveManager().getWaves().size();
		g.drawString("Wave " + (current + 1) + " / " + Boyut, 425, 770);

	}

	private void drawDusmanlarSOLInfo(Graphics g) {
		int remaining = oynaniyor.getdusmanManger().getAmountOfYasiyorDusmanlar();
		g.drawString("Dusmanlar SOL: " + remaining, 425, 790);
	}

	private void drawDalgaZamanlayiciInfo(Graphics g) {
		if (oynaniyor.getWaveManager().isDalgaZamanlayiciBaslangiced()) {

			float timeSOL = oynaniyor.getWaveManager().getTimeSOL();
			String formattedText = formatter.format(timeSOL);
			g.drawString("Time SOL: " + formattedText, 425, 750);
		}
	}

	private void drawgosterlimisKule(Graphics g) {
		if (gosterlimisKule != null) {
			g.setColor(Color.gray);
			g.fillRect(410, 645, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410, 645, 220, 85);
			g.drawRect(420, 650, 50, 50);
			g.drawImage(oynaniyor.getKuleManager().getKulefotos()[gosterlimisKule.getKuleTipi()], 420, 650, 50, 50, null);
			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			g.drawString("" + Kuleler.GetIsim(gosterlimisKule.getKuleTipi()), 480, 660);
			g.drawString("ID: " + gosterlimisKule.getId(), 480, 675);
			g.drawString("asama: " + gosterlimisKule.getasama(), 560, 660);
			drawgosterlimisKuleBorder(g);
			drawgosterlimisKulemesafe(g);

			satKule.draw(g);
			drawButtonFeedback(g, satKule);

	
			if (gosterlimisKule.getasama() < 3 && altın >= getYukseltAmount(gosterlimisKule)) {
				YukseltmeKule.draw(g);
				drawButtonFeedback(g, YukseltmeKule);
			}

			if (satKule.isMouseOver()) {
				g.setColor(Color.red);
				g.drawString("sat for: " + getsatAmount(gosterlimisKule) + "g", 480, 695);
			} else if (YukseltmeKule.isMouseOver() && altın >= getYukseltAmount(gosterlimisKule)) {
				g.setColor(Color.blue);
				g.drawString("Yukselt for: " + getYukseltAmount(gosterlimisKule) + "g", 480, 695);
			}

		}

	}

	private int getYukseltAmount(Kule gosterlimisKule) {
		return (int) (Yardimcilar.Sabitler.Kuleler.GetKuleFiyat(gosterlimisKule.getKuleTipi()) * 0.3f);
	}

	private int getsatAmount(Kule gosterlimisKule) {
		int YukseltFiyat = (gosterlimisKule.getasama() - 1) * getYukseltAmount(gosterlimisKule);
		YukseltFiyat *= 0.5f;

		return Yardimcilar.Sabitler.Kuleler.GetKuleFiyat(gosterlimisKule.getKuleTipi()) / 2 + YukseltFiyat;
	}

	private void drawgosterlimisKulemesafe(Graphics g) {
		g.setColor(Color.white);
		g.drawOval(gosterlimisKule.getX() + 16 - (int) (gosterlimisKule.getmesafe() * 2) / 2, gosterlimisKule.getY() + 16 - (int) (gosterlimisKule.getmesafe() * 2) / 2, (int) gosterlimisKule.getmesafe() * 2,
				(int) gosterlimisKule.getmesafe() * 2);

	}

	private void drawgosterlimisKuleBorder(Graphics g) {

		g.setColor(Color.CYAN);
		g.drawRect(gosterlimisKule.getX(), gosterlimisKule.getY(), 32, 32);

	}

	public void gosterKule(Kule t) {
		gosterlimisKule = t;
	}

	private void satKuleTiklandi() {
		oynaniyor.KuleKaldır(gosterlimisKule);
		altın += Yardimcilar.Sabitler.Kuleler.GetKuleFiyat(gosterlimisKule.getKuleTipi()) / 2;

		int YukseltFiyat = (gosterlimisKule.getasama() - 1) * getYukseltAmount(gosterlimisKule);
		YukseltFiyat *= 0.5f;
		altın += YukseltFiyat;

		gosterlimisKule = null;

	}

	private void YukseltmeKuleTiklandi() {
		oynaniyor.YukseltmeKule(gosterlimisKule);
		altın -= getYukseltAmount(gosterlimisKule);

	}

	private void togglePause() {
		oynaniyor.setoyunDurdu(!oynaniyor.isoyunDurdu());

		if (oynaniyor.isoyunDurdu())
			bPause.setText("Unpause");
		else
			bPause.setText("Pause");

	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getSinirlar().contains(x, y))
			SetOyunDurumu(MENU);
		else if (bPause.getSinirlar().contains(x, y))
			togglePause();
		else {

			if (gosterlimisKule != null) {
				if (satKule.getSinirlar().contains(x, y)) {
					satKuleTiklandi();

					return;
				} else if (YukseltmeKule.getSinirlar().contains(x, y) && gosterlimisKule.getasama() < 3 && altın >= getYukseltAmount(gosterlimisKule)) {
					YukseltmeKuleTiklandi();
					return;
				}
			}

			for (Buton b : KuleButonlar) {
				if (b.getSinirlar().contains(x, y)) {
					if (!isaltınEnoughForKule(b.getId()))
						return;

					seciliKule = new Kule(0, 0, -1, b.getId());
					oynaniyor.setseciliKule(seciliKule);
					return;
				}
			}
		}

	}

	private boolean isaltınEnoughForKule(int KuleTipi) {

		return altın >= Yardimcilar.Sabitler.Kuleler.GetKuleFiyat(KuleTipi);
	}

	public void mouseMoved(int x, int y) {
		bMenu.MouseUzerinde(false);
		bPause.MouseUzerinde(false);
		KuleFiyatiGoster = false;
		satKule.MouseUzerinde(false);
		YukseltmeKule.MouseUzerinde(false);

		for (Buton b : KuleButonlar)
			b.MouseUzerinde(false);

		if (bMenu.getSinirlar().contains(x, y))
			bMenu.MouseUzerinde(true);
		else if (bPause.getSinirlar().contains(x, y))
			bPause.MouseUzerinde(true);
		else {

			if (gosterlimisKule != null) {
				if (satKule.getSinirlar().contains(x, y)) {
					satKule.MouseUzerinde(true);
					return;
				} else if (YukseltmeKule.getSinirlar().contains(x, y) && gosterlimisKule.getasama() < 3) {
					YukseltmeKule.MouseUzerinde(true);
					return;
				}
			}

			for (Buton b : KuleButonlar)
				if (b.getSinirlar().contains(x, y)) {
					b.MouseUzerinde(true);
					KuleFiyatiGoster = true;
					KuleFiyatTipi = b.getId();
					return;
				}
		}
	}

	
	public void mouseReleased(int x, int y) {
		bMenu.SıfırlaBooleans();
		bPause.SıfırlaBooleans();
		for (Buton b : KuleButonlar)
			b.SıfırlaBooleans();
		satKule.SıfırlaBooleans();
		YukseltmeKule.SıfırlaBooleans();

	}

	public void payForKule(int KuleTipi) {
		this.altın -= Yardimcilar.Sabitler.Kuleler.GetKuleFiyat(KuleTipi);

	}

	public void addaltın(int getOdul) {
		this.altın += getOdul;
	}

	public int getcanlar() {
		return canlar;
	}

}
