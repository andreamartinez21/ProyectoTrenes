package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import clases.Viaje;
import log.Log;

public class VentanaTransbordo extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JPanel panel;
	private JPanel panelArriba;
	private JPanel panelAbajo;

	public static List<Viaje> listaViajes;
	
	double precioTotal = 0.0;

	public VentanaTransbordo() throws IOException, ParseException {

		setBackground(new Color(153, 0, 102));

		setTitle("Transbordo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(490, 320));
		setVisible(true);
		pack();

		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 0, 102));
		setContentPane(contentPane);

		listaViajes = new ArrayList<Viaje>();
		listaViajes = Metodos.transbordo(VentanaCompra.comboOrigen.getSelectedItem().toString(),
				VentanaCompra.comboDestino.getSelectedItem().toString());
//		listaViajes = Metodos.transbordo("Madrid", "Berlin");

		panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(153, 0, 102));
		panelArriba = new JPanel(new GridLayout(listaViajes.size(), 1));
		panelArriba.setBackground(new Color(153, 0, 102));
		panelAbajo = new JPanel(new GridLayout(1, 2));

		contentPane.add(panel);

		int i = 1;
		
		for (Viaje viaje : listaViajes) {

			JPanel panelLabelViaje = new JPanel();
			panelLabelViaje.setPreferredSize(new Dimension(500, 50));
			panelLabelViaje.setBackground(new Color(153, 0, 102));
			JLabel labelViaje = new JLabel(i + ". viaje: " + viaje.getOrigen().toUpperCase() + " - "
					+ viaje.getDestino().toUpperCase() + " --> " + viaje.getFecha());
			labelViaje.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
			labelViaje.setForeground(Color.LIGHT_GRAY);
			panelLabelViaje.add(labelViaje);

			panelArriba.add(panelLabelViaje);
			i++;

			precioTotal += Metodos.calcularPrecioBillete(VentanaCompra.tipoBillete, viaje, null, VentanaCompra.claseInt,
					VentanaCompra.extraComida, VentanaCompra.extraAsientoIndividual, VentanaCompra.extraSeguroViaje,
					VentanaCompra.extraMesa, VentanaConfirmacionCompra.conUsuario);
		}

		// botón volver

		JPanel panelBotonVolver = new JPanel();
		panelBotonVolver.setBackground(new Color(153, 0, 102));
		panelBotonVolver.setPreferredSize(new Dimension(160, 40));
		JButton botonVolver = new JButton("Volver a compra");
		botonVolver.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		botonVolver.setBackground(Color.GRAY);
		botonVolver.setForeground(Color.WHITE);
		botonVolver.setPreferredSize(new Dimension(160, 30));
		panelBotonVolver.add(botonVolver);

		botonVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					listaViajes.clear();
					new VentanaCompra();
					dispose();
				} catch (Exception e) {
					Log.logger.log(Level.SEVERE, "No se han podido abrir la ventana compra." + e.getStackTrace());
				}
			}
		});

		panelAbajo.add(panelBotonVolver);

		// label precio total

		JPanel panelPrecio = new JPanel();
		panelPrecio.setBackground(new Color(153, 0, 102));
		JLabel labelPrecio = new JLabel("Precio total: " + VentanaConfirmacionCompra.formato1.format(precioTotal) + "€");
		labelPrecio.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		labelPrecio.setForeground(Color.WHITE);
		panelPrecio.add(labelPrecio);

		panelAbajo.add(panelPrecio);

		if (i == 3) {
			Border border = panel.getBorder();
			Border margin = new EmptyBorder(50, 0, 0, 0);
			panel.setBorder(new CompoundBorder(border, margin));
			
			// poner para cuando haya 3 viajes (i == 4)
			
		} else if (i == 5) {
			Border border = panel.getBorder();
			Border margin = new EmptyBorder(20, 0, 0, 0);
			panel.setBorder(new CompoundBorder(border, margin));
		}
		
		panel.add(panelArriba, BorderLayout.NORTH);
		panel.add(panelAbajo, BorderLayout.SOUTH);

		contentPane.setVisible(true);
		panel.setVisible(true);
	}
}
