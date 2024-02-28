package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Utentes;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme enunciado
 * 4.2.3. Mostrar Notificações do Utente
 *
 */
public class DoShowUserNotifications extends Comando<LibraryManager> {

  private final LerInteiro id;

  /**
   * @param receiver
   */
  public DoShowUserNotifications(LibraryManager receiver) {
    super(receiver, Label.SHOW_USER_NOTIFICATIONS);
    this.id = new LerInteiro(Message.requestUserId());
  }

  @Override
  public final void executar() throws DialogException {
    // Prompt for the user ID
    ui.lerInput(this.id);
    try {
      // Call the mostrarNotificacoesUtente function from the LibraryManager
      this.getReceptor().mostrarNotificacoesUtente(id.getValor());
    } catch (Utentes.UserNotFoundException e) {
      System.out.println("User with ID"  + id.getValor() + " not found.");
    }
  }
}
