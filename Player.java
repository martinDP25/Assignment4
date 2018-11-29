public class Player {
 	private String name;
	private Garden garden;
	
	public Player(String name, int garden)
	{
		this.name=name;
		this.garden=new Garden(garden);
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public int howManyFlowersPossible()
	{
		return garden.countPossibleFlowers();
	}
	
	public int howManyTreesPossible()
	{
		return garden.countPossibleTrees();
	}
	
	public char whatIsPlanted(int r, int c)
	{
		return garden.getInLocation(r, c);
	}
	
	public char plantTreeInGarden(int r, int c)
	{
		return garden.plantTree(r, c);
	}
	
	public char plantFlowerInGarden(int r, int c)
	{
		return garden.plantFlower(r, c);
	}
	
	public char eatHere(int r, int c)
	{
		return garden.removeFlower(r, c);
	}
	
	public boolean isGardenFull()
	{
		return garden.gardenFull();
	}
	
	public boolean isGardenEmpty()
	{
		return garden.gardenEmpty();
	}
	
	public String showGarden()
	{
		return garden.toString();
	}
}