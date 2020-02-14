import java.io.*;
public class Main{
	static long[] num = new long[2];
	static String op = "";
	static int hex = 10;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++){
			String data[] = br.readLine().split(" ");
			read(data);
		}
	}
	
	private static void read(String[] data){
		switch(data[0]){
		case "NUM" : 
			if(op.equals(""))
				num[0] = Long.valueOf(data[1], hex);
			else {
				num[1] = Long.valueOf(data[1], hex);
				num[0] = cal();
				op = "";
			}
			break;
		case "ADD":
			op = "ADD"; break;
		case "SUB":
			op = "SUB"; break;
		case "MUL":
			op = "MUL"; break;
		case "DIV":
			op = "DIV"; break;
		case "MOD":
			op = "MOD"; break;
		case "CHANGE":
			hex = Integer.parseInt(data[1]);
			break;
		case "EQUAL":
			System.out.println(Long.toString(num[0], hex).toUpperCase());
			break;
		case "CLEAR":
			num[0] = 0;
			num[1] = 0;
			op = "";
			break;
		}
	}
	
	private static long cal(){
		long res = 0;
		switch(op){
		case "ADD": 
			res = num[0] + num[1];
			break;
		case "SUB":
			res = num[0] - num[1];
			break;
		case "MUL":
			res = num[0] * num[1];
			break;
		case "DIV":
			res = num[0] / num[1];
			break;
		case "MOD":
			res = num[0] % num[1];
			break;
		}
		return res;
	}
	
}