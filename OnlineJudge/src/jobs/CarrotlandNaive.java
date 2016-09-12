package jobs;

public class CarrotlandNaive {
	private static double e = 1e-10;
    public static int answer(int[][] vertices) { 
    	int res = 0;
    	int max_x = Integer.MIN_VALUE; int max_y = Integer.MIN_VALUE;
    	int min_x = Integer.MAX_VALUE; int min_y = Integer.MAX_VALUE;
    	
    	for(int i = 0; i < vertices.length; i++) {
    		max_x = Math.max(max_x, vertices[i][0]);
    		max_y = Math.max(max_y, vertices[i][1]);
    		min_x = Math.min(min_x, vertices[i][0]);
    		min_y = Math.min(min_y, vertices[i][1]);
    	}
    	
    	if(max_x - min_x > max_y - min_y) {
    		for(int i = 0; i < vertices.length; i++) {
    			int tmp = vertices[i][0];
    			vertices[i][0] = vertices[i][1];
    			vertices[i][1] = tmp;
    		}
    		int tmp = min_x;
    		min_x = min_y;
    		min_y = tmp;
    		
    		tmp = max_x;
    		max_x = max_y;
    		max_y = tmp;
    	}
    	
    	Double[] ys = new Double[3];
    	for(int k = min_x+1; k < max_x; k++) {
    		int index = 0;

    		ys[index++] = help(vertices,0,1,k);
    		ys[index++] = help(vertices,0,2,k);
    		ys[index++] = help(vertices,1,2,k);
    		
    		double y_min, y_max;
    		y_min = min(ys);
    		y_max = max(ys);
    		
    		double up_y, down_y;
    		// 精度问题
    		if(Math.abs(Math.floor(y_max) - y_max) < e) {
    			up_y = Math.floor(y_max) - 1;
    		} else {
    			up_y = Math.floor(y_max);
    		}
    		
    		if(Math.abs(Math.ceil(y_min) - y_min) < e) { 
    			down_y = Math.ceil(y_min) + 1;
    		} else {
    			down_y = Math.ceil(y_min);
    		}
    		
    		res += (up_y - down_y + 1);
    	}
    	
    	return res;
    }
    
    public static double min(Double[] ys) {
    	double cur;
    	if(ys[0] == null) {
    		cur = ys[1];
    		return Math.min(cur, ys[2]);
    	} else {
    		cur = ys[0];
    		for(int i = 1; i < 3; i++) {
    			if(ys[i] != null && ys[i] < cur) {
    				cur = ys[i];
    			}
    		}
    		return cur;
    	}
    }
    
    public static double max(Double[] ys) {
    	double cur;
    	if(ys[0] == null) {
    		cur = ys[1];
    		return Math.max(cur, ys[2]);
    	} else {
    		cur = ys[0];
    		for(int i = 1; i < 3; i++) {
    			if(ys[i] != null && ys[i] > cur) {
    				cur = ys[i];
    			}
    		}
    		return cur;
    	}
    }
    
    public static boolean beyond(double y, int y1, int y2) {
    	return y > Math.max(y1, y2) || y < Math.min(y1, y2);
    }
    
    public static Double help(int[][] vertices, int pos1, int pos2, int k) {
    	if(beyond(k, vertices[pos1][0], vertices[pos2][0])) {
    		return null;
    	}
    	return cross(vertices[pos1][0], vertices[pos1][1],
    			vertices[pos2][0], vertices[pos2][1], k);
    }
    
    // (x1,y1) (x2,y2) 与 x = k的交点 对应的y值
    public static double cross(int x1,int y1, int x2, int y2, int k) {
    	double tmp = (double)(y2-y1) * (double)(k-x1);
    	tmp = tmp / (double)(x2 - x1);
    	return tmp + y1;
    }
    
    public static void main(String[] args) {
    	int[][] vertices1 = {{2,3}, {6,9}, {10,160}};
		int[][] vertices = {{91207,89566}, {-88690,-83026}, {67100,47194}};
		System.out.println(answer(vertices));
	}
}
