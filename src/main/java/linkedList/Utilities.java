package linkedList;

public class Utilities {

	public static void testPrint() {
		System.out.println("ich bin eine Testfunktion");
	}

	// Durchlaufen LinkedList, Rückgabe der Objekte in ArrayList:
	public static void showLinkedList(ListItem<Character> head, boolean print_values) {

		if (print_values == true) {
			for (ListItem<Character> sub_p = head; sub_p != null; sub_p = sub_p.next) {
				System.out.println(sub_p.key);
			}
		} else {
			for (ListItem<Character> sub_p = head; sub_p != null; sub_p = sub_p.next) {
				System.out.println(sub_p);
			}
		}
	}

	/**
	 * Function to show a linked list of linked lists
	 * 
	 * @param HEAD         first element of list of list
	 * @param print_values whether to print the values of the items or the object id
	 */
	public static void showLinkedListOfLinkedLists(ListItem<ListItem<Character>> HEAD, boolean print_values) {

		if (print_values == true) {
			for (ListItem<ListItem<Character>> p = HEAD; p != null; p = p.next) {
				System.out.println("\n**neue Einzelliste**");
				if (p != null && p.key == null) {
					System.out.println(p.key);
				} else {
					for (ListItem<Character> sub_p = p.key; sub_p != null; sub_p = sub_p.next) {
						System.out.println(sub_p.key);
					}
				}
			}
		} else {
			for (ListItem<ListItem<Character>> p = HEAD; p != null; p = p.next) {
				System.out.println("\n**neue Einzelliste**");
				if (p != null && p.key == null) {
					System.out.println(p);
				} else {
					for (ListItem<Character> sub_p = p.key; sub_p != null; sub_p = sub_p.next) {
						System.out.println(sub_p);
					}
				}
			}
		}
	}
}
