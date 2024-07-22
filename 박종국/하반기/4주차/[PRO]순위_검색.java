package binarysearch;

import java.util.Arrays;
import java.util.StringTokenizer;

public class PRO_순위_검색 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] info = {
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"
        };
        String[] query = {
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"
        };

        System.out.println(Arrays.toString(solution.solution(info, query)));
    }
}

class Solution {
    public int[] solution(String[] info, String[] query) {
        // 참가자의 수
        int infoSize = info.length;
        // 쿼리의 수
        int querySize = query.length;

        int[] answer = new int[querySize];
        StringTokenizer st;
        Participant[] participants = new Participant[infoSize];
        Participant[] queries = new Participant[infoSize];

        for(int i = 0; i < infoSize; i++){
            st = new StringTokenizer(info[i]);
            String language = st.nextToken();
            String job = st.nextToken();
            String career = st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            participants[i] = new Participant(language, job, career, food, score);
        }
        Arrays.sort(participants);

        for(int i = 0; i < querySize; i++){
            st = new StringTokenizer(query[i]);
            String language = st.nextToken();
            // and 생략
            st.nextToken();
            String job = st.nextToken();
            // and 생략
            st.nextToken();
            String career = st.nextToken();
            // and 생략
            st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            Participant condition = new Participant(language, job, career, food, score);
            int skip = binarySearch(participants, condition);
            answer[i] = (int) Arrays.stream(participants)
                    .skip(skip)
                    .filter(p -> (condition.language.equals("-") || p.language.equals(condition.language)) &&
                            (condition.job.equals("-") || p.job.equals(condition.job)) &&
                            (condition.career.equals("-") || p.career.equals(condition.career)) &&
                            (condition.food.equals("-") || p.food.equals(condition.food)))
                    .count();

        }

        return answer;
    }

    int binarySearch(Participant[] participants, Participant p) {
        int start = 0;
        int end = participants.length;

        // score보다 높은 값이 나오는 처음 값 찾기
        while(start < end){
            int mid = (start + end) / 2;
            int value = participants[mid].score;
            if(value >= p.score) end = mid;
            else start = mid + 1;
        }
        return start;
    }
}

class Participant implements Comparable<Participant>  {
    String language, job, career, food;
    int score;

    public Participant(String language, String job, String career, String food, int score) {
        this.language = language;
        this.job = job;
        this.career = career;
        this.food = food;
        this.score = score;
    }


    @Override
    public int compareTo(Participant o) {
        return this.score - o.score;
    }
}
