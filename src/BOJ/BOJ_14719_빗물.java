package BOJ;
import java.util.*;
import java.io.*;

public class BOJ_14719_빗물 {
    public static void main(String[] args) throws Exception {
        int h, m;
        int[] arr;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int rain = 0;
        for (int i = 1; i < m-1; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j < i; j++) { // left
                left = Math.max(left, arr[j]);
            }
            for (int j = i+1; j < m; j++) { // right
                right = Math.max(right, arr[j]);
            }
            int wall = Math.min(left, right);
            rain += (wall > arr[i]? wall - arr[i] : 0);
        }
        System.out.println(rain);
        br.close();
    }
}
