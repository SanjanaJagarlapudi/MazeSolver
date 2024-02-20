package Maze;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/** This Class contains the Breath First Search traversal method of maze, along with printer methods to help visualize the traversal.
 * @author Sanjana Jagarlapudi & Michael Jerome Magbual
 *
 */
public class BFS {
	//The adjacency list representation of the maze that BFS uses to traverse
	private ArrayList<ArrayList<Room>> matrix; 
	//The int 2D array containing the values of each cell
	private int[][] maze;
	//A list of every node that is visited during BFS in order
	ArrayList<Room> visitedPath;
	//A list of every node in the actual path taken to solve the maze
	ArrayList<Room> correctPath;
	
	//time is visitation order, take care of starting time 

	//Enqueue: Adding an item to the end of the queue
	//Dequeue: Taking off an item from the front of the queue
	
	/**
	 * Constructor Method for the BFS class. Initializes all the instance variables in the class to either the parameters
	 * or a new object of the given type.
	 * @param m - adjacency list representation of maze
	 * @param thisMaze - integer matrix containing in values of each room
	 */
	public BFS(ArrayList<ArrayList<Room>> m, int[][] thisMaze) {
		this.matrix = m; 
		maze = thisMaze; 
		visitedPath = new ArrayList<Room>();
		correctPath = new ArrayList<Room>();
		
	}
	
	/**
	 * This method executes the actual BFS traversal of the maze. This is done using a queue. Rooms are incrementally added and removed
	 *  from the queue, based on their children and the traversal ends when we've reached the room at the end of the maze 
	 * @param row - The row index of the starting room
	 * @param col - the col index of the starting room
	 */
	public void bfs(int row, int col) {
		int visitIterator = 1; // In order to track the order in which rooms are visited, make a int variable that can be incremented
		Room room = this.matrix.get(row).get(col); //obtain the room located at the given indexes
		room.setVisitOrder(0); //Initialize the first room as the first in the order of rooms visited.
		visitedPath.add(room); //Add the current room into the list of visited rooms.
		Queue q = new LinkedList<Room>(); //Create a Queue to keep track of the traversal of nodes
	
		q.add(room); //Add the starting room to queue to begin
	
		while(q.size() != 0) { //As long as the queue isn't empty...
			Room current = (Room) q.remove(); //Remove the item from the front of the queue
			for(Room c: current.getList()) { //Obtain all of its children
				if(c.getColor() == 0) { //As long as the child node hasn't been visisted before
					c.setColor(1); //Set its color as grey to denote that we are working with it
					c.setVisitOrder(visitIterator++); //increment the visit order and set this nodes visit order, as it has just been "visited"
					visitedPath.add(c); //Add the child node to list of visited nodes
					c.setParent(current); //Update the parent of the child node as the parent
					q.add(c); //Add it to the queue so its children can go on to be processed
					if(c.getRow() == matrix.size() - 1 && c.getCol() == matrix.size() - 1) { //Stop running bfs if we've reached the end of the maze
						return;
					}
				}
				
			}
			current.setColor(2); //Set the room's color as black to denote that we are done with it
		}
	}
	/**
	 * This method prints out the Visitation order of Rooms after BFS is run on the maze. 
	 */
	public void bfsDisplayMazeVisitationOrder() {
			for (int i = 0; i < matrix.size(); i++) {
				// draw the north edge
				
				for (int j = 0; j < matrix.get(0).size(); j++) {
					//If we are at the beginning, then we need to have a opening
					if(i == 0 && j == 0) {
						System.out.print((maze[i][j] & 1) == 0 ? "+   " : "+   ");
					}
					//Otherwise, we just want a regular wall
					else {
						System.out.print((maze[i][j] & 1) == 0 ? "+---" : "+   ");
					}
				}
				
				System.out.println("+");
				// draw the west edge
				
				for (int j = 0; j < matrix.get(0).size(); j++) {
					System.out.print((maze[i][j] & 8) == 0 ? "| " : "  ");
					//Reset the visitation order if it is greater than nine
					if(matrix.get(i).get(j).getVisitOrder() > 9) {
						matrix.get(i).get(j).setVisitOrder((matrix.get(i).get(j).getVisitOrder() % 10));
					}
					
					//if the node has been visited, then print the visitation order out
					if(matrix.get(i).get(j).getVisitOrder() != -1) {
						System.out.print(Integer.toString(matrix.get(i).get(j).getVisitOrder()) + " ");
					}
					//Else, we do not want to print anything out
					else {
						System.out.print("  ");
					}
				}
				System.out.println("|");
			}
			
			// draw the bottom line
			for (int j = 0; j < matrix.get(0).size(); j++) {
				//If we are at the end of the maze, then we want to print a opening
				if( j == matrix.size() - 1) {
					System.out.print("+   ");
				}
				//Otherwise print a wall
				else {
					System.out.print("+---");
				}
				
			}
			System.out.println("+");
			
			
		}
	/**
	 * This method prints out the actual path taken to solve the maze using #'s after BFS is run on the maze
	 */
	public void bfsDisplayMazePath() {
		//First, we must backtrack from the last cell to the first cell to find the correct shortest path taken...
		//We do this by tracing back the parents of each node starting from the last node.
		Room r = matrix.get(matrix.size() - 1).get(matrix.size() - 1);//First obtain the last room in the maze
		
		while(r.getRow() != 0 || r.getCol() != 0 ) { //As long as we are not back at the beginning of the maze
			r.setRoomVal(); //Set the value of room as "#"
			correctPath.add(r); //Add this node to the list of nodes in the correct path for future reference
			r = r.getParent(); //set r to its parent and repeat the process 
		}
		r.setRoomVal(); //at this point, r is the first room in the maze, thus set its value to "#"
		correctPath.add(r);//and add it to the correct path
		//now start to actually print the maze:
		for (int i = 0; i < matrix.size(); i++) {
			// draw the north edge
			
			for (int j = 0; j < matrix.get(0).size(); j++) {
				//If we are at the beginning, then we need to have a opening
				if(i == 0 && j == 0) {
					System.out.print((maze[i][j] & 1) == 0 ? "+   " : "+   ");
				}
				//Otherwise, we just want a regular wall
				else {
					System.out.print((maze[i][j] & 1) == 0 ? "+---" : "+   ");
				}
			}
			
			System.out.println("+");
			// draw the west edge
			
			for (int j = 0; j < matrix.get(0).size(); j++) {
				System.out.print((maze[i][j] & 8) == 0 ? "| " : "  ");

				if(matrix.get(i).get(j).getRoomVal().equals("#")){ //if the room's value is "#", meaning it is a part of the correct path...
					System.out.print("#" + " "); //then print out a hash
				}
				//Else, we do not want to print anything out
				else {
					System.out.print("  ");
				}
			}
			System.out.println("|");
		}
		
		// draw the bottom line
		for (int j = 0; j < matrix.get(0).size(); j++) {
			//If we are at the end of the maze, then we want to print a opening
			if( j == matrix.size() - 1) {
				System.out.print("+   ");
			}
			//Otherwise print a wall
			else {
				System.out.print("+---");
			}
		}
		System.out.println("+");
		
	}
	
	/**
	 * This method prints out the coordinate path, number of visited cells, and size of the path taken after bfs is run
	 * @return Path - a string representation of the coordinate path, number of visited cells, and size of the path
	 */
	public String getVisitedPathBFS() {
		String coordinates = "";
		for(int i = correctPath.size() - 1; i >= 0; i--) { //Get the coordinates of every node in the correct path as a string representation
			coordinates += "(" + correctPath.get(i).getRow() + ", " + correctPath.get(i).getCol() + ")  ";
		}
		//Add other useful information 
		String Path = "Path : " + coordinates + "\n" + "Length of Path: " + correctPath.size() + "\n" + "Number of Visited Cells: " + visitedPath.size();
		
		return Path; //Return the final string 
		
	}
	
	
	
	//Methods for the tester class, instead of printing the traversal, these save and write them in a file.
	
	/**
	 * This method prints the bfs visit order solution on a given file
	 * @param f - the file on which the solution is printed 
	 * @throws IOException
	 */
	public void bfsVisitOrderFilePrinter(File f) throws IOException{
		PrintWriter p = new PrintWriter(f);
		
		for (int i = 0; i < matrix.size(); i++) {
			// draw the north edge
			
			for (int j = 0; j < matrix.get(0).size(); j++) {
				//If we are at the beginning, then we need to have a opening
				if(i == 0 && j == 0) {
					p.write((maze[i][j] & 1) == 0 ? "+   " : "+   ");
				}
				//Otherwise, we just want a regular wall
				else {
					p.write((maze[i][j] & 1) == 0 ? "+---" : "+   ");
				}
			}
			
			p.write("+\n");
			// draw the west edge
			
			for (int j = 0; j < matrix.get(0).size(); j++) {
				p.write((maze[i][j] & 8) == 0 ? "| " : "  ");
				//Reset the visitation order if it is greater than nine
				if(matrix.get(i).get(j).getVisitOrder() > 9) {
					matrix.get(i).get(j).setVisitOrder((matrix.get(i).get(j).getVisitOrder() % 10));
				}
				
				//if the node has been visited, then print the visitation order out
				if(matrix.get(i).get(j).getVisitOrder() != -1) {
					p.write(Integer.toString(matrix.get(i).get(j).getVisitOrder()) + " ");
				}
				//Else, we do not want to print anything out
				else {
					p.write("  ");
				}
			}
			p.write("|\n");
		}
		
		// draw the bottom line
		for (int j = 0; j < matrix.get(0).size(); j++) {
			//If we are at the end of the maze, then we want to print a opening
			if( j == matrix.size() - 1) {
				p.write("+   ");
			}
			//Otherwise print a wall
			else {
				p.write("+---");
			}
			
		}
		p.write("+\n");
		p.close();
		
		
	}
	
	/**
	 * This method prints the bfs hash solution on a given file
	 * @param f - the file on which the solution is printed 
	 * @throws IOException
	 */
	public void bfsHashFilePrinter(File f) throws IOException{
		PrintWriter p = new PrintWriter(f);
		
		//First, we must backtrack from the last cell to the first cell to find the correct shortest path taken...
		//We do this by tracing back the parents of each node starting from the last node.
		Room r = matrix.get(matrix.size() - 1).get(matrix.size() - 1);//First obtain the last room in the maze
		
		while(r.getRow() != 0 || r.getCol() != 0 ) { //As long as we are not back at the beginning of the maze
			r.setRoomVal(); //Set the value of room as "#"
			correctPath.add(r); //Add this node to the list of nodes in the correct path for future reference
			r = r.getParent(); //set r to its parent and repeat the process 
		}
		r.setRoomVal(); //at this point, r is the first room in the maze, thus set its value to "#"
		correctPath.add(r);//and add it to the correct path
		
		for (int i = 0; i < matrix.size(); i++) {
			// draw the north edge
			
			for (int j = 0; j < matrix.get(0).size(); j++) {
				//If we are at the beginning, then we need to have a opening
				if(i == 0 && j == 0) {
					p.write((maze[i][j] & 1) == 0 ? "+   " : "+   ");
				} 
				//Otherwise, we just want a regular wall
				else {
					p.write((maze[i][j] & 1) == 0 ? "+---" : "+   ");
				}
			}
			
			p.write("+\n");
			// draw the west edge
			
			for (int j = 0; j < matrix.get(0).size(); j++) {
				p.write((maze[i][j] & 8) == 0 ? "| " : "  ");

				if(matrix.get(i).get(j).getRoomVal().equals("#")){ //if the room's value is "#", meaning it is a part of the correct path...
					p.write("#" + " "); //then print out a hash
				}
				//Else, we do not want to print anything out
				else {
					p.write("  ");
				}
			}
			p.write("|\n");
		}
		
		// draw the bottom line
		for (int j = 0; j < matrix.get(0).size(); j++) {
			//If we are at the end of the maze, then we want to print a opening
			if( j == matrix.size() - 1) {
				p.write("+   ");
			}
			//Otherwise print a wall
			else {
				p.write("+---");
			}
		}
		p.write("+\n");
		p.close();
				
	}
	
}
