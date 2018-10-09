package fr.eni.tp.qcm.bll.exception;

public class ManagerException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4536038063996540275L;

    /**
     * @param message
     * @param cause
     */
    public ManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public ManagerException(String message) {
        super(message);
    }

}
