import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
public class FindPath {
	static int range = 25;
	
	public static int MinDist(ArrayList<Integer> distance, Boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for(int i = 0; i < distance.size(); i++) {
			if( distance.get(i) <= min) {
				 min = distance.get(i);
				index = i;
			}
		}
		return index;
	}
	
	public static boolean IsValid(int x, int y,ArrayList<Coordinate> Colist) {
		Coordinate co = new Coordinate(x,y);
		return (x >= 0) && (x < range) && 
		           (y >= 0) && (y < range); 
	}
	
	public static boolean isDestination(int x, int y, Coordinate end) 
	{ 
	    if (x == end.x && y == end.y) 
	        return (true); 
	    else
	        return (false); 
	} 
	
	public static int linearSearch(ArrayList<Coordinate> Colist, Coordinate key) {
		int n = Colist.size();
		int index = 0;

		while (index <= (n - 1) && !Colist.get(index).equals(key)) {
			index = index + 1;

		}

		if (index > n - 1) {
			index = -1;
		}

		return index;
	}
	
	
	public static boolean isClear(ArrayList<Coordinate> obsList, Coordinate check) {
		
		return (!obsList.contains(check));
	}
	
	public static double calculateHValue(int row, int col, Coordinate end) 
	{ 
	    // Return using the distance formula 
	    return ((double)Math.sqrt((row-end.x)*(row-end.x) 
	                          + (col-end.y)*(col-end.y))); 
	} 
    public static int manhattanDistance(Coordinate a, Coordinate b) {
		
		int distance = Math.abs(b.x-a.x) + Math.abs(b.y-a.y);
		return distance;
	}
    
    
    public static ArrayList<Coordinate> adjacent(Coordinate node, int[][] graph, ArrayList<Coordinate> Colist) {

        ArrayList<Coordinate> neighbours = new ArrayList<>();

        for(int i = 0; i < Colist.size();++i){

            if(graph[node.CoNo][i] == 1){
                neighbours.add(Colist.get(i));
            }
        }
        return neighbours;
    }
    
    public static ArrayList<Coordinate> PrintPath(Coordinate start, Coordinate end) {

        ArrayList<Coordinate> path = new ArrayList<>();

        Coordinate current = end;

        while (current != start) {
            path.add(current);
            current = current.parent;
        }

        return path;
    }
	
	public static void AStar(ArrayList<Coordinate> Colist, int graph[][], int start, int end) {
		for(int i = 0; i < Colist.size();i++) {
			Colist.get(i).CoNo = i;
		}
		
		 Coordinate startNode = Colist.get(start);
		 
		 startNode.g = 0;
		 startNode.h = 0;
		 startNode.f = 0;
	     Coordinate endNode = Colist.get(end);
	     
    
       // System.out.println("In Astar");
		
		//open list		
		ArrayList<Coordinate> open= new ArrayList<Coordinate>();
		//closed list	
		ArrayList<Coordinate> closed= new ArrayList<Coordinate>();
		
		boolean allVisited = true;
		
		open.add(startNode); 
		
		while(open.size()>0) {
			 //System.out.println("In while");
			
			Coordinate current = open.get(0);
			
			
			for (int i = 1; i < open.size(); ++i) {

                if (open.get(i).f < current.f || open.get(i).f == current.f && open.get(i).h < current.h) {
                    current = open.get(i);
                }
            }
			
			open.remove(current);
			closed.add(current);
			
			//System.out.print(current.toString()+" "+current.CoNo+" ");
			if (current.equals(endNode)) {

                allVisited = false;

                ArrayList<Coordinate> path = (PrintPath(Colist.get(start), Colist.get(end)));

                System.out.print(Colist.get(start).CoNo + " ");

                for(int i = path.size()-1;i >= 0;--i){
                    System.out.print(path.get(i).CoNo+ " ");
                }
                break;
            }
			
			ArrayList<Coordinate> neigbour = adjacent(current,graph,Colist);
			
			for (int i = 0; i < neigbour.size(); ++i) {

                if (closed.contains(neigbour.get(i))) {
                    continue;
                }

                double cost = current.g + manhattanDistance(current, neigbour.get(i));

                if (cost < neigbour.get(i).g || !open.contains(neigbour.get(i))) {
                    neigbour.get(i).g = cost;
                    neigbour.get(i).h = calculateHValue(neigbour.get(i).x,neigbour.get(i).y, Colist.get(end));
                    neigbour.get(i).parent = current;

                    if (!open.contains(neigbour.get(i))) {
                        open.add(neigbour.get(i));
                    }

                }
            }
			
			
			
	}
		if(allVisited == true){
            System.out.println("-1");
        }
}

}
