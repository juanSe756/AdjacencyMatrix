package models;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

public class Circulo {
	private float valorDesde;
	private int cordenadasX;
	private int cordenadasY;
	private int dimension;
	private Rectangle rectangulo;
	private Color color;
	private String data;
	private CopyOnWriteArrayList<Edge> edges;
	boolean visitado;
	public Circulo(String data,int cordenadasX,int cordenadasY) {
		this.data=data;
		this.cordenadasX=cordenadasX;
		this.cordenadasY=cordenadasY;
		dimension = CONSTANTS.tamanio;
		rectangulo = new Rectangle(cordenadasX, cordenadasY, dimension, dimension);
		visitado=false;

	}
	
	public boolean isVisitado() {
		return visitado;
	}
	
	public void visitadoOk(){
		visitado=true;
	}
	public void visitadoNo(){
		visitado=true;
	}
	
	public float getValorDesde() {
		return valorDesde;
	}
	
	public void setValorDesde(float valorDesde) {
		this.valorDesde = valorDesde;
	}
	
	public CopyOnWriteArrayList<Edge> getEdges() {
		return edges;
	} 

	public void addEdge(Edge edge) {
		if (edges== null) {
			edges= new CopyOnWriteArrayList<>();
		}
		edges.add(edge);
	}

	public void colorEnter() {
		color=Color.BLUE;
	}
	public void colorExit() {
		color=Color.WHITE;
	}

	public int getCordenadasX() {
		return cordenadasX;
	}
	public int getCordenadasY() {
		return cordenadasY;
	}
	public int getDimension() {
		return dimension;
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}
	
	public void setRectangulo(Rectangle rectangulo) {
		this.rectangulo = rectangulo;
		cordenadasX=rectangulo.x;
		cordenadasY=rectangulo.y;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getData() {
		return data;
	}
	

	@Override
	public String toString() {
		return data +" edges->" + edges ;
	}	
}
