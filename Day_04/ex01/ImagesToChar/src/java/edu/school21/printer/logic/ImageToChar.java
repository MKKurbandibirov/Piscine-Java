package edu.school21.printer.logic;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;

public class ImageToChar {
	private String white;
	private String black;
	private int height;
	private int width;
	
	public ImageToChar(String white, String black) {
		this.white = white;
		this.black = black;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void printImage() {
        try {
            BufferedImage image = ImageIO.read(ImageIO.class.getResource("/resources/it.bmp"));
            this.height = image.getHeight();
			this.width = image.getWidth();
            for (int j = 0 ; j < this.height; j++) {
                for (int i = 0; i < this.width; i++) {
                Color color = new Color(image.getRGB(i, j));
                    int rgb = color.getRGB();
                    if (rgb == -1) {
                        System.out.printf("%s", this.white);
                    } else {
                        System.out.printf("%s", this.black);
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Couldn't open this file!");
        }
	}
}
