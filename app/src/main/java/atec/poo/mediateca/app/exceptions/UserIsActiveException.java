package atec.poo.mediateca.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

/**
 * Class encoding reference to an invalid user state.
 */
public class UserIsActiveException extends DialogException {

    /**
     * Serial number for serialization.
     */
    static final long serialVersionUID = 201901091828L;

    /**
     * Bad user id.
     */
    private int _id;

    /**
     * @param id
     */
    public UserIsActiveException(int id) {
        _id = id;
    }


    @Override
    public String getMessage() {
        return Message.userNotSuspended(_id);
    }

}
