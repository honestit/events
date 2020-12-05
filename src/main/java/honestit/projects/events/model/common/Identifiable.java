package honestit.projects.events.model.common;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> {

    T getId();

    void setId(T id);
}
