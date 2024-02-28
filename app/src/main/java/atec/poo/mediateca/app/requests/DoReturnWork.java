package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.WorkNotBorrowedByUserException;
import atec.poo.mediateca.core.*;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Comando<LibraryManager> {

    /**
     * @param receiver
     */
    public DoReturnWork(LibraryManager receiver) {
        super(receiver, Label.RETURN_WORK);
    }

    @Override
    public final void executar() throws DialogException {
        LerInteiro idUtente = new LerInteiro("Digite o identificador do utente: ");
        LerInteiro obraId = new LerInteiro("Digite o identificador da obra a requisitar: ");

        ui.lerInput(idUtente);
        ui.lerInput(obraId);

        Utentes utente = this.getReceptor().getUtente(idUtente.getValor());
        Work obra = this.getReceptor().getWorkByID(obraId.getValor());

        if (utente == null || obra == null) {
            System.out.println("Utilizador não encontrado.");
        }

        // Check if the obra was borrowed by the user
        boolean isObraBorrowed = utente.isObraBorrowed(obra);
        if (!isObraBorrowed) {
            throw new WorkNotBorrowedByUserException(obraId.getValor(), idUtente.getValor());
        }

        // Perform the obra return
        this.getReceptor().devolverObra(idUtente.getValor(), obraId.getValor());


        // Check if there is a fine to be paid
        double divida = utente.getDivida();
        if (divida > 0) {
            boolean paymentChoice = Boolean.parseBoolean(Message.requestFinePaymentChoice());
            if (paymentChoice) {
                Message.showFine(idUtente.getValor(), (int) Math.round(divida));
                utente.setDivida(0); // Clear the divida
                utente.setEstado(CategoriasUser.UserStatus.ACTIVE); // Set the user to active
            }
        }
    }
}
