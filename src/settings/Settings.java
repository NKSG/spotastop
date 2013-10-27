package settings;


public class Settings {

	private static String serverUrl = "localhost";

	/**
	 * Returns the actually used serverUrl
	 * 
	 * @return
	 */
	public static String getServerUrl() {
		return serverUrl;
	}

	/**
	 * Allows to configure the actual serverUrl of all requests
	 * 
	 * @param serverUrl
	 */
	public static void setServerUrl(String serverUrl) {
		Settings.serverUrl = serverUrl;
	}

}
