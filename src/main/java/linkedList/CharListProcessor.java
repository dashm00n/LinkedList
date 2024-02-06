package linkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CharListProcessor {

	/**
	 * Function to flatten a linked list of linked lists <b>inplace</b>. The same
	 * objects are used for the flat list.
	 * 
	 * @param listOfLists head of listOfLists (first linked list as key and pointer
	 *                    to next linked list)
	 * @return returns the first item of the flat linked list
	 * @throws ListOfListsExceptions
	 */
	public static ListItem<Character> makeFlatListInPlace(ListItem<ListItem<Character>> listOfLists)
			throws ListOfListsExceptions {

		Character sentinal = '&';
		int p_position = 0;

		// run through list of lists
		for (ListItem<ListItem<Character>> p = listOfLists; p != null; p = p.next) {
			int sub_p_position = 0;

			ListItem<Character> sub_head = new ListItem<Character>();
			sub_head = p.key;

			// run through sublist
			for (ListItem<Character> sub_p = sub_head; sub_p != null; sub_p = sub_p.next) {

				if (sub_p.key == null) {
					throw new ListOfListsExceptions(p_position, sub_p_position, true);
				} else if (sub_p.key == sentinal) {
					throw new ListOfListsExceptions(p_position, sub_p_position, false);
				}

				// connect last and first element of current and next sublist with a sentinal
				if (sub_p.next == null && p.next != null) {
					ListItem<Character> sentinal_item = new ListItem<Character>();
					sentinal_item.key = sentinal;
					sub_p.next = sentinal_item;
					sentinal_item.next = p.next.key;
					break;
				}

				sub_p_position++;

			}
			p_position++;

		}

		return listOfLists.key;
	}

	/**
	 * Function to flatten a linked list of linked lists <b>as copy</b>.
	 * 
	 * @param listOfLists head of listOfLists (first linked list as key and pointer
	 *                    to next linked list)
	 * @return head of the flat list
	 * @throws ListOfListsExceptions
	 */
	public static ListItem<Character> makeFlatListAsCopy(ListItem<ListItem<Character>> listOfLists)
			throws ListOfListsExceptions {

		// head of new list and first list item
		ListItem<Character> head = new ListItem<Character>();
		ListItem<Character> list = new ListItem<Character>();
		head = list;

		Character sentinal = '&';
		int p_position = 0;

		// run through list of lists
		for (ListItem<ListItem<Character>> p = listOfLists; p != null; p = p.next) {
			int sub_p_position = 0;

			ListItem<Character> sub_head = new ListItem<Character>();
			sub_head = p.key;

			// run through sublists
			for (ListItem<Character> sub_p = sub_head; sub_p != null; sub_p = sub_p.next) {

				if (sub_p.key == null) {
					throw new ListOfListsExceptions(p_position, sub_p_position, true);
				} else if (sub_p.key == sentinal) {
					throw new ListOfListsExceptions(p_position, sub_p_position, false);
				}

				list.key = sub_p.key;

				// last item reached
				if (sub_p.next == null && p.next == null) {
					break;
				}
				// connect last and first element of current and next sublist with a sentinal
				else if (sub_p.next == null && p.next != null) {
					list.key = sentinal;
					ListItem<Character> next_list_item = new ListItem<Character>();
					list.next = next_list_item;
					list = list.next;
					break;
				} else {
					ListItem<Character> next_item = new ListItem<Character>();
					list.next = next_item;
					list = list.next;
				}

				sub_p_position++;

			}
			p_position++;

		}

		return head;
	}

	/**
	 * Function to make a linked list of linked lists <b>in place</b>.
	 * 
	 * @param list head of the simple linked list
	 * @param pred object with testfunction for valid characters (cases: sentinal or
	 *             null)
	 * @return list of lists
	 * @throws ListOfListsExceptions
	 */
	public static ListItem<ListItem<Character>> makeListOfListsInPlace(ListItem<Character> list,
			ValidCharacterTest pred) throws ListOfListsExceptions {

		int p_position = 0;
		int sub_p_position = 0;
		Character sentinal = '&';
		Character next_key;

		// check if list is empty
		pred.test(list.key, p_position, sub_p_position);

		// TODO: handle sentinals in first position

		ListItem<ListItem<Character>> head = new ListItem<ListItem<Character>>();
		ListItem<ListItem<Character>> p = new ListItem<ListItem<Character>>();
		head.key = list;
		p = head;

		while (list.next != null) {

			next_key = list.next.key;

			if (pred.test(next_key, p_position, sub_p_position)) {

				ListItem<Character> next_head = new ListItem<Character>();
				next_head = list.next.next;
				list.next = null;

				// handle consecutive sentinals
				while (next_head.key == sentinal) {
					ListItem<ListItem<Character>> empty_toplevel_head = new ListItem<ListItem<Character>>();
					p.next = empty_toplevel_head;
					p = p.next;
					next_head = next_head.next;
					if (next_head == null) {
						ListItem<ListItem<Character>> last_empty_toplevel_head = new ListItem<ListItem<Character>>();
						p.next = last_empty_toplevel_head;
						p = p.next;
						return head;
					}
				}

				list = next_head;

				ListItem<ListItem<Character>> next_toplevel_head = new ListItem<ListItem<Character>>();
				p.next = next_toplevel_head;
				p.next.key = list;
				p = p.next;
			}

			else {
				list = list.next;
			}

		}

		return head;

	}

	/**
	 * Function to make a linked list of linked lists <b>as copy</b>.
	 * 
	 * @param list head of the simple linked list
	 * @param pred object with testfunction for valid characters (cases: sentinal or
	 *             null)
	 * @return list of lists
	 * @throws ListOfListsExceptions
	 */
	public static ListItem<ListItem<Character>> makeListOfListsAsCopy(ListItem<Character> list, ValidCharacterTest pred)
			throws ListOfListsExceptions {

		int p_position = 0;
		int sub_p_position = 0;
		Character sentinal = '&';
		Character next_key;

		// check if list is empty
		pred.test(list.key, p_position, sub_p_position);

		// TODO: handle sentinals in first position

		ListItem<ListItem<Character>> head = new ListItem<ListItem<Character>>();
		ListItem<ListItem<Character>> p = new ListItem<ListItem<Character>>();
		ListItem<Character> sub_p = new ListItem<Character>();
		sub_p.key = list.key;
		head.key = sub_p;
		p = head;

		while (list.next != null) {
			next_key = list.next.key;
			ListItem<Character> next_item = new ListItem<Character>();

			if (pred.test(next_key, p_position, sub_p_position)) {
				// jump over sentinal item and create new list
				ListItem<ListItem<Character>> next_toplevel_head = new ListItem<ListItem<Character>>();

				// handle consecutive sentinals
				while (list.next.next != null && list.next.next.key == sentinal) {
					ListItem<ListItem<Character>> empty_toplevel_head = new ListItem<ListItem<Character>>();
					p.next = empty_toplevel_head;
					p = p.next;
					list = list.next;
				}

				try {
					next_item.key = list.next.next.key;
				} catch (NullPointerException e) {
					p.next = next_toplevel_head;
					return head;
				}

				p.next = next_toplevel_head;
				p.next.key = next_item;
				p = p.next;
				sub_p = next_item;
				list = list.next.next;

			} else {
				sub_p.next = next_item;
				next_item.key = next_key;
				sub_p = sub_p.next;
				list = list.next;
			}
		}
		return head;
	}

	/**
	 * Notizen zur H7
	 * 
	 * Parameter: ListItem<ListItem<Character>>
	 * 
	 * Rückgabe: ListItem<ListItem<Character>> Verweis auf den Kopf der umgedrehten
	 * Hautpliste
	 * 
	 * Aufgabe: Umdrehen der Hauptliste (inplace -> wiederverwenden der Objekte)
	 * Dabei sollen auch die einzelnen Hauptlisten selbst umgedreht werden. key der
	 * Hautplisten zeigt dann auf das ursprüngliche Ende der Listen.
	 * 
	 */
	public static ListItem<ListItem<Character>> reverseListOfListsInPlaceIteratively(
			ListItem<ListItem<Character>> list) {

		// Erstellen der Notwendigen ArrayLists
		ArrayList<ListItem<Character>> sub_buffer = new ArrayList<ListItem<Character>>();
		ArrayList<ListItem<ListItem<Character>>> buffer = new ArrayList<ListItem<ListItem<Character>>>();

		// Durchlaufen der Oberliste und umdrehen der Unterlisten. Jedem p.key wird nun
		// der Start der umgedrehten Liste hinzugefügt
		for (ListItem<ListItem<Character>> p = list; p != null; p = p.next) {

			sub_buffer.clear();

			// Rein in eine Unterliste
			// Objekte in eine ArrayList einfügen
			for (ListItem<Character> sub_p = p.key; sub_p != null; sub_p = sub_p.next) {
				sub_buffer.add(sub_p);
			}

			// Neuen Kopf initialisieren (letzte Stelle des Arrays)
			int size = sub_buffer.size();
			ListItem<Character> newSubHead = sub_buffer.get(size - 1);

			// Rückwärts Durchlaufen des Arrays
			for (ListItem<Character> new_pointer = newSubHead; size != 0; size--) {
				new_pointer = sub_buffer.get(size - 1);
				try {
					new_pointer.next = sub_buffer.get(size - 2);
				} catch (IndexOutOfBoundsException e) {
					new_pointer.next = null;
				}
			}
			p.key = newSubHead;
		}

		// Durchlaufen der Oberliste. Umdrehen der Zuordnung. Erste Unterliste zuletzt
		// und andersrum.
		for (ListItem<ListItem<Character>> p = list; p != null; p = p.next) {
			buffer.add(p);
		}

		// Neuen Kopf der Oberliste zuweisen (-> vormals letztes Element).
		int size = buffer.size();
		ListItem<ListItem<Character>> newHead = buffer.get(size - 1);

		// Rückwärts Durchlaufen des Arrays
		for (ListItem<ListItem<Character>> new_pointer = newHead; size != 0; size--) {
			new_pointer = buffer.get(size - 1);
			try {
				new_pointer.next = buffer.get(size - 2);
			} catch (IndexOutOfBoundsException e) {
				new_pointer.next = null;
			}

			new_pointer = new_pointer.next;
		}

		return newHead;
	}

	/**
	 * Function to reverse a linked list of linked lists in place (recursively).
	 * The simple linked lists in the list of lists are also reversed in the same way.
	 * 
	 * @param head head of the list of lists
	 * @return returns the new head (former end) of the list of lists
	 */
	public static ListItem<ListItem<Character>> reverseListOfListsInPlaceRecursively(ListItem<ListItem<Character>> head) {
		
		if (head.next == null || head == null) {
			return head;
		}
		
		ListItem<ListItem<Character>> newHead = reverseListOfListsInPlaceRecursively(head.next);
		
		head.next.next = head;
		head.next = null;
		
		newHead.key = reverseListInPlaceRecursivley(newHead.key);
		
		return newHead;
	}
	
	/**
	 * Function to reverse a simple linked list in place (recursively).
	 * 
	 * @param head head of the linked list to be reversed
	 * @return returns the new head (former end) of the linked list
	 */
	public static ListItem<Character> reverseListInPlaceRecursivley(ListItem<Character> head) {

		if (head.next == null || head == null) {
			return head;
		}

		ListItem<Character> newHead = reverseListInPlaceRecursivley(head.next);
		
		head.next.next = head;
		head.next = null;

		return newHead;

	}

	/**
	 * Notizen zur H9
	 * 
	 * Parameter: BufferedReader
	 * 
	 * Rückgabe: ListItem<ListItem<Character>>
	 * 
	 * Aufgabe: Auslesen der Quelle, die mit dem BufferedReader verbunden ist.
	 * 
	 * Dabei ist für die Datei immer gegeben: Entweder die Datei hat keine Zeile
	 * mehr zum Einlesen (line == null), oder:
	 * 
	 * AktuelleZeile: int AnzahlderZeilen NächstenZeilen: Zeichen der jeweiligen
	 * Einzellisten. Reihenfolge aufsteigend.
	 * 
	 * Es sind nur Kleinbuchstaben als Zeichen.
	 * 
	 */
	public static ListItem<ListItem<Character>> readListOfListsOfChars(BufferedReader input) {

		/*
		 * Auslesen der Zeile Null oder Int? Null: return null; Int: Auslesen der
		 * Int-nächsten Zeilen Aufschlüsseln der Zeile in Zeichen -> Eine Liste ->
		 * nächste Zeile, nächste Liste
		 */
		int start;
		try {
			start = Integer.parseInt(input.readLine());
		} catch (IOException | NumberFormatException | NullPointerException e) {
			return null;
		}

		// Erstellen der benötigten Listenelemente
		ListItem<ListItem<Character>> head = new ListItem<ListItem<Character>>();
		ListItem<ListItem<Character>> p = new ListItem<ListItem<Character>>();
		ListItem<Character> sub_p = new ListItem<Character>();
		head = p;

		// Defaultwert für line initialisieren.
		String line = "a";

		// Wenn das Ende nicht erreicht ist -> Zeile in Character zerlegen und in eine
		// LinkedList schreiben.
		int counter = 1;
		while (line != null) {

			int i = 0;
			char value;

			try {
				line = input.readLine();
			} catch (IOException e) {
				return null;
			}

			// Einzelne Zeichen der Zeile auslesen und in eine LinkedList schreiben.
			if (line != null) {
				// Prüfen ob der eingelesen String leer ist. -> leere Unterliste.
				if (line.length() == 0) {
					sub_p.next = null;
				} else {
					for (int size = line.length(); size > 0; size--) {
						value = line.charAt(i);
						sub_p.key = value;

						// Kopf der Oberliste mit Kopf der Unterliste verbinden.
						if (size == line.length()) {
							p.key = sub_p;
						}

						// Neues Listenelement, falls das Ende noch nicht erreicht wurde.
						if (size > 1) {
							ListItem<Character> tmp = new ListItem<Character>();
							sub_p.next = tmp;
							sub_p = sub_p.next;
						}
						i++;
					}
				}
			}

			// Überprüfen ob das Ende der eingelesenen Datei erreicht ist.
			// Falls nein -> neue Objekte für neue Liste erstellen.
			if (start > counter) {
				// Ende der aktuellen Unterliste -> Neues Objekt ohne Referenz zu einem anderen
				// Objekt.
				ListItem<Character> tmp = new ListItem<Character>();
				sub_p = tmp;

				// Erstellen der nächsten Liste mithilfer eines Hilfsobjektes;
				ListItem<ListItem<Character>> tmp2 = new ListItem<ListItem<Character>>();
				p.next = tmp2;
				p = p.next;
				p.key = sub_p;
			}
			counter++;
		}

		return head;
	}

	/**
	 * Notizen zur H10
	 * 
	 * Aufgabe: Tests für die 7 Methoden dieser Klasse (H3-H9)
	 * 
	 * Genauer: Per Hand mehrere (aussagekräftige) Testbeispiele erstellen.
	 * 
	 * Verschiedene Randfälle: Leere Liste von Listen, nichtleere Liste von leeren
	 * Listen...
	 * 
	 * Exceptions: Auch Fälle wo Exceptions geworfen werden. Dabei soll getestet
	 * werden, ob die richtige Message übergeben wird.
	 * 
	 * AsCopy: Test ob die Eingabeliste wirklich nicht verändert wird.
	 * 
	 * Lambda- Ausdruck: Mindestens einmal einen Lambda-Ausdruck statt der Referenz
	 * auf ein Objekt von PredicatWithException.
	 * 
	 */

}
