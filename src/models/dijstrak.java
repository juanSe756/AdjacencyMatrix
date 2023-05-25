package models;

import java.util.Arrays;
import java.util.PriorityQueue;

public class dijstrak {

	private static int matrix[][];
	private static int maxvertices;
	private static int maxaristas;
	private static int aristas;


	public dijstrak() {
	}
	public String algoritmoFloyd() {
		return "";
	}



	public void dataPrueva() {
		Graph g = new Graph();
		Circulo one = new Circulo("uno",0,0);
		Circulo two = new Circulo("Dos",0,0);
		Circulo three = new Circulo("Tres",0,0);
		Circulo four = new Circulo("Cuatro",0,0);
		Circulo five = new Circulo("Cinco",0,0);

//		one.addEdge(new Edge(one,one,2.0f));
//		one.addEdge(new Edge(one,three,2.0f));
//		one.addEdge(new Edge(one,five,5f));
//		two.addEdge(new Edge(two, two,8.0f));
//		two.addEdge(new Edge(two, five,8.0f));
//		three.addEdge(new Edge(three,three,5.0f));
//		three.addEdge(new Edge(three,four,5.0f));
//		four.addEdge(new Edge(four,one,5.0f));
//		five.addEdge(new Edge(five,three,10.0f));
//		five.addEdge(new Edge(five,five,10.0f));

		g.addNode(one);
		g.addNode(two);
		g.addNode(three);
		g.addNode(four);
		g.addNode(five);

		System.out.println(g.toString());
	}

	public static void main(String[] args) {
		new dijstrak();
	}
}
