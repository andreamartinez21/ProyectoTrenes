package ventanas;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaVerViajes extends JFrame{

    private JPanel contentPane;

    private JPanel panel;
    private JPanel panelArriba;
    private JPanel panelAbajo;
    
	public VentanaVerViajes() throws IOException {
    	
        setBackground(new Color(0, 0, 51));

        setTitle("Ver mis viajes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 350));
        setVisible(true);
        pack();

        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 0, 102));
        setContentPane(contentPane);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(153, 0, 102));
//      panelArriba = new JPanel(new GridLayout(viajes.length, 1));
        panelArriba = new JPanel(new BorderLayout());
        panelArriba.setBackground(new Color(153, 0, 102));
        panelAbajo = new JPanel(new BorderLayout());
        panelAbajo.setBackground(new Color(153, 0, 102));

        contentPane.add(panel);
        
        // JTable
        
        // array con título tabla
        String[] columns = new String[] {
            "ORIGEN", "DESTINO", "IDA Y VUELTA", "PRECIO"
        };
         
        // array con datos tabla
        
//        BD.getBilletesUsuarioBD(BD.clienteActual);
//        List<Billete> listaBilletesUsuario = new ArrayList<Billete>();
//        listaBilletesUsuario = BD.clienteActual.getListaBilletes();
        
        Object[][] data = new Object[][] {
            {/*listaBilletesUsuario.get(0).getViajeIda().getOrigen()*/ "Bilbao", "Málaga", "No", 150},
            {"Bilbao", "París", "Sí", 80},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
            {"Madrid", "Londres", "No", 55},
        };
        
        // crear tabla
        JTable table = new JTable(data, columns);
        
        for(int i = 0; i < data.length; i++) {
        	table.setRowHeight(i, 30);
        }
        
//      table.setBackground(Color.GRAY);
        
        // poner la tabla no editable
         
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(450, 220));
        panelArriba.add(scrollPane);
        
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
        botonVolver.setPreferredSize(new Dimension(160, 35));
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

        //

        panel.add(panelArriba, BorderLayout.NORTH);
        panel.add(panelAbajo, BorderLayout.SOUTH);

        contentPane.setVisible(true);
        panel.setVisible(true);
    }

    public static void main(String[] args) throws IOException {

		new VentanaVerViajes();
	}
}