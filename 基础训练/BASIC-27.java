import java.util.*;
public class Main
{
	static int count=0;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int arr[] = new int[n];	//皇后所在的列
		int board[][] = new int[n][n];	//棋盘的具体摆放
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				board[i][j] = sc.nextInt();
			}
		}
		set(0, 0, arr, board);	//递归
		System.out.println(count);
		sc.close();
	}
	
	public static void set(int flag, int k, int arr[], int[][] board){	//flag=0表示落子的为白皇后，1表示落子的为黑皇后
		if(k == arr.length){
			if(flag == 0){
				arr = new int[arr.length];		//	重置皇后所在的列
				set(1, 0, arr, board);		//如果白皇后放置完毕，则放置黑皇后
			}
			else	
				count++;		//黑皇后放置完毕，计数
			return;
		}
		for(int i = 0; i < arr.length; i++){		//循环一行的每一列i，其中k既为皇后数量也为行数
			if(board[k][i] == 0) continue;
			int j;		//之前皇后所在的行数为j，列数为arr[j]
			for(j = 0; j < k; j++)
				if(arr[j] == i || Math.abs(arr[j] - i) == (k - j))
					break;
			if(j == k){
				arr[k] = i;
				board[k][i] = 0;
				set(flag, k+1, arr, board);
				board[k][i] = 1;		//还原棋盘
			}
		}
		return;
	}
}
