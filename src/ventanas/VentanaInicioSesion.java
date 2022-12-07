package ventanas;

import javax.swing.*;

import BD.BD;
import clases.Billete;
import log.Log;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInicioSesion extends JFrame{

    private JPanel contentPane;

    private JPanel panel;
    private JPanel panelArriba;
    private JPanel panelMedio;
    private JPanel panelAbajo;
    
	public VentanaInicioSesion() throws IOException {
    	
        setBackground(new Color(153, 0, 102));

        setTitle("Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 285));
        setVisible(true);
        pack();

        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 0, 102));
        setContentPane(contentPane);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(153, 0, 102));
        panelArriba = new JPanel(new GridLayout(1, 2));
        panelArriba.setBackground(new Color(153, 0, 102));
        panelMedio = new JPanel(new GridLayout(1, 2));
        panelMedio.setBackground(new Color(153, 0, 102));
        panelAbajo = new JPanel(new GridLayout(1, 2));
        panelAbajo.setBackground(new Color(153, 0, 102));

        contentPane.add(panel);

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

        panelArriba.add(panelLabelUsuario);
        panelArriba.add(panelTextoUsuario);

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
                    new VentanaInicio();
                    dispose();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        // botón acceder

        JPanel panelBotonAcceder = new JPanel();
        panelBotonAcceder.setBackground(new Color(153, 0, 102));
        JButton botonAcceder = new JButton("Acceder");
        botonAcceder.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
        botonAcceder.setBackground(Color.GRAY);
        botonAcceder.setForeground(Color.WHITE);
        botonAcceder.setPreferredSize(new Dimension(150, 30));
        panelBotonAcceder.add(botonAcceder);

        botonAcceder.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (Metodos.login(textoUsuario.getText(), textoContrasenya.getText())) {
						new VentanaMenuPrincipal();
						dispose();
						Log.logger.log(Level.INFO, "Se ha iniciado sesión correctamente.");
                	} else {
                		JOptionPane.showMessageDialog(null, "Ha habido un error en el inicio de sesión.");
                		Log.logger.log(Level.SEVERE, "Ha habido un error en el inicio de sesión.");
                	}
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error");
                    Log.logger.log(Level.SEVERE, "Error");
                }
            }
        });

        panelAbajo.add(panelBotonVolver);
        panelAbajo.add(panelBotonAcceder);

        panel.add(panelArriba, BorderLayout.NORTH);
        panel.add(panelMedio, BorderLayout.CENTER);
        panel.add(panelAbajo, BorderLayout. SOUTH);

        contentPane.setVisible(true);
        panel.setVisible(true);
    }
}