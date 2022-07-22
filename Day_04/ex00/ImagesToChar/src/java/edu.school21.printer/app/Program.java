package edu.school21.printer.app;

import edu.school21.printer.logic.ImageToChar;

public class Program {
    private static final int SIZE = 16;

    public static void main(String[] args) {
        if(args.length == 1){
            ImageToChar obj = new ImageToChar(args[0], SIZE, SIZE);
            obj.printImage();
		
		} else {
			System.err.println("Invalid argument");
			System.exit(-1);
		}

    }
}
