import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Arrays;
/**
 * Write a description of class GameEngine here.
 * 
 * @Catherine Huang
 * @vr. 1.5 (Oct. 3, 2014)
 */
public class GameEngine
{
    // instance variables
    //for 
    private LinkedList<Question> questions;
    private File textFile;
    private Scanner scan;
    private FileReader reader;
    
    //main game controls
    private Question questionAdd;
    public int numAttempt;
    public int numQuest;
    public int numCorrect;
    private boolean gameStop;
    private int questionIndex;
    private String response;
    
    //variables help create the lists.
    private int message=10;
    private String qString;
    private String[] aString;
    private String cString;

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        // initialise instance variables
        questions = new LinkedList();
        gameStop = false;
        numAttempt = 7;
        numQuest = 5;
        numCorrect = 0;
        questionIndex = 0;
        //System.out.print(welcome());
        scanText();
        
    }
    
    //Setting up the game.
    /**
     * Prints welcome message and the condition of the game to user.
     */
    public String welcome(){
        return "\nWelcome,\nPlease choose a categories to begin your game." +
               "\nYou have " + numQuest + "questions to answer\nand "
               + numAttempt + " chances to answer before game over.";
               
    }
   
    /**
     * Scan the file that contains questions and answers,
     * stores the Strings into respective linked lists
     */
    public void scanText() {
            try{
            //read and scan the text file
            textFile = new File("trivia.txt");
            reader = new FileReader(textFile);
            scan = new Scanner(reader);
            //eliminate unwanted symbols
            scan.useDelimiter("\\n");
        
            //store the scanned line
            
            while(scan.hasNextLine()){ 
                String text = scan.nextLine();
                //puts scanned text into the appropriated lists.
                if(!text.isEmpty() && text.charAt(0)=='c'){
                    cString = text.substring(2);
                    //System.out.println(text.substring(2));
                    //System.out.println(cString);
                    text = scan.nextLine();
                    
                    qString = text.substring(2);
                    //System.out.println(text.substring(1));
                    //System.out.println(qString);
                    text = scan.nextLine();
                    
                    aString = text.split(",");
                    //System.out.println(text.substring(1));
                    questions.add(new Question(qString,aString,cString));
                    //System.out.println(questions.get(0).getCat());
                    
                }
                
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
            
    }
    
    /**
     * choose the categories and display the answers
     * 
     * @param int c user choice of category
     */
    public String getQuest(String c){
        String cate = c;
        String question = "";
        for(int i = 0; i < questions.size(); i++){
            String cat = questions.get(i).getCat();
            if(cate.equalsIgnoreCase(cat)){
                question = questions.get(i).toString();
                questionIndex = i;
            }
            //else
            //    question =  "question not found, please choose another category.";
            
        }
        return question;
    }
    
    //check answer and remove question
    /**
     * Check user inputed answer with the system.
     * Remove question when answer correctly.
     */
    public void checkAnswer(String answer){
        //String ansr = answer;
        //System.out.println(ansr);
        questionAdd = questions.get(questionIndex);
        //System.out.println(questionAdd);
        boolean test = questionAdd.checkAns(answer);
        //System.out.println(test);
        if(test == true){
            questions.remove(questionIndex);
            incrementCorrect();
            decreaseAttempt();
            response = "Correct!";
        }
        else{
            decreaseAttempt();
            response = "Wrong!";
        }
    }
        
    
    //getters, counters
    /**
     * Decrement when a answer is submitted.
     */
    public void decreaseAttempt(){
        numAttempt--;
    }
    
    /**
     * Increment when a question is answered correctly.
     */
    public void incrementCorrect(){
        numCorrect++;
    }
    
    /**
     * Show the total attempt user still have.
     */
    public int getAttempt(){
        return numAttempt;
    }
    
    /**
     * Show how many question have been asked.
     * 
     * @return int total questions asked.
     */
    public int getAsked(){
        return (7-numAttempt);
    }
    
    /**
     * Show how many question has been answer correctly.
     * 
     * @return int number of correct answers.
     */
    public int getCorrect(){
        return numCorrect;
    }
    
    /**
     * Return String of the response to answering question correctly or not.
     */
    public String getResponse(){
        return response;
    }
   
    /**
     * Check the conditions that if the game should continue or stop.
     * 
     * @return boolean game stop or cont.
     */
    public boolean getGameOver(){
        if(numAttempt == 0 || numCorrect == numQuest)
            gameStop = true;
        return gameStop;
    }
    
}
