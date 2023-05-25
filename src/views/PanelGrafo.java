package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import models.Circulo;
import models.Edge;

public class PanelGrafo extends JPanel {

	private static final long serialVersionUID = 1L;
	ArrayList<Circulo> nodes ;
	ArrayList<NodeGraphics> nodesGraphics ;
	CopyOnWriteArrayList<Rectangle> rectangulos ;
	ImageIcon flecha= new ImageIcon(getClass().getResource("/img/flecha.png"));
	boolean interset = true;
	Rectangle r;

	public PanelGrafo() {
		r = new Rectangle((int)(Math.random()*600)+70, (int)(Math.random()*600)+70, 80,80);
		nodesGraphics = new ArrayList<>();
		rectangulos = new CopyOnWriteArrayList<>();
		setBackground(Color.CYAN);

	}

	public void paintNodes(Graphics g) {
		if (nodes!=null) {
			for (int i = 0; i < nodes.size(); i++) {
				if (rectangulos.isEmpty()) {
					g.setColor(Color.BLACK);
					g.drawOval(r.x,r.y, 50,50);
					rectangulos.add(new Rectangle(r.x,r.y, 80,80));
					g.drawString(nodes.get(i).getData(),r.x+10, r.y+25);
					nodesGraphics.add(new NodeGraphics(nodes.get(i).getData(),new Rectangle(r.x, r.y, 80,80)));
				}else {
					while (intersetRec(r)) {
						r = cambiarXY(r);
					}
					rectangulos.add(r);
					g.setColor(Color.BLACK);
					g.drawOval(r.x,r.y, 50,50);
					g.drawString(nodes.get(i).getData(),r.x+10, r.y+25);
					nodesGraphics.add(new NodeGraphics(nodes.get(i).getData(),r));
				}
			}
			paintLines(g);
		}
	}

	public Rectangle cambiarXY(Rectangle rec) {
		int	X = (int)(Math.random()*600)+70;
		int	Y = (int)(Math.random()*600)+70;
		return  new Rectangle(X, Y, rec.width, rec.height);
	}

	public boolean intersetRec(Rectangle r) {
		boolean dato = false;
		for (Rectangle recInicial : rectangulos) {
			if (recInicial.intersects(r)) {
				dato=true;
				return dato;
			}
		}
		return dato;
	}

	public void paintLines(Graphics g) {
		int Ox =0;
		int Oy =0;
		int Dx=0;
		int Dy=0;
		for (Circulo node : nodes) {
			if (node.getEdges()!=null) {
				for (Edge node2 : node.getEdges()) {
					Circulo origen =node2.getOrigin();
					Circulo destino =node2.getDestination();

					for (NodeGraphics nodeG : nodesGraphics) {
						if (origen.getData().equalsIgnoreCase(nodeG.getData())) {
							Ox= nodeG.getRec().x;
							Oy= nodeG.getRec().y;
						}
						if (destino.getData().equalsIgnoreCase(nodeG.getData())) {
							Dx= nodeG.getRec().x;
							Dy= nodeG.getRec().y;
						}
					}
					if (origen.getData().equalsIgnoreCase(destino.getData())) {
						g.setColor(Color.DARK_GRAY);
						g.drawArc(Ox-20, Oy-20, 45,45, 0,270);
					}else {
						if (Oy>Dy) {
							g.setColor(Color.BLUE);
							g.drawLine(Ox+25, Oy,Dx+25, Dy+50);
						}else {
							g.setColor(Color.BLUE);
							g.drawLine(Ox+25, Oy+50,Dx+25, Dy);
						}
					}
				}
			}
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		nodesGraphics.clear();
		rectangulos.clear();
		paintNodes(g);
	}


	public void paintCirculos(ArrayList<Circulo> nodes ) {
		nodesGraphics.clear();
		rectangulos.clear();
		this.nodes=nodes;
		repaint();
	}

	public void limpiarVentana() {
		try {
			nodes.clear() ;
			nodesGraphics.clear() ;
			rectangulos.clear() ;
			repaint();
		} catch (Exception e) {
		}
	}

}
