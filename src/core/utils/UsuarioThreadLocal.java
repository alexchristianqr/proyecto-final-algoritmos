package core.utils;

import models.Usuario;

public class UsuarioThreadLocal  {

     public static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    public static void set(Usuario user) {
        THREAD_LOCAL.set(user);
    }

    public static void unset() {
        THREAD_LOCAL.remove();
    }

    public static Usuario get() {
        return (Usuario) THREAD_LOCAL.get();
    }
}
