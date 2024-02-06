package linkedList;

public class ValidCharacterTest implements PredicateWithException<Character> {

	/**
	 * Throws exception {@link ListOfListsException} if item value is null.
	 * Returns true if the item value is the sentinal '&' and returns false otherwise.
	 * 
	 * @param item_value Character to test
	 * @param p_position Position in List of Lists (for exception)
	 * @param sub_p_position Position in sublist (for exception)
	 * 
	 * @return true/false - sentinal/other character
	 */
	@Override
	public boolean test(Character item_value, int p_position, int sub_p_position) throws ListOfListsExceptions {

		if (item_value == null) {
			throw new ListOfListsExceptions(p_position, sub_p_position, true);
		} else if (item_value.equals('&')) {
			return true;
		}

		return false;

	}
}
