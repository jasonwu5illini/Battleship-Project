import java.util.ArrayList;
import java.util.Scanner;

public class mediumAI extends ai {

	public int score;
	public board aiBoard;
	private position firstHit;
	private position northTry;
	private position eastTry;
	private position southTry;
	private position westTry;
	private boolean first;
	private boolean second;
	private boolean third;
	private boolean fourth;
	
	private ArrayList<Integer> posPos = new ArrayList<Integer>(); // possible Positions
	

	mediumAI(board b) {
		aiBoard = b;
		score = 0;
		firstHit = new position();
		northTry = new position();
		eastTry = new position();
		southTry = new position();
		westTry = new position();
		for (int i = 0; i < 100; i++) {
			posPos.add(i);
		}
		first = false;
		second = false;
		third = false;
		fourth = false;
		
	}

	public position nextMove(board humanBoard) {
		// TODO Auto-generated method stub
		position move = new position();
		int x;
		Integer intObj;
		if (firstHit.getX() != -1) //if firstHit exists
		{
			if(northTry.getX() != -1 && aiBoard.getHits().contains(northTry) && first && !second && !third && !fourth)// if northTry exists and its a hit
			{
				northTry = new position(northTry.getX(), northTry.getY()+1);
				if (posPos.contains(this.returnPosIndex(northTry)) && this.checkIfOnBoard(northTry))// if new northTry's position wasn't shot at before and its on the board
				{
					intObj = new Integer(this.returnPosIndex(northTry));
					posPos.remove(intObj);
					first = humanBoard.isOn(northTry);
					return northTry;
				}
				else //northTry cannot be done, try south now
				{
					southTry = new position(firstHit.getX(),firstHit.getY()-1);
					if(posPos.contains(this.returnPosIndex(southTry)) && this.checkIfOnBoard(southTry))//if southTry's position wasn't shot and its on the board
					{
						intObj = new Integer(this.returnPosIndex(southTry));
						posPos.remove(intObj); // remove the index from the list of not shot
						second = humanBoard.isOn(southTry);
						return southTry;
					}
					else // if southTry was shot at before or its not on the board
					{
						eastTry = new position(firstHit.getX()+1, firstHit.getY()); // now try east
						if(posPos.contains(this.returnPosIndex(eastTry)) && this.checkIfOnBoard(eastTry)) // if eastTry has not been shot before and is on the board
						{
							intObj = new Integer(this.returnPosIndex(eastTry));
							posPos.remove(intObj); //remove the index 
							third = humanBoard.isOn(eastTry);
							return eastTry; 
						}
						else //if eastTry was shot at before or its not on the board
						{
							westTry = new position(firstHit.getX()-1, firstHit.getY());// try westTry
							if(posPos.contains(this.returnPosIndex(westTry)) && this.checkIfOnBoard(westTry)) //if westTry has not been shot and on board
							{
								intObj = new Integer(this.returnPosIndex(westTry));
								posPos.remove(intObj); //remove the index
								fourth = humanBoard.isOn(westTry);
								return westTry;
							}
							else // if westTry was shot or is not on the board
							{
								firstHit = new position();
								northTry = new position();
								southTry = new position();
								eastTry = new position();
								westTry = new position();
								first = false;
								second = false;
								third = false;
								fourth = false;
								x = posPos.get((int) (posPos.size() * Math.random()));
								intObj = new Integer(x);
								posPos.remove(intObj);
								move = new position((x % 10) + 1, (x / 10) + 1);
								if (humanBoard.isOn(move) ) {
									firstHit = move;
									
								}
								
								return move;
							}
						}
						
					}
				}
			}
			else if(northTry.getX() != -1 && aiBoard.getMisses().contains(northTry) && !first && !second && !third && !fourth) //if northTry exists and missed
			{
				southTry = new position(firstHit.getX(), firstHit.getY()-1);
				if (posPos.contains(this.returnPosIndex(southTry)) && this.checkIfOnBoard(southTry))// if southTry's position wasn't shot at before and its on the board
				{
					intObj = new Integer(this.returnPosIndex(southTry));
					posPos.remove(intObj);
					second = humanBoard.isOn(southTry);
				
					return southTry;
				}
				else //if new southTry was actually shot before or its not on the board
				{
					eastTry = new position(firstHit.getX()+1, firstHit.getY()); // now try east
					if(posPos.contains(this.returnPosIndex(eastTry)) && this.checkIfOnBoard(eastTry)) // if eastTry has not been shot before and is on the board
					{
						intObj = new Integer(this.returnPosIndex(eastTry));
						posPos.remove(intObj); //remove the index 
						third = humanBoard.isOn(eastTry);
						return eastTry; 
					}
					else //if eastTry was shot at before or its not on the board
					{
						westTry = new position(firstHit.getX()-1, firstHit.getY());// try westTry
						if(posPos.contains(this.returnPosIndex(westTry)) && this.checkIfOnBoard(westTry)) //if westTry has not been shot and on board
						{
							intObj = new Integer(this.returnPosIndex(westTry));
							posPos.remove(intObj); //remove the index
							fourth = humanBoard.isOn(westTry);
							return westTry;
						}
						else // if westTry was shot or is not on the board
						{
							firstHit = new position();
							northTry = new position();
							southTry = new position();
							eastTry = new position();
							westTry = new position();
							first = false;
							second = false;
							third = false;
							fourth = false;
							x = posPos.get((int) (posPos.size() * Math.random()));
							intObj = new Integer(x);
							posPos.remove(intObj);
							move = new position((x % 10) + 1, (x / 10) + 1);
							if (humanBoard.isOn(move) ) {
								firstHit = move;
								
							}
							
							return move;
						}
					}
				}
			}
			else if(southTry.getX() != -1 && aiBoard.getHits().contains(southTry) && second && !third && !fourth) //if southTry exists and is a hit
			{
				southTry = new position(southTry.getX(), southTry.getY()-1);
				if (posPos.contains(this.returnPosIndex(southTry)) && this.checkIfOnBoard(southTry))// if new southTry's position wasn't shot at before and its on the board
				{
					intObj = new Integer(this.returnPosIndex(southTry));
					posPos.remove(intObj);
					second = humanBoard.isOn(southTry);
					return southTry;
				}
				else //if new southTry was actually shot before or its not on the board
				{
					eastTry = new position(firstHit.getX()+1, firstHit.getY()); // now try east
					if(posPos.contains(this.returnPosIndex(eastTry)) && this.checkIfOnBoard(eastTry)) // if eastTry has not been shot before and is on the board
					{
						intObj = new Integer(this.returnPosIndex(eastTry));
						posPos.remove(intObj); //remove the index 
						third = humanBoard.isOn(eastTry);
						return eastTry; 
					}
					else //if eastTry was shot at before or its not on the board
					{
						westTry = new position(firstHit.getX()-1, firstHit.getY());// try westTry
						if(posPos.contains(this.returnPosIndex(westTry)) && this.checkIfOnBoard(westTry)) //if westTry has not been shot and on board
						{
							intObj = new Integer(this.returnPosIndex(westTry));
							posPos.remove(intObj); //remove the index
							fourth = humanBoard.isOn(westTry);
							return westTry;
						}
						else // if westTry was shot or is not on the board
						{
							firstHit = new position();
							northTry = new position();
							southTry = new position();
							eastTry = new position();
							westTry = new position();
							first = false;
							second = false;
							third = false;
							fourth = false;
							x = posPos.get((int) (posPos.size() * Math.random()));
							intObj = new Integer(x);
							posPos.remove(intObj);
							move = new position((x % 10) + 1, (x / 10) + 1);
							if (humanBoard.isOn(move) ) {
								firstHit = move;
								
							}
							
							return move;
						}
					}
				}
			}
			else if (southTry.getX() != -1 && aiBoard.getMisses().contains(southTry) && !second && !third && !fourth) //if southTry exists and missed
			{
				eastTry = new position(firstHit.getX()+1, firstHit.getY()); // now try east
				if(posPos.contains(this.returnPosIndex(eastTry)) && this.checkIfOnBoard(eastTry)) // if eastTry has not been shot before and is on the board
				{
					intObj = new Integer(this.returnPosIndex(eastTry));
					posPos.remove(intObj); //remove the index 
					third = humanBoard.isOn(eastTry);
					return eastTry; 
				}
				else //if eastTry was shot at before or its not on the board
				{
					westTry = new position(firstHit.getX()-1, firstHit.getY());// try westTry
					if(posPos.contains(this.returnPosIndex(westTry)) && this.checkIfOnBoard(westTry)) //if westTry has not been shot and on board
					{
						intObj = new Integer(this.returnPosIndex(westTry));
						posPos.remove(intObj); //remove the index
						fourth = humanBoard.isOn(westTry);
						return westTry;
					}
					else // if westTry was shot or is not on the board
					{
						firstHit = new position();
						northTry = new position();
						southTry = new position();
						eastTry = new position();
						westTry = new position();
						first = false;
						second = false;
						third = false;
						fourth = false;
						x = posPos.get((int) (posPos.size() * Math.random()));
						intObj = new Integer(x);
						posPos.remove(intObj);
						move = new position((x % 10) + 1, (x / 10) + 1);
						if (humanBoard.isOn(move) ) {
							firstHit = move;
							
						}
						
						return move;
					}
				}
			}
			else if (eastTry.getX() != -1 && aiBoard.getHits().contains(eastTry) && !fourth) // if eastTry exists and hits
			{
				eastTry = new position(eastTry.getX() + 1, eastTry.getY());
				if (posPos.contains(this.returnPosIndex(eastTry)) && this.checkIfOnBoard(eastTry))// if new eastTry's position wasn't shot at before and its on the board
				{
					intObj = new Integer(this.returnPosIndex(eastTry));
					posPos.remove(intObj); //remove the index
					third = humanBoard.isOn(eastTry);
					return eastTry;
				}
				else //if new eastTry was shot at before or its not on the board
				{
					westTry = new position(firstHit.getX()-1, firstHit.getY());// try westTry
					if(posPos.contains(this.returnPosIndex(westTry)) && this.checkIfOnBoard(westTry)) //if westTry has not been shot and on board
					{
						intObj = new Integer(this.returnPosIndex(westTry));
						posPos.remove(intObj); //remove the index
						fourth = humanBoard.isOn(westTry);
						return westTry;
					}
					else // if westTry was shot or is not on the board
					{
						firstHit = new position();
						northTry = new position();
						southTry = new position();
						eastTry = new position();
						westTry = new position();
						first = false; 
						second = false;
						third = false;
						fourth = false;
						x = posPos.get((int) (posPos.size() * Math.random()));
						intObj = new Integer(x);
						posPos.remove(intObj);
						move = new position((x % 10) + 1, (x / 10) + 1);
						if (humanBoard.isOn(move) ) {
							firstHit = move;
							
						}
						
						return move;
					}
				}
						
			}
			else if (eastTry.getX() != -1 && aiBoard.getMisses().contains(eastTry) && !fourth)// if eastTry exists and missed
			{
				westTry = new position(firstHit.getX()-1, firstHit.getY());// try westTry
				if(posPos.contains(this.returnPosIndex(westTry)) && this.checkIfOnBoard(westTry)) //if westTry has not been shot and on board
				{
					intObj = new Integer(this.returnPosIndex(westTry));
					posPos.remove(intObj); //remove the index
					fourth = humanBoard.isOn(westTry);
					return westTry;
				}
				else // if westTry was shot or is not on the board
				{
					firstHit = new position();
					northTry = new position();
					southTry = new position();
					eastTry = new position();
					westTry = new position();
					first = false;
					second = false;
					third = false;
					fourth = false;
					x = posPos.get((int) (posPos.size() * Math.random()));
					intObj = new Integer(x);
					posPos.remove(intObj);
					move = new position((x % 10) + 1, (x / 10) + 1);
					if (humanBoard.isOn(move) ) {
						firstHit = move;
						
					}
					
					return move;
				}
			}
			else if(westTry.getX() != -1 && aiBoard.getHits().contains(westTry))//if westTry exists and does hit
			{
				westTry = new position(westTry.getX()-1, westTry.getY()); //new westTry
				if(posPos.contains(this.returnPosIndex(westTry)) && this.checkIfOnBoard(westTry)) //if new westTry has not been shot and on board
				{
					intObj = new Integer(this.returnPosIndex(westTry));
					posPos.remove(intObj); //remove the index
					
					return westTry;
				}
				else
				{
					firstHit = new position();
					northTry = new position();
					southTry = new position();
					eastTry = new position();
					westTry = new position();
					first = false;
					second = false;
					third = false;
					fourth = false;
					x = posPos.get((int) (posPos.size() * Math.random()));
					intObj = new Integer(x);
					posPos.remove(intObj);
					move = new position((x % 10) + 1, (x / 10) + 1);
					if (humanBoard.isOn(move) ) {
						firstHit = move;
						
					}
					
					return move;
				}
			}
			else if(westTry.getX() != -1 && aiBoard.getMisses().contains(westTry))
			{
				firstHit = new position();
				northTry = new position();
				southTry = new position();
				eastTry = new position();
				westTry = new position();
				first = false;
				second = false;
				third = false;
				fourth = false;
				x = posPos.get((int) (posPos.size() * Math.random()));
				intObj = new Integer(x);
				posPos.remove(intObj);
				move = new position((x % 10) + 1, (x / 10) + 1);
				if (humanBoard.isOn(move) ) {
					firstHit = move;
					
				}
				
				return move;
			}
			else // if northTry does not exist 
			{
				northTry = new position(firstHit.getX(),firstHit.getY()+1); //create new northTry
				if (posPos.contains(this.returnPosIndex(northTry)) && this.checkIfOnBoard(northTry)) // if northTry's position wasn't shot at before and its on the board
				{
					intObj = new Integer(this.returnPosIndex(northTry));
					posPos.remove(intObj); //remove the index from the list of not shot
					first = humanBoard.isOn(northTry);
					
					return northTry;
				}
				else // if northTry was shot at before or its not on the board
				{
					southTry = new position(firstHit.getX(),firstHit.getY()-1);
					if(posPos.contains(this.returnPosIndex(southTry)) && this.checkIfOnBoard(southTry))//if southTry's position wasn't shot and its on the board
					{
						intObj = new Integer(this.returnPosIndex(southTry));
						posPos.remove(intObj); // remove the index from the list of not shot
						
						return southTry;
					}
					else // if southTry was shot at before or its not on the board
					{
						eastTry = new position(firstHit.getX()+1, firstHit.getY()); // now try east
						if(posPos.contains(this.returnPosIndex(eastTry)) && this.checkIfOnBoard(eastTry)) // if eastTry has not been shot before and is on the board
						{
							intObj = new Integer(this.returnPosIndex(eastTry));
							posPos.remove(intObj); //remove the index 
							
							return eastTry; 
						}
						else //if eastTry was shot at before or its not on the board
						{
							westTry = new position(firstHit.getX()-1, firstHit.getY());// try westTry
							if(posPos.contains(this.returnPosIndex(westTry)) && this.checkIfOnBoard(westTry)) //if westTry has not been shot and on board
							{
								intObj = new Integer(this.returnPosIndex(westTry));
								posPos.remove(intObj); //remove the index
								
								return westTry;
							}
							else // if westTry was shot or is not on the board
							{
								firstHit = new position();
								northTry = new position();
								southTry = new position();
								eastTry = new position();
								westTry = new position();
								first = false;
								second = false;
								third = false;
								fourth = false;
								x = posPos.get((int) (posPos.size() * Math.random()));
								intObj = new Integer(x);
								posPos.remove(intObj);
								move = new position((x % 10) + 1, (x / 10) + 1);
								if (humanBoard.isOn(move) ) {
									firstHit = move;
									
								}
								
								return move;
							}
						}
						
					}
				}
			}
		} 
		else //if it doesn't, generate some random move and see if it hits. 
		{
			x = posPos.get((int) (posPos.size() * Math.random()));
			intObj = new Integer(x);
			posPos.remove(intObj);
			move = new position((x % 10) + 1, (x / 10) + 1);
			if (humanBoard.isOn(move) ) {
				firstHit = move;
				
			}
			
			return move;
		}
		
		
	}
	
	public boolean checkIfOnBoard(position p)
	{
		return(p.getX() >= 1 && p.getX() <= 10 && p.getY() >= 1 && p.getY() <= 10);
		
	}
	public int returnPosIndex(position p) {
		return (((p.getY())-1) * 10 + ((p.getX())-1));
	}

	public void addScore() {
		score++;
	}

	public int getScore() {
		return score;
	}

	

}
