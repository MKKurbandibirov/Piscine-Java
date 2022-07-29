package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import preprocessor.PreProcessor;
import preprocessor.PreProcessorToUpperImpl;
import printer.Printer;
import printer.PrinterWithDateTimeImpl;
import renderer.Renderer;


import renderer.RendererStandardImpl;

public class Program {

    public static void main(String[] args) {
        {
            PreProcessor preProcessor = new PreProcessorToUpperImpl();
            Renderer renderer = new RendererStandardImpl(preProcessor);
            PrinterWithDateTimeImpl printer = new PrinterWithDateTimeImpl(renderer);
            printer.print ("Hello!") ;
        }
        {
            ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
            Printer printer = context.getBean("printerWithDateAndLowerErrRenderer", Printer.class);
            printer.print ("Hello!") ;
        }
    }
}
