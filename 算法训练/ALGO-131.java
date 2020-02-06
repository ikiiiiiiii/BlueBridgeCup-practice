import java.util.*;
import java.io.*;
/*
 * 这道题挺简单的，写好排序方法即可
 * 但是oj上的测试数据非常之坑，不加上count<=200000的判断条件只能通过20%，查了好久才发现问题
 */
public class Main{
	static class Pro implements Comparable<Pro>{		//把每个科学家提出的问题根据坏对分别分组，再合并
		int t;		//此问题所在的坏对数
		long w;		//此问题需要消耗的资源
		int sci;		//给出此问题的科学家序号
		public Pro(int t, long a, int sci){
			this.t = t;
			this.w = a;
			this.sci = sci;
		}
		public int compareTo(Pro p){
			if(this.t == p.t){
				if(this.w > p.w || (this.w == p.w && this.sci > p.sci))
					return 1;
				else
					return -1;
			}
			return this.t > p.t ? 1 : -1;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		Pro p[] = new Pro[200001];			//建立问题类的数组
		int count = 0;
		int res = 0;
		for(int i = 1; i <= n; i++){
			String data[] = br.readLine().split(" ");
			long k = Long.parseLong(data[0]);
			long a = Long.parseLong(data[1]);
			long x = Long.parseLong(data[2]);
			long y = Long.parseLong(data[3]);
			long m = Long.parseLong(data[4]);
			int t = 0;		//记录该问题所在的坏对数
			for(int j = 1; j <= k; j++){
				if(count <= 200000) p[count++] = new Pro(t, a, i);
				long temp = (a * x + y) % m;
				if(temp < a && j != k) t++;
				a = temp;
			}
			res = Math.max(res, t);		//坏对数最大的为最优顺序
		}
		System.out.println(res);
		if(count <= 200000){		//重点！不加无法AC
			Arrays.sort(p, 0, count);
			for(int i = 0; i < count; i++)
				System.out.println(p[i].w + " " + p[i].sci);
		}
	}
}