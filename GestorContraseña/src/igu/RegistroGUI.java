package igu;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import servicio.ServicioRegistro;
import servicio.impl.ServiceRegistroImpl;
import utils.PeticionesWeb;
import utils.Utils;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Registro;
import modelo.Usuario;

import javax.swing.ListSelectionModel;
import java.awt.Toolkit;
import java.awt.Color;
public class RegistroGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnMoficiarContraseaMaestra;
	private JLabel label;
	private String cadena;
	private JTable table;
	private JScrollPane scrollPane;
	private JTable table_1;
	private JButton btnEliminarColumna;
	private JButton btnAbrirPlataforma;
	private JLabel lblParaAbrirUna;
	private JButton btnNewButton;
	private ServicioRegistro servicio;
	private JButton btnEditarRegistro;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public RegistroGUI(ServicioRegistro servicio) {
		this.servicio=servicio;
		setBackground(Color.WHITE);
		setTitle("Gestor Contrase\u00F1as PUJ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroGUI.class.getResource("/img/icon.png")));
		cadena=this.modificarLabel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTable());
		contentPane.add(getScrollPane_1());
		contentPane.add(getBtnEliminarColumna());
		contentPane.add(getLabel());
		contentPane.add(getBtnMoficiarContraseaMaestra());
		contentPane.add(getBtnAbrirPlataforma());
		contentPane.add(getLblParaAbrirUna());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnEditarRegistro());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				PeticionesWeb.BorrarPortapapeles();
				InicioSesion frame;
				frame=new InicioSesion(servicio);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.setVisible(true);    	
			}
		}); 


	}

	private JButton getBtnMoficiarContraseaMaestra() {
		if (btnMoficiarContraseaMaestra == null) {
			btnMoficiarContraseaMaestra = new JButton("Moficiar Contrase\u00F1a Maestra");
			btnMoficiarContraseaMaestra.setBounds(377, 270, 211, 23);
			btnMoficiarContraseaMaestra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ModificarContraseñaGUI mod = new ModificarContraseñaGUI("Llene los siguientes campos para cambiar la contraseña", false, servicio);
					mod.setResizable(false);
					mod.setLocationRelativeTo(null);
					mod.setVisible(true);

					dispose();
				}
			});
		}
		return btnMoficiarContraseaMaestra;
	}
	private JLabel getLabel() {


		if (label == null) {
			label = new JLabel(cadena);
			label.setBounds(10, 270, 380, 23);
		}
		return label;

	}


	private String modificarLabel() {

		try {
			LocalDate fechaCreacionContra;
			fechaCreacionContra = servicio.leerUsuario().getFechaDeCreacionContraseña();
			LocalDate date=LocalDate.now();
			long diferencia=ChronoUnit.DAYS.between(fechaCreacionContra, date);
			if(diferencia<0)
			{
				diferencia*=-1;
			}

			return new String("Dias restantes de vigencia de\n la contraseña maestra: "+ (7-diferencia)+" dias.");

		} catch (IOException e) {
			System.out.println("error" + e.toString());
			e.printStackTrace();
		}
		return null;

	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setBounds(0, 0, 0, 0);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return table;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 54, 360, 183);
			scrollPane.setViewportView(getTable_1());
		}
		return scrollPane;
	}
	private JTable getTable_1() {
		if (table_1 == null) {
			table_1 = new JTable();
			table_1.setModel(llenarTabla());

		}
		table_1.setSelectionMode(0);
		return table_1;
	}
	private DefaultTableModel llenarTabla()
	{
		DefaultTableModel modelo= new DefaultTableModel(){

			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		modelo.addColumn("Nombre de Cuenta");
		modelo.addColumn("Plataforma");
		ServicioRegistro servicio=new ServiceRegistroImpl();

		try {
			for (Registro registro : servicio.leerUsuario().getRegistros()) {
				modelo.addRow(new Object[] {registro.getNombreUsuario(), registro.getURL()});
			}
		} catch (IOException e) {
			System.out.println("error" + e.toString());
			e.printStackTrace();
		}


		return modelo;
	}
	private JButton getBtnEliminarColumna() {
		if (btnEliminarColumna == null) {
			btnEliminarColumna = new JButton("Eliminar Registro");
			btnEliminarColumna.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ServicioRegistro servicio=new ServiceRegistroImpl();

					if(table_1.getSelectedRow()==-1)
					{
						JOptionPane.showMessageDialog(new JFrame(), "¡Selecciona un registro para eliminarlo!", "Error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						try {
							Usuario usuario=servicio.leerUsuario();
							servicio.eliminarRegistro(usuario.getRegistros().get(table_1.getSelectedRow()),usuario);
							actualizarTabla();
						} catch (IOException e) {
							System.out.println("error" + e.toString());
							e.printStackTrace();
						}
					}

				}
			});
			btnEliminarColumna.setBounds(377, 242, 211, 23);
		}
		return btnEliminarColumna;
	}

	private void actualizarTabla()
	{
		//table_1 = new JTable();
		table_1.setModel(llenarTabla());
		table_1.setSelectionMode(0);
		table_1.repaint();
		//contentPane.add(table_1);
	}
	private JButton getBtnAbrirPlataforma() {
		if (btnAbrirPlataforma == null) {
			btnAbrirPlataforma = new JButton("Abrir Plataforma");
			btnAbrirPlataforma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table_1.getSelectedRow()==-1)
					{
						JOptionPane.showMessageDialog(new JFrame(), "¡Selecciona un registro para abrir la cuenta!", "Error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						try {		

							Registro registro=new ServiceRegistroImpl().leerUsuario().getRegistros().get(table_1.getSelectedRow());
							String nombreDeUsuario=registro.getNombreUsuario();
							String url=registro.getURL();
							String contraseña= registro.getContrasena();
							String inputName= registro.getInputName();
							PeticionesWeb.openInBrowser(url, nombreDeUsuario, inputName);
							PeticionesWeb.CopiarPortapapeles(Utils.desencriptar(Utils.desencriptar(contraseña)));

						} catch (Exception e) {
							System.out.println("error" + e.toString());
							e.printStackTrace();
						}
					}
				}
			});
			btnAbrirPlataforma.setBounds(401, 94, 141, 23);
		}
		return btnAbrirPlataforma;
	}
	private JLabel getLblParaAbrirUna() {
		if (lblParaAbrirUna == null) {
			lblParaAbrirUna = new JLabel("Para abrir una cuenta o eliminar un registro, primero seleccionar uno.");
			lblParaAbrirUna.setBounds(10, 11, 489, 40);
		}
		return lblParaAbrirUna;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Agregar Nuevo Registro");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AgregarRegistroGUI ag = new AgregarRegistroGUI(servicio);
					ag.setLocationRelativeTo(null);
					ag.setResizable(false);
					ag.setVisible(true);
					dispose();
				}
			});
			btnNewButton.setBounds(380, 209, 208, 23);
		}
		return btnNewButton;
	}
	private JButton getBtnEditarRegistro() {
		if (btnEditarRegistro == null) {
			btnEditarRegistro = new JButton("Editar Registro");
			btnEditarRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table_1.getSelectedRow()==-1)
					{
						JOptionPane.showMessageDialog(new JFrame(), "¡Selecciona un registro para editarlo!", "Error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						try {		
							ModificarRegistro frame;
							Registro registro=new ServiceRegistroImpl().leerUsuario().getRegistros().get(table_1.getSelectedRow());
							frame=new ModificarRegistro(servicio, registro);
							frame.setLocationRelativeTo(null);
							frame.setResizable(false);
							frame.setVisible(true);	
							dispose();
						} catch (Exception e) {
							System.out.println("error" + e.toString());
							e.printStackTrace();
						}
					}

				}
			});
			btnEditarRegistro.setBounds(401, 148, 143, 23);
		}
		return btnEditarRegistro;
	}
}


