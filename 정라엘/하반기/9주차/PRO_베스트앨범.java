import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genrePlayCount = new HashMap<>();
        Map<String, PriorityQueue<Song>> genreSongs = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            genrePlayCount.put(genres[i], genrePlayCount.getOrDefault(genres[i], 0) + plays[i]);
            
            genreSongs.putIfAbsent(genres[i], new PriorityQueue<>());
            genreSongs.get(genres[i]).offer(new Song(i, plays[i]));
        }
        
        // 장르별로 총 재생 횟수 정렬
        List<String> sortedGenres = new ArrayList<>(genrePlayCount.keySet());
        sortedGenres.sort((a, b) -> genrePlayCount.get(b) - genrePlayCount.get(a));
        
        // 베스트 앨범에 추가할 노래 리스트 생성
        List<Integer> bestAlbum = new ArrayList<>();
        for (String genre : sortedGenres) {
            PriorityQueue<Song> pq = genreSongs.get(genre);
            int count = 0;
            while (!pq.isEmpty() && count < 2) {
                bestAlbum.add(pq.poll().index);
                count++;
            }
        }
        
        return bestAlbum.stream().mapToInt(i -> i).toArray();
    }
}

class Song implements Comparable<Song> {
    int index;
    int plays;
    
    Song(int index, int plays) {
        this.index = index;
        this.plays = plays;
    }
    
    // 재생 횟수 기준 내림차순, 동일하면 인덱스 오름차순 정렬
    @Override
    public int compareTo(Song other) {
        if (this.plays == other.plays) {
            return this.index - other.index;
        }
        return other.plays - this.plays;
    }
}
