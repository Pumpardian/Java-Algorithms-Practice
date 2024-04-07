package com.task_2_5_14.data;

import java.util.Arrays;

public class Domain implements Comparable<Domain>
{
	private final String[] fields;
	private final int size;
	
	public Domain(String name)
	{
		fields = name.split("\\.");
		size = fields.length;
	}
	
	public String toString()
	{
		if (size == 0)
		{
			return "";
		}
		StringBuilder str = new StringBuilder();
		str.append(fields[0]);
		
		for (int i = 1; i < size; ++i)
		{
			StringBuilder s = new StringBuilder();
			s.append(fields[i]);
			s.append(".");
			s.append(str);
			str = s;
		}
		return str.toString();
	}
	
	public int compareTo(Domain domain)
	{
		for (int i = 0; i < Math.min(this.size, domain.size); ++i)
		{
			String s = this.fields[this.size - i - 1];
			String t = domain.fields[domain.size - i - 1];
			int c = s.compareTo(t);
			if (c > 0)
			{
				return 1;
			}
			if (c < 0)
			{
				return -1;
			}
		}
		return this.size - domain.size;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(fields);
		return result;
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
		Domain other = (Domain) obj;
		return Arrays.equals(fields, other.fields);
	}
}
