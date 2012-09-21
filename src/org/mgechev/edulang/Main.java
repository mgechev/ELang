package org.mgechev.edulang;

public class Main {

    public static void main(String[] args) {
        //EduLang.loadProgramFile(args[0]);
        String program =
                "current = 0;" +
                "n = 7;" +
                "while current < n:" +
                "if current % 2 == 0:" +
                "print current;" +
                "endif;" +
                "print '\n';" +
                "current = current + 1;" +
                "endwhile;";

        EduLang.loadProgramString(program);
        EduLang.execute();
    }

}
