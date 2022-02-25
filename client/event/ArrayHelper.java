package client.event;

import java.util.Iterator;

public class ArrayHelper<T> implements Iterable<T>
{
    private T[] elements;

    public ArrayHelper(T[] array)
    {
        this.elements = array;
    }

    public ArrayHelper()
    {
        this.elements = new Object[0];
    }

    public void add(T t)
    {
        if (t != null)
        {
            Object[] aobject = new Object[this.size() + 1];

            for (int i = 0; i < aobject.length; ++i)
            {
                if (i < this.size())
                {
                    aobject[i] = this.get(i);
                }
                else
                {
                    aobject[i] = t;
                }
            }

            this.set(aobject);
        }
    }

    public boolean contains(T t)
    {
        Object[] aobject;

        for (T t : aobject = this.array())
        {
            if (t.equals(t))
            {
                return true;
            }
        }

        return false;
    }

    public void remove(T t)
    {
        if (this.contains(t))
        {
            Object[] aobject = new Object[this.size() - 1];
            boolean flag = true;

            for (int i = 0; i < this.size(); ++i)
            {
                if (flag && this.get(i).equals(t))
                {
                    flag = false;
                }
                else
                {
                    aobject[flag ? i : i - 1] = this.get(i);
                }
            }

            this.set(aobject);
        }
    }

    public T[] array()
    {
        return this.elements;
    }

    public int size()
    {
        return this.array().length;
    }

    public void set(T[] array)
    {
        this.elements = array;
    }

    public T get(int index)
    {
        return (T)this.array()[index];
    }

    public void clear()
    {
        this.elements = new Object[0];
    }

    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    public Iterator<T> iterator()
    {
        return new Iterator<T>()
        {
            private int index = 0;
            public boolean hasNext()
            {
                return this.index < ArrayHelper.this.size() && ArrayHelper.this.get(this.index) != null;
            }
            public T next()
            {
                return ArrayHelper.this.get(this.index++);
            }
            public void remove()
            {
                ArrayHelper.this.remove(ArrayHelper.this.get(this.index));
            }
        };
    }
}
