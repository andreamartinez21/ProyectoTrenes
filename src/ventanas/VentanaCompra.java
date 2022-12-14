package ventanas;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;

import log.Log;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCompra extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JPanel panel;
	private JPanel panelArriba;
	private JPanel panelMedio;
	private JPanel panelAbajo;

	private JCalendar calendarioIda;
	private JCalendar calendarioVuelta;
	public static JTextField textFechaIda;
	public static JTextField textFechaVuelta;

	private int origenSeleccionado = 0;
	public static int tipoBilleteInt = 1;
	public static String tipoBillete = "Ida y vuelta";
	public static String clase = "Segunda clase";
	public static int claseInt = 2;

	public static String extra1 = "";
	public static String extra2 = "";
	
	public static int extraComida = 0;
	public static int extraAsientoIndividual = 0;
	public static int extraSeguroViaje = 0;
	public static int extraMesa = 0;
	
	private int transbordo = 0;
	
	public static JComboBox<String> comboOrigen;
	public static JComboBox<String> comboDestino;
	public static JSpinner spinnerNumBilletes;
	
	public static String origen;
	
	JPanel panelCalendarioVuelta;
	JPanel panelBotonFechaVuelta;
	JPanel panelCheckTransbordo;
	JCheckBox checkTransbordo;

	public VentanaCompra() throws IOException {

		setBackground(new Color(153, 0, 102));

		setTitle("Compra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(505, 655));
		setVisible(true);
		pack();

		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 0, 102));
		setContentPane(contentPane);

		panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(153, 0, 102));
		panelArriba = new JPanel(new GridLayout(4, 1));
		panelArriba.setBackground(new Color(153, 0, 102));
		panelMedio = new JPanel(new GridLayout(1, 2));
		panelMedio.setBackground(new Color(153, 0, 102));
		panelAbajo = new JPanel(new GridLayout(2, 2));
		panelAbajo.setBackground(new Color(153, 0, 102));

		contentPane.add(panel);

		// combo origen

		Set<String> listaOrigen = new HashSet<String>();
		listaOrigen = Metodos.obtenerMapaOrigenDestino().get("Origen");
		JPanel panelComboOrigen = new JPanel();
		panelComboOrigen.setBackground(new Color(153, 0, 102));
		comboOrigen = new JComboBox<String>();
		comboOrigen.setBackground(Color.WHITE);

		for (String ciudad : listaOrigen) { // lo que recorres y el tipo de cosa que recorres a cada vuelta
			comboOrigen.addItem(ciudad);
		}

		Border bordejpanel5 = new TitledBorder(new MatteBorder(null), "DESDE:");
		panelComboOrigen.setBorder(bordejpanel5);

		panelComboOrigen.add(comboOrigen);
		
		comboOrigen.setSelectedIndex(-1);
		comboOrigen.setRenderer(new ComboBoxRenderer("Origen"));
		
		JPanel panelCombos = new JPanel(new GridLayout(1, 2));
		panelCombos.setBackground(new Color(153, 0, 102));

		// combo destino
		
		JPanel panelComboDestino = new JPanel();
		panelComboDestino.setBackground(new Color(153, 0, 102));
		comboDestino = new JComboBox<String>();
		comboDestino.setBackground(Color.WHITE);
		String vacio = "                ";
		comboDestino.addItem(vacio);
		
		comboOrigen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				origenSeleccionado = 1;
				if (transbordo == 0) {
					Set<String> listaDestino = new HashSet<String>();
					origen = comboOrigen.getSelectedItem().toString();
					listaDestino = Metodos.obtenerMapaOrigenDestino().get("Destino");
					comboDestino.removeAllItems();

					for (String ciudad : listaDestino) {
						comboDestino.addItem(ciudad);
					}
				}

			}
		});
		
		Border bordejpanel6 = new TitledBorder(new MatteBorder(null), "A:");
		panelComboDestino.setBorder(bordejpanel6);

		comboDestino.setSelectedIndex(-1);
		comboDestino.setRenderer(new ComboBoxRenderer("Destino"));
		
		panelComboDestino.add(comboDestino);
		
		panelCombos.add(panelComboOrigen);
		panelCombos.add(panelComboDestino);
		
		panelArriba.add(panelCombos);
		
		// button groups
		
		ButtonGroup buttonGroup1 = new ButtonGroup();
		ButtonGroup buttonGroup2 = new ButtonGroup();

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
		
		buttonGroup1.add(radioIdaVuelta);
		buttonGroup1.add(radioIda);

		panelRadioIdaVueltaIda.add(radioIdaVuelta);
		panelRadioIdaVueltaIda.add(radioIda);

		Border bordejpanel = new TitledBorder(new EtchedBorder(), "Tipo de billete");
		panelRadioIdaVueltaIda.setBorder(bordejpanel);

		radioIdaVuelta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioIda.setSelected(false);
				panelCalendarioVuelta.setVisible(true);
				panelBotonFechaVuelta.setVisible(true);
				panelCheckTransbordo.setVisible(false);
				tipoBilleteInt = 1; // ida y vuelta
				tipoBillete = "Ida y vuelta";
				checkTransbordo.setSelected(false);
				transbordo = 0;
				
				if (origenSeleccionado == 1 && comboOrigen.getSelectedItem() != null) {
					Set<String> listaDestino = new HashSet<String>();
					origen = comboOrigen.getSelectedItem().toString();
					listaDestino = Metodos.obtenerMapaOrigenDestino().get("Destino");
					comboDestino.removeAllItems();

					for (String ciudad : listaDestino) {
						comboDestino.addItem(ciudad);
					}
				}
			}
		});

		radioIda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioIdaVuelta.setSelected(false);
				panelCalendarioVuelta.setVisible(false);
				panelBotonFechaVuelta.setVisible(false);
				panelCheckTransbordo.setVisible(true);
				tipoBilleteInt = 0; // ida
				tipoBillete = "Ida";
				
				if (origenSeleccionado == 1 && comboOrigen.getSelectedItem() != null) {
					Set<String> listaDestino = new HashSet<String>();
					origen = comboOrigen.getSelectedItem().toString();
					listaDestino = Metodos.obtenerMapaOrigenDestino().get("Destino");
					comboDestino.removeAllItems();

					for (String ciudad : listaDestino) {
						comboDestino.addItem(ciudad);
					}
				}
			}
		});

		// label y spinner num billetes

		JPanel panelLabelNumBilletes = new JPanel();
		panelLabelNumBilletes.setBackground(new Color(153, 0, 102));
		JLabel labelNumBilletes = new JLabel("N?mero de billetes:");
		labelNumBilletes.setForeground(Color.WHITE);
		panelLabelNumBilletes.add(labelNumBilletes);

		JPanel panelSpinnerNumBilletes = new JPanel();
		panelSpinnerNumBilletes.setBackground(new Color(153, 0, 102));
		spinnerNumBilletes = new JSpinner();
		spinnerNumBilletes.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		panelSpinnerNumBilletes.add(spinnerNumBilletes);

		JPanel panelNumCheck = new JPanel(new GridLayout(2, 1));
		panelNumCheck.setBackground(new Color(153, 0, 102));
		
		JPanel panelNumBilletes = new JPanel();
		panelNumBilletes.setBackground(new Color(153, 0, 102));
		panelNumBilletes.add(panelLabelNumBilletes);
		panelNumBilletes.add(panelSpinnerNumBilletes);

		panelNumCheck.add(panelNumBilletes);
		
		// checkBox transbordo
		
		panelCheckTransbordo = new JPanel();
		panelCheckTransbordo.setVisible(false);
		panelCheckTransbordo.setBackground(new Color(153, 0, 102));
		checkTransbordo = new JCheckBox("Transbordo ejemplo");
		checkTransbordo.setBackground(new Color(153, 0, 102));
		checkTransbordo.setForeground(Color.WHITE);
		panelCheckTransbordo.add(checkTransbordo);
		
		checkTransbordo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkTransbordo.isSelected()) {
					transbordo = 1;
					
					Set<String> listaDestinoTransbordo = new HashSet<String>();
					listaDestinoTransbordo = Metodos.destinosTransbordo();
					comboDestino.removeAllItems();

					for (String ciudad : listaDestinoTransbordo) {
						comboDestino.addItem(ciudad);
					}
					comboOrigen.setSelectedIndex(-1);
					comboOrigen.setRenderer(new ComboBoxRenderer("Origen"));
					comboDestino.setSelectedIndex(-1);
					comboDestino.setRenderer(new ComboBoxRenderer("Destino"));
					
				} else if(!checkTransbordo.isSelected()) {
					transbordo = 0;
				}
			}
		});
		
		panelNumCheck.add(panelCheckTransbordo);

		// radio button primera y segunda clase

		JPanel panelRadioClase = new JPanel(new GridLayout(1, 2));
		panelRadioClase.setBackground(new Color(153, 0, 102));

		JRadioButton radioSegundaClase = new JRadioButton("Segunda clase (+0.00?)", true);
		radioSegundaClase.setBackground(new Color(153, 0, 102));
		radioSegundaClase.setForeground(Color.WHITE);

		JRadioButton radioPrimeraClase = new JRadioButton("Primera clase (+12.00?)", false);
		radioPrimeraClase.setBackground(new Color(153, 0, 102));
		radioPrimeraClase.setForeground(Color.WHITE);

		Border bordejpanel2 = new TitledBorder(new EtchedBorder(), "Clase");
		panelRadioClase.setBorder(bordejpanel2);
		
		buttonGroup2.add(radioSegundaClase);
		buttonGroup2.add(radioPrimeraClase);

		panelRadioClase.add(radioSegundaClase);
		panelRadioClase.add(radioPrimeraClase);

		// JCheckBox extras

		JPanel panelCheckExtras = new JPanel(new GridLayout(2, 1));
		panelCheckExtras.setBackground(new Color(153, 0, 102));

		JPanel panelCheckExtras2 = new JPanel(new GridLayout(2, 1));
		panelCheckExtras2.setBackground(new Color(153, 0, 102));

		JCheckBox checkComida = new JCheckBox("Comida (+15.00?)");
		checkComida.setBackground(new Color(153, 0, 102));
		checkComida.setForeground(Color.WHITE);
		
		checkComida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkComida.isSelected()) {
					extra1 = "comida";
					extraComida = 1;
				} else if(!checkComida.isSelected()) {
					extra1 = "";
					extraComida = 0;
				}
			}
		});

		JCheckBox checkAsientoIndividual = new JCheckBox("Asiento individual (+9.00?)");
		checkAsientoIndividual.setBackground(new Color(153, 0, 102));
		checkAsientoIndividual.setForeground(Color.WHITE);
		
		checkAsientoIndividual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkAsientoIndividual.isSelected()) {
					extra2 = "asiento individual";
					extraAsientoIndividual = 1;
				} else if(!checkAsientoIndividual.isSelected()) {
					extra2 = "";
					extraAsientoIndividual = 0;
				}
			}
		});
		
		JCheckBox checkSeguro = new JCheckBox("Seguro viaje (+3.00?)");
		checkSeguro.setBackground(new Color(153, 0, 102));
		checkSeguro.setForeground(Color.WHITE);
		
		checkSeguro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkSeguro.isSelected()) {
					extra1 = "seguro viaje";
					extraSeguroViaje = 1;
				} else if(!checkSeguro.isSelected()) {
					extra1 = "";
					extraSeguroViaje = 0;
				}
			}
		});
		
		JCheckBox checkMesa = new JCheckBox("Mesa (+2.00?)");
		checkMesa.setBackground(new Color(153, 0, 102));
		checkMesa.setForeground(Color.WHITE);
		
		checkMesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkMesa.isSelected()) {
					extra2 = "mesa";
					extraMesa = 1;
				} else if(!checkMesa.isSelected()) {
					extra2 = "";
					extraMesa = 0;
				}
			}
		});

		Border bordejpanel3 = new TitledBorder(new EtchedBorder(), "Extras");
		panelCheckExtras.setBorder(bordejpanel3);

		Border bordejpanel4 = new TitledBorder(new EtchedBorder(), "Extras");
		panelCheckExtras2.setBorder(bordejpanel4);
		panelCheckExtras2.setVisible(false);

		panelCheckExtras.add(checkSeguro);
		panelCheckExtras.add(checkMesa);
		panelCheckExtras2.add(checkComida);
		panelCheckExtras2.add(checkAsientoIndividual);

		JPanel panelExtras = new JPanel(new GridLayout(1, 2));
		panelExtras.setBackground(new Color(153, 0, 102));

		panelExtras.add(panelCheckExtras);
		panelExtras.add(panelCheckExtras2);

		radioSegundaClase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioPrimeraClase.setSelected(false);
				panelCheckExtras.setVisible(true);
				panelCheckExtras2.setVisible(false);
				clase = "Segunda clase";
				claseInt = 2;
				extra1 = "";
				extra2 = "";
				extraComida = 0;
				extraAsientoIndividual = 0;
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
				extra1 = "";
				extra2 = "";
				extraSeguroViaje = 0;
				extraMesa = 0;
			}
		});

		JPanel panelRadioNum = new JPanel(new GridLayout(1, 2));
		panelCombos.setBackground(new Color(153, 0, 102));
		
		panelRadioNum.add(panelRadioIdaVueltaIda);
		panelRadioNum.add(panelNumCheck);
		
		panelArriba.add(panelRadioNum);
		panelArriba.add(panelRadioClase);
		panelArriba.add(panelExtras);
		
		// bot?n fecha ida

		JPanel panelBotonFechaIda = new JPanel();
		panelBotonFechaIda.setBackground(new Color(153, 0, 102));
		JButton botonFechaIda = new JButton("Seleccionar");
		botonFechaIda.setFont(new Font("Yu Gothic UI", Font.BOLD, 9));
		botonFechaIda.setBackground(Color.WHITE);
		botonFechaIda.setForeground(Color.BLACK);
		botonFechaIda.setPreferredSize(new Dimension(89, 18));
		panelBotonFechaIda.add(botonFechaIda);

		// calendario ida

		JPanel panelCalendarioIda = new JPanel();
		panelCalendarioIda.setBackground(new Color(153, 0, 102));
		calendarioIda = new JCalendar();
		textFechaIda = new JTextField();
		// calendarioIda.setTodayButtonVisible(true);
		calendarioIda.setWeekOfYearVisible(false);
		panelCalendarioIda.add(calendarioIda);

		Border bordejpanel7 = new TitledBorder(new MatteBorder(null), "Ida:");
		panelCalendarioIda.setBorder(bordejpanel7);
		
		botonFechaIda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String a?o = Integer.toString(calendarioIda.getCalendar().get(java.util.Calendar.YEAR));
					String mes = Integer.toString(calendarioIda.getCalendar().get(java.util.Calendar.MONTH) + 1);
					String dia = Integer.toString(calendarioIda.getCalendar().get(java.util.Calendar.DATE));
					textFechaIda.setText(dia + "-" + mes + "-" + a?o);
				} catch (Exception e) {
					Log.logger.log(Level.SEVERE, "No se ha podido guardar la fecha." + e.getStackTrace());
				}
			}
		});

		panelMedio.add(panelCalendarioIda);
		
		// bot?n fecha vuelta

		panelBotonFechaVuelta = new JPanel();
		panelBotonFechaVuelta.setBackground(new Color(153, 0, 102));
		JButton botonFechaVuelta = new JButton("Seleccionar");
		botonFechaVuelta.setFont(new Font("Yu Gothic UI", Font.BOLD, 9));
		botonFechaVuelta.setBackground(Color.WHITE);
		botonFechaVuelta.setForeground(Color.BLACK);
		botonFechaVuelta.setPreferredSize(new Dimension(89, 18));
		panelBotonFechaVuelta.add(botonFechaVuelta);

		// calendario vuelta

		panelCalendarioVuelta = new JPanel();
		panelCalendarioVuelta.setBackground(new Color(153, 0, 102));
		calendarioVuelta = new JCalendar();
		textFechaVuelta = new JTextField();
		// calendarioVuelta.setTodayButtonVisible(true);
		calendarioVuelta.setWeekOfYearVisible(false);
		panelCalendarioVuelta.add(calendarioVuelta);

		Border bordejpanel8 = new TitledBorder(new MatteBorder(null), "Vuelta:");
		panelCalendarioVuelta.setBorder(bordejpanel8);
		
		botonFechaVuelta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String a?o = Integer.toString(calendarioVuelta.getCalendar().get(java.util.Calendar.YEAR));
					String mes = Integer.toString(calendarioVuelta.getCalendar().get(java.util.Calendar.MONTH) + 1);
					String dia = Integer.toString(calendarioVuelta.getCalendar().get(java.util.Calendar.DATE));
					textFechaVuelta.setText(dia + "-" + mes + "-" + a?o);
				} catch (Exception e) {
					Log.logger.log(Level.SEVERE, "No se ha podido guardar la fecha." + e.getStackTrace());
				}
			}
		});

		panelMedio.add(panelCalendarioVuelta);
		
		panelAbajo.add(panelBotonFechaIda);
		panelAbajo.add(panelBotonFechaVuelta);

		// bot?n volver

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
					if (VentanaInicio.var == 1) {
						new VentanaInicio();
						dispose();
					} else if (VentanaInicio.var == 2) {
						new VentanaMenuPrincipal();
						dispose();
					}
				} catch (Exception e) {
					Log.logger.log(Level.SEVERE, "No se ha podido abrir la ventana" + e.getStackTrace());
				}
			}
		});

		// bot?n siguiente

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
					if (transbordo == 1 && !comboOrigen.getSelectedItem().toString().equals(null) && !comboDestino.getSelectedItem().toString().equals(null)) {
						new VentanaTransbordo();
						dispose();
					}
					else if (Metodos.existeViaje(comboOrigen.getSelectedItem().toString(),
							comboDestino.getSelectedItem().toString(), textFechaIda.getText(),
							textFechaVuelta.getText(), (int) spinnerNumBilletes.getValue(), tipoBilleteInt)) {
						new VentanaConfirmacionCompra();
						dispose();
					}
				} catch (Exception e) {
					Log.logger.log(Level.SEVERE, "Error en la compra." + e.getStackTrace());
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
}