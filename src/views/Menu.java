package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;			
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.w3c.dom.css.RGBColor;

import controller.ACTIONS;

public class Menu  extends JPanel{

	// private static final long serialVersionUID = 1L;

	ArrayList<JButton> botones = new ArrayList<>();
	JButton nuevoGrafo;
	ClearButton tablaAdj;
	ClearButton newGraph;


	public Menu(ActionListener listener) {
		Font font = new Font("Aharoni",Font.BOLD,13);
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		ImageIcon img = new ImageIcon(new ImageIcon(getClass().getResource("/img/grafopng.png")).getImage().getScaledInstance(250,150, Image.SCALE_SMOOTH));
		JLabel logo = new JLabel("",SwingConstants.CENTER);
		logo.setIcon(img);
		logo.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		add(logo,BorderLayout.PAGE_START);
		Color col = new Color(64, 207, 255);
		JPanel menu = new JPanel();
		menu.setBackground(Color.BLACK);
		menu.setLayout(new GridLayout(2,1));
		menu.setPreferredSize(new Dimension(250,180));

		// nuevoGrafo = new JButton("Nuevo Grafo");
		// nuevoGrafo.addActionListener(listener);
		// nuevoGrafo.setActionCommand(ACTIONS.NUEVO_GRAFO.toString());
		// nuevoGrafo.setForeground(Color.BLACK);
		// nuevoGrafo.setBackground(Color.LIGHT_GRAY);
		// nuevoGrafo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// menu.add(nuevoGrafo);
		newGraph = new ClearButton("Nuevo grafo", font,  Color.WHITE, WIDTH, 35, col);
		newGraph.addActionListener(listener);
		newGraph.setActionCommand(ACTIONS.NUEVO_GRAFO.toString());
		menu.add(newGraph);
		// tablaAdyacencia = new JButton("Matriz Adyacencia");
		// tablaAdyacencia.addActionListener(listener);
		// tablaAdyacencia.setActionCommand(ACTIONS.ADYACENCIA.toString());
		// tablaAdyacencia.setName(ACTIONS.ADYACENCIA.toString());
		// tablaAdyacencia.setBackground(Color.LIGHT_GRAY);
		// tablaAdyacencia.setForeground(Color.BLACK);
		// tablaAdyacencia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// botones.add(tablaAdyacencia);
		// menu.add(tablaAdyacencia);

		tablaAdj = new ClearButton("Matriz Adyacencia", font, Color.WHITE, WIDTH, 35, col);
		tablaAdj.addActionListener(listener);
		tablaAdj.setActionCommand(ACTIONS.ADYACENCIA.toString());
		botones.add(tablaAdj);
		menu.add(tablaAdj);

		add(menu);
	}


	public void activarBotones(Boolean data) {
		for (JButton jButton : botones) {
			jButton.setEnabled(data);
		}
	}
}
