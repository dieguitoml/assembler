package ast;

public class StringUtils {
    public static String generate_identation(int indentLevel) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            indent.append("    "); 
        }
        return indent.toString();
    }
}

