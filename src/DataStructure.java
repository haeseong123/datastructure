import java.util.ArrayList;

public class DataStructure {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(3);
        arrayList.add(1);
        arrayList.add(1);

        System.out.println(arrayList.get(2));

        arrayList.forEach(System.out::println);
    }
}
// 완료
// 고정 크기 배열

// 진행
// 동적 크기 배열
// 연결 리스트
// 원형 연결 리스트
// 이중 연결 리스트
// 스택
// 큐
// 데크
// 링버퍼
// 해시 맵/테이블/셋
// 트리
// 그래프
