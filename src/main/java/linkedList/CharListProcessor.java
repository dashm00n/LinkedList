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
		int pointerPosition = 0;

		// run through list of lists
		for (ListItem<ListItem<Character>> p = listOfLists; p != null; p = p.next) {
			int subPointerPosition = 0;

			ListItem<Character> subHead = new ListItem<Character>();
			subHead = p.key;

			// run through sublist
			for (ListItem<Character> subP = subHead; subP != null; subP = subP.next) {

				if (subP.key == null) {
					throw new ListOfListsExceptions(pointerPosition, subPointerPosition, true);
				} else if (subP.key == sentinal) {
					throw new ListOfListsExceptions(pointerPosition, subPointerPosition, false);
				}

				// connect last and first element of current and next sublist with a sentinal
				if (subP.next == null && p.next != null) {
					ListItem<Character> sentinalItem = new ListItem<Character>();
					sentinalItem.key = sentinal;
					subP.next = sentinalItem;
					sentinalItem.next = p.next.key;
					break;
				}

				subPointerPosition++;

			}
			pointerPosition++;

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
		int pointerPosition = 0;

		// run through list of lists
		for (ListItem<ListItem<Character>> p = listOfLists; p != null; p = p.next) {
			int subPointerPosition = 0;

			ListItem<Character> subHead = new ListItem<Character>();
			subHead = p.key;

			// run through sublists
			for (ListItem<Character> subP = subHead; subP != null; subP = subP.next) {

				if (subP.key == null) {
					throw new ListOfListsExceptions(pointerPosition, subPointerPosition, true);
				} else if (subP.key == sentinal) {
					throw new ListOfListsExceptions(pointerPosition, subPointerPosition, false);
				}

				list.key = subP.key;

				// last item reached
				if (subP.next == null && p.next == null) {
					break;
				}
				// connect last and first element of current and next sublist with a sentinal
				else if (subP.next == null && p.next != null) {
					list.key = sentinal;
					ListItem<Character> nextListItem = new ListItem<Character>();
					list.next = nextListItem;
					list = list.next;
					break;
				} else {
					ListItem<Character> nextItem = new ListItem<Character>();
					list.next = nextItem;
					list = list.next;
				}

				subPointerPosition++;

			}
			pointerPosition++;

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

		int pointerPosition = 0;
		int subPointerPosition = 0;
		Character sentinal = '&';
		Character nextKey;

		// check if list is empty
		pred.test(list.key, pointerPosition, subPointerPosition);

		// TODO: handle sentinals in first position

		ListItem<ListItem<Character>> head = new ListItem<ListItem<Character>>();
		ListItem<ListItem<Character>> p = new ListItem<ListItem<Character>>();
		head.key = list;
		p = head;

		while (list.next != null) {

			nextKey = list.next.key;

			if (pred.test(nextKey, pointerPosition, subPointerPosition)) {

				ListItem<Character> nextHead = new ListItem<Character>();
				nextHead = list.next.next;
				list.next = null;

				// handle consecutive sentinals
				while (nextHead.key == sentinal) {
					ListItem<ListItem<Character>> emptyToplevelHead = new ListItem<ListItem<Character>>();
					p.next = emptyToplevelHead;
					p = p.next;
					nextHead = nextHead.next;
					if (nextHead == null) {
						ListItem<ListItem<Character>> lastEmptyToplevelHead = new ListItem<ListItem<Character>>();
						p.next = lastEmptyToplevelHead;
						p = p.next;
						return head;
					}
				}

				list = nextHead;

				ListItem<ListItem<Character>> nextToplevelHead = new ListItem<ListItem<Character>>();
				p.next = nextToplevelHead;
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

		int pointerPosition = 0;
		int subPointerPosition = 0;
		Character sentinal = '&';
		Character nextKey;

		// check if list is empty
		pred.test(list.key, pointerPosition, subPointerPosition);

		// TODO: handle sentinals in first position

		ListItem<ListItem<Character>> head = new ListItem<ListItem<Character>>();
		ListItem<ListItem<Character>> p = new ListItem<ListItem<Character>>();
		ListItem<Character> subP = new ListItem<Character>();
		subP.key = list.key;
		head.key = subP;
		p = head;

		while (list.next != null) {
			nextKey = list.next.key;
			ListItem<Character> nextItem = new ListItem<Character>();

			if (pred.test(nextKey, pointerPosition, subPointerPosition)) {
				// jump over sentinal item and create new list
				ListItem<ListItem<Character>> nextToplevelHead = new ListItem<ListItem<Character>>();

				// handle consecutive sentinals
				while (list.next.next != null && list.next.next.key == sentinal) {
					ListItem<ListItem<Character>> emptyToplevelHead = new ListItem<ListItem<Character>>();
					p.next = emptyToplevelHead;
					p = p.next;
					list = list.next;
				}

				try {
					nextItem.key = list.next.next.key;
				} catch (NullPointerException e) {
					p.next = nextToplevelHead;
					return head;
				}

				p.next = nextToplevelHead;
				p.next.key = nextItem;
				p = p.next;
				subP = nextItem;
				list = list.next.next;

			} else {
				subP.next = nextItem;
				nextItem.key = nextKey;
				subP = subP.next;
				list = list.next;
			}
		}
		return head;
	}

	/**
	 * Function to reverse list of lists inplace (iteratively). Sublists are
	 * reversed in the same manner.
	 * 
	 * @param list head of the original list of lists
	 * @return returns the new head (former end) of the list of lists
	 */
	public static ListItem<ListItem<Character>> reverseListOfListsInPlaceIteratively(
			ListItem<ListItem<Character>> list) {

		ArrayList<ListItem<Character>> arrayOfListItems = new ArrayList<ListItem<Character>>();
		ArrayList<ListItem<ListItem<Character>>> arrayOfLists = new ArrayList<ListItem<ListItem<Character>>>();

		// run through ListOfLists, reverse sublists
		for (ListItem<ListItem<Character>> p = list; p != null; p = p.next) {

			arrayOfListItems.clear();
			// store sublist
			for (ListItem<Character> subP = p.key; subP != null; subP = subP.next) {
				arrayOfListItems.add(subP);
			}

			ListItem<Character> newSubHead = reverseListInPlace(arrayOfListItems);
			p.key = newSubHead;
		}

		// store list of lists
		for (ListItem<ListItem<Character>> p = list; p != null; p = p.next) {
			arrayOfLists.add(p);
		}

		ListItem<ListItem<Character>> newHead = reverseListInPlace(arrayOfLists);
		return newHead;
	}

	public static <T> ListItem<T> reverseListInPlace(ArrayList<? extends ListItem<T>> arr) {
		int sizeListOfLists = arr.size();
		ListItem<T> newHead = arr.get(sizeListOfLists - 1);
		ListItem<T> newPointer = newHead;

		// reverse list of lists
		for (int i = sizeListOfLists - 1; i != 0; i--) {
			newPointer.next = arr.get(i - 1);
			newPointer = (ListItem<T>) newPointer.next;
		}
		newPointer.next = null;
		return newHead;
	}

	/**
	 * Function to reverse a linked list of linked lists in place (recursively). The
	 * simple linked lists in the list of lists are also reversed in the same way.
	 * 
	 * @param head head of the list of lists
	 * @return returns the new head (former end) of the list of lists
	 */
	public static ListItem<ListItem<Character>> reverseListOfListsInPlaceRecursively(
			ListItem<ListItem<Character>> head) {

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
		ListItem<Character> subP = new ListItem<Character>();
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
					subP.next = null;
				} else {
					for (int size = line.length(); size > 0; size--) {
						value = line.charAt(i);
						subP.key = value;

						// Kopf der Oberliste mit Kopf der Unterliste verbinden.
						if (size == line.length()) {
							p.key = subP;
						}

						// Neues Listenelement, falls das Ende noch nicht erreicht wurde.
						if (size > 1) {
							ListItem<Character> tmp = new ListItem<Character>();
							subP.next = tmp;
							subP = subP.next;
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
				subP = tmp;

				// Erstellen der nächsten Liste mithilfer eines Hilfsobjektes;
				ListItem<ListItem<Character>> tmp2 = new ListItem<ListItem<Character>>();
				p.next = tmp2;
				p = p.next;
				p.key = subP;
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
