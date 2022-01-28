package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriToimiiJosNegatiivinenTilavuus() {
        Varasto v = new Varasto(-1,2);
        Varasto v2 = new Varasto(-1);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAlkuSaldoLisaaOikeanMaaran() {
        Varasto v = new Varasto(10,2);
        assertEquals(8, v.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAlkusaldoEiVoiOllaNegatiivinen() {
        Varasto v = new Varasto(10,-2);
        assertEquals(10, v.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaVainSenMikäMahtuu() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysPaljonkoMahtuuEiNegatiivinen() {
        varasto.lisaaVarastoon(12);

        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiVoiLisätäYliTilavuuden() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(-12);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenTäytyyOllaPositiivinen() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(-2);

        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenSaldoOikeaJosMeneeYli() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(12);

        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMääränJosSaldoEiRiitä() {
        varasto.lisaaVarastoon(8);

        double d = varasto.otaVarastosta(12);

        assertEquals(8, d, vertailuTarkkuus);
    }

    @Test
    public void toStringToimiiOikein() {
        varasto.lisaaVarastoon(8);

        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }
}