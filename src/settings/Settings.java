package settings;


public class Settings {

	private static boolean jarvisMode=true;
	
	public static boolean isJarvisMode() {
		return jarvisMode;
	}

	public static void setJarvisMode(boolean jarvisMode) {
		Settings.jarvisMode = jarvisMode;
	}

	private static String serverUrl = "localhost";
	
	private static String jarvisProxyServerUrl= "nicfixplace.altervista.org/Home_Alfonsi/raspberrypi";

	private static String jarvisProxyServerPort= "7026";
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

	public static String getJarvisProxyServerUrl() {
		return jarvisProxyServerUrl;
	}

	public static void setJarvisProxyServerUrl(String jarvisProxyServerUrl) {
		Settings.jarvisProxyServerUrl = jarvisProxyServerUrl;
	}

	public static String getJarvisProxyServerPort() {
		return jarvisProxyServerPort;
	}

	public static void setJarvisProxyServerPort(String jarvisProxyServerPort) {
		Settings.jarvisProxyServerPort = jarvisProxyServerPort;
	}

}
