package util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;   
import org.jdom2.Document;   
import org.jdom2.Element;   
import org.jdom2.JDOMException;   
import org.jdom2.input.SAXBuilder;   
import org.jdom2.output.XMLOutputter;   
public class XmlParse {
	public Dictionary<String, Rect> rects = new Hashtable<String, Rect>();
	public  void getLocation()
	{
		try
		{
			SAXBuilder builder=new SAXBuilder(false);
			Document document=builder.build("plane.xml");   
			Element employees=document.getRootElement();    
			List employeeList=employees.getChildren("SubTexture");
			
			for(int i = 0; i < employeeList.size(); i ++)
			{
				Element node = (Element)employeeList.get(i);
				Rect r = new Rect();
				r.x = node.getAttribute("x").getIntValue();
				r.y = node.getAttribute("y").getIntValue();
				r.width = node.getAttribute("width").getIntValue();
				r.height = node.getAttribute("height").getIntValue();
				String name2 = node.getAttribute("name").getValue();
				rects.put(name2, r);
				
			}
	 
		} catch (FileNotFoundException e) { 
		System.out.println(e.getMessage()); 
		} catch (IOException e) { 
		System.out.println(e.getMessage()); 
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	public static void main(String[]args)
	{
		XmlParse parse = new XmlParse();
		parse.getLocation();
	}

}

