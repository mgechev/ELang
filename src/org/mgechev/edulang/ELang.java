package org.mgechev.edulang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.mgechev.edulang.interpreter.Interpreter;
import org.mgechev.edulang.lexer.Lexer;
import org.mgechev.edulang.parser.Parser;
import org.mgechev.edulang.tokens.Token;

public class ELang {
    
    private static String program = "";
    
    public static void loadProgramFile(String file) {
        try {
            File programFile = new File(file);
            FileReader fileReader = new FileReader(programFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String programLine;
            while ((programLine = reader.readLine()) != null) {
                program += programLine;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void loadProgramString(String currentProgram) {
        program = currentProgram;
    }
    
    public static void execute() {
        Lexer l = new Lexer(program);
        ArrayList<Token> lst = l.lex();
        
        Parser parser = new Parser(lst);
        parser.parse();
        
        Interpreter interpreter = new Interpreter(parser.getStatements());
        interpreter.interpret();
    }

}
