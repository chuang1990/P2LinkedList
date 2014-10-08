import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
/**
 * GameGUI create the GUI for this trivia game, it also contain the needed
 * action listeners to provide input for the GameEngine to control the gameplay.
 * 
 * @Catherine Huang 
 * @Oct. 5, 2014
 */
public class GameGUI extends JFrame
{
    // instance variables 
    private GameEngine game;
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 500;
    
    //Frame
    private JFrame frame;
    //main Panel
    private JPanel mainPanel;
    //text Panel
    private JPanel questionPanel;
    private JLabel welcomeText;
    private JLabel questionAsked;
    private JComboBox questCategory;
    private JLabel respondText;
    //Answer Panel
    private JPanel answerPanel;
    private JTextField answerInput;
    private JButton submitAnswer;
    //Counter Panel
    private JPanel counterPanel;
    private JLabel countAsked;
    private JLabel countRight;
    private JLabel countAttempt;
    
    //input variables
    private String userInput;
    private String categoryChoice ="";

    /**
     * Constructor for objects of class GameGUI
     */
    public GameGUI()
    {
        // initialise instance variables
        
        this.game = new GameEngine();
        game.scanText();
        this.makeFrame();
    }
    
    /**
     * Set all the elements in the Frame, including all actionListeners.
     */
    public void makeFrame(){
        frame = new JFrame("Trivia Game");
        
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        
        contentCreator(frame);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Create the Frame
     */
    public void contentCreator(JFrame frame){
        Container contentPane = frame.getContentPane();
        
        mainPanel = new JPanel(new BorderLayout());
        contentPane.add(mainPanel);
        
        questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBackground(Color.WHITE);
        welcomeText = new JLabel(game.welcome());
        welcomeText.setFont(new Font("Ariel", Font.BOLD, 15));
        questionAsked = new JLabel("ask atuat");
        questionAsked.setFont(new Font("SansSerif", Font.PLAIN, 20));
        questionPanel.add(welcomeText, BorderLayout.NORTH);
        questionPanel.add(questionAsked, BorderLayout.CENTER);
        respondText = new JLabel("");
        respondText.setFont(new Font("Ariel", Font.ITALIC, 20));
        respondText.setForeground(Color.RED);
        questionPanel.add(respondText, BorderLayout.EAST);
        
        //list of categories
        String[] categories = {"","Geography", "Math", "History", "Animal", "Chemistry"};
        questCategory = new JComboBox(categories);
        questCategory.addItemListener (new ItemListener () {
            @Override
            public void itemStateChanged(ItemEvent e){
                if(questCategory.getSelectedIndex() == 0){
                    categoryChoice = "Please select a category to get question.";
                    setAsk(categoryChoice);
                    
                }
                else if(questCategory.getSelectedIndex() == 1){
                    categoryChoice = "Geography";
                    changeQuestion();
                }
                else if(questCategory.getSelectedIndex() == 2){
                    categoryChoice = "Math";
                    changeQuestion();
                }
                else if(questCategory.getSelectedIndex() == 3){
                    categoryChoice = "History";
                    changeQuestion();
                }
                else if(questCategory.getSelectedIndex() == 4){
                    categoryChoice = "Animal";
                    changeQuestion();
                }
                else if(questCategory.getSelectedIndex() == 5){
                    categoryChoice = "Chemistry";
                    changeQuestion();
                }
                
                }
            });
        questionPanel.add(questCategory, BorderLayout.SOUTH);
        
        mainPanel.add(questionPanel,BorderLayout.NORTH);
        //Answer Panel
        answerPanel = new JPanel(new BorderLayout());
        
        answerInput = new JTextField(20);
        answerPanel.add(answerInput, BorderLayout.WEST);
        
        submitAnswer = new JButton("Submit");
        submitAnswer.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        userInput = answerInput.getText();
                        game.checkAnswer(userInput);
                        setRespond(game.getResponse());
                        changeQuestion();
                        clearInput();
                        stopGame(game.getCorrect());
                    }
                });
        answerPanel.add(submitAnswer, BorderLayout.EAST);
        mainPanel.add(answerPanel, BorderLayout.SOUTH);
        
                
        //Counter Panel
        counterPanel = new JPanel(new BorderLayout());
        countAsked = new JLabel("Question Asked:");
        countRight = new JLabel("Correct Answer: ");
        countAttempt = new JLabel("Attemps Left: ");
        counterPanel.add(countAsked, BorderLayout.NORTH);
        counterPanel.add(countRight, BorderLayout.CENTER);
        counterPanel.add(countAttempt, BorderLayout.SOUTH);
        mainPanel.add(counterPanel, BorderLayout.WEST);
        
        //Menu
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu menu = new JMenu("Quit");
        menubar.add(menu);
        
        JMenuItem btnQuit = new JMenuItem("Quit");
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent A) {
                System.exit(0);
            }
        });
        menu.add(btnQuit);
        
        
    }
    
    /**
     * Procedure when changing categories.
     */
    public void changeQuestion(){
        setAsk(game.getQuest(categoryChoice));
        setAsked("Question Asked: "+ game.getAsked());
        setCorrect("Correctly Answered: "+game.getCorrect());
        setAttempt("Attempt Left: "+game.getAttempt());
        stopGame(game.getCorrect());
        gameOver();
    }
    
    /**
     * stop the game
     */
    public void gameOver(){
        if(game.getGameOver() == true) {
            setRespond("Game Over!");
            System.exit(1);
        }
    }
    
    /**
     * reset the user input text field
     */
    public void clearInput(){
        answerInput.setText("");
    }

    //     /**
    //      * Get user input
    //      */
    //     public String getAnswer(){
        //         return userInput = answerInput.getText();
        //     }
    
    /**
     * convert getAsked to String
     */
    public String askString(){
        return "" + game.getAsked();
    }
    
    /**
     * convert getCorrect to String
     */
    public String correctString(){
        return "" + game.getCorrect();
    }
    
    /**
     * Convert getAttempt to String
     */
    public String attemptString(){
        return "" + game.getAttempt();
    }
    
    /**
     * Set user input
     */
    public void setUserInput(){
        this.userInput = answerInput.getText();
    }
    
    
    //Set text for labels
    /**
     * Set question label
     */
    public void setAsk(String questAsk){
        questionAsked.setText(questAsk);
    }
    
    /**
     * set response label
     */
    public void setRespond(String respond){
        respondText.setText(respond);
    }
    
    /**
     * Set question asked label
     */
    public void setAsked(String asked){
        countAsked.setText(asked);
    }
    
    /**
     * set answer correct label.
     */
    public void setCorrect(String correct){
        countRight.setText(correct);
    }
    
    /**
     * set left attempt label
     */
    public void setAttempt(String attempt){
        countAttempt.setText(attempt);
    }
    
    /**
     * Check if the game should be stop.
     */
    public void stopGame(int c){
        if(c == 5){
            setRespond("You Win!");
        }
        respondText.setFont(new Font("Ariel", Font.ITALIC, 35));
    }
    
    
        
}
