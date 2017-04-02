package com.oncontentstop.anothertestapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mario on 4/1/2017.
 */

public class MyButton extends View {
	private static final int PAD = 4;
	private Coordinate initialTouch, currentTouch;
	private float width, height;
	private Paint borderPaint, fillPaint, textPaint;
	private String text;

	public MyButton(Context context) {
		super(context);
		init();
	}
	private void init() {
		text = "";
		borderPaint = new Paint();
		borderPaint.setColor(0xff000000);
		borderPaint.setStyle(Paint.Style.STROKE);
		fillPaint = new Paint();
		fillPaint.setColor(0xffffff00);
		fillPaint.setStyle(Paint.Style.FILL);
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setTextSize(getIdealTextSize());
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(0, 0, width, height, fillPaint);
		float textWidth = textPaint.measureText(text);
		float textHeight = textPaint.getTextSize();
		canvas.drawText(text, width / 2 - textWidth / 2, height / 2 + textHeight / 2, textPaint);
		canvas.drawRect(0, 0, width, height, borderPaint);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getPointerCount() > 1) {
			return super.onTouchEvent(event);
		}
		int x = (int) event.getX();
		int y = (int) event.getY();
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			initialTouch = new Coordinate(x, y);
		}
		currentTouch = new Coordinate(x, y);
		//last resort
		return super.onTouchEvent(event);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
		textPaint.setTextSize(getIdealTextSize());
	}
	private boolean isInBounds(int x, int y) {
		return x >= 0 && y >= 0 && x < width && y < height;
	}
	private float getIdealTextSize() {
		float testSize = 20;
		textPaint.setTextSize(testSize);
		float width = textPaint.measureText(text);
		if(width == 0)
			return testSize;
		double whRatio = width / testSize;
		//want width to be close to edges
		float goalWidth = this.width - (2 * PAD);
		float possibleHeight = (float) (goalWidth / whRatio);
		if(possibleHeight < height - PAD * 2)
			return (float) (goalWidth / whRatio);
		else
			return height - PAD * 2;
	}
	public void setText(String newText) {
		text = newText;
		textPaint.setTextSize(getIdealTextSize());
	}
	public void setBGColor(@android.support.annotation.ColorInt int color) {
		fillPaint.setColor(color);
		invalidate();
	}
}
