package compiler;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//SymbolReader breaks the input into tokens as it is being read
public class SymbolReader {
    public static void main(String[] args) {    //DEBUG TODO remove
        SymbolReader reader = new SymbolReader(new File("test.txt"));
        while(!reader.eof())
            System.out.println(reader.get());
    }

    private static final boolean[] literalChars = new boolean[256];
    static{
        //all literal chars
        for(char i = '0'; i <= '9'; ++i)
            literalChars[i] = true;
        for(char i = 'A'; i <= 'Z'; ++i)
            literalChars[i] = true;
        for(char i = 'a'; i <= 'z'; ++i)
            literalChars[i] = true;
        literalChars['$'] = literalChars['?'] = literalChars['.'] = literalChars['_'] = true;
    };

    private BufferedReader source;
    private Queue<String> lineBuffer = new LinkedList<>();  //lineBuffer is always full unless eof
    private boolean eof = false;

    public SymbolReader(File source) {
        try {
            this.source = new BufferedReader(new FileReader(source));
            loadBuffer(); //fill buffer
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    private void loadLine() {
        String line = getLine(); //newline is used to prevent error when peeking at the next char
        if(line == null)
            return;

        int cind = 0;
        while (cind + 1 < line.length()) {   //before newline
            int len = 1;
            switch (line.charAt(cind)) {
                case '~': 	//~=, ~
                case '!':	//!=, !
                case '?':   //?=
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
                    if (line.charAt(cind + 1) == '/')
                        return;
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
                    lineBuffer.add("\"");
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

    private void loadBuffer() {
        while (lineBuffer.isEmpty() && !eof())
            loadLine();
    }

    private boolean isLiteralChar(char charAt) {
        return literalChars[charAt];
    }

    private String getLine() {
        try {
            String ret = source.readLine();
            if(ret != null)
                return ret + '\n';
            eof = true;
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-3);
            return null;
        }
    }

    private Map.Entry<String, Integer> getStringLiteral(String line, StringBuilder ret, int cind) {
        char endDelim = line.charAt(cind);

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
        ++cind; //end delimiter

        return Map.entry(line, cind);
    }

    //strings are split into (",value)
    //numbers are split into (num[, ., num][, e, num])
    public String get() {
        String ret = lineBuffer.remove();
        if(lineBuffer.isEmpty())
            loadBuffer();
        return ret;
    }

    public String peek() {
        return lineBuffer.peek();
    }

    public boolean eof() {
        return eof;
    }
}
