package SEA;

import java.io.*;
import java.util.*;

public class SEA_6808_규영이와_인영이의_카드게임 {
	
	static int N = 18;
	static int WIN;
	static int LOSE;
	static int[] myCards;
	static boolean[] isSelected;
	static int[] yourCards;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1 ; tc <= T; tc++) {
			WIN = 0;
			LOSE = 0;
			myCards = new int[N/2];
			yourCards = new int[N/2];
			isSelected = new boolean[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N/2; i++) {
				myCards[i] =  Integer.parseInt(st.nextToken());
				isSelected[myCards[i]] = true; // 규영이가 선택한 카드는 방문 표시해준다.
			}
			solve(0, 0, 0);
			bw.write("#" + tc + " " + WIN + " " + LOSE + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	// count번째 숫자를 뽑는다.
	private static void solve(int count, int myScore, int yourScore) {
		if (count == 9) {
			if (myScore > yourScore) {
				WIN++;
			} else if (myScore < yourScore) {
				LOSE++;
			}
			return;
		}
		
		for (int number = 1; number < N + 1; number++) {
			
			if (isSelected[number]) { continue; } // 이미 선택된 카드라면 skip
			
			yourCards[count] = number; // 인영이가 뽑은 count번째 카드는 i이다.
			isSelected[number] = true; // i번 카드 뽑았다는 표시를 한다.
			int bonus = myCards[count] + yourCards[count];
			if (myCards[count] > yourCards[count]) { // 내가 이기는 경우
				solve(count + 1, myScore +bonus, yourScore);
			} else if (myCards[count] < yourCards[count]) { // 내가 지는 경우
				solve(count + 1, myScore, yourScore + bonus);
			} else { // 무승부인 경우
				solve(count + 1, myScore, yourScore);
			}
			isSelected[number] = false;
		}
		
	}
}
