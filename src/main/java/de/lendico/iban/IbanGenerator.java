package de.lendico.iban;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


public class IbanGenerator {
	
	private static HashSet<String> ibans = new HashSet<>();
    public static String generateIban(Countries country) {
    	boolean generated = false;
    	String exampleIban = null;
        while (!generated ) {
             exampleIban = generateExampleIban(FormatProvider.getInstance(country));
            if (!ibans.contains(exampleIban))
            	
            	synchronized (ibans) {
            		ibans.add(exampleIban);
            		generated = true;
				}
                
        }

        return exampleIban;
    }

    private static String generateExampleIban(String format) {
        String countryCode = format.substring(0,2);
        String bBan = generateExampleBban(format.substring(5));

        String checkDigit = generateCheckDigit(bBan+countryCode+"00");

        if (checkDigit.length()==1)
            checkDigit = "0" + checkDigit;

        return countryCode + checkDigit + bBan;
    }

    private static String generateCheckDigit(String extendedBban) {
        StringBuilder numericRepresentation = new StringBuilder();

        for (int i=0; i<extendedBban.length(); i++) {
            int num = extendedBban.charAt(i);
            if (num > 57) {
                num = extendedBban.charAt(i) - 'A' + 10;
                numericRepresentation.append(num);
            } else {
                numericRepresentation.append(extendedBban.charAt(i));
            }

        }

        BigInteger i = new BigInteger(numericRepresentation.toString()).mod(BigInteger.valueOf(97));
        return BigInteger.valueOf(98).subtract(i).toString();
    }

    private static String generateExampleBban(String bBanFormat) {

        List<String> numbers = Arrays.asList(bBanFormat.replace("n","@").replace("a","@").split("!@"));

        String types = bBanFormat.replaceAll("[0-9]","");

        StringBuilder bBan = new StringBuilder();

        for (int i=0; i<numbers.size(); i++) {
            for (int j=0; j<Integer.parseInt(numbers.get(i)); j++) {
                switch (types.substring(2*i,2*i+2)) {
                    case "!n":
                        bBan.append((new Random()).nextInt(10) + 0);
                        break;
                    case "!a":
                        Random rnd = new Random();
                        char a = (char) (rnd.nextInt(26) + 'A');
                        bBan.append(a);
                        break;
                    default:
                        break;
                }
            }
        }

        return bBan.toString();
    }
}
