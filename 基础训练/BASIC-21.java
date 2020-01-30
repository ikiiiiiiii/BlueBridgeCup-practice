import java.util.*;
public class Main
{
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		System.out.print(strSn(n, n));
	}
	
	private static String strAn(int num, int n){
		String str;
		String fh;
		if(num % 2 == 0) fh = "+";
		else
			fh = "-";
		if(num == n)
			str = "sin(" + num + ")";
		else
			str = "sin(" + num + fh + strAn(num + 1, n) + ")";
		return str;
	}
	
	private static String strSn(int num, int n){
		String str;
		if(num == 1) str = strAn(1, 1) + "+" + n;
		else 
			str = "(" + strSn(num - 1, n) + ")" + strAn(1, num) + "+" + (n - num + 1);
		return str;
	}
}