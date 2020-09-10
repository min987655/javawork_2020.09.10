package array03;

import java.util.Random;
import java.util.Scanner;

public class CharTest {		
	public static void main(String[] args) {
		System.out.println("수를 결정하였습니다. 맞추어 보세요.");
		System.out.println("0-99");
		
		Random r = new Random();
		int k = r.nextInt(100);
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			int num = sc.nextInt();
			
			if(k<num) {
				System.out.println("더 낮게");
			} else if(k>num) {
				System.out.println("더 높게");
			} else {
				System.out.println("맞았습니다.");
				System.out.println("다시하시겠습니까(y/n)>>");
				String check = sc.next();
				if (check.equals("n")) {
					break;
				}
			}
			  
		}
	
	}
		
	}


