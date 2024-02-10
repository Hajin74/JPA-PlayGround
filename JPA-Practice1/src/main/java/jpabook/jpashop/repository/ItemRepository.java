package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // jpa에 저장하기 전까지 id가 없다. id가 없으면 새걸로 보고 저장한다고 생각.
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
            // item 을 find 에서 필드값을 하나하나 바꿔치기한다
            // 파라미터 item은 준영속인 상태고 리턴된건 영속상태다
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
