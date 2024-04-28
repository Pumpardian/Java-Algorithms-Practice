package com.task_3_4_25.data;

import java.util.Date;
import java.util.Objects;

public class Transaction 
{
    private final String who;
    private final Date when;
    private final double amount;

    private int hash;

    public Transaction(String who, Date when, double amount) 
    {
        this.who = who;
        this.when = when;
        this.amount = amount;

        hash = -1;
    }

    @Override
    public int hashCode() 
    {
        int h;

        if (hash != -1) 
        {
            h = hash;
        } 
        else 
        {
            h = 17;
            h = 31 * h + who.hashCode();
            h = 31 * h + when.hashCode();
            h = 31 * h + ((Double) amount).hashCode();

            hash = h;
        }

        return h;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Transaction other = (Transaction) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && hash == other.hash
				&& Objects.equals(when, other.when) && Objects.equals(who, other.who);
	}
}
