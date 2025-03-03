
public class PRO_서버증설횟수 {
    /*
        m명 늘어날 때 마다 서버 1대 추가 필요
        m명 미만이면 증설 필요 X
        n x m 명 이상, ( n+1 ) x m명 미만 = 최소 n대의 서버.
        한번 증설하면 k시간동안 운영하고, 이후는 반납

        하루동안 모든 게임 이용자가 게임을 하려면 최소 몇번 증설?
     */
    public static void main(String[] args) {
        int[] arr = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        System.out.println(solution(arr,3,5));
    }
    static int[] server;
    public static int solution(int[] players, int m, int k) {

        server = new int[24]; //현재 서버
        int addServer = 0;//증설 횟수
        for(int i=0;i<24;i++){
            int need = players[i]/m;
            if(need>server[i]){ //현재 서버 개수보다 필요한 개수가 크면 그만큼 증설해야함.
                addServer+=need-server[i]; //증설해야하는 서버 더하기
                setServer(need-server[i], i, k); //자신 포함 k개의 서버에 서버 증설
            }
        }

        return addServer;
    }

    public static void setServer(int newServer,int idx,int k) {
        int cnt=0;
        while(cnt!=k){
            if(cnt+idx>=24){
                break;
            }
            server[idx+cnt]+=newServer;
            cnt++;
        }
    }

}
