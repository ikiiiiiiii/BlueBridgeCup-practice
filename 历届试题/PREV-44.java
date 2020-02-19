import java.util.*;
import java.io.*;
//这道题本来是用list储存的状态，放oj上超时，后来改成map就AC了……看来map明显查找速度要快得多
public class Main{
	public static class Node{
		String s;
		int step;
		public Node(String s, int step){
			this.s = s;
			this.step = step;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		Queue<Node> q = new LinkedList<Node>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		q.offer(new Node(s1, 0));
		map.put(s1, 1);
		//bfs
		while(!q.isEmpty()){
			Node temp = q.poll();
			char str[] = temp.s.toCharArray();
			for(int i = 0; i < str.length; i++){
				if(str[i] == '*')
					for(int j = Math.max(0, i - 3); j <= Math.min(str.length - 1, i + 3); j++){
						str[i] = str[j];
						str[j] = '*';
						String s = new String(str);
						if(s.equals(s2)){
							System.out.print(temp.step + 1);
							System.exit(0);
						}
						if(!map.containsKey(s)){
							map.put(s, 1);
							q.offer(new Node(s, temp.step + 1));
						}
						str[j] = str[i];		//回溯
						str[i] = '*';
					}
			}
			
		}
		
	}
}