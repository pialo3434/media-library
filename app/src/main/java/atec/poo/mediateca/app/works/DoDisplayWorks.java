// DoDisplayWorks.java
package atec.poo.mediateca.app.works;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Work;
import atec.poo.ui.Comando;

import java.util.Collections;
import java.util.List;

/**
 * 4.3.2. Listar Obras
 */
public class DoDisplayWorks extends Comando<LibraryManager> {

    public DoDisplayWorks(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORKS);
    }

    @Override
    public final void executar() {
        List<Work> worksList = this.getReceptor().showWorks();
        for (Work work : worksList) {
            System.out.println(work);
        }
    }
}
