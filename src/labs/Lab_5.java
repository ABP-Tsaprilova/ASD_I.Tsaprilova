package labs;

class ListItem {
    int data;
    ListItem prev;
    ListItem next;

    public ListItem(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class Lab_5 {
    public static void lab_5() {
        ListItem head = new ListItem(1);
        ListItem item2 = new ListItem(2);
        ListItem item3 = new ListItem(3);
        ListItem item4 = new ListItem(4);
        ListItem item5 = new ListItem(5);

        head.next = item2;
        item2.prev = head;
        item2.next = item3;
        item3.prev = item2;
        item3.next = item4;
        item4.prev = item3;
        item4.next = item5;
        item5.prev = item4;

        ListItem p0 = item3;

        int k = 2;
        moveElement(head, p0, k);

        System.out.println("Список після переміщення:");
        printList(head);

        System.out.println("Перший елемент: " + findFirst(head).data);
        System.out.println("Останній елемент: " + findLast(head).data);
    }

    public static void moveElement(ListItem head, ListItem p0, int k) {
        if (p0 == null) {
            return;
        }

        ListItem current = p0;
        for (int i = 0; i < k; i++) {
            if (current.next == null) {
                break;
            }
            current = current.next;
        }

        if (p0 == current) {
            return;
        }

        if (p0.prev != null) {
            p0.prev.next = p0.next;
        } else {
            head = p0.next;
        }
        if (p0.next != null) {
            p0.next.prev = p0.prev;
        }

        if (current.next != null) {
            current.next.prev = p0;
        }
        p0.next = current.next;
        current.next = p0;
        p0.prev = current;
    }

    public static ListItem findFirst(ListItem head) {
        if (head == null) {
            return null;
        }
        while (head.prev != null) {
            head = head.prev;
        }
        return head;
    }

    public static ListItem findLast(ListItem head) {
        if (head == null) {
            return null;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    public static void printList(ListItem head) {
        ListItem current = findFirst(head); // Знаходимо перший елемент
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        lab_5();
    }
}