package renderer;

import preprocessor.PreProcessor;

import java.io.PrintStream;

public class RendererStandardImpl implements Renderer{
    private PreProcessor processor;

    public RendererStandardImpl(PreProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void render(String message) {
        System.out.println(this.processor.process(message));;
    }
}
