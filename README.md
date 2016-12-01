# Scarne-s-Dice-Java-

INTRODUCTION ----------------

Scarne’s Dice is a turn-based dice game where player and computer score points by rolling a die and then:

* if they roll a 1, score no points and lose their turn.
* if they roll a 2 to 6:
	~ add the rolled value to their points.
	~ choose to either reroll or keep/hold their score and end their turn
The winner is the first one that reaches (or exceeds) 100 points.
Player can hold their turn score on their own whim which will be added to their Player's Overall Score. Computer will hold as soon as their turn total exceeds 20 (changeable).

LAYOUT ----------------------

The layout consists of :
* 3 Labels - Player's Overall Score, Computer's Overall Score, Turn Total (also displays when either the player or the computer reaches 100)
* 3 Buttons - Roll, Hold, Reset (Scores become 0)
* 1 Image to display the dice faces as per the random integer generated

P.S. : Please download the 6 dice faces and make sure that images and java file are in the same folder because the code searches for the images in the same directory as the code.
