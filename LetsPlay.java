import java.util.Scanner;
import java.util.Random;

public class LetsPlay {

	public static void main(String[] args)
	{
		int x=0, y=0, z=0, a=0, DD, BS, ranNum1, ranNum2, RR = 0, gArg = 0;
		Random rand = new Random();
		String PP;
		boolean test = false, yup=false;
		Player[] Player=new Player[10];

		Scanner myKeys = new Scanner(System.in);
		String[] player = new String[10];
		//Player[] person = new Player[0];
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
				System.out.println("What size board would you like?");
				BS=myKeys.nextInt();
				Player person = new Player("X", BS);
				gArg=BS;
				break;

			}else
				System.out.println("Please pick 0 or 1.");
			BS=myKeys.nextInt();
		}

		//Garden myJardine = new Garden(gArg);


		System.out.println("How many players will there be (minimum 2, maximum 10)?");
		BS=myKeys.nextInt();

		//Number of players chosen
		while(BS>2||BS<10)
		{
			if(BS>=2&&BS<=10)
			{
				player=new String[BS];
				System.out.println("Great, " + BS + " players it is!");

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
		}

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

			//Identifies same-number rolls.
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
		System.out.println("Time to play!!\n-------------\n\n");


		do
		{
			x=0;
			for(x=0; x<player.length; x++)
			{
				//Rolling dice.
				DD=myDice.rollDice();
				System.out.println(player[x] + " you rolled a " + DD + " (Die 1: " + myDice.getDice1() + ", Die 2: " + myDice.getDice2() + ")\n");
				//Rolling a 3.
				if(DD==3)
				{
					System.out.println("You must plant a tree (2x2) and a flower (1x1).\n");
					Player[x].showGarden();
					if(Player[x].howManyTreesPossible()>=1)
					{
						System.out.println("You have " + Player[x].howManyTreesPossible() + " space(s) available to plant a tree.");
						System.out.println("Enter tree coordinates as row & column: ");

						Player[x].plantTreeInGarden(myKeys.nextInt(), myKeys.nextInt());

					}else if(Player[x].howManyTreesPossible()==0)
					{
						System.out.println("Sorry, no room to plant a tree!\n");
					}
					if(Player[x].howManyFlowersPossible()>=1)
					{
						System.out.println("You have " + Player[x].howManyFlowersPossible() + " space(s) available to plant a flower.");
						System.out.println("Enter flower coordinates as row & column: ");
						Player[x].plantFlowerInGarden(myKeys.nextInt(), myKeys.nextInt());
					}else if(Player[x].howManyFlowersPossible()==0)
					{
						System.out.println("Sorry, no room to plant a flower!\n");
					}

					Player[x].showGarden();
					System.out.println(Player[x].isGardenFull());

					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
				//Rolling a 6.
				else if(DD==6)
				{
					System.out.println("You must plant 2 flowers (each 1x1).\n");
					Player[x].showGarden();
					System.out.println("You have " + Player[x].howManyFlowersPossible() + " space(s) available per flower.");
					System.out.println("Enter flower 1 coordinates as row & column: ");
					Player[x].plantFlowerInGarden(myKeys.nextInt(), myKeys.nextInt());
					System.out.println("Enter flower 2 coordinates as row & column: ");
					Player[x].plantFlowerInGarden(myKeys.nextInt(), myKeys.nextInt());

					Player[x].showGarden();
					System.out.println(Player[x].isGardenFull());

					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
				//Rolling a 12.
				else if(DD==12)
				{
					System.out.println("You must plant 2 trees (each 2x2).\n");
					Player[x].showGarden();
					if(Player[x].howManyTreesPossible()>=1)
					{
						System.out.println("You have " + Player[x].howManyTreesPossible() + " space(s) available per tree.");
						System.out.println("Enter tree 1 coordinates as row & column: ");
						Player[x].plantTreeInGarden(myKeys.nextInt(), myKeys.nextInt());
					}else if(Player[x].howManyTreesPossible()==0)
					{
						System.out.println("Sorry, no room to plant a tree!\n");
					}
					if(Player[x].howManyTreesPossible()>=1)
					{
						System.out.println("Enter tree 2 coordinates as row & column: ");
						Player[x].plantTreeInGarden(myKeys.nextInt(), myKeys.nextInt());
					}else if(Player[x].howManyTreesPossible()==0)
					{
						System.out.println("Sorry, no room to plant a tree!\n");
					}

					System.out.println(Player[x].isGardenFull());

					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
					Player[x].showGarden();
				}
				//Rolling a 5 or 10.
				else if(DD==5 || DD==10)
				{
					if(Player[x].isGardenEmpty()==false)
					{
						System.out.println("Oh no! The rabbit got to your garden! A space of 1x1 is removed (either a whole flower, or part of a tree).\n");
						do{
							ranNum1=rand.nextInt(gArg);
							ranNum2=rand.nextInt(gArg);	
							if(Player[x].whatIsPlanted(ranNum1, ranNum2)=='f' || Player[x].whatIsPlanted(ranNum1, ranNum2)=='t')
							{
								System.out.println("The rabbit ate wahtever was planted at (" + ranNum1 + "," + ranNum2 + ")" );
								Player[x].eatHere(ranNum1, ranNum2);
								break;
							}
						}while(Player[x].whatIsPlanted(ranNum1, ranNum2)!='f' || Player[x].whatIsPlanted(ranNum1, ranNum2)!='t');
					}
					else if(Player[x].isGardenEmpty()==true)
					{
						System.out.println("The rabbit passed by, but luckily you have neither flower(s) or tree(s) planted! You lose a turn.\n");
					}
					Player[x].showGarden();
					System.out.println(Player[x].isGardenFull());

					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
				//Rolling any other even number.
				else if(DD==2 || DD==4 || DD==8)
				{
					System.out.println("You must plant 1 tree (2x2).\n");
					Player[x].showGarden();				
					if(Player[x].howManyTreesPossible()>=1)
					{
						System.out.println("You have " + Player[x].howManyTreesPossible() + " space(s) available to plant a tree.");
						System.out.println("Enter tree coordinates as row & column: ");
						Player[x].plantTreeInGarden(myKeys.nextInt(), myKeys.nextInt());
					}else if(Player[x].howManyTreesPossible()==0)
					{
						System.out.println("Sorry, no room to plant a tree!\n");
					}

					Player[x].showGarden();
					System.out.println(Player[x].isGardenFull());

					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
				//Rolling any other odd number.
				else if(DD==7 || DD==9 || DD== 11)
				{
					System.out.println("You must plant 1 flower (1x1).\n");
					Player[x].showGarden();
					if(Player[x].howManyFlowersPossible()>=1)
					{
						System.out.println("You have " + Player[x].howManyFlowersPossible() + " space(s) available to plant a flower.");
						System.out.println("Enter flower coordinates as row & column: ");
						Player[x].plantFlowerInGarden(myKeys.nextInt(), myKeys.nextInt());
					}else if(Player[x].howManyFlowersPossible()==0)
					{
						System.out.println("Sorry, no room to plant a flower!\n");
					}

					Player[x].showGarden();
					System.out.println(Player[x].isGardenFull());

					if(Player[x].isGardenFull()==true)
					{
						yup=true;
						break;
					}
				}
				System.out.println(Player[x].isGardenFull());
			}
			if(yup==true)
			{
				break;
			}
		}while(test==false);

		System.out.println("Winner Winner Chicken Dinner");
	}
}