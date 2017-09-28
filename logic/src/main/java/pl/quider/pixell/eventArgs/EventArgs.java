package pl.quider.pixell.eventArgs;

import java.util.Date;

public abstract class EventArgs {
    protected final Date occureDate;
    protected final Object sender;

    public EventArgs(Object sender) {
        this.occureDate = new Date();
        this.sender = sender;
    }

    public Date getOccureDate() {
        return occureDate;
    }

}
