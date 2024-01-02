package Yoneticiler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Dusmanlar.Dusman;
import Objeler.Kule;
import Sahneler.Oynaniyor;
import Yardimcilar.YukleKaydet;

public class KuleYoneticisi {

	private Oynaniyor oynaniyor;
	private BufferedImage[] Kulefotos;
	private ArrayList<Kule> Kuleler = new ArrayList<>();
	private int KuleAmount = 0;

	public KuleYoneticisi(Oynaniyor oynaniyor) {
		this.oynaniyor = oynaniyor;
		yukleKulefotos();
	}
	private void yukleKulefotos() {
		BufferedImage atlas = YukleKaydet.resimal();
		Kulefotos = new BufferedImage[3];
		for (int i = 0; i < 3; i++)
			Kulefotos[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);
	}

	public void addKule(Kule seciliKule, int xPos, int yPos) {
		Kuleler.add(new Kule(xPos, yPos, KuleAmount++, seciliKule.getKuleTipi()));
	}

	public void KuleKaldır(Kule gosterlimisKule) {
		for (int i = 0; i < Kuleler.size(); i++)
			if (Kuleler.get(i).getId() == gosterlimisKule.getId())
				Kuleler.remove(i);
	}

	public void YukseltmeKule(Kule gosterlimisKule) {
		for (Kule t : Kuleler)
			if (t.getId() == gosterlimisKule.getId())
				t.YukseltmeKule();
	}
	public void Yukseltme() {
		for (Kule t : Kuleler) {
			t.Yukseltme();
			attackdusmanIfClose(t);
		}
	}
	private void attackdusmanIfClose(Kule t) {
		for (Dusman e : oynaniyor.getdusmanManger().getDusmanlar()) {
			if (e.Yasiyormu())
				if (isdusmanInmesafe(t, e)) {
					if (t.isBeklemeSuresiOver()) {
						oynaniyor.dusmanavur(t, e);
						t.SıfırlaBeklemeSuresi();
					}
				} else {
					
			}
		}

	}
	private boolean isdusmanInmesafe(Kule t, Dusman e) {
		int mesafe = Yardimcilar.Yardim.UzaklikHesapla(t.getX(), t.getY(), e.getX(), e.getY());
		return mesafe < t.getmesafe();
	}

	public void draw(Graphics g) {
		for (Kule t : Kuleler)
			g.drawImage(Kulefotos[t.getKuleTipi()], t.getX(), t.getY(), null);
	}

	public Kule getKuleAt(int x, int y) {
		for (Kule t : Kuleler)
			if (t.getX() == x)
				if (t.getY() == y)
					return t;
		return null;
	}
	public BufferedImage[] getKulefotos() {
		return Kulefotos;
	}
	public void Sıfırla() {
		Kuleler.clear();
		KuleAmount = 0;
	}

}
