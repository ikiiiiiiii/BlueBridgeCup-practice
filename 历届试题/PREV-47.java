import java.util.*;
import java.io.*;
//我对这道题有些迷惑……主要使用二分法
class S implements Comparable<S>{
	int l;
	int r;
	public S(int l, int r){
		this.l = l;
		this.r = r;
	}
	public int compareTo(S o){
		if(this.r != o.r)
			return (int)(this.r - o.r);
		else
			return (int)(this.l - o.l);
	}
}
public class Main{
	static int N = 20000;
	static S[] s;
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		s = new S[n];
		for(int i = 0; i < n; i++){
			String data[] = br.readLine().split(" ");
			int a = Integer.parseInt(data[0]);
			int b = Integer.parseInt(data[1]);
			s[i] = new S(2 * a, 2 * b);		//乘2，方便处理
		}
		Arrays.sort(s);
		int l = 0, r = N;
		while(l <= r){
			int mid = (l + r) / 2;
			if(check(mid))
				r = mid - 1;
			else
				l = mid + 1;
		}
		if(l % 2 == 0)
			System.out.print(l / 2);
		else
			System.out.printf("%.1f", (double)l / 2);
	}
	
	private static boolean check(int x){
		int now = 0;
		int[] visit = new int[n];
		while(true){
			boolean flag = false;
			int l = 0, r = n;
			while(l <= r){
				int mid = (l + r) / 2;
				if(s[mid].r + x >= now)
					r = mid - 1;
				else
					l = mid + 1;
			}
			for(int i = l; i < n; i++){
				if(s[i].l <= x + now && visit[i] == 0){
					flag = true;
					visit[i] = 1;
					if(now <= s[i].l + x) now += s[i].r - s[i].l;
					else
						now = s[i].r + x;
					break;
				}
			}
			if(now >= 20000) return true;
			if(!flag) return false;
		}
	}
	
}