package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageTest extends Image {
	
	private BufferedImage image;
	
	public ImageTest (String name) {
		try {
			image = ImageIO.read(new File("/dkeep/dkeep.res/" + name + ".png"));
		}
		catch (IOException ex) {
			
		}
	}

	@Override
	public int getWidth(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	 @Override
//	 protected void paintComponent(Graphics g) {
//		 super.paintComponent(g);
//		 g.drawImage(image, 0, 0, this);           
//	 }
}
