package linkedList;

/**
 * Custom Exception Class for LinkedList of LinkedLists.
 */
@SuppressWarnings("serial")
public class ListOfListsExceptions extends Exception {

	int p_position;
	int sub_p_position;
	boolean object_flag;

	String ausgabe;

	public ListOfListsExceptions() {
	}

	/**
	 * Constructs a new exception showing the wrong character and its position in
	 * the list of lists.
	 * 
	 * @param p_position     position of the linked list
	 * @param sub_p_position position of the list item in the linked list
	 * @param object_flag    flag whether a list item contains a value
	 */
	public ListOfListsExceptions(int p_position, int sub_p_position, boolean object_flag) {
		this.p_position = p_position;
		this.sub_p_position = sub_p_position;
		this.object_flag = object_flag;
	}

	public String getMessage() {

		String obj_true = "no object";
		String obj_false = "sentinel character not allowed";

		if (object_flag == true) {
			ausgabe = obj_true + " " + "at position (" + String.valueOf(p_position) + ","
					+ String.valueOf(sub_p_position) + ")";
		} else {
			ausgabe = obj_false + " " + "at position (" + String.valueOf(p_position) + ","
					+ String.valueOf(sub_p_position) + ")";
		}

		return ausgabe;

	}
}
