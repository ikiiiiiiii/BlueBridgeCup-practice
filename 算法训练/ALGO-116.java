import java.io.*;
public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);
		String[] data = br.readLine().split(" ");
		long[] sum = new long[n + 1];
		long[][] dp = new long[n + 1][k + 1];
		for(int i = 1; i <= n; i++){
			sum[i] = sum[i - 1] + (long)Integer.parseInt(data[i - 1]);
			dp[i][0] = sum[i];
		}
		for(int i = 2; i <= n; i++){		//i代表计算i个元素的算术最大值
			for(int j = 1; j <= Math.min(i - 1, k); j++)		//j代表共有j个乘号，j应是i - 1和乘号数k的最小值
				for(int x = 2; x <= i; x++)		//x表示最后一个乘号的位置
					dp[i][j] = Math.max(dp[i][j], dp[x - 1][j - 1] * (sum[i] - sum[x - 1]));
		}
		System.out.print(dp[n][k]);
	}
}