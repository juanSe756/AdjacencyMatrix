package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ACTIONS;

public class menuDibujo extends JPanel{

	private static final long serialVersionUID = 1L;
	ArrayList<JButton>botones;

	public menuDibujo(ActionListener listener) {
		botones = new ArrayList<>();

		setBackground(Color.GRAY);
		setLayout(new GridLayout(1,3));

		JButton butonAddNodo = new JButton("Add Nodo");
		butonAddNodo.addActionListener(listener);
		butonAddNodo.setActionCommand(ACTIONS.ADD_CIRCULO.toString());
		butonAddNodo.setBackground(Color.LIGHT_GRAY);
		butonAddNodo.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		butonAddNodo.setName(ACTIONS.ADD_CIRCULO.toString());
		botones.add(butonAddNodo);
		
		JButton conectarNodos = new JButton("Conectar");
		conectarNodos.addActionListener(listener);
		conectarNodos.setActionCommand(ACTIONS.CONECTAR.toString());
		conectarNodos.setBackground(Color.LIGHT_GRAY);
		conectarNodos.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		conectarNodos.setName(ACTIONS.CONECTAR.toString());
		botones.add(conectarNodos);

		JButton remover = new JButton("Remover");
		remover.addActionListener(listener);
		remover.setActionCommand(ACTIONS.REMOVER.toString());
		remover.setName(ACTIONS.REMOVER.toString());
		remover.setBackground(Color.LIGHT_GRAY);
		remover.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		botones.add(remover);

		add(butonAddNodo);
		add(conectarNodos);
		add(remover);
	}

	public void activar(String data) {
		for (JButton jButton : botones) {
			if (jButton.getName().equals(data)) {
				jButton.setBackground(Color.DARK_GRAY);
				jButton.setForeground(Color.WHITE);
			}else {
				jButton.setBackground(Color.GRAY);
				jButton.setForeground(Color.WHITE);
			}
		}
	}
}
