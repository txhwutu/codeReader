package codeReader;

import java.awt.Rectangle;
import java.util.ArrayList;

public class myFile {
	private String text;
	private ArrayList<Pair> hlList = new ArrayList<Pair>();
	private ArrayList<Anno> annoList = new ArrayList<Anno>();
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArrayList<Pair> getHlList() {
		return hlList;
	}
	public void setHlList(ArrayList<Pair> hlList) {
		this.hlList = hlList;
	}
	public ArrayList<Anno> getAnnoList() {
		return annoList;
	}
	public void setAnnoList(ArrayList<Anno> annoList) {
		this.annoList = annoList;
	}
}
 