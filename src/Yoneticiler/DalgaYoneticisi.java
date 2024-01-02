package Yoneticiler;

import java.util.ArrayList;
import java.util.Arrays;
import Etkinlikler.Dalga;
import Sahneler.Oynaniyor;

public class DalgaYoneticisi {

	private Oynaniyor oynaniyor;
	private ArrayList<Dalga> waves = new ArrayList<>();
	private int dusmancanlandirTickLimit = 60 * 1;
	private int dusmancanlandirTick = dusmancanlandirTickLimit;
	private int dusmanIndex, waveIndex;
	private int waveTickLimit = 60 * 5;
	private int waveTick = 0;
	private boolean waveBaslangicTimer, waveTickTimerOver;
	public DalgaYoneticisi(Oynaniyor oynaniyor) {
		this.oynaniyor = oynaniyor;
		olusturWaves();
	}
	public void Yukseltme() {
		if (dusmancanlandirTick < dusmancanlandirTickLimit)
			dusmancanlandirTick++;

		if (waveBaslangicTimer) {
			waveTick++;
			if (waveTick >= waveTickLimit) {
				waveTickTimerOver = true;
	     	}
		}
	}
	public void DalgaIndeksiniArttır() {
		waveIndex++;
		waveTick = 0;
		waveTickTimerOver = false;
		waveBaslangicTimer = false;
	}
	public boolean DalgaZamanayiciBittiMi() {
		return waveTickTimerOver;
	}
	public void BaslangicDalgaZamanlayici() {
		waveBaslangicTimer = true;
	}
	public int getsonrakidusman() {
		dusmancanlandirTick = 0;
		return waves.get(waveIndex).getdusmanliste().get(dusmanIndex++);
	}
	private void olusturWaves() {
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1))));
	}
	public ArrayList<Dalga> getWaves() {
		return waves;
	}
	public boolean YeniDusmanZamaniMi() {
		return dusmancanlandirTick >= dusmancanlandirTickLimit;
	}
	public boolean DalgadaDahaDusmanVarMi() {
		return dusmanIndex < waves.get(waveIndex).getdusmanliste().size();
	}
	public boolean DahaDalgaVarmı() {
		return waveIndex + 1 < waves.size();
	}
	public void SıfırladusmanIndex() {
		dusmanIndex = 0;
	}
	public int getWaveIndex() {
		return waveIndex;
	}
	public float getTimeSOL() {
		float ticksSOL = waveTickLimit - waveTick;
		return ticksSOL / 60.0f;
	}
	public boolean isDalgaZamanlayiciBaslangiced() {
		return waveBaslangicTimer;
	}
	public void Sıfırla() {
		waves.clear();
		olusturWaves();
		dusmanIndex = 0;
		waveIndex = 0;
		waveBaslangicTimer = false;
		waveTickTimerOver = false;
		waveTick = 0;
		dusmancanlandirTick = dusmancanlandirTickLimit;
	}

}
