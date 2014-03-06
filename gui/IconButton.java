package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconButton extends JButton {

	private static final long serialVersionUID = 1L;
	private Image img;

	public IconButton(Action a,ImageIcon icon) {
		super(a);
		img = icon.getImage();
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setPreferredSize(new Dimension(img.getWidth(this), img.getHeight(this)));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}
