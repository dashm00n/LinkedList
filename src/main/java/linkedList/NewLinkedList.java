package linkedList;

public class NewLinkedList {

	public static ListItem<ListItem<Character>> newListOfLists() {

		// Eigene kleine LinkedList
		ListItem<ListItem<Character>> p = new ListItem<ListItem<Character>>();
		ListItem<ListItem<Character>> p2 = new ListItem<ListItem<Character>>();
		ListItem<ListItem<Character>> p3 = new ListItem<ListItem<Character>>();

		ListItem<Character> tmp = new ListItem<Character>();
		ListItem<Character> tmp2 = new ListItem<Character>();
		ListItem<Character> tmp22 = new ListItem<Character>();
		ListItem<Character> tmp3 = new ListItem<Character>();
		ListItem<Character> tmp4 = new ListItem<Character>();
		ListItem<Character> tmp5 = new ListItem<Character>();
		ListItem<Character> tmp6 = new ListItem<Character>();
		ListItem<Character> tmp7 = new ListItem<Character>();

		p.key = tmp;
		tmp.key = 'a';
		tmp.next = tmp2;
		tmp2.key = 'b';
		tmp2.next = tmp22;
		tmp22.key = 'x';
//		tmp22.next = null;

		p.next = p2;
		p2.key = tmp3;

		tmp3.key = 'c';
		tmp3.next = tmp4;
		tmp4.key = 'd';

		p2.next = p3;
		p3.key = tmp5;

		tmp5.key = 'e';
		tmp5.next = tmp6;
		tmp6.key = 'f';
		tmp6.next = tmp7;
		tmp7.key = 'g';

		return p;
	}

	public static ListItem<ListItem<Character>> newListOfListsSmall() {

		// Eigene kleine LinkedList
		ListItem<ListItem<Character>> p = new ListItem<ListItem<Character>>();
		ListItem<ListItem<Character>> p2 = new ListItem<ListItem<Character>>();

		ListItem<Character> tmp = new ListItem<Character>();
		ListItem<Character> tmp2 = new ListItem<Character>();
		ListItem<Character> tmp3 = new ListItem<Character>();
		ListItem<Character> tmp4 = new ListItem<Character>();

		p.key = tmp;
		tmp.key = 'a';
		tmp.next = tmp2;
		tmp2.key = 'b';

		p.next = p2;
		p2.key = tmp3;

		tmp3.key = 'c';
		tmp3.next = tmp4;
		tmp4.key = 'd';

		return p;
	}

	public static ListItem<Character> newSimpleList() {

		ListItem<Character> tmp = new ListItem<Character>();
		ListItem<Character> tmp2 = new ListItem<Character>();
		ListItem<Character> tmp3 = new ListItem<Character>();
		ListItem<Character> tmp4 = new ListItem<Character>();
		ListItem<Character> tmp5 = new ListItem<Character>();
		ListItem<Character> tmp6 = new ListItem<Character>();
		ListItem<Character> tmp7 = new ListItem<Character>();

		tmp.key = 'a';
		tmp.next = tmp2;
		tmp2.key = 'b';
		tmp2.next = tmp3;
		tmp3.key = 'c';
		tmp3.next = tmp4;
		tmp4.key = '&';
		tmp4.next = tmp5;
		tmp5.key = 'e';
		tmp5.next = tmp6;
		tmp6.key = 'f';
		tmp6.next = tmp7;
		tmp7.key = 'g';

		return tmp;
	}

}
