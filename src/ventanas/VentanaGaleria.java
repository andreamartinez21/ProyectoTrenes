package ventanas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import clases.Viaje;
import log.Log;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import BD.BD;

public class VentanaGaleria extends JFrame {

    private JPanel contentPane;

    private JScrollPane scroll;
    private JPanel panel;
    private JPanel panelArriba;
    private JPanel panelFotoBoton;
    
    JMenuBar menuBar;
	JMenu menu;
	JMenuItem itemInicio, itemGaleria, itemAdmin;

    public VentanaGaleria() throws IOException {

        setTitle("Galería de destinos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 690));
        setVisible(true);
        pack();

        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 0, 102));
        setContentPane(contentPane);
        
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
	
	    itemInicio.addActionListener(new ActionListener() {
				
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
          
        setJMenuBar(menuBar);
  		menuBar.add(menu);
  		menu.add(itemInicio);
  		menu.add(itemGaleria);

  		List<Viaje> listaViajes = new ArrayList<Viaje>();
        listaViajes = BD.getViajesBD();
       
        double numFotos = listaViajes.size();
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(153, 0, 102));
        
        double layoutY = Math.ceil(numFotos / 2);
        panelArriba = new JPanel(new GridLayout((int)layoutY, 2));
        panelArriba.setBackground(new Color(153, 0, 102));
        
        scroll = new JScrollPane(panelArriba);
        scroll.setPreferredSize(new Dimension(990, 600));
        scroll.setBackground(new Color(153, 0, 102));

    	for (Viaje viaje : listaViajes) {
    		
			BufferedImage bufferedImage = ImageIO.read(new File(viaje.getImagen()));
            Image image = bufferedImage.getScaledInstance(400, 200, Image.SCALE_DEFAULT);

            JLabel labelImagen = new JLabel(new ImageIcon(image));

            JPanel panelBotonDestino = new JPanel();
            panelBotonDestino.setBackground(new Color(153, 0, 102));
            JButton botonDestino = new JButton(viaje.getDestino());
            botonDestino.setPreferredSize(new Dimension(200, 30));
            botonDestino.setBackground(Color.GRAY);
            botonDestino.setForeground(Color.WHITE);
            panelBotonDestino.add(botonDestino);

            botonDestino.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    try {
                        new VentanaGaleriaDestino(viaje);
                        dispose();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                    	Log.logger.log(Level.SEVERE, "No se ha podido abrir la ventana.");
                    }
                }
            });

            panelFotoBoton = new JPanel(new GridLayout(2, 1));
            panelFotoBoton.setBackground(new Color(153, 0, 102));

            Border border = panelFotoBoton.getBorder();
            Border margin = new EmptyBorder(25, 0, -145, 0);
            panelFotoBoton.setBorder(new CompoundBorder(border, margin));

            panelFotoBoton.add(labelImagen);
            panelFotoBoton.add(panelBotonDestino);

            panelArriba.add(panelFotoBoton);
		}

        panel.add(scroll, BorderLayout.NORTH);
        contentPane.setVisible(true);
        panel.setVisible(true);
        contentPane.add(panel);
    }
}