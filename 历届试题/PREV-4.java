import java.io.*;
public class Main{
	static int num[][];
	static boolean check[][];
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	static int M, N;
	static int sum = 0;
	static int min = 100;
	static boolean flag = false;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String mn[] = br.readLine().split(" ");
		M = Integer.parseInt(mn[0]);
		N = Integer.parseInt(mn[1]);
		num = new int[N][M];
		check = new boolean[N][M];
		for(int i = 0; i < N; i++){
			String data[] = br.readLine().split(" ");
			for(int j = 0; j < M; j++){
				num[i][j] = Integer.parseInt(data[j]);
				sum += num[i][j];
			}
		}
		if(sum%2 == 1)
			System.out.print(0);
		else
		{
			check[0][0] = true;
			dfs(0, 0, num[0][0]);
			if(!flag) System.out.print(0);
			else
				System.out.print(min);
		}
	}
	
	private static void dfs(int x, int y, int n){
		if(n == sum/2){
			flag = true;
			int count = 0;
			for(int i = 0; i < N; i++){
				for(int j = 0; j < M; j++)
					if(check[i][j]) count++;
			}
			if(min > count) min = count;
			return;
		}
		for(int i = 0; i < 4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!judge(nx, ny, n)) continue;
			check[nx][ny] = true;
			dfs(nx, ny, n + num[nx][ny]);
			check[nx][ny] = false;
		}
	}
	
	private static boolean judge(int x, int y, int n){
		if(x < 0 || x >= N || y < 0 || y>= M)
			return false;
		if(check[x][y]) return false;
		if(n + num[x][y] > sum / 2) return false;
		return true;
	}
	
}