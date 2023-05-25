package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.Circulo;
import models.Edge;

public class ViewsPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	PanelCOntenDibujo dibujo;
	ActionListener actionListener;
	MouseListener mouseListener;
	JPanel p;
	Matriz tablaUno;
	MatrizDistancias distancias;
	PanelGrafo grafo;
	PanelGrafo grafoPrim;
	PanelRepresentaciones representaciones;
	Component viewsActual;
	Image image;
	Menu menu;
	public ViewsPrincipal(MouseListener mouseListener,ActionListener actionListener) {
		setSize(1080,720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(rootPane);
		this.actionListener=actionListener;
		this.mouseListener=mouseListener;
		this.setResizable(false);
		JPanel m = new JPanel();
		grafo = new PanelGrafo();
		grafoPrim = new PanelGrafo();
		m.setBorder(BorderFactory.createEmptyBorder(20,20,15,5));
		m.setBackground(Color.BLACK);
		menu = new Menu(actionListener);
		m.add(menu);
		add(m,BorderLayout.LINE_START);
		representaciones = new PanelRepresentaciones(mouseListener);
		add(representaciones,BorderLayout.LINE_END);
		p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20,20,20,20),BorderFactory.createLineBorder(Color.BLACK, 3,true)));
		//		p.addMouseListener(mouseListener);
		p.setBackground(Color.WHITE);
		p.setLayout(new BorderLayout());
		add(p,BorderLayout.CENTER);
		setVisible(true);
	}

	public void paintCirculos(CopyOnWriteArrayList<Circulo> circulos) {
		dibujo.painCirculos(circulos);

	}

	public void paintEdges(CopyOnWriteArrayList<Edge> edges) {
		dibujo.paintEdges(edges);
	}

	public void resaltarCircle(Circulo circulo) {
		dibujo.resaltarCirculo(circulo);
		dibujo.repaint();
	}

	public void mensaje(String titulo,String mensaje) {
		JOptionPane.showMessageDialog(null,mensaje,titulo,1);
	}

	public String mensajeSOlicitar(String titulo,String mensaje) {
		return JOptionPane.showInputDialog(titulo,mensaje);
	}

	public void dibujarGrafo() {
		p.removeAll();
		p.addMouseListener(mouseListener);
		dibujo = new PanelCOntenDibujo(mouseListener,actionListener);
		viewsActual= dibujo;
		p.add(dibujo,BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	public void tablaAdyacencia(int nodos) {
		p.removeAll();
		p.removeMouseListener(mouseListener);
		tablaUno = new Matriz(nodos,actionListener);
		viewsActual= tablaUno;
		p.add(tablaUno,BorderLayout.CENTER);
		revalidate();
		repaint();
	}


	public ArrayList<Integer> dataGrilla() {
		return tablaUno.dataGrilla();
	}

	public ArrayList<Integer> dataGrillaDistancias() {
		return distancias.dataGrilla();
	}


	public void generarGrafica(ArrayList<Circulo> grafoIn,String conjuntoVertices,String conjuntoAristas,String secuencia,String listaAdyacencia,String matrizAdyacencia) {
		p.removeAll();
		p.removeMouseListener(mouseListener);
		grafo.paintCirculos(grafoIn);
		p.add(grafo,BorderLayout.CENTER);
		viewsActual= grafo;
		revalidate();
		repaint();
		n(conjuntoVertices, conjuntoAristas, secuencia, listaAdyacencia,matrizAdyacencia);
	}
	public void n(String conjuntoVertices,String conjuntoAristas,String secuencia,String listaAdyacencia,String matrizAdyacencia) {
		image =createImage(grafo);
		representaciones.paintData(conjuntoVertices, conjuntoAristas, secuencia, listaAdyacencia,matrizAdyacencia,image,null);
		revalidate();
		repaint();
	}
	public void generarGraficaPrim(ArrayList<Circulo> grafoIn,String conjuntoVertices,String conjuntoAristas,String secuencia,String listaAdyacencia,String matrizAdyacencia) {
		p.removeAll();
		p.removeMouseListener(mouseListener);
		grafoPrim.paintCirculos(grafoIn);
		p.add(grafoPrim,BorderLayout.CENTER);
		viewsActual= grafoPrim;
		revalidate();
		repaint();
		n(conjuntoVertices, conjuntoAristas, secuencia, listaAdyacencia,matrizAdyacencia);
	}

	public String solicitarInicial(ArrayList<Circulo> circulos) {
		String[] data = new String[circulos.size()];
		for (int i = 0; i < circulos.size(); i++) {
			data[i]=circulos.get(i).getData();
		}
		JFrame frame = new JFrame("Seleciona un Nodo");
		String favoritePizza = (String) JOptionPane.showInputDialog (frame, 
				"Seleciona el Nodo Inicial",
				"Seleciona un Nodo",
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				data, 
				data[0]);
		return favoritePizza;
	}
	public void generarRepresentaciones(String conjuntoVertices,String conjuntoAristas,String secuencia,String listaAdyacencia,String matrizAdyacencia) {
		representaciones.paintData(conjuntoVertices, conjuntoAristas, secuencia, listaAdyacencia,matrizAdyacencia,image,null);

		revalidate();
		repaint();
	}

	public void matrizDistancias(int nodos) {
		p.removeAll();
		p.removeMouseListener(mouseListener);
		distancias = new MatrizDistancias(nodos, actionListener);
		viewsActual= distancias;
		p.add(distancias,BorderLayout.CENTER);
		revalidate();
	}
	public void paintZoom(Component info,String conjuntoVertices,String conjuntoAristas,String secuencia,String listaAdyacencia,String matrizAdyacencia,boolean imagen) {
		p.removeAll();
		p.removeMouseListener(mouseListener);
		remove(representaciones);
		representaciones = new PanelRepresentaciones(mouseListener);
		if (imagen) {
			representaciones.paintData(conjuntoVertices, conjuntoAristas, secuencia, listaAdyacencia,matrizAdyacencia,image,null);
			p.add(grafo,BorderLayout.CENTER);
		}else {
			representaciones.paintData(conjuntoVertices, conjuntoAristas, secuencia, listaAdyacencia,matrizAdyacencia,image,(JComponent)p);
			info.setFont(new Font("Arial",50,30));
			p.add(info,BorderLayout.CENTER);
		}
		add(representaciones,BorderLayout.LINE_END);
		p.setBackground(Color.WHITE);
		revalidate();
		repaint();
	}

	private  BufferedImage createImage(JPanel panel) {
		int w = panel.getWidth();
		int h = panel.getHeight();
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		panel.paint(g);
		return bi;
	}
	public void paintexitMouse() {
		if (viewsActual!=null) {
			viewsActual.setPreferredSize(p.getSize());
			p.removeAll();
			p.removeMouseListener(mouseListener);
			p.setBackground(Color.WHITE);
			JPanel pan = new JPanel();
			pan.add(viewsActual,BorderLayout.CENTER);
			p.add(pan);
			p.revalidate();
			revalidate();
		}
	}

	public void clearInfo() {
		try {
			p.removeAll();
			p.removeMouseListener(mouseListener);
			grafo.limpiarVentana();
			representaciones.paintData("", "", "", "","",null,null);
			dibujo.clear();
			repaint();
		} catch (Exception e) {
		}
	}

	public void activarBotones(String data) {
		dibujo.ActivarBotones(data);
	}
	
	public void activateBotonesMenu(Boolean data){
		menu.activarBotones(data);
	}
}
