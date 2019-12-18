package study.qi.com.algorithm.sort;

public class Test {
    public static char[] sortChar(char[] chars) {
        int i = 0;
        int j = chars.length - 1;

        while (i != j) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
//
                i++;
            } else {
// swap i, j--
                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
                j--;
            }
        }

        for (; j < chars.length; j++) {
            if (chars[j] >= '0' && chars[j] <= '9') {
// swap i, j
                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
                i++;
            }
        }
        return chars;
    }

    public static void main(String[] args) {
        char[] chars = {'D', 'a', 'F', 'B', 'c', 'A', 'z'};
       sortChar(chars);
        StringBuilder stringBuilder2 = new StringBuilder();
        for (char index : chars) {
            stringBuilder2.append(index + ",");
        }
        System.out.print(stringBuilder2.toString());


    }


}
