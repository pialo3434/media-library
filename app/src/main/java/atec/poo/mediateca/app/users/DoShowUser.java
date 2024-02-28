package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Utentes;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme Enunciado
 * 4.2.2. Mostrar Utente.
 */
public class DoShowUser extends Comando<LibraryManager> {
    LerInteiro id;

    /**
     * @param receiver
     */
    public DoShowUser(LibraryManager receiver) {
        super(receiver, Label.SHOW_USER);
        this.id = new LerInteiro(Message.requestUserId());

    }

    @Override
    public final void executar() throws DialogException {

        try{
            ui.lerInput(this.id);
            this.getReceptor().ShowUser(id.getValor());
        }catch (Exception e){
            throw new NoSuchWorkException(id.getValor());
        }
    }

}
