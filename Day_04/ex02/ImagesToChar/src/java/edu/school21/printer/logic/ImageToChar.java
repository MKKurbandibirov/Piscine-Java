package edu.school21.printer.logic;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import com.diogonunes.jcdp.color.*;
import com.diogonunes.jcdp.color.api.Ansi;

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
		ColoredPrinter cp1 = new ColoredPrinter.Builder(1, false)
				.background(Ansi.BColor.valueOf(this.white)).build();

		ColoredPrinter cp2 = new ColoredPrinter.Builder(1, false)
				.background(Ansi.BColor.valueOf(this.black)).build();
        try {
            BufferedImage image = ImageIO.read(ImageIO.class.getResource("/resources/it.bmp"));
            this.height = image.getHeight();
			this.width = image.getWidth();
            for (int j = 0 ; j < this.height; j++) {
                for (int i = 0; i < this.width; i++) {
                Color color = new Color(image.getRGB(i, j));
                    int rgb = color.getRGB();
                    if (rgb == -1) {
                        cp1.print(" ");
                    } else {
                        cp2.print(" ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Couldn't open this file!");
        }
	}
}
