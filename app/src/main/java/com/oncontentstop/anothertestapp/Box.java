package com.oncontentstop.anothertestapp;

import static com.oncontentstop.anothertestapp.BoxStatus.*;

/**
 * Created by mario on 3/28/2017.
 */

public class Box {
	private BoxStatus status;
	public Box() {
		status = EMPTY;
	}
	public BoxStatus getStatus() {
		return status;
	}

	public void setStatus(BoxStatus status) {
		this.status = status;
	}
	public String toString() {
		switch(status) {
			case EMPTY:
				return "EMPTY";
			case CORRECT:
				return "CORRECT";
			case INCORRECT:
				return "INCORRECT";
			case MARKED:
				return "MARKED";
		}
		return "";
	}
}
