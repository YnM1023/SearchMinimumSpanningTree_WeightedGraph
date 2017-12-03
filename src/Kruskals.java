// Name: Yingnan Mei
// NetID: yxm173430@utdallas.edu


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Kruskals {
	PriorityQueue<Edge> edges;
	DisjSets graph;
	// Note: String List below can be replaced by a HashMap to get better performance in runtime if the size of data is too large.
	LinkedList<String> vertices;
	
	class Edge implements Comparable<Edge>{
		String v1;
		String v2;
		int dist;
		
		Edge(String v1, String v2, int dist){
			this.v1 = v1;
			this.v2 = v2;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Edge e) {
			if(this.dist<e.dist) return -1;
			else if(this.dist>e.dist) return 1;
			return 0;
		}	
	}
	
	public Kruskals (File filename) {
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			
			String line = "";
			line = br.readLine();
			int numberOfVertices = Integer.parseInt(line);
			
			// construct the DisjSets and vertices for this question:
			graph = new DisjSets(numberOfVertices);
			vertices = new LinkedList<>();
			edges = new PriorityQueue<>();
			// take these vertices in the k's String array
			System.out.println("=========== All Cities==========");
			
			for(int i=0;i<numberOfVertices;i++) {
				line = br.readLine();
				vertices.add(line);
				System.out.println(line);
			}
			
			System.out.println("===========All Edges============");
			for(int i=0;i<numberOfVertices;i++) {
				line = br.readLine();
				int numberOfEdges = Integer.parseInt(line);
				String v1 = vertices.get(i);
				for(int j=0;j<numberOfEdges;j++) {
					String v2 = br.readLine();
					int dist = Integer.parseInt(br.readLine());
					edges.add(new Edge(v1, v2, dist));
					System.out.println(v1+" | "+dist+" | "+v2);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n"+"Reading data process finished!"+"\n");
		System.out.println("------------End of reading-------------");
	}
	
	public void searchMinSpanTree() {
		System.out.println("\n"+"------Start searching for Minimum Spanning Tree in the Graph:------");
		int sumOfDist = 0;
		while(!edges.isEmpty()) {
			Edge edge = edges.poll();
			int setOfv1 = graph.find(vertices.indexOf(edge.v1));
			int setOfv2 = graph.find(vertices.indexOf(edge.v2));
			if(setOfv1!=setOfv2) {
				System.out.println("add Edge: "+edge.v1+" | "+edge.dist+" | "+edge.v2);
				graph.union(setOfv1, setOfv2);
				sumOfDist+=edge.dist;
			}
		}
		System.out.println("\n"+"-----------Searching process finished!------------");
		System.out.println("The sum of edges' lengths is: "+sumOfDist);
	}
	
	public static void main(String[] args) {
		System.out.println("Program has been started!");
		System.out.println("Start reading data form file: assn9_data.txt");
		System.out.println("--------------Note---------------");
		System.out.println("Since reading data from Excel needs importing a jar file, I think it is complex for testing by TA");
		System.out.println("So I have moved the data into a txt file named as 'assn9_data.txt', please put it in the root directory of this project(in same level of folder 'src')");
		System.out.println("If there is any error in the process of reading data, please check the path of file: assn9_data.txt");
		System.out.println("---------------------------------");
		File filename = new File("assn9_data.txt");
		Kruskals k = new Kruskals(filename);
		k.searchMinSpanTree();		
	}
}
