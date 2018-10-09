package fr.eni.tp.qcm.common.util;

import fr.eni.tp.qcm.common.exception.TechnicalException;

public class ResourceUtil {

    /**
     * Safe closing resource connections.
     * 
     * @param closeables The closeable resources
     */
    public static void safeClose(AutoCloseable... closeables) {
        for(AutoCloseable closeable : closeables) {
            try {
                if(closeable != null) {
                    closeable.close();
                }
            } catch (Exception e) {
                throw new TechnicalException("Could not close resource connection", e);
            }
        }
    }
}
