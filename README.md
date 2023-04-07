# 데이터 구조 프로젝트

이 프로젝트는 다양한 데이터 구조들의 예시 코드를 제공하는 프로젝트입니다. 
데이터 구조들의 예시 코드는 Java를 사용하여 List, Stack, Queue, Deque, Hash 등의 다양한 데이터 구조를 구현한 것을 포함합니다. 
예시 코드는 해당 데이터 구조를 활용하여 데이터의 추가, 삭제, 검색, 정렬 등을 수행하는 예시를 제공합니다. 
예시 코드를 참고하여 데이터 구조의 사용 방법과 예시를 확인하고, 필요에 따라 프로젝트에 적용할 수 있습니다.

해당 프로젝트는 아래 포스팅을 참고하여 작성하였습니다.

[자바 11 - Collections](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collections.html)

[자바 컬렉션 프레임워크 (Java Collections Framework)](https://st-lab.tistory.com/142?category=856997)

## 데이터 구조 구현

프로젝트는 다음과 같은 데이터 구조들을 구현합니다.

- List Interface 🌞 
  - ArrayList 🌞
  - LinkedList (단일 연결 리스트) 🌞
  - LinkedList (이중 연결 리스트) 🌞
- Stack Interface 🌞
  - Stack 🌞
- Queue Interface 🌞
  - ArrayQueue 🌞
  - LinkedListQueue 🌞
  - ArrayDeque 🌞
  - LinkedListDeque 🌞
  - PriorityQueue ⛅
- Set Interface ⛅
  - HashSet ⛅
  - LinkedHashSet ⛅
  - TreeSet ⛅

## 테스트

프로젝트에서 구현한 데이터 구조들은 JUnit 5를 사용하여 단위 테스트를 수행하였습니다.
아래는 몇 가지 단위 테스트 코드의 예시입니다.

#### ArrayList 테스트
```java
package structure;

import org.junit.jupiter.api.Test;
import structure.list.array.DynamicArray;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

  @Test
  void testAdd() {
    DynamicArray<String> array = new DynamicArray<>(2);

    assertTrue(array.add("banana"));
    assertTrue(array.add("apple"));
    assertTrue(array.add("orange"));
    assertEquals("orange", array.get(2));
  }

  @Test
  void testAddWithIdx() {
    DynamicArray<String> array = new DynamicArray<>(2);
    array.add("banana");
    array.add("apple");
    array.add(1, "orange");

    assertEquals("orange", array.get(1));
    assertEquals("apple", array.get(2));
  }

  @Test
  void testRemoveByIdx() {
    DynamicArray<String> array = new DynamicArray<>(2);
    array.add("banana");
    array.add("apple");
    array.add("orange");

    assertEquals("apple", array.remove(1));
    assertEquals(2, array.size());
    assertEquals("orange", array.remove(1));

    Throwable lessException = assertThrows(IndexOutOfBoundsException.class, () -> array.remove(-1));
    assertEquals("Index: -1, Size: 1", lessException.getMessage());
    Throwable excessException = assertThrows(IndexOutOfBoundsException.class, () -> array.remove(1000));
    assertEquals("Index: 1000, Size: 1", excessException.getMessage());
  }

  @Test
  void testRemoveByObj() {
    DynamicArray<String> array = new DynamicArray<>(2);
    array.add("banana");
    array.add("apple");
    array.add("orange");

    assertTrue(array.remove("apple"));
    assertFalse(array.remove("I'm not fruit"));
    assertEquals(2, array.size());
    assertEquals("orange", array.get(1));
  }

  @Test
  void testGet() {
    DynamicArray<String> array = new DynamicArray<>(3);
    array.add("banana");
    array.add("apple");

    assertEquals("banana", array.get(0));
    assertEquals("apple", array.get(1));
  }

  @Test
  void testSet() {
    DynamicArray<String> array = new DynamicArray<>(3);
    array.add("banana");
    array.add("apple");

    assertEquals("banana", array.get(0));
    array.set(0, "orange");
    assertEquals("orange", array.get(0));
  }

  @Test
  void testContains() {
    DynamicArray<Integer> array = new DynamicArray<>();
    array.add(0);
    array.add(1);
    array.add(2);

    assertTrue(array.contains(2));
    assertFalse(array.contains(10000));
  }

  @Test
  void testIndexOf() {
    DynamicArray<Integer> array = new DynamicArray<>();
    array.add(0);
    array.add(1);
    array.add(2);

    assertEquals(0, array.indexOf(0));
    assertEquals(1, array.indexOf(1));
    assertEquals(2, array.indexOf(2));
    assertEquals(-1, array.indexOf(100));
  }

  @Test
  void testSize() {
    DynamicArray<Integer> array = new DynamicArray<>();
    array.add(0);
    array.add(1);
    array.add(2);

    assertEquals(3, array.size());
    array.remove(1);
    assertEquals(2, array.size());
  }

  @Test
  void testIsEmpty() {
    DynamicArray<Integer> array = new DynamicArray<>();
    array.add(0);
    array.add(1);
    array.add(2);

    assertFalse(array.isEmpty());
    array.clear();
    assertTrue(array.isEmpty());
  }

  @Test
  void testClear() {
    DynamicArray<Integer> array = new DynamicArray<>();
    array.add(0);
    array.add(1);
    array.add(2);
    array.clear();

    assertEquals(0, array.size());
  }

  @Test
  void testIterator() {
    DynamicArray<Integer> array = new DynamicArray<>();
    array.add(0);
    array.add(1);
    array.add(2);
    StringBuilder sb = new StringBuilder();
    for (int i : array) {
      sb.append(i);
    }

    assertEquals("012", sb.toString());
  }
}
```


#### Stack 테스트
```java
package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.stack.MyStack;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {

  private MyStack<Integer> myStack;

  @BeforeEach
  void setUp() {
    myStack = new MyStack<>();
  }

  @Test
  void testPushAndPop() {
    assertEquals(1, myStack.push(1));
    assertEquals(2, myStack.push(2));
    assertEquals(3, myStack.push(3));
    assertEquals(3, myStack.pop());
    assertEquals(2, myStack.pop());
    assertEquals(1, myStack.pop());
  }

  @Test
  void testPeek() {
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);

    assertEquals(3, myStack.peek());
    assertEquals(3, myStack.size());
  }

  @Test
  void testSearch() {
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);

    assertEquals(3, myStack.search(1));
    assertEquals(-1, myStack.search(4));
  }

  @Test
  void testSize() {
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);

    assertEquals(3, myStack.size());
    myStack.pop();
    myStack.pop();
    assertEquals(1, myStack.size());
  }

  @Test
  void testClear() {
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);
    myStack.clear();

    assertEquals(0, myStack.size());
    assertTrue(myStack.empty());
  }

  @Test
  void testRemove() {
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);


    // remove using index
    assertEquals(3, myStack.remove(2));

    // remove using Obj
    assertTrue(myStack.remove(Integer.valueOf(1)));
    assertEquals(1, myStack.size());
  }

  @Test
  void testContains() {
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);

    assertTrue(myStack.contains(2));
    assertFalse(myStack.contains(4));
  }

  @Test
  void testIndexOf() {
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);

    assertEquals(1, myStack.indexOf(2));
    assertEquals(-1, myStack.indexOf(4));
  }
}
```

## 문의 사항

프로젝트에 대한 문의 사항이 있다면 Issues 탭에서 질문을 남겨주세요!