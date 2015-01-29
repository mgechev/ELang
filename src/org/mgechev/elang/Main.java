package org.mgechev.elang;

public class Main {

    public static void main(String[] args) {
        ELang.loadProgramFile(args[0]);
        ELang.execute();
    }

}
