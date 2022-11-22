package ventanas;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCompra extends JFrame{

    private JPanel contentPane;

    private JPanel panel;
    private JPanel panelArriba;
    private JPanel panelMedio;
    private JPanel panelAbajo;
    
    private JCalendar calendarioIda;
    private JCalendar calendarioVuelta;
    
    private int tipoBilleteInt = 1;
    public static String tipoBillete = "Ida y vuelta";
    public static String clase = "Segunda clase";
    public static int claseInt = 2;
    
    public static JComboBox<String> comboOrigen;
    public static JComboBox<String> comboDestino;
    public static JSpinner spinnerNumBilletes;
    
	public VentanaCompra() throws IOException {
    	
        setBackground(new Color(153, 0, 102));

        setTitle("Compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(530, 575));
        setVisible(true);
        pack();

        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 0, 102));
        setContentPane(contentPane);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(153, 0, 102));
        panelArriba = new JPanel(new GridLayout(2, 2));
        panelArriba.setBackground(new Color(153, 0, 102));
        panelMedio = new JPanel(new GridLayout(2, 1));
        panelMedio.setBackground(new Color(153, 0, 102));
        panelAbajo = new JPanel(new GridLayout(2, 2));
        panelAbajo.setBackground(new Color(153, 0, 102));
        
        JPanel panelCalendarioVuelta = new JPanel();

        contentPane.add(panel);
        
        // combo origen

        HashSet<String> listaOrigen = new HashSet<>();
 	    listaOrigen = Metodos.obtenerMapaOrigenDestino().get("Origen");
        JPanel panelComboOrigen = new JPanel();
        panelComboOrigen.setBackground(new Color(153, 0, 102));
        comboOrigen = new JComboBox<String>();
        
        for (String ciudad : listaOrigen) { // lo que recorres y el tipo de cosa que recorres a cada vuelta
			comboOrigen.addItem(ciudad);
		}
        comboOrigen.setBackground(Color.WHITE);
        
        Border bordejpanel5 = new TitledBorder(new MatteBorder(null), "DESDE:");
        panelComboOrigen.setBorder(bordejpanel5);
        
        panelComboOrigen.add(comboOrigen);

        panelArriba.add(panelComboOrigen);

        // combo destino

        HashSet<String> listaDestino = new HashSet<>();
        listaDestino = Metodos.obtenerMapaOrigenDestino().get("Destino");
        JPanel panelComboDestino = new JPanel();
        panelComboDestino.setBackground(new Color(153, 0, 102));
        comboDestino = new JComboBox<String>();
        
        for (String ciudad : listaDestino) {
			comboDestino.addItem(ciudad);
		}
        comboDestino.setBackground(Color.WHITE);
        
        Border bordejpanel6 = new TitledBorder(new MatteBorder(null), "A:");
        panelComboDestino.setBorder(bordejpanel6);
        
        panelComboDestino.add(comboDestino);

        panelArriba.add(panelComboDestino);
        
        // radio button ida y vuelta

        JPanel panelRadioIdaVueltaIda = new JPanel(new GridLayout(2, 1));
        panelRadioIdaVueltaIda.setBackground(new Color(153, 0, 102));
        JRadioButton radioIdaVuelta = new JRadioButton("Ida y vuelta", true);
        radioIdaVuelta.setBackground(new Color(153, 0, 102));
        radioIdaVuelta.setForeground(Color.WHITE);        

        // radio button ida

        JRadioButton radioIda = new JRadioButton("Ida", false);
        radioIda.setBackground(new Color(153, 0, 102));
        radioIda.setForeground(Color.WHITE);
        
        panelRadioIdaVueltaIda.add(radioIdaVuelta);
        panelRadioIdaVueltaIda.add(radioIda);
        
        Border bordejpanel = new TitledBorder(new EtchedBorder(), "Tipo de billete");
        panelRadioIdaVueltaIda.setBorder(bordejpanel); 

        radioIdaVuelta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioIda.setSelected(false);
                panelCalendarioVuelta.setVisible(true);
                tipoBilleteInt = 1; // ida y vuelta
                tipoBillete = "Ida y vuelta";
			}
		});
        
        radioIda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioIdaVuelta.setSelected(false);
                panelCalendarioVuelta.setVisible(false);
                tipoBilleteInt = 0; // ida
                tipoBillete = "Ida";
			}
		});
        
        // label y spinner num billetes
        
        JPanel panelLabelNumBilletes = new JPanel();
        panelLabelNumBilletes.setBackground(new Color(153, 0, 102));
        JLabel labelNumBilletes = new JLabel("Número de billetes:");
        labelNumBilletes.setForeground(Color.WHITE);
        panelLabelNumBilletes.add(labelNumBilletes);
        
        JPanel panelSpinnerNumBilletes = new JPanel();
        panelSpinnerNumBilletes.setBackground(new Color(153, 0, 102));
        spinnerNumBilletes = new JSpinner();
        spinnerNumBilletes.setModel(new SpinnerNumberModel(1, 1, 6, 1));
        panelSpinnerNumBilletes.add(spinnerNumBilletes);
        
        JPanel panelNumBilletes = new JPanel(new GridLayout(1, 2));
        panelNumBilletes.setBackground(new Color(153, 0, 102));
        
        panelNumBilletes.add(panelLabelNumBilletes);
        panelNumBilletes.add(panelSpinnerNumBilletes);
        
        // radio button primera y segunda clase
        
        JPanel panelRadioClase = new JPanel(new GridLayout(1, 2));
        panelRadioClase.setBackground(new Color(153, 0, 102));
        
        JRadioButton radioSegundaClase = new JRadioButton("Segunda clase (+0.00€)", true);
        radioSegundaClase.setBackground(new Color(153, 0, 102));
        radioSegundaClase.setForeground(Color.WHITE);
        
        JRadioButton radioPrimeraClase = new JRadioButton("Primera clase (+12.00€)", false);
        radioPrimeraClase.setBackground(new Color(153, 0, 102));
        radioPrimeraClase.setForeground(Color.WHITE);
        
        Border bordejpanel2 = new TitledBorder(new EtchedBorder(), "Clase");
        panelRadioClase.setBorder(bordejpanel2); 
        
        panelRadioClase.add(radioSegundaClase);
        panelRadioClase.add(radioPrimeraClase);
        
        // JCheckBox extras
        
        JPanel panelCheckExtras = new JPanel(new GridLayout(2, 1));
        panelCheckExtras.setBackground(new Color(153, 0, 102));
        
        JPanel panelCheckExtras2 = new JPanel(new GridLayout(2, 1));
        panelCheckExtras2.setBackground(new Color(153, 0, 102));
        
        JCheckBox checkComida = new JCheckBox("Comida (+15.00€)");
        checkComida.setBackground(new Color(153, 0, 102));
        checkComida.setForeground(Color.WHITE);
        
        JCheckBox checkAsientoIndividual = new JCheckBox("Asiento individual (+9.00€)");
        checkAsientoIndividual.setBackground(new Color(153, 0, 102));
        checkAsientoIndividual.setForeground(Color.WHITE);
        
        JCheckBox checkSeguro = new JCheckBox("Seguro viaje (+3.00€)");
        checkSeguro.setBackground(new Color(153, 0, 102));
        checkSeguro.setForeground(Color.WHITE);
        
        JCheckBox checkMesa = new JCheckBox("Mesa (+2.00€)");
        checkMesa.setBackground(new Color(153, 0, 102));
        checkMesa.setForeground(Color.WHITE);
        
        Border bordejpanel3 = new TitledBorder(new EtchedBorder(), "Extras");
        panelCheckExtras.setBorder(bordejpanel3);
        
        Border bordejpanel4 = new TitledBorder(new EtchedBorder(), "Extras");
        panelCheckExtras2.setBorder(bordejpanel4);
        panelCheckExtras2.setVisible(false);
        
        panelCheckExtras.add(checkSeguro);
    	panelCheckExtras.add(checkMesa);
    	panelCheckExtras2.add(checkComida);
    	panelCheckExtras2.add(checkAsientoIndividual);
        
        JPanel panelPrueba = new JPanel(new GridLayout(1, 2));
        panelPrueba.setBackground(new Color(153, 0, 102));
        
        panelPrueba.add(panelCheckExtras);
        panelPrueba.add(panelCheckExtras2);
        
        radioSegundaClase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioPrimeraClase.setSelected(false);
				panelCheckExtras.setVisible(true);
				panelCheckExtras2.setVisible(false);
				clase = "Segunda clase";
				claseInt = 2;
			}
		});
        
        radioPrimeraClase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioSegundaClase.setSelected(false);
				panelCheckExtras.setVisible(false);
				panelCheckExtras2.setVisible(true);
				clase = "Primera clase";
				claseInt = 1;
			}
		});
        
        panelArriba.add(panelRadioIdaVueltaIda);
        panelArriba.add(panelNumBilletes);
        panelMedio.add(panelRadioClase);
        panelMedio.add(panelPrueba);
        
        // calendario ida
        
        JPanel panelCalendarioIda = new JPanel();
        panelCalendarioIda.setBackground(new Color(153, 0, 102));
        calendarioIda = new JCalendar();
        // calendarioIda.setTodayButtonVisible(true);
        calendarioIda.setWeekOfYearVisible(false);
        panelCalendarioIda.add(calendarioIda);
        
        Border bordejpanel7 = new TitledBorder(new MatteBorder(null), "Ida:");
        panelCalendarioIda.setBorder(bordejpanel7);
        
        panelAbajo.add(panelCalendarioIda);
        
        // calendario vuelta
        
        panelCalendarioVuelta.setBackground(new Color(153, 0, 102));
        calendarioVuelta = new JCalendar();
        // calendarioVuelta.setTodayButtonVisible(true);
        calendarioVuelta.setWeekOfYearVisible(false);
        panelCalendarioVuelta.add(calendarioVuelta);
        
        Border bordejpanel8 = new TitledBorder(new MatteBorder(null), "Vuelta:");
        panelCalendarioVuelta.setBorder(bordejpanel8);
        
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
                	if(Metodos.existeViaje(comboOrigen.getSelectedItem().toString(), comboDestino.getSelectedItem().toString(), (int)spinnerNumBilletes.getValue(), tipoBilleteInt)) {
                		new VentanaConfirmacionCompra();
                		dispose();
                	}
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        panelAbajo.add(panelBotonVolver);
        panelAbajo.add(panelBotonSiguiente);

        panel.add(panelArriba, BorderLayout.NORTH);
        panel.add(panelMedio, BorderLayout.CENTER);
        panel.add(panelAbajo, BorderLayout.SOUTH);

        contentPane.setVisible(true);
        panel.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
		new VentanaCompra();
	}
}