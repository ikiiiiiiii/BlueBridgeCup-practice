import java.util.*;
import java.io.*;
//这道题oj上给的示例结果有错，输出结果应为178，题目上给的176，评测倒是正确的
class Cow implements Comparable<Cow>{
	int u;
	int v;
	int w;
	public Cow(int u, int v, int w){
		this.u = u;
		this.v = v;
		this.w = w;
	}
	public int compareTo(Cow c){		//重写排序方法
		return w > c.w ? 1 : -1;
	}
}

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] np = br.readLine().split(" ");
		int n = Integer.parseInt(np[0]);
		int p = Integer.parseInt(np[1]);
		
		int[] time = new int[n];
		int mintime = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++){
			time[i] = Integer.parseInt(br.readLine());
			if(mintime > time[i])
				mintime = time[i];			//记录下需要安抚奶牛最少的时间，该点可作为起点和终点
		}
		
		List<Cow> li = new ArrayList<Cow>();
		for(int i = 0; i < p; i++){
			String line[] = br.readLine().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			int w = Integer.parseInt(line[2]);
			li.add(new Cow(u, v, (time[u - 1] + time[v - 1] + w*2)));		//一条边的权值为两点权值与边权值的和，因为要返回原点，边权值需要乘2
		}
		int[] check = new int[n + 1];
		//求最小生成树，首先把每一条边按权值从小到大排序，在不形成闭环的情况下依次取边
		Collections.sort(li);
		int sumtime = 0;
		for(int i = 0; i < p; i++){
			int a = find(check, li.get(i).u);
			int b = find(check, li.get(i).v);
			if(a > b){
				check[a] = b;
				sumtime += li.get(i).w;
			}
			else if(a < b){
				check[b] = a;
				sumtime += li.get(i).w;
			}
		}
		sumtime += mintime;		//最后加上回到起点时安抚奶牛的时间
		System.out.println(sumtime);
	}
	
	private static int find(int[] check, int k){
		while(check[k] > 0)
			k = check[k];
		return k;
	}
}