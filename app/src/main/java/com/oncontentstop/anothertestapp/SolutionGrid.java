package com.oncontentstop.anothertestapp;

/**
 * Created by mario on 3/29/2017.
 */

public class SolutionGrid extends Grid {
	public SolutionGrid(int sx, int sy) {
		super(sx, sy);
	}
	@Override
	protected void initBoxes() {
		for(int x = 0; x < sizeX; x++) {
			for(int y = 0; y < sizeY; y++) {
				contents[x][y] = new Box();
				if(Math.random() > 0.5)
					contents[x][y].setStatus(BoxStatus.CORRECT);
			}
		}
	}
}
