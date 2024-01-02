package Sahneler;

import static Yardimcilar.Sabitler.Karo.CIMEN_KAROSU;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Dusmanlar.Dusman;
import Objeler.Yol;
import Objeler.Kule;
import Yardimcilar.YukleKaydet;
import Yoneticiler.DusmanYoneticisi;
import Yoneticiler.FuzeYoneticisi;
import Yoneticiler.KuleYoneticisi;
import Yoneticiler.DalgaYoneticisi;
import main.Game;
import ui.AksiyonBari;

public class Oynaniyor extends OyunEkrani implements SahneMethotlari {

	private int[][] lvl;

	private AksiyonBari aksiyonBari;
	private int mouseX, mouseY;
	private DusmanYoneticisi DusmanYoneticisi;
	private KuleYoneticisi kuleYoneticisi;
	private FuzeYoneticisi FuzeYoneticisi;
	private DalgaYoneticisi dalgaYoneticisi;
	private Yol Baslangic, end;
	private Kule seciliKule;
	private int altınTick;
	private boolean oyunDurdu;

	public Oynaniyor(Game game) {
		super(game);
		yukleStandartLevel();

		aksiyonBari = new AksiyonBari(0, 640, 640, 160, this);
		DusmanYoneticisi = new DusmanYoneticisi(this, Baslangic, end);
		kuleYoneticisi = new KuleYoneticisi(this);
		FuzeYoneticisi = new FuzeYoneticisi(this);
		dalgaYoneticisi = new DalgaYoneticisi(this);
	}

	private void yukleStandartLevel() {
		lvl = YukleKaydet.GetLevelData("new_level");
		ArrayList<Yol> puanlar = YukleKaydet.GetLevelyolpuanlari("new_level");
		Baslangic = puanlar.get(0);
		end = puanlar.get(1);
	}

	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	public void Yukseltme() {
		if (!oyunDurdu) {
			YukseltmeTick();
			dalgaYoneticisi.Yukseltme();
			altınTick++;
			if (altınTick % (60 * 3) == 0)
				aksiyonBari.addaltın(1);

			if (ButunDusmanlarOlduMu()) {
				if (DahaDalgaVarmı()) {
					dalgaYoneticisi.BaslangicDalgaZamanlayici();
					if (DalgaZamanayiciBittiMi()) {
						dalgaYoneticisi.DalgaIndeksiniArttır();
						DusmanYoneticisi.getDusmanlar().clear();
						dalgaYoneticisi.SıfırladusmanIndex();

					}
				}
			}

			if (YeniDusmanZamaniMi()) {
				if (!dalgaYoneticisi.DalgaZamanayiciBittiMi())
					canlandirdusman();
			}

			DusmanYoneticisi.Yukseltme();
			kuleYoneticisi.Yukseltme();
			FuzeYoneticisi.Yukseltme();
		}

	}

	private boolean DalgaZamanayiciBittiMi() {

		return dalgaYoneticisi.DalgaZamanayiciBittiMi();
	}

	private boolean DahaDalgaVarmı() {
		return dalgaYoneticisi.DahaDalgaVarmı();

	}

	private boolean ButunDusmanlarOlduMu() {

		if (dalgaYoneticisi.DalgadaDahaDusmanVarMi())
			return false;

		for (Dusman e : DusmanYoneticisi.getDusmanlar())
			if (e.Yasiyormu())
				return false;

		return true;
	}

	private void canlandirdusman() {
		DusmanYoneticisi.canlandirdusman(dalgaYoneticisi.getsonrakidusman());
	}

	private boolean YeniDusmanZamaniMi() {
		if (dalgaYoneticisi.YeniDusmanZamaniMi()) {
			if (dalgaYoneticisi.DalgadaDahaDusmanVarMi())
				return true;
		}

		return false;
	}

	public void setseciliKule(Kule seciliKule) {
		this.seciliKule = seciliKule;
	}

	@Override
	public void render(Graphics g) {

		drawLevel(g);
		aksiyonBari.draw(g);
		DusmanYoneticisi.draw(g);
		kuleYoneticisi.draw(g);
		FuzeYoneticisi.draw(g);

		drawseciliKule(g);
		drawHighlight(g);

	}

	private void drawHighlight(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(mouseX, mouseY, 32, 32);

	}

	private void drawseciliKule(Graphics g) {
		if (seciliKule != null)
			g.drawImage(kuleYoneticisi.getKulefotos()[seciliKule.getKuleTipi()], mouseX, mouseY, null);
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

	public int getKaroTipi(int x, int y) {
		int xKord = x / 32;
		int yKord = y / 32;

		if (xKord < 0 || xKord > 19)
			return 0;
		if (yKord < 0 || yKord > 19)
			return 0;

		int id = lvl[y / 32][x / 32];
		return game.getKaroYoneticisi().getTile(id).getKaroTipi();
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640)
			aksiyonBari.mouseClicked(x, y);
		else {
			
			if (seciliKule != null) {
				if (KaroCimenMi(mouseX, mouseY)) {
					if (getKuleAt(mouseX, mouseY) == null) {
						kuleYoneticisi.addKule(seciliKule, mouseX, mouseY);

						altındusur(seciliKule.getKuleTipi());

						seciliKule = null;

					}
				}
			} else {
				Kule t = getKuleAt(mouseX, mouseY);
				aksiyonBari.gosterKule(t);
			}
		}
	}

	private void altındusur(int KuleTipi) {
		aksiyonBari.payForKule(KuleTipi);

	}

	public void YukseltmeKule(Kule gosterlimisKule) {
		kuleYoneticisi.YukseltmeKule(gosterlimisKule);

	}

	public void KuleKaldır(Kule gosterlimisKule) {
		kuleYoneticisi.KuleKaldır(gosterlimisKule);
	}

	private Kule getKuleAt(int x, int y) {
		return kuleYoneticisi.getKuleAt(x, y);
	}

	private boolean KaroCimenMi(int x, int y) {
		int id = lvl[y / 32][x / 32];
		int KaroTipi = game.getKaroYoneticisi().getTile(id).getKaroTipi();
		return KaroTipi == CIMEN_KAROSU;
	}

	public void dusmanavur(Kule t, Dusman e) {
		FuzeYoneticisi.newProjectile(t, e);

	}

	public void setoyunDurdu(boolean oyunDurdu) {
		this.oyunDurdu = oyunDurdu;
	}

	public void TusaBasildi(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			seciliKule = null;
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640)
			aksiyonBari.mouseMoved(x, y);
		else {
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}
	}



	@Override
	public void mouseReleased(int x, int y) {
		aksiyonBari.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {

	}

	public void OdulOyuncu(int Dusmantipi) {
		aksiyonBari.addaltın(Yardimcilar.Sabitler.Dusmanlar.GetOdul(Dusmantipi));
	}

	public KuleYoneticisi getKuleManager() {
		return kuleYoneticisi;
	}

	public DusmanYoneticisi getdusmanManger() {
		return DusmanYoneticisi;
	}

	public DalgaYoneticisi getWaveManager() {
		return dalgaYoneticisi;
	}

	public boolean isoyunDurdu() {
		return oyunDurdu;
	}

	public void canidusur() {
		aksiyonBari.canidusur();
	}

	public void SıfırlaHersey() {

		aksiyonBari.SıfırlaHersey();
		DusmanYoneticisi.Sıfırla();
		kuleYoneticisi.Sıfırla();
		FuzeYoneticisi.Sıfırla();
		dalgaYoneticisi.Sıfırla();

		mouseX = 0;
		mouseY = 0;

		seciliKule = null;
		altınTick = 0;
		oyunDurdu = false;

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