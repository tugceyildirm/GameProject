package main;

import javax.swing.JFrame;
import Girdiler.KeyBoardListener;
import Girdiler.MouseListener;
import Sahneler.Duzenleme;
import Sahneler.Menu;
import Sahneler.Oynaniyor;
import Sahneler.OyunBitti;
import Yardimcilar.YukleKaydet;
import Yoneticiler.HaritaYoneticisi;


public class Game extends JFrame implements Runnable {

	private static final long ZamanBasınaYukseltme = 0;
	private OyunEkranı oyunEkranı;
	private Thread gameThread;
	private Goruntuleme goruntuleme;
	private Menu menu;
	private Oynaniyor oynaniyor;
	private Duzenleme duzenleme;
	private OyunBitti oyunBitti;

	private HaritaYoneticisi haritaYoneticisi;

	public Game() {

		initClasses();
		olusturStandartLevel();

		setStandartCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Your Game");
		add(oyunEkranı);
		pack();
		setVisible(true);

	}

	private void setStandartCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}

	private void olusturStandartLevel() {
		int[] dizi = new int[400];
		for (int i = 0; i < dizi.length; i++)
			dizi[i] = 0;

		YukleKaydet.olusturLevel("new_level", dizi);

	}

	private void initClasses() {
		haritaYoneticisi = new HaritaYoneticisi();
		goruntuleme = new Goruntuleme(this);
		oyunEkranı = new OyunEkranı(this);
		menu = new Menu(this);
		oynaniyor = new Oynaniyor(this);
		duzenleme = new Duzenleme(this);
		oyunBitti = new OyunBitti(this);

	}

	private void Baslangic() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}

	private void GuncelleOyun() {
		switch (OyunIstatislikleri.OyunDurumu) {
		case DUZENLE:
			duzenleme.Yukseltme();
			break;
		case MENU:
			break;
		case Oynaniyor:
			oynaniyor.Yukseltme();
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.oyunEkranı.BaslangicGirdileri();
		game.Baslangic();

	}

	@Override
	public void run() {

		
		long sonframe = System.nanoTime();
		long SonYukseltme = System.nanoTime();
		long SonZamanKontrolu = System.currentTimeMillis();
		
		long now;

		while (true) {
			now = System.nanoTime();
			
			if (now - SonYukseltme >= ZamanBasınaYukseltme) {
				GuncelleOyun();
				SonYukseltme = now;
							}

		
		}

	}

	public Goruntuleme getRender() {
		return goruntuleme;
	}

	public Menu getMenu() {
		return menu;
	}

	public Oynaniyor getOynaniyor() {
		return oynaniyor;
	}


	public Duzenleme getDUZENLEor() {
		return duzenleme;
	}

	public OyunBitti getOyunBitti() {
		return oyunBitti;
	}

	public HaritaYoneticisi getKaroYoneticisi() {
		return haritaYoneticisi;
	}

}