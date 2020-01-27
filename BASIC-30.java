import java.util.*;
public class Main
{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		int jinwei = 0; //进位
		int temp;
		int max = 5000;	 //结果的位数大概定为5000
		int num[] = new int[max];
		num[0] = 1;
		for(int i = 2; i <= n; i++){
			for(int j = 0; j < max; j++){
				temp = num[j] * i + jinwei;
				jinwei = temp/10;
				num[j] = temp%10;
			}
		}
		int target = 0;  //标记结果最高位，最高位不会为0
		for(int i = max - 1; i >= 0; i--){
			if(num[i] != 0){
				target = i;
				break;
			}
		}
		for(int i = target; i >= 0; i--){
			System.out.print(num[i]);
		}
	}
}