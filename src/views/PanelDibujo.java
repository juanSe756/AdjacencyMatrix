package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;
import models.Circulo;
import models.Edge;

public class PanelDibujo extends JPanel{

	private static final long serialVersionUID = 1L;
	CopyOnWriteArrayList<Circulo> circulos;
	CopyOnWriteArrayList<Circulo> circulosP;
	CopyOnWriteArrayList<Edge> edges;
	Circulo circulo;
	public PanelDibujo(MouseListener listener) {
		addMouseListener(listener);
		setBackground(Color.WHITE);
		circulosP = new CopyOnWriteArrayList<>();
		edges= new CopyOnWriteArrayList<>();
	}

	public void painCirculos(CopyOnWriteArrayList<Circulo> circulos) {
		this.circulos=circulos;
		repaint();
	}

	public void paintEdges(CopyOnWriteArrayList<Edge> edges) {
		this.edges=edges;
		repaint();
	}
	public void resaltarCirculo(Circulo circuloIn) {
		this.circulo=circuloIn;
		repaint();
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (edges!=null) {
			paintLines(g);
		}
		if (circulos!=null) {
			for (Circulo circulo : circulos) {
				g.setColor(circulo.getColor());
				g.drawOval(circulo.getCordenadasX(),circulo.getCordenadasY(), circulo.getDimension(),circulo.getDimension());
				g.setFont(new Font("Arial",1,20));
				g.drawString(circulo.getData()+"",circulo.getCordenadasX()+20, circulo.getCordenadasY()+25);
				circulosP.add(circulo);			}
		}
		if (circulo!=null) {
			for (Circulo circuloP : circulosP) {
				if (this.circulo.getRectangulo().contains(circuloP.getRectangulo())) {
					g.setColor(circulo.getColor());
					g.fillOval(circulo.getCordenadasX(),circulo.getCordenadasY(), circulo.getDimension(),circulo.getDimension());
					g.setColor(Color.WHITE);
					g.drawString(circulo.getData()+"",circulo.getCordenadasX()+20, circulo.getCordenadasY()+25);
					g.setColor(Color.BLACK);
				}
			}
		}
	}



	public void paintLines(Graphics g) {
		int Ox =0;
		int Oy =0;
		int Dx=0;
		int Dy=0;
		for (Edge edge : edges) {
			for (Circulo circulo : circulos) {
				if (circulo.getRectangulo().equals(edge.getOrigin().getRectangulo())) {
					Ox=circulo.getCordenadasX();
					Oy=circulo.getCordenadasY();
				}
				if (circulo.getRectangulo().equals(edge.getDestination().getRectangulo())) {
					Dx=circulo.getCordenadasX();
					Dy=circulo.getCordenadasY();
				}
			}
			if (!edge.getOrigin().equals(edge.getDestination())) {
				if (Oy>Dy) {
					g.drawLine(Ox+25, Oy,Dx+25, Dy+50);
					try {
						g.drawString(edge.getDistanciaf()+"", Ox+30, Oy-50);

					} catch (Exception e) {
						g.drawString(edge.getDistanciaf()+"", Ox+30, Oy-50);
					}
				}else {
					if (Oy<Dy) {
						g.drawLine(Ox+25, Oy,Dx+25, Dy+50);
						try {
							g.drawString(edge.getDistanciaf()+"", Ox-30, Oy);

						} catch (Exception e) {
							g.drawString(edge.getDistanciaf()+"", Ox-30, Oy);
						}
					}else {
						g.drawLine(Ox+25, Oy+50,Dx+25, Dy);
						try {
							g.drawString(edge.getDistanciaf()+"", Ox+50, Oy+80);
						} catch (Exception e) {
							g.drawString(edge.getDistanciaf()+"", Ox+50, Oy+80);
						}
					}
				}
			}
			if (edge.getOrigin().equals(edge.getDestination())) {
				g.drawArc(Ox-20, Oy-20, 45,45, 0,270);
				g.drawString(edge.getDistanciaf()+"", Ox-5, Oy-20);
			}
		}
	}
	
	public void resetear() {
		try {
			circulos.clear();;
			circulosP.clear();;
			edges.clear();;
			circulo=null;
		} catch (Exception e) {
		}
	}

}