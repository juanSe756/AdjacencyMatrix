package views;

import java.awt.Rectangle;

public class NodeGraphics {

	private String data;
	private Rectangle rec;

	public NodeGraphics(String data,Rectangle rec) {
		this.data=data;
		this.rec= rec;
	}

	public String getData() {
		return data;
	}

	public Rectangle getRec() {
		return rec;
	}

}
