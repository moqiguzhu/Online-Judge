package DFS;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    	if(numCourses <= 1) {
    		return true;
    	}
    	
    	ArrayList<List<Integer>> graph = new ArrayList<>();
    	for(int i = 0; i < numCourses; i++) {
    		ArrayList<Integer> tmp = new ArrayList<>();
    		graph.add(tmp);
    	}
    	for(int i = 0; i < prerequisites.length; i++) {
    		graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
    	}
    	
    	boolean[] visited = new boolean[numCourses];
    	for(int i = 0; i < graph.size(); i++) {
    		if(!dfs(graph, visited, i)) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    private boolean dfs(ArrayList<List<Integer>> graph, boolean[] visited, int course) {
    	if(visited[course]) {
    		return false;
    	} else {
    		visited[course] = true;
    	}
    	
    	for(int i = 0; i < graph.get(course).size(); i++) {
    		if(!dfs(graph, visited, graph.get(course).get(i))) {
    			return false;
    		}
    	}
    	visited[course] = false;
    	
    	return true;
    }
    
    
	public static void main(String[] args) {
		CourseSchedule cs = new CourseSchedule();
		int numCourses = 2;
		int[][] prerequisites = {{1,0},{0,1}};
		
		System.out.println(cs.canFinish(numCourses, prerequisites));
	}
}
