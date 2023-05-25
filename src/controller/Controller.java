package controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;

import models.CONSTANTS;
import models.Circulo;
import models.Edge;
import models.Graph;
import views.ViewsPrincipal;

public class Controller implements MouseListener,MouseMotionListener,ActionListener {

	CopyOnWriteArrayList<Circulo> circulos;
	CopyOnWriteArrayList<Edge> edges;
	ArrayList<Graph> grafos;
	ViewsPrincipal views;
	Circulo actual;
	Circulo ini;
	Circulo fin;
	Graph g;
	int nodos;
	boolean contains = false;
	boolean conectar;
	boolean dibujar;
	public Controller() {
		g = new Graph();
		grafos = new ArrayList<>();
		circulos = new CopyOnWriteArrayList<>();
		edges = new CopyOnWriteArrayList<>();
		views = new ViewsPrincipal(this,this);


	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (dibujar==true) {
			try {
				if (circulos.isEmpty()) {
					String data=views.mensajeSOlicitar("Mensaje","Ingresa Identificador");
					if (!data.equals("")) {
						Circulo circulo = new Circulo(data,e.getX(),e.getY());
						circulos.add(circulo);
						g.addNode(circulo);
						views.paintCirculos(circulos);
					}
				}else {
					if (interset(e)==false) {
						String data2=views.mensajeSOlicitar("Mensaje","Ingresa Identificador");
						if (!data2.equals("")) {
							Circulo circulo = new Circulo(data2,e.getX(),e.getY());
							circulos.add(circulo);
							views.paintCirculos(circulos);
							g.addNode(circulo);
						}
					}
				}
			} catch (Exception e2) {
			}
		}else {
			try {
				
				if (!e.getComponent().getName().equals("")&&e!=null) {
					Component newComponent = e.getComponent();
					if (e.getComponent().getName().equals("Grafica Grafo")) {
						views.paintZoom(newComponent, conjuntoVertices(g.getNodes()), conjuntosAristas(g.getNodes()), secuenciaDeGrados(g.getNodes()), generarlistaAdyacencia(g.getNodes()),g.generateMatrizAdyacencia(),true);
					}else {
						views.paintZoom(newComponent, conjuntoVertices(g.getNodes()), conjuntosAristas(g.getNodes()), secuenciaDeGrados(g.getNodes()), generarlistaAdyacencia(g.getNodes()),g.generateMatrizAdyacencia(),false);
					}
				}
			} catch (Exception e2) {
			}
		}

	}
	


	public boolean interset(MouseEvent e) {
		for (Circulo circulo : circulos) {
			if (circulo.getRectangulo().intersects(new Rectangle(e.getPoint(),new Dimension(50,50)))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		//		if (!e.getComponent().getName().equals("")&&e!=null) {
		//			views.paintexitMouse();
		//			System.out.println("salio");
		//		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (Circulo circulo : circulos) {
			if (circulo.getRectangulo().contains(e.getPoint())) {
				views.resaltarCircle(circulo);
				actual = circulo;
				ini = circulo;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (Circulo circulo : circulos) {
			if (circulo.getRectangulo().contains(e.getPoint())) {
				views.resaltarCircle(circulo);
				if (conectar==true) {
					fin = circulo;
					try {
						String data = views.mensajeSOlicitar("Mensaje", "Ingrese la DISTANCIA deseada");
						if (!data.equals("")&&data!=null) {
							try {
								edges.add(new Edge(edges.size()+1+"",ini, fin,(float)(Integer.parseInt(data)),ini));
								ini.addEdge(new Edge(edges.size()+"",ini, fin,(float)(Integer.parseInt(data)),ini));
								views.paintEdges(edges);

							} catch (Exception e2) {
								views.mensaje("���ERROR!!!","DISTANCIA ERRONEA :( ");
							}
						}
					} catch (Exception e2) {
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		new Controller();
	}
	@Override
	public void mouseDragged(MouseEvent e) {

	}
	@Override
	public void mouseMoved(MouseEvent e) {
	JButton b =	(JButton) e.getComponent();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (ACTIONS.valueOf(e.getActionCommand())) {
		case DIBUJAR:
			views.dibujarGrafo();
			views.activateBotonesMenu(false);
			break;
		case ADYACENCIA:
			try {
				nodos =Integer.parseInt(views.mensajeSOlicitar("Mensaje", "Ingrese CANTIDAD de nodos"));
				views.tablaAdyacencia(nodos);
				views.activateBotonesMenu(false);
			} catch (Exception e2) {
				views.mensaje("���ERROR!!!","NUMERO DE NODOS ERRONEO :( ");
			}
			break;
		case GUARDARGRILLA:
			crearGrafoN(views.dataGrilla());
			views.generarGrafica(g.getNodes(), conjuntoVertices(g.getNodes()), conjuntosAristas(g.getNodes()), secuenciaDeGrados(g.getNodes()), generarlistaAdyacencia(g.getNodes()),g.generateMatrizAdyacencia());
			grafos.add(g);
			g.generateMatrizIncidencia();
			g.algoritmoPrim(g.buscarCirculo(views.solicitarInicial(g.getNodes())));
//			g.algoritmodeDistraj(g.buscarCirculo(views.solicitarInicial(g.getNodes())));
			break;
		case GENERAR_MATRIZ:
			System.out.println(g.getNodes().size());
			System.out.println(g.generateMatrizAdyacencia());
			g.generateMatrizIncidencia();
			g.algoritmodeDistraj(g.buscarCirculo(views.solicitarInicial(g.getNodes())));
//			g.algoritmodeDistraj();
			g.algoritmoPrim(g.buscarCirculo(views.solicitarInicial(g.getNodes())));

			views.generarRepresentaciones( conjuntoVertices(g.getNodes()), conjuntosAristas(g.getNodes()), secuenciaDeGrados(g.getNodes()), generarlistaAdyacencia(g.getNodes()),g.generateMatrizAdyacencia());
			
			break;
		case ALGORITMO_PRIM:
//			g.algoritmodeDistraj(g.buscarCirculo(views.solicitarInicial(g.getNodes())));
//			System.out.println(views.solicitarInicial(g.getNodes()));
//			views.generarGraficaPrim(g.getNodes(), conjuntoVertices(g.getNodes()), conjuntosAristas(g.getNodes()), secuenciaDeGrados(g.getNodes()), generarlistaAdyacencia(g.getNodes()),g.generateMatrizAdyacencia());
			break;
		case DISTANCIAS:
			try {
				nodos =Integer.parseInt(views.mensajeSOlicitar("Mensaje", "Ingrese CANTIDAD de nodos"));
				views.matrizDistancias(nodos);
				views.activateBotonesMenu(false);
			} catch (Exception e2) {
				views.mensaje("���ERROR!!!","NUMERO DE NODOS ERRONEO :( ");
			}
			break;
		case GUARDAR_DISTANCIAS:
			crearGrafoDistancias(views.dataGrillaDistancias());
			views.generarGrafica(g.getNodes(), conjuntoVertices(g.getNodes()), conjuntosAristas(g.getNodes()), secuenciaDeGrados(g.getNodes()), generarlistaAdyacencia(g.getNodes()),g.generateMatrizAdyacencia());
			break;
		case ADD_CIRCULO:
			dibujar =true;
			conectar=false;
			views.activarBotones(ACTIONS.ADD_CIRCULO.toString());
			break;
		case NUEVO_GRAFO:
			g = new Graph();
			views.clearInfo();
			views.activateBotonesMenu(true);
			break;
		case CONECTAR:
			dibujar =false;
			conectar=true;
			views.activarBotones(ACTIONS.CONECTAR.toString());
			break;
		case REMOVER:
			dibujar =false;
			conectar=false;
			if (actual!=null) {
				if (actual.getEdges()!=null&&!actual.getEdges().isEmpty()) {
					views.mensaje("ERROR ", "No puedes eliminar este Nodo");
				}else {
					g.remover(actual);
					views.resaltarCircle(null);
					circulos.remove(actual);
					views.paintCirculos(circulos);
				}
			}
			views.activarBotones(ACTIONS.REMOVER.toString());
			break;
		case GUARDAR_GRAFO:
			try {
				if (g!=null&&!g.getNodes().isEmpty()) {
					grafos.add(g);
				}else {
					views.mensaje("Error", "Debes guardar un Grafo Valido");
				}
			} catch (Exception e2) {
				views.mensaje("Error", "Debes guardar un Grafo Valido");
			}
			break;
		}
	}
	

	private String generarlistaAdyacencia(ArrayList<Circulo> g) {
		String D="";
		if (g!=null) {
			String data ="[ ";
			for (Circulo circulo : g) {
				String	dataL ="{ ";
				if (circulo.getEdges()!=null) {
					for (Edge circulo2 : circulo.getEdges()) {
						dataL+=circulo2.getDestination().getData()+",";
					}
				}
				dataL+="},";
				char[] cadenaV = dataL.toCharArray();
				cadenaV[cadenaV.length-3]=' ';
				for (int i = 0; i < cadenaV.length; i++) {
					D+=cadenaV[i];
				}
				data+=D;
			}
			data +="]";

			char[] cadenaV = data.toCharArray();
			cadenaV[cadenaV.length-2]=' ';

			for (int i = 0; i < cadenaV.length; i++) {
				D+=cadenaV[i];
			}
		}
		return D;
	}
	private String secuenciaDeGrados(ArrayList<Circulo> g) {
		String D="";
		if (g!=null) {

			String data ="( ";
			for (Circulo circulo : g) {
				if (circulo.getEdges()!=null) {
					data+=circulo.getEdges().size()+",";
				}
			}
			data +=")";
			char[] cadenaV = data.toCharArray();
			cadenaV[cadenaV.length-2]=' ';

			for (int i = 0; i < cadenaV.length; i++) {
				D+=cadenaV[i];
			}
		}
		return D;
	}

	private String conjuntosAristas(ArrayList<Circulo> g) {
		String D="";
		if (g!=null) {
			String dataAristas ="A = { ";
			CopyOnWriteArrayList<String> dataPrimeroList = new CopyOnWriteArrayList<>();
			for (Circulo circulo : g) {
				if (circulo.getEdges()!=null) {
					for (Edge edge : circulo.getEdges()) {
						String dataPrimero = "{"+edge.getOrigin().getData()+","+edge.getDestination().getData()+"}";
						dataPrimeroList.add(dataPrimero);
					}
				}
			}
			for (int i = 0; i < dataPrimeroList.size(); i++) {
				for (int j = 0; j < dataPrimeroList.size(); j++) {
					if (dataPrimeroList.get(i).equals(volterarCadena(dataPrimeroList.get(j)))&&i!=j) {
						dataPrimeroList.remove(j);
					}
				}
			}

			for (String dataH : dataPrimeroList) {
				dataAristas+=dataH;
				dataAristas+=",";
			}

			dataAristas+="}";
			char[] cadena = dataAristas.toCharArray();
			cadena[cadena.length-2]=' ';
			for (int i = 0; i < cadena.length; i++) {
				D+=cadena[i];
			}
		}
		return D;
	}
	private String conjuntoVertices(ArrayList<Circulo> g) {
		String D="";
		if (g!=null) {
			String dataVertices ="V= { ";
			for (Circulo circulo : g) {
				dataVertices+=circulo.getData()+",";
			}
			dataVertices +="}";

			char[] cadenaV = dataVertices.toCharArray();
			cadenaV[cadenaV.length-2]=' ';
			for (int i = 0; i < cadenaV.length; i++) {
				D+=cadenaV[i];
			}
		}
		return D;
	}

	private String volterarCadena(String sCadena) {
		String sCadenaInvertida ="";
		for (int x=sCadena.length()-2;x>=1;x--)
			sCadenaInvertida = sCadenaInvertida + sCadena.charAt(x);
		return "{"+sCadenaInvertida+"}";
	}

	public void crearGrafoN(ArrayList<Integer> data) {
		for (int i = 0; i < nodos; i++) {
			Circulo c = new Circulo((i+1)+"", i*80,i*80);
			g.addNode(c);
		}
		generateEdge(data);
	}

	public void crearGrafoDistancias(ArrayList<Integer> data) {
		for (int i = 0; i < nodos; i++) {
			Circulo c = new Circulo((i+1)+"", i*80,i*80);
			g.addNode(c);
		}
		generateEdgeDistancias(data);
	}

	private void generateEdge(ArrayList<Integer> data) {
		int iteraciones=0;
		int ddd=0;
		Circulo c = g.getNodes().get(iteraciones);
		for (int i = 0; i < data.size(); i++) {
			if (ddd==nodos) {
				iteraciones+=1;
				c = g.getNodes().get(iteraciones);
				ddd=0;
			}
			if ( data.get(i)==1) {
				edges.add(new Edge(edges.size()+1+"",c, g.buscarCirculo((ddd+1)+""), 0,c));
				c.addEdge(new Edge(edges.size()+"",c, g.buscarCirculo((ddd+1)+""), 0,c));
			}
			ddd++;
		}
	}

	private void generateEdgeDistancias(ArrayList<Integer> data) {
		int iteraciones=0;
		int ddd=0;
		Circulo c = g.getNodes().get(iteraciones);
		for (int i = 0; i < data.size(); i++) {
			if (ddd==nodos) {
				iteraciones+=1;
				c = g.getNodes().get(iteraciones);
				ddd=0;
			}
			if ( data.get(i)!=0) {
				edges.add(new Edge(edges.size()+1+"",c, g.buscarCirculo((ddd+1)+""), data.get(i),c));
				c.addEdge(new Edge(edges.size()+"",c, g.buscarCirculo((ddd+1)+""), data.get(i),c));
			}
			ddd++;
		}
	}
}
