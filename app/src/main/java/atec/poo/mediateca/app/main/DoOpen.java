package atec.poo.mediateca.app.main;

import atec.poo.ui.exceptions.DialogException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * Conforme enunciado
 * 4.1.1 Abrir um ficheiro de dados
 */
public class DoOpen extends Comando<LibraryManager> {

  /**
   * @param receiver
   */
  public DoOpen(LibraryManager receiver) {
    super(receiver,Label.OPEN);

  }

  @Override
  public final void executar() throws DialogException {
    ui.escreveLinha("---> Carregar aplicação a partir de ficheiro");// A apagar. Só indicativo
  }
}
