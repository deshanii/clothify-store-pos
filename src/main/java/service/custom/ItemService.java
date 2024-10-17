package service.custom;

import dto.Item;

import java.util.List;

public interface ItemService {
    boolean addItem (Item item);
    boolean deleteItem(String text);
    boolean updateItem(Item item);
    Item findItem(String id);

    List<Item> getItem();
}
