import java.io.*;
public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nk[] = br.readLine().split(" ");
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);
		int num[] = new int[n + 1];
		int sum[] = new int[n + 1];			//前缀和取模相等的一对区间是k倍区间
		int count[] = new int[k];			//计数
		long res = 0;				//注意是否会溢出
		for(int i = 1; i <= n; i++){
			num[i] = Integer.parseInt(br.readLine());
			sum[i] = (sum[i - 1] + num[i]) % k;
			res += count[sum[i]];
			count[sum[i]]++;
		}
		System.out.print((long)(res + count[0]));	 		//加上前缀和的前端为0的情况
	}
}