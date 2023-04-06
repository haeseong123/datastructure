package structure.list;

public interface List<E> extends Iterable<E>{
    /**
     * 리스트에 요소 추가
     *
     * @param e 리스트에 추가할 요소
     * @return 리스트에서 중복을 허용하지 않을 경우
     * 리스트에 이미 중복되는 요소가 있다면 {@code false}
     * 중복되는 원소가 없을 경우 {@code true}
     */
    boolean add(E e);

    /**
     * 리스트의 특정 인덱스에 요소 추가
     * 해당 인덱스 이후의 요소들은 한 칸씩 뒤로 밀림
     *
     * @param index 리스트에 요소를 추가할 특정 위치 변수
     * @param e     리스트에 추가할 요소
     */
    void add(int index, E e);

    /**
     * 리스트의 index 위치에 있는 요소를 삭제
     *
     * @param index 리스트에서 삭제 할 요소의 위치 변수
     * @return 삭제된 요소
     */
    E remove(int index);

    /**
     * 리스트의 특정 요소 삭제
     * 동일한 요소가 여러 개일 경우 가장 처음 발견한 요소만 삭제
     *
     * @param o 리스트에서 삭제할 요소
     * @return 리스트에
     * 삭제할 요소가 없거나 삭제에 실패할 경우 {@code false}
     * 삭제에 성공할 경우 {@code true}
     */
    boolean remove(Object o);

    /**
     * 리스트의 특정 위치에 있는 요소 가져오기
     *
     * @param index 리스트에 접근할 위치 변수
     * @return 리스트의 index 위치에 있는 요소
     */
    E get(int index);

    /**
     * 리스트에서 특정 위치에 있는 요소를 새 요소로 대체
     *
     * @param index 리스트에 접근할 위치 변수
     * @param e     새로 대체할 요소 변수
     */
    void set(int index, E e);

    /**
     * 특정 요소가 리스트에 존재하는지 확인
     *
     * @param e 리스트에서 찾을 특정 요소 변수
     * @return 리스트에
     * 특정 요소가 존재할 경우 {@code true}
     * 특정 요소가 존재하지 않는 경우 {@code false}
     */
    boolean contains(Object o);

    /**
     * 특정 요소가 리스트의 몇 번째 위치에 있는지 반환
     *
     * @param e 리스트 찾을 특정 요소 변수
     * @return 리스트에서 처음으로 요소와 일치하는 위치를 반환
     * 만약 일치하는 요소가 없을 경우 -1을 반환
     */
    int indexOf(Object o);

    /**
     * 리스트에 있는 요소의 개수를 반환
     *
     * @return 리스트에 있는 요소 개수 반환
     */
    int size();

    /**
     * 리스트에 요소가 없는지 확인
     *
     * @return 리스트에
     * 요소가 있을 없을 경우{@code true}
     * 요소가 있을 경우 {@code false}
     */
    boolean isEmpty();

    /**
     * 리스트의 모든 요소를 삭제
     */
    public void clear();
}
