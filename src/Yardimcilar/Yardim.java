package Yardimcilar;

import java.util.ArrayList;

public class Yardim {

	public static int[][] ArrayListTo2Dint(ArrayList<Integer> liste, int yBoyut, int xBoyut) {
		int[][] yeniDizi = new int[yBoyut][xBoyut];

		for (int j = 0; j < yeniDizi.length; j++)
			for (int i = 0; i < yeniDizi[j].length; i++) {
				int index = j * yBoyut + i;
				yeniDizi[j][i] = liste.get(index);
			}

		return yeniDizi;

	}

	public static int[] IkiDden1DeIntDiziAl(int[][] ikidizi) {
		int[] onedizi = new int[ikidizi.length * ikidizi[0].length];

		for (int j = 0; j < ikidizi.length; j++)
			for (int i = 0; i < ikidizi[j].length; i++) {
				int index = j * ikidizi.length + i;
				onedizi[index] = ikidizi[j][i];
			}

		return onedizi;
	}

	public static int UzaklikHesapla(float x1, float y1, float x2, float y2) {

		float xDiff = Math.abs(x1 - x2);
		float yDiff = Math.abs(y1 - y2);

		return (int) Math.hypot(xDiff, yDiff);

	}

}
