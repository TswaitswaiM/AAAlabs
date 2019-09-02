
public class Coordinate {

	int y;
	int x;
	double g = 0;
	double h = 0;
	double f = 0;
	double distance;
	int CoNo;
	public Coordinate parent;
	public Coordinate(String a) {
		String[] co = a.split(",");
		y = Integer.parseInt(co[1]);	
		x = Integer.parseInt(co[0]);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) {
			return true;
		}
		if(obj == null || obj.getClass()!=this.getClass()) {
			return false;
		}
		Coordinate co = (Coordinate) obj;
		return(co.x == this.x && co.y == this.y);
	}
	
	@Override
	public String toString() {
		return String.format(x+","+y);
	}
	
	public Coordinate(int xco ,int yco) {
		
		x = xco;	
		y = yco;
	}
}
