package preprocessor;

import java.util.Locale;

public class PreProcessorToLowerImpl implements PreProcessor{

    public String process(String message) {
        return message.toLowerCase();
    }
}
