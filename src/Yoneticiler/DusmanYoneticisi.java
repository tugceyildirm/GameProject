package Yoneticiler;

import static Yardimcilar.Sabitler.Yon.*
;
import static Yardimcilar.Sabitler.Dusmanlar.*;
import static Yardimcilar.Sabitler.Karo.*;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Dusmanlar.Yarasa;
import Objeler.Yol;
import Sahneler.Oynaniyor;
import Yardimcilar.Sabitler.Dusmanlar;
import Yardimcilar.YukleKaydet;
import Dusmanlar.Dusman;
import Dusmanlar.Orc;


public class DusmanYoneticisi {

	
	private Oynaniyor oynaniyor;
	private BufferedImage[] dusmanfotos;
	private ArrayList<Dusman> Dusmanlar= new ArrayList<>();
	private Yol Baslangic, end;
	private int HPbarWidth = 20;
	private BufferedImage yavasEffect;

	public DusmanYoneticisi(Oynaniyor oynaniyor, Yol Baslangic, Yol end) {
		this.oynaniyor = oynaniyor;
		dusmanfotos = new BufferedImage[4];
		this.Baslangic = Baslangic;
		this.end = end;

		yukleEffectfoto();
		yukledusmanfotos();
	}

	private void yukleEffectfoto() {
		yavasEffect = YukleKaydet.resimal().getSubimage(32 * 9, 32 * 2, 32, 32);
	}

	private void yukledusmanfotos() {
		BufferedImage atlas = YukleKaydet.resimal();
		for (int i = 0; i < 2; i++)
			dusmanfotos[i] = atlas.getSubimage(i * 32, 32, 32, 32);

	}

	public void Yukseltme() {

		for (Dusman e : Dusmanlar)
			if (e.Yasiyormu())
				Yukseltmedusmanhareket(e);

	}

	public void Yukseltmedusmanhareket(Dusman e) {
		if (e.getendDizin() == -1)
			setNewYonAndhareket(e);

		int newX = (int) (e.getX() + getHızAndWidth(e.getendDizin(), e.getDusmantipi()));
		int newY = (int) (e.getY() + getHızAndHeight(e.getendDizin(), e.getDusmantipi()));

		if (getKaroTipi(newX, newY) == YOL_KAROSU) {
			e.hareket(GetHız(e.getDusmantipi()), e.getendDizin());
		} else if (isAtend(e)) {
			e.Oldu();
			oynaniyor.canidusur();
		} else {
			setNewYonAndhareket(e);
		}
	}

	private void setNewYonAndhareket(Dusman e) {
		int dir = e.getendDizin();

		int xKord = (int) (e.getX() / 32);
		int yKord = (int) (e.getY() / 32);

		fixdusmanOffsetTile(e, dir, xKord, yKord);

		if (isAtend(e))
			return;

		if (dir == SOL || dir == SAG) {
			int newY = (int) (e.getY() + getHızAndHeight(YUKARI, e.getDusmantipi()));
			if (getKaroTipi((int) e.getX(), newY) == YOL_KAROSU)
				e.hareket(GetHız(e.getDusmantipi()), YUKARI);
			else
				e.hareket(GetHız(e.getDusmantipi()), ASAGI);
		} else {
			int newX = (int) (e.getX() + getHızAndWidth(SAG, e.getDusmantipi()));
			if (getKaroTipi(newX, (int) e.getY()) == YOL_KAROSU)
				e.hareket(GetHız(e.getDusmantipi()), SAG);
			else
				e.hareket(GetHız(e.getDusmantipi()), SOL);

		}

	}

	private void fixdusmanOffsetTile(Dusman e, int dir, int xKord, int yKord) {
		switch (dir) {
		case SAG:
			if (xKord < 19)
				xKord++;
			break;
		case ASAGI:
			if (yKord < 19)
				yKord++;
			break;
		}

		e.setPos(xKord * 32, yKord * 32);

	}

	private boolean isAtend(Dusman e) {
		if (e.getX() == end.getxKord() * 32)
			if (e.getY() == end.getyKord() * 32)
				return true;
		return false;
	}

	private int getKaroTipi(int x, int y) {
		return oynaniyor.getKaroTipi(x, y);
	}

	private float getHızAndHeight(int dir, int Dusmantipi) {
		if (dir == YUKARI)
			return -GetHız(Dusmantipi);
		else if (dir == ASAGI)
			return GetHız(Dusmantipi) + 32;

		return 0;
	}

	private float getHızAndWidth(int dir, int Dusmantipi) {
		if (dir == SOL)
			return -GetHız(Dusmantipi);
		else if (dir == SAG)
			return GetHız(Dusmantipi) + 32;

		return 0;
	}

	public void canlandirdusman(int sonrakidusman) {
		adddusman(sonrakidusman);
	}

	public void adddusman(int Dusmantipi) {

		int x = Baslangic.getxKord() * 32;
		int y = Baslangic.getyKord() * 32;

		switch (Dusmantipi) {
		case ORC:
			Dusmanlar.add(new Orc(x, y, 0, this));
			break;
		case YARASA:
			Dusmanlar.add(new Yarasa(x, y, 0, this));
			break;
	
		}

	}

	public void draw(Graphics g) {
		for (Dusman e : Dusmanlar) {
			if (e.Yasiyormu()) {
				drawdusman(e, g);
				drawCanBar(e, g);
				drawEffects(e, g);
			}
		}
	}

	private void drawEffects(Dusman e, Graphics g) {
		if (e.isyavasmı())
			g.drawImage(yavasEffect, (int) e.getX(), (int) e.getY(), null);

	}

	private void drawCanBar(Dusman e, Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) e.getX() + 16 - (getNewBarWidth(e) / 2), (int) e.getY() - 10, getNewBarWidth(e), 3);

	}

	private int getNewBarWidth(Dusman e) {
		return (int) (HPbarWidth * e.getCanBarFloat());
	}

	private void drawdusman(Dusman e, Graphics g) {
		g.drawImage(dusmanfotos[e.getDusmantipi()], (int) e.getX(), (int) e.getY(), null);
	}

	public ArrayList<Dusman> getDusmanlar() {
		return Dusmanlar;
	}

	public int getAmountOfYasiyorDusmanlar() {
		int Boyut = 0;
		for (Dusman e : Dusmanlar)
			if (e.Yasiyormu())
				Boyut++;

		return Boyut;
	}

	public void OdulOyuncu(int Dusmantipi) {
		oynaniyor.OdulOyuncu(Dusmantipi);
	}

	public void Sıfırla() {
		Dusmanlar.clear();
	}

}
