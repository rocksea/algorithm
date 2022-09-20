import java.util.*;

public class DFS {
    static int[][] graph = {{}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2}};
    static boolean visited[] = new boolean[graph.length];

    public static void main(String args[]){
        dfs(1);
    }

    public static void dfs(int n){
        System.out.println("visited : " +n);
        visited[n] = true;
        for(int i : graph[n]) {
            if(!visited[i]) {
                dfs(i);
            }
        }
    }
}
