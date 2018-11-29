import java.util.Random;

public class Dice {
	
	//Declaring 2 Dice attributes
	private int dice1;
	private int dice2;
	
	//Declaring random method variable
	Random rand = new Random();
	
	//Dice constructor, default set to 0 for each dice.
	public Dice()
	{
		dice1=0;
		dice2=0;
	}
	
	//Get method for both Dice variables
	public int getDice1()
	{
		return dice1;
	}
	public int getDice2()
	{
		return dice2;
	}
	
	//Method that makes use of random method to assign random numbers (1-6) to each die.
	public int rollDice()
	{
		dice1=rand.nextInt(6) +1;
		dice2=rand.nextInt(6) +1;
		return dice1+dice2;
	}
	
	//Method that returns a string stating each dice value
	public String toString()
	{
		return("The value of the 1st dice is " + dice1 + " and the value of the 2nd dice is " + dice2);
	}
}