package models;

public class Edge {
	private String name; 
	private Circulo padre;
	private Circulo origin;
	private Circulo destination;
	private float distanciaf;
	public Edge(String name,Circulo origin, Circulo destination,float distanciaf,Circulo padre) {
		this.origin = origin;
		this.padre =padre;
		this.distanciaf=distanciaf;
		this.destination = destination;
		this.name= name;
	}
	public Circulo getDestination() {
		return destination;
	}
	public Circulo getOrigin() {
		return origin;
	}
	public float getDistanciaf() {
		return distanciaf;
	}
	public Circulo getPadre() {
		return padre;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "[Origen: " + origin.getData() + ", Destino: " + destination.getData() + ", Distancia=" + distanciaf+"]";
	}
	
}
