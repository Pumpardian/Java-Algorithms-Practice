package com.task_2_5_24.datastructures;

import java.util.Objects;

public class Tuple implements Comparable<Tuple>
{
	private String name;
	private int id;
	
	public Tuple(String name, int id)
	{
		this.name = name;
		this.id = id;
	}

	@Override
	public int compareTo(Tuple tuple)
	{
		return this.name.compareTo(tuple.name);
	}
	
	public String toString()
	{
		return name + " " + id;
	}

	@Override
	public int hashCode() 
	{
		return Objects.hash(id, name);
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
		Tuple other = (Tuple) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
}
