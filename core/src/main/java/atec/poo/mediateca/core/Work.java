package atec.poo.mediateca.core;

import java.io.Serial;
import java.io.Serializable;


public abstract class Work implements Comparable<Work>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static int nextId = 1;

    private int idWork;
    private String title;
    private int price;
    private int numCopies;
    private int numCopiesDisponiveis;
    private Categorias categorias;
    private TipoObra tipoObra;


    public void setNumCopiesDisponiveis(int numCopiesDisponiveis) {
        this.numCopiesDisponiveis = numCopiesDisponiveis;
    }

    public Work(String title, int price, int numCopies, int numCopiesDisponiveis, Categorias categorias, TipoObra tipoObra) {
        this.idWork = nextId;
        nextId++;
        this.title = title;
        this.price = price;
        this.numCopies = numCopies;
        this.numCopiesDisponiveis = numCopies;
        this.categorias = categorias;

        // Here, determine the correct subtype based on tipoObra and create an instance of that subtype
        switch (tipoObra) {
            case Book:
                this.tipoObra = TipoObra.Book;
                break;
            case DVD:
                this.tipoObra = TipoObra.DVD;
                break;
            // Add more cases for other types if needed
            default:
                throw new IllegalArgumentException("Invalid tipoObra: " + tipoObra);
        }
    }


    public int getId() {
        return idWork;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public int getNumCopiesDisponiveis() {
        return numCopiesDisponiveis;
    }

    public TipoObra getTipoObra() {
        return tipoObra;
    }

    public static class WorkNotFoundException extends Throwable {

        private final String workId;

        public WorkNotFoundException(String workId) {
            this.workId = workId;
        }

        @Override
        public String getMessage() {
            return "Work with ID " + workId + " not found.";
        }
    }


    //NOTA: Acabar a funcao que inventei de fazer os witch chamando o toString() de obras e dvd's.
    @Override
    public String toString() {
        return String.format("%d - %d de %d - %s - %s - %d - %s",
                getId(), getNumCopiesDisponiveis(), getNumCopies(), getTipoObra(), getTitle(), getPrice(), getCategorias());
    }

    @Override
    public int compareTo(Work other) {
        return Integer.compare(idWork, other.getId());
    }
}
