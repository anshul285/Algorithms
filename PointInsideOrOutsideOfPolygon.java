import java.util.Scanner;



public class PointInsideOrOutsideOfPolygon {
	class Point{
		int x,y;
		public Point(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	int max(int a,int b){
		return (a>b)?a:b;
	}
	int min(int a,int b){
		return (a<b)?a:b;
	}
	
	int orientation(Point p1,Point p2,Point p3){
		
		int o=((p2.y-p1.y)*(p3.x-p2.x))-((p3.y-p2.y)*(p2.x-p1.x));
		 if(o==0) return 0;
		return (o>0)?1:2;
	}
	boolean onSegment(Point p1,Point p2,Point p3){
		if(p2.x<=max(p1.x,p3.x)&&p2.x>=min(p1.x,p3.x)&&p2.y<=max(p1.y,p3.y)&&p2.y>=min(p1.y,p3.y)){
			return true;
		}
		return false;
	}
	boolean doIntersect(Point p1,Point q1,Point p2,Point q2){
		int o1=orientation(p1, q1, p2);
		int o2=orientation(p1, q1, q2);
		int o3=orientation(p2, q2, p1);
		int o4=orientation(p2, q2, q1);
		//System.out.println(","+o1+","+o2+","+o3+","+o4);
		//General case
		if(o1!=o2&&o3!=o4){
			return true;
		}
		//special case
		if(o1==0&&onSegment(p1,p2,q1)) return true;
		if(o2==0&&onSegment(p1,q2,q1)) return true;
		if(o3==0&&onSegment(p2,p1,q2)) return true;
		if(o4==0&&onSegment(p2,q1,q2)) return true;
		return false;
		
	}
	boolean isInside(Point[] polygon,int n,Point p){
		
		if(n<3) return false;
		int count = 0;
		Point infPoint = new Point(1000, p.y);
		int i=0;
		do{
			int next = (i+1)%n;
		
			if(doIntersect(polygon[i], polygon[next], p, infPoint)){
				int o = orientation(polygon[i], polygon[next], p);
				
				if(onSegment(p, polygon[i], infPoint)){
					count=count-2;
					
				}
				count++;
			}
			i=next;
			
		}while(i!=0);
		count=Math.abs(count);
		if(count%2==1){
			return true;
		}else{
			return false;
		}
		
	}
	public static void main(String[] arg){
		PointInsideOrOutsideOfPolygon piop = new PointInsideOrOutsideOfPolygon();
		Point[] polygon  =new Point[6];
		polygon[0]  =piop.new Point(1, 3);
		polygon[1]  =piop.new Point(3, 2);
		polygon[2]  =piop.new Point(7, 3);
		polygon[3]  =piop.new Point(6, 4);
		polygon[4]  =piop.new Point(5, 4);
		polygon[5]  =piop.new Point(4, 6);
		Point p = piop.new Point(3, 4);
		boolean result=piop.isInside(polygon,polygon.length,p);
		System.out.println(result);
		
	}

}
