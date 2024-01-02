package Girdiler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Game;
import main.OyunIstatislikleri;

public class MouseListener implements java.awt.event.MouseListener, MouseMotionListener {

	private Game game;

	public MouseListener(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) { 
		switch (OyunIstatislikleri.OyunDurumu) {
		case MENU:
			game.getMenu().mouseDragged(e.getX(), e.getY());
			break;
		case Oynaniyor:
			game.getOynaniyor().mouseDragged(e.getX(), e.getY());
			break;
		case DUZENLE:
			game.getDUZENLEor().mouseDragged(e.getX(), e.getY());
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) { 
		switch (OyunIstatislikleri.OyunDurumu) {
		case MENU:
			game.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case Oynaniyor:
			game.getOynaniyor().mouseMoved(e.getX(), e.getY());
			break;
		case DUZENLE:
			game.getDUZENLEor().mouseMoved(e.getX(), e.getY());
			break;
		case OYUN_BITTI:
			game.getOyunBitti().mouseMoved(e.getX(), e.getY());
			break;
		default: 
			break;
		}
	}
	   @Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			switch (OyunIstatislikleri.OyunDurumu) {
			case MENU:
				game.getMenu().mouseClicked(e.getX(), e.getY());
				break;
			case Oynaniyor:
				game.getOynaniyor().mouseClicked(e.getX(), e.getY());
				break;
			case DUZENLE:
				game.getDUZENLEor().mouseClicked(e.getX(), e.getY());
				break;
			case OYUN_BITTI:
				game.getOyunBitti().mouseClicked(e.getX(), e.getY());
				break;
			default: 
				break;
			}
		}
	}
	   @Override
	public void mousePressed(MouseEvent e) {
		switch (OyunIstatislikleri.OyunDurumu) {
		case MENU:
			game.getMenu().mousePressed(e.getX(), e.getY());
			break;
		case Oynaniyor:
			game.getOynaniyor().mousePressed(e.getX(), e.getY());
			break;
		case DUZENLE:
			game.getDUZENLEor().mousePressed(e.getX(), e.getY());
			break;
		case OYUN_BITTI:
			game.getOyunBitti().mousePressed(e.getX(), e.getY());
			break;
		default: 
			break;
		}
	}
   @Override
	public void mouseReleased(MouseEvent e) {
		switch (OyunIstatislikleri.OyunDurumu) {
		case MENU:
			game.getMenu().mouseReleased(e.getX(), e.getY());
			break;
		case Oynaniyor:
			game.getOynaniyor().mouseReleased(e.getX(), e.getY());
			break;
		case DUZENLE:
			game.getDUZENLEor().mouseReleased(e.getX(), e.getY());
			break;
		case OYUN_BITTI:
			game.getOyunBitti().mouseReleased(e.getX(), e.getY());
			break;
		default: 
			break;
		}
	}
   @Override
	public void mouseEntered(MouseEvent e) {	
	}
   @Override
	public void mouseExited(MouseEvent e) {
	}
}
