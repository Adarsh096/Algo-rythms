package expertalgo.twonumsum.src;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class TwoNumSum {
    public static void main(String[] args) {
        System.out.println("two num sum problem.");

        System.out.println("Bruteforce way: 0(n^2)");
        findSumPair1(new int[]{1, 3, 4, 5},17);

        System.out.println("Unsorted Array Approach: 0(n)");
        findSumPair2(new int[]{1, 3, 4, 5},97);

        System.out.println("Sorted Array Approach: 0(n.log(n))");
        findSumPair3(new int[]{11, 3, 2, 5},14);
    }

    static void findSumPair1(int[] array, int sumVal){
        // Bruteforce
        for(int i=0; i<array.length; i++){
            for(int j=i+1; j<array.length; j++){
                if(array[i]+array[j]==sumVal){
                    System.out.printf("%d and %d have sum = %d\n",array[i], array[j],sumVal);
                    break;
                }
            }
        }
    }
    static void findSumPair2(int[] array, int sumVal){
        // unsorted array approach:
        Set<Integer> visitedNums = new HashSet<>(array.length);//fast access data-structure

        for(int i=0; i<array.length; i++){
            int diff = sumVal - array[i];
            if(visitedNums.contains(diff)){
                System.out.printf("%d and %d have sum = %d\n",array[i], diff,sumVal);
                break;
            }
            visitedNums.add(array[i]);
        }
    }
    static void findSumPair3(int[] array, int sumVal){
        //sorting the array:
        Arrays.sort(array);
        int leftPtr = 0, rightPtr = array.length-1;
        while(leftPtr<rightPtr){
            int ourSum = array[leftPtr]+array[rightPtr];
            if(ourSum==sumVal) {
                System.out.printf("%d and %d have sum = %d\n", array[leftPtr], array[rightPtr], sumVal);
                break;
            } else if (ourSum>sumVal) {
                rightPtr--;
            }else {
                leftPtr++;
            }
        }
    }
}
