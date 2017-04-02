package com.oncontentstop.anothertestapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by mario on 4/2/2017.
 */

public class MistakeCounter extends View {
	private final int PAD = 4;
	private Paint borderPaint, fadedXPaint, boldXPaint;
	private float width, height;
	private final Rect textBounds = new Rect();
	public MistakeCounter(Context context) {
		super(context);
		init();
	}

	private void init() {
		borderPaint = new Paint();
		borderPaint.setColor(Color.BLACK);
		borderPaint.setStyle(Paint.Style.STROKE);
		fadedXPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		fadedXPaint.setColor(0x66000000);
		fadedXPaint.setTextSize(getTextSize());
		fadedXPaint.setTextAlign(Paint.Align.LEFT);
		boldXPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		boldXPaint.setColor(0xffff0000);
		boldXPaint.setTextSize(getTextSize());
		boldXPaint.setTextAlign(Paint.Align.LEFT);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(0, 0, width, height, borderPaint);
		float totalWidthFilledByX = CommonVars.getMaxMistakes() * fadedXPaint.measureText("X");
		float xWidth = fadedXPaint.measureText("X");
		float emptySpace = width - totalWidthFilledByX;
		if(emptySpace < 0)
			return;
		float spacePerX = emptySpace / (CommonVars.getMaxMistakes() + 1);//includes start and end spaces
		for(int currX = 0; currX < CommonVars.getMaxMistakes(); currX++) {
			float xPos = spacePerX + currX * spacePerX + currX * xWidth + xWidth / 2;
			float yPos = height / 2;
			Paint p;
			if(currX < CommonVars.getNumMistakes()) {
				p = boldXPaint;
			} else {
				p = fadedXPaint;
			}
			drawTextCentered(canvas, p, "X", xPos, yPos);
		}
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
		fadedXPaint.setTextSize(getTextSize());
		boldXPaint.setTextSize(getTextSize());
	}

	private float getTextSize() {
		return height - 2 * PAD;
	}

	public void drawTextCentered(Canvas canvas, Paint paint, String text, float cx, float cy){
		paint.getTextBounds(text, 0, text.length(), textBounds);
		canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
	}
}
