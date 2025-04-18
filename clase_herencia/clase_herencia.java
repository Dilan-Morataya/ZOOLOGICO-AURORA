package clase_herencia;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class clase_herencia {

    abstract static class Animal {
        protected String nombre;
        protected int edad;
        protected double peso;
        protected String dieta;

        public Animal(String nombre, int edad, double peso, String dieta) {
            this.nombre = nombre;
            this.edad = edad;
            this.peso = peso;
            this.dieta = dieta;
        }

        public abstract void alimentar();

        public double calcularAlimento(int dias) {
            if (dias < 0) {
                throw new IllegalArgumentException("Los días no pueden ser negativos");
            }
            return consumoDiario() * dias;
        }

        protected abstract double consumoDiario();

        public void exportarCSV() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("animales.csv", true))) {
                writer.write(nombre + "," + edad + "," + peso + "," + dieta + "\n");
            } catch (IOException e) {
                System.out.println("Error al guardar en CSV: " + e.getMessage());
            }
        }

        @Override
        public String toString() {
            return nombre + " - " + edad + " años - " + peso + " kg - " + dieta;
        }
    }

    static class Mamifero extends Animal {
        public Mamifero(String nombre, int edad, double peso, String dieta) {
            super(nombre, edad, peso, dieta);
        }

        @Override
        public void alimentar() {
            System.out.println(nombre + " (mamífero) ha sido alimentado.");
        }

        @Override
        public double consumoDiario() {
            return 10; // Ejemplo
        }
    }

    static class Ave extends Animal {
        public Ave(String nombre, int edad, double peso, String dieta) {
            super(nombre, edad, peso, dieta);
        }

        @Override
        public void alimentar() {
            System.out.println(nombre + " (ave) ha sido alimentado.");
        }

        @Override
        public double consumoDiario() {
            return 2; // Ejemplo
        }
    }

    static class Reptil extends Animal {
        public Reptil(String nombre, int edad, double peso, String dieta) {
            super(nombre, edad, peso, dieta);
        }

        @Override
        public void alimentar() {
            System.out.println(nombre + " (reptil) ha sido alimentado.");
        }

        @Override
        public double consumoDiario() {
            return 5; // Ejemplo
        }
    }

    public static void main(String[] args) {
        try {
            Mamifero leon = new Mamifero("León", 5, 190.5, "Carnívoro");
            Ave aguila = new Ave("Águila", 3, 5.2, "Omnívoro");
            Reptil cocodrilo = new Reptil("Cocodrilo", 8, 500, "Carnívoro");

            leon.alimentar();
            aguila.alimentar();
            cocodrilo.alimentar();

            System.out.println(leon);
            System.out.println("Alimento para 3 días: " + leon.calcularAlimento(3) + " kg");

            System.out.println(aguila);
            System.out.println("Alimento para 3 días: " + aguila.calcularAlimento(3) + " kg");

            System.out.println(cocodrilo);
            System.out.println("Alimento para 3 días: " + cocodrilo.calcularAlimento(3) + " kg");

            leon.exportarCSV();
            aguila.exportarCSV();
            cocodrilo.exportarCSV();

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}