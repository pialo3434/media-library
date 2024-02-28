package atec.poo.mediateca.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

/**
 * Class encoding reference to an invalid work id.
 */
public class  NoSuchWorkException extends DialogException {

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
    public NoSuchWorkException(int id) {
        _id = id;
    }


    @Override
    public String getMessage() {
        return Message.noSuchWork(_id);
    }

}
