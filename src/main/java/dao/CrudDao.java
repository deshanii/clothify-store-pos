package dao;

import java.util.List;

public interface CrudDao<T, S> extends SuperDao {
    boolean add(T t) ;
    boolean update(T t) ;
    boolean delete (S id);
    List<T> findAll() ;
    S findlastId() ;
      T find(S id) ;
}
