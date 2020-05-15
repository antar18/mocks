package model;

import java.util.ArrayList;
import java.util.Collection;

public class AeroportImpl implements Aeroport{
    SystemeThermique systemeThermique;
    Collection<Passager> passagers;

    public AeroportImpl(){
        passagers = new ArrayList<>();
    }

    @Override
    public SystemeThermique getSystemeThermique() {
        return systemeThermique;
    }

    public void setSystemeThermique(SystemeThermique systemeThermique) {
        this.systemeThermique = systemeThermique;
    }

    @Override
    public Collection<Passager> getPassagers() {
        return passagers;
    }

    public void setPassagers(Collection<Passager> passagers) {
        this.passagers = passagers;
    }



    @Override
    public Collection<Passager> goBackHome(){
        final Collection<Passager> nonAcceptes = new ArrayList<>();

        for (Passager passager : this.getPassagers()){
            if (this.getSystemeThermique().detecter(passager)){
                nonAcceptes.add(passager);
            }
        }
        return nonAcceptes;

    }
}























