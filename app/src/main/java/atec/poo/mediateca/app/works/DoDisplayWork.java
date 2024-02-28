package atec.poo.mediateca.app.works;

import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Work;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

import java.util.InputMismatchException;

/**
 * 4.3.1. Mostrar Obra.
 */
public class DoDisplayWork extends Comando<LibraryManager> {
    private LerInteiro idWork;

    public DoDisplayWork(LibraryManager libraryManager) {
        super(libraryManager, Label.SHOW_WORK);
        this.idWork = new LerInteiro(Message.requestWorkId());
    }

    @Override
    public final void executar() throws DialogException {
        try {
            ui.lerInput(this.idWork);
            int workID = idWork.getValor();
            Work work = getReceptor().getWorkByID(workID);
            if (work != null) {
                System.out.println(work); // Implicitly calls toString() of the Work superclass
            } else {
                System.out.println("Work with ID " + workID + " not found.");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            throw new NoSuchWorkException(idWork.getValor());
        }
    }
}
