package de.lendico.iban;
import java.util.HashMap;
import java.util.Map;

class FormatProvider {
	Map<String, String> ibanFormats;
	private static FormatProvider instance = null;

	public synchronized static String getInstance(Countries country) {

		if (instance == null) {
			instance = new FormatProvider();
		}
		return instance.getFormat(country.toString());
	}

	private FormatProvider() {
		ibanFormats = new HashMap();
		// Add new countries below
		ibanFormats.put("Germany", "DE2!n8!n10!n");
		ibanFormats.put("Austria", "AT2!n5!n11!n");
		ibanFormats.put("Netherlands", "NL2!n4!a10!n");
		ibanFormats.put("Belgium", "BE2!n3!n7!n2!n");
		ibanFormats.put("Czech_Republic", "CZ2!n4!n6!n10!n");
		ibanFormats.put("Denmark", "DK2!n4!n9!n1!n");
		ibanFormats.put("United_Kingdom", "GB2!n4!a6!n8!n");
		ibanFormats.put("France", "FR2!n5!n5!n11!a2!n");
	}

	public String getFormat(String country) {
		if (ibanFormats.containsKey(country))
			return ibanFormats.get(country).toString();
		else
			return "Country Not Found";
	}
}
