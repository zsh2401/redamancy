package org.redamancy.server.event;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

/**
 * Cancelable Event(Support set or get value that dedicates value of if cancel.)
 */

public class CancelableEvent extends Event {

    public enum CancelMode {
        DEFAULT,
        ONE_VOTE_TO_CANCEL,
        ONE_VOTE_TO_NO_CANCEL,
    }

    private final CancelMode cancelMode;

    @Getter
    private boolean cancelled;

    public CancelableEvent(Object sender, CancelMode mode) {
        super(sender);
        this.cancelMode = mode;
    }

    public void cancel() {
    }

    private static final CancelMode DEFAULT_CANCEL_MODE = CancelMode.ONE_VOTE_TO_CANCEL;
    private static final boolean DEFAULT_CANCEL_VALUE = false;
    private static final boolean COULD_RESET_CANCEL_MODE = true;

    public void setCancel(boolean value) {
        switch (this.cancelMode) {
            case ONE_VOTE_TO_CANCEL:
                cancelled = cancelled || value;
            case ONE_VOTE_TO_NO_CANCEL:
                cancelled = cancelled && value;
            case DEFAULT:
            default:
                cancelled = value;
                break;
        }
    }
}
