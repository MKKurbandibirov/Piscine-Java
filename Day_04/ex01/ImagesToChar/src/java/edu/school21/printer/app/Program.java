package edu.school21.printer.app;

import edu.school21.printer.logic.ImageToChar;

public class Program {
    public static void main(String[] args) {
        if(args.length == 2){
            ImageToChar obj = new ImageToChar(args[0], args[1]);
            obj.printImage();
		
		} else {
			System.err.println("Invalid argument");
			System.exit(-1);
		}
    }
}
