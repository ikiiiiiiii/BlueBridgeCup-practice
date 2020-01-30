/* 
*之前尝试用遍历写了一遍，果然超时，看了看网上的代码都是用动态规划写的，我果然还是太菜了
*代码不想删先放这里
import java.util.*;
public class Main
{
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int L = sc.nextInt();
		sc.close();
		
		if(K <= 2){
			System.out.print(0);
			System.exit(0);
		}
		int[] num = new int[L];
		num[L - 1] = 1;
		int count = 0;
		while(true){
			num[0]++;
			int wei = 0;
			while(wei < L && num[wei] == K){
				if(num[L - 1] == K) break;
				num[wei + 1]++;
				num[wei] = 0;
				wei++;
			}
			if(num[L - 1] == K) break;
			int flag = 0;
			for(int i = 1; i < L; i++){
				if(Math.abs(num[i - 1] - num[i]) == 1){
					flag = 1;
					break;
				}
			}
			if(flag == 0) count++;
		}
		System.out.print(count);
	}
} */

import java.util.*;
public class Main
{
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int L = sc.nextInt();
		sc.close();
		
		int[][] arr = new int[L][K]; 		//定义二维数组，行代表数的每一位，列代表每一位可能取的数字，值为取该数字的次数
		for(int i = 1; i < K; i++)
			arr[0][i] = 1;		//初始化第一行，排除数字首位为0的情况，其他均有可能，取值为1
		for(int i = 1; i < L; i++){
			for(int j = 0; j < K; j++){
				for(int a = 0; a < K; a++){		//筛选掉相邻的列
					if((j != a - 1) && (j != a + 1))
						arr[i][j] = (arr[i][j] + arr[i - 1][a]) % 1000000007;
				}
			}
		}
		
		int res = 0;
		for(int i = 0; i < K; i++)			//计算出最后一行的和，即是求得的值
			res = (res + arr[L - 1][i]) % 1000000007;
		System.out.print(res);
	}
}