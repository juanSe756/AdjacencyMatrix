package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import controller.ACTIONS;

public class Matriz extends JPanel  {

	private static final long serialVersionUID = 1L;
	ArrayList<JFormattedTextField> data;
	ArrayList<JComponent> dataLabel;
	JLabel labelConten;
	ArrayList<Integer> dataGrilla;
	ActionListener listener;
	public Matriz(int nodos,ActionListener listener) {
		setLayout(new BorderLayout());
		this.listener=listener;
		data = new ArrayList<>();
		dataLabel= new ArrayList<>();
		dataGrilla= new ArrayList<>();
		labelConten = new JLabel();
		labelConten.setBorder(BorderFactory.createEmptyBorder(50, 70, 80,120));
		labelConten.setLayout(new GridLayout(nodos+1, nodos+1));
		cargarCell(nodos);
		add(labelConten,BorderLayout.CENTER);
	}	

	public void cargarCell(int nodos) {
		labelConten.add(new JLabel(""));
		dataLabel.add(new JLabel(""));
		for (int i = 0; i < nodos; i++) {
			JLabel label = new JLabel((i+1)+"",SwingConstants.CENTER);
			label.setFont(new Font("Arial", 1,30));
			label.setName((i)+"");
			dataLabel.add(label);
			labelConten.add(label);
		}
		for (int i = 0; i < nodos; i++) {
			JLabel label = new JLabel((i+1)+"",SwingConstants.CENTER);
			label.setFont(new Font("Arial", 1,30));
			labelConten.add(label);
			for (int j = 0; j < nodos; j++) {
				NumberFormat format = NumberFormat.getInstance();
				NumberFormatter formatter = new NumberFormatter(format);
				formatter.setValueClass(Integer.class);
				formatter.setMinimum(0); //valor mínimo
				formatter.setMaximum(1); //valor máximo
//				formatter.setAllowsInvalid(false);
//				formatter.setCommitsOnValidEdit(true);
				JFormattedTextField field = new JFormattedTextField(formatter);
				field.setHorizontalAlignment(SwingConstants.CENTER);
				field.setFont(new Font("Arial", 1,30));
				labelConten.add(field);
				data.add(field);
			}
		}
		JButton buton = new JButton("GENERAR GRAFICA");
		buton.addActionListener(listener);
		buton.setActionCommand(ACTIONS.GUARDARGRILLA.toString());
		add(buton,BorderLayout.PAGE_END);
	}

	public ArrayList<Integer> dataGrilla() {
		dataGrilla.clear();
		for (JFormattedTextField jFormattedTextField : data) {
			if (jFormattedTextField.getValue()==null||jFormattedTextField.getValue().equals("")) {
				dataGrilla.add(0);
			}else {
				dataGrilla.add((int)jFormattedTextField.getValue());
			}
		}
		return dataGrilla;
	}

}