package com.oncontentstop.anothertestapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 3/31/2017.
 */

public class Clue {
	private List<Integer> groupLengths;
	public Clue() {
		groupLengths = new ArrayList<>();
	}
	public Clue(int[] lengths) {
		groupLengths = new ArrayList<>();
		for(int length : lengths) {
			groupLengths.add(length);
		}
	}
	public Clue(List<Integer> lengths) {
		groupLengths = lengths;
	}
	public void addGroup(int length) {
		if(length <= 0) {
			return;
		}
		groupLengths.add(length);
	}
	public String toString() {
		String out = "";
		for(int length : groupLengths) {
			out += "" + length + " ";
		}
		return out.length() > 0 ? out.substring(0, out.length() - 1) : "0";//trims final space
	}
	public String toString(char separatorChar) {
		String out = "";
		for(int length : groupLengths) {
			out += "" + length + separatorChar;
		}
		return out.length() > 0 ? out.substring(0, out.length() - 1) : "0";//trims final space
	}
	public int getNumGroups() {
		return groupLengths.size();
	}
	public int getGroup(int index) {
		return groupLengths.get(index);
	}
}
