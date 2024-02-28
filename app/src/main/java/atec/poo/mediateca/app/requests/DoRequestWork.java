package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.core.CategoriasUser;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Utentes;
import atec.poo.mediateca.core.Work;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

public class DoRequestWork extends Comando<LibraryManager> {

    /**
     * @param receptor
     */
    public DoRequestWork(LibraryManager receptor) {
        super(receptor, Label.REQUEST_WORK);
    }

    @Override
    public final void executar() throws DialogException {
        LerInteiro idUtente = new LerInteiro("Digite o identificador do utente: ");
        LerInteiro obraId = new LerInteiro("Digite o identificador da obra a requisitar: ");

        ui.lerInput(idUtente);
        ui.lerInput(obraId);

        try {
            // Attempt to request the work
            int dueDate = getDueDate(idUtente.getValor(), String.valueOf(obraId.getValor()));
            this.getReceptor().requisitarObra(idUtente.getValor(), obraId.getValor(), this.getReceptor().getDiaAtual(), dueDate);

            // Show the return day message
            System.out.println("A obra " + obraId.getValor() + " deve ser devolvida no dia " + dueDate + ".");
        } catch (Utentes.UserNotFoundException | Work.WorkNotFoundException ex) {
            // Handle user or work not found exceptions
            System.out.println("User or work not found.");
        } catch (Exception ex) {
            // Handle any other unexpected exceptions
            System.out.println("An unexpected error occurred.");
        }
    }

    // Helper method to calculate the due date for the work requisition
    private int getDueDate(int idUtente, String obraId) throws Utentes.UserNotFoundException, Work.WorkNotFoundException {
        // Retrieve the user and work objects
        Utentes utente = this.getReceptor().getUtente(idUtente);
        Work obra = this.getReceptor().getWorkByID(Integer.parseInt(obraId));

        // Get the number of total exemplares and conduct of the user from the estado attribute
        int numExemplares = obra.getNumCopies();
        CategoriasUser.UserStatus estado = utente.getEstado();

        // Calculate the due date based on the rules specified in the enunciado
        if (numExemplares == 1) {
            return this.getReceptor().getDiaAtual() + (estado == CategoriasUser.UserStatus.CUMPRIDOR ? 8 : 3);
        } else if (numExemplares <= 5) {
            return this.getReceptor().getDiaAtual() + (estado == CategoriasUser.UserStatus.CUMPRIDOR ? 15 : 8);
        } else {
            return this.getReceptor().getDiaAtual() + (estado == CategoriasUser.UserStatus.CUMPRIDOR ? 30 : 15);
        }
    }
}
