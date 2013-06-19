package org.mgechev.edulang;

public class Main {

    public static void main(String[] args) {
        ELang.loadProgramFile(args[0]);
        ELang.execute();
    }

}
