package linkedList;

public interface PredicateWithException<T> {

	public boolean test(T t, int a, int b) throws Exception;
	
}