package ventanas;

import log.Log;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class VentanaInicio extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

    private JPanel panel;
    private JPanel panelArriba;
    private JPanel panelDerecha;
    private JPanel panelIzquierda;
    private JPanel panelAbajo;

    JMenuBar menuBar;
	JMenu menu;
	JMenuItem itemInicio, itemGaleria, itemAdmin;

    static int var = 0;
    
	public VentanaInicio() throws IOException {
    	
        setBackground(new Color(153, 0, 102));

        setTitle("Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(570, 335));
        setVisible(true);
        pack();

        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 0, 102));
        setContentPane(contentPane);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(153, 0, 102));
        panelArriba = new JPanel(new BorderLayout());
        panelArriba.setBackground(new Color(153, 0, 102));
        panelDerecha = new JPanel(new GridLayout(3, 1));
        panelDerecha.setBackground(new Color(153, 0, 102));
        panelIzquierda = new JPanel(new BorderLayout());
        panelIzquierda.setBackground(new Color(153, 0, 102));
        panelAbajo = new JPanel(new GridLayout(1, 2));
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
                	Log.logger.log(Level.SEVERE, "No se ha podido abrir la VentanaGaleria" + e.toString());
                }
            }
        });

        setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(itemInicio);
		menu.add(itemGaleria);

        // panel
		
		// label título
		
		JPanel panelLabelTitulo = new JPanel();
		panelLabelTitulo.setBackground(new Color(153, 0, 102));
		JLabel labelTitulo = new JLabel("BUSCADOR DE VIAJES EN TREN");
		labelTitulo.setForeground(Color.WHITE);
		labelTitulo.setFont(new Font("Calibri", Font.BOLD, 23));
		panelLabelTitulo.add(labelTitulo);
		
		panelArriba.add(panelLabelTitulo);
		
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
                	Log.logger.log(Level.SEVERE, "No se ha podido abrir la VentanaInicioSesion." + e.getStackTrace());
                }
            }
        });

        panelDerecha.add(panelBotonIniciarSesion);

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
                	Log.logger.log(Level.SEVERE, "No se ha podido abrir la VentanaRegistro." + e.getStackTrace());
                }
            }
        });

        panelDerecha.add(panelBotonRegistrarse);

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
                	Log.logger.log(Level.SEVERE, "No se ha podido abrir la VentanaCompra." + e.getStackTrace());
                }
            }
        });

        panelDerecha.add(panelBotonComprarSinUsuario);
        
		// label imagen

		JPanel panelLabelImagen = new JPanel();
		panelLabelImagen.setBackground(new Color(153, 0, 102));
		BufferedImage bufferedImage = ImageIO.read(new File("src/img/tren.jpg"));
		Image image = bufferedImage.getScaledInstance(250, 160, Image.SCALE_DEFAULT);
		JLabel labelImagen = new JLabel(new ImageIcon(image));
		panelLabelImagen.add(labelImagen);
		
		panelIzquierda.add(panelLabelImagen);
		
		panelAbajo.add(panelIzquierda);
		panelAbajo.add(panelDerecha);
		
		Border border = panel.getBorder();
		Border margin = new EmptyBorder(14, 0, 0, 0);
		panel.setBorder(new CompoundBorder(border, margin));

        panel.add(panelArriba, BorderLayout.NORTH);
        panel.add(panelAbajo, BorderLayout.SOUTH);

        contentPane.setVisible(true);
        panel.setVisible(true);
    }
}