package pl.sda.magcie.weather.convert;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PolishToEnglishLettersConverterTest {

    @Test
    void convert() {
        //given
        String polishString="Łańcut";
        //when
        String englishString=PolishToEnglishLettersConverter.convert(polishString);
        //then
        assertThat(englishString).isEqualTo("lancut");
    }
}