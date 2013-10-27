package resources;

import java.util.HashMap;

public class ResourcesMapper {
	
	private static ResourcesMapper instance;
	
	public static ResourcesMapper getInstance(){
		if(instance==null)
		{
			instance=new ResourcesMapper();
		}
		return instance;
	}
	
	private ResourcesMapper(){
		
	}
	
	private HashMap<String,Class> mapping=new HashMap<String,Class>();
	
	public void registerResClass(String resClassifier, Class javaClass)
	{
		this.mapping.put(resClassifier, javaClass);
	}
	
	public Class getResClass(String resClassifier)
	{
		return this.mapping.get(resClassifier);
	}
}
