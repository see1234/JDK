public class ArrayUtils {

    public static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] instanceof Number && arr2[i] instanceof Number) {

              if(((Number) arr1[i]).doubleValue() != ((Number) arr2[i]).doubleValue()) {
                  return false;
              }
            }
            else {
                if (!arr1[i].equals(arr2[i])) {
                    return false;
                }
            }
        }

        return true;
    }
}
