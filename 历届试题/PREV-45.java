import java.io.*;

class Pair implements Comparable<Pair>{
	int x, y;
	Pair(){}
	Pair(Pair a){
		x = a.x;
		y = a.y;
	}
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int compareTo(Pair a){
		return x == a.x ? y - a.y : x - a.x;
	}
}

class Triple{
	int x, y, h;
	Triple(){}
	Triple(int x, int y, int h){
		this.x = x;
		this.y = y;
		this.h = h;
	}
	Triple(Triple a){
		x = a.x;
		y = a.y;
		h = a.h;
	}
}

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String mn[] = br.readLine().split(" ");
		int m = Integer.parseInt(mn[0]);
		int n = Integer.parseInt(mn[1]);
		Pair a[] = new Pair[n + 1];			//储存图片
		Triple cr[] = new Triple[n + 1];			//储存图片位置，及右下角所在的x和y值以及被压缩后最高的高度
		cr[0] = new Triple();
		for(int i = 1; i <= n; i++){
			String data[] = br.readLine().split(" ");
			Triple tmp = new Triple(cr[i - 1]);
			if(tmp.x == m) tmp.x = tmp.h = 0;
			a[i] = new Pair(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
			cr[i] = new Triple();
			Pair b = change(a[i], m - tmp.x);
			cr[i].x = tmp.x + b.x;
			cr[i].h = Math.max(tmp.h, b.y);
			cr[i].y = cr[i].h - tmp.h + tmp.y;
		}
		Triple A[] = new Triple[m];
		Triple B[] = new Triple[m];
		for(int i = 0; i < m; i++){
			A[i] = new Triple();
			B[i] = new Triple();
		}
		int ans = cr[n].y;
		
		for(int i = n; i >= 1; i--){
			Triple pre = cr[i - 1];
			int ah;
			if(pre.x == m)
				ah = pre.y + B[0].y;
			else
				ah = pre.y - pre.h + B[pre.x].y - B[pre.x].h + Math.max(pre.h, B[pre.x].h);
			ans = Math.min(ans, ah);
			for(int j = 0; j < m; j++){
				Pair b = change(a[i], m - j);
				Triple tmp;
				if(j + b.x == m)
					tmp = new Triple(0, B[0].y, 0);
				else
					tmp = new Triple(B[j + b.x]);
				A[j].h = Math.max(b.y, tmp.h);
				A[j].y = A[j].h + tmp.y - tmp.h;
			}
			for(int j = 0; j < m; j++)
				B[j] = new Triple(A[j]);
		}
		System.out.print(ans);
	}
	
	private static Pair change(Pair a, int x){
		if(a.x <= x) return new Pair(a);
		else
			return new Pair(x, (a.y * x + a.x - 1) / a.x);
	}
	
}