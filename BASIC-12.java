import java.util.*;
public class Main
{
	static String[] bin = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", 
			"1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] str = new String[n];
		for(int i = 0; i < n; i++){
			str[i] = sc.next();
		}
		for(int i = 0; i < n; i++){
			String result = hexToBin(str[i]).toString();
			result = binToOct(result).toString();
			if(result.charAt(0) == '0')
				result = result.substring(1);
			System.out.println(result);
		}
		sc.close();
	}
	
	private static StringBuffer hexToBin(String str){
		StringBuffer result = new StringBuffer();
		for(int i = 0; i < str.length(); i++){
			switch(str.charAt(i)){
			case '0': result.append(bin[0]); break;
			case '1': result.append(bin[1]); break;
			case '2': result.append(bin[2]); break;
			case '3': result.append(bin[3]); break;
			case '4': result.append(bin[4]); break;
			case '5': result.append(bin[5]); break;
			case '6': result.append(bin[6]); break;
			case '7': result.append(bin[7]); break;
			case '8': result.append(bin[8]); break;
			case '9': result.append(bin[9]); break;
			case 'A': result.append(bin[10]); break;
			case 'B': result.append(bin[11]); break;
			case 'C': result.append(bin[12]); break;
			case 'D': result.append(bin[13]); break;
			case 'E': result.append(bin[14]); break;
			case 'F': result.append(bin[15]); break;
			}
		}
		return result;
	}
	
	private static StringBuffer binToOct(String str){
		StringBuffer result = new StringBuffer();
		if (str.length()%3 == 1)
			str = "00" + str;
		else if (str.length()%3 == 2)
			str = "0" + str;
		int start = 0;
		int end = 3;
		for(int i = 0; i < str.length()/3; i++){
			String temp = str.substring(start, end);
			start = start + 3;
			end = end + 3;
			switch(temp){
			case "000": result.append("0"); break;
			case "001": result.append("1"); break;
			case "010": result.append("2"); break;
			case "011": result.append("3"); break;
			case "100": result.append("4"); break;
			case "101": result.append("5"); break;
			case "110": result.append("6"); break;
			case "111": result.append("7"); break;
			}
		}
		return result;
	}
}