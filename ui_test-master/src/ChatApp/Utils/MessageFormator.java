package ChatApp.Utils;

import java.util.List;

public class MessageFormator {

    public static String methodConvertMessage(List<String> msgs) {
        String text = "";
        for(String msg : msgs) {
            text+="#$;" + text;
        }
        return text.replaceFirst("#$;", "");
    }
}
