package models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Graph {
	private ArrayList<Circulo> ciculos;
	ArrayList<Circulo> cola = new ArrayList<>();
	HashMap<String,String> datos = new HashMap<>();
	ArrayList<String> dataNew;
	String[][] matriz;

	public void addNode(Circulo node) {
		if (ciculos ==null) {
			ciculos = new ArrayList<>();
		}
		ciculos.add(node);
	}

	public ArrayList<Circulo> getNodes() {
		return ciculos;
	}

	@Override
	public String toString() {
		generarlistaAdyacencia();
		return "Graph [nodes=" + ciculos + "]";
	}

	public Circulo buscarCirculo(String data) {
		for (Circulo circulo : ciculos) {
			if (circulo.getData().equals(data)) {
				return circulo;
			}
		}
		return null;
	}
	public void generarlistaAdyacencia(){
		@SuppressWarnings("unused")
		String data ="";
		for (Circulo circulo : ciculos) {
			data+="{";
			for (Edge circulo2 : circulo.getEdges()) {
				data+=circulo2.getDestination();
			}
			data+="}";
		}
	}

	public String generateMatrizIncidencia() {
		String datat="";
		if (ciculos!=null) {
			if (ciculos!=null) {
				for (Circulo circulo : ciculos) {
					if (circulo.getEdges()!=null) {
						for (Circulo circulo2 : ciculos) {
							if (lineData(circulo2, circulo.getEdges())==true) {
								datat+=" 1 ";
							}
						}
					}else {
						for (@SuppressWarnings("unused") Circulo circulo2 : ciculos) {
							datat+=" 0 ";
						}
					}
					datat+="\n";
				}
			}
		}
		return datat;
	}

	public String generateMatrizAdyacencia() {
		String datat="";
		if (ciculos!=null) {
			if (ciculos!=null) {
				for (Circulo circulo : ciculos) {
					if (circulo.getEdges()!=null) {
						for (Circulo circulo2 : ciculos) {
							if (lineData(circulo2, circulo.getEdges())==true) {
								datat+=" 1 ";
							}else {
								datat+=" 0 ";
							}
						}
					}else {
						for (@SuppressWarnings("unused") Circulo circulo2 : ciculos) {
							datat+=" 0 ";
						}
					}
					datat+="\n";
				}
			}
		}
		return datat;
	}

	public boolean lineData(Circulo busqueda,CopyOnWriteArrayList<Edge> circulo) {
		for (Edge edge : circulo) {
			if (busqueda.getData().equals(edge.getDestination().getData())) {
				return true;
			}
		}
		return false;
	}

	public void cicloHamiltoniano(CopyOnWriteArrayList<Edge> aristas) {
		for (Circulo circulo : ciculos) {
			circulo.colorEnter();
		}
	}

	//	public void matrizIncidenci(CopyOnWriteArrayList<Edge> edges) {
	//		String data="  ";
	//		int linea=1;
	//		System.out.println("JAJAJAJJAJAJAJAJA");
	//		System.out.println(edges.size()+" cantidad de lineas");
	//		for (@SuppressWarnings("unused") Edge circulo2 : edges) {
	//			data+=" "+(linea++)+"   ";
	//		}
	//		//		for (Circulo circulo : ciculos) {
	//		//			data+="\n";
	//		//			data+=circulo.getData();
	//		//			for (Edge circulo2 : circulo.getEdges()) {
	//		//				if (circulo2.getOrigin().getData().equals(circulo.getData())) {
	//		//					data+="  1  ";
	//		//				}
	//		//			}
	//		//		}
	//		System.out.println(data);
	//	}


	public void algoritmoPrim(Circulo desde) {
		desde.setValorDesde(0);
		recorertodoDesde(desde,0);
		for (Circulo circulo : cola) {

		}

	}




	public void recorertodoDesde(Circulo cActual,int posicionInicial) {
		if (cActual.isVisitado()==false) {
			cActual.visitadoOk();
			int d=posicionInicial;
			posicionInicial++;
			if ( cActual.getEdges()!=null||cActual.getEdges().size()>0) {
				for (Edge edge : cActual.getEdges()) {
					cola.add(edge.getDestination());
					recorertodoDesde(cola.get(d),posicionInicial);
				}
			}
		}
	}



	public void remover(Circulo actual) {
		ciculos.remove(actual);	
	}

	public void algoritmodeDistraj(Circulo cInicial) {
	}
}
