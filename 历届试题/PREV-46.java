import java.io.*;
//超时了……就超时的这份都是抄的网上的(
public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++){
			String temp = br.readLine();
			System.out.println(dfs(temp));
		}
	}
	
	private static int dfs(String s){
		if(s.contains("LOL"))		//当本局已含LOL时，上一步为必胜局
			return -1;
		if(!s.contains("*"))
			return 0;
		int ping = 0;
		char str[] = s.toCharArray();
		for(int i = 0; i < s.length(); i++){
			if(str[i] == '*'){
				str[i] = 'L';
				int tmp = dfs(new String(str));
				if(tmp == -1)
					return 1;
				else if(tmp == 0)
					ping = 1;
				
				str[i] = 'O';
				tmp = dfs(new String(str));
				if(tmp == -1)
					return 1;
				else if(tmp == 0)
					ping = 1;
				str[i] = '*';
			}
		}
		if(ping > 0) return 0;
		return -1;
	}
	
}