package edu.cmu.cs4pe.a5;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class A5 {

    public static void main(String[] args) {

		

		BufferedReader br = null;
		
		ActorGraph graph = new ActorGraph();
		
		
		//https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
		try {
			
			br = new BufferedReader(new FileReader("C:\\Users\\Michael Beck\\Desktop\\A5\\src\\main\\java\\edu\\cmu\\cs4pe\\a5\\MovieDataANSI.txt"));
		
			String sCurrentLine;
			
			String[] movieInfo;

			int editFirstElement = 0;
			
			while ((sCurrentLine = br.readLine()) != null) 
			{
			
				movieInfo = sCurrentLine.split("\\(");
				
				if (movieInfo.length > 1)
				{
					String movie = movieInfo[0].trim();
					String[] actorSplit = movieInfo[movieInfo.length-1].split(",");
					if ((actorSplit[0] != null && !actorSplit[0].trim().isEmpty()) && (actorSplit.length > 1))
					{	
						for (int i = 0; i < actorSplit.length; i++)
						{
							for (int j = 0; j <actorSplit[i].length(); j++)
							{
								if(Character.isUpperCase(actorSplit[i].charAt(j))) 
								{
									actorSplit[i] = actorSplit[i].substring(j);
            						break;
								}
							}
						
							if (i == actorSplit.length-1)
								actorSplit[i]= actorSplit[i].substring(0, actorSplit[i].indexOf(")"));
							
							actorSplit[i] = actorSplit[i].trim();
						}	
						
						for (int i = 0; i < actorSplit.length; i++)
						{
							if (graph.findActor(actorSplit[i]) == null)
							{
								graph.addActor(new Actor(actorSplit[i]));
							}	
						}
						for (int i = 0; i < actorSplit.length; i++)
						{
							for (int j = 0; j <actorSplit.length; j++)
							{
								if (actorSplit[i] != actorSplit[j])
									graph.findActor(actorSplit[i]).addLink(new Movie(movie), graph.findActor(actorSplit[j]));
							}	
						}
						
					}
				}
				
				
			}


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		

		
		
		
		
		
		
		NDegreeSearch sixDegree = new NDegreeSearch(6);
		System.out.print("Enter Actor 1: ");
		String actor1 = System.console().readLine();
		System.out.print("Enter Actor 2: ");
		String actor2 = System.console().readLine();
		actor1 = actor1.trim();
		actor2 = actor2.trim();
		sixDegree.searchFormatted(graph.findActor(actor1), graph.findActor(actor2));


    }
}
