import java.io.*;
//需要一个数学定理的支持，即a b两个互质的数不能组成的数的最大值为a*b - a - b
public class Main{
	static boolean dp[] = new boolean[10000];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int num[] = new int[n];
		for(int i = 0; i < n; i++)
			num[i] = Integer.parseInt(br.readLine());
		if(n == 1 && num[0] == 1){
			System.out.print(0);
			System.exit(0);
		}
		int flag = num[0];
		for(int i = 1; i < n; i++){
			flag = gcd(flag, num[i]);
		}
		if(flag != 1){
			System.out.print("INF");
			System.exit(0);
		}
		dp[0] = true;
		for(int i = 0; i < n; i++){
			for(int k = 0; k + num[i] < 10000; k++){
				if(dp[k])
					dp[k + num[i]] = true;
			}
		}
		int count = 0;
		for(int i = 1; i < 10000; i++){
			if(!dp[i]) count++;
		}
		System.out.print(count);
	}
	
	private static int gcd(int a, int b){
		return b == 0 ? a : gcd(b, a % b);
	}
	
}