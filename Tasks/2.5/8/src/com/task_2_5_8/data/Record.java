package com.task_2_5_8.data;

import java.util.Objects;

public class Record implements Comparable<Record>
{
	private String value;
	private int freq;
	
	public Record(String v, int f)
	{
		value = v;
		freq = f;
	}

	@Override
	public int compareTo(Record r) 
	{
		if (this.freq > r.freq)
		{
			return 1;
		}
		if (this.freq < r.freq)
		{
			return -1;
		}
		if (!Objects.equals(this.value, r.value))
		{
			return this.value.compareTo(r.value);
		}
		return 0;
	}
	
	public String toString()
	{
		return value + ", " + freq;
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
		Record other = (Record) obj;
		return Objects.equals(value, other.value);
	}
	
	@Override
	public int hashCode() 
	{
		return Objects.hash(value);
	}
}
