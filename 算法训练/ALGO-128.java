import java.io.*;
/*
 * 放到oj里跑的时候只有85分，剩下三个错没找出来是哪里的问题
 * 这道题看得很晕，代码是抄的网上的，抄完也很蒙……
 * dp[i][0] 表示第 i 个数不与前者交换，dp[i][1]表示第i个数与前者交换
 * 如果当前数与前者相等，则采用 dp[i][0] = dp[i-1][0] + dp[i-1][1];
 * 如果当前数为A，前者为B ，则 ‘AB’ 不可能是‘BA’变化得到，且前一个数必定经过了交换，dp[i][0] = dp[i-1][1]; dp[i][1] = 0;
 * 如果当前数为B，前者为A ，则‘BA’可能是‘AB’变化得到，也可能原本就是’BA’，dp[i][0] = dp[i-1][0] + dp[i-1][1]; dp[i][1] = dp[i-2][0] + dp[i-2][1];
 * 一定可以找到一个 BA ，先不管当前BA的可能取值，设该‘B’ 的位置为index，
 * 取start = index+2，end = index - 1，初始化dp[start][1] = 0, dp[start][0]=1
 * 如果 BA 之前是 AA，则一定要变为 AAAB，如果BA之后是BB，则一定要变为 ABBB
 * 如果 BA 之前是 B 且 BA 之后 是 A，则 BA 可变可不变
 * 如果 BA 之前是 BA 或者 BA 之后 是 BA，则 start+=2 或 end -=2，即 BABA 可能由 ABBA 或者 BAAB得到，这时候还要考虑 s 为 BABA 与 BABABA 的情况。
 */
public class Main{
	static char[] s = new char[100];
	static int[][] dp = new int[101][2];		//使用数组储存可能的次数，行代表一个字母，列代表该字母是否产生变化（0或1）
	static int len;
	static int sum = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		len = str.length();
		for(int i = 0; i < len; i++)
			s[i] = str.charAt(i);
		int index = 0;
		for( ; index < len; index++)
			if(s[index] - 1 == s[index + 1])break;		//寻找第一个BA所在的位置作为起点
		choose((index + 2) % len, (index - 1 + len) % len);
		nochoose((index + 2) % len, (index - 1 + len) % len);
		System.out.print(sum);
	}
	
	private static void choose(int start, int end){
		dp[start][0] = 1;
		dp[start][1] = 0;
		while(start != end){
			start = (start + 1) % len;
			if(s[start] == s[(start - 1 + len)%len]) //当前所指字母与前一个相同，AA或BB
			{
				dp[start][0] = dp[(start - 1 + len)%len][0] + dp[(start - 1 + len)%len][1];
				dp[start][1] = 0;
			}
			else if(s[start] - 1== s[(start - 1 + len)%len]) //AB
			{
				dp[start][0] = dp[(start - 1 + len)%len][1];
				dp[start][1] = 0;
			}
			else		//BA
			{
				dp[start][0] = dp[(start - 1 + len)%len][0] + dp[(start - 1 + len)%len][1];
				dp[start][1] = dp[(start - 2 + len)%len][0] + dp[(start - 2 + len)%len][1];
				if(dp[start][1] == 0) dp[start][1] = 1;
			}
		}
		sum += dp[start][0] + dp[start][1];
	}
	
	static void nochoose(int start, int end){
		int temp = 2;
		if(s[start] == 'B'){
			if(s[(start + 1) % len] == 'A'){
				start = (start + 2) % len;
				temp += 2;
			}
			else
				return;
		}
		if(s[end] == 'A'){
			if(s[(end - 1 + len) % len] == 'B'){
				end = (end - 2 + len) % len;
				temp += 2;
			}
			else
				return;
		}
		if(temp >= len){
			sum = sum + 1;
			return;
		}
		dp = null;
		choose(start, end);
	}
	
}