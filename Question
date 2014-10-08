import java.util.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Arrays;
/**
 * Question Class provides the Game Engine with Question and all function related to create, return, 
 * and deleting the questions.  
 * It also contains a list of answers to be compare with user's input.
 * 
 * @Catherine Huang
 * @vr 1.5 (Oct. 03, 2014)
 */
public class Question
{
    // instance variables - replace the example below with your own
    private String question;
    private LinkedList<String> answers;
    private int numbQuest;
    private String categ;

    /**
     * Constructor for objects of class Question
     */
    public Question(String q, String[] a, String c)
    {
        // initialise instance variables
        this.question = q;
        this.categ = c;
        
        answers = new LinkedList<String>();
        setAns(a);
    }

    /**
     * Create Question.
     * 
     * @param s String of question.
     */
    public void setQuest(String q){
        this.question = q;
    }
    
    /**
     * Get the Question
     */
    public String getQuest(){
        return question;
    }
    
    /**
     * Set the Answer for the question
     * 
     * @param a String of answer.
     */
    public void setAns(String[] a){
        //add the string to the linked list of answers.
        for(String answer: a)
            answers.add(answer);
    }
    
    /**
     * Set the category of the Question
     */
    public void setCat(String c){
        this.categ = c;
    }
    
    /**
     * get the category of the Question
     */
    public String getCat(){
        return categ;
    }
    
    /**
     * check through the list of answers and return true or false.
     * 
     * @param String user input answer
     * @return boolean
     */
    public boolean checkAns(String a){
        //String an = a;
        boolean correct = false;
        //creates Iterator to check the answers
        
        for(Iterator<String> check = answers.iterator(); check.hasNext();){
            String answer = check.next();
            if(answer.equalsIgnoreCase(a)){
                correct = true;
                return correct;
            }
             else
                correct = false;
        }
        
        return correct;
    }
    
    /**
     * Print out the Question.
     */
    public String toString(){
        return getQuest();
    }
}
