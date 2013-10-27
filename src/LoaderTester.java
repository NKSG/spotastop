import rest.RestApi;


public class LoaderTester {

	public static void main(String[] args) {
		RestApi.setServerUrl("http://192.168.159.115");
		System.out.println(RestApi.requestResource("nicfix\\apps\\places\\Place",1).get(0).getClass());
		
	}

}
