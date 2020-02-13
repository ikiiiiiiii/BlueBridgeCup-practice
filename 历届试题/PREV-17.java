import java.util.*;
import java.io.*;
/*
 * 一个需要思考一下的博弈论问题
 * 若当前一方拿卡片且没有其他卡片可选，则当前是必胜态，那么之前一方拿牌的状态则是必败态
 * 要确定当前为必胜态，要确定之后的所以情况均必败
 */
public class Main{
	static int num[] = new int[101];
	static List<Integer> choice = new ArrayList<Integer>();
	static List<List<Integer>> li = new ArrayList<List<Integer>>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 101; i++)
			li.add(new ArrayList<Integer>());
		String data1[] = br.readLine().split(" ");
		String data2[] = br.readLine().split(" ");
		for(int i = 0; i < data1.length; i++)
			num[Integer.parseInt(data1[i])]++;
		for(int i = 0; i < data2.length; i++)
			choice.add(Integer.parseInt(data2[i]));
		for(int i = 1; i <= 100; i++){
			if(num[i] != 0){
				num[i]--;
				for(int j = 1; j <= 100; j++)
					if((num[j] != 0) && (i % j == 0 || j % i == 0))
						li.get(i).add(j);
				num[i]++;
			}
		}
		Collections.sort(choice);
		boolean flag = false;
		for(int i = 0; i < choice.size(); i++){
			num[choice.get(i)]--;
			if(dfs(choice.get(i)) == -1){
				System.out.print(choice.get(i));
				flag = true;
				break;
			}
			num[choice.get(i)]++;
		}
		if(!flag) System.out.print(-1);
	}
	
	private static int dfs(int x){
		List<Integer> temp = li.get(x);
		for(int i = temp.size() - 1; i >= 0; i--){
			if(num[temp.get(i)] != 0){
				num[temp.get(i)]--;
				int t = dfs(temp.get(i));
				num[temp.get(i)]++;
				if(t == -1)		//返回-1证明没有下一个约数或倍数，则下一个状态是必胜态
					return 1;		//当前状态是必败态
			}
		}
		return -1;		//取到当前x的人必胜
	}
	
}