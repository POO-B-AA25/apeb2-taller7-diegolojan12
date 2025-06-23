
import java.util.ArrayList;

public class Problema1_Libro {

    public static void main(String[] args) {

        ArrayList<Palabra> palabras1 = new ArrayList<>();
        palabras1.add(new Palabra("En"));
        palabras1.add(new Palabra("un"));
        palabras1.add(new Palabra("lugar"));
        palabras1.add(new Palabra("de"));
        palabras1.add(new Palabra("la"));
        palabras1.add(new Palabra("Mancha"));

        ArrayList<Palabra> palabras2 = new ArrayList<>();
        palabras2.add(new Palabra("de"));
        palabras2.add(new Palabra("cuyo"));
        palabras2.add(new Palabra("nombre"));
        palabras2.add(new Palabra("no"));
        palabras2.add(new Palabra("quiero"));
        palabras2.add(new Palabra("acordarme"));

        ArrayList<Sentencia> sentencias1 = new ArrayList<>();
        sentencias1.add(new Sentencia(palabras1));
        sentencias1.add(new Sentencia(palabras2));

        ArrayList<Componente> componentesSeccion1 = new ArrayList<>();
        componentesSeccion1.add(new Parrafo("Inicio de la obra", sentencias1));

        componentesSeccion1.add(new Figura("Figura 1", "Don Quijote", "Ilustración del personaje principal"));

        ArrayList<String> itemsTabla = new ArrayList<>();
        itemsTabla.add("Capítulo 1");
        itemsTabla.add("Capítulo 2");
        itemsTabla.add("Capítulo 3");
        componentesSeccion1.add(new Lista("Índice de capítulos", itemsTabla));

        ArrayList<Seccion> seccionesCapitulo1 = new ArrayList<>();
        seccionesCapitulo1.add(new Seccion("1.1", componentesSeccion1));

        ArrayList<Componente> componentesSeccion2 = new ArrayList<>();

        ArrayList<Palabra> palabras3 = new ArrayList<>();
        palabras3.add(new Palabra("En"));
        palabras3.add(new Palabra("esta"));
        palabras3.add(new Palabra("sección"));
        palabras3.add(new Palabra("se"));
        palabras3.add(new Palabra("desarrolla"));
        palabras3.add(new Palabra("el"));
        palabras3.add(new Palabra("argumento"));

        ArrayList<Sentencia> sentencias2 = new ArrayList<>();
        sentencias2.add(new Sentencia(palabras3));

        componentesSeccion2.add(new Parrafo("Desarrollo", sentencias2));

        componentesSeccion2.add(new Tabla("Personajes principales", 3, 2));

        ArrayList<String> puntosVineta = new ArrayList<>();
        puntosVineta.add("Don Quijote");
        puntosVineta.add("Sancho Panza");
        puntosVineta.add("Dulcinea");
        componentesSeccion2.add(new Vineta("Personajes", puntosVineta));

        seccionesCapitulo1.add(new Seccion("1.2", componentesSeccion2));

        ArrayList<Capitulo> capitulosLibro = new ArrayList<>();
        capitulosLibro.add(new Capitulo("Introducción", seccionesCapitulo1));

        ArrayList<Componente> componentesSeccion3 = new ArrayList<>();

        ArrayList<Palabra> palabras4 = new ArrayList<>();
        palabras4.add(new Palabra("Segunda"));
        palabras4.add(new Palabra("parte"));
        palabras4.add(new Palabra("de"));
        palabras4.add(new Palabra("la"));
        palabras4.add(new Palabra("obra"));

        ArrayList<Sentencia> sentencias3 = new ArrayList<>();
        sentencias3.add(new Sentencia(palabras4));

        componentesSeccion3.add(new Parrafo("Continuación", sentencias3));
        componentesSeccion3.add(new Figura("Figura 2", "Molinos de viento", "Escena famosa"));

        ArrayList<Seccion> seccionesCapitulo2 = new ArrayList<>();
        seccionesCapitulo2.add(new Seccion("2.1", componentesSeccion3));

        capitulosLibro.add(new Capitulo("Desarrollo", seccionesCapitulo2));

        Libro libro = new Libro("Miguel de Cervantes", 1, 1605, capitulosLibro);

        System.out.println(libro);
    }
}

class Libro {

    private String autor;
    private int numberCap;
    private int numberPag;
    private ArrayList<Capitulo> capitulos;

    public Libro(String autor, int numberCap, int numberPag, ArrayList<Capitulo> capitulos) {
        this.autor = autor;
        this.numberCap = numberCap;
        this.numberPag = numberPag;
        this.capitulos = capitulos;
    }

    @Override
    public String toString() {
        return "Libro{" + "autor=" + autor + ", numberCap=" + numberCap + ", numberPag=" + numberPag + ", capitulos=" + capitulos + '}';
    }

}

class Capitulo {

    private String titulo;
    private ArrayList<Seccion> secciones;

    public Capitulo(String titulo, ArrayList<Seccion> secciones) {
        this.titulo = titulo;
        this.secciones = secciones;
    }

    @Override
    public String toString() {
        return "Capitulo{" + "titulo=" + titulo + ", secciones=" + secciones + '}';
    }

}

class Seccion {

    private String numero;
    private ArrayList<Componente> componentes;

    public Seccion(String numero, ArrayList<Componente> componentes) {
        this.numero = numero;
        this.componentes = componentes;
    }

    @Override
    public String toString() {
        return "Seccion{" + "numero=" + numero + ", componentes=" + componentes + '}';
    }

}

abstract class Componente {

    protected String contenido;

    public Componente(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Componente{" + "contenido=" + contenido + '}';
    }

}

class Parrafo extends Componente {

    private ArrayList<Sentencia> sentencias;

    public Parrafo(String contenido, ArrayList<Sentencia> sentencias) {
        super(contenido);
        this.sentencias = sentencias;
    }

    @Override
    public String toString() {
        return "Parrafo{" + "sentencias=" + sentencias + '}' + super.toString();
    }

}

class Sentencia {

    private ArrayList<Palabra> palabras;

    public Sentencia(ArrayList<Palabra> palabras) {
        this.palabras = palabras;
    }

    @Override
    public String toString() {
        return "Sentencia{" + "palabras=" + palabras + '}';
    }

}

class Palabra {

    private String texto;

    public Palabra(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Palabra{" + "texto=" + texto + '}';
    }

}

class Figura extends Componente {

    private String titulo;
    private String leyenda;

    public Figura(String contenido, String titulo, String leyenda) {
        super(contenido);
        this.titulo = titulo;
        this.leyenda = leyenda;
    }

    @Override
    public String toString() {
        return "Figura{" + "titulo=" + titulo + ", leyenda=" + leyenda + '}' + super.toString();
    }

}

class Tabla extends Componente {

    private int filas;
    private int columnas;

    public Tabla(String contenido, int filas, int columnas) {
        super(contenido);
        this.filas = filas;
        this.columnas = columnas;
    }

    @Override
    public String toString() {
        return "Tabla{" + "filas=" + filas + ", columnas=" + columnas + '}' + super.toString();
    }

}

class Lista extends Componente {

    private ArrayList<String> items;

    public Lista(String contenido, ArrayList<String> items) {
        super(contenido);
        this.items = items;
    }

    @Override
    public String toString() {
        return "Lista{" + "items=" + items + '}' + super.toString();
    }

}

class Vineta extends Componente {

    private ArrayList<String> puntos;

    public Vineta(String contenido, ArrayList<String> puntos) {
        super(contenido);
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Vineta{" + "puntos=" + puntos + '}' + super.toString();
    }

}
