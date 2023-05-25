package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import controller.ACTIONS;

public class MatrizDistancias extends JPanel {

	private static final long serialVersionUID = 1L;
	ArrayList<JFormattedTextField> data;
	JLabel labelConten;
	ArrayList<Integer> dataGrilla;
	ActionListener listener;
	public MatrizDistancias(int nodos,ActionListener listener) {
		setLayout(new BorderLayout());
		this.listener=listener;
		data = new ArrayList<>();
		dataGrilla= new ArrayList<>();
		JPanel conten = new JPanel();
		conten.setLayout(new BorderLayout());
		conten.setBackground(Color.WHITE);
		conten.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		labelConten = new JLabel();
		labelConten.setBackground(Color.WHITE);
		labelConten.setLayout(new GridLayout(nodos+1, nodos+1));
		cargarCell(nodos);
		conten.add(labelConten,BorderLayout.CENTER);
		add(conten,BorderLayout.CENTER);
	}	

	public void cargarCell(int nodos) {
		labelConten.add(new JLabel(""));
		for (int i = 0; i < nodos; i++) {
			JLabel label = new JLabel((i+1)+"",SwingConstants.CENTER);
			label.setFont(new Font("Arial", 1,30));
			labelConten.add(label);
		}
		for (int i = 0; i < nodos; i++) {
			JLabel label = new JLabel((i+1)+"",SwingConstants.CENTER);
			label.setFont(new Font("Arial", 1,30));
			labelConten.add(label);
			for (int j = 0; j < nodos; j++) {
				NumberFormat format = NumberFormat.getNumberInstance();
				NumberFormatter formatter = new NumberFormatter(format);
				formatter.setValueClass(Integer.class);
				formatter.setMinimum(-100); //valor mínimo
				formatter.setMaximum(100); //valor máximo
				formatter.setCommitsOnValidEdit(true);
				
				JFormattedTextField field = new JFormattedTextField(formatter);
				field.setHorizontalAlignment(SwingConstants.CENTER);
				field.setFont(new Font("Arial", 1,30));
				labelConten.add(field);
				data.add(field);
			}
		}
		JButton buton = new JButton("GENERAR GRAFICA");
		buton.addActionListener(listener);
		buton.setActionCommand(ACTIONS.GUARDAR_DISTANCIAS.toString());
		add(buton,BorderLayout.PAGE_END);
	}

	public ArrayList<Integer> dataGrilla() {
		dataGrilla.clear();
		for (JFormattedTextField jFormattedTextField : data) {
			if (jFormattedTextField.getValue()==null||jFormattedTextField.getValue().equals("")) {
				dataGrilla.add(99999999);
			}else {
				dataGrilla.add((int)jFormattedTextField.getValue());
			}
//			if (jFormattedTextField.getValue().equals("")) {
//				dataGrilla.add(0);
//			}
//			if (jFormattedTextField.getValue().equals("")) {
//				dataGrilla.add(0);
//			}
//			if (jFormattedTextField.getValue().equals("")) {
//				dataGrilla.add(0);
//			}
		}
		return dataGrilla;
	}




}