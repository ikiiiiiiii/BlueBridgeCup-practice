import java.util.*;
public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		System.out.print(change(n));
	}
	
	private static String change(int n){
		String str = "";
		int[] num = new int[15];
		int i = 0;
		while(n != 0){
			if(n%2 == 1) num[i] = 1;
			else
				num[i] = 0;
			n /= 2;
			i++;
		}
		int begin = 0;
		while(num[begin] == 0) begin++;
		String t = "+";
		for(int index = i - 1; index >= begin; index--){
			if(num[index] == 1){
				if(index == begin) t ="";
				if(index > 2) str += "2(" + change(index) + ")" + t;
				else if(index == 2) str += "2(2)" + t;
				else if(index == 1) str += "2" + t;
				else
					str += "2(0)";
			}
		}
		return str;
	}
}