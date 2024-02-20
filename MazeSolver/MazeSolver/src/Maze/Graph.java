package Maze;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * This class creates an adjacency list representation of the maze. 
 * @author Sanjana Jagarlapudi and Michael Jerome Magbual
 *
 */
public class Graph {
	//The 2d arraylist of Rooms, in which each room contains its own adjacency list
	static ArrayList<ArrayList<Room>> matrix; 
	//The array of integery values to be converted into binary strings in order to determine edges between nodes
	int[][] maze;
	
	/** This is the Constructor Method for the Graph class. It initialize the two instance variables by taking in a int maze, containing the integer values used to 
	 * determine edges between rooms, and making a new 2D arraylist object to hold the adjacency list representation of the maze and rooms.
	 * @param - maze - the integer array containing values of each room (to be converted into binary strings to determine edges)
	 */
	public Graph(int[][] maze) {
		this.maze = maze;
		//Make the graph
		matrix = new ArrayList<ArrayList<Room>>();
	}
	/** This method actually creates a graph representation of the given maze. Each maze consists of rooms. To create a visual representation of the maze, 
	 * we use a 2D arrayList of rooms, such that there is a grid of rooms. Each room then holds an adjacency list of its own containing a list of all its
	 * children. In the given int array, each room is assigned an integer value. We find each rooms children by using the binary representation of this int value,
	 * and determining where it has edges. 
	 * 
	 * @param maze - the integer 2D array containing the values to be converted into a binary string to determine edges.
	 */
	public void makeGraph(int[][] maze) {
		for (int k = 0; k < maze.length; k++) { //Double for loop to initialize the two-d array of rooms
			matrix.add(new ArrayList<Room>());
			for (int g = 0; g < maze[0].length; g++) {
				Room r = new Room(k, g);
				r.setValue(maze[k][g]);
				matrix.get(k).add(r); 
			}
		}
		//in these for loops, every room is checked for neighboring rooms with no walling in between.
				//if a room does not have a wall with another, the other wall is added to its adjacency list, as the absence of a wall is an edge.
				
				for (int i = 0; i < maze.length; i++) { //Double for loop to loop through the 2d array 
					for (int j = 0; j < maze[0].length; j++) {
						//Find command to convert number into four digit binary string
						//The method to obtain the full Binary String of a number is obtained from stack 
						String s = String.format("%4s", Integer.toBinaryString(maze[i][j])).replace(' ', '0'); //obtaining the binary string representation of each room to check for neighbors
						if (Integer.parseInt(s.substring(0, 1)) == 1) { //Checking for a west neighbor
								Room westRoom = matrix.get(i).get(j - 1); //If there is no wall to the west, it means that the room to the west is a child of this node's
								matrix.get(i).get(j).getList().add(westRoom); //Thus add it to this node's adjacency list.
						} 
						if (Integer.parseInt(s.substring(1, 2)) == 1) { //Checking for a east neighbor
								Room eastRoom = matrix.get(i).get(j + 1);//If there is no wall to the east, it means that the room to the east is a child of this node's
								matrix.get(i).get(j).getList().add(eastRoom);//Thus add it to this node's adjacency list.
						}
						if (Integer.parseInt(s.substring(2, 3)) == 1) { //Checking for a south neighbor
								Room southRoom = matrix.get(i + 1).get(j);//If there is no wall to the south, it means that the room to the south is a child of this node's
								matrix.get(i).get(j).getList().add(southRoom);//Thus add it to this node's adjacency list.
						}
						if (Integer.parseInt(s.substring(3, 4)) == 1) { //Checking for a north neighbor
								Room northRoom = matrix.get(i - 1).get(j);//If there is no wall to the north, it means that the room to the north is a child of this node's
								matrix.get(i).get(j).getList().add(northRoom);//Thus add it to this node's adjacency list.
						}		
					}
				}
	}
	
	//Getter methods 
	
	/**  
	 * Returns the adjacency list representation of a maze
	 * @return matrix - the adjacency list representation of a maze
	 */
	public ArrayList<ArrayList<Room>> getGraph(){
		return this.matrix; 
	}
	
	/**
	 * Returns the size of the adjacency list representation of a maze
	 * @return size - the size of the adjacency list representation of a maze
	 */
	public int getSize() {
    	int size = this.matrix.size() * this.matrix.get(0).size();
    	return size;
    }

	
}

