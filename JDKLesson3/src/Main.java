
public class Main {
    public static void main(String[] args) {

        Task3();



    }
    public static void Task1() {
        System.out.println(Calculator.sum((int) 152435324583284573428752.324312434122,(double)3));
        System.out.println(Calculator.divide((short) 5,(long) 2));
        System.out.println(Calculator.subtraction((byte) 332,(byte) 2));
        System.out.println(Calculator.multiply((long) 234,(short) 3));

    }
    public static void Task2() {
        Integer[] arr1 = {1, 2, 3, 4, 5};
        Integer[] arr2 = {1, 2, 3, 4, 5};
        System.out.println(ArrayUtils.compareArrays(arr1, arr2));

        String[] arr3 = {"привет", " ", "мир"};
        String[] arr4 = {"привет", " ", "мир"};
        System.out.println(ArrayUtils.compareArrays(arr3, arr4));

        Double[] arr5 = {1.5, 2.5, 3.5};
        Double[] arr6 = {1.5, 2.5, 3.5};
        System.out.println(ArrayUtils.compareArrays(arr5, arr6));

        Integer[] arr7 = {1, 2, 3};
        Double[] arr8 = {1.0, 2.0, 3.0};
        System.out.println(ArrayUtils.compareArrays(arr7, arr8));

        Integer[] arr9 = {1, 2, 3};
        Integer[] arr10 = {1, 2, 5};
        System.out.println(ArrayUtils.compareArrays(arr9, arr10));
    }

    public static void Task3() {

        Pair<Integer, String> p1 = new Pair<Integer, String>(1, "П");
        Pair<String, Double> p2 = new Pair<String, Double>("А", 2.0);
        System.out.println(p1);
        System.out.println(p1.getFirst());
        System.out.println(p1.getSecond());
        System.out.println(p2);
        System.out.println(p2.getFirst());
        System.out.println(p2.getSecond());

    }
}