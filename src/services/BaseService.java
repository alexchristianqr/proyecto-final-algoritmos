package services;

import core.services.MysqlDBService;
import core.utils.UsuarioThreadLocal;
import core.utils.Util;
import models.Usuario;

public abstract class BaseService {

    public static Util util = new Util();
    public MysqlDBService db;
    public String querySQL_1, querySQL_2, querySQL_3;
    public Usuario auth = UsuarioThreadLocal.get();
    public boolean originalAutoCommit = true;
}
