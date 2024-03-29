package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import clases.Viaje;
import log.Log;

public class VentanaGaleriaDestino extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelArriba;
	private JPanel panelMedio;
	private JPanel panelAbajo;

	public VentanaGaleriaDestino(Viaje viaje) throws IOException {

		setBackground(new Color(153, 0, 102));

		setTitle("Galer�a de destinos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 432));
		setVisible(true);
		pack();

		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 0, 102));
		setContentPane(contentPane);

		panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(153, 0, 102));
		panelArriba = new JPanel(new BorderLayout());
		panelArriba.setBackground(new Color(153, 0, 102));
		panelMedio = new JPanel(new GridLayout(2, 2));
		panelMedio.setBackground(new Color(153, 0, 102));
		panelAbajo = new JPanel(new BorderLayout());
		panelAbajo.setBackground(new Color(153, 0, 102));

		// labels

		JPanel panelLabelDestino = new JPanel();
		panelLabelDestino.setBackground(new Color(153, 0, 102));
		JLabel labelDestino = new JLabel(viaje.getDestino().toUpperCase());
		labelDestino.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		labelDestino.setForeground(Color.WHITE);
		panelLabelDestino.add(labelDestino);

		JPanel panelLabelOrigen = new JPanel();
		panelLabelOrigen.setBackground(new Color(153, 0, 102));
		JLabel labelOrigen = new JLabel("Origen: " + viaje.getOrigen());
		labelOrigen.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		labelOrigen.setForeground(Color.WHITE);
		panelLabelOrigen.add(labelOrigen);
		panelLabelOrigen.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.white, Color.gray));

		JPanel panelLabelFecha = new JPanel();
		panelLabelFecha.setBackground(new Color(153, 0, 102));
		JLabel labelFecha = new JLabel("Fecha: " + viaje.getFecha());
		labelFecha.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		labelFecha.setForeground(Color.WHITE);
		panelLabelFecha.add(labelFecha);
		panelLabelFecha.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.white, Color.gray));

		JPanel panelLabelAforo = new JPanel();
		panelLabelAforo.setBackground(new Color(153, 0, 102));
		JLabel labelAforo = new JLabel("Plazas disponibles: " + String.valueOf(viaje.getAforo()));
		labelAforo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		labelAforo.setForeground(Color.WHITE);
		panelLabelAforo.add(labelAforo);
		panelLabelAforo.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.white, Color.gray));

		JPanel panelLabelPrecio = new JPanel();
		panelLabelPrecio.setBackground(new Color(153, 0, 102));
		JLabel labelPrecio = new JLabel("Precio: " + String.valueOf(VentanaConfirmacionCompra.formato1.format(viaje.getPrecio())) + " �");
		labelPrecio.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		labelPrecio.setForeground(Color.WHITE);
		panelLabelPrecio.add(labelPrecio);
		panelLabelPrecio.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.white, Color.gray));

		// label imagen

		JPanel panelLabelImagen = new JPanel();
		panelLabelImagen.setBackground(new Color(153, 0, 102));
		BufferedImage bufferedImage = ImageIO.read(new File(viaje.getImagen()));
		Image image = bufferedImage.getScaledInstance(400, 200, Image.SCALE_DEFAULT);
		JLabel labelImagen = new JLabel(new ImageIcon(image));
		panelLabelImagen.add(labelImagen);

		// bot�n volver

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
					new VentanaGaleria();
					dispose();
				} catch (Exception e) {
					Log.logger.log(Level.SEVERE, "No se ha podido volver a la VentanaGaleria." + e.getStackTrace());
				}
			}
		});

		panelArriba.add(panelLabelDestino);
		panelMedio.add(panelLabelOrigen);
		panelMedio.add(panelLabelFecha);
		panelMedio.add(panelLabelAforo);
		panelMedio.add(panelLabelPrecio);

		panelAbajo.add(panelLabelImagen, BorderLayout.NORTH);
		panelAbajo.add(panelBotonVolver, BorderLayout.SOUTH);
		
		Border border = panel.getBorder();
		Border margin = new EmptyBorder(8, 0, 0, 0);
		panel.setBorder(new CompoundBorder(border, margin));

		panel.add(panelArriba, BorderLayout.NORTH);
		panel.add(panelMedio, BorderLayout.CENTER);
		panel.add(panelAbajo, BorderLayout.SOUTH);

		contentPane.setVisible(true);
		contentPane.add(panel);
		panel.setVisible(true);
	}
}
