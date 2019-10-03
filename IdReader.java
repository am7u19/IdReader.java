import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;

public class IdReader{
	
	public static void main(String[] args) {
		
		BufferedReader input = new BufferedReader(
		    new InputStreamReader(System.in)); // buffers input from keyboard so that it is faster
		
		try { // tries block of code to see if it works without errors
			System.out.println("Type ID:");
			String id = input.readLine(); // reads a line from the buffered stream
			
			String webPageAdress =
 			    "https://www.ecs.soton.ac.uk/people/" + id; // concatenates the two strings
				
			URL webPageUrl = new URL(webPageAdress); // creates an URL from the web page adress
			BufferedReader urlReader = new BufferedReader(
			    new InputStreamReader(webPageUrl.openStream())); // buffers input from URL
				
			String urlLine;
			while ((urlLine = urlReader.readLine()) != null) {	// goes through URL source
				if (urlLine.contains("property=\"name\">")) { 
					break;	// if it finds substring, goes out of loop, retaining line in memory
				}
			}
			
			Integer nameStart = urlLine.indexOf(
			    "property=\"name\">") + 16; // finds first char of name
			Integer nameEnd = urlLine.indexOf('<', nameStart); // finds last char of name
			
			String name = urlLine.substring(
			    nameStart, nameEnd); // finds substring containing name
			System.out.println(name); // prints name
			
		} catch(IOException e) { // if block of code had an error, outputs that an error occured
			System.out.println("IOException occured");
		}
	}
}