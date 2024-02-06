package linkedList;

public class Main {
	public static void main(String[] args) throws ListOfListsExceptions {
				
		/*
		 * list of lists
		 */
//		ListItem<ListItem<Character>> head = NewLinkedList.newListOfLists();
//		Utilities.showLinkedListOfLinkedLists(head, true);
//		Utilities.showLinkedListOfLinkedLists(head, false);
//		System.out.println("\n");
		
		/*
		 * linked list
		 */
		ListItem<Character> head_simple_list = NewLinkedList.newSimpleList();
		Utilities.showLinkedList(head_simple_list, true);
		Utilities.showLinkedList(head_simple_list, false);
		System.out.println("\n");

		/*
		 * H3 list of lists -> simple linked list in place
		 */
//		ListItem<Character> head_flat_list = CharListProcessor.makeFlatListInPlace(head);
//		Utilities.showLinkedList(head_flat_list, false);
//		Utilities.showLinkedList(head_flat_list, true);
//		System.out.println("\n");

		/*
		 * H4 list of list -> simple linked list as copy
		 */
//		ListItem<Character> head_flat_list_copy = CharListProcessor.makeFlatListAsCopy(NewLinkedList.newListOfLists());
//		Utilities.showLinkedList(head_flat_list_copy, false);
//		Utilities.showLinkedList(head_flat_list_copy, true);
//		System.out.println("\n");
		
		/*
		 * H5 & H6: simple linked list -> list of lists
		 */

//		ValidCharacterTest predicate = new ValidCharacterTest(); 
//
//		System.out.println("\n\nAs Copy:");
//		ListItem<ListItem<Character>> head_list_of_lists_copy = CharListProcessor.makeListOfListsAsCopy(head_simple_list, predicate);
//		Utilities.showLinkedListOfLinkedLists(head_list_of_lists_copy, false);
//		Utilities.showLinkedListOfLinkedLists(head_list_of_lists_copy, true);
//		
//		System.out.println("In Place:");
//		ListItem<ListItem<Character>> head_list_of_lists_copy2 = CharListProcessor.makeListOfListsInPlace(head_simple_list, predicate);
//		Utilities.showLinkedListOfLinkedLists(head_list_of_lists_copy2, false);
//		Utilities.showLinkedListOfLinkedLists(head_list_of_lists_copy2, true);
		
		/*
		 * H7 & H8: reverse list of lists (iterative and recursive)
		 */
		
//		ListItem<ListItem<Character>> reversed_list_inplace = CharListProcessor.reverseListOfListsInPlaceIteratively(head);
//		Utilities.showLinkedListOfLinkedLists(reversed_list_inplace, false);
//		Utilities.showLinkedListOfLinkedLists(reversed_list_inplace, true);
//		System.out.println("\n");
//
//		CharListProcessor Processor = new CharListProcessor(); 
//		
//		ListItem<ListItem<Character>> reversed_list_copy = Processor.reverseListOfListsInPlaceRecursively(NewLinkedList.newListOfLists());
//		Utilities.showLinkedListOfLinkedLists(reversed_list_copy, false);
//		Utilities.showLinkedListOfLinkedLists(reversed_list_copy, true);
//		System.out.println("\n");
		
//		ListItem<Character> reversedSimpleList = CharListProcessor.reverseListInPlaceRecursivley(head_simple_list);
//		Utilities.showLinkedList(reversedSimpleList, false);
//		Utilities.showLinkedList(reversedSimpleList, true);
		
		ListItem<ListItem<Character>> reversed_list_copy = CharListProcessor.reverseListOfListsInPlaceRecursively(NewLinkedList.newListOfListsSmall());
		Utilities.showLinkedListOfLinkedLists(reversed_list_copy, false);
		Utilities.showLinkedListOfLinkedLists(reversed_list_copy, true);
		System.out.println("\n");



		
	}
}
