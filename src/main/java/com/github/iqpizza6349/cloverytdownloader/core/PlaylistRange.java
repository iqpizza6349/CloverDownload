package com.github.iqpizza6349.cloverytdownloader.core;

public final class PlaylistRange {

    private static PlaylistRange INSTANCE;

    public static PlaylistRange getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlaylistRange();
        }

        return INSTANCE;
    }

    private int min = 1;
    private int max = 10;

    public void setRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
