package model;

import java.util.Collection;

public interface Aeroport {

    Collection<Passager> getPassagers();

    void setPassagers(Collection<Passager> passagers);

    SystemeThermique getSystemeThermique();

    void setSystemeThermique(SystemeThermique systemThermique);

    Collection<Passager> goBackHome();

}
