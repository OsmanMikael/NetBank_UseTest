package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    //test hentKundeInfo
    @Test
    public void hentKundeInfo_loggetInn() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }


    //test hentKonti
    @Test
    public void hentKonti_LoggetInn() {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("105010123456");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn() {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    //test hentSaldi
    @Test
    public void hentSaldi_LoggetINN() {
        List<Konto> saldi = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        saldi.add(konto1);
        saldi.add(konto2);
        //arrange
        when(sjekk.loggetInn()).thenReturn("105010123456");

        when(repository.hentSaldi(anyString())).thenReturn(saldi);

        //act
        List<Konto> resultat = bankController.hentSaldi();

        //assert
        assertEquals(saldi, resultat);
    }

    @Test
    public void hentSaldi_IkkeLoggetInn() {
        //arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        List<Konto> resultat = bankController.hentSaldi();

        //assert
        assertNull(resultat);
    }

    //test hentBetalinger
    @Test
    public void hentBetalinger_LoggetInn() {
        //arrange
        List<Transaksjon> betalinger = new ArrayList<>();
        Transaksjon transaksjon1 = new Transaksjon(1, "47714545445", 125000.00, "01.01.20", "Hei Osman", "1", "4711240045");
        Transaksjon transaksjon2 = new Transaksjon(2, "33314545445", 333000.00, "03.03.23", "Hei Ali", "1", "3331240045");

        betalinger.add(transaksjon1);
        betalinger.add(transaksjon2);

        when(sjekk.loggetInn()).thenReturn("4711240045");
        when(repository.hentBetalinger(anyString())).thenReturn(betalinger);

        //act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        //assert
        assertEquals(betalinger, resultat);
    }

    @Test
    public void hentBetalinger_IkkeLoggetInn() {
        //arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        //assert
        assertNull(resultat);
    }

    //test utforBetaling
    @Test
    public void utforBetaling_LoggetInn() {
        //arrange


        //act


        //assert

    }

    @Test
    public void utforBetaling_IkkeLoggetInn() {
        //arrange


        //act


        //assert

    }

    //test endreKundeInfo
    @Test
    /**/ public void endreKundeInfo_LoggetInn() {
        //arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("ok");


        //act
        String resultat = bankController.endre(enKunde);


        //assert
        assertEquals("ok", resultat);
    }

    @Test
    public void endreKundeInfo_IkkeLoggetInn() {
        //arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = bankController.endre(enKunde);

        //assert
        assertNull(resultat);
    }

    //test registrerBetaling:
    @Test
    public void registrerBetaling_LoggetInn() {
        //arrange
        Transaksjon transaksjon1 = new Transaksjon(121, "47714545445", 125000.00, "01.01.20", "Hei Osman", "en dag", "4711240045");
        when(sjekk.loggetInn()).thenReturn("1212121");
        when(repository.registrerBetaling(any(Transaksjon.class))).thenReturn("ok");
        //act
        String resultat = bankController.registrerBetaling(transaksjon1);

        //arrest
        assertEquals("ok", resultat);
    }

    @Test
    public void registrerBetaling_IkkeLoggetInn() {
        //arrange
        Transaksjon transaksjon1 = new Transaksjon(121, "47714545445", 125000.00, "01.01.20", "Hei Osman", "en dag", "4711240045");
        when(sjekk.loggetInn()).thenReturn("1212121");
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = bankController.registrerBetaling(transaksjon1);

        //arrest
        assertNull(resultat);
    }

    //test hentTransaksjoner:
    @Test
    public void hentTransaksjoner_LoggetInn() {
        //arrange
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        when(sjekk.loggetInn()).thenReturn("105010123456");
        when(repository.hentTransaksjoner(anyString(), anyString(), anyString())).thenReturn(konto1);

        //act
        Konto resultat = bankController.hentTransaksjoner("01010110523", "01.02.2024", "01.03.2024");

        //assert
        assertEquals(konto1, resultat);

    }

    @Test
    public void hentTransaksjoner_IkkeLoggetInn() {
        //arrange
        when(sjekk.loggetInn()).thenReturn(null);


        //act
        Konto resultat = bankController.hentTransaksjoner("01010110523", "01.02.2024", "01.03.2024");


        //assert
        assertNull(resultat);
    }
}

