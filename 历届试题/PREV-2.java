import java.util.*;
public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		int len = 5 + n*4;
		char[][] str = new char[len][len];
		for(int i = 0; i < len; i++)
			for(int j = 0; j < len; j++)
				str[i][j] = '.';
		int up = 0;
		int left = 0;
		for(int num = n; num > 0; num--){
			for(int j = left + 2; j < len - left - 2; j++)	{  //输出第一行和倒数第一行
				str[up][j] = '$';
				str[len - up - 1][j] = '$';
			}
			str[up + 1][left + 2] = '$';
			str[up + 1][len - left - 3] = '$';
			str[len - up - 2][left + 2] = '$';
			str[len - up - 2][len - left - 3] = '$';		//输出第二行和倒数第二行
			for(int j = left; j <= left + 2; j++) {
				str[up + 2][j] = '$';
				str[up + 2][len - j - 1] = '$';
				str[len - up - 3][j] = '$';
				str[len - up - 3][len - j - 1] = '$';			//输出第三行和倒数第三行
			}
			for(int i = up + 3; i < len - up - 3; i++){
				str[i][left] = '$';
				str[i][len - left - 1] = '$';						//输出中间部分
			}
			up += 2; left += 2;
		}
		int mid = len / 2;			//最中央的图像
		for(int i = mid - 2; i <= mid + 2; i++){
			str[i][mid] = '$';
			str[mid][i] = '$';
		}
		//打印结果
		for(int i = 0; i < len; i++){
			for(int j = 0; j < len; j++)
				System.out.print(str[i][j]);
			System.out.println();
		}
	}
}