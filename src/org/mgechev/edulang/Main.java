package org.mgechev.edulang;

public class Main {

    public static void main(String[] args) {
        
        //String program = "print 'asdasd\n';second = 0; first = (-tan(-(-1-sin(second)))) * 8; print first;";
        
        EduLang.loadProgramFile(args[0]);
        EduLang.execute();
    }

}
