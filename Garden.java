
public class Garden {

	private char garden[][];

	//Constructor to set default garden size to 3x3.
	public Garden()
	{
		garden[3][3]='-';
		initializeGarden();
	}

	//Constructor that takes an integer to set the garden size.
	//If statement used to ensure garden size is 2x2 or greater. 
	public Garden(int N)
	{
		if(N>=3)
		{
			char garden[][]=new char [N][N];
		}else
			System.out.println("Please pick a value equal to 3 or greater");
		initializeGarden();
	}
	
	public char initializeGarden()
	{
		/*for(int x = 0; x<garden.length; x++)
		{
			for(int y=0; y<garden[x].length; y++)
			{
				garden[x][y]='-';
			}
			System.out.println("");
		}*/
		return 0;
	}
	
	public int getInLocation(int r, int c)
	{
		return garden[r][c];
	}
	
	public char plantFlower(int r, int c)
	{
		return garden[r][c]='f';
	}
	
	public char plantTree(int r, int c)
	{
		garden[r+1][c]='t';
		garden[r][c+1]='t';
		garden[r+1][c+1]='t';
		return garden[r][c];
	}
	
	public char removeFlower(int r, int c)
	{
		return garden[r][c]='-';
	}
	
	public int countPossibleTrees()
	{
		return 0;
	}
	
	public int countPossibleFlowers()
	{
		return 0;
	}
	
	public boolean gardenFull()
	{
		return true;
	}
	
	public String toString()
	{
		return ("The garden size is " + garden);
	}
}
