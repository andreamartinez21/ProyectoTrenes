package ventanas;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import clases.Viaje;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCompra extends JFrame{

    private JPanel contentPane;

    private JPanel panel;
    private JPanel panelArriba;
    private JPanel panelMedio;
    private JPanel panelMedioIzquierda;
    private JPanel panelMedioDerecha;
    private JPanel panelAbajo;
    
    private JCalendar calendarioIda;
    private JCalendar calendarioVuelta;
    
	public VentanaCompra() throws IOException {
    	
        setBackground(new Color(153, 0, 102));

        setTitle("Compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(530, 380));
        setVisible(true);
        pack();

        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 0, 102));
        setContentPane(contentPane);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(153, 0, 102));
        panelArriba = new JPanel(new GridLayout(1, 2));
        panelArriba.setBackground(new Color(153, 0, 102));
        panelMedio = new JPanel(new BorderLayout());
        panelMedio.setBackground(new Color(153, 0, 102));
        panelMedioIzquierda = new JPanel(new GridLayout(2, 1));
        panelMedioIzquierda.setBackground(new Color(153, 0, 102));
        panelMedioDerecha = new JPanel(new GridLayout(2, 2));
        panelMedioDerecha.setBackground(new Color(153, 0, 102));
        panelAbajo = new JPanel(new GridLayout(2, 2));
        panelAbajo.setBackground(new Color(153, 0, 102));

        contentPane.add(panel);

        // radio button ida y vuelta

        JPanel panelRadioIdaVuelta = new JPanel();
        panelRadioIdaVuelta.setBackground(new Color(153, 0, 102));
        JRadioButton radioIdaVuelta = new JRadioButton("Ida y vuelta", true);
        radioIdaVuelta.setBackground(Color.WHITE);
        panelRadioIdaVuelta.add(radioIdaVuelta);

        panelMedioIzquierda.add(panelRadioIdaVuelta);

        // radio button ida

        JPanel panelRadioIda = new JPanel();
        panelRadioIda.setBackground(new Color(153, 0, 102));
        JRadioButton radioIda = new JRadioButton("Ida", false);
        radioIda.setBackground(Color.WHITE);
        panelRadioIda.add(radioIda);

        panelMedioIzquierda.add(panelRadioIda);

        if(radioIdaVuelta.isSelected() == true){
            radioIda.setSelected(false);
        } else if(radioIda.isSelected() == true){
            radioIdaVuelta.setSelected(false);
        }
        
        // labels y spinners
        
        JPanel panelLabelAdultos = new JPanel();
        panelLabelAdultos.setBackground(new Color(153, 0, 102));
        JLabel labelAdultos = new JLabel("Adulto");
        labelAdultos.setForeground(Color.WHITE);
        panelLabelAdultos.add(labelAdultos);
        
        JPanel panelSpinnerAdultos = new JPanel();
        panelSpinnerAdultos.setBackground(new Color(153, 0, 102));
        JSpinner spinnerAdultos = new JSpinner();
        spinnerAdultos.setModel(new SpinnerNumberModel(1, 1, 6, 1));
        panelSpinnerAdultos.add(spinnerAdultos);

        JPanel panelLabelNinyos = new JPanel(); 
        panelLabelNinyos.setBackground(new Color(153, 0, 102));
        JLabel labelNinyos = new JLabel("Niño (descuento 20%)");
        labelNinyos.setForeground(Color.WHITE);
        panelLabelNinyos.add(labelNinyos);
        
        JPanel panelSpinnerNinyos = new JPanel();
        panelSpinnerNinyos.setBackground(new Color(153, 0, 102));
        JSpinner spinnerNinyos = new JSpinner();
        spinnerNinyos.setModel(new SpinnerNumberModel(0, 0, 6, 1));
        panelSpinnerNinyos.add(spinnerNinyos);

        panelMedioDerecha.add(panelLabelAdultos);
        panelMedioDerecha.add(panelSpinnerAdultos);
        panelMedioDerecha.add(panelLabelNinyos);
        panelMedioDerecha.add(panelSpinnerNinyos);

        // combo origen

        HashSet<String> listaOrigen = new HashSet<>();
 	    listaOrigen = Metodos.obtenerMapaOrigenDestino().get("Origen");
        JPanel panelComboOrigen = new JPanel();
        panelComboOrigen.setBackground(new Color(153, 0, 102));
        JComboBox<String> comboOrigen = new JComboBox<String>();
        // poner origen como defecto
        for (String ciudad : listaOrigen) { // lo que recorres y el tipo de cosa que recorres a cada vuelta
			comboOrigen.addItem(ciudad);
		}
        comboOrigen.setBackground(Color.WHITE);
        panelComboOrigen.add(comboOrigen);

        panelArriba.add(panelComboOrigen);

        // combo destino

        HashSet<String> listaDestino = new HashSet<>();
        listaDestino = Metodos.obtenerMapaOrigenDestino().get("Destino");
        JPanel panelComboDestino = new JPanel();
        panelComboDestino.setBackground(new Color(153, 0, 102));
        JComboBox<String> comboDestino = new JComboBox<String>();
        
        for (String ciudad : listaDestino) {
			comboDestino.addItem(ciudad);
		}
        comboDestino.setBackground(Color.WHITE);
        panelComboDestino.add(comboDestino);

        panelArriba.add(panelComboDestino);

        radioIdaVuelta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboDestino.setVisible(true);
                radioIda.setSelected(false);
                calendarioVuelta.setVisible(true);
			}
		});
        radioIda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboDestino.setVisible(false);
                radioIdaVuelta.setSelected(false);
                calendarioVuelta.setVisible(false);
			}
		});
        
        // calendario ida
        
        JPanel panelCalendarioIda = new JPanel();
        panelCalendarioIda.setBackground(new Color(153, 0, 102));
        calendarioIda = new JCalendar();
        // calendarioIda.setTodayButtonVisible(true);
        calendarioIda.setWeekOfYearVisible(false);
        panelCalendarioIda.add(calendarioIda);
        
        panelAbajo.add(panelCalendarioIda);
        
        // calendario ida
        
        JPanel panelCalendarioVuelta = new JPanel();
        panelCalendarioVuelta.setBackground(new Color(153, 0, 102));
        calendarioVuelta = new JCalendar();
        // calendarioVuelta.setTodayButtonVisible(true);
        calendarioVuelta.setWeekOfYearVisible(false);
        panelCalendarioVuelta.add(calendarioVuelta);
        
        panelAbajo.add(panelCalendarioVuelta);

        // botón volver

        JPanel panelBotonVolver = new JPanel();
        panelBotonVolver.setBackground(new Color(153, 0, 102));
        JButton botonVolver = new JButton("Volver");
        botonVolver.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
        botonVolver.setBackground(Color.GRAY);
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setPreferredSize(new Dimension(150, 30));
        panelBotonVolver.add(botonVolver);

        botonVolver.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if(VentanaInicio.var == 1){
                        new VentanaInicio();
                        dispose();
                    } else if(VentanaInicio.var == 2){
                        new VentanaMenuPrincipal();
                        dispose();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        // botón siguiente

        JPanel panelBotonSiguiente = new JPanel();
        panelBotonSiguiente.setBackground(new Color(153, 0, 102));
        JButton botonSiguiente = new JButton("Siguiente");
        botonSiguiente.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
        botonSiguiente.setBackground(Color.GRAY);
        botonSiguiente.setForeground(Color.WHITE);
        botonSiguiente.setPreferredSize(new Dimension(150, 30));
        panelBotonSiguiente.add(botonSiguiente);

        botonSiguiente.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new VentanaConfirmacionCompra();
                    dispose();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        panelAbajo.add(panelBotonVolver);
        panelAbajo.add(panelBotonSiguiente);

        //

        panel.add(panelArriba, BorderLayout.NORTH);
        panel.add(panelMedio, BorderLayout.CENTER);
        panel.add(panelAbajo, BorderLayout.SOUTH);
        panelMedio.add(panelMedioIzquierda, BorderLayout.WEST);
        panelMedio.add(panelMedioDerecha, BorderLayout.EAST);

        contentPane.setVisible(true);
        panel.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
		new VentanaCompra();
	}
}