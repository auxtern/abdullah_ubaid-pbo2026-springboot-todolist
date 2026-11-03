package org.delcom.abdullah_ubaidpbo2026springboottodolist.domain.exception;

/**
 * Checked exception dasar untuk hierarki domain model aplikasi Todo List.
 */
public class DomainException extends Exception {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
