package renderer;

import preprocessor.PreProcessor;

import java.io.PrintStream;

public class RendererErrImpl implements Renderer{
    private PreProcessor processor;

    public RendererErrImpl(PreProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void render(String message) {
        System.err.println(this.processor.process(message));
    }
}
