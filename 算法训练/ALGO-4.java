import java.util.*;
public class Main
{
	/*
	 * 这道题也没有做出来，参考了网上的代码，使用了dfs和动态规划，然而最后还是会报运行超时，只有50分TUT
	 * 定义一个状态矩阵dp，其中dp[i][0]代表不取该点，dp[i][1]代表取该点
	 * 每个点有两种情况，被选中和不被选中，如果该点被选中，则不能取相邻所有点；
	 * 不选中该点，则有可能选取邻接点或不选邻接点，该点权值和与相邻最大和累加
	 * 所以：
	 * dp[i][0] += max(dp[j][0],dp[j][1])，i 是 j 的邻接点
	 * dp[i][1] += dp[j][0] 
	 */
	static List<List<Integer>> li = new ArrayList<List<Integer>>();		//使用list来存储邻接表
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] dp = new int[n + 1][2];
		for(int i = 1; i <= n; i++ )		//方便理解，从1开始取值
			dp[i][1] = sc.nextInt();
		
		for(int i = 0; i <= n; i++){		//初始化储存每一个结点的list，方便之后取值第一个list不储存结点，共n + 1个list
			List<Integer> t = new ArrayList<Integer>();
			li.add(t);
		}
		for(int i = 1; i < n; i++){		//接收输入，一共n - 1行
			int a = sc.nextInt();
			int b = sc.nextInt();
			li.get(a).add(b);
			li.get(b).add(a);
		}
		sc.close();
		dfs(1, 0, dp);
		System.out.print(Math.max(dp[1][1], dp[1][0]));
	}
	
	private static void dfs(int child, int farther, int dp[][]){
		for(int i = 0; i < li.get(child).size(); i++){
			int temp = li.get(child).get(i);
			if(temp != farther){
				dfs(temp, child, dp);
				dp[child][0] += Math.max(dp[temp][0], dp[temp][1]);
				dp[child][1] += dp[temp][0];
			}
		}
	}
}