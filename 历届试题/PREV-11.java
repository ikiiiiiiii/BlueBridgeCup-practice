import java.io.*;
public class Main{
	static int root;
	public static class Node{
		int value;
		int left;
		int right;
		int father;
		public Node(){
			this.value = 0;
			this.left = 0;
			this.right = 0;
			this.father = 0;
		}
	}
	static Node[] tree = new Node[10001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data[] = br.readLine().split(" ");
		root = Integer.parseInt(data[0]);
		for(int i = 0; i < tree.length; i++)
			tree[i] = new Node();
		for(int i = 0; i < data.length; i++){
			int a = Integer.parseInt(data[i]);
			tree[a].value = a;
		}
		for(int i = 1; i < data.length; i++){
			int temp = root;
			int a = Integer.parseInt(data[i]);
			while(true){
				if(a > temp){
					if(tree[temp].right == 0){
						tree[temp].right = a;
						break;
					}
					else
						temp = tree[temp].right;
				}
				else{
					if(tree[temp].left == 0){
						tree[temp].left = a;
						break;
					}
					else
						temp = tree[temp].left;
				}
			}
		}
		String s = "";
		String s1 = "";
		dfs(root, s, 0, s1);
	}
	
	private static void dfs(int start, String s, int n, String s1){
		if(tree[start].value == root)
			s += tree[start].value;
		else{
			s += "-|-" + tree[start].value;
		}
		if(tree[start].right > 0){
			s1 += "1";
			dfs(tree[start].right, s, n + 1, s1);
			s1 = s1.substring(0, s1.length() - 1);
		}
		int len = s.length();
		int cot = 0;
		for(int i = 0; i < len; i++){
			if(s.charAt(i) == '|'){
				if(s1.length() <= cot + 1 || s1.charAt(cot) != s1.charAt(cot + 1))
					System.out.print("|");
				else
					System.out.print(".");
				cot++;
			}
			else if(cot < n)
				System.out.print(".");
			else
				System.out.print(s.charAt(i));
		}
		if(tree[start].left > 0 || tree[start].right > 0)
			System.out.print("-|");
		System.out.println();
		if(tree[start].left > 0){
			s1 += "0";
			dfs(tree[start].left, s, n + 1, s1);
			s1 = s1.substring(0, s1.length() - 1);
		}
	}

}