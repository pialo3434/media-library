package atec.poo.mediateca.core;

import java.util.ArrayList;

public class Utentes implements Comparable<Utentes> {
    private Integer numUtente;
    public static int numutente = 0;
    private final String nome;
    private final String email;
    private CategoriasUser.UserStatus estado;
    private CategoriasUser.UserClassification classif;
    private double divida;
    private int numerObrasRequi;
    private int devolveInTime;
    private int devolveAtrasado;
    private ArrayList<Notificacao> notificacoes;
    private ArrayList<Requisicao> requisicoes;


    public Utentes(int numUtente, String nome, String email) {
        this.numUtente = numUtente;
        this.nome = nome;
        this.email = email;
        this.estado = CategoriasUser.UserStatus.ACTIVE;
        this.classif = CategoriasUser.UserClassification.NORMAL;
        this.divida = 0.0;
        this.devolveInTime = 0;
        this.devolveAtrasado = 0;
        this.numerObrasRequi = 0;
        this.notificacoes = new ArrayList<>();
        this.requisicoes = new ArrayList<>();
        Utentes.numutente++;
    }


    // Method to make a requisition
    // Inside the Utentes class
    public void requisitarObraUtentes(Work obra, int dataRequisicao, int dataDevolucao) {
        Requisicao requisicao = new Requisicao(this, obra, dataRequisicao, dataDevolucao);
        requisicoes.add(requisicao);
    }

    public void clearNotificacoes() {
        notificacoes.clear();
    }

    public ArrayList<Requisicao> getRequisicoes() {
        return new ArrayList<>(requisicoes);
    }


    public void adicionarNotificacao(Notificacao notificacao) {
        notificacoes.add(notificacao);
    }

    public ArrayList<Notificacao> getNotificacoes() {
        return new ArrayList<>(notificacoes);
    }

    public int getNumUtente() {
        return numUtente;
    }

    public void setNumUtente(Integer numUtente) {
        this.numUtente = numUtente;
    }

    public static void setNumutente(int numutente) {
        Utentes.numutente = numutente;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public CategoriasUser.UserStatus getEstado() {
        return estado;
    }

    public void setEstado(CategoriasUser.UserStatus estado) {
        this.estado = estado;
    }

    public CategoriasUser.UserClassification getClassif() {
        return classif;
    }

    public void setClassif(CategoriasUser.UserClassification classif) {
        this.classif = classif;
    }

    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }

    public int getNumerObrasRequi() {
        return numerObrasRequi;
    }

    public void setNumerObrasRequi(int numerObrasRequi) {
        this.numerObrasRequi = numerObrasRequi;
    }

    public int getDevolveInTime() {
        return devolveInTime;
    }

    public void setDevolveInTime(int devolveInTime) {
        this.devolveInTime = devolveInTime;
    }

    public int getDevolveAtrasado() {
        return devolveAtrasado;
    }

    public void setDevolveAtrasado(int devolveAtrasado) {
        this.devolveAtrasado = devolveAtrasado;
    }

    @Override
    public String toString() {
        String dividaString = this.divida == 0 ? "" : String.format(" - %.2f", this.divida);
        return String.format("%d - %s - %s - %s - %s%s", this.numUtente, this.nome, this.email, this.estado, this.classif, dividaString);
    }

    @Override
    public int compareTo(Utentes other) {
        // Compare based on the numUtente field
        return Integer.compare(this.numUtente, other.getNumUtente());
    }

    public void devolverObraUtentes(Work obra) {
        // Remove the work from the user's requisicoes list to indicate it has been returned
        requisicoes.removeIf(requisicao -> requisicao.getObra().equals(obra));
    }

    public boolean isObraBorrowed(Work obra) {
        for (Requisicao requisicao : requisicoes) {
            if (requisicao.getObra().equals(obra)) {
                return true;
            }
        }
        return false;
    }



    public static class UserNotFoundException extends Throwable {

        private final int userId;

        public UserNotFoundException(int userId) {
            this.userId = userId;
        }

        @Override
        public String getMessage() {
            return "User with ID " + userId + " not found.";
        }
    }

}
