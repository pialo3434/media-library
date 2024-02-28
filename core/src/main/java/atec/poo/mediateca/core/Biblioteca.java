package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.BadEntrySpecificationException;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Biblioteca implements Serializable {


    private int proximo_utente = 1;
    private int proximo_livro = 1;
    private int diaAtual;

    public Map<Integer, Utentes> todosUtentes;
    public Map<Integer, Work> works;

    public Biblioteca() {
        todosUtentes = new Hashtable<>();
        works = new Hashtable<>();
        this.diaAtual = 0;
    }
    // Getter and setter for current date
    public int getDiaAtual() {
        return diaAtual;
    }

    public int avancarDataAtual(int dias) {
        if (dias <= 0) {
            // The value of dias must be positive, otherwise, the operation has no effect.
            return dias;
        }

        diaAtual += dias;

        // Call the method to check overdue Requisicoes and update user status and divida
        verificarRequisicoes();

        return dias;
    }



    public void setDiaAtual(int diaAtual) {
        this.diaAtual = diaAtual;
    }
    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename of the file to load
     * @throws BadEntrySpecificationException
     * @throws IOException
     */
    public void importFile(String filename) throws BadEntrySpecificationException, IOException {
        Scanner s = new Scanner(new File(filename));
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] elementos = line.split(":", 0);
            switch (elementos[0]) {
                case "USER":
                    this.criarUtente(elementos[1], elementos[2]);
                    break;
                case "BOOK":
                    this.criarLivro(elementos[1], elementos[2], Integer.parseInt(elementos[3]), elementos[4], elementos[5], Integer.parseInt(elementos[6]));
                    break;
                case "DVD":
                    this.criarDVD(elementos[1], elementos[2], Integer.parseInt(elementos[3]), elementos[4], elementos[5], Integer.parseInt(elementos[6]));
                    break;
                default:
                    throw new BadEntrySpecificationException("Unknown type of category");
            }
        }
        s.close();
    }


    public Work getWorkByID(int idWork) {
        return works.get(idWork);
    }


    /**
     * Creates a new user (Utentes) and adds it to the users map with a unique identifier.
     *
     * @param nome  the name of the user
     * @param email the email of the user
     */
    public void criarUtente(String nome, String email) {
        // Generate a unique identifier for the new user
        //int newUserId = generateUniqueUserId();
        Utentes newUser = new Utentes(proximo_utente, nome, email);

        todosUtentes.put(proximo_utente, newUser);
        proximo_utente++;

    }


    /**
     * Creates a new book (Livro) and adds it to the works map with a unique identifier.
     *
     * @param title     the title of the book
     * @param author    the author of the book
     * @param price     the price of the book
     * @param categoria the category of the book (Categorias enum as String)
     * @param isbn      the ISBN of the book
     * @param copies    the total number of copies of the book
     */
    public void criarLivro(String title, String author, int price, String categoria, String isbn, int copies) {
        Categorias categoriaEnum;
        switch (categoria.toUpperCase()) {
            case "REFERENCE":
                categoriaEnum = Categorias.REFERENCIA;
                break;
            case "FICTION":
                categoriaEnum = Categorias.FICTION;
                break;
            case "SCITECH":
                categoriaEnum = Categorias.SCITECH;
                break;
            default:
                throw new IllegalArgumentException("Invalid categoria: " + categoria);
        }

        Book newBook = new Book(title, author, price, categoriaEnum, TipoObra.Book, copies, isbn);

        works.put(proximo_livro, newBook);
        proximo_livro++;
    }


    /**
     * Creates a new book (Livro) and adds it to the works map with a unique identifier.
     *
     * @param title     the title of the book
     * @param director  the author of the book
     * @param price     the price of the book
     * @param categoria the category of the book (Categorias enum as String)
     * @param IGAC      the ISBN of the book
     * @param copies    the total number of copies of the book
     */
    public void criarDVD(String title, String director, int price, String categoria, String IGAC, int copies) {
        Categorias categoriaEnum;
        switch (categoria.toUpperCase()) {
            case "REFERENCIA":
                categoriaEnum = Categorias.REFERENCIA;
                break;
            case "FICTION":
                categoriaEnum = Categorias.FICTION;
                break;
            case "SCITECH":
                categoriaEnum = Categorias.SCITECH;
                break;
            default:
                throw new IllegalArgumentException("Invalid categoria: " + categoria);
        }

        DVD newDVD = new DVD(title, director, price, categoriaEnum, TipoObra.DVD, copies, IGAC);

        works.put(proximo_livro, newDVD);
        proximo_livro++;
    }

    public ArrayList<Utentes> ShowUsers() {
        ArrayList<Utentes> listaut = new ArrayList<>(this.todosUtentes.values());
        Collections.sort(listaut);
        return listaut;
    }

    public void ShowUser(int id) {
        ArrayList<Utentes> NovoUtente = new ArrayList<>(this.todosUtentes.values());
        for (Utentes u : NovoUtente) {
            if (u.getNumUtente() == (id)) {
                System.out.println(u);
            }
        }

    }


    /**
     * Get a formatted string representation of all Utentes objects.
     *
     * @return A string containing details of all Utentes.
     */
    public String getUtentes() {
        StringBuilder sb = new StringBuilder();
        for (Utentes utente : todosUtentes.values()) {
            sb.append(utente.toString()).append("\n");
        }
        return sb.toString();
    }

    // Function to show all works in the library
    public ArrayList<Work> showWorks() {
        ArrayList<Work> listWorks = new ArrayList<>(this.works.values());
        Collections.sort(listWorks);
        return listWorks;
    }

    // Function to show work by its ID
    public void showWorkByID(int id) {
        Work work = works.get(id);
        if (work != null) {
            System.out.println(work);
        } else {
            System.out.println("Work with ID " + id + " not found.");
        }
    }

    /**
     * Get a formatted string representation of all works.
     *
     * @return A string containing details of all works.
     */
    public String getWorks() {
        StringBuilder sb = new StringBuilder();
        for (Work work : works.values()) {
            sb.append(work.toString()).append("\n");
        }
        return sb.toString();
    }

    // Function to perform search for works in the library based on the given search term
// Function to perform search for works in the library based on the given search term
    public ArrayList<Work> performSearch(String searchTerm) {
        searchTerm = searchTerm.toLowerCase().trim();
        ArrayList<Work> matchingWorks = new ArrayList<>();

        for (Work work : works.values()) {
            // Check if the search term matches the title
            if (work.getTitle().toLowerCase().contains(searchTerm)) {
                matchingWorks.add(work);
            }

            // Check if the search term matches the author for books
            if (work instanceof Book) {
                Book book = (Book) work;
                if (book.getAuthor().toLowerCase().contains(searchTerm)) {
                    matchingWorks.add(book);
                }
            }

            // Check if the search term matches the director for DVDs
            if (work instanceof DVD) {
                DVD dvd = (DVD) work;
                if (dvd.getDirector().toLowerCase().contains(searchTerm)) {
                    matchingWorks.add(dvd);
                }
            }
        }

        // Sort the matching works by their IDs
        Collections.sort(matchingWorks);

        // Remove duplicate works, if any
        Set<Work> uniqueMatchingWorks = new HashSet<>(matchingWorks);
        return new ArrayList<>(uniqueMatchingWorks);
    }

    public void pagarMulta(int idUtente) {
        Utentes utente = todosUtentes.get(idUtente);

        if (utente != null) {
            utente.setDivida(0.0);
        }
    }

    // Check if a user is suspended
    public boolean isUserSuspended(int userId) {
        Utentes user = todosUtentes.get(userId);
        return user != null && user.getEstado() == CategoriasUser.UserStatus.SUSPENDED;
    }

    // Inside the LibraryManager class
    public void requisitarObra(int idUtente, int obraId, int dataRequisicao, int dataDevolucao) {
        Utentes utente = todosUtentes.get(idUtente);

        if (utente == null) {
            System.out.println("Utente not found.");
            return;
        }

        // Find the work by its ID (obraId)
        Work obra = works.get(obraId);

        if (obra == null) {
            System.out.println("Work not found.");
            return;
        }

        // For now, we will just create the requisicao object and add it to the user's requisicoes list
        utente.requisitarObraUtentes(obra, dataRequisicao, dataDevolucao);

        // Decrement the available copies of the work
        obra.setNumCopiesDisponiveis(obra.getNumCopiesDisponiveis() - 1);

        // Create a notificacao of type REQUISIÇÃO and add it to the user's notifications
        Notificacao notificacaoRequisicao = new Notificacao(false, obra);
        utente.adicionarNotificacao(notificacaoRequisicao);

        // Check if the Requisicao is overdue (ENTREGA) and add ENTREGA notification
        int currentDay = getDiaAtual();
        if (currentDay > dataDevolucao) {
            double divida = utente.getDivida();
            int daysOverdue = currentDay - dataDevolucao;
            divida += (daysOverdue * 5.0); // 5 euros per day of overdue
            utente.setDivida(divida);
            utente.setEstado(CategoriasUser.UserStatus.SUSPENDED);

            // Create a notificacao of type ENTREGA and add it to the user's notifications
            Notificacao notificacaoEntrega = new Notificacao(false, obra);
            notificacaoEntrega.setDivida(divida);
            utente.adicionarNotificacao(notificacaoEntrega);
        }
    }






    /**
     * Get a user (Utentes) based on the user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the Utentes object corresponding to the given user ID
     * @throws Utentes.UserNotFoundException if the user with the given ID is not found
     */
    public Utentes getUtente(int userId) throws Utentes.UserNotFoundException {
        Utentes utente = todosUtentes.get(userId);
        if (utente == null) {
            throw new Utentes.UserNotFoundException(userId);
        }
        return utente;
    }

    public void mostrarNotificacoesUtente(int idUtente) throws Utentes.UserNotFoundException {
        Utentes utente = todosUtentes.get(idUtente);

        if (utente == null) {
            throw new Utentes.UserNotFoundException(idUtente);
        }

        ArrayList<Notificacao> notificacoes = utente.getNotificacoes();

        if (notificacoes.isEmpty()) {
            System.out.println("Sem notificações para o utilizador " + utente.getNome());
            return;
        }

        System.out.println("Notificações para o utilizador: " + utente.getNome() + ":");

        for (Notificacao notificacao : notificacoes) {
            System.out.println(notificacao.toString());
        }

        // Clear the user's notifications after displaying them
        utente.clearNotificacoes();
    }






    public void verificarRequisicoes() {
        int currentDay = getDiaAtual();

        for (Utentes utente : todosUtentes.values()) {
            ArrayList<Requisicao> requisicoes = utente.getRequisicoes();
            ArrayList<Notificacao> notificacoes = utente.getNotificacoes();

            // Check for overdue Requisicoes (ENTREGA)
            for (Requisicao requisicao : requisicoes) {
                if (currentDay > requisicao.getDataDevolucao()) {
                    int daysOverdue = currentDay - requisicao.getDataDevolucao();
                    double divida = utente.getDivida();
                    divida += (daysOverdue * 5.0); // 5 euros per day of overdue
                    utente.setDivida(divida);
                    utente.setEstado(CategoriasUser.UserStatus.SUSPENDED);
                }
            }

            // Clear the notifications
            notificacoes.clear();
        }
    }



    public void devolverObra(int idUtente, int obraId) {
        Utentes utente = todosUtentes.get(idUtente);
        Work obra = works.get(obraId);

        if (utente != null && obra != null) {
            utente.devolverObraUtentes(obra);
            obra.setNumCopiesDisponiveis(obra.getNumCopiesDisponiveis() + 1);

            // Create an "ENTREGA" notification for the obra
            Notificacao entregaNotification = new Notificacao(true, obra);
            utente.adicionarNotificacao(entregaNotification);
        }
    }




} //END





