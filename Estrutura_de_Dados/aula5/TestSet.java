public class TestSet {
   public static void main(String[] args) {
      IntSet s = new MyIntSet(); // Chama o construtor padrão

      s.clear();
      System.out.println(s.size());      // 0

      System.out.println(s.add(1));      // true
      System.out.println(s.add(5));      // true
      System.out.println(s.add(7));      // true
      System.out.println(s.add(1));      // false
      System.out.println(s.size());      // 3

      System.out.println(s.remove(5));   // true
      System.out.println(s.remove(5));   // false
      System.out.println(s.size());      // 2

      System.out.println(s.contains(1)); // true
      System.out.println(s.contains(2)); // false

      s.clear();
      System.out.println(s.size());      // 0
   }
}
