import java.io.*;
/*
 * 尼姆堆问题，然而我没懂……大致意思是把每两个人之间的阶梯数量理解成一个堆，若异或结果为0，则先手必败，否则先手必胜
 * 有了这个问题的结论暴力破解即可
 */
public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data[] = br.readLine().split(" ");
		int[] num = new int[data.length];
		for(int i = 0; i < data.length; i++)
			num[i] = Integer.parseInt(data[i]);
		solve(num);
	}
	
	private static boolean check(int[] num){
		int sum = 0;
		for(int i = 0; i < num.length - 1; i+= 2)
			sum ^= (num[i + 1] - num[i] - 1);
		return (sum != 0);
	}
	
	private static void solve(int[] num){
		for(int i = 0; i < num.length - 1; i++){
			for(int k = num[i] + 1; k < num[i + 1]; k++){
				int old = num[i];
				num[i] = k;
				if(!check(num)){
					System.out.print(old + " " + k);
					return;
				}
				num[i] = old;
			}
		}
	}
	
}