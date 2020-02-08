import java.util.*;
import java.io.*;
public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data = br.readLine();
		for(int i = 0; i < data.length(); i++){		//处理负数的情况
			if(data.charAt(i) == '-') {
				if(i == 0) data = "0" + data;
				else if(data.charAt(i - 1) == '(')
					data = data.substring(0, i) + "0" + data.substring(i);
			}
		}
		char arr[] = data.toCharArray();
		Stack<Integer> num = new Stack<Integer>();
		Stack<Character> op = new Stack<Character>();
		int n = 0;			//保存数字，当数字大于10的情况
		boolean flag = false;
		for(int i = 0; i < arr.length; i++) {
			char temp = arr[i];
			if(Character.isDigit(temp)) {
				n = n * 10 + (int)(temp - '0');
				flag = true;
			}
			else {
				if(flag){
					num.push(n);
					n = 0;
					flag = false;
				}
				if(temp == '(')
					op.push(temp);
				else if(temp == ')'){
					while(op.peek() != '(')
						num.push(cal(num.pop(), num.pop(), op.pop()));
					op.pop();
				}
				else if(check(temp) > 0){
					if(op.isEmpty()) 
						op.push(temp);
					else {
						if(check(op.peek()) >= check(temp))
							num.push(cal(num.pop(), num.pop(), op.pop()));
						op.push(temp);
					}
				}
			}
		}
		if(flag) num.push(n);
		while(!op.isEmpty())
			num.push(cal(num.pop(), num.pop(), op.pop()));
		System.out.print(num.pop());
	}
	
	private static int cal(int x, int y, char op){
		int res = 0;
		switch(op) {
		case '+' : res = y + x; break;
		case '-' : res = y - x; break;
		case '*' : res = y * x; break;
		case '/' : res = y / x; break;
		}
		return res;
	}
	
	private static int check(char op){
		if(op == '+' || op == '-')
			return 1;
		else if(op == '*' || op == '/')
			return 2;
		else
			return 0;
	}
}