import java.util.*;
public class Main{
	static int num[] = new int[9];
	static int count = 0;
	static int n;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		dfs(0);
		System.out.print(count);
	}
	
	private static void dfs(int k){	//使用num存储9个数字的全排列，k为num存储的个数
		if(k == 9){
			check();
			return;
		}
		for(int i = 1; i <= 9; i++){
			if(sure(i) == 0){
				num[k] = i;
				dfs(k + 1);
				num[k] = 0;
			}
		}
	}
	
	private static void check(){		//查看该数组是否是符合的数组
		for(int i = 1; i <= 7; i++){		//将数组分为三段，i和j为第一段和第二段的长度
			for(int j = 1; j <= 8 - i; j++){
				if(j < 9 - i - j) continue;		//如果第三段的长度大于第二段j的长度，则分数不可能为整数，跳过
				int num1 = toNum(0, i);
				int num2 = toNum(i, j);
				int num3 = toNum(i + j, 9 - i - j);
				if((num2 % num3 == 0) && (num1 + num2/num3 == n))
					count++;
			}
		}
	}
	
	private static int toNum(int begin, int len){	//把数组的数字变为整数
		int temp = 0;
		for(int i = begin; i < begin + len; i++)
			temp = temp * 10 + num[i];
		return temp;
	}
	
	private static int sure(int k){		//确定该数在数组中没有重复
		for(int i = 0; i < 9; i++)
			if(num[i] == k) return 1;
		return 0;
	}
	
}