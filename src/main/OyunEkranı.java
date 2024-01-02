package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import Girdiler.KeyBoardListener;
import Girdiler.MouseListener;

public class OyunEkranı extends JPanel {
	private Game game;
	private Dimension Boyut;
	private MouseListener MouseListener;
	private KeyBoardListener KeyBoardListener;

	public OyunEkranı(Game game) {
		this.game = game;

		setPanelSize();
	}
	public void BaslangicGirdileri() {
		MouseListener = new MouseListener(game);
		KeyBoardListener = new KeyBoardListener(game);

		addMouseListener((java.awt.event.MouseListener) MouseListener);
		addMouseMotionListener(MouseListener);
		addKeyListener(KeyBoardListener);
		requestFocus();
	}

	private void setPanelSize() {
		Boyut = new Dimension(640, 800);
		setMinimumSize(Boyut);
		setPreferredSize(Boyut);
		setMaximumSize(Boyut);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.getRender().render(g);

	}

}