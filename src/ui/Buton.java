package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Buton {

	public int x, y, width, height, id;
	private String text;
	private Rectangle Sinirlar;
	private boolean mouseOver,mouseClicked;

	public Buton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initSinirlar();
	}
	public Buton(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;

		initSinirlar();
	}

	private void initSinirlar() {
		this.Sinirlar = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
	
		drawBody(g);

	
		drawBorder(g);

	
		drawText(g);
	}

	private void drawBorder(Graphics g) {

		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		if (mouseClicked) {
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}

	}

	private void drawBody(Graphics g) {
		if (mouseOver)
			g.setColor(Color.gray);
		else
			g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);

	}

	private void drawText(Graphics g) {
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);

	}

	public void SıfırlaBooleans() {
		this.mouseOver = false;
		this.mouseClicked = false;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setmouseClicked(boolean mouseClicked) {
		this.mouseClicked =mouseClicked;
	}

	public void MouseUzerinde(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean ismouseClicked() {
		return mouseClicked;
	}

	public Rectangle getSinirlar() {
		return Sinirlar;
	}

	public int getId() {
		return id;
	}

}
