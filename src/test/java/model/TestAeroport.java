package model;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class TestAeroport {

    private Aeroport aeroport;

    @Before
    public void intancier() {
        this.aeroport = new AeroportImpl();
    }


    @Test
    public void testgoHomeAucunPassagerFievreux(){
        SystemeThermique systemeThermique;
        Collection<Passager> passagers;

        Passager passager1 = EasyMock.createMock(Passager.class);
        Passager passager2 = EasyMock.createMock(Passager.class);
        Passager passager3 = EasyMock.createMock(Passager.class);


        systemeThermique = EasyMock.createMock(SystemeThermique.class);
        EasyMock.expect(systemeThermique.detecter(passager1)).andReturn(false);
        EasyMock.expect(systemeThermique.detecter(passager2)).andReturn(false);
        EasyMock.expect(systemeThermique.detecter(passager3)).andReturn(false);

        EasyMock.replay(passager1,passager2,passager3,systemeThermique);
        passagers = Arrays.asList(passager1,passager2,passager3);

        aeroport.setSystemeThermique(systemeThermique);
        passagers = Arrays.asList(passager1,passager2,passager3);
        aeroport.setPassagers(passagers);

        Collection<Passager> passagerfievreux = aeroport.goBackHome();

        Assert.assertTrue(passagerfievreux.size() == 0);

    }

    @Test
    public void testgoHomeDeuxPassagerFievreux(){
        SystemeThermique systemeThermique;
        Collection<Passager> passagers;

        Passager passager1 = EasyMock.createMock(Passager.class);
        Passager passager2 = EasyMock.createMock(Passager.class);
        Passager passager3 = EasyMock.createMock(Passager.class);


        systemeThermique = EasyMock.createMock(SystemeThermique.class);
        EasyMock.expect(systemeThermique.detecter(passager1)).andReturn(true);
        EasyMock.expect(systemeThermique.detecter(passager2)).andReturn(true);
        EasyMock.expect(systemeThermique.detecter(passager3)).andReturn(false);

        EasyMock.replay(passager1,passager2,passager3,systemeThermique);

        aeroport.setSystemeThermique(systemeThermique);
        passagers = Arrays.asList(passager1,passager2,passager3);
        aeroport.setPassagers(passagers);

        Collection<Passager> passagerfievreux = aeroport.goBackHome();

        Assert.assertTrue(passagerfievreux.size() == 2);

    }
}
