import java.util.*;
import java.io.*;
/*
 * 这道题最恶心的地方在不能用邻接矩阵，最后一个测试点会超内存，只能用邻接表来做
 * 其他都很简单，就是求一颗树的直径，两遍dfs即可
 */
public class Main{
	static boolean[] visit;
	static int max = 0; 
	static int last;
	public static class Edge{
		int from;
		int to;
		int weight;
		public Edge(int a, int b, int w){
			this.from = a;
			this.to = b;
			this.weight = w;
		}
	}
	static List<List<Edge>> li = new ArrayList<List<Edge>>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		visit = new boolean[n + 1];
		for(int i = 0; i < n + 1; i++)
			li.add(new ArrayList<Edge>());
		for(int i = 0; i < n - 1; i++){
			String data[] = br.readLine().split(" ");
			int v = Integer.parseInt(data[0]);
			int u = Integer.parseInt(data[1]);
			int w = Integer.parseInt(data[2]);
			li.get(v).add(new Edge(v, u, w));
			li.get(u).add(new Edge(u, v, w));
		}
		visit[1] = true;
		dfs(1, 0);		//首先从任意一点开始dfs一遍
		visit[1] = false;
		visit[last] = true;
		dfs(last, 0);		//再从找出的最长路径末端再dfs一遍
		int res = 11*max + max*(max - 1)/2;
		System.out.print(res);
	}
	
	private static void dfs(int dot, int dis){
		if(dis > max) {
			max = dis;
			last = dot;
		}
		for(int i = 0; i < li.get(dot).size(); i++){
			int next = li.get(dot).get(i).to;
			if(!visit[next]){
				visit[next] = true;
				dfs(next, dis + li.get(dot).get(i).weight);
				visit[next] = false;
			}
		}
	}
}