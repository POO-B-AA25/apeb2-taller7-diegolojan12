
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Problema3_Mensajes {
    public static void main (String args[]) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        
        String[] nombres = {"Juan", "María", "Carlos", "Ana", "Luis", "Laura", "Pedro", "Sofía"};
        String[] mensajesTexto = {"Hola, ¿cómo estás?", "Te llamo más tarde", "Nos vemos a las 8", 
                                 "Feliz cumpleaños!", "Confirmado el meeting", "LLegaré tarde"};
        String[] imagenes = {"foto1.jpg", "vacaciones.png", "memes.gif", "documento.pdf", "captura.png"};
        Movil miMovil = new Movil();
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        
        char opcion;
        do {
            System.out.println("\n--- Sistema de Mensajería Móvil ---");
            System.out.println("1. Enviar mensaje de texto (SMS)");
            System.out.println("2. Enviar mensaje con imagen (MMS)");
            System.out.println("3. Visualizar todos los mensajes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.next().charAt(0);
            sc.nextLine(); 
            
            switch(opcion) {
                case '1':
                    int numRemSMS = 600000000 + random.nextInt(100000000);
                    int numDesSMS = 600000000 + random.nextInt(100000000);
                    String nombreRemSMS = nombres[random.nextInt(nombres.length)];
                    String nombreDesSMS = nombres[random.nextInt(nombres.length)];
                    String texto = mensajesTexto[random.nextInt(mensajesTexto.length)];
                    
                    MensajeTexto sms = new MensajeTexto(texto, numRemSMS, numDesSMS, nombreRemSMS, nombreDesSMS);
                    listaMensajes.add(sms);
                    miMovil.agregarMensaje(sms);
                    System.out.println("\nMensaje de texto enviado: " + sms);
                    break;
                    
                case '2':
                    int numRemMMS = 600000000 + random.nextInt(100000000);
                    int numDesMMS = 600000000 + random.nextInt(100000000);
                    String nombreRemMMS = nombres[random.nextInt(nombres.length)];
                    String nombreDesMMS = nombres[random.nextInt(nombres.length)];
                    String imagen = imagenes[random.nextInt(imagenes.length)];     
                    MensajeImagen mms = new MensajeImagen(imagen, numRemMMS, numDesMMS, nombreRemMMS, nombreDesMMS);
                    listaMensajes.add(mms);
                    miMovil.agregarMensaje(mms);
                    System.out.println("\nMensaje con imagen enviado: " + mms);
                    break;
                    
                case '3':
                    System.out.println("\n--- Lista de Mensajes ---");
                    System.out.println(miMovil);
                    break;
                    
                case '4':
                    System.out.println("Saliendo del programa...");
                    break;
                    
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            
        } while(opcion != '4');
    }
}

class Mensaje {
    public int numRemitente;
    public int numDestinatario;
    public String nombreRem;
    public String nombreDes;

    public Mensaje() { }

    public Mensaje(int numRemitente, int numDestinatario) {
        this.numRemitente = numRemitente;
        this.numDestinatario = numDestinatario;
    }

    public Mensaje(int numRemitente, int numDestinatario, String nombreRem, String nombreDes) {
        this.numRemitente = numRemitente;
        this.numDestinatario = numDestinatario;
        this.nombreRem = nombreRem;
        this.nombreDes = nombreDes;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "numRemitente=" + numRemitente + ", numDestinatario=" + numDestinatario + ", nombreRem=" + nombreRem + ", nombreDes=" + nombreDes + '}';
    }
    
    
    
}

class MensajeTexto extends Mensaje {
    public String infoMensaje;

    public MensajeTexto(String infoMensaje, int numRemitente, int numDestinatario, String nombreRem, String nombreDes) {
        super(numRemitente, numDestinatario, nombreRem, nombreDes);
        this.infoMensaje = infoMensaje;
    }

    @Override
    public String toString() {
        return "MensajeTexto{" + "infoMensaje=" + infoMensaje + '}' + super.toString();
    }
    
    
    
}

class MensajeImagen extends Mensaje {
    public String nombreFichero;

    public MensajeImagen(String nombreFichero, int numRemitente, int numDestinatario, String nombreRem, String nombreDes) {
        super(numRemitente, numDestinatario, nombreRem, nombreDes);
        this.nombreFichero = nombreFichero;
    }

    @Override
    public String toString() {
        return "MensajeImagen{" + "nombreFichero=" + nombreFichero + '}' + super.toString();
    }
    
    
    
}

class Movil {
    private ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();

    public Movil() {}

    public void agregarMensaje(Mensaje m) {
        mensajes.add(m);
    }

    @Override
    public String toString() {
        return "Movil{" + "mensajes=" + mensajes + '}';
    }


}