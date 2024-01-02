package Objeler;

import static Yardimcilar.Sabitler.Kuleler.*;

public class Kule {

	private int x, y, id, KuleTipi, cdTick, Hasar;
	private float mesafe, BeklemeSuresi;
	private int asama;

	public Kule(int x, int y, int id, int KuleTipi) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.KuleTipi = KuleTipi;
		asama = 1;
		setStandartHasar();
		setStandartmesafe();
		setStandartBeklemeSuresi();
	}

	public void Yukseltme() {
		cdTick++;
	}

	public void YukseltmeKule() {
		this.asama++;

		switch (KuleTipi) {
		case OKCU:
			Hasar += 2;
			mesafe += 20;
			BeklemeSuresi -= 5;
			break;
		case TOP:
			Hasar += 5;
			mesafe += 20;
			BeklemeSuresi -= 15;
			break;
		case BUYUCU:
			mesafe += 20;
			BeklemeSuresi -= 10;
			break;
		}
	}

	public boolean isBeklemeSuresiOver() {

		return cdTick >= BeklemeSuresi;
	}

	public void SıfırlaBeklemeSuresi() {
		cdTick = 0;
	}

	private void setStandartBeklemeSuresi() {
		BeklemeSuresi = Yardimcilar.Sabitler.Kuleler.GetStandartBeklemeSuresi(KuleTipi);

	}

	private void setStandartmesafe() {
		mesafe = Yardimcilar.Sabitler.Kuleler.GetStandartmesafe(KuleTipi);

	}

	private void setStandartHasar() {
		Hasar = Yardimcilar.Sabitler.Kuleler.GetBaslangicHasar(KuleTipi);

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKuleTipi() {
		return KuleTipi;
	}

	public void setKuleTipi(int KuleTipi) {
		this.KuleTipi = KuleTipi;
	}

	public int getHasar() {
		return Hasar;
	}

	public float getmesafe() {
		return mesafe;
	}

	public float getBeklemeSuresi() {
		return BeklemeSuresi;
	}

	public int getasama() {
		return asama;
	}

}
