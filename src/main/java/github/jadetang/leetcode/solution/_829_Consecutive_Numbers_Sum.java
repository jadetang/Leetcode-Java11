package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.IntStream.Builder;
import org.junit.jupiter.api.Test;

public class _829_Consecutive_Numbers_Sum {


    @Test
    void test() {
        assertEquals(3, consecutiveNumbersSum(9));
        assertEquals(4, consecutiveNumbersSum(15));
    }

    @Test
    void test2() {
        assertEquals(1, consecutiveNumbersSum(8));
    }

    @Test
    void test3() {
        assertEquals(4, consecutiveNumbersSum(15));
    }

    @Test
    void test4() {
        assertEquals(2, consecutiveNumbersSum(866));
    }


    @Test
    void test5() {
        assertEquals(8, consecutiveNumbersSum(93003));
    }

    public int consecutiveNumbersSum(int n) {
        Builder i = IntStream.builder();
        int ans = 0;
        for (int j = n; j >= 0; j--) {
            int b = -1;
            int a = 1;
            int c = -j * j - j + 2 * n;
            int square = b * b - 4 * a * c;
            if (isPerfectSquare(square)) {
         //   if (is_square(square)) {
                int root = (int)Math.sqrt(square);
                int x1 = (1 - root) / 2;
                int x2 = (1 + root) / 2;
                if ((x1 > 0 && x1 != j) || (x2 > 0 && x2 != j)) {
                    System.out.println("x1:" + x1 + ", x2:" + x2 + "," + j + ",square:" + square + "," + root);
                    ans++;
                }
            }
        }
        return ans + 1;
    }

    public boolean isPerfectSquare(int num) {
        if (num == 0) {
            return false;
        }
        int l = 1, r = num;

        while( l <= r){
            int mid =  ( l + r) >>> 1;
            long guess = (long)mid*(long)mid;
            if ( guess == num ) {
                return true;
            }else if ( guess < num) {
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return false;
    }
}
