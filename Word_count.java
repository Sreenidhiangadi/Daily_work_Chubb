package nov_13_assignment;

import java.io.*;

public class Word_count {

	public static void main(String[] args) throws FileNotFoundException {
		int c=0;
		
			BufferedReader br=new BufferedReader(new FileReader("C://Users//Angadi Sreenidhi//eclipse-workspace//nov_13_assignment//src//nov_13_assignment//india.txt") );
			try {
				String sent;
				while((sent=br.readLine())!=null)
				{
					sent=sent.toLowerCase();
					String words[]=sent.split(" ");
					for(String word:words)
					{
						if(word.equals("india"))
							c++;
					}
					
				}
				System.out.println("no. of occurences is: "+c);
		}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}

	}

}
