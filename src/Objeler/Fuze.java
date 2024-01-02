package Objeler;

import java.awt.geom.Point2D;

public class Fuze {
	private Point2D.Float pos;
	private int id, FuzeTipi, Hasar;
	private float xHız, yHız, Yonleme;
	private boolean Aktif = true;

	public Fuze(float x, float y, float xHız, float yHız, int Hasar, float Yonleme, int id, int FuzeTipi) {
		pos = new Point2D.Float(x, y);
		this.xHız = xHız;
		this.yHız = yHız;
		this.Hasar = Hasar;
		this.Yonleme = Yonleme;
		this.id = id;
		this.FuzeTipi = FuzeTipi;
	}

	public void hareket() {
		pos.x += xHız;
		pos.y += yHız;
	}

	public Point2D.Float getPos() {
		return pos;
	}

	public void setPos(Point2D.Float pos) {
		this.pos = pos;
	}

	public int getId() {
		return id;
	}

	public int getFuzeTipi() {
		return FuzeTipi;
	}

	public boolean AktifMi() {
		return Aktif;
	}

	public void setAktif(boolean Aktif) {
		this.Aktif = Aktif;
	}

	public int getHasar() {
		return Hasar;
	}

	public float getYonleme() {
		return Yonleme;
	}

}
