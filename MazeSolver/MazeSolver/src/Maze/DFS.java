package Maze;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/** This Class contains the Depth First Search traversal method of maze, along with printer methods to help visualize the traversal.
 * @author Sanjana Jagarlapudi & Michael Jerome Magbual
 *
 */
public class DFS {
	//The adjacency list representation of the graph used to traverse the maze
	private ArrayList<ArrayList<Room>> matrix;
	//int variable to help track visit order
	int visit;
	//boolean variable to help end DFS traversal when end of maze is reached
	boolean done;
	//A list of every node that is visited during DFS in order
	ArrayList<Room> visitedPath;
	//A list of every node in the actual path taken to solve the maze
	ArrayList<Room> correctPath;
	//The int 2D array containing the values of each cell
	private int[][] maze;
	
	/**
	 * Constructor Method for the DFS class. Initializes all the instance variables in the class to either the parameters
	 * or a new object of the given type.
	 * @param m - adjacency list representation of maze
	 * @param thisMaze - integer matrix containing in values of each room
	 */
	public DFS(ArrayList<ArrayList<Room>> m, int[][] thisMaze) {
		this.matrix = m;
		maze = thisMaze;
		
		correctPath = new ArrayList<Room>();
		visitedPath = new ArrayList<Room>();
	}
	
	/**
	 * This method executes the actual DFS traversal of the maze. This is essentially done by calling a helper method, dfsVisit, on every 
	 * room in the maze.
	 * @param row - The row index of the starting room
	 * @param col - the col index of the starting room
	 */
	public void dfs(int row, int col) {
		done = false; //set done to be false initially 
		visit = 0; //No nodes are visited yet, so set visit to 0; 
		for(int i = 0; i < matrix.size(); i++) { //Traverse through entire maze to call dfs visit on every node
			for(int j = 0; j < matrix.get(0).size(); j++) {
				Room room = matrix.get(i).get(j);
				if (room.getColor() == 0) { //if the node has not been visited yet and...
					if(!done) { //done is not false
						dfsVisit(room); //then call dfsVisit on the current room
					}
					else { //Otherwise end the method.
						return;
					}	
				}
			}
		}
	}
	
	/**
	 * Helper method for DFS. Processes a node and then calls itself on that nodes next consecutive child until it reaches the end of the maze.
	 * @param r - the room that dfs visit initially processes before moving on to its children
	 */
	private void dfsVisit(Room r) {
		r.setColor(1); //Set the color of the room we are looking at to "grey"
		
		r.setVisitOrder(visit); //Set the visit order of the current room to visit
		visitedPath.add(r); //As we are visiting the current node, add it to list of visited nodes for future use.
		visit = visit + 1; //Increment visit by one, for the next node that is to be visited
		
		
		for(Room v : r.getList()) { //Get the children of the current node
			if(v.getColor() == 0) { //if we have node visited the child node yet, meaning it is white
				v.setParent(r); //then set the child nodes parent as r
				if(v.getRow() == matrix.size() - 1 && v.getCol() == matrix.size() - 1) { //Stop running dfs if we've reached the end of the maze
					v.setVisitOrder(visit); //Set the visit order of the last cell in the maze, as we are no longer running dfsVisit on it.
					visitedPath.add(v);//Add the current node to the list of visited nodes, as it has just been visited
					done = true; //Set done to true so dfsVisit is not called in dfs again
					return; 
				}
				dfsVisit(v);//Otherwise, call dfs visit on v, the child node.
			}
		}
		r.setColor(2); //Set r to black to denote that we are done looking at it

	}
	
	/**
	 * This method prints out the Visitation order of Rooms after DFS is run on the maze. 
	 */
	public void dfsDisplayMazeVisitationOrder() {
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
	 * This method prints out the actual path taken to solve the maze using #'s after DFS is run on the maze
	 */
	public void dfsDisplayMazePath() {
		//First, we must backtrack from the last cell to the first cell to find the correct shortest path taken...
		//We do this by tracing back the parents of each node starting from the last node.
		Room r = matrix.get(matrix.size() - 1).get(matrix.size() - 1); //First obtain the last room in the maze
		while(r.getRow() != 0 || r.getCol() != 0 ) { //While we do not reach the starting index.
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
	 * This method prints out the coordinate path, number of visited cells, and size of the path taken after dfs is run
	 * @return Path - a string representation of the coordinate path, number of visited cells, and size of the path
	 */
	public String getVisitedPathDFS() {
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
	 * This method prints the dfs visit order solution on a given file
	 * @param f - the file on which the solution is printed 
	 * @throws IOException
	 */
	public void dfsVisitOrderFilePrinter(File f) throws IOException{
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
				p.print((maze[i][j] & 8) == 0 ? "| " : "  ");
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
	 * This method prints the dfs Hash solution on a given file
	 * @param f - file on which the solution is printed
	 * @throws IOException
	 */
	public void dfsHashFilePrinter(File f) throws IOException{
		PrintWriter p = new PrintWriter(f);
		
		//First, we must backtrack from the last cell to the first cell to find the correct shortest path taken...
				//We do this by tracing back the parents of each node starting from the last node.
				Room r = matrix.get(matrix.size() - 1).get(matrix.size() - 1); //First obtain the last room in the maze
				while(r.getRow() != 0 || r.getCol() != 0 ) { //While we do not reach the starting index.
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
				p.print((maze[i][j] & 8) == 0 ? "| " : "  ");
	
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

	
	

