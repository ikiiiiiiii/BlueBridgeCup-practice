import java.util.*;
public class Main
{
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.close();
		
		String[] change = {"ling", "yi", "er", "shan", "si", "wu", "liu", "qi", "ba", "jiu"};
		String[] wei = {"shi", "bai", "qian", "wang", "yi"};
		String[] result = new String[20];
		int i = 0;		//记录num的位数
		int j = 0;		//记录result的下标
		int []check = new int[2];		//检查是否已经添加位数
		while(num > 0){
			int last = num % 10;		//分离num的末位
			num /= 10;
			
			if(last != 0){
				if(i > 0){
					if(i >= 4 && check[i/4 - 1] == 0){		//如果数字大于四位并未添加位数
						check[i/4 - 1] = 1;
						result[j++] = wei[i/4 + 2];
					}
					if(i % 4 != 0)
						result[j++] = wei[i % 4 - 1];
				}
				result[j++] = change[last];
			}
			else if(j > 0 && result[j - 1] != change[0])		//如果中部的零不连续
				result[j++] = change[0];		//其余情况跳过
			i++;
		}
		if(j > 1){
			if(!(result[j - 1] == "yi" && result[j - 2] == "shi"))
				System.out.print(result[j - 1] + " ");
			for(i = j - 2; i >=0; i--)
				System.out.print(result[i] + " ");
		}
		else
			System.out.print(result[0]);
	}
}
