package ventanas;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Billete;
import BD.BD;

public class VentanaVerViajes extends JFrame {
	
	BD bd = new BD();

	private JPanel contentPane;

	private JPanel panel;
	private JPanel panelArriba;
	private JPanel panelAbajo;

	public VentanaVerViajes() throws IOException {

		setBackground(new Color(0, 0, 51));

		setTitle("Ver mis viajes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 315));
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
		
		// JTable
		
		// array columnas tabla
		
		String[] columnas = new String[] {"ORIGEN", "DESTINO", "LOCALIZADOR(ES)", "PRECIO"};

        // crear tabla
		
        DefaultTableModel modeloTabla;
        modeloTabla = new DefaultTableModel(columnas, 0);
		JTable table = new JTable(modeloTabla);
		table.setBackground(Color.PINK);
		
		// arrayList datos filas
		
		int i = 0;
        for (Billete billete : listaBilletesCliente) {
        	modeloTabla.addRow(new Object[] {listaBilletesCliente.get(i).getViajeIda().getOrigen(), listaBilletesCliente.get(i).getViajeIda().getDestino(), listaBilletesCliente.get(i).getViajeIda().getLocalizador() + " - " + listaBilletesCliente.get(i).getViajeVuelta().getLocalizador(), VentanaConfirmacionCompra.formato1.format(listaBilletesCliente.get(i).getPrecio()) + " €"});
        	table.setRowHeight(i, 30);
        	i++;
        }
		
        // scrollPane
        
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(450, 220));
		panelArriba.add(scrollPane);
		
		// poner la tabla no editable

//        DefaultTableModel tableModel = new DefaultTableModel() { 
//        	
//        	@Override 
//        	public boolean isCellEditable(int row, int column) { 
//        	//all cells false 
//        		return false; 
//        	}
//        };
//       
//        table.setModel(tableModel);

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
					// TODO Auto-generated catch block
					e.printStackTrace();
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