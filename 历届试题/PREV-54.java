import java.io.*;
public class Main{
	static int[] num;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String mn[] = br.readLine().split(" ");
		int m = Integer.parseInt(mn[0]);
		int n = Integer.parseInt(mn[1]);
		num = new int[m * n + 1];
		for(int i = 1; i <= m * n; i++)
			num[i] = i;
		int line = Integer.parseInt(br.readLine());
		for(int i = 0; i < line; i++){
			String data[] = br.readLine().split(" ");
			int x = Integer.parseInt(data[0]);
			int y = Integer.parseInt(data[1]);
			int x1 = find(x);
			int y1 = find(y);
			if(x1 != y1)
				num[y1] = x1;
		}
		int root[] = new int[m * n + 1];
		for(int i = 1; i <= m * n; i++)
			root[find(i)] = 1;
		int res = 0;
		for(int i = 1; i <= m * n; i++)
			res += root[i];
		System.out.print(res);
	}
	
	private static int find(int x){
		if(x != num[x])
			return num[x] = find(num[x]);
		return x;
	}
	
}