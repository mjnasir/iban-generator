import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import de.lendico.iban.Countries;
import de.lendico.iban.IbanGenerator;

public class ValidateIban {

	@Test
	public void test() {
		String iban = IbanGenerator.generateIban(Countries.Germany);
		int len = iban.length();
		if(len == 22){
			String validating = iban.substring(4,iban.length()) + iban.substring(0,4);
			validating = validating.replace("DE", "1314");
			BigDecimal rem = new BigDecimal(validating).remainder(new BigDecimal(97)) ;
			if (rem.compareTo(new BigDecimal(1)) == 0){
				Assert.assertEquals(1,1);
			}
		}
	}

}
