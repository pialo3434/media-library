package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.UserIsActiveException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme enunciado
 * 4.2.5. Pagar Multa
 */
public class DoPayFine extends Comando<LibraryManager> {

    /**
     * @param receiver
     */
    public DoPayFine(LibraryManager receiver) {
        super(receiver, Label.PAY_FINE);
    }

    @Override
    public final void executar() throws DialogException {
        int suspendedUserId = -1; // Default value if not suspended

        try {
            LerInteiro input = new LerInteiro(Message.requestUserId());
            ui.lerInput(input);

            int userId = input.getValor();

            if (!this.getReceptor().isUserSuspended(userId)) {
                suspendedUserId = userId;
                throw new UserIsActiveException(userId);
            }

            this.getReceptor().pagarMulta(userId);
            ui.escreveLinha("Multa paga com sucesso. Utente desbloqueado.");
        } catch (UserIsActiveException e) {
            ui.escreveLinha("O utente com ID " + suspendedUserId + " não está suspenso. Nenhuma multa a pagar.");
        } catch (NumberFormatException e) {
            ui.escreveLinha("Ocorreu um erro ao ler o identificador do utente.");
        } catch (Exception e) {
            ui.escreveLinha("Ocorreu um erro ao pagar a multa.");
        }
    }
}
