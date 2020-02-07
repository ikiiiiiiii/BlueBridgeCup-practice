import java.io.*;
/*
 * 还是参考的网上的算法，先dfs然后添加判断条件优化
 * 不难想，但是感觉写起来很繁琐
 */
public class Main{
	static class People {
		int pos;		//所在位置的距离
		boolean lifted; 		//正在被举
		boolean lifting;		//正在举别人
		int liftp;		//举着的人是谁
		int maxMove; 		//最大移动距离
		int maxThrow;		//最大抛出距离
		boolean hasMoved;		//是否移动过
		boolean hasLift;			//是否举/抛过别人
	}
	static People[] p;
	static boolean Pos[] = new boolean[50];		//标记当前位置上是否有人
	static boolean visit[] = new boolean[9];		//3个人共9种操作
	static int max;			//最大距离
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		p = new People[3];
		for(int i = 0; i < 3; i++){
			String data[] = br.readLine().split(" ");
			p[i] = new People();
			p[i].pos = Integer.parseInt(data[0]);
			p[i].maxMove = Integer.parseInt(data[1]);
			p[i].maxThrow = Integer.parseInt(data[2]);
			p[i].liftp = -1;
			Pos[p[i].pos] = true;
		}
		for(int i = 0; i < 9; i++) {		//分别从哪一个人的哪个操作开始dfs
			if(i % 3 != 2) {			//第一步不能是抛
				visit[i] = true;
				dfs(i, 1);
				visit[i] = false;
			}
		}
		System.out.print(max);
	}
	
	static void dfs(int k, int step){		//k分为012 345 678 分别代表三个人的三种操作，step表示当前做了多少种操作
		int n = k / 3;		//执行操作的人
		int m = k % 3;		//执行的动作
		
		if(m == 0){		//该操作为移动
			if(p[n].lifted || p[n].lifting || p[n].hasMoved) return;
			int i = 1;
			if(step == 9) i = p[n].maxMove;
			else {
				for(int j = 1; j < p[n].pos; j++){
					if(Pos[j]) {
						int l = - (p[n].pos - j - 1);			//往回走
						i = l < i ? l : i;
					}
				}
			}
			i = i > -p[n].maxMove ? i : -p[n].maxMove;		//若此人操作时身后没人，就向前走；若身后有人，从身后有人位置的前一个位置开始枚举
			for(; i <= p[n].maxMove; i++) {
				if(Pos[p[n].pos + i - 1] || Pos[p[n].pos + i + 1] || i == p[n].maxMove){		//只需要考虑移动到别人旁边的情况和最后一次操作
					if(p[n].pos + i > 0 && !Pos[p[n].pos + i]) {
						if(i == 0) continue;
						Pos[p[n].pos] = false;
						p[n].pos += i;			//开始移动
						Pos[p[n].pos] = true;
						p[n].hasMoved = true;
						max = p[n].pos > max ? p[n].pos : max;
						
						for(int j = 0; j < 9; j++){		//进行下一步的操作
							if(!visit[j]) {
								visit[j] = true;
								dfs(j, step + 1);
								visit[j] = false;			//还原状态
							}
						}
						p[n].hasMoved = false;		//还原状态方便下一次循环
						Pos[p[n].pos] = false;
						p[n].pos -= i;
						Pos[p[n].pos] = true;
					}
				}
			}
		}
		else if(m == 1){		//该操作为举起
			if(p[n].lifted || p[n].lifting || p[n].hasLift) return;
			for(int i = 0; i < 3; i++) {
				if(Math.abs(p[i].pos - p[n].pos) == 1) {
					if(p[i].lifted) continue;
					p[n].hasLift = true;
					p[n].lifting = true;
					p[n].liftp = i;
					p[i].lifted = true;
					int temp = p[i].pos;
					Pos[p[i].pos] = false;
					p[i].pos = p[n].pos;
					if(p[i].lifting) p[p[i].liftp].pos = p[i].pos;
					
					for(int j = 0; j < 9; j++){
						if(!visit[j]) {
							visit[j] = true;
							dfs(j, step + 1);
							visit[j] = false;
						}
					}
					p[n].hasLift = false;
					p[n].lifting = false;
					p[n].liftp = -1;
					p[i].lifted = false;
					p[i].pos = temp;
					Pos[p[i].pos] = true;
					if(p[i].lifting) p[p[i].liftp].pos = p[i].pos;
				}
			}
		}
		else {		//扔
			if(p[n].lifted || !p[n].lifting) return;
			int i = 1;
			if(step == 9) i = p[n].maxThrow;		//向前扔
			else {
				for(int j = 1; j < p[n].pos; j++) {
					if(Pos[j]) {
						int l = - (p[n].pos - j - 1);
						i = l < i ? l : i;
					}
				}
				i = i > -p[n].maxThrow ? i : -p[n].maxThrow;
			}
			for( ; i <= p[n].maxThrow; i++){
				if(p[n].pos + i > 0 && !Pos[p[n].pos + i]) {
					if(Pos[p[n].pos + i - 1] || Pos[p[n].pos + i + 1] || i == p[n].maxThrow) {		//只需要考虑扔到别人旁边的情况和最后一次操作
						int j = p[n].liftp;
						p[j].pos += i;
						p[n].lifting = false;
						p[n].liftp = -1;
						p[j].lifted = false;
						Pos[p[j].pos] = true;
						max = p[j].pos > max ? p[j].pos : max;
						if(p[j].lifting) p[p[j].liftp].pos = p[j].pos;
						
						for(int x = 0; x < 9; x++) {
							if(!visit[x]) {
								visit[x] = true;
								dfs(x, step + 1);
								visit[x] = false;
							}
						}
						Pos[p[j].pos] = false;
						p[j].pos -= i;
						p[j].lifted = true;
						p[n].lifting = true;
						p[n].liftp = j;
						if(p[j].lifting) p[p[j].liftp].pos = p[j].pos;
					}
				}
			}
		}
	}
	
}