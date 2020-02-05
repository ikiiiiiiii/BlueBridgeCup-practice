import java.io.*;
public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] mn = br.readLine().split(" ");
		int m = Integer.parseInt(mn[0]);
		int n = Integer.parseInt(mn[1]);
		System.out.print(get(m, n));
	}
	
	private static int get(int m, int n){
		if(m < n) return 0;
		if(n == 0) return 1;
		else
			return get(m - 1, n) + get(m, n - 1);
	}
}