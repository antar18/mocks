package model;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class TestAeroportMethodeIsolee {

    private Aeroport aeroport;

    @Before
    public void setAeroport() {
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


        passagers = Arrays.asList(passager1,passager2,passager3);

       this.aeroport = EasyMock.createMockBuilder(AeroportImpl.class)
               .addMockedMethod("getPassagers")
               .addMockedMethod("getSystemeThermique")
               .createMock();

       EasyMock.expect(aeroport.getPassagers()).andReturn(passagers).times(3);
       EasyMock.expect(aeroport.getSystemeThermique()).andReturn(systemeThermique).times(3);

       EasyMock.replay(passager1,passager2,passager3,systemeThermique, aeroport);

       Collection<Passager> passagersFievreux = this.aeroport.goBackHome();

       Assert.assertTrue(passagersFievreux.size() == 0);

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

        passagers = Arrays.asList(passager1,passager2,passager3);

        this.aeroport = EasyMock.createMockBuilder(AeroportImpl.class)
                .addMockedMethod("getPassagers")
                .addMockedMethod("getSystemeThermique")
                .createMock();

        EasyMock.expect(aeroport.getPassagers()).andReturn(passagers).times(3);
        EasyMock.expect(aeroport.getSystemeThermique()).andReturn(systemeThermique).times(3);

        EasyMock.replay(passager1,passager2,passager3,systemeThermique, aeroport);

        Collection<Passager> passagerFievreux = this.aeroport.goBackHome();

        Assert.assertTrue(passagerFievreux.size() == 2);

    }
}
