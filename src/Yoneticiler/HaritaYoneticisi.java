package Yoneticiler;

import static Yardimcilar.Sabitler.Karo.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Objeler.Karo;
import Yardimcilar.ResimDuzelt;
import Yardimcilar.YukleKaydet;

public class HaritaYoneticisi {

	public Karo GRASS, WATER, ROAD_LR, ROAD_TB, ROAD_B_TO_R, ROAD_L_TO_B, ROAD_L_TO_T, ROAD_T_TO_R, BL_WATER_CORNER, TL_WATER_CORNER, TR_WATER_CORNER, BR_WATER_CORNER, T_WATER, R_WATER, B_WATER,
			L_WATER, TL_ISLE, TR_ISLE, BR_ISLE, BL_ISLE;

	private BufferedImage atlas;
	public ArrayList<Karo> Karo = new ArrayList<>();

	public ArrayList<Karo> roadsS = new ArrayList<>();
	public ArrayList<Karo> roadsC = new ArrayList<>();
	public ArrayList<Karo> corners = new ArrayList<>();
	public ArrayList<Karo> beaches = new ArrayList<>();
	public ArrayList<Karo> islands = new ArrayList<>();

	public HaritaYoneticisi() {

		yukleAtalas();
		olusturKaro();

	}

	private void olusturKaro() {

		int id = 0;

		Karo.add(GRASS = new Karo(getkarakter(9, 0), id++, CIMEN_KAROSU));
		Karo.add(WATER = new Karo(getAnikarakters(0, 0), id++, SU_KAROSU));

		roadsS.add(ROAD_LR = new Karo(getkarakter(8, 0), id++, YOL_KAROSU));
		roadsS.add(ROAD_TB = new Karo(ResimDuzelt.getResimAcisi(getkarakter(8, 0), 90), id++, YOL_KAROSU));

		roadsC.add(ROAD_B_TO_R = new Karo(getkarakter(7, 0), id++, YOL_KAROSU));
		roadsC.add(ROAD_L_TO_B = new Karo(ResimDuzelt.getResimAcisi(getkarakter(7, 0), 90), id++, YOL_KAROSU));
		roadsC.add(ROAD_L_TO_T = new Karo(ResimDuzelt.getResimAcisi(getkarakter(7, 0), 180), id++, YOL_KAROSU));
		roadsC.add(ROAD_T_TO_R = new Karo(ResimDuzelt.getResimAcisi(getkarakter(7, 0), 270), id++, YOL_KAROSU));

		corners.add(BL_WATER_CORNER = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(5, 0), 0), id++, SU_KAROSU));
		corners.add(TL_WATER_CORNER = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(5, 0), 90), id++, SU_KAROSU));
		corners.add(TR_WATER_CORNER = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(5, 0), 180), id++, SU_KAROSU));
		corners.add(BR_WATER_CORNER = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(5, 0), 270), id++, SU_KAROSU));

		beaches.add(T_WATER = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(6, 0), 0), id++, SU_KAROSU));
		beaches.add(R_WATER = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(6, 0), 90), id++, SU_KAROSU));
		beaches.add(B_WATER = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(6, 0), 180), id++, SU_KAROSU));
		beaches.add(L_WATER = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(6, 0), 270), id++, SU_KAROSU));

		islands.add(TL_ISLE = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(4, 0), 0), id++, SU_KAROSU));
		islands.add(TR_ISLE = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(4, 0), 90), id++, SU_KAROSU));
		islands.add(BR_ISLE = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(4, 0), 180), id++, SU_KAROSU));
		islands.add(BL_ISLE = new Karo(ResimDuzelt.getYapResimAcisi(getAnikarakters(0, 0), getkarakter(4, 0), 270), id++, SU_KAROSU));

		Karo.addAll(roadsS);
		Karo.addAll(roadsC);
		Karo.addAll(corners);
		Karo.addAll(beaches);
		Karo.addAll(islands);
	}

	private BufferedImage[] getfotos(int firstX, int firstY, int secondX, int secondY) {
		return new BufferedImage[] { getkarakter(firstX, firstY), getkarakter(secondX, secondY) };
	}

	private void yukleAtalas() {
		atlas = YukleKaydet.resimal();
	}

	public Karo getTile(int id) {
		return Karo.get(id);
	}

	public BufferedImage getkarakter(int id) {
		return Karo.get(id).getkarakter();
	}

	public BufferedImage getAnikarakter(int id, int animasyonIndex) {
		return Karo.get(id).getkarakter(animasyonIndex);
	}

	private BufferedImage[] getAnikarakters(int xKord, int yKord) {
		BufferedImage[] dizi = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			dizi[i] = getkarakter(xKord + i, yKord);
		}

		return dizi;

	}

	private BufferedImage getkarakter(int xKord, int yKord) {
		return atlas.getSubimage(xKord * 32, yKord * 32, 32, 32);
	}

	public boolean karakteranimasyonukontrolu(int karakterID) {
		return Karo.get(karakterID).animasyonkontrolu();
	}

	public ArrayList<Karo> getRoadsS() {
		return roadsS;
	}

	public ArrayList<Karo> getRoadsC() {
		return roadsC;
	}

	public ArrayList<Karo> getCorners() {
		return corners;
	}

	public ArrayList<Karo> getBeaches() {
		return beaches;
	}

	public ArrayList<Karo> getIslands() {
		return islands;
	}

}
