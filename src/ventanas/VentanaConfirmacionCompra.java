package ventanas;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import BD.BD;
import log.Log;

public class VentanaConfirmacionCompra extends JFrame{

    private JPanel contentPane;

    private JPanel panel;
    private JPanel panelArriba;
    private JPanel panelMedio;
    private JPanel panelAbajo;
    
	public VentanaConfirmacionCompra() throws IOException {
    	
        setBackground(new Color(153, 0, 102));

        setTitle("Confirmación compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 350));
        setVisible(true);
        pack();

        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 0, 102));
        setContentPane(contentPane);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(153, 0, 102));
        panelArriba = new JPanel(new GridLayout(1, 1));
        panelArriba.setBackground(new Color(153, 0, 102));
        panelMedio = new JPanel(new GridLayout(5, 2));
        panelMedio.setBackground(new Color(153, 0, 102));
        panelAbajo = new JPanel(new GridLayout(3, 2));
        panelAbajo.setBackground(new Color(153, 0, 102));

        contentPane.add(panel);
        
        // título
        
        JPanel panelLabelTitulo = new JPanel();
        panelLabelTitulo.setBackground(new Color(153, 0, 102));
        JLabel labelTitulo = new JLabel("RESUMEN DE LA COMPRA:");
        labelTitulo.setForeground(Color.WHITE);
        panelLabelTitulo.add(labelTitulo);
        
        panelArriba.add(panelLabelTitulo);

        // labels datos del viaje

        JPanel panelLabelOrigen = new JPanel();
        panelLabelOrigen.setBackground(new Color(153, 0, 102));
        JLabel labelOrigen = new JLabel("Origen: Madrid");
        labelOrigen.setForeground(Color.WHITE);
        panelLabelOrigen.add(labelOrigen);
        
        JPanel panelLabelDestino = new JPanel();
        panelLabelDestino.setBackground(new Color(153, 0, 102));
        JLabel labelDestino = new JLabel("Destino: Malta");
        labelDestino.setForeground(Color.WHITE);
        panelLabelDestino.add(labelDestino);
        
        JPanel panelLabelFechaIda = new JPanel();
        panelLabelFechaIda.setBackground(new Color(153, 0, 102));
        JLabel labelFechaIda = new JLabel("Fecha ida: 2022-11-10");
        labelFechaIda.setForeground(Color.WHITE);
        panelLabelFechaIda.add(labelFechaIda);
        
        JPanel panelLabelFechaVuelta = new JPanel();
        panelLabelFechaVuelta.setBackground(new Color(153, 0, 102));
        JLabel labelFechaVuelta = new JLabel("Fecha vuelta: 2022-11-17");
        labelFechaVuelta.setForeground(Color.WHITE);
        panelLabelFechaVuelta.add(labelFechaVuelta);
        
        JPanel panelLabelAsiento = new JPanel();
        panelLabelAsiento.setBackground(new Color(153, 0, 102));
        JLabel labelAsiento = new JLabel("Asiento: 22C");
        labelAsiento.setForeground(Color.WHITE);
        panelLabelAsiento.add(labelAsiento);
        
        JPanel panelLabelClase = new JPanel();
        panelLabelClase.setBackground(new Color(153, 0, 102));
        JLabel labelClase = new JLabel("Primera clase");
        labelClase.setForeground(Color.WHITE);
        panelLabelClase.add(labelClase);
        
        JPanel panelLabelExtras = new JPanel();
        panelLabelExtras.setBackground(new Color(153, 0, 102));
        JLabel labelExtras = new JLabel("Extras: comida");
        labelExtras.setForeground(Color.WHITE);
        panelLabelExtras.add(labelExtras);
        
        JPanel panelLabelPrecio = new JPanel();
        panelLabelPrecio.setBackground(new Color(153, 0, 102));
        JLabel labelPrecio = new JLabel("Precio total: 209€");
        labelPrecio.setForeground(Color.WHITE);
        panelLabelPrecio.add(labelPrecio);
        
        panelMedio.add(panelLabelOrigen);
        panelMedio.add(panelLabelDestino);
        panelMedio.add(panelLabelFechaIda);
        panelMedio.add(panelLabelFechaVuelta);
        panelMedio.add(panelLabelAsiento);
        panelMedio.add(panelLabelClase);
        panelMedio.add(panelLabelExtras);
        panelMedio.add(panelLabelPrecio);
        
        JPanel panelLabelConfirmar = new JPanel();
        panelLabelConfirmar.setBackground(new Color(153, 0, 102));
        JLabel labelConfirmar = new JLabel("Confirme la compra: ");
        labelConfirmar.setForeground(Color.WHITE);
        panelLabelConfirmar.add(labelConfirmar);
        
        JPanel panelLabelBlanco = new JPanel();
        panelLabelBlanco.setBackground(new Color(153, 0, 102));
        JLabel labelBlanco = new JLabel("");
        labelBlanco.setForeground(Color.WHITE);
        panelLabelBlanco.add(labelBlanco);
        
        panelAbajo.add(panelLabelConfirmar);
        panelAbajo.add(panelLabelBlanco);

        // label y texto contraseña

        JPanel panelLabelContrasenya = new JPanel();
        panelLabelContrasenya.setBackground(new Color(153, 0, 102));
        JLabel labelContrasenya = new JLabel("Contraseña: ");
        labelContrasenya.setForeground(Color.WHITE);
        panelLabelContrasenya.add(labelContrasenya);

        JPanel panelTextoContrasenya = new JPanel(); 
        panelTextoContrasenya.setBackground(new Color(153, 0, 102));
        JPasswordField textoContrasenya = new JPasswordField();
        textoContrasenya.setPreferredSize(new Dimension(150, 25));
        panelTextoContrasenya.add(textoContrasenya);

        panelAbajo.add(panelLabelContrasenya);
        panelAbajo.add(panelTextoContrasenya);
        
        if(VentanaInicio.var == 1) {
        	panelLabelContrasenya.setVisible(false);
        	panelTextoContrasenya.setVisible(false);
        } else if(VentanaInicio.var == 2) {
        	panelLabelContrasenya.setVisible(true);
        	panelTextoContrasenya.setVisible(true);
        }

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
                    new VentanaCompra();
                    dispose();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        // botón aceptar y ticket

        JPanel panelBotonTicket = new JPanel();
        panelBotonTicket.setBackground(new Color(153, 0, 102));
        JButton botonTicket = new JButton("Aceptar compra e imprimir ticket");
        botonTicket.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
        botonTicket.setBackground(Color.GRAY);
        botonTicket.setForeground(Color.WHITE);
        botonTicket.setPreferredSize(new Dimension(195, 30));
        panelBotonTicket.add(botonTicket);

        botonTicket.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	if(VentanaInicio.var == 1) {
            			new VentanaInicio();
            			dispose();
                	} else if(VentanaInicio.var == 2){
                		if(textoContrasenya.getText().equals(BD.clienteActual.getContrasenya())) {
                			new VentanaMenuPrincipal();
                			dispose();
                		} else {
                			JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
            	   			Log.logger.log(Level.SEVERE, "Contraseña incorrecta.");
                		}
                	}
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        panelAbajo.add(panelBotonVolver);
        panelAbajo.add(panelBotonTicket);

        panel.add(panelArriba, BorderLayout.NORTH);
        panel.add(panelMedio, BorderLayout.CENTER);
        panel.add(panelAbajo, BorderLayout. SOUTH);

        contentPane.setVisible(true);
        panel.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
		new VentanaConfirmacionCompra();
	}
}