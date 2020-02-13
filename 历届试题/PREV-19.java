import java.util.*;
import java.io.*;
public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start = br.readLine();			//把九宫格存为字符串
		String end = br.readLine();
		HashMap<String, Integer> hash = new HashMap<String, Integer>(100000);		//保存转换过程中存储的字符串，值为到字符串所需的步数
		Queue<String> q = new LinkedList<String>();
		hash.put(start, 0);
		q.offer(start);
		int res = -1;
		while(res == -1){
			if(q.isEmpty()) break;
			String temp = q.poll();
			int index = 0;
			for( ; index < temp.length(); index++)
				if(temp.charAt(index) == '.') break;
			int[] go = {3, -3, 1, -1};			//分别表示向下、上、右、左走
			for(int i = 0; i < 4; i++){		//开始bfs
				int j = index + go[i];
				if(j >= 0 && j <= 8 && (j % 3 == index % 3 || j / 3 == index / 3)){		//检查现在和之前的位置所在的行或列，防止越界
					char t = temp.charAt(j);
					String str = temp.replace('.', '*');
					str = str.replace(t, '.');
					str = str.replace('*', t);
					if(str.equals(end))
						res = hash.get(temp) + 1;
					if(!hash.containsKey(str)){			//如果之前不存在当前状态，保存当前状态的字符串
						hash.put(str, hash.get(temp) + 1);
						q.offer(str);
					}
				}
			}
		}
		System.out.print(res);
	}
	
}