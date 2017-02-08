package com.oncontentstop.anothertestapp;


/**
 * @author onContentStop
 */
public class Motion {
	public static final int MODE_EXPONENTIAL = 0;
	public static final int MODE_LINEAR = 1;
	public static final int MODE_QUADRATIC = 2;
	public static final int MODE_LOGISTIC = 3;
	public static final int MODE_CIRCLE_QUADRANT = 4;
	public static int getNumberBetween(int mode, int n1, int n2, double percentage) {
		double progressFunction = 0;//this will be changed in any valid mode, otherwise an error will be thrown
		switch(mode) {
			case MODE_EXPONENTIAL:
				progressFunction = Math.pow(100, percentage - 1);
				break;
			case MODE_LINEAR:
				progressFunction = percentage;
				break;
			case MODE_QUADRATIC:
				progressFunction = Math.pow(percentage, 2);
				break;
			case MODE_LOGISTIC:
				progressFunction = 1.06 / (1d + Math.pow(Constants.e, -3.5 * (2 * percentage - 1))) - 0.03;
				break;
			case MODE_CIRCLE_QUADRANT:
				progressFunction = Math.sqrt(-1d * Math.pow(percentage - 1, 2) + 1);
				break;
			default:
				System.out.println("Mode does not exist: " + mode);
		}
		long out = Math.round((double)n1 * (1 - progressFunction) + (double) n2 * progressFunction);
		if(percentage > 1)
			return n2;
		return (int)out;
	}
}
