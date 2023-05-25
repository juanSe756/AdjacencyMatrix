package views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ACTIONS;
import models.Circulo;
import models.Edge;

public class PanelCOntenDibujo  extends JPanel{

	private static final long serialVersionUID = 1L;

	PanelDibujo dibujo; 
	menuDibujo menu;
	public PanelCOntenDibujo(MouseListener mouseListener,ActionListener actionListener) {

		setLayout(new BorderLayout());

		menu = new menuDibujo(actionListener);
		add(menu,BorderLayout.PAGE_START);
		dibujo = new PanelDibujo(mouseListener);
		add(dibujo,BorderLayout.CENTER);
		JButton matriz = new JButton("GENERAR MATRIZ");
		matriz.addActionListener(actionListener);
		matriz.setActionCommand(ACTIONS.GENERAR_MATRIZ.toString());
		add(matriz,BorderLayout.PAGE_END);
	}

	public void painCirculos(CopyOnWriteArrayList<Circulo> circulos) {
		dibujo.painCirculos(circulos);
	}

	public void paintEdges(CopyOnWriteArrayList<Edge> edges) {
		dibujo.paintEdges(edges);
	}

	public void resaltarCirculo(Circulo circulo) {
		dibujo.resaltarCirculo(circulo);
		dibujo.repaint();
	}

	public PanelDibujo getDibujo() {
		return dibujo;
	}

	public void ActivarBotones(String data) {
		menu.activar(data);
	}

	public void clear() {
		dibujo.resetear();
	}
}
