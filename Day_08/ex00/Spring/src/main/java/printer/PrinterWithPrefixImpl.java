package printer;

import preprocessor.PreProcessor;
import renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithPrefixImpl implements Printer{
    private String prefix;
    private Renderer renderer;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String message) {
        this.renderer.render(this.prefix + message);
    }
}
