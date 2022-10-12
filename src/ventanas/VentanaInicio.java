package ventanas;

import javax.swing.JFrame;

import clases.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInicio extends JFrame{

    private JPanel contentPane;

    private JPanel panel;
    private JPanel panelArriba;
    private JPanel panelMedio;
    private JPanel panelAbajo;

    JMenuBar menuBar;
	JMenu menu;
	JMenuItem itemInicio, itemGaleria, itemAdmin;

    static int var = 0;
    
	public VentanaInicio() throws IOException {
    	
        setBackground(new Color(153, 0, 102));

        setTitle("Inicio");
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
        panelMedio = new JPanel(new BorderLayout());
        panelMedio.setBackground(new Color(153, 0, 102));
        panelAbajo = new JPanel(new BorderLayout());
        panelAbajo.setBackground(new Color(153, 0, 102));

        contentPane.add(panel);

        //menú

		menuBar = new JMenuBar();
        menuBar.setBackground(new Color(153, 0, 102));
		menu = new JMenu("Más opciones");
		menu.setForeground(Color.WHITE);

        itemInicio = new JMenuItem("Inicio");
        itemInicio.setBackground(new Color(153, 0, 102));
        itemInicio.setForeground(Color.WHITE);
        itemGaleria = new JMenuItem("Galería");
        itemGaleria.setBackground(new Color(153, 0, 102));
        itemGaleria.setForeground(Color.WHITE);

        itemGaleria.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new VentanaGaleria();
                    dispose();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(itemInicio);
		menu.add(itemGaleria);

        // panel
		
        // botón iniciar sesión

        JPanel panelBotonIniciarSesion = new JPanel();
        panelBotonIniciarSesion.setBackground(new Color(153, 0, 102));
        JButton botonIniciarSesion = new JButton("Iniciar sesión");
        botonIniciarSesion.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
        botonIniciarSesion.setBackground(Color.GRAY);
        botonIniciarSesion.setForeground(Color.WHITE);
        botonIniciarSesion.setPreferredSize(new Dimension(190, 35));
        panelBotonIniciarSesion.add(botonIniciarSesion);

        botonIniciarSesion.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new VentanaInicioSesion();
                    dispose();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        panelArriba.add(panelBotonIniciarSesion);

        // botón registrarse

        JPanel panelBotonRegistrarse = new JPanel();
        panelBotonRegistrarse.setBackground(new Color(153, 0, 102));
        JButton botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
        botonRegistrarse.setBackground(Color.GRAY);
        botonRegistrarse.setForeground(Color.WHITE);
        botonRegistrarse.setPreferredSize(new Dimension(190, 35));
        panelBotonRegistrarse.add(botonRegistrarse);

        botonRegistrarse.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new VentanaRegistro();
                    dispose();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        panelMedio.add(panelBotonRegistrarse);

        // botón comprar sin usuario

        JPanel panelBotonComprarSinUsuario = new JPanel();
        panelBotonComprarSinUsuario.setBackground(new Color(153, 0, 102));
        JButton botonComprarSinUsuario = new JButton("Comprar sin usuario");
        botonComprarSinUsuario.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        botonComprarSinUsuario.setBackground(Color.GRAY);
        botonComprarSinUsuario.setForeground(Color.WHITE);
        botonComprarSinUsuario.setPreferredSize(new Dimension(160, 35));
        panelBotonComprarSinUsuario.add(botonComprarSinUsuario);

        botonComprarSinUsuario.addActionListener(new ActionListener() {
				
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new VentanaCompra();
                    dispose();
                    var = 1;
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        panelAbajo.add(panelBotonComprarSinUsuario);

        //

        panel.add(panelArriba, BorderLayout.NORTH);
        panel.add(panelMedio, BorderLayout.CENTER);
        panel.add(panelAbajo, BorderLayout.SOUTH);

        contentPane.setVisible(true);
        panel.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
		new VentanaInicio();
	}
}