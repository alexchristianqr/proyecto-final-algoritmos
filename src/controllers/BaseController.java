package controllers;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseController<T, U> {

    public List<T> lista = new ArrayList<>();
    public U service;
    public String querySQL_1, querySQL_2, querySQL_3;

    public int idAutoIncrementado() {
        return lista.size() + 1;
    }

}
