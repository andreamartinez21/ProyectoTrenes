package ventanas;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        panelArriba = new JPanel(new GridLayout(1, 2));
        panelArriba.setBackground(new Color(153, 0, 102));
        panelMedio = new JPanel(new GridLayout(2, 2));
        panelMedio.setBackground(new Color(153, 0, 102));
        panelAbajo = new JPanel(new GridLayout(1, 2));
        panelAbajo.setBackground(new Color(153, 0, 102));

        contentPane.add(panel);

        // METER BORDES

        // label datos del viaje

        JPanel panelLabelDatos = new JPanel();
        panelLabelDatos.setBackground(new Color(153, 0, 102));
        JLabel labelDatos = new JLabel("DATOS DEL VIAJE:\n CONFIRMACIÓN DE COMPRA");
        labelDatos.setForeground(Color.WHITE);
        panelLabelDatos.add(labelDatos);

        panelArriba.add(panelLabelDatos);

        // label usuario

        JPanel panelLabelUsuario = new JPanel();
        panelLabelUsuario.setBackground(new Color(153, 0, 102));
        JLabel labelUsuario = new JLabel("Usuario: ");
        labelUsuario.setForeground(Color.WHITE);
        panelLabelUsuario.add(labelUsuario);

        JPanel panelTextoUsuario = new JPanel(); 
        panelTextoUsuario.setBackground(new Color(153, 0, 102));
        JTextField textoUsuario = new JTextField();
        textoUsuario.setPreferredSize(new Dimension(150, 25));
        panelTextoUsuario.add(textoUsuario);

        panelMedio.add(panelLabelUsuario);
        panelMedio.add(panelTextoUsuario);

        // label contraseña

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

        panelMedio.add(panelLabelContrasenya);
        panelMedio.add(panelTextoContrasenya);

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

        // botón ticket

        JPanel panelBotonTicket = new JPanel();
        panelBotonTicket.setBackground(new Color(153, 0, 102));
        JButton botonTicket = new JButton("Aceptar compra e imprimir ticket");
        botonTicket.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
        botonTicket.setBackground(Color.GRAY);
        botonTicket.setForeground(Color.WHITE);
        botonTicket.setPreferredSize(new Dimension(195, 30));
        panelBotonTicket.add(botonTicket);

        // botonTicket.addActionListener(new ActionListener() {
				
        //     @Override
        //     public void actionPerformed(ActionEvent arg0) {
        //         try {
        //             new VentanaMenuPrincipal();
        //			   dispose();
        //         } catch (Exception e) {
        //             // TODO Auto-generated catch block
        //             e.printStackTrace();
        //         }
        //     }
        // });

        panelAbajo.add(panelBotonVolver);
        panelAbajo.add(panelBotonTicket);

        //

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