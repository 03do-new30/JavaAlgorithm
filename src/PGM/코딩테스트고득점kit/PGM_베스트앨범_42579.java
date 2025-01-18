package PGM.코딩테스트고득점kit;

import java.util.*;

public class PGM_베스트앨범_42579 {
    public static void main(String[] args) {
        String[][] genres = {{"classic", "pop", "classic", "classic", "pop"}};
        int[][] plays = {{500, 600, 150, 800, 2500}};
        int[][] result = {{4, 1, 3, 0}};

        Solution sol = new Solution();
        for (int i = 0; i < result.length; i++) {
            System.out.println("# " + i);
            System.out.println(
                    Arrays.equals(result[i], sol.solution(genres[i], plays[i]))
            );
        }
    }

    static class Solution {
        public int[] solution(String[] genres, int[] plays) {

            ArrayList<Integer> answer = new ArrayList<>();

            HashMap<String, Integer> num = new HashMap<>(); // 장르별 개수
            HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>(); // 장르에 속하는 노래 및 재생 횟수

            for (int i = 0; i < plays.length; i ++) {
                if(!num.containsKey(genres[i])) {
                    HashMap<Integer, Integer> map = new HashMap<>();
                    map.put(i, plays[i]);
                    music.put(genres[i], map);
                    num.put(genres[i], plays[i]);
                } else {
                    music.get(genres[i]).put(i, plays[i]);
                    num.put(genres[i], num.get(genres[i]) + plays[i]);
                }
            }

            List<String> keySet = new ArrayList(num.keySet());
            // 재생 횟수가 많은 장르 순서로 내림차순 정렬
            Collections.sort(keySet, (s1, s2) -> num.get(s2) - num.get(s1));

            for (String key : keySet) {
                HashMap<Integer, Integer> map = music.get(key);
                List<Integer> genre_key = new ArrayList(map.keySet());

                // 해당 장르의 곡들 중 재생 횟수가 많은 곡 순서대로 내림차순 정렬
                Collections.sort(genre_key, (i1, i2) -> map.get(i2) - map.get(i1));
                answer.add(genre_key.get(0));
                if (genre_key.size() > 1)
                    answer.add(genre_key.get(1));
            }

            return answer.stream().mapToInt(i -> i).toArray();
        }
    }
}
