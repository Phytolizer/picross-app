package com.oncontentstop.anothertestapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mario on 3/28/2017.
 */

public class GameGrid extends View {
	private int squareWidth;
	private int sizeX, sizeY;
	private Coordinate bound1, bound2, currPos;
	private Paint bgPaint, borderPaint, xPaint, greenPaint, redPaint;
	private Grid playGrid;
	public GameGrid(Context context, AttributeSet attrs, int sizeX, int sizeY) {
		super(context, attrs);
		init(sizeX, sizeY);
	}
	private void init(int sx, int sy) {
		sizeX = sx;
		sizeY = sy;
		playGrid = new Grid(sizeX, sizeY);
		bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bgPaint.setColor(0xffffffff);
		bgPaint.setStyle(Paint.Style.FILL);
		borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		borderPaint.setColor(0xff000000);
		xPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		xPaint.setColor(0xff000000);
		greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		greenPaint.setColor(0xff00ff00);
		greenPaint.setStyle(Paint.Style.FILL);
		redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		redPaint.setColor(0xffff0000);
		redPaint.setStyle(Paint.Style.FILL);
		currPos = new Coordinate(0, 0);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		squareWidth = calculateSquareWidth(w, h);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(bound1.x, bound1.y, bound2.x, bound2.y, bgPaint);
		canvas.drawRect(bound1.x, bound1.y, bound2.x, bound2.y, borderPaint);
		for(;currPos.x < bound2.x || currPos.y < bound2.y; incrementCurrPos()) {
			boolean drawX = false;
			Coordinate boxCoords = getBoxPos(currPos.x, currPos.y);
			assert boxCoords != null;
			Box currBox = playGrid.getBox(boxCoords.x, boxCoords.y);
			Paint currPaint = null;
			switch(currBox.getStatus()) {
				case EMPTY:
					break;
				case CORRECT:
					currPaint = greenPaint;
					break;
				case INCORRECT:
					currPaint = redPaint;
					drawX = true;
					break;
				case MARKED:
					drawX = true;
					break;
			}
			if(currPaint != null)
				canvas.drawRect(currPos.x, currPos.y, currPos.x + squareWidth, currPos.y + squareWidth, currPaint);
			if(drawX) {
				int pad = squareWidth / 8;
				canvas.drawLine(currPos.x + pad, currPos.y + pad, currPos.x + squareWidth - pad, currPos.y + squareWidth - pad, xPaint);
				canvas.drawLine(currPos.x + pad, currPos.y + squareWidth - pad, currPos.x + squareWidth - pad, currPos.y + pad, xPaint);
			}
		}
	}
	private int calculateSquareWidth(int w, int h) {
		double whRatio = (double) w / (double) h;
		double xyRatio = (double)sizeX / (double)sizeY;
		if(whRatio > xyRatio) {
			//top & bottom will touch edges of the grid
			int size = h / sizeY;
			int gridPixelWidth = sizeX * size;
			int leftSpace = (w - gridPixelWidth) / 2;
			bound1 = new Coordinate(leftSpace, 0);
			bound2 = new Coordinate(leftSpace + gridPixelWidth, h);
			return size;
		} else {
			//left & right are extremes
			int size = w / sizeX;
			int gridPixelHeight = sizeY * size;
			int topSpace = (w - gridPixelHeight) / 2;
			bound1 = new Coordinate(0, topSpace);
			bound2 = new Coordinate(w, topSpace + gridPixelHeight);
			return size;
		}
	}
	public void touchAt(int relX, int relY) {

	}
	private void incrementCurrPos() {
		if(currPos.x >= bound2.x) {
			currPos.x = bound1.x;
			currPos.y += squareWidth;
		} else {
			currPos.x += squareWidth;
		}
	}
	private Coordinate getBoxPos(int pixelX, int pixelY) {
		if(pixelX < bound1.x || pixelX > bound2.x || pixelY < bound1.y || pixelY > bound2.y) {
			return null;
		}
		Coordinate unPaddedPos = new Coordinate(pixelX - bound1.x, pixelY - bound1.y);
		return new Coordinate(unPaddedPos.x / squareWidth, unPaddedPos.y / squareWidth);
	}
}
