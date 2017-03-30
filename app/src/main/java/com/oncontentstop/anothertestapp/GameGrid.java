package com.oncontentstop.anothertestapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static com.oncontentstop.anothertestapp.Direction.*;

/**
 * Created by mario on 3/28/2017.
 */

public class GameGrid extends View {
	private int squareWidth;
	private int sizeX, sizeY;
	private int totalW, totalH;
	private Coordinate initialTouch;
	private Coordinate bound1, bound2, currPos;
	private Paint bgPaint, borderPaint, xPaint, greenPaint, redPaint;
	private Grid playGrid;
	private SolutionGrid solution;
	private Direction touchDirection;
	public GameGrid(Context context, int sizeX, int sizeY) {
		super(context);
		init(sizeX, sizeY);
	}
	private void init(int sx, int sy) {
		sizeX = sx;
		sizeY = sy;
		playGrid = new Grid(sizeX, sizeY);
		solution = new SolutionGrid(sizeX, sizeY);
		int strokeWidth = 2;
		bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bgPaint.setColor(0xffffffff);
		bgPaint.setStyle(Paint.Style.FILL);
		borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		borderPaint.setColor(0xff000000);
		borderPaint.setStyle(Paint.Style.STROKE);
		borderPaint.setStrokeWidth(strokeWidth);
		xPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		xPaint.setColor(0xff000000);
		xPaint.setStyle(Paint.Style.STROKE);
		xPaint.setStrokeWidth(strokeWidth);
		greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		greenPaint.setColor(0xff00ff00);
		greenPaint.setStyle(Paint.Style.FILL);
		redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		redPaint.setColor(0xffff0000);
		redPaint.setStyle(Paint.Style.FILL);
		currPos = new Coordinate(0, 0);
		touchDirection = NONE;
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		squareWidth = calculateSquareWidth(w, h);
		totalW = w;
		totalH = h;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		squareWidth = calculateSquareWidth(totalW, totalH);
		canvas.drawRect(bound1.x, bound1.y, bound2.x, bound2.y, bgPaint);
		for(;currPos.x + squareWidth < bound2.x || currPos.y + squareWidth <= bound2.y; incrementCurrPos()) {
			boolean drawX = false;
			Coordinate boxCoords = getBoxPos(currPos.x, currPos.y);
			if(boxCoords == null)
				break;
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
			canvas.drawRect(currPos.x, currPos.y, currPos.x + squareWidth, currPos.y + squareWidth, borderPaint);
			if(drawX) {
				int pad = squareWidth / 8;
				canvas.drawLine(currPos.x + pad, currPos.y + pad, currPos.x + squareWidth - pad, currPos.y + squareWidth - pad, xPaint);
				canvas.drawLine(currPos.x + pad, currPos.y + squareWidth - pad, currPos.x + squareWidth - pad, currPos.y + pad, xPaint);//TODO test this to make sure it can draw
			}
		}
		canvas.drawRect(bound1.x, bound1.y, bound2.x, bound2.y, borderPaint);
	}
	private int calculateSquareWidth(int w, int h) {
		double whRatio = (double) w / (double) h;
		double xyRatio = (double)sizeX / (double)sizeY;
		if(whRatio > xyRatio) {
			//top & bottom will touch edges of the grid
			int size = h / sizeY;
			int gridPixelWidth = sizeX * size;
			int leftSpace = (w - gridPixelWidth) / 2;
			int topSpace = (h - sizeY * size) / 2;
			bound1 = new Coordinate(leftSpace, topSpace);
			currPos = new Coordinate(bound1);
			bound2 = new Coordinate(bound1.x + sizeX * squareWidth, bound1.y + sizeY * squareWidth);
			return size;
		} else {
			//left & right are extremes
			int size = w / sizeX;
			int gridPixelHeight = sizeY * size;
			int topSpace = (h - gridPixelHeight) / 2;
			int leftSpace = (w - sizeX * size) / 2;
			bound1 = new Coordinate(leftSpace, topSpace);
			currPos = new Coordinate(bound1);
			bound2 = new Coordinate(bound1.x + sizeX * squareWidth, bound1.y + sizeY * squareWidth);
			return size;
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getPointerCount() > 1)
			return super.onTouchEvent(event);
		boolean reveal = false;
		int x, y;
		x = (int) event.getX();
		y = (int) event.getY();
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			initialTouch = new Coordinate(x, y);
			reveal = true;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			touchDirection = NONE;
			return false;
		} else {
			if(touchDirection == NONE) {
				//determine which cardinal direction the line should go in
				//vertical
				if (Math.abs(x - initialTouch.x) < Math.abs(y - initialTouch.y)) {
					if(Math.abs(y - initialTouch.y) > squareWidth / 2) {
						x = initialTouch.x;
						touchDirection = VERTICAL;
						reveal = true;
					}
				} else {
					//horizontal
					if(Math.abs(x - initialTouch.x) > squareWidth / 2) {
						y = initialTouch.y;
						touchDirection = HORIZONTAL;
						reveal = true;
					}
				}
			} else {
				switch(touchDirection) {
					case HORIZONTAL:
						y = initialTouch.y;
						reveal = true;
						break;
					case VERTICAL:
						x = initialTouch.x;
						reveal = true;
						break;
				}
			}
		}
		if(x < bound1.x || x > bound2.x || y < bound1.y || y > bound2.y) {
			return super.onTouchEvent(event);
		}
		Coordinate boxCoords = getBoxPos(x, y);
		if(reveal)
			playGrid.reveal(boxCoords.x, boxCoords.y, solution);
		invalidate();
		return true;//tells the view to keep updating position
	}
	private void incrementCurrPos() {
		if(currPos.x + squareWidth >= bound2.x) {
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
		Coordinate boxPos = new Coordinate(unPaddedPos.x / squareWidth, unPaddedPos.y / squareWidth);
		if(boxPos.x >= playGrid.sizeX || boxPos.y >= playGrid.sizeY || boxPos.x < 0 || boxPos.y < 0)
			return null;
		return boxPos;
	}
}
