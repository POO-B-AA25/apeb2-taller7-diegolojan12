
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Problema6_CuentaBanco {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        String[] nombres = {"Juan", "María", "Carlos", "Ana", "Luis", "Laura", "Pedro", "Sofía"};
        String[] tiposCuenta = {"Cuenta de Ahorro", "Cuenta de Cheque", "Cuenta Platino"};

        ArrayList<CuentaBancaria> listaCuentas = new ArrayList<>();

        String continuar;

        do {

            String numeroCuenta = "C" + (10000000 + random.nextInt(90000000));
            String nombreCliente = nombres[random.nextInt(nombres.length)];
            double balanceInicial = 500 + random.nextDouble() * 9500;

            System.out.println("\nSeleccione el tipo de cuenta:");
            System.out.println("1. Cuenta de Ahorro");
            System.out.println("2. Cuenta de Cheque");
            System.out.println("3. Cuenta Platino");
            System.out.print("Opción: ");
            int tipoCuenta = sc.nextInt();
            sc.nextLine();

            switch (tipoCuenta) {
                case 1 -> {
                    double tasaInteres = 0.02 + random.nextDouble() * 0.08;
                    CuentaAhorro ca = new CuentaAhorro(tasaInteres, numeroCuenta, nombreCliente, balanceInicial);
                    listaCuentas.add(ca);
                    System.out.println("\nCuenta de Ahorro creada: " + ca);
                }
                case 2 -> {
                    CuentaCheque cc = new CuentaCheque(numeroCuenta, nombreCliente, balanceInicial);
                    listaCuentas.add(cc);
                    System.out.println("\nCuenta de Cheque creada: " + cc);
                }
                case 3 -> {
                    double tasaInteres =  0.10;
                    CuentaPlatino cp = new CuentaPlatino(tasaInteres, numeroCuenta, nombreCliente, balanceInicial);
                    listaCuentas.add(cp);
                    System.out.println("\nCuenta Platino creada: " + cp);
                }
                default ->
                    System.out.println("Opción no válida.");
            }

            if (!listaCuentas.isEmpty()) {
                CuentaBancaria cuentaActual = listaCuentas.get(listaCuentas.size() - 1);

                System.out.println("\n¿Desea realizar operaciones con esta cuenta? (S/N)");
                String operar = sc.nextLine().toUpperCase();

                while (operar.equals("S")) {
                    System.out.println("\nOperaciones disponibles:");
                    System.out.println("1. Depositar");
                    System.out.println("2. Retirar");
                    System.out.println("3. Obtener valance actual");
                    System.out.print("Opción: ");
                    int opcion = sc.nextInt();
                    sc.nextLine();

                    switch (opcion) {
                        case 1 -> {
                            System.out.print("Ingrese monto a depositar: ");
                            double montoDeposito;
                            do {
                                montoDeposito = sc.nextDouble();
                                sc.nextLine();
                                if (montoDeposito <= 0) {
                                    System.out.println("Cantidad invalida.");
                                }
                            } while (montoDeposito <= 0);
                            cuentaActual.depositar(montoDeposito);

                            System.out.println("Depósito realizado. Nuevo balance: " + cuentaActual.balance);
                        }
                        case 2 -> {
                            System.out.print("Ingrese monto a retirar: ");
                            double montoRetiro;
                            do {
                                montoRetiro = sc.nextDouble();
                                sc.nextLine();
                                if (cuentaActual instanceof CuentaAhorro && montoRetiro > cuentaActual.balance) {
                                    System.out.println("Cantidad invalida.");
                                    System.out.print("Vuelva a ingresar: ");
                                }
                            } while (cuentaActual instanceof CuentaAhorro && montoRetiro > cuentaActual.balance);
                            
                            cuentaActual.retirar(montoRetiro);
                            
                            if (cuentaActual instanceof CuentaCheque) {
                                ((CuentaCheque) cuentaActual).revisarSobregiro();
                            } else if (cuentaActual instanceof CuentaPlatino) {
                                ((CuentaPlatino) cuentaActual).revisarSobregiro();
                            }
                            
                            System.out.println("Retiro realizado. Nuevo balance: " + cuentaActual.balance);
                        }
                        case 3 -> {
                            System.out.println(cuentaActual.getBalance());
                        }
                        default ->
                            System.out.println("Opción no válida.");
                    }

                    System.out.println("\n¿Desea realizar otra operación con esta cuenta? (S/N)");
                    operar = sc.nextLine().toUpperCase();
                }

                if (cuentaActual instanceof CuentaAhorro) {
                    ((CuentaAhorro) cuentaActual).calcularInteres();
                    System.out.println("Interés calculado y agregado: $" + cuentaActual.interes);
                } else if (cuentaActual instanceof CuentaPlatino) {
                    ((CuentaPlatino) cuentaActual).calcularInteres();
                    System.out.println("Interés calculado y agregado: $" + cuentaActual.interes);
                }
            }

            System.out.println("\n¿Desea crear otra cuenta? (S/N)");
            continuar = sc.nextLine();

        } while (continuar.equals("S"));

        System.out.println("\nLista Final de Cuentas");
        for (CuentaBancaria cuenta : listaCuentas) {
            System.out.println(cuenta);
        }

        System.out.println("\nPrograma finalizado.");
    }
}

class CuentaBancaria {

    public String numeroCuenta;
    public String nombreCliente;
    public double balance;
    public double interes;

    public CuentaBancaria() {}

    public CuentaBancaria(String numeroCuenta, String nombreCliente, double balance) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = balance;
    }

    public void depositar(double monto) {
        this.balance += monto;
    }

    public void retirar(double monto) {
        this.balance -= monto;
    }

    public double getBalance() {
        return this.balance;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "numeroCuenta=" + numeroCuenta + ", nombreCliente=" + nombreCliente + ", balance=" + balance + '}';
    }
}

class CuentaAhorro extends CuentaBancaria {

    public double tasaInteres;

    public CuentaAhorro() {
    }

    public CuentaAhorro(double tasaInteres, String numeroCuenta, String nombreCliente, double balance) {
        super(numeroCuenta, nombreCliente, balance);
        this.tasaInteres = tasaInteres;
    }

    public void calcularInteres() {
        super.interes = balance * tasaInteres;
        balance += interes;

    }

    @Override
    public String toString() {
        return "CuentaAhorro{tasaInteres=" + tasaInteres + "} " + super.toString();
    }
}

class CuentaCheque extends CuentaBancaria {

    public boolean sobregiro;

    public CuentaCheque() {
    }

    public CuentaCheque(String numeroCuenta, String nombreCliente, double balance) {
        super(numeroCuenta, nombreCliente, balance);

    }

    public void revisarSobregiro() {
        this.sobregiro = this.balance < 0;
    }

    @Override
    public String toString() {
        return "CuentaCheque{sobregiro=" + sobregiro + "} " + super.toString();
    }
}

class CuentaPlatino extends CuentaBancaria {

    public double tasaInteres;
    public boolean sobregiro;

    public CuentaPlatino() {
    }

    public CuentaPlatino(double tasaInteres, String numeroCuenta, String nombreCliente, double balance) {
        super(numeroCuenta, nombreCliente, balance);
        this.tasaInteres = tasaInteres;
        
    }

    public void calcularInteres() {
        super.interes = balance * tasaInteres;
        this.balance += this.interes;
    }

    public void revisarSobregiro() {
        this.sobregiro = this.balance < 0;
    }

    @Override
    public String toString() {
        return "CuentaPlatino{tasaInteres=" + tasaInteres + "sobregiro" + sobregiro + "} " + super.toString();
    }
}
