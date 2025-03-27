package labs;

public class Lab_6 {
    public static void lab_6() {
        Queue queue = new Queue();

        queue.enqueue(2.1);
        queue.enqueue(2.1);
        queue.enqueue(5.3);


        queue.printQueue();

        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        queue.enqueue(4.9);

        queue.printQueue();

        double sum = queue.sumQueue();
        System.out.println("Сума елементів черги: " + sum);
    }
}
    class QueueItem {
        double data;
        QueueItem next;

        public QueueItem(double data) {
            this.data = data;
            this.next = null;
        }
    }

     class Queue {
        private QueueItem head;
        private QueueItem tail;

        public Queue() {
            this.head = null;
            this.tail = null;
        }

        public void enqueue(double data) {
            QueueItem newItem = new QueueItem(data);
            if (head == null) {
                head = newItem;
                tail = newItem;
                return;
            }
            tail.next = newItem;
            tail = newItem;
        }

        public double dequeue() {
            if (head == null) {
                throw new IllegalStateException("Черга порожня");
            }
            double data = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return data;
        }

        public double peek() {
            if (head == null) {
                throw new IllegalStateException("Черга порожня");
            }
            return head.data;
        }

        public void printQueue() {
            if (head == null) {
                System.out.println("Черга порожня");
                return;
            }
            StringBuilder sb = new StringBuilder("Черга: ");
            QueueItem current = head;
            while (current != null) {
                sb.append(current.data).append(" ");
                current = current.next;
            }
            System.out.println(sb.toString());
        }

        public double sumQueue() {
            if (head == null) {
                return 0;
            }
            double sum = 0;
            QueueItem current = head;
            while (current != null) {
                sum += current.data;
                current = current.next;
            }
            return sum;
        }
    }