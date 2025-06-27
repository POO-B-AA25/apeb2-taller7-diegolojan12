import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Problema5_Teatro {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        String[] nombres = {"Juan", "María", "Carlos", "Ana", "Luis", "Laura", "Pedro", "Sofía"};
        ArrayList<Entrada> entradasVendidas = new ArrayList<>();
        ArrayList<Zona> zonas = new ArrayList<>();

        zonas.add(new Zona("Principal", 200, 25, 17.5));
        zonas.add(new Zona("PalcoB", 40, 70, 40));
        zonas.add(new Zona("Central", 400, 20, 14));
        zonas.add(new Zona("Lateral", 100, 15.5, 10));

        int idEntrada = 0;
        String continuar = "S";

        do {
            System.out.println("\nSeleccione la zona:");
            for (int i = 0; i < zonas.size(); i++) {
                System.out.println((i + 1) + ". " + zonas.get(i).nombre);
            }
            
            int opcionZona;
            do {
                System.out.print("Opción: ");
                opcionZona = sc.nextInt();
                sc.nextLine(); 
                System.out.println();
                if (opcionZona < 1 || opcionZona > zonas.size()) {
                System.out.println("Zona inválida.");
                }
            } while (opcionZona > 4 || opcionZona < 0);
            
            Zona zonaSeleccionada = zonas.get(opcionZona - 1);
            System.out.println(zonaSeleccionada);
            if (!zonaSeleccionada.hayDisponibilidad()) {
                System.out.println("La zona está completa.");
            }

            String comprador = nombres[random.nextInt(nombres.length)];
            System.out.println("Nombre del comprador: " + comprador);

            System.out.println("Tipo de entrada:");
            System.out.println("1. Normal");
            System.out.println("2. Reducida (Estudiante/Pensionista)");
            System.out.println("3. Abonado");
            System.out.print("Opción: ");
            int tipoEntrada = sc.nextInt();
            sc.nextLine();


            switch (tipoEntrada) {
                case 1 -> {
                    EntradaNormal entrada = new EntradaNormal(idEntrada, comprador, zonaSeleccionada, 0);
                    entrada.calcularPrecio();
                    zonaSeleccionada.vender();
                    entradasVendidas.add(entrada);
                    System.out.println(entrada);
                    idEntrada++;
                }case 2 ->{
                    EntradaReducida entrada = new EntradaReducida(idEntrada, comprador, zonaSeleccionada, 0);
                    entrada.calcularPrecio();
                    zonaSeleccionada.vender();
                    entradasVendidas.add(entrada);
                    System.out.println(entrada);
                    idEntrada++;
                } case 3 -> {
                    EntradaAbonado entrada = new EntradaAbonado(idEntrada, comprador, zonaSeleccionada, 0);
                    entrada.calcularPrecio();
                    zonaSeleccionada.vender();
                    entradasVendidas.add(entrada);
                    System.out.println(entrada);
                    idEntrada++;
                } default -> {
                    System.out.println("Tipo inválido.");
                    continue;
                }
            }

            

            System.out.println("\n¿Desea consultar una entrada? (S/N)");
            String consultar = sc.nextLine().toUpperCase();
            if (consultar.equals("S")) {
                System.out.print("Ingrese ID de entrada: ");
                int idBuscar = sc.nextInt();
                sc.nextLine();

                boolean encontrada = false;
                for (Entrada e : entradasVendidas) {
                    if (e.id == idBuscar) {
                        System.out.println("Encontada: " + e);
                        encontrada = true;
                        break;
                    }
                }

                if (!encontrada) {
                    System.out.println("No existe una entrada con ese ID.");
                }
            }

            System.out.println("\n¿Desea vender otra entrada? (S/N)");
            continuar = sc.nextLine();

        } while (continuar.equals("S"));

        System.out.println("\nLista final de entradas vendidas ");
        for (Entrada e : entradasVendidas) {
            System.out.println(e);
        }

        System.out.println("\nPrograma finalizado.");
    }
}

class Zona {
    public String nombre;
    public int capacidad;
    public int ocupadas;
    public double precioNormal;
    public double precioAbonado;

    public Zona(String nombre, int capacidad, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }

    public boolean hayDisponibilidad() {
        return ocupadas < capacidad;
    }

    public void vender() {
        ocupadas++;
    }

    @Override
    public String toString() {
        return "Zona{" + "nombre=" + nombre + ", capacidad=" + capacidad + ", ocupadas=" + ocupadas + ", precioNormal=" + precioNormal + ", precioAbonado=" + precioAbonado + '}';
    }
    
}

class Entrada {
    public int id;
    public String comprador;
    public Zona zona;
    public double precio;
    
    public Entrada(){}

    public Entrada(int id, String comprador, Zona zona, double precio) {
        this.id = id;
        this.comprador = comprador;
        this.zona = zona;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Entrada{" + "id=" + id + ", comprador=" + comprador + ", zona=" + zona + ", precio=" + precio + '}';
    }

}

class EntradaNormal extends Entrada {

    public EntradaNormal(int id, String comprador, Zona zona, double precio) {
        super(id, comprador, zona, precio);
    }

    public void calcularPrecio() {
        this.precio = zona.precioNormal;
    }

    @Override
    public String toString() {
        return "EntradaNormal{}" + super.toString();
    }
}

class EntradaReducida extends Entrada {
    public EntradaReducida(int id, String comprador, Zona zona, double precio) {
        super(id, comprador, zona, precio);
    }

    public void calcularPrecio() {
        this.precio = zona.precioNormal * 0.85;
    }

    @Override
    public String toString() {
        return "EntradaReducida{}" + super.toString();
    }
}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(int id, String comprador, Zona zona, double precio) {
        super(id, comprador, zona, precio);
    }

    public void calcularPrecio() {
        this.precio = zona.precioAbonado;
    }

    @Override
    public String toString() {
        return "EntradaAbonado{}" + super.toString();
    }
}