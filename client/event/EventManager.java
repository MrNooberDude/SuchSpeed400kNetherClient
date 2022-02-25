package client.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class EventManager
{
    private static final Map < Class <? extends Event > , ArrayHelper<Data >> REGISTRY_MAP = new HashMap();

    public static void register(Object o)
    {
        Method[] amethod;

        for (Method method : amethod = o.getClass().getDeclaredMethods())
        {
            if (!isMethodBad(method))
            {
                register(method, o);
            }
        }
    }

    public static void register(Object o, Class <? extends Event > clazz)
    {
        Method[] amethod;

        for (Method method : amethod = o.getClass().getDeclaredMethods())
        {
            if (!isMethodBad(method, clazz))
            {
                register(method, o);
            }
        }
    }

    private static void register(Method method, Object o)
    {
        Class<?> oclass = method.getParameterTypes()[0];
        final Data data = new Data(o, method, ((EventTarget)method.getAnnotation(EventTarget.class)).value());

        if (!data.target.isAccessible())
        {
            data.target.setAccessible(true);
        }

        if (REGISTRY_MAP.containsKey(oclass))
        {
            if (!((ArrayHelper)REGISTRY_MAP.get(oclass)).contains(data))
            {
                ((ArrayHelper)REGISTRY_MAP.get(oclass)).add(data);
                sortListValue(oclass);
            }
        }
        else
        {
            REGISTRY_MAP.put(oclass, new ArrayHelper<Data>()
            {
                {
                    this.add(data);
                }
            });
        }
    }

    public static void unregister(Object o)
    {
        for (ArrayHelper<Data> arrayhelper : REGISTRY_MAP.values())
        {
            for (Data data : arrayhelper)
            {
                if (data.source.equals(o))
                {
                    arrayhelper.remove(data);
                }
            }
        }

        cleanMap(true);
    }

    public static void unregister(Object o, Class <? extends Event > clazz)
    {
        if (REGISTRY_MAP.containsKey(clazz))
        {
            for (Data data : (ArrayHelper)REGISTRY_MAP.get(clazz))
            {
                if (data.source.equals(o))
                {
                    ((ArrayHelper)REGISTRY_MAP.get(clazz)).remove(data);
                }
            }

            cleanMap(true);
        }
    }

    public static void cleanMap(boolean b)
    {
        Iterator < Entry < Class <? extends Event > , ArrayHelper<Data >>> iterator = REGISTRY_MAP.entrySet().iterator();

        while (iterator.hasNext())
        {
            if (!b || ((ArrayHelper)((Entry)iterator.next()).getValue()).isEmpty())
            {
                iterator.remove();
            }
        }
    }

    public static void removeEnty(Class <? extends Event > clazz)
    {
        Iterator < Entry < Class <? extends Event > , ArrayHelper<Data >>> iterator = REGISTRY_MAP.entrySet().iterator();

        while (iterator.hasNext())
        {
            if (((Class)((Entry)iterator.next()).getKey()).equals(clazz))
            {
                iterator.remove();
                break;
            }
        }
    }

    private static void sortListValue(Class <? extends Event > clazz)
    {
        ArrayHelper<Data> arrayhelper = new ArrayHelper();

        for (byte b0 : Priority.VALUE_ARRAY)
        {
            for (Data data : (ArrayHelper)REGISTRY_MAP.get(clazz))
            {
                if (data.priority == b0)
                {
                    arrayhelper.add(data);
                }
            }
        }

        REGISTRY_MAP.put(clazz, arrayhelper);
    }

    private static boolean isMethodBad(Method method)
    {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
    }

    private static boolean isMethodBad(Method method, Class <? extends Event > clazz)
    {
        return isMethodBad(method) || method.getParameterTypes()[0].equals(clazz);
    }

    public static ArrayHelper<Data> get(Class <? extends Event > clazz)
    {
        return (ArrayHelper)REGISTRY_MAP.get(clazz);
    }

    public static void shutdown()
    {
        REGISTRY_MAP.clear();
    }
}
