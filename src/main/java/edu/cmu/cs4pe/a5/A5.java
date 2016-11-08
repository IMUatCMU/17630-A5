package edu.cmu.cs4pe.a5;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class A5 {

    public static void main(String[] args) {

		System.out.print("Enter the path to the input file: ");
		String inputPath = System.console().readLine();

		BufferedReader br = null;
		ActorGraph graph = new ActorGraph();

		try {
			
			br = new BufferedReader(new FileReader(inputPath.trim()));
		
			String sCurrentLine;
			
			String[] movieInfo;

			int editFirstElement = 0;
			
			while ((sCurrentLine = br.readLine()) != null) 
			{
			
				movieInfo = sCurrentLine.split("\\(");
				
				if (movieInfo.length > 1)
				{
					String movieName = movieInfo[0].trim();
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
							for (int j = i + 1; j < actorSplit.length; j++)
							{
								if (i != j) {
									Actor one = graph.findActor(actorSplit[i]);
									Actor two = graph.findActor(actorSplit[j]);
									new Movie(movieName, one, two);
								}
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

		System.out.print("Enter Actor 1: ");
		String actor1 = System.console().readLine();
		System.out.print("Enter Actor 2: ");
		String actor2 = System.console().readLine();
		actor1 = actor1.trim();
		actor2 = actor2.trim();

		NDegreeSearch sixDegree = new NDegreeSearch(6, graph.findActor(actor1), graph.findActor(actor2));
		sixDegree.search();
    }
}
