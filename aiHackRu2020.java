/*
*
*@authors: Sajin Saju, Zihe Zhang, Aatrey Sahay
*11/8/2020
*/
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.Math;
import java.awt.Desktop;
import java.net.URI;
public class aiHackRu2020{
    public static void openBrowser(String key) throws Exception{
        Desktop desktop = Desktop.getDesktop();
        //add plus signs after each word within the string to search google without spaces since in the link it cannot have spaces.
        String word = "";
        for(int i=0;i<key.length();i++){
            if(key.charAt(i)==(' ')){
                word+="+";
            }
            else{
                word+=key.charAt(i);
            }
        }
        //in the uri type the address of the website.
        desktop.browse(new URI("https://google.com/search?q=" + word));
        //the key is the string with search question.
    }
    public static ArrayList<String> spaceCase(String phrase){
        //wake up jarvis
        ArrayList<String> result = new ArrayList<String>();
        while(phrase.indexOf(" ") > -1){
            result.add(phrase.substring(0,phrase.indexOf(" ")));
            phrase = phrase.substring(phrase.indexOf(" ") + 1);
           
        }
        result.add(phrase);
        return result;
    }
    public static String whatToDoAI(ArrayList<String> keyWords, ArrayList<String> temp){
        int count = 0;
        //System.out.println(temp.get(0));
        for(int i = 0; i < temp.size(); i++){
            for(int k = 0; k < keyWords.size()-1;k++){
                //System.out.println(temp.get(i));
                if(temp.get(i).equals(keyWords.get(k))){
                    count++;
                }
            }
            
        }
        if(count == keyWords.size()-1){
            return keyWords.get(keyWords.size()-1);
        }
        return "other";
    }
    public static String whatToDoBrainAi(ArrayList<String> keyWords, ArrayList<String> temp){
		int countTemp = 0;
		for(int i = keyWords.size()-1; i >= 0; i--){
			if(keyWords.get(i).equals(":"))
				countTemp = i;
		}
		int count = 0;
        //System.out.println(temp.get(0));
        for(int i = 0; i < temp.size(); i++){
            for(int k = 0; k < countTemp;k++){
                //System.out.println(temp.get(i));
                if(temp.get(i).equals(keyWords.get(k))){
                    count++;
                }
            }
            
        }
        if(count == countTemp){
			String result = "";
            for(int i = countTemp+1; i < keyWords.size();i++){
				result += keyWords.get(i);
				if(i != keyWords.size() - 1)
					result += " ";
			}
			return result;
        }
        return "other";
	}
	public static String checkAI() throws IOException{
        File file = new File("keyWordsForAi.txt");
        Scanner scan = new Scanner(file);
        //scanTextCommand should scan the terminal for an input to the String textCommand
        Scanner scanTextCommand = new Scanner(System.in);
        //textCommand is the input from the user to the AI as instruction to do something
        String textCommand = scanTextCommand.nextLine().toUpperCase();
        //String outPut is the base case if the AI found no keywords to match with.
        String outPut = "other";
        //textCommandArray is the array version without spaces of the textCommand or the input from the user without spaces in an array.
        ArrayList<String> textCommandArray = spaceCase(textCommand);
        //the while loop should end when there is nothing else to read in the keyWordsForAi.txt text file.
        while(scan.hasNextLine()){
            //String temp should contatin the line of the file.
            String temp = scan.nextLine();
            //temp2 is the ArrayList of the key words from the text file line without spaces.
            ArrayList<String> temp2 = spaceCase(temp);
            //the outPut should contain either other or the functions value to do something.
            outPut = whatToDoAI(temp2, textCommandArray);
            if( !(outPut.equals("other")) )
                break;
            //System.out.println(outPut);
		}
		if(outPut.equals("other")){
			File aiBrain = new File("aiSelfLearn.txt");
			Scanner aiScan = new Scanner(aiBrain);
			while(aiScan.hasNextLine()){
				String temp = aiScan.nextLine().toUpperCase();
				//temp2 is the ArrayList of the key words from the text file line without spaces.
				ArrayList<String> temp2 = spaceCase(temp);
				//the outPut should contain either other or the functions value to do something.
				outPut = whatToDoBrainAi(temp2, textCommandArray);
				if( !(outPut.equals("other")) ){
					return outPut;
					//return keyWords.get(keyWords.size()-1);
				}
			}
		}
        //list
        //Template
        return outPut;
    }
    public static void printArray(String[][] tttArray){
        for(int i = 0; i < tttArray.length;i++){
            for(int j = 0; j < tttArray[i].length;j++){
                System.out.print(tttArray[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static boolean endGame(String[][] tttArray){
        for(int i = 0; i < tttArray.length; i++){
            if(tttArray[i][0].equals(tttArray[i][1]) && tttArray[i][1].equals(tttArray[i][2]) && !(tttArray[i][0].equals("-"))){
                return true;
            }
            if(tttArray[0][i].equals(tttArray[1][i]) && tttArray[1][i].equals(tttArray[2][i]) && !(tttArray[0][i].equals("-"))){
                return true;
            }   
        }
        if(tttArray[0][0].equals(tttArray[1][1]) && tttArray[1][1].equals(tttArray[2][2]) && !(tttArray[0][0].equals("-"))){
            return true;
        }
        else if(tttArray[2][0].equals(tttArray[1][1]) && tttArray[1][1].equals(tttArray[0][2]) && !(tttArray[2][0].equals("-"))){
            return true;
        }
        return false;
    }
    public static int aiMove(){
        return (int)(Math.random() * 3);
    }
    public static void main(String[] args) throws IOException{
        while(true){
            String outPut = checkAI();
            if(outPut.equals("lead1")){ 
                System.out.println("Hello sir");
                while( !(outPut.equals("lead2")) ){
                    outPut = checkAI();//make sure jarvis is alive
                    Scanner textCommandScan = new Scanner(System.in);
                    if(outPut.equals("other")){
                        System.out.println("I don't understand sir, do you have an answer for me to remember for the next time?");
                        String temp = textCommandScan.nextLine().toUpperCase();
                        while(true){
                            if(temp.equals("YES")){
                                System.out.println("Repeat the question for me sir, specificaly:");
                                String question = textCommandScan.nextLine().toUpperCase();
                                System.out.println("The answer sir, ");
                                String answer = textCommandScan.nextLine().toUpperCase();
                                File file = new File("aiSelfLearn.txt");
                                Scanner scan = new Scanner(file);
                                String temp2 = "";
                                while(scan.hasNextLine()){
                                    temp2 = temp2.concat(scan.nextLine() + "\n");
                                }
                                FileWriter writer = new FileWriter("aiSelfLearn.txt");
                                writer.write(temp2 + question + " : " + answer);
                                writer.close();
                                break;
                            }
                            else{
                                break;
                            }
                        }
                        
                    }
                    else if(outPut.equals("lead3")){
                            System.out.println("Leading Coefficient of x^2 sir,");
                            int a = Integer.parseInt(textCommandScan.nextLine());
                            System.out.println("Leading Coefficient of x sir,");
                            int b = Integer.parseInt(textCommandScan.nextLine());
                            System.out.println("Leading Coefficient of the number sir,");
                            int c = Integer.parseInt(textCommandScan.nextLine());
                            int d = 0;
                            int ac = a * c;
                            
                            //System.out.println("Cannot be simplified sir, so here is the x values:\n");

                            double ans1 = ( ((-1)*(b)) + Math.sqrt(Math.pow(b,2) - (4*a*c)) ) / (2*a);
                            double ans2 = ( ((-1)*(b)) - Math.sqrt(Math.pow(b,2) - (4*a*c)) ) / (2*a);

                            System.out.println("x is equal to " + ans1);
                            System.out.println("x is equal to " + ans2);
                    }
                    else if(outPut.equals("lead4")){
                        //random number generator
                        double ranNum=Math.random()*100;
                        System.out.println(ranNum);
                        System.out.println("do you wanna geuss numbers");
                    }
                    else if(outPut.equals("lead5")){
                        //play a game
                        System.out.println("Which game you want to play Sensei? \nGuess Number \nTIC TAC TOE");
                        String response = textCommandScan.nextLine();
                        //check the input and match with the correct game
                        if(response.toUpperCase().equals("TIC TAC TOE")){
                            int count = 0;
                            Scanner scan = new Scanner(System.in);
                            String[][] ticTacToe = {{"-","-","-"},{"-","-","-"},{"-","-","-"}};
                            int[] player1 = new int[2];
                            printArray(ticTacToe);
                            while( !(endGame(ticTacToe) ) ){
                                System.out.println("Player 1's turn: ");
                                while(true){
                                    player1[0] = scan.nextInt();
                                    player1[1] = scan.nextInt();

                                    if(ticTacToe[player1[0]][player1[1]].equals("x") || ticTacToe[player1[0]][player1[1]].equals("o")){
                                        System.out.println("Try again player 1, ");
                                    }
                                    else{
                                        ticTacToe[player1[0]][player1[1]] = "x";
                                        count++;
                                        break;
                                    }
                                }
                                
                                printArray(ticTacToe);
                                if(endGame(ticTacToe)){
                                    System.out.println("Congrats player 1!");
                                    break;
                                }
                                while(true){
                                    int temp1 = aiMove();
                                    int temp2 = aiMove();
                                    if( !(ticTacToe[temp1][temp2].equals("x") || ticTacToe[temp1][temp2].equals("o") ) ){
                                        ticTacToe[temp1][temp2] = "o";
                                        count++;
                                        break;
                                    }
                                }
                                System.out.println();
                                printArray(ticTacToe);
                                if(endGame(ticTacToe)){
                                    System.out.println("Congrats player 2!");
                                    break;
                                }
                                if(count == 9){
                                    break;
                                }
                            }
                        }
                        else if(response.toUpperCase().equals("GUESS NUMBER")){
                            System.out.println("Hi! I'm thinking of a random number between 1 and 50.");
                            System.out.println();
                            int guessing_num = (int)(Math.random() * 50);
                            int i=1;
                            while (i<8){
                                System.out.println("--- Attempt "+i+"\n" + "What number I am thinking of?");
                                
                                int guessed_num = Integer.parseInt(textCommandScan.nextLine());
                                if (guessed_num == guessing_num){
                                    System.out.println("Winner!!");
                                    System.out.println(" The number was " + guessing_num);
                                    break;
                                }
                                else if(guessed_num<guessing_num){
                                    System.out.println("Too low");
                                }
                                else if(guessed_num>guessing_num){ 
                                    System.out.println("Too high");
                                }
                                if(i==7){
                                    System.out.println("Aw, you ran out of tries. The number was "+guessing_num+".");
                                }
                                i++;
                            }

                        }
                    }
                    else if(outPut.equals("lead6")){
                        //tell time
                        Date currentDate = new Date();
                        //System.out.println(currentDate);
                        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
                        System.out.println(timeFormat.format(currentDate));
                        //System.out.println("Anything else sir?");
                    }
                    else if(outPut.equals("lead7")){
                        String[] response = {"I am alright sir,", "I am good sir,", "Good sir,", "I am tired sir,", "I am pretty good sir,", "I am pretty good, thank you for asking sir."};
                        System.out.println(response[(int)(Math.random()*response.length)]);
                        //lead 7
                        //google it
                    }
                    else if(outPut.equals("lead8")){
                        //start running
                        //ArrayList<String> plans = new ArrayList<String>();
                        //ArrayList<String> times= new ArrayList<String>();
                        System.out.println("Set schedule or check schedule? (set/check)");
                        //if running is true, you can add stuff
                        //if running is false, print the current shcedule and end
                        String inputText = textCommandScan.nextLine().toUpperCase();
                        //check running is true so you can add more
                        while(inputText.equals("SET")){
                            File file = new File("scheduleOfUser.txt");
                            Scanner scan = new Scanner(file);
                            String temp = "";
                            while(scan.hasNextLine()){
                                temp = temp.concat(scan.nextLine() + "\n");
                            }
                            FileWriter writer = new FileWriter("scheduleOfUser.txt");
                            System.out.println("What you tranna plan");
                            String plan=textCommandScan.nextLine();
                            //plans.add(plan);
                            System.out.println("At what time?");
                            String time=textCommandScan.nextLine();
                            //times.add(time);
                            writer.write(temp + plan + " : " + time);
                            writer.close();
                            System.out.println("Continue to add more stuff or that's all? (more/no)");
                            if(textCommandScan.nextLine().toUpperCase().equals("NO")){
                                break;
                            }
                        }
                        
                        if(inputText.equals("CHECK")){
                            System.out.println("Sir your schedule is listed, be prepared:");
                            File file = new File("scheduleOfUser.txt");
                            Scanner scan = new Scanner(file);
                            String temp = "";
                            while(scan.hasNextLine()){
                                temp = temp.concat(scan.nextLine() + "\n");
                            }
                            System.out.println(temp);
                        }
                    }
                    else if(outPut.equals("lead9")){
                        try{
                            System.out.println("What do you want to search?");
                            String temp = textCommandScan.nextLine();
                            openBrowser(temp);
                        }
                        catch (Exception e){
                            System.out.println("Cant");
                        }
                    }
                    else{
                        System.out.println(outPut);
                    }
                    System.out.println("Anything else sir?");
                }
                //jarvis will be on hold when output is lead2, so it means we must end the entire program by hand
                System.out.println("Good night sir");
            }
            else if(outPut.equals("lead2")){
                break;
            }
        }
        
        
    }  
}