package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){ // id값이 없다는건 새로 생성하는 객체라는것.
            em.persist(item); // 신규로 등록
        } else {
            em.merge(item); // db에 등록된것을 가져온건 (update와 유사)
            // 단순하게는 updateItem 과 같은 로직이라고 할 수 있다.
            // Item객체(준영속상태)의 식별자에 해당(1차캐시->DB)하는 객체의 각 필드들을 엎어준다.
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
