
package acme.components;

public class SpamFilter {

	// Business methods -------------------------------------------------------

	public static boolean filterText(final String text, final String spam, final Double threshold) {

		boolean result = false;
		int cont = 0;
		Double resultado;

		//cortamos la cadena spam por comas
		String[] spamText = spam.split(",");
		//cortamos el texto por espacios
		String[] splitedText = text.split(" ");

		//lo recorremos si hay palabra en spam añadimos 1 al contador
		for (String wordSpam : spamText) {
			for (String wordText : splitedText) {
				if (wordText.equals(wordSpam)) {
					cont += 1;
				}
			}
		}

		resultado = (double) (cont / splitedText.length);

		if (threshold <= resultado) {
			result = true;
		}

		return result;
	}

	public static String[] splitText(final String text) {

		String[] result = text.split(",");

		return result;
	}

}
