import java.util.*;
import java.io.*;
//很简单，但是不仔细看题很容易错，处理起来比较麻烦
public class Main{
	public static class Date implements Comparable<Date>{
		int y;
		int m;
		int d;
		public Date(int y, int m, int d){
			this.y = y;
			this.m = m;
			this.d = d;
		}
		public int compareTo(Date o){
			if(y > o.y) return 1;
			else if(y == o.y){
				if(m > o.m) return 1;
				else if(m == o.m)
					return d > o.d ? 1 : -1;
				return -1;
			}
			return -1;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data[] = br.readLine().split("/");
		Date n[] = new Date[3];
		int y[][] = {{0,31,28,31,30,31,30,31,31,30,31,30,31}, {0,31,29,31,30,31,30,31,31,30,31,30,31}};
		n[0] = new Date(year(Integer.parseInt(data[0])), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
		n[1] = new Date(year(Integer.parseInt(data[2])), Integer.parseInt(data[0]), Integer.parseInt(data[1]));
		n[2] = new Date(year(Integer.parseInt(data[2])), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
		Arrays.sort(n);
		for(int i = 0; i < 3; i++){
			if(n[i].m > 12 || n[i].m < 1|| n[i].d < 1) continue;
			int year = 0;
			if((n[i].y % 4 == 0 && n[i].y % 100 != 0) || (n[i].y % 400 == 0))
				year = 1;
			if(y[year][n[i].m] < n[i].d) continue;
			if((i >= 1) && (n[i].d == n[i - 1].d && n[i].m == n[i - 1].m && n[i].y == n[i - 1].y)) continue;
			print(n[i]);
		}
	}
	
	private static int year(int n){
		if(n >= 60)
			return (1900 + n);
		else
			return (2000 + n);
	}
	
	private static void print(Date s){
		System.out.print(s.y + "-");
		if(s.m < 10)
			System.out.print("0" + s.m + "-");
		else
			System.out.print(s.m + "-");
		if(s.d < 10)
			System.out.println("0" + s.d);
		else
			System.out.println(s.d);
	}
	
}