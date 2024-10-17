package service.custom;


import dto.Item;
import entity.ItemEntity;
import java.util.List;

public interface ItemService {
    boolean addItem (Item item);
    boolean deleteItem(String text);
    boolean updateItem(Item item);
    Item findItem(String id);


    List<ItemEntity> getItem();
}
