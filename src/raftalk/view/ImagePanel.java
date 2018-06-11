package raftalk.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Pomocna klasa za iscrtavanje nindze u login prozoru.
 * 
 * @author dzimiks
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private Image img;

	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	public ImagePanel(Image img) {
		this.img = img;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, (int)(this.getSize().getWidth() - img.getWidth(null)) / 2,
	    			   	 (int)(this.getSize().getHeight() - img.getHeight(null)) / 2, null);
	}
}