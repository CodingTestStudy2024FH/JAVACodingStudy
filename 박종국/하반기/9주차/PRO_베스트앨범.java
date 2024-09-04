import java.util.*;

class Solution{
    HashMap<String, HashMap<Integer, Integer>> playTimes;
    HashMap<String, Integer> takeGenres;
    public ArrayList<Integer> solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        int size = genres.length;
        playTimes = new HashMap<>();
        takeGenres = new HashMap<>();

        for(int i = 0; i < size; i++){
            String genre = genres[i];
            int play = plays[i];
            if(playTimes.containsKey(genre)) playTimes.get(genre).put(i, play);
            else{
                HashMap<Integer, Integer> playMap = new HashMap<>();
                playMap.put(i, play);
                playTimes.put(genre, playMap);
            }
            takeGenres.put(genre, takeGenres.getOrDefault(genre, 0) + play);
        }

        ArrayList<String> keySet = new ArrayList<>(takeGenres.keySet());
        keySet.sort((s1, s2) -> takeGenres.get(s2) - (takeGenres.get(s1)));
        System.out.println(keySet);

        for(String genre: keySet){
            HashMap<Integer, Integer> playMap = playTimes.get(genre);
            List<Integer> genreList = new ArrayList<>(playMap.keySet());
            genreList.sort((o1, o2) -> playMap.get(o2) - playMap.get(o1));
            answer.add(genreList.get(0));
            if(genreList.size() > 1) answer.add(genreList.get(1));
        }
        return answer;
    }
}
