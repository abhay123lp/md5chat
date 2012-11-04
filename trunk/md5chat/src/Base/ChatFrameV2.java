
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*
 *  Ejemplo del uso de sockets
 *  Chat version 1
 *  No maneja servidor concurrente
 *  Atiende a más de un cliente a la , pero ellos no se ven!
 *
 *  @Yalu Galicia
 *  Programación concurrente y paralela 2011
 *
 */
class Escucha extends Thread{
	private ClienteV2 cliente;
	private JTextArea txaDialogo;

	public Escucha(ClienteV2 cliente,JTextArea txaDialogo){
		this.cliente=cliente;
		this.txaDialogo=txaDialogo;
	}

	public void run(){
		while(true){
			try {
				//
				String cadena = cliente.receiveInfo();
				txaDialogo.append(cadena +"\n");
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Cadena null desde el servidor");
			}
		}

	}
}
public class ChatFrameV2 extends JFrame{
    private JTextArea txaDialogo;
    private JScrollPane scroll;
    private JLabel lbMensaje;
    private JTextField txtMensaje;
    private JButton btConectar, btDesconectar;
    private JPanel pBotones, pMensaje;

    //
    private ClienteV2 cliente;

    //

    private Escucha e;
    private String host;
    private int puerto;
    private String nick;

    public ChatFrameV2(String host, int puerto){
        super("Chat usando sockets - DEMO");
        setLayout(new BorderLayout(2,2));
        setLocation(150, 150);

        this.host = host;
        this.puerto = puerto;
        iniComponentes(); //inicializa componentes
        pack();

    }

    private void iniComponentes(){
        pMensaje = new JPanel();
        pBotones = new JPanel(new GridLayout(5,1, 2,2));

		e=new Escucha(cliente,txaDialogo);
		e.start();
        lbMensaje = new JLabel("Mensaje:");
        txtMensaje = new JTextField("", 25);

        txtMensaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    String cadena=null;
                    cadena = txtMensaje.getText();
                    //
                    cliente.sendInfo(cadena);
                    txtMensaje.setText("");

                    try {
                    	//
                        cadena = cliente.receiveInfo();
                        txaDialogo.append(cadena +"\n");
                   } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Cadena null desde el servidor");
                    }
            }
        });

        pMensaje.add(lbMensaje);
        pMensaje.add(txtMensaje);

        txaDialogo = new JTextArea("", 20, 18);
        txaDialogo.setEditable(false);
        scroll = new JScrollPane(txaDialogo);

        btConectar = new JButton("Conectar");
        btConectar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               establecerConexion();
               btConectar.setEnabled(false);
           }
        });

        btDesconectar = new JButton("Salir");
        btDesconectar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
                    terminarConexion();
           }});

        pBotones.add(btConectar);
        pBotones.add(btDesconectar);

        //agrega componetes al panel
        add(scroll,BorderLayout.CENTER );
    	add(pBotones, BorderLayout.LINE_END);
        add(pMensaje, BorderLayout.PAGE_END);
    }


    public void establecerConexion() {
        nick = JOptionPane.showInputDialog("Introduce tu nickName");
        //se crea un cliente con los datos esperados
        try {
            cliente = new ClienteV2(host, puerto, nick);

            String cadena = cliente.receiveInfo(); //lee primero del servidor
            txaDialogo.setText(cadena+"\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cadena inicial no recibida desde el servidor");
        }
    }

    public  void terminarConexion(){
        try {
        cliente.cerrar();
       } catch (Exception ex) {}
        System.exit(0);
    }

    public static void main(String [] args){

        String host = "localhost";//"148.228.23.114";//args[0];
        int puerto = 2000; //Integer.parseInt(args[1]);

        ChatFrameV2 miFrame = new ChatFrameV2(host, puerto);
        miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miFrame.setVisible(true);
    }
}