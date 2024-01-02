package Yoneticiler;

import static Yardimcilar.Sabitler.Fuzeler.*;
import static Yardimcilar.Sabitler.Kuleler.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Dusmanlar.Dusman;
import Objeler.Fuze;
import Objeler.Kule;
import Sahneler.Oynaniyor;
import Yardimcilar.Sabitler.Fuzeler;
import Yardimcilar.YukleKaydet;

public class FuzeYoneticisi {

	private Oynaniyor oynaniyor;
	private ArrayList<Fuze> fuzes = new ArrayList<>();
	private ArrayList<Explosion> explosions = new ArrayList<>();
	private BufferedImage[] proj_fotos, explo_fotos;
	private int proj_id = 0;

	public FuzeYoneticisi(Oynaniyor oynaniyor) {
		this.oynaniyor = oynaniyor;
		importfotos();

	}

	private void importfotos() {
		BufferedImage atlas = YukleKaydet.resimal();
		proj_fotos = new BufferedImage[3];

		for (int i = 0; i < 3; i++)
			proj_fotos[i] = atlas.getSubimage((7 + i) * 32, 32, 32, 32);
		importExplosion(atlas);
	}

	private void importExplosion(BufferedImage atlas) {
		explo_fotos = new BufferedImage[7];

		for (int i = 0; i < 7; i++)
			explo_fotos[i] = atlas.getSubimage(i * 32, 32 * 2, 32, 32);

	}

	public void newProjectile(Kule t, Dusman e) {
		int type = getProjType(t);

		int xDist = (int) (t.getX() - e.getX());
		int yDist = (int) (t.getY() - e.getY());
		int totDist = Math.abs(xDist) + Math.abs(yDist);

		float xPer = (float) Math.abs(xDist) / totDist;

		float xHız = xPer * Yardimcilar.Sabitler.Fuzeler.GetHız(type);
		float yHız = Yardimcilar.Sabitler.Fuzeler.GetHız(type) - xHız;

		if (t.getX() > e.getX())
			xHız *= -1;
		if (t.getY() > e.getY())
			yHız *= -1;

		float rotate = 0;

		if (type == OK) {
			float arcValue = (float) Math.atan(yDist / (float) xDist);
			rotate = (float) Math.toDegrees(arcValue);

			if (xDist < 0)
				rotate += 180;
		}

		fuzes.add(new Fuze(t.getX() + 16, t.getY() + 16, xHız, yHız, t.getHasar(), rotate, proj_id++, type));

	}

	public void Yukseltme() {
		for (Fuze p : fuzes)
			if (p.AktifMi()) {
				p.hareket();
				if (isProjHittingdusman(p)) {
					p.setAktif(false);
					if (p.getFuzeTipi() == BOMBA) {
						explosions.add(new Explosion(p.getPos()));
						explodeOnDusmanlar(p);
					}
				} else if (isProjOutsideSinirlar(p)) {
					p.setAktif(false);
				}
			}

		for (Explosion e : explosions)
			if (e.getIndex() < 7)
				e.Yukseltme();
	}

	private void explodeOnDusmanlar(Fuze p) {
		for (Dusman e : oynaniyor.getdusmanManger().getDusmanlar()) {
			if (e.Yasiyormu()) {
				float radius = 40.0f;

				float xDist = Math.abs(p.getPos().x - e.getX());
				float yDist = Math.abs(p.getPos().y - e.getY());

				float realDist = (float) Math.hypot(xDist, yDist);

				if (realDist <= radius)
					e.hasaraldi(p.getHasar());
			}

		}

	}

	private boolean isProjHittingdusman(Fuze p) {
		for (Dusman e : oynaniyor.getdusmanManger().getDusmanlar()) {
			if (e.Yasiyormu())
				if (e.getSinirlar().contains(p.getPos())) {
					e.hasaraldi(p.getHasar());
					if (p.getFuzeTipi() == ZINCIR)
						e.yavas();

					return true;
				}
		}
		return false;
	}

	private boolean isProjOutsideSinirlar(Fuze p) {
		if (p.getPos().x >= 0)
			if (p.getPos().x <= 640)
				if (p.getPos().y >= 0)
					if (p.getPos().y <= 800)
						return false;
		return true;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		for (Fuze p : fuzes)
			if (p.AktifMi()) {
				if (p.getFuzeTipi() == OK) {
					g2d.translate(p.getPos().x, p.getPos().y);
					g2d.rotate(Math.toRadians(p.getYonleme()));
					g2d.drawImage(proj_fotos[p.getFuzeTipi()], -16, -16, null);
					g2d.rotate(-Math.toRadians(p.getYonleme()));
					g2d.translate(-p.getPos().x, -p.getPos().y);
				} else {
					g2d.drawImage(proj_fotos[p.getFuzeTipi()], (int) p.getPos().x - 16, (int) p.getPos().y - 16, null);
				}
			}

		drawExplosions(g2d);

	}

	private void drawExplosions(Graphics2D g2d) {
		for (Explosion e : explosions)
			if (e.getIndex() < 7)
				g2d.drawImage(explo_fotos[e.getIndex()], (int) e.getPos().x - 16, (int) e.getPos().y - 16, null);
	}

	private int getProjType(Kule t) {
		switch (t.getKuleTipi()) {
		case OKCU:
			return OK;
		case TOP:
			return BOMBA;
		case BUYUCU:
			return ZINCIR;
		}
		return 0;
	}

	public class Explosion {

		private Point2D.Float pos;
		private int exploTick, exploIndex;

		public Explosion(Point2D.Float pos) {
			this.pos = pos;
		}

		public void Yukseltme() {
			exploTick++;
			if (exploTick >= 6) {
				exploTick = 0;
				exploIndex++;
			}
		}

		public int getIndex() {
			return exploIndex;
		}

		public Point2D.Float getPos() {
			return pos;
		}
	}

	public void Sıfırla() {
		fuzes.clear();
		explosions.clear();

		proj_id = 0;
	}

}
