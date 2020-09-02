
package acme.components;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import acme.datatypes.Email;

public class EmailFormatter implements Formatter<Email> {

	// Formatter<Money> interface ----------------------------------------

	@Override
	public String print(final Email object, final Locale locale) {
		assert object != null;
		assert locale != null;

		String result;
		String displayNameText, userText, domainNameText, domainExtensionText;

		displayNameText = String.format("%s", object.getDisplayName());
		userText = String.format("%s", object.getUser());
		domainNameText = String.format("%s", object.getDomainName());
		domainExtensionText = String.format("%s", object.getDomainExtension());

		result = String.format("+%s%s%s", displayNameText, userText, domainNameText, domainExtensionText);

		return result;
	}

	@Override
	public Email parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override
	 * public Email parse(final String text, final Locale locale) throws ParseException {
	 * assert !StringHelper.isBlank(text);
	 * assert locale != null;
	 *
	 * Email result;
	 * String displayNameText, userText, domainNameText, domainExtensionText;
	 * Pattern pattern;
	 * Matcher matcher;
	 * String errorMessage;
	 * String countryCodeText;
	 * int countryCode;
	 * String areaCode, number;
	 *
	 * displayNameText = "\\+\\d{1,3}";
	 * userText = "\\d{1,6}";
	 * domainNameText = "\\d{1,9}([\\s.]\\d{1,9}){0,5}";
	 * //phoneRegex = String.format("^\\s*(?<CC>)%1$s)(\\s+\\((?<AC>%")
	 * emailRegex = String.format(//
	 * "^\\s*(?<CC>%1$s)(\\s+\\((?<AC>%2$s)\\)\\s+|\\s+|\\s+)(?<N>%3$s)\\s*$", countryCodeRegex, //
	 * areaCodeRegex, //
	 * numberRegex //
	 * );
	 *
	 * pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
	 * matcher = pattern.matcher(text);
	 *
	 * if (!matcher.find()) {
	 * errorMessage = MessageHelper.getMessage("default.error.conversion", null, "Invalid value", locale);
	 * throw new ParseException(errorMessage, 0);
	 * } else {
	 * countryCodeText = matcher.group("CC");
	 * countryCode = Integer.valueOf(countryCodeText);
	 * areaCode = matcher.group("AC");
	 * number = matcher.group("N");
	 *
	 * result = new Email();
	 * result.set(countryCode);
	 * result.setAreaCode(areaCode);
	 * result.setNumber(number);
	 * }
	 *
	 * return result;
	 *
	 * }
	 */

}
