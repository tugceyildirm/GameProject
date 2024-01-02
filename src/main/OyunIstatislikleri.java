package main;

public enum OyunIstatislikleri {

	Oynaniyor, MENU, DUZENLE, OYUN_BITTI;

	public static OyunIstatislikleri OyunDurumu = MENU;

	public static void SetOyunDurumu(OyunIstatislikleri state) {
		OyunDurumu = state;
	}

}
