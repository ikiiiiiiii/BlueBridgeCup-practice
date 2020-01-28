import java.util.*;
public class Main
{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		char str[] = new char[n];
		String s = sc.next();
		sc.close();
		
		for(int i = 0; i < n; i++)
			str[i] = s.charAt(i);
		int front = 0;
		int last = n - 1;
		int count = 0;		//计数
		boolean odd = false;		//默认输入的字符出现个数都是偶数
		while(front < last){
			for(int i = last; i >= front; i--){		//从数组的最后一位开始寻找与front指向的字符相同的字符
				if(i == front){		//如果没有找到，则front指向的字符是一个单独的字符
					if(n % 2 == 0 || odd){
						System.out.println("Impossible");		//如果字符串长度为偶数或之前已经出现过奇数个的字符，则不可能是回文
						System.exit(0);
					}
					else{		//否则front指向的字符应处于中间，此字符应最后进行移动，跳过并记录移动需要的次数
						count += (n / 2) - front;
						front++;
						odd = true;
						break;		//跳出以更新front
					}
				}
				if(str[i] == str[front]){		//找到则进行交换
					str = swap(str, i, last);
					count += last - i;
					front++;
					last--;
					break;		//跳出以更新front和last
				}
			}	//end for	
		}  //end while
		System.out.println(count);
	}
	
	private static char[] swap(char str[], int a, int b)	//将a处的字符交换到b处，a<b
	{
		char temp = str[a];
		for(int i = a; i < b; i++){
			str[i] = str[i + 1];
		}
		str[b] = temp;
		return str;
	}
}
