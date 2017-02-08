package com.oncontentstop.anothertestapp;


/**
 * @author onContentStop
 */

public class Background {
	private int[] color1;
	private int[] color2;
    private int[] currColor;
    public Background() {
	    color1 = new int[] {255, 255, 255};
	    do {
		    color2 = RandomColorGenerator.getOpaque();
	    } while (color2[0] + color2[1] + color2[2] < 350);
        currColor = new int[3];
    }
    public void updateColor(int time) {
	    double progress = (double)time / 10000d;
		if(progress > 1)
			progress = 1;
	    int currentR = Motion.getNumberBetween(Motion.MODE_CIRCLE_QUADRANT, color1[0], color2[0], progress);
	    int currentG = Motion.getNumberBetween(Motion.MODE_CIRCLE_QUADRANT, color1[1], color2[1], progress);
	    int currentB = Motion.getNumberBetween(Motion.MODE_CIRCLE_QUADRANT, color1[2], color2[2], progress);
	    currColor = new int[]{currentR, currentG, currentB};
    }
    public int[] getCurrColor() {
        return currColor;
    }
	public void resetColor() {
		do {
			color2 = RandomColorGenerator.getOpaque();
		} while (color2[0] + color2[1] + color2[2] < 350);
		color1 = currColor;

	}
    private boolean equalColors(int[] c1, int[] c2) {
        if(c1.length != 3 || c2.length != 3) {
            throw new IllegalArgumentException("Some color is not of length 3!!!");
        }
        return c1[0] == c2[0] && c1[1] == c2[1] && c1[2] == c2[2];
    }
}
