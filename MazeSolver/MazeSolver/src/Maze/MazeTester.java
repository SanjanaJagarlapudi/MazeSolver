package Maze;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.Before;
import org.junit.jupiter.api.Test;
/** This is a tester class. Essentially, this class tests bfs and dfs by printing their maze solutions onto a text file. This text file
 * is then compared to a text file containing the accurate solutions. We test mazes of size 3, 6, 9, and 12 to ensure that it works on mazes of 
 * all sizes.
 * 
 *@author Sanjana Jagarlapudi & Michael Jerome Magbual
 *
 */
class MazeTester {
	
	FileReader frTest;
	BufferedReader brTest;
	FileReader fr2;
	BufferedReader br2;
	
	//***Testing 3 X 3 mazes***: 
	
	//DFS Visit Order Maze
	@Test
	public void dfs3VisitTest() {
		//Generate the mazes with with the testing will occur
		//Since we use a seed in the generateMaze method, we are sure to get the same maze for each test, no matter the size
		MazeGenerator m = new MazeGenerator(3, 3);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		DFS d = new DFS(g.getGraph(), m.getMaze());
		d.dfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			d.dfsVisitOrderFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		File fTest = new File("src/Size3VisitDFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		//Create new Reader in Try/Catch blocks as they throw checked exceptions
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		//compare each line of the test file to the solution file to check if the output is accurate
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();
				
				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		//Close the readers 
		try {
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}	
	}
	
	//From here the rest of the methods have the same implementations, so the same comments and logics apply
	//DFS Hash Path Maze
	@Test
	public void dfs3HashTest(){
		MazeGenerator m = new MazeGenerator(3, 3);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		DFS d = new DFS(g.getGraph(), m.getMaze());
		d.dfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			d.dfsHashFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		File fTest = new File("src/Size3HashDFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();

				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		try {
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
	}
	
	//BFS Visit Order Maze
	@Test
	public void bfs3VisitTest() {
		MazeGenerator m = new MazeGenerator(3, 3);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		BFS b = new BFS(g.getGraph(), m.getMaze());
		b.bfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			b.bfsVisitOrderFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		File fTest = new File("src/Size3VisitBFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();
				
				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		try {
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
	}
	//BFS Hash Maze
	@Test
	public void bfs3HashTest() {
		MazeGenerator m = new MazeGenerator(3, 3);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		BFS b = new BFS(g.getGraph(), m.getMaze());
		b.bfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			b.bfsHashFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		File fTest = new File("src/Size3HashBFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();
				System.out.println(lineExpected);
				System.out.println(lineTest);
				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		try {
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	//***Testing 6 X 6 mazes***: 
	
		//DFS Visit Order Maze
		@Test
		public void dfs6VisitTest() {
			MazeGenerator m = new MazeGenerator(6, 6);
			Graph g = new Graph(m.getMaze());
			g.makeGraph(m.getMaze());;
			DFS d = new DFS(g.getGraph(), m.getMaze());
			d.dfs(0, 0);
			//Print the output of the dfs visit order on a file so it can be compared with the correct solution
			File f  = new File("Test");
			try {
				d.dfsVisitOrderFilePrinter(f);
			}
			catch(IOException x){
				System.out.println(x.getMessage());
			}
			//Compare with correct solution in the file for 
			boolean done = false;
			
			File fTest = new File("src/Size6VisitDFSCorrectSolution");
			
			String lineExpected = "";
			String lineTest = "";
			try {
				frTest = new FileReader(fTest);
				brTest = new BufferedReader(frTest);
				fr2 = new FileReader(f);
				br2 = new BufferedReader(fr2);
			}
			catch (IOException x) {
				System.out.println(x.getMessage());
			}
			
			while(!done) {
				if(lineExpected == null && lineTest == null) {
					done = true;
				}
				
				try {
					lineExpected = brTest.readLine();
					lineTest = br2.readLine();
					
					assertEquals(lineExpected, lineTest);	
				}
				catch(IOException x) {
					System.out.println(x.getMessage());
				}
			}
			try {
				br2.close();
				fr2.close();
				brTest.close();
				frTest.close();
			}
			catch (IOException x) {
				System.out.println(x.getMessage());
			}	
		}
		//DFS Hash Path Maze
		@Test
		public void dfs6HashTest(){
			MazeGenerator m = new MazeGenerator(6, 6);
			Graph g = new Graph(m.getMaze());
			g.makeGraph(m.getMaze());;
			DFS d = new DFS(g.getGraph(), m.getMaze());
			d.dfs(0, 0);
			//Print the output of the dfs visit order on a file so it can be compared with the correct solution
			File f  = new File("Test");
			try {
				d.dfsHashFilePrinter(f);
			}
			catch(IOException x){
				System.out.println(x.getMessage());
			}
			//Compare with correct solution in the file for 
			boolean done = false;
			
			File fTest = new File("src/Size6HashDFSCorrectSolution");
			
			String lineExpected = "";
			String lineTest = "";
			try {
				frTest = new FileReader(fTest);
				brTest = new BufferedReader(frTest);
				fr2 = new FileReader(f);
				br2 = new BufferedReader(fr2);
			}
			catch (IOException x) {
				System.out.println(x.getMessage());
			}
			
			while(!done) {
				if(lineExpected == null && lineTest == null) {
					done = true;
				}
				try {
					lineExpected = brTest.readLine();
					lineTest = br2.readLine();
					
					assertEquals(lineExpected, lineTest);	
				}
				catch(IOException x) {
					System.out.println(x.getMessage());
				}
			}
			try {
				br2.close();
				fr2.close();
				brTest.close();
				frTest.close();
			}
			catch (IOException x) {
				System.out.println(x.getMessage());
			}
			
		}
		
		//BFS Visit Order Maze
		@Test
		public void bfs6VisitTest() {
			MazeGenerator m = new MazeGenerator(6, 6);
			Graph g = new Graph(m.getMaze());
			g.makeGraph(m.getMaze());;
			BFS b = new BFS(g.getGraph(), m.getMaze());
			b.bfs(0, 0);
			//Print the output of the dfs visit order on a file so it can be compared with the correct solution
			File f  = new File("Test");
			try {
				b.bfsVisitOrderFilePrinter(f);
			}
			catch(IOException x){
				System.out.println(x.getMessage());
			}
			//Compare with correct solution in the file for 
			boolean done = false;
			
			File fTest = new File("src/Size6VisitBFSCorrectSolution");
			
			String lineExpected = "";
			String lineTest = "";
			try {
				frTest = new FileReader(fTest);
				brTest = new BufferedReader(frTest);
				fr2 = new FileReader(f);
				br2 = new BufferedReader(fr2);
			}
			catch (IOException x) {
				System.out.println(x.getMessage());
			}
			
			while(!done) {
				if(lineExpected == null && lineTest == null) {
					done = true;
				}
				
				try {
					lineExpected = brTest.readLine();
					lineTest = br2.readLine();
					
					assertEquals(lineExpected, lineTest);	
				}
				catch(IOException x) {
					System.out.println(x.getMessage());
				}
			}
			try {
				br2.close();
				fr2.close();
				brTest.close();
				frTest.close();
			}
			catch (IOException x) {
				System.out.println(x.getMessage());
			}
		}
		//BFS Hash Maze
		@Test
		public void bfs6HashTest() {
			MazeGenerator m = new MazeGenerator(6, 6);
			Graph g = new Graph(m.getMaze());
			g.makeGraph(m.getMaze());;
			BFS b = new BFS(g.getGraph(), m.getMaze());
			b.bfs(0, 0);
			//Print the output of the dfs visit order on a file so it can be compared with the correct solution
			File f  = new File("Test");
			try {
				b.bfsHashFilePrinter(f);
			}
			catch(IOException x){
				System.out.println(x.getMessage());
			}
			//Compare with correct solution in the file for 
			boolean done = false;
			
			File fTest = new File("src/Size6HashBFSCorrectSolution");
			
			String lineExpected = "";
			String lineTest = "";
			try {
				frTest = new FileReader(fTest);
				brTest = new BufferedReader(frTest);
				fr2 = new FileReader(f);
				br2 = new BufferedReader(fr2);
			}
			catch (IOException x) {
				System.out.println(x.getMessage());
			}
			
			while(!done) {
				if(lineExpected == null && lineTest == null) {
					done = true;
				}
				
				try {
					lineExpected = brTest.readLine();
					lineTest = br2.readLine();
					
					assertEquals(lineExpected, lineTest);	
				}
				catch(IOException x) {
					System.out.println(x.getMessage());
				}
			}
			try {
				br2.close();
				fr2.close();
				brTest.close();
				frTest.close();
			}
			catch (IOException x) {
				System.out.println(x.getMessage());
			}
		}
		
		
		
		
		
		
		
		
	//***Testing 9 X 9 mazes***: 
		
	//DFS Visit Order Maze
	@Test
	public void dfs9VisitTest() {
		MazeGenerator m = new MazeGenerator(9, 9);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		DFS d = new DFS(g.getGraph(), m.getMaze());
		d.dfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			d.dfsVisitOrderFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		File fTest = new File("src/Size9VisitDFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();
				
				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		try {
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}	
	}
	//DFS Hash Path Maze
	@Test
	public void dfs9HashTest(){
		MazeGenerator m = new MazeGenerator(9, 9);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		DFS d = new DFS(g.getGraph(), m.getMaze());
		d.dfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			d.dfsHashFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		File fTest = new File("src/Size9HashDFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();
				
				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		try {
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
	}
	
	//BFS Visit Order Maze
	@Test
	public void bfs9VisitTest() {
		MazeGenerator m = new MazeGenerator(9, 9);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		BFS b = new BFS(g.getGraph(), m.getMaze());
		b.bfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			b.bfsVisitOrderFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		File fTest = new File("src/Size9VisitBFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();
				
				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		try {
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
	}
	//BFS Hash Maze
	@Test
	public void bfs9HashTest() {
		MazeGenerator m = new MazeGenerator(9, 9);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		BFS b = new BFS(g.getGraph(), m.getMaze());
		b.bfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			b.bfsHashFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		File fTest = new File("src/Size9HashBFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();
				
				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		try {
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
	}
	
	
	
	
	
	
	
	//***Testing 12 X 12 mazes***: 
	
		//DFS Visit Order Maze
		@Test
		public void dfs12VisitTest() {
			MazeGenerator m = new MazeGenerator(12, 12);
			Graph g = new Graph(m.getMaze());
			g.makeGraph(m.getMaze());;
			DFS d = new DFS(g.getGraph(), m.getMaze());
			d.dfs(0, 0);
			//Print the output of the dfs visit order on a file so it can be compared with the correct solution
			File f  = new File("Test");
			try {
				d.dfsVisitOrderFilePrinter(f);
			}
			catch(IOException x){
				System.out.println(x.getMessage());
			}
			//Compare with correct solution in the file for 
			boolean done = false;
			
			File fTest = new File("src/Size12VisitDFSCorrectSolution");
			
			String lineExpected = "";
			String lineTest = "";
			try {
				frTest = new FileReader(fTest);
				brTest = new BufferedReader(frTest);
				fr2 = new FileReader(f);
				br2 = new BufferedReader(fr2);
			}
			catch (IOException x) {
				System.out.println(x.getMessage());
			}
			
			while(!done) {
				if(lineExpected == null && lineTest == null) {
					done = true;
				}
				
				try {
					lineExpected = brTest.readLine();
					lineTest = br2.readLine();
					
					assertEquals(lineExpected, lineTest);	
				}
				catch(IOException x) {
					System.out.println(x.getMessage());
				}
			}
			try {
				br2.close();
				fr2.close();
				brTest.close();
				frTest.close();
			}
			catch (IOException x) {
				System.out.println(x.getMessage());
			}	
		}
	//DFS Hash Path Maze
	@Test
	public void dfs12HashTest(){
		MazeGenerator m = new MazeGenerator(12, 12);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		DFS d = new DFS(g.getGraph(), m.getMaze());
		d.dfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			d.dfsHashFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		File fTest = new File("src/Size12HashDFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();
				
				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		try {
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
	}
	
	//BFS Visit Order Maze
	@Test
	public void bfs12VisitTest() {
		MazeGenerator m = new MazeGenerator(12, 12);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		BFS b = new BFS(g.getGraph(), m.getMaze());
		b.bfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			b.bfsVisitOrderFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		File fTest = new File("src/Size12VisitBFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();
				
				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		try {
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
	}
	//BFS Hash Maze
	@Test
	public void bfs12HashTest() {
		MazeGenerator m = new MazeGenerator(12, 12);
		Graph g = new Graph(m.getMaze());
		g.makeGraph(m.getMaze());;
		BFS b = new BFS(g.getGraph(), m.getMaze());
		b.bfs(0, 0);
		//Print the output of the dfs visit order on a file so it can be compared with the correct solution
		File f  = new File("Test");
		try {
			b.bfsHashFilePrinter(f);
		}
		catch(IOException x){
			System.out.println(x.getMessage());
		}
		//Compare with correct solution in the file for 
		boolean done = false;
		
		
		File fTest = new File("src/Size12HashBFSCorrectSolution");
		
		String lineExpected = "";
		String lineTest = "";
		try {
			frTest = new FileReader(fTest);
			brTest = new BufferedReader(frTest);
			fr2 = new FileReader(f);
			br2 = new BufferedReader(fr2);
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
		
		while(!done) {
			if(lineExpected == null && lineTest == null) {
				done = true;
			}
			
			try {
				lineExpected = brTest.readLine();
				lineTest = br2.readLine();
				
				assertEquals(lineExpected, lineTest);	
			}
			catch(IOException x) {
				System.out.println(x.getMessage());
			}
		}
		try { 
			br2.close();
			fr2.close();
			brTest.close();
			frTest.close();
		}
		catch (IOException x) {
			System.out.println(x.getMessage());
		}
	}

}
