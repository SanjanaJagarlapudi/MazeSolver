package Maze;
import java.util.*;

public class Room {
		//A room is essentially one cell in the maze. 
	    
	    //Since each cell has a location in the maze, we denote this location by row and column number.
	    int row;
	    int col;
	    
	   //Each Room has a String value, such as "#". Create a variable in order to store this value.
	    private String roomVal;

	   //Each Room keeps track of all of its children, so essentially, this is each room's adjacency list.
	    private ArrayList<Room> adjList;
	    
	    //Each individual Room's number in the visitation order of the maze. For example, if visitOrder is 3, this room is the fourth room
	    //to be visited when traversing the maze during dfs or dfs
	    private int visitOrder; //Order in which you visit the nodes
	    
	    //To keep track of each room's parent.
	    private Room parent;
	    
	    //Color is to help BFS and DFs know which nodes have been processed or have not been processed.
	    //white = 0, grey = 1, black = 2
	    private int color; 
	    
	    //Each room's binary value that is converted to see where its edges are missing or in place.
	    private int value;
	
	    /**
	     * Constructor method for all Room objects. A room is essentially one cell in the maze. The constructor initializes all the instance 
	     * variables of the Room class, such as its row, col, adjlist, visitOrder, roomVal, color, and value.
	     * all these values accordingly
	     * @param r - the row number of this room
	     * @param c - the column number of this room 
	     */
	    public Room (int r, int c) { 
	    	this.row = r; //initialize the room's row number
	    	this.col = c; //initialize the room's column number
	    	
	    	adjList = new ArrayList<Room>(); //Contains the adjacency list of this particular node, or room.
	    	
	    	parent = null; //when created, a room's parent is initially null
	    	
	    	visitOrder = -1; //initially visit order is -1, until a new number is assigned in dfs or bfs
	    
	    	roomVal = "";//initially, roomVal is an empty string because this is changed depending on whehter it is 
	    	//apart of the correct path when traversed by dfs or bfs.
	    	
	    	// every node is initially white, as it is not visited by bfs or dfs yet
	    	this.color = 0; 
	    	
	    	//Initialize value to -1 until we can assign its actually value in traversal.
	    	this.value = -1;
	    
	    }
	    
	    //Getters and Setters for all the instance variables of the Room Class
	    
	    public int getRow() {
			return row;
		}
	    
		public void setRow(int row) {
			this.row = row;
		}
		
		public int getCol() {
			return col;
		}
		
		public void setCol(int col) {
			this.col = col;
		}
		
		public int getColor() {
	    	return color;
	    }
		
		public void setColor(int n) {
	    	this.color = n;
	    }
	    
	    public int getVisitOrder() {
	    	return visitOrder;
	    }
	    
	    public void setVisitOrder(int n) {
	    	this.visitOrder = n;
	    }
	    
	    public int getValue() {
			return value;
		}
	    
		public void setValue(int value) {
			this.value = value;
		}
	   
	    public ArrayList<Room> getList() {
	    	return this.adjList;
	    }
	    
	    public Room getParent() {
	    	return this.parent;
	    }
	    
	    public void setParent(Room p) {
	    	this.parent = p;
	    }
	    
	    public void setRoomVal() {
	    	this.roomVal = "#";
	    }
	    
	    public String getRoomVal() {
	    	return this.roomVal;
	    }
	   
	    
	    
	    
	   

	    	    
	
}
