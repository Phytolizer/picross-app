package com.oncontentstop.anothertestapp;

/**
 * Created by mario on 3/28/2017.
 */

public class Grid {
	protected int sizeX, sizeY;
	protected Box[][] contents;
	public Grid(int sx, int sy) {
		sizeX = sx;
		sizeY = sy;
		contents = new Box[sx][sy];
		initBoxes();
	}
	public Box getBox(int x, int y) {
		if(x < 0 || x >= sizeX || y < 0 || y >= sizeY)
			return null;
		return contents[x][y];
	}
	public boolean reveal(int x, int y, SolutionGrid compareGrid) {
		if(x < 0 || x >= sizeX || y < 0 || y >= sizeY)
			return false;
		Box b = contents[x][y];
		Box answer = compareGrid.getBox(x, y);
		if(b.getStatus() == BoxStatus.CORRECT || b.getStatus() == BoxStatus.INCORRECT) {
			return true;
		} else if(b.getStatus() == BoxStatus.MARKED) {
			b.setStatus(BoxStatus.EMPTY);
		} else {
			//compare with compareGrid
			if(answer.getStatus() == BoxStatus.CORRECT) {
				b.setStatus(BoxStatus.CORRECT);
				return true;
			} else {
				b.setStatus(BoxStatus.INCORRECT);
				return true;
			}
		}
		return false;
	}
	protected void initBoxes() {
		for(int x = 0; x < sizeX; x++) {
			for(int y = 0; y < sizeY; y++) {
				contents[x][y] = new Box();
			}
		}
	}
}