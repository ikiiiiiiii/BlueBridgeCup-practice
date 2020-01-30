import java.util.*;
public class Main
{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 2; i <= b; i++){
			int k = 0;
			for(int j = 2; j <= Math.sqrt(i); j++)
			{
				if(i % j == 0) k++;
			}
			if(k < 2) list.add(i);
			if(i >= a){
				int m = i;
				System.out.print(i + "=");
				while(m != 1){
					for(int j = 0; j < list.size(); j++){
						if(m % list.get(j) == 0){
							m = m/list.get(j);
							if(m == 1){
								System.out.println(list.get(j));
								break;
							}
							else{
								System.out.print(list.get(j) + "*");
								break;
							}
						}
					}
				}
			}
		}
		
	}
}