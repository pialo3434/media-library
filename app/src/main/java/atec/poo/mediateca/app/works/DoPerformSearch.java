package atec.poo.mediateca.app.works;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Work;
import atec.poo.ui.Comando;
import atec.poo.ui.LerString;

import java.util.ArrayList;

/**
 * Conforme Enunciado
 * 4.3.3. Pesquisar Obras
 */
public class DoPerformSearch extends Comando<LibraryManager> {

  /**
   * @param receiver
   */
  public DoPerformSearch(LibraryManager receiver) {
    super(receiver, Label.PERFORM_SEARCH);
  }

  @Override
  public final void executar() {
    // Read the user's input for the search term
    LerString searchTermInput = new LerString(Message.requestSearchTerm(), ".*");
    ui.lerInput(searchTermInput);
    String searchTerm = searchTermInput.getValor().toLowerCase().trim();

    // Perform the search using the searchTerm
    ArrayList<Work> matchingWorks = getReceptor().performSearch(searchTerm);

    // Display the matching works
    for (Work work : matchingWorks) {
      System.out.println(work);
    }
  }
}
