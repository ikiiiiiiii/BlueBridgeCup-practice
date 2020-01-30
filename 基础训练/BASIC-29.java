import java.util.*;
public class Main
{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String str1 = sc.next();
		String str2 = sc.next();
		sc.close();
		
		int a[] = new int[100];
		int b[] = new int[100];
		int c[] = new int[101];
		int temp;
		int jinwei = 0;		//进位
		for(int i = 0; i < str1.length(); i++)		//倒序存储
			a[i] = (int)(str1.charAt(str1.length() - i - 1) - '0');
		for(int i = 0; i < str2.length(); i++)
			b[i] = (int)(str2.charAt(str2.length() - i - 1) - '0');
		int len = str1.length() > str2.length() ? str1.length() : str2.length();
		for(int i = 0; i < len + 1; i++){
			temp = a[i] + b[i] + jinwei;
			jinwei = temp/10;
			c[i] = temp%10;
		}
		if(c[len] != 0)len += 1;
		for(int i = len - 1; i >= 0; i--)
			System.out.print(c[i]);
	}
}