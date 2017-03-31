package com.oncontentstop.anothertestapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 3/28/2017.
 */

public class Grid {
	protected int sizeX, sizeY;
	protected Box[][] contents;
	private Clue[] cluesHorizontal, cluesVertical;
	public Grid(int sx, int sy) {
		sizeX = sx;
		sizeY = sy;
		contents = new Box[sx][sy];
		initBoxes();
		initClues();
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
	private void initClues() {
		cluesHorizontal = new Clue[sizeY];
		cluesVertical = new Clue[sizeX];
		for(int i = 0; i < sizeY; i++) {
			cluesHorizontal[i] = new Clue();
		}
		for(int i = 0; i < sizeX; i++) {
			cluesVertical[i] = new Clue();
		}
	}
	public void createCluesFrom(SolutionGrid solutionGrid) {
		//all rows
		for(int row = 0; row < sizeY; row++) {
			int currGroupLength = 0;
			for(int boxInRow = 0; boxInRow < sizeX; boxInRow++) {
				if(solutionGrid.getBox(boxInRow, row).getStatus() == BoxStatus.CORRECT) {
					currGroupLength++;
				} else if(currGroupLength > 0) {
					cluesHorizontal[row].addGroup(currGroupLength);
					currGroupLength = 0;
				}
			}
			if(currGroupLength > 0) {
				cluesHorizontal[row].addGroup(currGroupLength);
			}
		}
		//all columns
		for(int col = 0; col < sizeX; col++) {
			int currGroupLength = 0;
			for(int boxInCol = sizeY - 1; boxInCol >= 0; boxInCol--) {
				if(solutionGrid.getBox(col, boxInCol).getStatus() == BoxStatus.CORRECT) {
					currGroupLength++;
				} else if(currGroupLength > 0) {
					cluesVertical[col].addGroup(currGroupLength);
					currGroupLength = 0;
				}
			}
			if(currGroupLength > 0) {
				cluesVertical[col].addGroup(currGroupLength);
			}
		}
	}
	public String toString() {
		String[] rows = new String[sizeY];
		for(int i = 0; i < sizeY; i++) {
			rows[i] = "";
			for(int j = 0; j < sizeX; j++) {
				if(contents[i][j].getStatus() == BoxStatus.CORRECT) {
					rows[i] += "" + '1';
				} else {
					rows[i] += "" + '0';
				}
			}
		}
		String out = "";
		for(int row = 0; row < sizeY; row++) {
			out += rows[row] + ", ";
		}
		return out;
	}
	public Clue[] getCluesHorizontal() {
		return cluesHorizontal;
	}
	public Clue[] getCluesVertical() {
		return cluesVertical;
	}
}
