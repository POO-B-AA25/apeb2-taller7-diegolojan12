
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Problema4_Trabajador {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        String[] nombres = {"Juan", "María", "Carlos", "Ana", "Luis", "Laura", "Pedro", "Sofía"};
        String[] apellidos = {"García", "Rodríguez", "González", "Fernández", "López", "Martínez", "Sánchez"};
        String[] direcciones = {"Calle Principal 123", "Avenida Libertad 45", "Plaza Central 7",
            "Calle Secundaria 89", "Boulevard Norte 12"};

        ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();
        ArrayList<Jefe> listaJefes = new ArrayList<>();

        String continuarJefes;

        do {
            // CREAR UN NUEVO JEFE
            String nombrej = nombres[random.nextInt(nombres.length)];
            String apellidoj = apellidos[random.nextInt(apellidos.length)] + " " + apellidos[random.nextInt(apellidos.length)];
            String direccionj = direcciones[random.nextInt(direcciones.length)];
            int dnij = 10000000 + random.nextInt(90000000);
            double sueldoJefe = 3000 + random.nextDouble() * 2000;

            Jefe jefe = new Jefe(sueldoJefe, nombrej, apellidoj, direccionj, dnij);
            listaJefes.add(jefe);
            listaTrabajadores.add(jefe);

            System.out.println("\nJefe añadido: " + jefe);

            String continuarTrabajadores;
            do {
                System.out.println("\n¿Deseas añadir un trabajador para este jefe? (S/N)");
                continuarTrabajadores = sc.nextLine().toUpperCase();

                if (continuarTrabajadores.equals("S")) {
                    String nombre = nombres[random.nextInt(nombres.length)];
                    String apellido = apellidos[random.nextInt(apellidos.length)] + " " + apellidos[random.nextInt(apellidos.length)];
                    String direccion = direcciones[random.nextInt(direcciones.length)];
                    int dni = 10000000 + random.nextInt(90000000);

                    System.out.println("\nSeleccione el tipo de trabajador:");
                    System.out.println("1. Trabajador Fijo Mensual");
                    System.out.println("2. Trabajador por Comisión");
                    System.out.println("3. Trabajador por Horas");
                    System.out.print("Opción: ");
                    int tipoTrabajador = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    switch (tipoTrabajador) {
                        case 1 -> {
                            double sueldo = 1500 + random.nextDouble() * 1500;
                            TrabajadorFijoMensual tfm = new TrabajadorFijoMensual(sueldo, nombre, apellido, direccion, dni, jefe);
                            listaTrabajadores.add(tfm);
                            System.out.println("Trabajador Fijo Mensual añadido: " + tfm);
                        }
                        case 2 -> {
                            int ventas = 5 + random.nextInt(50);
                            double comision = 0.05 + random.nextDouble() * 0.15;
                            TrabajadorComisionista tc = new TrabajadorComisionista(ventas, comision, nombre, apellido, direccion, dni, jefe);
                            tc.calcularSueldo();
                            listaTrabajadores.add(tc);
                            System.out.println("Trabajador Comisionista añadido: " + tc);
                        }
                        case 3 -> {
                            int horas = 20 + random.nextInt(60);
                            double precioHora = 10 + random.nextDouble() * 15;
                            double precioHoraExtra = precioHora * 1.5;
                            TrabajadorHoras th = new TrabajadorHoras(horas, precioHora, precioHoraExtra, nombre, apellido, direccion, dni, jefe);
                            th.calcularSueldo();
                            listaTrabajadores.add(th);
                            System.out.println("Trabajador por Horas añadido: " + th);
                        }
                        default ->
                            System.out.println("Opción no válida.");
                    }
                }

            } while (continuarTrabajadores.equals("S"));

            System.out.println("\n¿Deseas añadir otro jefe? (S/N)");
            continuarJefes = sc.nextLine().toUpperCase();

        } while (continuarJefes.equals("S"));

        System.out.println("\n--- Lista Final de Trabajadores ---");
        for (Trabajador t : listaTrabajadores) {
            System.out.println(t);
        }

        System.out.println("\n--- Lista Final de Jefes ---");
        for (Jefe j : listaJefes) {
            System.out.println(j);
        }

        System.out.println("\nPrograma finalizado.");
    }
}

class Trabajador {

    public String nombre;
    public String apellidos;
    public String direccion;
    public int dni;
    public Jefe jefe;

    public Trabajador() {
    }

    public Trabajador(String nombre, String apellidos, String direccion, int dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
    }

    public Trabajador(String nombre, String apellidos, String direccion, int dni, Jefe jefe) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    @Override
    public String toString() {
        return "Trabajador{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + ", dni=" + dni + ", jefe=" + jefe + '}';
    }
}

class TrabajadorFijoMensual extends Trabajador {

    public double sueldo;

    public TrabajadorFijoMensual() {
    }

    public TrabajadorFijoMensual(double sueldo, String nombre, String apellidos,String direccion, int dni, Jefe jefe) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "TrabajadorFijoMensual{sueldo=" + sueldo + "} " + super.toString();
    }
}

class TrabajadorComisionista extends Trabajador {

    public int ventas;
    public double comision;
    private double sueldoCalculado;

    public TrabajadorComisionista() {
    }

    public TrabajadorComisionista(int ventas, double comision, String nombre,
            String apellidos, String direccion, int dni, Jefe jefe) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.ventas = ventas;
        this.comision = comision;
    }

    public void calcularSueldo() {
        this.sueldoCalculado = this.ventas * this.comision;
    }

    @Override
    public String toString() {
        return "TrabajadorComisionista{ventas=" + ventas + ", comision=" + comision
                + ", sueldoCalculado=" + sueldoCalculado + "} " + super.toString();
    }
}

class TrabajadorHoras extends Trabajador {

    public int cantidadHoras;
    public double horasPorcentaje;
    public double horasExtraPorcentaje;
    private double sueldoCalculado;

    public TrabajadorHoras() {
    }

    public TrabajadorHoras(int cantidadHoras, double horasPorcentaje, double horasExtraPorcentaje, String nombre, String apellidos, String direccion, int dni, Jefe jefe) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.cantidadHoras = cantidadHoras;
        this.horasPorcentaje = horasPorcentaje;
        this.horasExtraPorcentaje = horasExtraPorcentaje;
    }

    public void calcularSueldo() {
        if (cantidadHoras <= 40) {
            this.sueldoCalculado = cantidadHoras * horasPorcentaje;
        } else {
            int horasExtra = cantidadHoras - 40;
            this.sueldoCalculado = (40 * horasPorcentaje) + (horasExtra * horasExtraPorcentaje);
        }
    }

    @Override
    public String toString() {
        return "TrabajadorHoras{cantidadHoras=" + cantidadHoras
                + ", horasPorcentaje=" + horasPorcentaje
                + ", horasExtraPorcentaje=" + horasExtraPorcentaje
                + ", sueldoCalculado=" + sueldoCalculado + "} " + super.toString();
    }
}

class Jefe extends Trabajador {

    public double sueldo;
    private double sueldoCalculado;

    public Jefe() {
    }

    public Jefe(double sueldo, String nombre, String apellidos, String direccion, int dni) {
        super(nombre, apellidos, direccion, dni);
        this.sueldo = sueldo;
    }

    public void calcularSueldo() {
        this.sueldoCalculado = this.sueldo * 1.2; // 20% de bonificacióSSn
    }

    @Override
    public String toString() {
        return "Jefe{sueldo=" + sueldo + ", sueldoCalculado=" + sueldoCalculado + "} " + super.toString();
    }
}
