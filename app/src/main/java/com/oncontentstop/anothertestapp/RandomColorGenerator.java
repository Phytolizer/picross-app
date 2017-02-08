package com.oncontentstop.anothertestapp;

/**
 * Created by mario on 2/3/2017.
 */

public class RandomColorGenerator {
    public static int[] getOpaque() {
        int[] out = new int[3];
        out[0] = (int) (Math.random() * 256);
        out[1] = (int) (Math.random() * 256);
        out[2] = (int) (Math.random() * 256);
        return out;
    }
}
