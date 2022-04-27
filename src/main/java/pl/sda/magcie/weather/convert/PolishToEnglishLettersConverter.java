package pl.sda.magcie.weather.convert;

public class PolishToEnglishLettersConverter {

    public static String convert(String polishString) {
        polishString=polishString.toLowerCase();
        polishString = polishString.replace('ą', 'a');
        polishString = polishString.replace('ę', 'e');
        polishString = polishString.replace('ó','o');
        polishString = polishString.replace('ń','n');
        polishString = polishString.replace('ł','l');
        polishString = polishString.replace('ż','z');
        polishString = polishString.replace('ź','z');
        polishString = polishString.replace('ć','c');
        polishString = polishString.replace('ś','s');
        return polishString;
    }
}