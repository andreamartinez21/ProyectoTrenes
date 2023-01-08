package ventanas;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import clases.Billete;
import log.Log;
import BD.BD;

public class VentanaVerViajes extends JFrame {

	private static final long serialVersionUID = 1L;

	BD bd = new BD();

	private JPanel contentPane;

	private JPanel panel;
	private JPanel panelArriba;
	private JPanel panelAbajo;
	
	public static JTable tableBillete;

	public VentanaVerViajes() throws IOException {

		setBackground(new Color(0, 0, 51));

		setTitle("Ver mis viajes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(655, 315));
		setVisible(true);
		pack();

		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 0, 102));
		setContentPane(contentPane);

		panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(153, 0, 102));
		panelArriba = new JPanel(new BorderLayout());
		panelArriba.setBackground(new Color(153, 0, 102));
		panelAbajo = new JPanel(new BorderLayout());
		panelAbajo.setBackground(new Color(153, 0, 102));

		contentPane.add(panel);
		
		// añadir al cliente su lista de billetes
		
		List<Billete> listaBilletesCliente = new ArrayList<Billete>();
		listaBilletesCliente = bd.getBilletesClienteBD(BD.clienteActual);
		BD.clienteActual.setListaBilletes(listaBilletesCliente);
	    
        // tabla
        
		tableBillete = new JTable();
		tableBillete.setModel(new BilleteTableModel(listaBilletesCliente));
		tableBillete.setBackground(new Color(204, 204, 204));
		tableBillete.setDefaultRenderer(Object.class, new CellRenderer());
		
		tableBillete.setRowHeight(30);
		tableBillete.getColumnModel().getColumn(4).setMinWidth(118);
		
		tableBillete.getColumnModel().getColumn(0).setCellRenderer(CellRenderer.textRenderer);
		tableBillete.getColumnModel().getColumn(1).setCellRenderer(CellRenderer.textRenderer);
		tableBillete.getColumnModel().getColumn(2).setCellRenderer(CellRenderer.textRenderer);
		tableBillete.getColumnModel().getColumn(3).setCellRenderer(CellRenderer.numRenderer);
		tableBillete.getColumnModel().getColumn(4).setCellRenderer(CellRenderer.textRenderer);

        // scrollPane
        
		JScrollPane scrollPane = new JScrollPane(tableBillete);
		scrollPane.setPreferredSize(new Dimension(615, 220));
		scrollPane.getViewport().setBackground(new Color(153, 0, 102));
		panelArriba.add(scrollPane);

		// botón volver

		JPanel panelBotonVolver = new JPanel();
		panelBotonVolver.setBackground(new Color(153, 0, 102));
		JButton botonVolver = new JButton("Volver");
		botonVolver.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		botonVolver.setBackground(Color.GRAY);
		botonVolver.setForeground(Color.WHITE);
		botonVolver.setPreferredSize(new Dimension(160, 33));
		panelBotonVolver.add(botonVolver);

		botonVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new VentanaMenuPrincipal();
					dispose();
				} catch (Exception e) {
					Log.logger.log(Level.SEVERE, "No se ha podido volver a la VentanaMenuPrincipal." + e.getStackTrace());
				}
			}
		});

		panelAbajo.add(panelBotonVolver);

		panel.add(panelArriba, BorderLayout.NORTH);
		panel.add(panelAbajo, BorderLayout.SOUTH);

		contentPane.setVisible(true);
		panel.setVisible(true);
	}
}