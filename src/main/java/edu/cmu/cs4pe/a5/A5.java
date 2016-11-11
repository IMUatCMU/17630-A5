package edu.cmu.cs4pe.a5;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class A5 {

    public static void main(String[] args) {

		//Inform the user about the program
		System.out.println("\n");
		System.out.println("*************************************************");
		System.out.println("Welcome to 6 Degrees of Separation, Actor Edition");
		System.out.println("*************************************************");
		System.out.println("This program creates a graph of actors from the Movie Data text file.");
		System.out.println("Each actor is connected in the graph by movies they haved starred in together.");
		System.out.println("The user is prompted to input any two actor names.");
		System.out.println("The output generated is the actor connections between those actors, based on movies they've starred in.");
		System.out.println("This output is limited to 6 degrees of separation.");
		System.out.println("(This interface is meant to mimic the popular \"Six Degrees of Separation from Kevin Bacon\" notion)");
		System.out.println("\n");

		//Initialize a file reader
		BufferedReader br = null;

		//Initialize a graph to store the actors with their movie/actor edge lists
		ActorGraph graph = new ActorGraph();


		//https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
		try {

			//Prompt the user for the location of the Movie Data text file
			System.out.println("Please enter a valid filepath for the Movie Data text file: ");
			//Store the file string
			String file = System.console().readLine();

			System.out.println("\n");

			//Set the file reader to the file location
			br = new BufferedReader(new FileReader(file));

			//Initialize a variable to hold the current line of the text file
			String sCurrentLine;

			//Create a string array to hold the divided movie information from each line
			String[] movieInfo;

			//Loop through each line of the text file, checking to see if the line is empty
			while ((sCurrentLine = br.readLine()) != null)
			{

				//Split the data by opening paranthesis
				movieInfo = sCurrentLine.split("\\(");

				//Manipulate the data if it includes more information than just the movie title
				if (movieInfo.length > 1)
				{
					//Trim any whitespace before or after relevant content
					String movieName = movieInfo[0].trim();

					//Separate the actor information by commas
					String[] actorSplit = movieInfo[movieInfo.length-1].split(",");

					//If actor data exists and there is more than one actor manipulate the data
					if ((actorSplit[0] != null && !actorSplit[0].trim().isEmpty()) && (actorSplit.length > 1))
					{


						for (int i = 0; i < actorSplit.length; i++)
						{
							//Separate out words from the actor data that are lowercase
							for (int j = 0; j <actorSplit[i].length(); j++)
							{
								if(Character.isUpperCase(actorSplit[i].charAt(j)))
								{
									actorSplit[i] = actorSplit[i].substring(j);
									break;
								}
							}

							//Get rid of end paranthesis or brackets within the actor string for the last actor
							if (i == actorSplit.length-1)
							{
								if (actorSplit[i].indexOf(")") != -1 )
									actorSplit[i]= actorSplit[i].substring(0, actorSplit[i].indexOf(")"));
								else if (actorSplit[i].indexOf("]") != -1 )
									actorSplit[i]= actorSplit[i].substring(0, actorSplit[i].indexOf("]"));
								else
									break;
							}

							actorSplit[i] = actorSplit[i].trim();
						}

						//Add an actor to the graph if the actor doesn't already exist in the graph
						for (int i = 0; i < actorSplit.length; i++)
						{
							if (graph.findActor(actorSplit[i]) == null)
							{
								graph.addActor(new Actor(actorSplit[i]));
							}
						}

						//Add all movie/actor edge lists for the current movie to each actor who starred in the movie
						for (int i = 0; i < actorSplit.length; i++)
						{
							for (int j = i + 1; j <actorSplit.length; j++)
							{
								Actor one = graph.findActor(actorSplit[i]);
								Actor two = graph.findActor(actorSplit[j]);
								new Movie(movieName, one, two);
							}
						}

					}
				}
			}

			//End the program if the user entered an invalid filepath, and print the relevant errors
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				return;
			}
		}

		//Prompt the user for the actors to perform the 6 degree search on
		System.out.print("Enter Actor 1: ");
		String actor1 = System.console().readLine();
		System.out.print("Enter Actor 2: ");
		String actor2 = System.console().readLine();
		actor1 = actor1.trim();
		actor2 = actor2.trim();

		// Do search and output results
		NDegreeSearch sixDegree = new NDegreeSearch(6, graph.findActor(actor1), graph.findActor(actor2));
		sixDegree.search();
    }
}
