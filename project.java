import java.util.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Handler;

public class project extends Applet implements ActionListener {
	Image img;
	MediaTracker tr;
	Button btn1, btn2, btn3;
	Label tv1, tv2, tv3;
	
	static int userOverallScore = 0;
	static int userTurnTotal = 0;
	int compOverallScore = 0;
	int compTurnScore = 0;
	final int TIMER = 1500;
	
	public void init() {
		tv1 = new Label("User Total: 0");
		tv2 = new Label("Computer Total: 0");
		tv3 = new Label("Turn Total: 0");
		add(tv1);
		add(tv2);
		add(tv3);
		img = getImage(getCodeBase(), "dice0.png");
		
		btn1 = new Button("Roll");
		btn2 = new Button("Hold");
		btn3 = new Button("Reset");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		add(btn1);
		add(btn2);
		add(btn3);
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 100, 100, this);
	}

	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn1) {
      	onRollHandler();
      }
      else if(e.getSource() == btn2) {
      	onHoldHandler();
      }
      else if(e.getSource() == btn3) {
      	onResetHandler();
      }
    }

	//Roll Button
    public void onRollHandler(){
        int diceRoll = getRandomDiceRoll();
        changeImage(diceRoll);
        controlPlayerTurn(diceRoll);
    }

    public void controlPlayerTurn(int diceRoll){
        if(diceRoll==1){
            changeToComputerTurn();
            tv3.setText("Too bad! You got 1");
        }
        else{
            userTurnTotal+= diceRoll;
            displayTurnTotal(userTurnTotal);
        }
    }



    private void displayTurnTotal(int thisTurnTotal) {
        tv3.setText("Turn Total : " + thisTurnTotal);
    }

	//Disabled Hold and Roll Button while Computer plays its turn
    private void changeToComputerTurn() {
        if(userOverallScore >= 100){
            tv3.setText("Yeah! You won! :D");
        }
        else{
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            changeImage(0);
            userTurnTotal = 0;
            displayTurnTotal(0);
            displayOverallScore();
            compTurnScore = 0;
            computerTurn();
        }
    }

	//Computer's Turn can be really fast. Thus used Timer so that each turn can be seen.
    private void controlComputerTurn(int diceRoll) {
        if(diceRoll==1){
            compTurnScore=0;
            changeToPlayerTurn();
            tv3.setText("I got 1 :(");
        }
        else{
            compTurnScore+=diceRoll;
            displayTurnTotal(compTurnScore);
            new java.util.Timer().schedule( 
				new java.util.TimerTask() {
				@Override
				public void run() {
					computerTurn();
				}
			}, TIMER); 
        }
    }

    public void computerTurn(){
        if(compTurnScore<=20){
            int diceRoll = getRandomDiceRoll();
            changeImage(diceRoll);
            controlComputerTurn(diceRoll);
        }
        else{
            displayTurnTotal(compTurnScore);
            changeToPlayerTurn();
        }
    }

    private void changeToPlayerTurn() {
        if (compOverallScore >= 100) {
            tv3.setText("Better luck next time! :D");
        }
        else {
            btn1.setEnabled(true);
            btn2.setEnabled(true);
            changeImage(0);
            compOverallScore+=compTurnScore;
            userTurnTotal = 0;
            displayTurnTotal(0);
            displayOverallScore();
        }
    }

	//Displays Overall Score
    private void displayOverallScore() {
        tv1.setText("Your score : " + userOverallScore);
        tv2.setText("Computer Score : " + compOverallScore);
        tv3.setText("Turn Total : 0");
    }

    //Reset Button
    public void onResetHandler(){
        userOverallScore = 0;
        userTurnTotal = 0;
        compOverallScore = 0;
        compTurnScore = 0;
        tv1.setText("Your Score: 0");
        tv2.setText("Computer Score: 0");
        tv3.setText(" ");
        btn1.setEnabled(true);
        btn2.setEnabled(true);
		img = getImage(getCodeBase(), "dice0.png");
    }

    //Hold Button
    public void onHoldHandler(){
        userOverallScore+=userTurnTotal;
        userTurnTotal=0;
        changeToComputerTurn();
    }

	//Generates Random integer between 1 and 6
    public int getRandomDiceRoll(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    public void changeImage(int diceRoll){
        switch (diceRoll){
            case 1:
                img = getImage(getCodeBase(), "dice0.png");
				repaint();
                break;
            case 2:
                img = getImage(getCodeBase(), "dice1.png");
				repaint();
                break;
            case 3:
                img = getImage(getCodeBase(), "dice2.png");
				repaint();
                break;
            case 4:
                img = getImage(getCodeBase(), "dice3.png");
				repaint();
                break;
            case 5:
                img = getImage(getCodeBase(), "dice4.png");
				repaint();
                break;
            case 6:
                img = getImage(getCodeBase(), "dice5.png");
				repaint();
                break;
            default:
				img = getImage(getCodeBase(), "dice0.png");
				repaint();
        }
		
    }
	
	//To repaint the other dice faces
	public void update(Graphics g) {
		paint(g);
	}   
}