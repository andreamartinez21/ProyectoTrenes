package ventanas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class VentanaGaleria extends JFrame {

    private JPanel contentPane;

    private JScrollPane scroll;
    private JPanel panel;
    private JPanel panelArriba;
    private JPanel panelFotoBoton;
    private JPanel panelFotoBoton2;
    
    JMenuBar menuBar;
	JMenu menu;
	JMenuItem itemInicio, itemGaleria, itemAdmin;

    public VentanaGaleria() throws IOException {

        // bd.connect();

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

        // listaActividades = new ArrayList<Actividad>();
        // listaActividades = bd.getActividades();
       
        double numFotos = /*listaActividades.size()*/ 8;
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(153, 0, 102));
        
        double layoutY = Math.ceil(numFotos / 2);
        panelArriba = new JPanel(new GridLayout((int)layoutY, 2));
        panelArriba.setBackground(new Color(153, 0, 102));
        
        scroll = new JScrollPane(panelArriba);
        scroll.setPreferredSize(new Dimension(990, 600));
        scroll.setBackground(new Color(153, 0, 102));

        for (int i = 0; i < numFotos; i++) {
            // Actividad actividad = listaActividades.get(i);
            
            BufferedImage bufferedImage = ImageIO.read(new File("src/img/img" + String.valueOf(i) + ".png"));
            Image image = bufferedImage.getScaledInstance(400, 200, Image.SCALE_DEFAULT);

            JLabel labelImagen = new JLabel(new ImageIcon(image));

            JPanel panelBotonActividad = new JPanel();
            panelBotonActividad.setBackground(new Color(153, 0, 102));
            JButton botonActividad = new JButton("Destino " + i/*actividad.getNombre() + " - " + actividad.getUbicacion()*/);
            botonActividad.setPreferredSize(new Dimension(200, 30));
            botonActividad.setBackground(Color.GRAY);
            botonActividad.setForeground(Color.WHITE);
            panelBotonActividad.add(botonActividad);

            // botonActividad.addActionListener(new ActionListener() {

            //     @Override
            //     public void actionPerformed(ActionEvent arg0) {
            //         // TODO Auto-generated method stub
            //         try {
            //             new VentanaGaleriaEspecifica(actividad);
            //             dispose();
            //         } catch (Exception e) {
            //             // TODO Auto-generated catch block
            //         	bd.ficheroLogger();
            //             bd.logger.log(Level.INFO, "No se puede abrir la ventana");
            //             bd.closeLogger();
            //         }
            //     }
            // });

            panelFotoBoton = new JPanel(new GridLayout(2, 1));
            panelFotoBoton.setBackground(new Color(153, 0, 102));

            Border border = panelFotoBoton.getBorder();
            Border margin = new EmptyBorder(25, 0, -145, 0);
            panelFotoBoton.setBorder(new CompoundBorder(border, margin));

            panelFotoBoton.add(labelImagen);
            panelFotoBoton.add(panelBotonActividad);

            panelArriba.add(panelFotoBoton);
        }

        JPanel panelBotonVolver = new JPanel();
        panelBotonVolver.setBackground(new Color(153, 0, 102));
        JButton botonVolver = new JButton("Volver");
        botonVolver.setPreferredSize(new Dimension(200, 30));
        botonVolver.setBackground(Color.GRAY);
        botonVolver.setForeground(Color.WHITE);
        panelBotonVolver.add(botonVolver);

        botonVolver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new VentanaInicio();
                    dispose();
                } catch (Exception e1) {
                	// bd.ficheroLogger();
                    // bd.logger.log(Level.INFO, "No se puede abrir la ventana");
                    // bd.closeLogger();
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        panel.add(scroll, BorderLayout.NORTH);
        contentPane.setVisible(true);
        panel.setVisible(true);
        contentPane.add(panel);
    }

    public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
		 public void run() {
		  try {
		   VentanaGaleria frame = new VentanaGaleria();
		   frame.setVisible(true);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
		});
	}
}