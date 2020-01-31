import java.util.*;
import java.io.*;
public class Main
{
	static int leng[];		//1号点到其他点的距离组成数组
	static class node
	{
		int x;
		int length;		//1号点到该点的距离
		public node(int x, int length){
			this.x = x;
			this.length = length;
		}
	}
	
	public static void main(String[] args) throws IOException{
		//为了提高读入的效率和时间这里采用BufferedReader方法读取数据
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		int n = (int)in.nval;
		in.nextToken();
		int m = (int)in.nval;
		List<node> list[] = new ArrayList[n];
		for(int i = 0; i < n; i++)
			list[i] = new ArrayList<node>();		//list数组存储每个点到其他点的距离，这些距离组成一个列表list[i]
		leng = new int[n];
		for(int i = 1; i < n; i++)
			leng[i] = Integer.MAX_VALUE;
		boolean check[] = new boolean[n];		//判断该点是否在队列中
		for(int i = 0; i < m; i++){
			in.nextToken(); int u = (int)in.nval;
			in.nextToken(); int v = (int)in.nval;
			in.nextToken(); int l = (int)in.nval;
			list[u - 1].add(new node(v - 1, l));		//有向图，仅记录一边
		}
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(0);
		/*
		 * 首先添加第一点到队列中，
		 * 通过list[i]操作可以把与第一点连接的其他点添加到队列中并记录其相应的值，并标记此点已经在队列中防止重复
		 * 该点到第一点的距离与该点到邻居点的距离与邻居到第一点距离之和进行对比更新
		 */
		while(!q.isEmpty()){
			int x = q.poll();
			check[x] = false;
			for(int i = 0; i < list[x].size(); i++){
				int length = list[x].get(i).length;		//点x到邻居点的值
				int index = list[x].get(i).x;		//邻居点的编号
				if(leng[index] > (length + leng[x])){
					leng[index] = length + leng[x];
					if(!check[index]){
						q.add(index);
						check[index] = true;
					}
				}
			}
		}
		for(int i = 1; i < n; i++) out.println(leng[i]);
		out.flush();
		
	}
}