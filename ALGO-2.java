import java.util.*;
public class Main
{
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
		long n = sc.nextInt();
		sc.close();
		
		/*
		 *当n为奇数时，n*(n - 1)*(n - 2)是n以内三数的最大公倍数，因为此三数不能同时被2/3/5等质数整除
		 *当n为偶数时，假设n*(n - 1)*(n - 3)是最终结果，那么n不能被3整除
		 *						否则结果应为(n - 1)*(n - 2)*(n - 3)
		 */
		long r;
		if(n % 2 != 0)
			r = n*(n - 1)*(n - 2);
		else {
			if(n % 3 == 0)
				r = (n - 1)*(n - 2)*(n - 3);
			else
				r = n*(n - 1)*(n - 3);
		}
		System.out.print(r);
	}
}
