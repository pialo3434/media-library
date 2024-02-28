package atec.poo.mediateca.core;

import java.io.Serializable;

public class Requisicao implements Serializable {
    private Utentes utente;
    private Work obra;
    private int dataRequisicao;
    private int dataDevolucao;



    public Requisicao(Utentes utente, Work obra, int dataRequisicao, int dataDevolucao) {
        this.utente = utente;
        this.obra = obra;
        this.dataRequisicao = dataRequisicao;
        this.dataDevolucao = dataDevolucao;
    }

    public Utentes getUtente() {
        return utente;
    }

    public Work getObra() {
        return obra;
    }

    public void setDataRequisicao(int dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public void setDataDevolucao(int dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getDataRequisicao() {
        return dataRequisicao;
    }

    public int getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString() {
        return String.format("Utente: %s - Obra: %s - Data Requisicao: %d - Data Devolucao: %d",
                utente.getNome(), obra.getTitle(), dataRequisicao, dataDevolucao);
    }
}
