import java.io.*;
public class Main{
	static int A[] = new int[100001];
	static int temp[] = new int[100001];
	static int dp[] = new int[100001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nk[] = br.readLine().split(" ");
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);
		String data[] = br.readLine().split(" ");
		for(int i = 0; i < n; i++){
			A[Integer.parseInt(data[i])]++;
		}
		int res = 0;
		if(k == 0){
			for(int i = 0; i < A.length; i++)
				if(A[i] != 0) res++;
			System.out.print(res);
			System.exit(0);
		}
		for(int i = 0; i < k; i++){
			int index = 0;
			for(int j = i; j < A.length; j += k)
				temp[index++] = A[j];				//利用temp数组存储每一组相邻相差为k的数
			dp[0] = temp[0];
			for(int j = 1; j < index; j++){
				if(j == 1) dp[j] = Math.max(dp[0], temp[1]);
				else 
					dp[j] = Math.max(dp[j - 1], dp[j - 2] + temp[j]); 		//要么取本身与同自己不相连的值的和，要么取和自己相邻的值
			}
			res += dp[index - 1];
		}
		System.out.print(res);
	}
}