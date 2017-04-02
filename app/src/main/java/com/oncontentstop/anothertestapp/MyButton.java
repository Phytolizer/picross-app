package com.oncontentstop.anothertestapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mario on 4/1/2017.
 */

public class MyButton extends View {
	private static final int PAD = 4;
	private float width, height;
	private Paint borderPaint, fillPaint, textPaint, coverPaint;
	private String text;
	private final Rect textBounds = new Rect();
	private boolean drawCover = false;

	public MyButton(Context context) {
		super(context);
		init();
	}
	private void init() {
		text = "";
		borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		borderPaint.setColor(0xff000000);
		borderPaint.setStyle(Paint.Style.STROKE);
		fillPaint = new Paint();
		fillPaint.setColor(0xffffff00);
		fillPaint.setStyle(Paint.Style.FILL);
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setTextSize(getIdealTextSize());
		coverPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		coverPaint.setColor(0x66000000);
		coverPaint.setStyle(Paint.Style.FILL);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(0, 0, width, height, fillPaint);
		drawTextCentered(canvas, textPaint, text, width / 2, height / 2);
		canvas.drawRect(0, 0, width, height, borderPaint);
		if(drawCover) {
			canvas.drawRect(0, 0, width, height, coverPaint);
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getPointerCount() > 1) {
			return super.onTouchEvent(event);
		}
		int x = (int) event.getX();
		int y = (int) event.getY();
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			drawCover = true;
			invalidate();
		} else if(event.getAction() == MotionEvent.ACTION_UP) {
			drawCover = false;
			invalidate();
		}
		if(!isInBounds(x, y)) {
			drawCover = false;
			invalidate();
		}
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
	public void setBackgroundColor(@android.support.annotation.ColorInt int color) {
		fillPaint.setColor(color);
		invalidate();
	}

	public void drawTextCentered(Canvas canvas, Paint paint, String text, float cx, float cy){
		paint.getTextBounds(text, 0, text.length(), textBounds);
		canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
	}
}
