package preprocessor;

import java.util.Locale;

public class PreProcessorToUpperImpl implements PreProcessor{

    public String process(String message) {
        return message.toUpperCase();
    }
}
