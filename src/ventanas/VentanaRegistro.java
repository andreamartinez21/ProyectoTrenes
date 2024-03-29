package ventanas;

import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import log.Log;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JPanel panel;
	private JPanel panelArriba;
	private JPanel panelMedio;
	private JPanel panelAbajo;

	public VentanaRegistro() throws IOException {

		setBackground(new Color(153, 0, 102));

		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 399));
		setVisible(true);
		pack();

		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 0, 102));
		setContentPane(contentPane);

		panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(153, 0, 102));
		panelArriba = new JPanel(new GridLayout(8, 2));
		panelArriba.setBackground(new Color(153, 0, 102));
		panelMedio = new JPanel(new GridLayout(1, 2));
		panelMedio.setBackground(new Color(153, 0, 102));
		panelAbajo = new JPanel(new BorderLayout());

		contentPane.add(panel);

		// label texto nombre

		JPanel panelLabelNombre = new JPanel();
		panelLabelNombre.setBackground(new Color(153, 0, 102));
		JLabel labelNombre = new JLabel("Nombre: ");
		labelNombre.setForeground(Color.WHITE);
		panelLabelNombre.add(labelNombre);

		JPanel panelTextoNombre = new JPanel();
		panelTextoNombre.setBackground(new Color(153, 0, 102));
		JTextField textoNombre = new JTextField();
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
		JTextField textoApellido = new JTextField();
		textoApellido.setPreferredSize(new Dimension(150, 25));
		panelTextoApellido.add(textoApellido);

		panelArriba.add(panelLabelApellido);
		panelArriba.add(panelTextoApellido);

		// label texto usuario

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

		// label texto contrasenya

		JPanel panelLabelContrasenya = new JPanel();
		panelLabelContrasenya.setBackground(new Color(153, 0, 102));
		JLabel labelContrasenya = new JLabel("Contrase�a: ");
		labelContrasenya.setForeground(Color.WHITE);
		panelLabelContrasenya.add(labelContrasenya);

		JPanel panelTextoContrasenya = new JPanel();
		panelTextoContrasenya.setBackground(new Color(153, 0, 102));
		JTextField textoContrasenya = new JTextField();
		textoContrasenya.setPreferredSize(new Dimension(150, 25));
		panelTextoContrasenya.add(textoContrasenya);

		panelArriba.add(panelLabelContrasenya);
		panelArriba.add(panelTextoContrasenya);

		// label texto dni

		JPanel panelLabelDni = new JPanel();
		panelLabelDni.setBackground(new Color(153, 0, 102));
		JLabel labelDni = new JLabel("DNI: ");
		labelDni.setForeground(Color.WHITE);
		panelLabelDni.add(labelDni);

		JPanel panelTextoDni = new JPanel();
		panelTextoDni.setBackground(new Color(153, 0, 102));
		JTextField textoDni = new JTextField();
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
		JTextField textoEmail = new JTextField();
		textoEmail.setPreferredSize(new Dimension(150, 25));
		panelTextoEmail.add(textoEmail);

		panelArriba.add(panelLabelEmail);
		panelArriba.add(panelTextoEmail);

		// label texto num tel�fono

		JPanel panelLabelNumTelefono = new JPanel();
		panelLabelNumTelefono.setBackground(new Color(153, 0, 102));
		JLabel labelNumTelefono = new JLabel("N�mero de tel�fono: ");
		labelNumTelefono.setForeground(Color.WHITE);
		panelLabelNumTelefono.add(labelNumTelefono);

		JPanel panelTextoNumTelefono = new JPanel();
		panelTextoNumTelefono.setBackground(new Color(153, 0, 102));
		JTextField textoNumTelefono = new JTextField();
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
		JTextField textoCuentaBancaria = new JTextField();
		textoCuentaBancaria.setPreferredSize(new Dimension(150, 25));
		panelTextoCuentaBancaria.add(textoCuentaBancaria);

		panelArriba.add(panelLabelCuentaBancaria);
		panelArriba.add(panelTextoCuentaBancaria);
		
		// progress bar
		
		JPanel panelProgressBar = new JPanel();
		panelProgressBar.setBackground(new Color(153, 0, 102));
		JProgressBar progressBar = new JProgressBar();
		panelProgressBar.add(progressBar);
		panelAbajo.add(panelProgressBar);
		progressBar.setVisible(false);

		// bot�n volver

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
					Log.logger.log(Level.SEVERE, "No se ha podido volver a la VentanaInicio." + e.getStackTrace());
				}
			}
		});

		// bot�n registrarme

		JPanel panelBotonRegistrarme = new JPanel();
		panelBotonRegistrarme.setBackground(new Color(153, 0, 102));
		JButton botonRegistrarme = new JButton("Registrarme");
		botonRegistrarme.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		botonRegistrarme.setBackground(Color.GRAY);
		botonRegistrarme.setForeground(Color.WHITE);
		botonRegistrarme.setPreferredSize(new Dimension(150, 30));
		panelBotonRegistrarme.add(botonRegistrarme);

		botonRegistrarme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (Metodos.register(textoNombre.getText(), textoApellido.getText(), textoUsuario.getText(),
							textoContrasenya.getText(), textoDni.getText(), textoEmail.getText(),
							textoNumTelefono.getText(), textoCuentaBancaria.getText())) {

						// hilo

						Thread t = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {
									for (int i = 0; i < 100; i++) {
										progressBar.setValue(i);
										Thread.sleep(9);
									}
								} catch (Exception e2) {
									Log.logger.log(Level.SEVERE, "No se ha podido ejecutar el hilo." + e2.getStackTrace());
								}
							}
						});
						t.start();
						progressBar.setVisible(true);

						JOptionPane.showMessageDialog(null, "Se ha registrado correctamente.");
						Log.logger.log(Level.INFO, "Se ha registrado correctamente.");
						new VentanaInicio();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Ha habido un error en el registo.");
						Log.logger.log(Level.SEVERE, "Ha habido un error en el registo.");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error");
					Log.logger.log(Level.SEVERE, "Error." + e.getStackTrace());
				}
			}
		});

		panelMedio.add(panelBotonVolver);
		panelMedio.add(panelBotonRegistrarme);

		panel.add(panelArriba, BorderLayout.NORTH);
		panel.add(panelMedio, BorderLayout.CENTER);
		panel.add(panelAbajo, BorderLayout.SOUTH);
		
		Border border = panel.getBorder();
		Border margin = new EmptyBorder(13, 0, 0, 0);
		panel.setBorder(new CompoundBorder(border, margin));
		
		contentPane.setVisible(true);
		panel.setVisible(true);
	}
}