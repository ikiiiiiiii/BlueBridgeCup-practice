import java.io.*;
/*
 * 之前尝试暴力解法只能过70%，原解法放在下面注释了。
 * 去查了下解法，蛮有意思的，把数组一行数据看成十进制数，求反即可解决问题，AC
 */
/*public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nm[] = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		int[][] data = new int[n][m];
		boolean[] check = new boolean[n];
		for(int i = 0; i < n; i++){
			String s[] = br.readLine().split(" ");
			for(int j = 0; j < m; j++)
				data[i][j] = Integer.parseInt(s[j]);
		}
		int count = 0;
		for(int i = 0; i < n; i++){
			if(check[i]) continue;
			int count1 = 1, count2 = 0;			//count1代表和第i行数据相同的数据(包括第i行本身)，count2代表与第i行相反的数据
			for(int k = i + 1; k < n; k++){
				if(check[k]) continue;
				int x =0, y = 0;
				for(int j = 0; j < m; j++){
					if(data[i][j] == data[k][j]) x++;
					else
						y++;
				}
				if(x == m || y == m) {
					check[k] = true;
					if(x == m) count1++;
					if(y == m) count2++;
				}
			}
			count += count1*count2;
		}
		System.out.print(count);
	}
}*/

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nm[] = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		int[][] data = new int[n][m];
		for(int i = 0; i < n; i++){
			String s[] = br.readLine().split(" ");
			for(int j = 0; j < m; j++)
				data[i][j] = Integer.parseInt(s[j]);
		}
		int count = 0;
		int max = (1 << m) - 1;		//把每个孩子的评价看成十进制数，以十进制数为索引，数量为值建立数组
		int[] num = new int[max + 1];
		for(int i = 0; i < n; i++){
			int temp = 0;
			for(int j = 0; j < m; j++)
				temp = (temp << 1) + data[i][j];
			num[temp]++;
		}
		for(int i = 0; i <= max; i++){
			if(num[i] != 0)
				count += num[i] * num[i^max];		//i^max即为数字i的取反
		}
		System.out.print(count/2); 		//去掉重复加上的值
	}
}