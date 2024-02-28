package atec.poo.mediateca.app.main;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

import java.io.IOException;

/**
 * Conforme enunciado
 * 4.1.2. Guardar o estado da aplicação
 */
public class DoSave extends Comando<LibraryManager> {

  /**
   * @param receiver
   */
  public DoSave(LibraryManager receiver) {
    super(receiver, Label.SAVE);
  }

  @Override
  public final void executar() throws DialogException {
    LerString input = new LerString(Message.saveAs(), null);
    ui.lerInput(input);
    String fileName = input.getValor();

    // Get the "imports" directory path
    String importsDirectory = "imports/";

    // Combine the "imports" directory path with the file name and ".import" extension
    String filePath = importsDirectory + fileName + ".import";

    try {
      this.getReceptor().save(filePath);
      ui.escreveLinha("Aplicação guardada com sucesso.");
    } catch (IOException e) {
      ui.escreveLinha("Erro ao guardar a aplicação.");
    }
  }
}
