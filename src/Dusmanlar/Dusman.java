package Dusmanlar;

import static Yardimcilar.Sabitler.Yon.*;

import java.awt.Rectangle;

import Yoneticiler.DusmanYoneticisi;

public abstract class Dusman {
	protected DusmanYoneticisi DusmanYoneticisi;
	protected float x, y;
	protected Rectangle Sinirlar;
	protected int Can;
	protected int maxCan;
	protected int ID;
	protected int Dusmantipi;
	protected int endDizin;
	protected boolean Yasiyor = true;
	protected int YavasTickLimiti = 120;
	protected int YavasTick = YavasTickLimiti;

	public Dusman(float x, float y, int ID, int Dusmantipi, DusmanYoneticisi DusmanYoneticisi) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.Dusmantipi = Dusmantipi;
		this.DusmanYoneticisi = DusmanYoneticisi;
		Sinirlar = new Rectangle((int) x, (int) y, 32, 32);
		endDizin = -1;
		setBaslangicCan();
	}

	private void setBaslangicCan() {
		Can = Yardimcilar.Sabitler.Dusmanlar.GetBaslangicCan(Dusmantipi);
		maxCan = Can;
	}
	public void hasaraldi(int Hasar) {
		this.Can -= Hasar;
		if (Can <= 0) {
			Yasiyor = false;
			DusmanYoneticisi.OdulOyuncu(Dusmantipi);
		}
	}
	public void Oldu() {
		Yasiyor = false;
		Can = 0;
	}
	public void yavas() {
		YavasTick = 0;
	}
   public void hareket(float Hız, int dir) {
		endDizin = dir;

		if (YavasTick < YavasTickLimiti) {
			YavasTick++;
			Hız *= 0.5f;
		}
		switch (dir) {
		case SOL:
			this.x -= Hız;
			break;
		case YUKARI:
			this.y -= Hız;
			break;
		case SAG:
			this.x += Hız;
			break;
		case ASAGI:
			this.y += Hız;
			break;
		}
		YUKARIyonluHitbox();
	}

	private void YUKARIyonluHitbox() {
		Sinirlar.x = (int) x;
		Sinirlar.y = (int) y;
	}

	public void setPos(int x, int y) {
		
		this.x = x;
		this.y = y;
	}

	public float getCanBarFloat() {
		return Can / (float) maxCan;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getSinirlar() {
		return Sinirlar;
	}

	public int getCan() {
		return Can;
	}

	public int getID() {
		return ID;
	}

	public int getDusmantipi() {
		return Dusmantipi;
	}

	public int getendDizin() {
		return endDizin;
	}

	public boolean Yasiyormu() {
		return Yasiyor;
	}

	public boolean isyavasmı() {
		return YavasTick < YavasTickLimiti;
	}

}
