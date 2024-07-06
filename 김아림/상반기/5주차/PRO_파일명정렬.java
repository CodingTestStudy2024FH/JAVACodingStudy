import java.util.*;

public class PRO_파일명정렬 {

    // static String[] files = {"img1.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
    //static String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
    //static String[] files = {"O00321", "O49qcGPHuRLR5FEfoO00321"};
    static String[] files = {"F13.png", "F14", "f013", "F014", "F013.TXT"};

    public static void main(String[] args) {

        // 만약 모든 pq에서 return이 0이 나올 경우에 넣은 대로 들어가야하는데 그게 쳐 안되고 있는 중임 ㅜ.ㅜ 왜이러냐고
        String[] answer = new String[files.length];
        List<FileStructure> list = new ArrayList<>();

        /**
         * PQ 로 하면 안되고 ArrayList 로 하면 되는 이유 ?
         * PriorityQueue는 기본적으로 오름차순으로 요소를 정렬합니다. 만약 두 객체의 비교 결과가 0이라면, 
         * PriorityQueue는 두 객체를 동등하게 취급하여 정렬되지 않는다고 보장하지 않습니다. 
         * 이러한 특성 때문에 두 객체의 우선순위가 동일한 경우에도 
         * 객체의 삽입 순서를 보장하지 않을 수 있습니다.
         */


//        PriorityQueue<FileStructure> pq = new PriorityQueue<FileStructure>((o1, o2) -> {
//            if (o1.head.compareToIgnoreCase(o2.head) == 0) {
//                // tail 은 비교 안해도 됨 (들어온 순서대로임)
//                return Integer.parseInt(o1.number) - Integer.parseInt(o2.number);
//            } else {
//                return o1.head.toLowerCase().compareToIgnoreCase(o2.head.toLowerCase());
//            }
//        });

        // HEAD는 숫자가 아닌 문자로 이루어져 있으며, 최소한 한 글자 이상이다.
        // NUMBER는 한 글자에서 최대 다섯 글자 사이의 연속된 숫자로 이루어져 있으며
        // 요 조건을 처리를 안해줘서 15점.. 추가해준다 ^^

        for (String file : files) {
            // FileStructure로 변환하기
            StringBuilder numberBuilder = new StringBuilder();
            StringBuilder headBuilder = new StringBuilder();
            headBuilder.append(file.charAt(0));
            String tail = "";
            boolean headFalg = false; // 헤드
            boolean numberFlag = false; // 숫자 시작
            for (int i = 1; i < file.length(); i++) {
                char c = file.charAt(i);
                // 숫자 시작
                if (Character.isDigit(c) && !numberFlag && !headFalg ) {
                    numberBuilder.append(c);
                    headFalg = true; // head 끝
                    headBuilder.append(file.substring(1, i));
                }
                // 숫자 받기
                else if (Character.isDigit(c) && headFalg && !numberFlag) {
                    numberBuilder.append(c);
                    if(numberBuilder.length() == 5){
                        numberFlag = true;
                    }
                }
                // 숫자를 받던도중 갑자기 숫자가 아닌 값이 들어오는 경우 혹은 이미 5자리를 넘어선 경우
                else if (!Character.isDigit(c) && headFalg || numberFlag) {
                    tail = file.substring(i, file.length());
                    break;
                }
            }

            FileStructure fs = new FileStructure(headBuilder.toString(), numberBuilder.toString(), tail);
            System.out.println(fs);
            list.add(fs);
        }

        list.sort(new Comparator<FileStructure>() {
            @Override
            public int compare(FileStructure o1, FileStructure o2) {
                if (o1.head.compareToIgnoreCase(o2.head) == 0) {
                    // tail 은 비교 안해도 됨 (들어온 순서대로임)
                    return Integer.parseInt(o1.number) - Integer.parseInt(o2.number);
                } else {
                    return o1.head.toLowerCase().compareToIgnoreCase(o2.head.toLowerCase());
                }
            }
        });
        int idx = 0;
        for (FileStructure fs : list){
            answer[idx++] = fs.makeOgFile();
        }

        System.out.println(Arrays.toString(answer));
    }


    static class FileStructure {
        String head;
        String number;
        String tail;

        public FileStructure(String h, String n, String t) {
            head = h;
            number = n;
            tail = t;
        }

        @Override
        public String toString() {
            return "FileStructure{" +
                    "head='" + head + '\'' +
                    ", number='" + number + '\'' +
                    ", tail='" + tail + '\'' +
                    '}';
        }

        public String makeOgFile() {
            return head.concat(number).concat(tail);
        }
    }
}


class Solution {
    public String[] solution(String[] files) {

        String[] answer = new String[files.length];
        PriorityQueue<FileStructure> pq = new PriorityQueue<FileStructure>((o1, o2) -> {
            if (o1.head.toLowerCase().equals(o2.head.toLowerCase())) {
                // tail 은 비교 안해도 됨 (들어온 순서대로임)
                return Integer.parseInt(o1.number) - Integer.parseInt(o2.number);
            } else {
                return o1.head.toLowerCase().compareTo(o2.head.toLowerCase());
            }
        });

        for (String file : files) {
            // FileStructure로 변환하기
            StringBuilder numberBuilder = new StringBuilder();
            String head = "";
            String tail = "";
            boolean headFalg = false; // 헤드
            boolean numberFlag = false; // 숫자 시작
            for (int i = 0; i < file.length(); i++) {
                char c = file.charAt(i);
                // 숫자 시작
                if (Character.isDigit(c) && numberFlag == false) {
                    numberBuilder.append(c);
                    numberFlag = true; // 숫자 시작
                    headFalg = true; // head 끝
                    head = file.substring(0, i);
                }
                // 숫자 받기
                else if (Character.isDigit(c) && headFalg == true) {
                    numberBuilder.append(c);
                }
                // 숫자 받고 있는데 갑자기 문자가 들어오는 경우 -> tail 시작
                else if (!Character.isDigit(c) && numberFlag == true) {
                    // 지금위치부터 끝까지 전부 tail이고 for문 탈출해도됨
                    tail = file.substring(i, file.length());
                    break;
                }
            }

            FileStructure fs = new FileStructure(head, numberBuilder.toString(), tail);
            // System.out.println(fs);
            pq.add(fs);
        }

        int idx = 0;
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll().makeOgFile();
        }

        System.out.println(Arrays.toString(answer));

        return answer;
    }


    static class FileStructure{
        String head;
        String number;
        String tail;

        public FileStructure(String h, String n, String t){
            head = h;
            number = n;
            tail = t;
        }


        @Override
        public String toString() {
            return "FileStructure{" +
                    "head='" + head + '\'' +
                    ", number='" + number + '\'' +
                    ", tail='" + tail + '\'' +
                    '}';
        }

        public String makeOgFile() {
            return head.concat(number).concat(tail);
        }
    }
}
