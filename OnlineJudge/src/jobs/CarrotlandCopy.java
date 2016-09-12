package jobs;

/**
 * Carrotland
==========
The rabbits are free at last, free from that horrible zombie science experiment. They need a happy, safe home, where they can recover.
You have a dream, a dream of carrots, lots of carrots, planted in neat rows and columns! But first, you need some land. And the only person who's selling land is Farmer Frida. Unfortunately, not only does she have only one plot of land, she also doesn't know how big it is - only that it is a triangle. However, she can tell you the location of the three vertices, which lie on the 2-D plane and have integer coordinates.
Of course, you want to plant as many carrots as you can. But you also want to follow these guidelines: The carrots may only be planted at points with integer coordinates on the 2-D plane. They must lie within the plot of land and not on the boundaries. For example, if the vertices were (-1,-1), (1,0) and (0,1), then you can plant only one carrot at (0,0).
Write a function answer(vertices), which, when given a list of three vertices, returns the maximum number of carrots you can plant.
The vertices list will contain exactly three elements, and each element will be a list of two integers representing the x and y coordinates of a vertex. All coordinates will have absolute value no greater than 1000000000. The three vertices will not be collinear.
Languages
=========
To provide a Python solution, edit solution.py
To provide a Java solution, edit solution.java
Test cases
==========
Inputs:
    (int) vertices = [[2, 3], [6, 9], [10, 160]]
Output:
    (int) 289
Inputs:
    (int) vertices = [[91207, 89566], [-88690, -83026], [67100, 47194]]
Output:
    (int) 1730960165
 * @author moqiguzhu
 *
 */
public class CarrotlandCopy {
	public static int answer(int[][] vertices) {
		long res = 0;
		// get number of points interior in rectangle
		int left = Math.min(vertices[0][0], Math.min(vertices[1][0], vertices[2][0]));
		
		int right = Math.max(vertices[0][0], Math.max(vertices[1][0], vertices[2][0]));
		
		int bottom = Math.min(vertices[0][1], Math.min(vertices[1][1], vertices[2][1]));
		
		int up = Math.max(vertices[0][1], Math.max(vertices[1][1], vertices[2][1]));

		res += numberPointsInRec(left, bottom, right, up);
		
		// 减去在三个小三角形边界上或者内部的点
		for(int i = 0; i < 3; i++) {
			int x1 = vertices[i%3][0];
			int y1 = vertices[i%3][1];
			
			int x2 = vertices[(i+1)%3][0];
			int y2 = vertices[(i+1)%3][1];
			
			// 轴平行
			if(x1 == x2 || y1 == y2) {
				continue;
			}
			
			// 矩形内部点的个数等于两个三角形内部点的个数之和加上对角线上点的个数
			long pointsOnLine = numberPointsOnLine(x1, y1, x2, y2);
			pointsOnLine -= 2;
			long pointsInRec = numberPointsInRec(x1, y1, x2, y2);
			assert((pointsInRec - pointsOnLine) % 2 == 0);
			long pointsInTri = (pointsInRec - pointsOnLine) / 2;
			
			res -= (pointsOnLine + pointsInTri);
		}
		
		// 特殊情形，矩形可能是由三个点中的两个决定的，也就是说，有一个节点在矩形内部
		// 这时还需要减去一个小矩形内部的点数
		for(int i = 0; i < 3; i++) {
			int x1 = vertices[i%3][0];
			int y1 = vertices[i%3][1];
			
			// 内部节点
			if(x1 != left && x1 != right && y1 != bottom && y1 != up) {
				// 这时，另外两个节点必然是矩形的两个对角点
				// 我们需要找到当前内部节点和矩形另外两个对角点钟距离较近的一个
				int point1_x = vertices[(i+1)%3][0];
				int point1_y = vertices[(i+2)%3][1];
				
				int point2_x = vertices[(i+2)%3][0];
				int point2_y = vertices[(i+1)%3][1];
				
				int point_x, point_y;
				double dist1 = squareDist(x1, y1, point1_x, point1_y);
				double dist2 = squareDist(x1, y1, point2_x, point2_y);
				
				if(dist1 > dist2) {
					point_x = point2_x; point_y = point2_y;
				} else {
					point_x = point1_x; point_y = point1_y;
				}
				
				// 边界上的点也要减去
				res -= (Math.abs(point_x-x1) * Math.abs(point_y - y1));
			}
		}
		
		return (int)res;
	}
	
	public static long numberPointsInRec(int x1, int y1, int x2, int y2) {
		return (Math.abs((long)x1-(long)x2)-1) * (Math.abs((long)y1 - (long)y2) - 1);
	}
	
	public static long numberPointsOnLine(int x1, int y1, int x2, int y2) {
		long x = Math.abs((long)x1 - (long)x2);
		long y = Math.abs((long)y1 - (long)y2);
		
		return gcd(x, y) + 1;
	}
	
	public static double squareDist(int x1, int y1, int x2, int y2) {
		double dist = 0;
		dist += (double)(y1-y2) * (double)(y1-y2);
		dist += (double)(x1-x2) * (double)(x1-x2);
		
		return dist;
	}
	
	public static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a%b);
	}
	
	public static void main(String[] args) {
		// 特殊情形
//		int[][] vertices = {{2, 3}, {6, 9}, {10, 160}};
//		System.out.println(answer(vertices));

//		int[][] vertices = {{91207, 89566}, {-88690, -83026}, {67100, 47194}};
//		System.out.println(answer(vertices));
		
//		int[][] vertices = {{0,4},{0,0},{4,1}};
//		System.out.println(answer(vertices));
		
//		int[][] vertices = {{2,4},{0,0},{4,1}};
//		System.out.println(answer(vertices));
		
		int[][] vertices = {{-1,-1},{1,0},{0,1}};
		System.out.println(answer(vertices));
	}
	
}
