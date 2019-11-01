package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import persistencia.Persistencia;
import persistencia.impl.PersistenciaImpl;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;

public class registrosGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnAbrirPlataforma;
	private JButton btnModificarContrasenia;
	private JLabel lblDiasRestantes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registrosGUI frame = new registrosGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public registrosGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addComponent(getLblDiasRestantes(), GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(getBtnModificarContrasenia())
					.addGap(29))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(27)
					.addComponent(getScrollPane(), GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addComponent(getBtnAbrirPlataforma(), GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(48))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(85)
							.addComponent(getBtnAbrirPlataforma(), GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(49)
							.addComponent(getScrollPane(), GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
							.addGap(27)))
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(getLblDiasRestantes(), GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(getBtnModificarContrasenia()))
					.addGap(30))
		);
		panel.setLayout(gl_panel);
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();

			table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Plataforma", "Nombre Usuario"
					}
					));
			scrollPane.setViewportView(table);
		}
		return scrollPane;
	}
	private JButton getBtnAbrirPlataforma() {
		if (btnAbrirPlataforma == null) {
			btnAbrirPlataforma = new JButton("Abrir Plataforma");
		}
		return btnAbrirPlataforma;
	}
	private JButton getBtnModificarContrasenia() {
		if (btnModificarContrasenia == null) {
			btnModificarContrasenia = new JButton("Modificar Contrase\u00F1a Maestra");
			btnModificarContrasenia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new modificarContraseñaGUI("Llene los siguientes campos para cambiar la contraseña").setVisible(true);
					dispose();
				}
			});
		}
		return btnModificarContrasenia;
	}
	private JLabel getLblDiasRestantes() {
		Persistencia persistencia= new PersistenciaImpl();
		try {
			LocalDate fechaCreacionContra;
			fechaCreacionContra = persistencia.leerUsuario().getFechaDeCreacionContraseña();
			LocalDate date=LocalDate.now();
			long diferencia=ChronoUnit.DAYS.between(fechaCreacionContra, date);
			if(diferencia<0)//Just in case xdxdxdddd
			{
				diferencia*=-1;
			}
			if (lblDiasRestantes == null) {
				lblDiasRestantes = new JLabel("Dias restantes de vigencia de\n la contraseña maestra: "+ (7-diferencia)+" dias.");
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lblDiasRestantes;	
		
	}
}
