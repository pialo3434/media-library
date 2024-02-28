package atec.poo.mediateca.core;

public class Notificacao {
    private boolean isEntrega; // Indica se a notificação é sobre uma entrega (true) ou requisição (false)
    private Work obra; // Reference to the Book or DVD
    private double divida; // Divida amount

    public Notificacao(boolean isEntrega, Work obra) {
        this.isEntrega = isEntrega;
        this.obra = obra;
    }

    public boolean isEntrega() {
        return isEntrega;
    }

    public void setEntrega(boolean isEntrega) {
        this.isEntrega = isEntrega;
    }

    public Work getObra() {
        return obra;
    }

    public void setObra(Work obra) {
        this.obra = obra;
    }

    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }

    @Override
    public String toString() {
        String tipoNotificacao = isEntrega ? "ENTREGA" : "REQUISIÇÃO";
        return tipoNotificacao + ": " + obra.toString();
    }
}
