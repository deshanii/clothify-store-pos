package service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dto.Item;
import entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.custom.ItemService;
import util.DaoType;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Override
    public boolean addItem(Item item) {
        ItemDao itemDao = DaoFactory.getDaoFactory().getDaoType(DaoType.ITEM);
        ItemEntity userEntity = new ObjectMapper().convertValue(item, ItemEntity.class);
        return itemDao.add(userEntity);

    }

    @Override
    public boolean deleteItem(String text) {
        ItemDao itemDao = DaoFactory.getDaoFactory().getDaoType(DaoType.ITEM);
        return itemDao.delete(text);
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }


    @Override
    public Item findItem(String id) {
        ItemDao itemDao = DaoFactory.getDaoFactory().getDaoType(DaoType.ITEM);
        ItemEntity itemEntity = itemDao.find(id);

        return new ObjectMapper().convertValue(itemEntity, Item.class);

    }

    @Override
    public List getItem() {
        ItemDao itemDao = DaoFactory.getDaoFactory().getDaoType(DaoType.ITEM);
        List<ItemEntity> list = itemDao.findAll();
        System.out.println(list);
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        list.forEach(itemEntity -> {
            itemList.add(new ObjectMapper().convertValue(itemEntity,Item.class));
        });
        return itemList;

    }
}
