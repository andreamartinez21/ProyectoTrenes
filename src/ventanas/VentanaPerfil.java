package ventanas;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import BD.BD;
import log.Log;

public class VentanaPerfil extends JFrame {

	private static final long serialVersionUID = 1L;
	
	BD bd = new BD();

	private JPanel contentPane;

	private JPanel panel;
	private JPanel panelArriba;
	private JPanel panelAbajo;

	public VentanaPerfil() throws IOException {

		setBackground(new Color(153, 0, 102));

		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 390));
		setVisible(true);
		pack();

		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 0, 102));
		setContentPane(contentPane);

		panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(153, 0, 102));
		panelArriba = new JPanel(new GridLayout(8, 2));
		panelArriba.setBackground(new Color(153, 0, 102));
		panelAbajo = new JPanel(new GridLayout(1, 2));
		panelAbajo.setBackground(new Color(153, 0, 102));

		contentPane.add(panel);

		// labels usuario

		JPanel panelLabelUsuario = new JPanel();
		panelLabelUsuario.setBackground(new Color(153, 0, 102));
		JLabel labelUsuario = new JLabel("Usuario: ");
		labelUsuario.setForeground(Color.WHITE);
		panelLabelUsuario.add(labelUsuario);

		JPanel panelLabelUsuario2 = new JPanel();
		panelLabelUsuario2.setBackground(new Color(153, 0, 102));
		JLabel labelUsuario2 = new JLabel(BD.clienteActual.getUsuario());
		labelUsuario2.setPreferredSize(new Dimension(150, 25));
		labelUsuario2.setForeground(Color.WHITE);
		panelLabelUsuario2.add(labelUsuario2);

		panelArriba.add(panelLabelUsuario);
		panelArriba.add(panelLabelUsuario2);

		// label texto nombre

		JPanel panelLabelNombre = new JPanel();
		panelLabelNombre.setBackground(new Color(153, 0, 102));
		JLabel labelNombre = new JLabel("Nombre: ");
		labelNombre.setForeground(Color.WHITE);
		panelLabelNombre.add(labelNombre);

		JPanel panelTextoNombre = new JPanel();
		panelTextoNombre.setBackground(new Color(153, 0, 102));
		JTextField textoNombre = new JTextField(BD.clienteActual.getNombre());
		textoNombre.setPreferredSize(new Dimension(150, 25));
		panelTextoNombre.add(textoNombre);

		panelArriba.add(panelLabelNombre);
		panelArriba.add(panelTextoNombre);

		// label texto apellido

		JPanel panelLabelApellido = new JPanel();
		panelLabelApellido.setBackground(new Color(153, 0, 102));
		JLabel labelApellido = new JLabel("Apellido: ");
		labelApellido.setForeground(Color.WHITE);
		panelLabelApellido.add(labelApellido);

		JPanel panelTextoApellido = new JPanel();
		panelTextoApellido.setBackground(new Color(153, 0, 102));
		JTextField textoApellido = new JTextField(BD.clienteActual.getApellido());
		textoApellido.setPreferredSize(new Dimension(150, 25));
		panelTextoApellido.add(textoApellido);

		panelArriba.add(panelLabelApellido);
		panelArriba.add(panelTextoApellido);

		// label texto dni

		JPanel panelLabelDni = new JPanel();
		panelLabelDni.setBackground(new Color(153, 0, 102));
		JLabel labelDni = new JLabel("DNI: ");
		labelDni.setForeground(Color.WHITE);
		panelLabelDni.add(labelDni);

		JPanel panelTextoDni = new JPanel();
		panelTextoDni.setBackground(new Color(153, 0, 102));
		JTextField textoDni = new JTextField(BD.clienteActual.getDni());
		textoDni.setPreferredSize(new Dimension(150, 25));
		panelTextoDni.add(textoDni);

		panelArriba.add(panelLabelDni);
		panelArriba.add(panelTextoDni);

		// label texto email

		JPanel panelLabelEmail = new JPanel();
		panelLabelEmail.setBackground(new Color(153, 0, 102));
		JLabel labelEmail = new JLabel("Email: ");
		labelEmail.setForeground(Color.WHITE);
		panelLabelEmail.add(labelEmail);

		JPanel panelTextoEmail = new JPanel();
		panelTextoEmail.setBackground(new Color(153, 0, 102));
		JTextField textoEmail = new JTextField(BD.clienteActual.getEmail());
		textoEmail.setPreferredSize(new Dimension(150, 25));
		panelTextoEmail.add(textoEmail);

		panelArriba.add(panelLabelEmail);
		panelArriba.add(panelTextoEmail);

		// label texto num teléfono

		JPanel panelLabelNumTelefono = new JPanel();
		panelLabelNumTelefono.setBackground(new Color(153, 0, 102));
		JLabel labelNumTelefono = new JLabel("Número de teléfono: ");
		labelNumTelefono.setForeground(Color.WHITE);
		panelLabelNumTelefono.add(labelNumTelefono);

		JPanel panelTextoNumTelefono = new JPanel();
		panelTextoNumTelefono.setBackground(new Color(153, 0, 102));
		JTextField textoNumTelefono = new JTextField(BD.clienteActual.getNumTelefono());
		textoNumTelefono.setPreferredSize(new Dimension(150, 25));
		panelTextoNumTelefono.add(textoNumTelefono);

		panelArriba.add(panelLabelNumTelefono);
		panelArriba.add(panelTextoNumTelefono);

		// label texto cuenta bancaria

		JPanel panelLabelCuentaBancaria = new JPanel();
		panelLabelCuentaBancaria.setBackground(new Color(153, 0, 102));
		JLabel labelCuentaBancaria = new JLabel("Cuenta bancaria: ");
		labelCuentaBancaria.setForeground(Color.WHITE);
		panelLabelCuentaBancaria.add(labelCuentaBancaria);

		JPanel panelTextoCuentaBancaria = new JPanel();
		panelTextoCuentaBancaria.setBackground(new Color(153, 0, 102));
		JTextField textoCuentaBancaria = new JTextField(BD.clienteActual.getCuentaBancaria());
		textoCuentaBancaria.setPreferredSize(new Dimension(150, 25));
		panelTextoCuentaBancaria.add(textoCuentaBancaria);

		panelArriba.add(panelLabelCuentaBancaria);
		panelArriba.add(panelTextoCuentaBancaria);
		
		// labels total gastado

		JPanel panelLabelTotalGastado = new JPanel();
		panelLabelTotalGastado.setBackground(new Color(153, 0, 102));
		JLabel labelTotalGastado = new JLabel("Total gastado: ");
		labelTotalGastado.setForeground(Color.WHITE);
		panelLabelTotalGastado.add(labelTotalGastado);

		JPanel panelLabelTotalGastado2 = new JPanel();
		panelLabelTotalGastado2.setBackground(new Color(153, 0, 102));
		JLabel labelTotalGastado2 = new JLabel(Metodos.sumaGastadoCliente(bd.getBilletesClienteBD(BD.clienteActual), 0) + " €");
		labelTotalGastado2.setPreferredSize(new Dimension(150, 25));
		labelTotalGastado2.setForeground(Color.WHITE);
		panelLabelTotalGastado2.add(labelTotalGastado2);

		panelArriba.add(panelLabelTotalGastado);
		panelArriba.add(panelLabelTotalGastado2);

		// botón volver

		JPanel panelBotonVolver = new JPanel();
		panelBotonVolver.setBackground(new Color(153, 0, 102));
		JButton botonVolver = new JButton("Volver");
		botonVolver.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		botonVolver.setBackground(Color.GRAY);
		botonVolver.setForeground(Color.WHITE);
		botonVolver.setPreferredSize(new Dimension(140, 30));
		panelBotonVolver.add(botonVolver);

		botonVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new VentanaMenuPrincipal();
					dispose();
				} catch (Exception e) {
					Log.logger.log(Level.SEVERE, "No se ha podido volver a la VentanaMenuPrincipal." + e.getStackTrace());
				}
			}
		});

		panelAbajo.add(panelBotonVolver);

		// botón actualizar información

		JPanel panelBotonActualizarInfo = new JPanel();
		panelBotonActualizarInfo.setBackground(new Color(153, 0, 102));
		JButton botonActualizarInfo = new JButton("Actualizar información");
		botonActualizarInfo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		botonActualizarInfo.setBackground(Color.GRAY);
		botonActualizarInfo.setForeground(Color.WHITE);
		botonActualizarInfo.setPreferredSize(new Dimension(165, 30));
		panelBotonActualizarInfo.add(botonActualizarInfo);

		botonActualizarInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Metodos.editarCliente(BD.clienteActual, textoNombre.getText(), textoApellido.getText(), textoDni.getText(),
							textoEmail.getText(), textoNumTelefono.getText(), textoCuentaBancaria.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error");
					Log.logger.log(Level.SEVERE, "Error" + e.getStackTrace());
				}
			}
		});

		panelAbajo.add(panelBotonActualizarInfo);

		Border border = panel.getBorder();
		Border margin = new EmptyBorder(10, 0, 0, 0);
		panel.setBorder(new CompoundBorder(border, margin));

		panel.add(panelArriba, BorderLayout.NORTH);
		panel.add(panelAbajo, BorderLayout.SOUTH);

		contentPane.setVisible(true);
		panel.setVisible(true);
	}
}