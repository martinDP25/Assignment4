public class Garden {

	private char garden[][];

	//Constructor to set default garden size to 3x3.
	public Garden()
	{
		garden= new char[3][3];
		initializeGarden();
	}

	//Constructor that takes an integer to set the garden size.
	//If statement implemented to ensure garden size is 3x3 or greater. 
	public Garden(int N)
	{
		if(N>=3)
		{
			garden=new char [N][N];
		}else if(N<3)
		{
			System.out.println("Please pick a value equal to 3 or greater.");
			System.exit(0);
		}
		initializeGarden();
	}
	
	private char initializeGarden()
	{
		for(int x = 0; x<garden.length; x++)
		{
			for(int y=0; y<garden[x].length; y++)
			{
				garden[x][y]='-';
			}
		}
		return 0;
	}
	
	public char getInLocation(int r, int c)
	{
		return garden[r][c];
	}
	
	public char plantFlower(int r, int c)
	{
		if(garden[r][c]=='f' || garden[r][c]=='t')
		{
			System.out.println("**Sorry, but this action is unavailable due to insufficient space.");
		}else
		garden[r][c]='f';
		return garden[r][c];
	}
	
	public char plantTree(int r, int c)
	{
		if(garden[r][c]=='f' || garden[r][c]=='t')
		{
			System.out.println("**Sorry, but this action is unavailable due to insufficient space.");
		}else
		garden[r][c]='t';
		garden[r+1][c]='t';
		garden[r][c+1]='t';
		garden[r+1][c+1]='t';
		
		return garden[r][c];
	}
	
	public char removeFlower(int r, int c)
	{
		if(garden[r][c]=='f' || garden[r][c]=='t')
		{
			garden[r][c]='-';
		}else
		garden[r][c]='-';
		return garden[r][c];
	}
	
	public int countPossibleTrees()
	{
		int ST=0;
		for(int x=0; x<garden.length-1; x++)
		{
			for(int y=0; y<garden[x].length-1; y++)
			{
				if(garden[x][y]=='-'&&garden[x+1][y]=='-'&&garden[x][y+1]=='-'&&garden[x+1][y+1]=='-')
				{
					ST+=1;
				}
			}
		}
		return ST;
	}
	
	public int countPossibleFlowers()
	{
		int SF=0;
		for(int x=0; x<garden.length; x++)
		{
			for(int y=0; y<garden[x].length; y++)
			{
				if(garden[x][y]=='-')
				{
					SF+=1;
				}
			}
		}
		return SF;
	}
	
	public boolean gardenFull()
	{
		for(int x=0; x<garden.length; x++)
		{
			for(int y=0; y<garden[x].length; y++)
			{
				if(garden[x][y]=='-')
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean gardenEmpty()
	{
		for(int x=0; x<garden.length; x++)
		{
			for(int y=0; y<garden[x].length; y++)
			{
				if(garden[x][y]=='f' || garden[x][y]=='t')
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public String toString()
	{
		System.out.print("  | ");
		for(int z=0; z<garden.length; z++)
		{
			System.out.print(z + "  ");
		}
		System.out.println("");
		for(int x = 0; x<garden.length; x++)
		{
			System.out.print(x + " | ");
			for(int y=0; y<garden[x].length; y++)
			{
				System.out.print(garden[x][y] + "  ");
			}
			System.out.println("");
		}
		return ("The garden size is " + garden.length + "x" + garden.length + "\n");
	}
}
