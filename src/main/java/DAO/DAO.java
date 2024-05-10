package DAO;


import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.DbDontWorkException;

import java.util.List;

public interface DAO<T> {

    List<T> findAll() throws DbDontWorkException;

    T find(T t) throws DbDontWorkException, DbObjectNotFoundException;

    T save(T t) throws DbDontWorkException;

    T update(T t) throws DbDontWorkException;

}
