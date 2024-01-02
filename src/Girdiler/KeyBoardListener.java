package Girdiler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.OyunIstatislikleri.*;

import main.Game;
import main.OyunIstatislikleri;

public class KeyBoardListener implements KeyListener {
    private Game game;

    public KeyBoardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) { 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (OyunIstatislikleri.OyunDurumu == DUZENLE)
            game.getDUZENLEor().TusaBasildi(e);
        else if (OyunIstatislikleri.OyunDurumu == Oynaniyor)
            game.getOynaniyor().TusaBasildi(e);
    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

    
}
