package com.github.iqpizza6349.cloverytdownloader.frame.util;

public enum IniData {
    DEVELOP("owner"),
    PROJECT("name"),
    VERSION("version", "scheme")
    ;

    private final String[] args;

    IniData(String... args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public boolean foundArg(String arg) {
        IniData[] iniData = values();
        for (IniData data : iniData) {
            for (String a : data.getArgs()) {
                if (a.equalsIgnoreCase(arg)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean found(String s) {
        IniData[] iniData = values();
        for (IniData data : iniData) {
            if (data.name().equals(s)) {
                return true;
            }
        }

        return false;
    }
}