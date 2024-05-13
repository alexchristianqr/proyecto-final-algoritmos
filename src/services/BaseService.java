package services;

import core.db.MysqlDBService;
import core.utils.Util;

public abstract class BaseService {

    public static Util util = new Util();
    public MysqlDBService db;
    public String querySQL_1, querySQL_2, querySQL_3;
}
