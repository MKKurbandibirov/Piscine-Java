package edu.school21.printer.app;

import edu.school21.printer.logic.ImageToChar;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")

public class Program {
    @Parameter(names = "--white")
    private static String whiteColor;
    @Parameter(names = "--black")
    private static String blackColor;

    public static void main(String[] args) {
        if(args.length == 2){
            Program program = new Program();
            JCommander.newBuilder()
                .addObject(program)
                .build()
                .parse(args);
            ImageToChar obj = new ImageToChar(whiteColor, blackColor);
            obj.printImage();
		} else {
			System.err.println("Invalid argument");
			System.exit(-1);
		}
    }
}
