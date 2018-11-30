//Because it is not possible to create a Garden object within the driver class (assignment rules),
//links had to be made between Player class and Garden class. This allowed me to create a player object
//which still used the necessary methods from the Garden class, without actually instantiating a Garden 
//object within the driver class.
public class Player {
 	private String name;
	private Garden garden;
	
	//Constructor that instantiates a new object of the Garden class.
	public Player(String name, int garden)
	{
		this.name=name;
		this.garden=new Garden(garden);
	}
	
	//Accessor method that returns player name.
	public String getName()
	{
		return name;
	}
	
	//Mutator method that can change name.
	public void setName(String name)
	{
		this.name=name;
	}
	
	//Method to count possible flowers, feeds into garden class.
	public int howManyFlowersPossible()
	{
		return garden.countPossibleFlowers();
	}
	
	//Method to count possible trees, feeds into garden class.
	public int howManyTreesPossible()
	{
		return garden.countPossibleTrees();
	}
	
	//Accessor method that returns garden length. Feeds into garden class.
	public int whatIsGardenLength()
	{
		return garden.getGardenLength();
	}
	
	//Method that returns what is planted at specific coordinates, feeds into garden class.
	public char whatIsPlanted(int r, int c)
	{
		return garden.getInLocation(r, c);
	}
	
	//Method that plants tree in garden based on user-set coordinates, feeds into garden class.
	public char plantTreeInGarden(int r, int c)
	{
		return garden.plantTree(r, c);
	}
	
	//Method that plants flower in garden based on user-set coordinates, feeds into garden class.
	public char plantFlowerInGarden(int r, int c)
	{
		return garden.plantFlower(r, c);
	}
	
	//Method that removes flower/part of plant based on randomly chosen coordinates, feeds into garden class.
	//Used by the rabbit.
	public char eatHere(int r, int c)
	{
		return garden.removeFlower(r, c);
	}
	
	//Method that returns true/false based on garden full, feeds into garden class.
	public boolean isGardenFull()
	{
		return garden.gardenFull();
	}
	
	//Method that returns true/false based on garden empty, feeds into garden class.
	public boolean isGardenEmpty()
	{
		return garden.gardenEmpty();
	}
	
	//Method that reveals toString method in garden class.
	public String showGarden()
	{
		return garden.toString();
	}
}