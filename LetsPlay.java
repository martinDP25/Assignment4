// -------------------------------------------------------
// Assignment 4
// Written by: Matthew Da Silva - 40008582
// For COMP 248 Section EE – Fall 2018
//
// The following program is a game involves 2-10 players, gardens, and planting flowers and trees. 
// A total of 4 classes were used, including the driver class (Dice, Garden, Player, LetsPlay-DRIVER).
// Setting up the Dice, Garden, and Player class was relatively simple. It was a matter of following instructions
// in the outline, along with some programming knowledge learned in class. The depth of the assignment came in the 
// driver class, where all the other classes and their methods are called upon to be used for their specific functions. 
// The most time consuming part of the driver class had to be the actual game part. Having loops within loops within IF statements
// within more loops (you get it) required a solid amount of brain power for me to complete this assignment. Although it 
// was lengthy, I can say that I learned a significant amount more compared to other assignments.
// --------------------------------------------------------

import java.util.Scanner;
import java.util.Random;

public class LetsPlay {

	public static void main(String[] args)
	{
		//Arbitrary integer variables used throughout driver class.
		int x=0, y=0, z=0, a=0, DD, cRow, cCol, roundCount=0, BS, ranNum1, ranNum2, RR = 0, gArg = 0;
		
		//Instantiating random object.
		Random rand = new Random();
		
		//Instantiating arbitrary string variables used throughout driver class.
		String PP, winner="";
		
		//Instantiating boolean variables used throughout driver class.
		boolean test = false, yup=false;
		
		//Instantiating Player array object used to store all player information.
		Player[] Player=new Player[10];

		//Instantiating scanner object.
		Scanner myKeys = new Scanner(System.in);
		
		//Instantiating player string array to simply hold player names.
		String[] player = new String[10];

		//Instantiating dice object.
		Dice myDice = new Dice();

		//Welcome banner
		System.out.println("-------****-------****-------****-------****-----****-----");
		System.out.println("       Welcome to Crazy Marn's Garden Game!");
		System.out.println("-------****-------****-------****-------****-----****-----\n" +
				"");
		//Game rules
		System.out.println("To win this game you need some luck with the dice and a bit of strategy.\n" + 
				"Your garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A tree takes 4 spots (2x2).\n" + 
				"You roll the dice and based on the outcome you get to plant a pre-set number of trees and flowers.\n" + 
				"");
		System.out.println("Rolls and their outcome:\n" + 
				"---------------------\n" + 
				"3: plan a tree (2x2) and a flower (1x1)\n" + 
				"6: plant 2 flowers (2 times 1x1)\n" + 
				"12: plant 2 trees (2 times 2x2)\n" + 
				"5 or 10: the rabbit will eat something that you have planted - might be a flower or\n" + 
				"part of a tree(1x1)\n" + 
				"Any other EVEN rolls: plant a tree (2x2) Any other ODD rolls: plant a flower (1x1)\n" + 
				"");
		System.out.println("Minimum number of players: 2.\n" + 
				"Minimum garden size: 3x3.\n" + 
				"You can only plant in empty locations. To plant a tree you give the top left coordinates of the 2x2 space\n" + 
				"and I will check to make sure all 4 locations are free.\n" + 
				"Okay .. Let's start the game! May the best gardener win!!!\n" + 
				"");
		System.out.println("The default garden size is a 3x3 square. You can use this default board size or change the size.\n" + 
				"Enter 0 to use the default garden size or 1 to enter your own size:");

		BS=myKeys.nextInt();

		//Choosing board size
		while(BS!=0||BS!=1)
		{
			//User selected default size (3x3)
			if(BS==0)
			{
				gArg=3;
				break;

				//User selected custom size (NxN)
			}else if(BS==1)
			{
				do {
					System.out.println("What size board would you like?");
					BS=myKeys.nextInt();
					//User selects board size 3 or greater
					if(BS>3)
					{
						Player person = new Player("X", BS);
						gArg=BS;
						break;
						//User selects board size less than 3
					}else if(BS<3)
					{
						System.out.println("Please pick a value equal to 3 or greater.");
					}
					//Loops as long as user selects custom board size less than 3
				}while(BS<3);
				break;
			}
		}
		System.out.println("How many players will there be (minimum 2, maximum 10)?");
		BS=myKeys.nextInt();

		//Number of players chosen (must be between 2 or 10)
		//If outside of 2-10 range, user will be prompted again
		do
		{
			if(BS>=2&&BS<=10)
			{
				player=new String[BS];
				System.out.println("Great, " + BS + " players it is!");

				//For loop used to cycle through player string, prompting user to enter names
				for(x=0, y=1; x<player.length; x++, y++)
				{
					System.out.print("Name of player " + y + " (no spaces allowed): ");
					player[x]=myKeys.next();
					Player[x]=new Player(player[x], gArg);
				}
				break;
			}
			System.out.println("Please choose a player count between (and including) 2 & 10");
			BS=myKeys.nextInt();
		}while(BS>2||BS<10);

			//Rolling die to see who goes first.
			int diceArr[]=new int[player.length];
		System.out.println("\nLets see who goes first...\n");
		do
		{
			test=false;
			//Rolls die and assigns to player.
			for(x=0; x<player.length; x++)
			{	
				diceArr[x]=myDice.rollDice();
				System.out.print(player[x] + " rolled a " + diceArr[x] + "\n");			
			}

			//Identifies same-number rolls. Loop cycles through dice array, and
			//if statement compares values at each index side by side. If it returns true, the
			//rolling dice process restarts
			for(x=0; x<player.length; x++)
			{
				for(y=x+1; y<diceArr.length; y++)
				{
					if(diceArr[x]==diceArr[y])
					{
						System.out.println("We must reroll, " + diceArr[x] + " was rolled multiple times.");
						test=true;
						break;
					}
				}
			}
		}while(test==true);

		//Sorting player names based on highest number rolled.
		//Arbitrary variables used to store array values at specific index, 
		//then place them back into the array under the compared value that rolled higher
		for(x=0; x<player.length; x++)
		{
			for(y=x+1; y<diceArr.length; y++)
			{
				if(diceArr[x]<diceArr[y])
				{
					RR=diceArr[x];
					diceArr[x]=diceArr[y];
					diceArr[y]=RR;

					PP=player[x];
					player[x]=player[y];
					player[y]=PP;
				}
			}
			System.out.println("");
			System.out.print(player[x] + " goes first.\n\n");
			break;
		}
		System.out.println("Time to play!!\n-------------");
		
		//Game actually begins with the player who rolled highest
		//Do while loop used to dictate each game (as well as keep the round looping until we get a winner).
		//This is repeated until the full garden condition is satisfied (AKA until we have a winner)
		do
		{
			//For loop used here to cycle through the played array, giving each person their turn. 
			//Variable x is used, and is reset to 0 every time a full cycle of the player array is done.
			
			//Integer variable that keeps track of rounds completed. 
			roundCount++;
			x=0;
			for(x=0; x<player.length; x++)
			{
				//Players roll both dice, values printed to screen.
				DD=myDice.rollDice();
				System.out.println("");
				System.out.println(player[x] + " you rolled a " + DD + " (Die 1: " + myDice.getDice1() + ", Die 2: " + myDice.getDice2() + ")");

				//Rolling a 3******************************************************************************
				if(DD==3)
				{
					//Prints what rolling a 3 does for you in the game, followed by showing the user his garden
					System.out.println("You must plant a tree (2x2) and a flower (1x1).\n");
					Player[x].showGarden();
					//If you can plant at least 1 tree, this condition is met
					if(Player[x].howManyTreesPossible()>=1)
					{
						//The following loop tells the user how many spaces available to plant a tree. It then prompts user to enter planting coordinates.
						//If statement used to check if planting coordinates will go off grid by identifying gardens (arrays) length. This is a method I put 
						//in myself, as I thought it was easier to use. Else if statement used to check if there is already something planted in the desired
						//coordinates. It calls the whatIsPlanted method to verify empty space. If 1 of the first 2 statements (if & else if) are satisfied, the
						//loop restarts, tells the user why, then prompts the user to pick new coordinates. 
						do {
							System.out.println("You have " + Player[x].howManyTreesPossible() + " space(s) available to plant a tree.");
							System.out.println("Enter tree coordinates as row & column: ");
							cRow=myKeys.nextInt();
							cCol=myKeys.nextInt();
							if(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || cRow+1>=Player[x].whatIsGardenLength() || cCol+1>=Player[x].whatIsGardenLength())
							{
								System.out.println("Sorry, you entered coordinates off grid. Please try again");
							}else if(Player[x].whatIsPlanted(cRow, cCol)!='-' || Player[x].whatIsPlanted(cRow+1, cCol)!='-' || Player[x].whatIsPlanted(cRow, cCol+1)!='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)!='-')
							{
								System.out.println("Sorry, but there is already something planted here. Please try again.");
							}else if(Player[x].whatIsPlanted(cRow, cCol)=='-' || Player[x].whatIsPlanted(cRow+1, cCol)=='-' || Player[x].whatIsPlanted(cRow, cCol+1)=='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)=='-')
							{
								Player[x].plantTreeInGarden(cRow, cCol);
								Player[x].showGarden();
								break;
							}
						//Loops as long as coordinates are off grid or coordinates are not empty.
						}while(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || cRow+1>=Player[x].whatIsGardenLength() || cCol+1>=Player[x].whatIsGardenLength() || Player[x].whatIsPlanted(cRow, cCol)!='-' || Player[x].whatIsPlanted(cRow+1, cCol)!='-' || Player[x].whatIsPlanted(cRow, cCol+1)!='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)!='-');
						if(Player[x].isGardenFull()==true)
						{
							yup=true;
							break;
						}
					//No room to plant a tree results in the message below
					}else if(Player[x].howManyTreesPossible()==0)
					{
						System.out.println("**Unfortunately you have no room to plant a tree!**\n");
					}
					//If statement to check if the 2nd tree can be planted (rolling a 3 requires 2 trees to be planted).
					//If it is satisfied is enters the same loop as used above, with slightly different message prints.
					if(Player[x].howManyFlowersPossible()>=1)
					{
						//Same as above loop, only this time the user is planting a flower.
						do {
							System.out.println("You have " + Player[x].howManyFlowersPossible() + " space(s) available to plant a flower.");
							System.out.println("Enter flower coordinates as row & column: ");
							cRow=myKeys.nextInt();
							cCol=myKeys.nextInt();

							if(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength())
							{
								System.out.println("Sorry, you entered coordinates off grid. Please try again");
							}else if(Player[x].whatIsPlanted(cRow, cCol)!='-')
							{
								System.out.println("Sorry, but there is already something planted here. Please try again.");
							}else if(Player[x].whatIsPlanted(cRow, cCol)=='-')
							{
								Player[x].plantFlowerInGarden(cRow, cCol);
								Player[x].showGarden();
								break;
							}
						}while(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || Player[x].whatIsPlanted(cRow, cCol)!='-');
					}else if(Player[x].howManyFlowersPossible()==0)
					{
						System.out.println("Sorry, no room to plant a flower!\n");
					}
					//If statement used to check validity of isGardenFull method. If
					//it returns true, then the game is over. The for loop is broken, redirecting
					//the flow further down, where another condition is met to break the gaming
					//loop (thus ending the game).
					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
				//Rolling a 6******************************************************************************
				else if(DD==6)
				{
					//Same as "Rolling a 3" section, only this time messages are slightly different, and the user is planting
					//2 flowers instead of 1 tree and 1 flower.
					System.out.println("You must plant 2 flowers (each 1x1).\n");
					Player[x].showGarden();
					do {
						System.out.println("You have " + Player[x].howManyFlowersPossible() + " space(s) available per flower.");
						System.out.println("Enter flower 1 coordinates as row & column: ");
						cRow=myKeys.nextInt();
						cCol=myKeys.nextInt();

						if(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength())
						{
							System.out.println("Sorry, you entered coordinates off grid. Please try again");
						}else if(Player[x].whatIsPlanted(cRow, cCol)!='-')
						{
							System.out.println("Sorry, but there is already something planted here. Please try again.");
						}else if(Player[x].whatIsPlanted(cRow, cCol)=='-')
						{
							Player[x].plantFlowerInGarden(cRow, cCol);
							Player[x].showGarden();
							break;
						}
					}while(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || Player[x].whatIsPlanted(cRow, cCol)!='-');
					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}else if(Player[x].isGardenFull()==false)
					{
						do {
							System.out.println("Enter flower 2 coordinates as row & column: ");
							cRow=myKeys.nextInt();
							cCol=myKeys.nextInt();

							if(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength())
							{
								System.out.println("Sorry, you entered coordinates off grid. Please try again");
							}else if(Player[x].whatIsPlanted(cRow, cCol)!='-')
							{
								System.out.println("Sorry, but there is already something planted here. Please try again.");
							}else if(Player[x].whatIsPlanted(cRow, cCol)=='-')
							{
								Player[x].plantFlowerInGarden(cRow, cCol);
								Player[x].showGarden();
								break;
							}
						}while(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || Player[x].whatIsPlanted(cRow, cCol)!='-');
					}
					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
				//Rolling a 12******************************************************************************
				else if(DD==12)
				{
					//Same as "Rolling a 3" section, only this time messages are slightly different, and the user is planting
					//2 trees instead of 1 tree and 1 flower.
					System.out.println("You must plant 2 trees (each 2x2).\n");
					Player[x].showGarden();
					if(Player[x].howManyTreesPossible()>=1)
					{
						do {
							System.out.println("You have " + Player[x].howManyTreesPossible() + " space(s) available per tree.");
							System.out.println("Enter tree 1 coordinates as row & column: ");
							cRow=myKeys.nextInt();
							cCol=myKeys.nextInt();
							if(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || cRow+1>=Player[x].whatIsGardenLength() || cCol+1>=Player[x].whatIsGardenLength())
							{
								System.out.println("Sorry, you entered coordinates off grid. Please try again");
							}else if(Player[x].whatIsPlanted(cRow, cCol)!='-' || Player[x].whatIsPlanted(cRow+1, cCol)!='-' || Player[x].whatIsPlanted(cRow, cCol+1)!='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)!='-')
							{
								System.out.println("Sorry, but there is already something planted here. Please try again.");
							}else if(Player[x].whatIsPlanted(cRow, cCol)=='-' || Player[x].whatIsPlanted(cRow+1, cCol)=='-' || Player[x].whatIsPlanted(cRow, cCol+1)=='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)=='-')
							{
								Player[x].plantTreeInGarden(cRow, cCol);
								Player[x].showGarden();
								break;
							}
						}while(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || cRow+1>=Player[x].whatIsGardenLength() || cCol+1>=Player[x].whatIsGardenLength() || Player[x].whatIsPlanted(cRow, cCol)!='-' || Player[x].whatIsPlanted(cRow+1, cCol)!='-' || Player[x].whatIsPlanted(cRow, cCol+1)!='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)!='-');
					}else if(Player[x].howManyTreesPossible()==0)
					{
						System.out.println("**Unfortunately you have no room to plant a tree!**\n"
								+ "     Your turn is over.");
						break;
					}
					if(Player[x].howManyTreesPossible()>=1)
					{
						do {
							System.out.println("Enter tree 2 coordinates as row & column: ");
							cRow=myKeys.nextInt();
							cCol=myKeys.nextInt();
							if(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || cRow+1>=Player[x].whatIsGardenLength() || cCol+1>=Player[x].whatIsGardenLength())
							{
								System.out.println("Sorry, you entered coordinates off grid. Please try again");
							}else if(Player[x].whatIsPlanted(cRow, cCol)!='-' || Player[x].whatIsPlanted(cRow+1, cCol)!='-' || Player[x].whatIsPlanted(cRow, cCol+1)!='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)!='-')
							{
								System.out.println("Sorry, but there is already something planted here. Please try again.");
							}else if(Player[x].whatIsPlanted(cRow, cCol)=='-' || Player[x].whatIsPlanted(cRow+1, cCol)=='-' || Player[x].whatIsPlanted(cRow, cCol+1)=='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)=='-')
							{
								Player[x].plantTreeInGarden(cRow, cCol);
								Player[x].showGarden();
								break;
							}
						}while(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || cRow+1>=Player[x].whatIsGardenLength() || cCol+1>=Player[x].whatIsGardenLength() || Player[x].whatIsPlanted(cRow, cCol)!='-' || Player[x].whatIsPlanted(cRow+1, cCol)!='-' || Player[x].whatIsPlanted(cRow, cCol+1)!='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)!='-');
					}else if(Player[x].howManyTreesPossible()==0)
					{
						System.out.println("**Unfortunately you have no room to plant a tree!**\n"
								+ "     Your turn is over.");
					}
					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
				//Rolling a 5 or 10******************************************************************************
				else if(DD==5 || DD==10)
				{
					//If statement used for flow control. If garden is not empty upon rolling a 5 or 10, the below condition is met, and
					//the rabbit removes a 1x1 piece of the garden at random.
					if(Player[x].isGardenEmpty()==false)
					{
						System.out.println("Oh no! The rabbit got to your garden! A space of 1x1 is removed (either a whole flower, or part of a tree).\n");
						do{
							//Do while loop used here, along with randomly generated numbers. These random numbers are tested with the 
							//whatIsPlanted method. If the condition is met, and there is something planted in this space, it is removed
							//and replaced with a '-' character. 
							ranNum1=rand.nextInt(gArg);
							ranNum2=rand.nextInt(gArg);	
							if(Player[x].whatIsPlanted(ranNum1, ranNum2)=='f' || Player[x].whatIsPlanted(ranNum1, ranNum2)=='t')
							{
								//Once above condition is met, a message is printed to inform the user what exact space has been eaten by the rabbit.
								System.out.println("The rabbit ate whatever was planted at (" + ranNum1 + "," + ranNum2 + ")" );
								//eatHere method used to eat 1x1 space (there must be a flower or part tree, or else it would not have satisfied the condition above).
								Player[x].eatHere(ranNum1, ranNum2);
								break;
							}
						}while(Player[x].whatIsPlanted(ranNum1, ranNum2)!='f' || Player[x].whatIsPlanted(ranNum1, ranNum2)!='t');
					}
					//If statement used for flow control. If garden is empty upon rolling a 5 or 10, the below condition is met, along with a 
					//little message about the rabbit passing by.
					else if(Player[x].isGardenEmpty()==true)
					{
						System.out.println("The rabbit passed by, but luckily you have neither flower(s) or tree(s) planted! Your turn is over.\n");
					}
					Player[x].showGarden();
					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
				//Rolling any other even number******************************************************************************
				else if(DD==2 || DD==4 || DD==8)
				{
					//Same as "Rolling a 3" section, only this time messages are slightly different, and the user is planting
					//1 tree instead of 1 tree and 1 flower.
					System.out.println("You must plant 1 tree (2x2).\n");
					Player[x].showGarden();				
					if(Player[x].howManyTreesPossible()>=1)
					{	
						do {
							System.out.println("You have " + Player[x].howManyTreesPossible() + " space(s) available to plant a tree.");
							System.out.println("Enter tree coordinates as row & column: ");
							cRow=myKeys.nextInt();
							cCol=myKeys.nextInt();
							if(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || cRow+1>=Player[x].whatIsGardenLength() || cCol+1>=Player[x].whatIsGardenLength())
							{
								System.out.println("Sorry, you entered coordinates off grid. Please try again");
							}else if(Player[x].whatIsPlanted(cRow, cCol)!='-' || Player[x].whatIsPlanted(cRow+1, cCol)!='-' || Player[x].whatIsPlanted(cRow, cCol+1)!='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)!='-')
							{
								System.out.println("Sorry, but there is already something planted here. Please try again.");
							}else if(Player[x].whatIsPlanted(cRow, cCol)=='-' || Player[x].whatIsPlanted(cRow+1, cCol)=='-' || Player[x].whatIsPlanted(cRow, cCol+1)=='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)=='-')
							{
								Player[x].plantTreeInGarden(cRow, cCol);
								Player[x].showGarden();
								break;
							}
						}while(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || cRow+1>=Player[x].whatIsGardenLength() || cCol+1>=Player[x].whatIsGardenLength() || Player[x].whatIsPlanted(cRow, cCol)!='-' || Player[x].whatIsPlanted(cRow+1, cCol)!='-' || Player[x].whatIsPlanted(cRow, cCol+1)!='-' || Player[x].whatIsPlanted(cRow+1, cCol+1)!='-');
					}else if(Player[x].howManyTreesPossible()==0)
					{
						System.out.println("**Unfortunately you have no room to plant a tree!**\n"
								+ "    Your turn is over.");
					}
					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
				//Rolling any other odd number******************************************************************************
				else if(DD==7 || DD==9 || DD== 11)
				{
					//Same as "Rolling a 3" section, only this time messages are slightly different, and the user is planting
					//1 flowers instead of 1 tree and 1 flower.
					System.out.println("You must plant 1 flower (1x1).\n");
					Player[x].showGarden();
					if(Player[x].howManyFlowersPossible()>=1)
					{
						do {
							System.out.println("You have " + Player[x].howManyFlowersPossible() + " space(s) available to plant a flower.");
							System.out.println("Enter flower coordinates as row & column: ");
							cRow=myKeys.nextInt();
							cCol=myKeys.nextInt();

							if(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength())
							{
								System.out.println("Sorry, you entered coordinates off grid. Please try again");
							}else if(Player[x].whatIsPlanted(cRow, cCol)!='-')
							{
								System.out.println("Sorry, but there is already something planted here. Please try again.");
							}else if(Player[x].whatIsPlanted(cRow, cCol)=='-')
							{
								Player[x].plantFlowerInGarden(cRow, cCol);
								Player[x].showGarden();
								break;
							}
						}while(cRow>=Player[x].whatIsGardenLength() || cCol>=Player[x].whatIsGardenLength() || Player[x].whatIsPlanted(cRow, cCol)!='-');
					}else if(Player[x].howManyFlowersPossible()==0)
					{
						System.out.println("");
						System.out.println("Sorry, no room to plant a flower!\n");
					}
					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
			}
			if(yup==true)
			{
				winner=Player[x].getName();
				break;
			}
			//Final condition that is satisfied for the game to end.
		}while(test==false);

		//Final results banner along with rounds played.
		System.out.println("\nWe have a winner!! That special someone is " + winner.toUpperCase() + "\n"
				+ " "
				+"-----FINAL RESULTS-----\n"
				+ "*************************\n"
				+ "Here is each players garden after " + roundCount + " rounds.\n");

		//For loop to display each players garden at the end of the game.
		for(x=0; x<player.length; x++)
		{
			System.out.println(Player[x].getName() + "'s garden\n");
			Player[x].showGarden();
			System.out.println("");
		} 
	}
}
