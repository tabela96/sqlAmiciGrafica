package sqlAmici;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;

public class Main {
	private static Text txtCognome;
	private static Text txtNome;
	private static Text txtDataNascita;
	private static List list;
	private static ArrayList<Integer> id = new ArrayList<Integer>();
	private static int eliminati = 0;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {
		Display display = Display.getDefault();
		Shell shlDatabaseGrafica = new Shell();
		shlDatabaseGrafica.setSize(450, 300);
		shlDatabaseGrafica.setText("Database Grafica");

		// ________________________________query
		// cn.close(); // chiusura connessione

		list = new List(shlDatabaseGrafica, SWT.BORDER);
		list.setBounds(10, 42, 148, 171);

		Label lblAmici = new Label(shlDatabaseGrafica, SWT.NONE);
		lblAmici.setBounds(66, 10, 55, 15);
		lblAmici.setText("Amici");

		Button btnNewButton = new Button(shlDatabaseGrafica, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list.removeAll();
				Connection cn = null;
				Statement st;
				ResultSet rs = null;
				String sql;
				// ________________________________connessione
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("ClassNotFoundException: ");
					System.err.println(e1.getMessage());
				} // fine try-catch
				/* ho modificato qui */
				try {
					cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/amici?user=root&password=");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// jdbc:mysql://localhost:3306/Contatti?user=root&password=secret
				sql = "SELECT * FROM amici;"; // qui seleziono la tabella
				try {
					st = cn.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next() == true) {
						list.add(rs.getString("cognome"));
						id.add(Integer.parseInt(rs.getString("id")));
					}

				} catch (SQLException e1) {
					System.out.println("errore:" + e1.getMessage());
				} // fine try-catch
			}
		});
		btnNewButton.setBounds(10, 227, 148, 25);
		btnNewButton.setText("Mostra lista");

		txtCognome = new Text(shlDatabaseGrafica, SWT.BORDER);
		txtCognome.setBounds(321, 42, 103, 21);

		txtNome = new Text(shlDatabaseGrafica, SWT.BORDER);
		txtNome.setBounds(321, 79, 103, 21);

		Label lblCognome = new Label(shlDatabaseGrafica, SWT.NONE);
		lblCognome.setBounds(260, 42, 55, 15);
		lblCognome.setText("cognome");

		Label lblNome = new Label(shlDatabaseGrafica, SWT.NONE);
		lblNome.setBounds(260, 79, 55, 15);
		lblNome.setText("nome");

		txtDataNascita = new Text(shlDatabaseGrafica, SWT.BORDER);
		txtDataNascita.setBounds(321, 118, 103, 21);

		Label lblDataNascita = new Label(shlDatabaseGrafica, SWT.NONE);
		lblDataNascita.setBounds(260, 124, 55, 15);
		lblDataNascita.setText("nascita");

		Button btnInserisci = new Button(shlDatabaseGrafica, SWT.NONE);
		btnInserisci.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Connection cn = null;
				Statement st;
				int rs;
				String sql;
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("ClassNotFoundException: ");
					System.err.println(e1.getMessage());
				} // fine try-catch
					// jdbc:mysql://localhost:3306/Contatti?user=root&password=secret
				if (txtCognome.getText() != "" || txtNome.getText() != "" || txtDataNascita.getText() != "") {
					try {
						cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/amici?user=root&password=");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list.add(txtCognome.getText());
					sql = "INSERT INTO amici (cognome, nome, dataNascita) VALUES ('" + txtCognome.getText() + "', '"
							+ txtNome.getText() + "', '" + txtDataNascita.getText() + "')";
					System.out.println(sql);
					txtCognome.setText("");
					txtDataNascita.setText("");
					txtNome.setText("");
					try {
						st = cn.createStatement();
						rs = st.executeUpdate(sql);
						cn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Compila i campi", "ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnInserisci.setBounds(190, 188, 66, 25);
		btnInserisci.setText("Inserisci");

		Label lblAaaammgg = new Label(shlDatabaseGrafica, SWT.NONE);
		lblAaaammgg.setBounds(321, 145, 103, 15);
		lblAaaammgg.setText("aaaa-mm-gg");

		Button btnElimina = new Button(shlDatabaseGrafica, SWT.NONE);
		btnElimina.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Connection cn = null;
				Statement st;
				int rs;
				String sql;
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("ClassNotFoundException: ");
					System.err.println(e1.getMessage());
				} // fine try-catch
					// jdbc:mysql://localhost:3306/Contatti?user=root&password=secret
				if (list.getSelectionIndex() != -1) {
					try {
						cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/amici?user=root&password=");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int i = list.getSelectionIndex();
					String controllaCognome = "";
					controllaCognome = list.getItem(i);
					sql = "DELETE FROM amici WHERE cognome='" + controllaCognome + "';";
					System.out.println(sql);

					try {
						st = cn.createStatement();
						rs = st.executeUpdate(sql);
						eliminati++;
						cn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleziona amico da cancellare", "ATTENZIONE!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnElimina.setBounds(277, 188, 66, 25);
		btnElimina.setText("Elimina");

		Button btnModifica = new Button(shlDatabaseGrafica, SWT.NONE);
		btnModifica.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Connection cn = null;
				Statement st;
				int rs;
				String sql = "";
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("ClassNotFoundException: ");
					System.err.println(e1.getMessage());
				} // fine try-catch
				if (txtCognome.getText() != "" || txtNome.getText() != "" || txtDataNascita.getText() != "") {
					try {
						cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/amici?user=root&password=");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String cognome = "";
					String nome = "";
					String data = "";
					cognome = txtCognome.getText();
					nome = txtNome.getText();
					data = txtDataNascita.getText();
					int i = list.getSelectionIndex();
					for (int j = 0; j < id.size(); j++) {
						if (i == id.get(j)) {
							sql = "UPDATE amici SET cognome='" + cognome + "', nome='" + nome + "', dataNascita='"
									+ data + "' WHERE id=" + (i + eliminati) + ";";
							System.out.println(sql);
						}
					}
					txtCognome.setText("");
					txtDataNascita.setText("");
					txtNome.setText("");
					try {
						st = cn.createStatement();
						rs = st.executeUpdate(sql);
						cn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Compila i campi", "ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModifica.setBounds(349, 188, 66, 25);
		btnModifica.setText("Modifica");
		
		shlDatabaseGrafica.open();
		shlDatabaseGrafica.layout();
		while (!shlDatabaseGrafica.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
