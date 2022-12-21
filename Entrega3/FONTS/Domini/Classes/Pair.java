package Domini.Classes;

import java.io.Serializable;

/**
 * Representa una classe pair
 * @param <F> valor de la primera variable del pair
 * @param <S> valor de la segona variable del pair
 * @author pol.camprubi.prats
 */
public class Pair<F extends Object,S extends Object> implements Serializable
{
    /**
     * Representa la primera variable del pair
     */
    private F _first;
    /**
     * Representa la segona variable del pair
     */
    private S _second;

    /**
     * Constructora del pair
     * @param first valor de la primera variable
     * @param second valor de la segona variable
     */
    public Pair(F first, S second)
    {
        _first = first;
        _second = second;
    }

    /**
     * Setter de la primera variable
     * @param nFirst nou valor de la primera variable pair
     */
    public void setFirst(F nFirst) 		{_first = nFirst;	}

    /**
     * Setter de la segona variable
     * @param nSecond nou valor de la segona variable pair
     */
    public void setSecond(S nSecond)	{_second = nSecond;	}

    /**
     * Getter de la primera variable
     * @return retorna la primera variable
     */
    public F first()	{ return _first;	}

    /**
     * Getter de la segona variable
     * @return retorna la segona variable
     */
    public S second() 	{ return _second;	}

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Pair)) return false;
        Pair p = (Pair)o;
        return (_first.equals(p.first()) && _second.equals(p.second()) );
    }

    @Override
    public String toString() {return new String(_first.toString() + " | " + _second.toString()); }

}
