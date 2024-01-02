package Yardimcilar;

public class Sabitler {

	public static class Fuzeler {
		public static final int OK = 0;
		public static final int ZINCIR = 1;
		public static final int BOMBA = 2;

		public static float GetHız(int type) {
			switch (type) {
			case OK:
				return 8f;
			case BOMBA:
				return 4f;
			case ZINCIR:
				return 6f;
			}
			return 0f;
		}

	}
	public static class Kuleler {
		public static final int TOP = 0;
		public static final int OKCU = 1;
		public static final int BUYUCU = 2;

		public static int GetKuleFiyat(int KuleTipi) {
			switch (KuleTipi) {
			case TOP:
				return 65;
			case OKCU:
				return 35;
			case BUYUCU:
				return 50;
			}
			return 0;
		}

		public static String GetIsim(int KuleTipi) {
			switch (KuleTipi) {
			case TOP:
				return "TOP";
			case OKCU:
				return "OKCU";
			case BUYUCU:
				return "BUYUCU";
			}
			return "";
		}

		public static int GetBaslangicHasar(int KuleTipi) {
			switch (KuleTipi) {
			case TOP:
				return 15;
			case OKCU:
				return 5;
			case BUYUCU:
				return 0;
			}

			return 0;
		}

		public static float GetStandartmesafe(int KuleTipi) {
			switch (KuleTipi) {
			case TOP:
				return 75;
			case OKCU:
				return 120;
			case BUYUCU:
				return 100;
			}

			return 0;
		}

		public static float GetStandartBeklemeSuresi(int KuleTipi) {
			switch (KuleTipi) {
			case TOP:
				return 120;
			case OKCU:
				return 35;
			case BUYUCU:
				return 50;
			}

			return 0;
		}
	}

	public static class Yon {
		public static final int SOL = 0;
		public static final int YUKARI = 1;
		public static final int SAG = 2;
		public static final int ASAGI = 3;
	}

	public static class Dusmanlar {

		public static final int ORC = 0;
		public static final int YARASA = 1;
		

		public static int GetOdul(int Dusmantipi) {
			switch (Dusmantipi) {
			case ORC:
				return 5;
			case YARASA:
				return 5;
			
			}
			return 0;
		}

		public static float GetHız(int Dusmantipi) {
			switch (Dusmantipi) {
			case ORC:
				return 0.5f;
			case YARASA:
				return 0.7f;
			
			}
			return 0;
		}

		public static int GetBaslangicCan(int Dusmantipi) {
			switch (Dusmantipi) {
			case ORC:
				return 85;
			case YARASA:
				return 100;
			
			}
			return 0;
		}
	}

	public static class Karo {
		public static final int SU_KAROSU = 0;
		public static final int CIMEN_KAROSU = 1;
		public static final int YOL_KAROSU = 2;
	}

}
