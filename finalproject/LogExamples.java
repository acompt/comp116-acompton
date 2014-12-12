//create list of locations with user names for each "log"

import java.util.List;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;

public class LogExamples {

    public static void main(String[] args) {
        try {
            LogExamples logs = new LogExamples();
            Map<String, Collection<String>> map = logs.initComponents();
            //iterate through the key set and display key and values
            for (String key :map.keySet()) {
                System.out.println("Location = " + key);
                System.out.println("Users = " + map.get(key));
                System.out.println("Users = " + map.get(key).size());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	public Map<String, Collection<String>> initComponents() {
 
        Map<String, Collection<String>> map = new HashMap<String, Collection<String>>();

        //can go through logs using AWS log parser to get user name and location

		try {
            BufferedReader br = new BufferedReader(new FileReader("test.txt"));
            String line;
     		// if no more lines the readLine() returns null
     		while ((line = br.readLine()) != null) {
          	// reading lines until the end of the file
     			String [] words = line.split(" ");
     			String location = words[0];
     			String user = words[1];

     			Collection<String> users = map.get(location);
    			if (users==null) {
        			users = new ArrayList<String>();
        			map.put(location, users);
    			}
    			users.add(user);
     		}
            br.close();
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException e){
            System.out.println(e);
        }

        return map;
        
	}
}



