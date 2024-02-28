package atec.poo.mediateca.core;
import java.io.Serializable;

public class DVD extends Work {
    private String director;
    private String IGAC;

    public DVD(String title, String director, int price, Categorias categoria, TipoObra tipoObra, int numCopies, String IGAC) {
        super(title, price, numCopies, 0, categoria, tipoObra);
        this.director = director;
        this.IGAC = IGAC;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getIGAC() {
        return IGAC;
    }

    public void setIGAC(String IGAC) {
        this.IGAC = IGAC;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", super.toString(), getDirector(), getIGAC());
    }

    @Override
    public int compareTo(Work other) {
        return Integer.compare(getId(), other.getId());
    }
}