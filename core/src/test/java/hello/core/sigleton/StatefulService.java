package hello.core.sigleton;

public class StatefulService {
    // cmd + shift + t : 테스트클래스 만들
    private int price; // 상태를 유지하는 필드
    public int order(String name, int price){
        System.out.println("name = " + name + "price = " + price);
        //this.price = price; // 문제 발생 원인
        return price;
    }
}
