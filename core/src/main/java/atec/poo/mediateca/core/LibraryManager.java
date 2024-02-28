package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.BadEntrySpecificationException;
import atec.poo.mediateca.core.exceptions.ImportFileException;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LibraryManager {
    Biblioteca _biblioteca;
    public LibraryManager(){
        this._biblioteca=new Biblioteca();
    }


    /**
     *
     * Recebe ficheiro de entrada
     * @param datafile
     * @throws ImportFileException
     */
    public void importFile(String datafile) throws ImportFileException {
        try {
            this._biblioteca.importFile(datafile);
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }
    }

    public Work getWorkByID(int id) {
        return this._biblioteca.getWorkByID(id);
    }


    public void criarUtente(String nome, String email) {
        this._biblioteca.criarUtente(nome, email);
    }


    public int getDiaAtual(){
        return this._biblioteca.getDiaAtual();
    }

    public int avancarDataAtual(int dias){
        return this._biblioteca.avancarDataAtual(dias);
    }

    public String getD(){
        return this._biblioteca.getUtentes();
    }

    public ArrayList<Utentes> ShowUsers(){
        return this._biblioteca.ShowUsers();
    }

    public ArrayList<Work> showWorks(){
        return this._biblioteca.showWorks();
    }

    public void ShowUser(int id) {
         this._biblioteca.ShowUser(id);
    }

    public void pagarMulta(int idUtente) {
        this._biblioteca.pagarMulta(idUtente);
    }

    public boolean isUserSuspended(int idUtente) {
        return _biblioteca.isUserSuspended(idUtente);
    }

    public void devolverObra(int idUtente, int obraId) {
        _biblioteca.devolverObra(idUtente, obraId);
    }

    public void requisitarObra(int idUtente, int obraId, int dataRequisicao, int dataDevolucao) {
        _biblioteca.requisitarObra(idUtente, obraId, dataRequisicao, dataDevolucao);
    }

    public void mostrarNotificacoesUtente(int idUtente) throws Utentes.UserNotFoundException {
        _biblioteca.mostrarNotificacoesUtente(idUtente);
    }

    public Utentes getUtente(int idUtente) {
        return _biblioteca.todosUtentes.get(idUtente);
    }

    public Work getWork(String obraId) {
        return _biblioteca.getWorkByID(Integer.parseInt(obraId));
    }

    /**
     * Performs a search for the given term in the relevant fields of the works (author and title for books, director and title for DVDs).
     * Returns a list of works that contain the search term in any of the relevant fields.
     *
     * @param searchTerm The term to search for.
     * @return A list of works that match the search term in any of the relevant fields.
     */
    public ArrayList<Work> performSearch(String searchTerm) {
        return this._biblioteca.performSearch(searchTerm);
    }


    public void save(String nome_ficheiro) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nome_ficheiro)));
        oos.writeObject(this);
        oos.close();
    }




} //END