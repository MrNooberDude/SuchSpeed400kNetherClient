package client.event;

import client.Client;
import java.lang.reflect.InvocationTargetException;

public abstract class Event
{
    private boolean cancelled;

    public Event call()
    {
        this.cancelled = false;
        call(this);
        return this;
    }

    public boolean isCancelled()
    {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }

    private static final void call(Event event)
    {
        EventManager eventmanager = Client.INSTANCE.eventManager;
        ArrayHelper<Data> arrayhelper = EventManager.get(event.getClass());

        if (arrayhelper != null)
        {
            for (Data data : arrayhelper)
            {
                try
                {
                    data.target.invoke(data.source, new Object[] {event});
                }
                catch (IllegalAccessException illegalaccessexception)
                {
                    illegalaccessexception.printStackTrace();
                }
                catch (InvocationTargetException invocationtargetexception)
                {
                    invocationtargetexception.printStackTrace();
                }
            }
        }
    }

    public static enum State
    {
        PRE("PRE", 0),
        POST("POST", 1);

        private State(String string, int number)
        {
        }
    }
}
