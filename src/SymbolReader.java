import java.io.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//SymbolReader breaks the input into tokens as it is being read
public class SymbolReader {
    private static final int[] literalChars = new int[]{
            0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	1,	0,	0,	0,	//$
            0,	0,	0,	0,	0,	0,	1,	0,	//?
            1,	1,	1,	1,	1,	1,	1,	1,	//0-7
            1,	1,	0,	0,	0,	0,	0,	0,	//8-9
            0,	1,	1,	1,	1,	1,	1,	1,	//A-G
            1,	1,	1,	1,	1,	1,	1,	1,	//H-O
            1,	1,	1,	1,	1,	1,	1,	1,	//P-W
            1,	1,	1,	0,	0,	0,	0,	1,	//X-Z, _
            0,	1,	1,	1,	1,	1,	1,	1,	//a-g
            1,	1,	1,	1,	1,	1,	1,	1,	//h-o
            1,	1,	1,	1,	1,	1,	1,	1,	//p-w
            1,	1,	1,	0,	0,	0,	0,	0,	//x-z
            0,	0,	0,	0,	0,	0,	0,	0
    };

    private BufferedReader source;
    private Queue<String> lineBuffer = new LinkedList<>();

    public SymbolReader(File source) {
        try {
            this.source = new BufferedReader(new FileReader(source));
            peek(); //fill buffer
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    private void loadBuffer() {
            String line = getLine(); //newline is used to prevent error when peeking at the next char
            int cind = 0;
            while (cind + 1 < line.length()) {   //before newline
                int len = 1;
                switch (line.charAt(cind)) {
                    case '~': 	//~=, ~
                    case '!':	//!=, !
                    case '=':	//=, ==
                        if (line.charAt(cind + 1) == '=')
                            ++len;
                        break;
                    case '<':	//<=, <<, <
                        if (line.charAt(cind + 1) == '=' || line.charAt(cind + 1) == '<')
                            ++len;
                        break;
                    case '>':	//>=, >>, >
                        if (line.charAt(cind + 1) == '=' || line.charAt(cind + 1) == '>')
                            ++len;
                        break;
                    case '-':	//->, -
                        if (line.charAt(cind + 1) == '>')
                            ++len;
                        break;
                    case '|':	//|
                        if (line.charAt(cind + 1) == '|')
                            ++len;
                        break;
                    case '@':	//@
                    case '#':	//#
                    case '%':	//%
                    case '^':	//^
                    case '&':	//&
                    case '+':	//+
                    case '*':	//*
                    case ':':	//:
                    case ';':	//;
                    case ',':	//,
                    case '\\':  //\
                    case '.':   //.
                    case '(':	//(
                    case ')':	//)
                    case '{':	//{
                    case '}':	//}
                    case '[':	//[
                    case ']':	//]
                        break;
                    case ' ':	//ignore
                    case '\t':	//ignore
                        ++cind;
                        continue;

                    case '/': {	////,/*,/
                        if (line.charAt(cind + 1) == '/') {
                            while(lineBuffer.isEmpty()) //make sure something is read
                                loadBuffer();
                            return;
                        }
                        else if (line.charAt(cind + 1) == '*') {
                            while(line.charAt(cind) != '*' || line.charAt(cind + 1) != '/') {
                                ++cind;
                                if(cind >= line.length()) {
                                    line = getLine();
                                    cind = 0;
                                }
                            }
                            continue;
                        }
                        break;
                    }

                    case '\'':	//'...'
                    case '"':	//"..."
                        StringBuilder val = new StringBuilder();
                        Map.Entry<String, Integer> temp = getStringLiteral(line, val, cind);
                        cind = temp.getValue();
                        line = temp.getKey();
                        lineBuffer.add(val.toString());
                        continue;

                    default:	//id
                        while(isLiteralChar(line.charAt(cind + len)))
                            ++len;
                }

                lineBuffer.add(line.substring(cind, cind + len));
                cind += len;
            }
    }

    private boolean isLiteralChar(char charAt) {
        return literalChars[charAt] != 0;
    }

    private String getLine() {
        try {
            return source.readLine() + '\n';
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-3);
            return null;
        }
    }

    private Map.Entry<String, Integer> getStringLiteral(String line, StringBuilder ret, int cind) {
        char endDelim = line.charAt(cind);
        ret.append(endDelim);

        ++cind;
        while (line.charAt(cind) != endDelim)
        {
            if (line.charAt(cind) == '\\')
            {
                char temp = line.charAt(++cind);
                switch (temp) {
                    case 'q' -> ret.append((char) 5);
                    case 'a' -> ret.append((char) 7);
                    case 'b' -> ret.append((char) 8);
                    case 't' -> ret.append((char) 9);
                    case 'n' -> ret.append((char) 10);
                    case 'v' -> ret.append((char) 11);
                    case 'r' -> ret.append((char) 13);
                    case '\\' -> ret.append('\\');
                    case '\'' -> ret.append('\'');
                    case '\"' -> ret.append('\"');
                    case '\n' -> {
                        line = getLine();
                        cind = 0;
                        while (line.charAt(cind) == ' ' || line.charAt(cind) == '\t') ++cind;
                        continue;
                    }
                    case 'x' -> {
                        ++cind;
                        ret.append((char) Integer.parseInt(line.substring(cind, cind + 2), 16));
                        cind += 2;
                        continue;
                    }
                    default -> {
                        ret.append((char) Integer.parseInt(line.substring(cind, cind + 3), 8));
                        cind += 3;
                        continue;
                    }
                }
            }
            else
                ret.append(line.charAt(cind));
            ++cind;
        }

        return Map.entry(line, cind);
    }

    public String get() {
        if(lineBuffer.isEmpty())
            loadBuffer();
        return lineBuffer.remove();
    }

    public String peek() {
        if(lineBuffer.isEmpty())
            loadBuffer();
        return lineBuffer.peek();
    }

    public boolean eof() {
        return lineBuffer.isEmpty();
    }
}
