
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Problema2_AlquilerPelicula {

    public static void main(String[] args) {
        
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        
        
        String[] titulos = {"El Padrino", "Pulp Fiction", "Forrest Gump", "Matrix", "Titanic", 
                            "Jurassic Park", "Avatar", "Star Wars", "Gladiador", "Inception"};
        String[] directores = {"Steven Spielberg", "James Cameron", "Quentin Tarantino", 
                               "Christopher Nolan", "Martin Scorsese", "Ridley Scott"};
        
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        
        char opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Añadir nueva película");
            System.out.println("2. Mostrar todas las películas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.next().charAt(0);
            sc.nextLine(); 
            
            switch(opcion) {
                case '1':

                    String titulo = titulos[random.nextInt(titulos.length)];
                    String director = directores[random.nextInt(directores.length)];
                    int año = 1970 + random.nextInt(50); 
                    
                    Pelicula nuevaPelicula = new Pelicula(titulo, director, año);
                    listaPeliculas.add(nuevaPelicula);
                    System.out.println("\nPelícula añadida: " + nuevaPelicula);
                    
                    System.out.println("\nSeleccione el tipo de soporte:");
                    System.out.println("1. VHS");
                    System.out.println("2. DVD");
                    System.out.print("Opción: ");
                    int tipoSoporte = sc.nextInt();
                    
                    if(tipoSoporte == 1) {

                        String[] idiomasVHS = {"Español", "Inglés", "Francés", "Alemán"};
                        String idioma = idiomasVHS[random.nextInt(idiomasVHS.length)];
                        double precio = 5 + random.nextDouble() * 10; 
                        
                        VHS vhs = new VHS(idioma, nuevaPelicula, precio);
                        
                        System.out.println("\nVHS creado: " + vhs);
                    } else if(tipoSoporte == 2) {
                        String[] idiomasDVD = {"Español", "Inglés", "Francés", "Alemán", "Italiano"};
                        int numIdiomas = 1 + random.nextInt(3); 
                        String[] idiomas = new String[numIdiomas];
                        for(int i = 0; i < numIdiomas; i++) {
                            idiomas[i] = idiomasDVD[random.nextInt(idiomasDVD.length)];
                        }
                        
                        ArrayList<Pelicula> peliculasDVD = new ArrayList<>();
                        peliculasDVD.add(nuevaPelicula);
                        System.out.println("Dese añadir otra pelicula al DVD? (S/N): ");
                        char anadirPelicula = sc.next().charAt(0);
                        if (anadirPelicula == 'S') {
                            String titulon = titulos[random.nextInt(titulos.length)];
                            String directorn = directores[random.nextInt(directores.length)];
                            int añon = 1970 + random.nextInt(50);
                            Pelicula nuevaPeliculan = new Pelicula(titulon, directorn, añon);
                            peliculasDVD.add(nuevaPeliculan);
                        }

                        double precio = 10 + random.nextDouble() * 15;   
                        DVD dvd = new DVD(idiomas, peliculasDVD, precio);  
                        dvd.calcularCostoAlquiler();
                        System.out.println("\nDVD creado: " + dvd);
                        
                    } else {
                        System.out.println("Opción no válida. No se creó soporte.");
                    }
                    break;
                    
                case '2':
                    System.out.println("\n--- Lista de Películas ---");
                    for(Pelicula p : listaPeliculas) {
                        System.out.println(p);
                    }
                    break;                   
                case '3':
                    System.out.println("Saliendo del programa...");
                    break;
                    
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            
        } while(opcion != '3');
    }
}
   

class SoportePelicla {

    public double precioAlq;

    public SoportePelicla(double precioAlq) {
        this.precioAlq = precioAlq;
    }

    @Override
    public String toString() {
        return "SoportePelicla{" + "precioAlq=" + precioAlq + '}';
    }
    

}

class DVD extends SoportePelicla {

    public String idomas[];
    public ArrayList<Pelicula> listaPelicula;

    public DVD(String[] idomas, ArrayList<Pelicula> listaPelicula, double precioAlq) {
        super(precioAlq);
        this.idomas = idomas;
        this.listaPelicula = listaPelicula;
    }

    public void calcularCostoAlquiler() {
        this.precioAlq = this.precioAlq + (this.precioAlq * 0.1);
    }

    @Override
    public String toString() {
        return "DVD{" + "idomas=" + Arrays.toString(idomas) + ", listaPelicula=" + listaPelicula + '}' + super.toString();
    }

}

class VHS extends SoportePelicla {

    public String idioma;
    public Pelicula pelicula;

    public VHS(String idioma, Pelicula pelicula, double precioAlq) {
        super(precioAlq);
        this.idioma = idioma;
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "VHS{" + "idioma=" + idioma + ", pelicula=" + pelicula + '}' + super.toString();
    }

}

class Pelicula {

    public String titulo;
    public String autor;
    public int anioEdicion;

    public Pelicula(String titulo, String autor, int anioEdicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioEdicion = anioEdicion;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", autor=" + autor + ", anioEdicion=" + anioEdicion + '}';
    }

}
