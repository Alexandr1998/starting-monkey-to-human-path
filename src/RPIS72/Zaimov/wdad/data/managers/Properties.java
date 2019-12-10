package RPIS72.Zaimov.wdad.data.managers;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

public class Properties {

    private Appconfig appconfig;

    public Properties(Appconfig appconfig){
        this.appconfig = appconfig;
    }

    public void setProperty(String key, String value) {
        String[] path = key.split("\\.");

        if (path[0].equalsIgnoreCase("appconfig")) {
            try {
                Object o = getObject(path);
                var set = o.getClass().getMethod("set" + path[path.length - 1], String.class);
                set.invoke(o, value);

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new NoSuchElementException("No element found");
            }
        } else
            throw new NoSuchElementException("No element found");
    }

    public String getProperty(String key) {
        String[] path = key.split("\\.");

        if (path[0].equalsIgnoreCase("appconfig")) {
            try {
                Object o = getObject(path);
                var set = o.getClass().getMethod("get" + path[path.length - 1]);
                return (String) set.invoke(o);

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new NoSuchElementException("No element found");
            }
        } else
            throw new NoSuchElementException("No element found");
    }

    public Object getObject(String[] path) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Properties.normalizeKey(path);
        Object[] objects = new Object[path.length];
        objects[0] = appconfig;

        for (int i = 1; i < path.length - 1; i++) {
            var get = objects[i - 1].getClass().getMethod("get" + path[i]);
            objects[i] = get.invoke(objects[i - 1]);
        }

        return objects[objects.length - 1];
    }

    public static void normalizeKey(String[] path) {
        for (int i = 0; i < path.length; i++) {
            var str = path[i];
            var chars = str.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            path[i] = new String(chars);
        }
    }
}
