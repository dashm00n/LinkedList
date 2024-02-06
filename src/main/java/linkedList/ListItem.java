package linkedList;

public class ListItem<T> {
	
	public T key;
	public ListItem<T> next;
	
	
	public boolean hasNext()
	{
		if(this.next == null)
		{
			return false;
		}
		else
		{ 
			return true;
		}
	}
}
