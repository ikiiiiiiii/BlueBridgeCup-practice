import java.io.*;
/*
 * 这道题让人窒息……分支条件也太多了，最后还是参考的网上的算法
 * 通过了95%，剩下那个数据懒得再去找咋回事儿了
 */
public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nw[] = br.readLine().split(" ");
		String data[] = br.readLine().split(" ");
		int n = Integer.parseInt(nw[0]);
		int w = Integer.parseInt(nw[1]);
		int[] N = new int[n];
		for(int i = 0; i < n; i++)
			N[i] = Integer.parseInt(data[i]);
		print(N, w);
	}
	
	private static int getMin(int[] N){		//返回数组N最小值下标
		int min = 0;
		for(int i = 0; i < N.length; i++)
			if(N[min] > N[i]) min = i;
		return min;
	}
	
	private static void run1(int[] N, int i){		//执行第i个进程中的一个线程，N[i]为执行语句的个数
		System.out.print(i + 1 + " ");
		N[i]--;
	}
	
	private static void run2(int[] N, int i, int n){			//顺序执行从i到n个进程中的每一个线程
		for( ; i <= n; i++){
			while(N[i] > 0) {
				System.out.print(i + 1 + " ");
				N[i]--;
			}
		}
	}
	
	private static void print(int[] N, int w){
		int max = 0;		//每个进程依次执行能达到的最大值
		for(int i = 0; i < N.length; i++){
			max += N[i];
			N[i] *= 2;			//使用N[i]表示执行语句的个数
		}
		if(w < 0) System.out.print("No");	 		//首先排除w小于0的情况
		else if(N.length == 1){		//N只有一个数时只存在一个进程
			if(w != max) System.out.print("No");
			else {
				System.out.println("Yes");
				run2(N, 0, 0);
			}
		}
		else if(w == 1){			//当w为1时，表明存在一个进程只循环一次，其他进程顺序执行即可
			int min = getMin(N);
			if(N[min] == 2){ 		//此时N是乘2后的数据，所以等于2
				System.out.println("Yes");
				run1(N, min);
				if(min == 0) run2(N, 1, N.length - 1);
				else {
					run2(N, 0, min - 1);
					run2(N, min + 1, N.length - 1);
				}
				run1(N, min);
			}
			else
				System.out.print("No");
		}
		/*
		 * temp表示第i个进程，注释中temp[i]表示y[i]
		 * 先执行count = (w-2)*2个线程，返回此时的进程数i，这时可得temp[i] = w -2
		 */
		else if(w > 1 && w <= max){
			System.out.println("Yes");
			int count = (w - 2)*2;
			if(count == 0){		//如果w=2
				int temp = 0;
				for(int i = 0; i < N.length - 1; i++){
					if(N[i] > 4) {		//说明至少可以进行两次完整的循环
						temp = i;
						break;
					}
				}
				run1(N, temp);     //先执行一次，此时temp[i] = 0
				if(temp == 0) run2(N, 1, N.length - 2);
				else {
					run2(N, 0, temp -1);
					run2(N, temp + 1, N.length - 2);
				}
				while(N[N.length - 1] > 2) run1(N, N.length - 1);
				run1(N, temp);		//y = temp[i] + 1 = 1
				run1(N, N.length - 1);		//temp[N.length - 1] = y = 1
				run2(N, temp, temp);		//运行完temp中剩余的语句
				run1(N, N.length - 1);		//运行最后一个语句，y = temp[N.length - 1] + 1 = 2
			}
			else {		//w != 2
				int i = -1;
				while(count > 0){	//从第0个进程执行到第i个进程，temp[i] = w - 3; y = w - 2
					i++;
					while(N[i] > 0){
						run1(N, i); run1(N, i);			//执行一整个循环，y自增1
						count -= 2;
						if(count == 0) break;
					}
				}
				run1(N, i);  		//temp[i] = w - 2
				run2(N, i + 1, N.length - 2);		//执行其他未执行的进程
				while(N[N.length - 1] > 2) run1(N, N.length - 1);
				run1(N, i);		//y = temp[i] + 1 = w - 1
				run1(N, N.length - 1); 		//temp[N.length - 1] = y = w - 1
				run2(N, i, i);		//执行完i进程中的剩余语句
				run1(N, N.length - 1);		//y = temp[N.length - 1] + 1 = w
			}
		}
		else if(w > max)
			System.out.print("No");
	}
	
}