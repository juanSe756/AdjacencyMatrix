package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import models.CONSTANTS;

public class PanelRepresentaciones extends JPanel {

	private static final long serialVersionUID = 1L;

	JTextArea conjuntoVertices;
	JTextArea conjuntoAristas;
	JTextArea grados;
	JTextArea listaAdyacencia;
	JTextPane matrizAdyacencia;
	JLabel imagenGrafo;
	public PanelRepresentaciones(MouseListener mouse) {
		Font font = new Font("Inter",Font.BOLD,13);
		setLayout(new GridLayout(5,1));
		setPreferredSize(new Dimension(250,300));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(20,10,15,25));
		JPanel panelConjuntos = new JPanel();
		panelConjuntos.setBackground(Color.WHITE);
		panelConjuntos.setLayout(new GridLayout(2, 1));
		panelConjuntos.setBorder(BorderFactory.createTitledBorder(CONSTANTS.conjuntos));
		panelConjuntos.setName(CONSTANTS.conjuntos);
		panelConjuntos.setFont(font);
		panelConjuntos.addMouseListener(mouse);
		conjuntoVertices = new JTextArea("");
		conjuntoVertices.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createTitledBorder("Vertices")));
		conjuntoVertices.setLineWrap(true);
		conjuntoVertices.setEditable(false);
		conjuntoVertices.setName(CONSTANTS.conjuntos);
		conjuntoVertices.setFont(font);
		conjuntoVertices.addMouseListener(mouse);
		conjuntoAristas = new JTextArea("");
		conjuntoAristas.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createTitledBorder("Aristas")));
		conjuntoAristas.setName(CONSTANTS.conjuntos);
		conjuntoAristas.setFont(font);
		conjuntoAristas.addMouseListener(mouse);
		conjuntoAristas.setLineWrap(true);
		conjuntoAristas.setEditable(false);

		grados = new JTextArea("");
		grados.setName(CONSTANTS.grados);
		grados.setFont(font);
		grados.addMouseListener(mouse);
		grados.setLineWrap(true);
		grados.setEditable(false);
		grados.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createTitledBorder(CONSTANTS.grados)));

		imagenGrafo = new JLabel("");
		imagenGrafo.setSize(150,150);
		imagenGrafo.setName(CONSTANTS.graficaGrafo);
		imagenGrafo.setFont(font);
		imagenGrafo.addMouseListener(mouse);
		imagenGrafo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createTitledBorder(CONSTANTS.graficaGrafo)));

		listaAdyacencia = new JTextArea("");
		listaAdyacencia.setName(CONSTANTS.listaAdyacencia);
		listaAdyacencia.setFont(font);
		listaAdyacencia.addMouseListener(mouse);
		listaAdyacencia.setLineWrap(true);
		listaAdyacencia.setEditable(false);
		listaAdyacencia.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createTitledBorder(CONSTANTS.listaAdyacencia)));

		matrizAdyacencia = new JTextPane();
		matrizAdyacencia.setEditable(false);
		matrizAdyacencia.setName(CONSTANTS.matrizAdyacencia);
		matrizAdyacencia.setFont(font);
		matrizAdyacencia.addMouseListener(mouse);
		matrizAdyacencia.setEditorKit(new MyEditorKit());
		StyledDocument doc = matrizAdyacencia.getStyledDocument(); 
		SimpleAttributeSet center = new SimpleAttributeSet(); 
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER); 
		doc.setParagraphAttributes(0, doc.getLength(), center, false); 
		matrizAdyacencia.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createTitledBorder(CONSTANTS.matrizAdyacencia)));

		panelConjuntos.add(conjuntoVertices);
		panelConjuntos.add(conjuntoAristas);

		add(imagenGrafo);
		add(panelConjuntos);
		add(grados);
		add(listaAdyacencia);
		add(matrizAdyacencia);
	}

	public void paintData(String conjuntoVerticesIn,String conjuntoAristasIn,String secuenciaGrados,String listaAdyacenciaIn,String matrizAdyacenciaIn,Image image,JComponent panelConten){
		conjuntoVertices.setText(conjuntoVerticesIn);
		conjuntoAristas.setText(conjuntoAristasIn);
		grados.setText(secuenciaGrados);
		listaAdyacencia.setText(listaAdyacenciaIn);
		matrizAdyacencia.setText(matrizAdyacenciaIn);
		revalidate();
		if (image!=null) {
			if (panelConten!=null) {
				paintImage(image,panelConten);
			}else {
				paintImage(image,imagenGrafo);
			}
		}else {
			imagenGrafo.setIcon(null);
		}
	}
	public void paintImage(Image image,JComponent panelConten) {
		imagenGrafo.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(panelConten.getWidth(), panelConten.getHeight(), Image.SCALE_SMOOTH)));
		revalidate();
	}
}
