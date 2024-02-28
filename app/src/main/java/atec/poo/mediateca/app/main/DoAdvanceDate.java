package atec.poo.mediateca.app.main;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme Enunciado
 * 4.1.4. Avançar data atual
 */
public class DoAdvanceDate extends Comando<LibraryManager> {

  /**
   * @param receiver
   */
  public DoAdvanceDate(LibraryManager receiver) {
    super(receiver, Label.ADVANCE_DATE);
  }

  @Override
  public final void executar() throws DialogException {
    try {
      LerInteiro input = new LerInteiro(Message.requestDaysToAdvance());
      ui.lerInput(input);

      int dias = input.getValor();
      int newDate = this.getReceptor().avancarDataAtual(dias);
      ui.escreveLinha(Message.currentDate(newDate));
    } catch (NumberFormatException e) {
      // Caso o input seja invalido...
    }
  }
}
