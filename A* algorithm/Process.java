
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.util.Scanner;


public class Process {
	static boolean onSegment(Coordinate p,Coordinate q,Coordinate r) {
		if(q.x <= Math.max(p.x,r.x ) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(p.y, r.y) & q.y >= Math.min(p.y, r.y)) 
			return true;
		
		
	    return false;
	
			
	}
	
	static Coordinate[] justy;
	static ArrayList<Coordinate> OBS;
	
	boolean intersects(Coordinate linea1,Coordinate linea2, Coordinate lineb1, Coordinate lineb2) {
		float bx = linea2.x - linea1.x;
		float by = linea2.y - linea1.y;
		float dx = lineb2.x - lineb1.x;
		float dy = lineb2.y - lineb1.y;
		float b_dot_d_perp = bx * dy - by * dx;
		if(b_dot_d_perp == 0) {
			return false;
		}
		
		float cx = lineb1.x - linea1.x;
		float cy = lineb1.y - linea1.y;
		
		float t = (cx*by-cy*dx)/b_dot_d_perp;
		
		if(t < 0 || t > 1) {
			return false;
		}
		
		float u = (cx*by-by*bx)/b_dot_d_perp;
		
		if(u < 0 || u>1) {
			return false;
		}
		
		return true;
	}
	
	static int orientation(Coordinate p,Coordinate q, Coordinate r) {
		int val = (q.y -p.y) * (r.x-q.x) - (q.x-p.x) * (r.y-q.y);
		
		if(val == 0) return 0;
		
		return (val > 0)? 1: 2;
	
	}
	
	static boolean doIntersect(Coordinate p1, Coordinate q1, Coordinate p2, Coordinate q2) {
		int o1 = orientation(p1,q1,p2);
		int o2 = orientation(p1,q1,q2);
		int o3 = orientation(p2,q2,p1);
		int o4 = orientation(p2,q2,q1);
		
		if( o1 != o2 && o3 != o4) return true;
		
		if( o1 == 0 && onSegment(p1,p2,q1)) return true;
		
		if( o2 == 0 && onSegment(p1,q2,q1)) return true;
		
		if( o3 == 0 && onSegment(p2,p1,q2)) return true;
		
		if( o4 == 0 && onSegment(p2,q1,q2)) return true;
		
		
		return false;
		
		
	}
	
	public static Coordinate[] removeCollision(Coordinate[] colided, Coordinate[] obstacle) {
	
		ArrayList<Coordinate> coll = new ArrayList<Coordinate>();
		for(int i = 0; i < colided.length; i++) {
			boolean boolobs = false;
			for(int j = 0;j< obstacle.length;j++) {
				if((colided[i].equals(obstacle[j]))) {
					boolobs = true;	
					break;
				}
			}
			if(boolobs == false) coll.add(colided[i]);
			
		}
		Coordinate[] myArray = coll.toArray(new Coordinate[coll.size()]);
		
		return myArray;
		
	} 
	
	
	public static Coordinate[] selectionSort(Coordinate[] arr) {
		for(int i = 0;i < arr.length; i++) {
			Coordinate min = arr[i];
			int minPos = i;
			for(int j = i+1; j < arr.length; j++ ) {
				if(arr[j].distance < min.distance) {
					min = arr[j];
					minPos = j;
				}
				
			}
			
			Coordinate temp = arr[i];
			arr[i] = min;
			arr[minPos] = temp;
			
		}
		
		return arr;
	}
	
	public static Coordinate[] knearest(Coordinate[] points, int k, Coordinate C) {
		for(int i = 0; i < points.length; i++) {
			points[i].distance = Math.sqrt((points[i].x-C.x)*(points[i].x-C.x)+(points[i].y-C.y)*(points[i].y-C.y));
		}
		selectionSort(points);
		
		
		return Arrays.copyOfRange(points, 1, 7);
			
	}
	
	/*public static Coordinate[] createEdge(Coordinate[] newstuff, Coordinate[] knn) {
		//do it in main
	}*/
	
	public static Coordinate[] findObstaclePoints(Coordinate top, Coordinate bottom) {
		
		ArrayList<Coordinate> arrlst = new ArrayList<Coordinate>();
		for(int i = top.x;i <= bottom.x; i++) {
			for(int j = top.y; j <= bottom.y ; j++) {
				Coordinate temp = new Coordinate(i,j);
				arrlst.add(temp);
			}
		}
		Coordinate[] myArray = arrlst.toArray(new Coordinate[arrlst.size()]);
		
		return myArray;
	}
	
	public static int findIndex(Coordinate[] points, Coordinate a) {
		for(int i =0;i<points.length;i++) {
		//	System.out.println(points[i].toString()+";"+a.toString());
			if(a.equals(points[i])) {
				return i;
			}
		}
		return -1;
	}
	
	public static void printMatrix(int print[][]) {
		for(int i = 0;i < print.length; i++) {
			for(int j = 0;j < print[i].length;j++) {
				System.out.print(print[i][j]+" ");
			}
			System.out.print("\n");
		}
	}
	
	public static int[][] processInput(){
		Scanner in = new Scanner(System.in);
		String[] inputs = in.nextLine().split(" ");
		int neighbours = Integer.parseInt(inputs[0]);
		
		int obstacleNo = Integer.parseInt(inputs[1]);
		Coordinate[] obstacles =  new Coordinate[obstacleNo*2];
		
		
		int pointNo = Integer.parseInt(inputs[2]);
		Coordinate[] points =  new Coordinate[pointNo+2];
		
		int dimension = Integer.parseInt(inputs[3]);
		
		Coordinate startpoint = new Coordinate(in.nextLine());
		points[0] = startpoint;
		Coordinate endpoint = new Coordinate(in.nextLine());
		points[1] = endpoint;
		ArrayList<Coordinate> arlst = new ArrayList<Coordinate>();
		for(int i = 0;i < obstacleNo; i++) {
			String[] co = in.nextLine().split(";");
			Coordinate temp = new Coordinate(co[0]);
			Coordinate temp1 = new Coordinate(co[1]);
			arlst.add(temp);
			arlst.add(temp1);
			
		}
		
		obstacles = arlst.toArray(new Coordinate[arlst.size()])	;	
		
		
		for(int i = 0;i < pointNo; i++) {
			Coordinate temp = new Coordinate(in.nextLine());
			points[i+2] = temp;
		}
		
		ArrayList<Coordinate> arrlst = new ArrayList<Coordinate>();
		for(int i = 0; i < obstacleNo*2; i = i+2) {
			Coordinate[] ob = findObstaclePoints(obstacles[i], obstacles[i+1]);
			for(int j = 0;j < ob.length; j++) {
				arrlst.add(ob[j]);
			}
		}
		
		
		Coordinate[] allObs = arrlst.toArray(new Coordinate[arrlst.size()]);
		
		OBS = arrlst;
		Coordinate[] noCollision = removeCollision(points, allObs);
		Coordinate[] just = removeCollision(points, allObs);
		justy = removeCollision(points, allObs);
		int[][] matr = new int[noCollision.length][noCollision.length];
		
		
		ArrayList<Coordinate[]> lineslist = new ArrayList<Coordinate[]>();
		
		for(int i = 0;i < obstacles.length; i=i+2) {
			Coordinate[] line1 = { obstacles[i],new Coordinate(obstacles[i].x,obstacles[i+1].y)};
			Coordinate[] line2 = { obstacles[i],new Coordinate(obstacles[i+1].x,obstacles[i].y)};
			Coordinate[] line3 = { obstacles[i+1],new Coordinate(obstacles[i+1].x,obstacles[i].y)};
			Coordinate[] line4 = { obstacles[i+1],new Coordinate(obstacles[i].x,obstacles[i+1].y)};
			lineslist.add(line1);
			lineslist.add(line2);
			lineslist.add(line3);
			lineslist.add(line4);
			
		}
		Coordinate[][] obslines = lineslist.toArray(new Coordinate[2][lineslist.size()]);	
		
		for( int i = 0; i < noCollision.length; i++) {
			Coordinate[] temp = knearest(noCollision, neighbours, just[i]);
			for(int j = 0; j < temp.length; j++) {
				boolean bool = false;
				for(int k = 0; k < obslines.length; k++) {
					if((doIntersect(just[i],temp[j],obslines[k][0],obslines[k][1]))){
						bool = true;
						break;
						
				    }
								
					
				}
				if(bool == false) {
					int index = findIndex(just, temp[j]);
					matr[i][index] = 1;
				}
			}
			
		}
		return matr;
	}
	
	public  ArrayList<Coordinate> allPoints(){
		ArrayList<Coordinate> co = new ArrayList<>(Arrays.asList(justy));
		return co;
	}
	public ArrayList<Coordinate> allOBS(){
		return OBS;
	}
	
	public static int manhattanDistance(Coordinate a, Coordinate b) {
		
		int distance = Math.abs(b.x-a.x) + Math.abs(b.y-a.y);
		return distance;
	}
	
	public static int TotalDistance(int start, int end) {
		//System.out.println("calculating");
		int TotalDist = 0;
			Coordinate a = justy[start];
			Coordinate b = justy[end];
			TotalDist = TotalDist + manhattanDistance(a,b);
		return TotalDist;
	}
	
	public static void main(String[] args) {
		
		
		
		printMatrix(processInput());
		
		
		
	}

}
