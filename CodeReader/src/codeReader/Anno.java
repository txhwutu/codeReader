package codeReader;

public class Anno {
	public Anno(Double a, Double b, Double h, Double w, String c) {
		// TODO Auto-generated constructor stub
		setStart(a);
		setEnd(b);
		setHeight(h);
		setWidth(w);
		setContent(c);
	}
	
	public Double getStart() {
		return start;
	}
	public void setStart(Double start) {
		this.start = start;
	}
	public Double getEnd() {
		return end;
	}
	public void setEnd(Double end) {
		this.end = end;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getHeight() {
		return height;
	}

	public void setHeight(double hight) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	private double start;
	private double end;
	private double height;
	private double width;
	private String content;
}
