package testers;



import edu.uprm.cse.datastructures.cardealer.util.AppointmentPositionalList;
import edu.uprm.cse.datastructures.cardealer.util.Position;

public class PositionalListTester {
	 
    public static void main(String[] args) {
        AppointmentPositionalList<Integer> list = new AppointmentPositionalList<>();
       
        System.out.println(list.isEmpty() + " | should be true");
        for (int i = 0; i < 10; i++) {
            list.addLast(i);
        }
        System.out.println(list.isEmpty() + " | should be false");
        System.out.println(list.size() + " | should be 10");
       
        Position<Integer> first = list.first();
        Position<Integer> last = list.last();
       
        System.out.println(first.getElement() + " | should be 0");
        System.out.println(last.getElement() + " | should be 9");
       
        System.out.println(list.before(last).getElement() + " | should be 8");
        System.out.println(list.after(first).getElement() + " | should be 1");
       
       
        list.addBefore(first, -2);
        list.addBefore(first, -1);
        list.addAfter(last, 11);
        list.addAfter(last, 10);
       
        printList(list);
        System.out.println("should be : -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11");
       
        list.set(first, 1000);
        list.remove(last);
        printPositions(list);
        System.out.println("should be : -2, -1, 1000, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11");
       
    }
 
    private static <E> void printPositions(AppointmentPositionalList<E> list) {
        Iterable<Position<E>> positions = list.positions();
       
        for(Position<E> p : positions) {
            System.out.print(p.getElement() + ", ");
        }
        System.out.println("");
       
    }
 
    private static <E> void printList(AppointmentPositionalList<E> list) {
         for(E e : list) {
             System.out.print(e + ", ");
         }
         System.out.println("");
    }
 
}
