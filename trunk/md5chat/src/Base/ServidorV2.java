import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;

/*
 *  Ejemplo del uso de sockets
 *  Chat version 2
 *  Incluye manejo de servidor concurrente
 *  Atiende a más de un cliente a la , pero ellos no se ven!
 *
 *  @Yalu Galicia
 *  Programación concurrente y paralela 2011
 *
 */

class HijoServer extends Thread {
    private PrintWriter out; //canal atraves del que enviaremos al sockect
    private BufferedReader in; //canal de entrada
    private String inputLine, outputLine;
    private Socket cliente;
    private ArrayList <Socket> clientes;

	public HijoServer(Socket cliente ,ArrayList <Socket> clientes){
                this.cliente = cliente;
                this.clientes=clientes;
	}

	public void run(){
            try
            {
                out = new PrintWriter(cliente.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                outputLine = "Conexion establecida con cliente ";
                out.println(outputLine);

                while ((inputLine = in.readLine()) != null) {
                     outputLine = inputLine;
                    System.out.println(inputLine.toString());
                    for(Socket c:clientes){
                    	out=new PrintWriter(c.getOutputStream(),true);
                    	out.println(outputLine);
                    }
                    
                }

     		System.out.println("Termina servicio a cliente");
         	cliente.close();
	      } catch (IOException ex) { }
	}
}

public class ServidorV2 {
    private int puerto;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ArrayList <Socket> clientes;

//    private PrintWriter out; //canal atraves del que enviaremos al sockect
//    private BufferedReader in; //canal de entrada
//    private String inputLine, outputLine;

    //HijoServer hilo;
    
    public ServidorV2(int puerto){
        this.puerto = puerto;
        serverSocket = null;
        clientes=new ArrayList <Socket>();
        try {
            serverSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            System.err.println("No se puede oir en puerto: "+ puerto);
            System.exit(1);
        }
    }

     public void iniciar() throws IOException{
        System.out.println("Esperando por clientes que se conecten\n");
        clientSocket = null;
        for(;;) { //máximo 5 clientes
        	try {
            	clientSocket = serverSocket.accept();
            	clientes.add(clientSocket);
        	} catch (IOException e) {
                    System.err.println("Fallo Accept.");
                    System.exit(1);
        	}
     		System.out.println("Cliente aceptado con ip: "+clientSocket.getInetAddress().toString()); 
                //arranca un hilo para atender a un cliente especifico y seguir a la
                //escucha de otros
            HijoServer hilo = new HijoServer(clientSocket,clientes );  //le pasa el socket al hilo para la comunicación
        	hilo.start();
        }
    }

    public void terminar() {
        try {
            clientSocket.close();
            serverSocket.close();
        } catch (IOException ex) {
            System.exit(1);
        }
    }
    
    public static void main(String []args) throws IOException{
        
      // ServidorV2 miServ = new ServidorV2(Integer.parseInt(args[0]));
      
        ServidorV2 miServ = new ServidorV2(2000);
        miServ.iniciar();
        miServ.terminar();        
    }
}
