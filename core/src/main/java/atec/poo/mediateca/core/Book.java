package atec.poo.mediateca.core;
import java.io.Serializable;

public class Book extends Work {
    private String author;
    private String isbn;

    public Book(String title, String author, int price, Categorias categoria, TipoObra tipoObra, int numCopies, String isbn) {
        super(title, price, numCopies, 0, categoria, tipoObra);
        this.author = author;
        this.isbn = isbn;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", super.toString(), getAuthor(), getIsbn());
    }

    @Override
    public int compareTo(Work other) {
        return Integer.compare(getId(), other.getId());
    }
}
