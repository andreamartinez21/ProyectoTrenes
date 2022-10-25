package ventanas;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import BD.BD;
import log.Log;

public class VentanaMenuPrincipal extends JFrame{

    private JPanel contentPane;

    private JPanel panel;
    private JPanel panelArriba;
    private JPanel panelMedio;
    private JPanel panelAbajo;
    
	public VentanaMenuPrincipal() throws IOException {
    	
        setBackground(new Color(153, 0, 102));

        setTitle("Menú principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 350));
        setVisible(true);
        pack();

        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 0, 102));
        setContentPane(contentPane);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(153, 0, 102));
        panelArriba = new JPanel(new BorderLayout());
        panelArriba.setBackground(new Color(153, 0, 102));
        panelMedio = new JPanel(new GridLayout(3, 1));
        panelMedio.setBackground(new Color(153, 0, 102));
        panelAbajo = new JPanel(new BorderLayout());
        panelAbajo.setBackground(new Color(153, 0, 102));

        contentPane.add(panel);

        // panel

        // label nombre usuario

        JPanel panelLabelNombreUsuario = new JPanel();
        panelLabelNombreUsuario.setBackground(new Color(153, 0, 102));
        JLabel labelNombreUsuario = new JLabel("Hola, " + BD.clienteActual.getNombre() + " " + BD.clienteActual.getApellido() + "!");
        labelNombreUsuario.setForeground(Color.WHITE);
        panelLabelNombreUsuario.add(labelNombreUsuario);

        panelArriba.add(panelLabelNombreUsuario);

        // botón comprar billetes

        JPanel panelBotonComprarBilletes = new JPanel();
        panelBotonComprarBilletes.setBackground(new Color(153, 0, 102));
        JButton botonComprarBilletes = new JButton("Comprar billetes");
        botonComprarBilletes.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
        botonComprarBilletes.setBackground(Color.GRAY);
        botonComprarBilletes.setForeground(Color.WHITE);
        botonComprarBilletes.setPreferredSize(new Dimension(190, 35));
        panelBotonComprarBilletes.add(botonComprarBilletes);

        botonComprarBilletes.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new VentanaCompra();
                    dispose();
                    VentanaInicio.var = 2;
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        panelMedio.add(panelBotonComprarBilletes);

        // botón perfil

        JPanel panelBotonPerfil = new JPanel();
        panelBotonPerfil.setBackground(new Color(153, 0, 102));
        JButton botonPerfil = new JButton("Perfil");
        botonPerfil.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
        botonPerfil.setBackground(Color.GRAY);
        botonPerfil.setForeground(Color.WHITE);
        botonPerfil.setPreferredSize(new Dimension(160, 35));
        panelBotonPerfil.add(botonPerfil);

        botonPerfil.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new VentanaPerfil();
                    dispose();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        panelMedio.add(panelBotonPerfil);

        // botón mis viajes

        JPanel panelBotonMisViajes = new JPanel();
        panelBotonMisViajes.setBackground(new Color(153, 0, 102));
        JButton botonMisViajes = new JButton("Mis viajes");
        botonMisViajes.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
        botonMisViajes.setBackground(Color.GRAY);
        botonMisViajes.setForeground(Color.WHITE);
        botonMisViajes.setPreferredSize(new Dimension(160, 35));
        panelBotonMisViajes.add(botonMisViajes);

        botonMisViajes.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new VentanaVerViajes();
                    dispose();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        panelMedio.add(panelBotonMisViajes);

        // botón cerrar sesión

        JPanel panelBotonCerrarSesion = new JPanel();
        panelBotonCerrarSesion.setBackground(new Color(153, 0, 102));
        JButton botonCerrarSesion = new JButton("Cerrar sesión");
        botonCerrarSesion.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        botonCerrarSesion.setBackground(Color.GRAY);
        botonCerrarSesion.setForeground(Color.WHITE);
        botonCerrarSesion.setPreferredSize(new Dimension(130, 30));
        panelBotonCerrarSesion.add(botonCerrarSesion);

        botonCerrarSesion.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new VentanaInicio();
                    dispose();
                    BD.clienteActual = null;
                    Log.logger.log(Level.INFO, "Se ha cerrado la sesión correctamente.");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        panelAbajo.add(panelBotonCerrarSesion);

        //

        panel.add(panelArriba, BorderLayout.NORTH);
        panel.add(panelMedio, BorderLayout.CENTER);
        panel.add(panelAbajo, BorderLayout. SOUTH);

        contentPane.setVisible(true);
        panel.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
		new VentanaMenuPrincipal();
	}
}