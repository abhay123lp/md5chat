
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 *  Ejemplo del uso de sockets
 *  Chat version 2
 *  Incluye manejo de servidor concurrente
 *  Atiende a más de un cliente a la , pero ellos no se ven!
 *
 *  @Yalu Galicia
 *  Programación concurrente y paralela 2011
 */

public class ClienteV2 {
    private int puerto;
    private String host;
    private Socket kkSocket;
    private PrintWriter out;
    private BufferedReader in;
    String nick;

    public ClienteV2 (String host, int puerto, String nick) throws IOException
    {
         kkSocket = null;
         out = null;
         in = null;
         this.puerto = puerto;
         this.host = host;
         this.nick = nick;
        try {
            kkSocket = new Socket(host, puerto);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host: "+host+" desconocido.");
            System.exit(1);
        }
    }
    
    public void sendInfo(String cadena) {
        		    
            String fromUser = nick+": "+cadena;
	    if (fromUser != null) {
                System.out.println(fromUser);
                out.println(fromUser); //se envia al servidor
	    }
    }
    
    public String receiveInfo() throws IOException{
        String fromServer=null;

        if ((fromServer = in.readLine()) != null) {  //recibe del servidor
            System.out.println("Server: " + fromServer);
        }	    
        return fromServer;
    }   
        
    public void cerrar() throws IOException{
        out.close();
        in.close();
        kkSocket.close();
    }
}