package services;

import core.db.MysqlDBService;

public abstract class BaseService {

    public MysqlDBService db;
    public String querySQL_1, querySQL_2, querySQL_3;
}
