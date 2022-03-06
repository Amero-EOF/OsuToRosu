import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class osuToRoblox {

	static ArrayList<String> notes;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner;
		notes = new ArrayList<String>();
		try {
			scanner = new Scanner(new File("Osu.txt"));
			int counter = 0;
			while (scanner.hasNext()) {
				String newNote = scanner.nextLine();
				//System.out.println(newNote);
				notes.add(newNote);
				
				counter++;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		FileWriter writer;
		try {
			writer = new FileWriter(new File("Roblox.txt"));
			writer.append("local Notes = {");
			for (int i=0; i < notes.size(); i++) {
				String[] noteSplit = notes.get(i).split(",");
				String xPos;
				String yPos;
				String time;
				String type;
				
				try {
					if (noteSplit.length >= 8) {
						xPos = noteSplit[0];
						yPos = noteSplit[1];
						time = noteSplit[2];
						type = noteSplit[3];
						
						String[] points = noteSplit[5].split("\\|");
						String sliderType = points[0];
						String curvePoints = "{";
						
						System.out.println(points.length);
						
						for (int j=1; j < points.length; j++) {
							
							String[] point = points[j].split(":");
							
							//System.out.println(points[j]);
							
							if (j == points.length - 1) {
								
								curvePoints += "{x = " + point[0] + ", y = " + point[1] + "}}";
								
							} else {
								
								curvePoints += "{x = " + point[0] + ", y = " + point[1] + "},";
								
							}
							
						}
						System.out.println("OHNO");
						String amountOfSlides = noteSplit[6];
						String length = noteSplit[7];
						writer.append("{" + xPos + ", " + yPos + ", " + time + ", " + type + ", \"" + sliderType + "\", " + curvePoints + ", " + amountOfSlides + ", " + length + ", "  + "},\n");
					} else {
						xPos = noteSplit[0];
						yPos = noteSplit[1];
						time = noteSplit[2];
						type = noteSplit[3];
						writer.append("{" + xPos + ", " + yPos + ", " + time + ", " + type + "},\n");
					}
					
						
				} catch (IOException e) {System.out.println(e);}
				
				
				
			}
			System.out.println("Here");
			writer.append("}");
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
