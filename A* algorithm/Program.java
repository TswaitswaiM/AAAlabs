
import java.util.ArrayList;

public class Program {
	
	

	public static void main(String[] args) {
		
		Process p = new Process();
		
		
		int[][] matr = p.processInput();
		//processBFS(matr);
		//System.out.println();
		//ShortestPath t = new ShortestPath();
		// t.dijkstra(matr, 0,1);
		//int dist =p.TotalDistance(0,1);
		//System.out.println();
		//System.out.println(dist);
		 
		 ArrayList<Coordinate> points =  p.allPoints();
		 //System.out.println("points "+ points);
		 FindPath t = new FindPath();
		// System.out.println("called ASTar");
		 t.AStar(points, matr, 0, 1);
		 
		
		
	}
}
