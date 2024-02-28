package atec.poo.mediateca.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

/**
 * Class encoding user registration failure.
 */
public class UserRegistrationFailedException extends DialogException {

    /**
     * Serial number for serialization.
     */
    static final long serialVersionUID = 201901091828L;

    /**
     * Bad user's name.
     */
    private String _name;

    /**
     * Bad user's email.
     */
    private String _email;

    /**
     * @param name
     * @param email
     */
    public UserRegistrationFailedException(String name, String email) {
        _name = name;
        _email = email;
    }


    @Override
    public String getMessage() {
        return Message.userRegistrationFailed(_name, _email);
    }

}
