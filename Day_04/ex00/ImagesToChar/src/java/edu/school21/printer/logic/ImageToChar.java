package edu.school21.printer.logic;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;

public class ImageToChar {
	private String filePath;
	private String white;
	private String black;
	private int height;
	private int width;
	
	public ImageToChar(String filePath, String white, String black) {
		this.filePath = filePath;
		this.white = white;
		this.black = black;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void printImage() {
        File file = new File(this.getFilePath());
        try {
            BufferedImage image = ImageIO.read(file);
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
