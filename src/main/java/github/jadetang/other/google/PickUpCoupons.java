package github.jadetang.other.google;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class PickUpCoupons {

    @Test
    void test() {
        assertEquals(4, minPickUpCoupons(new int[]{1, 3, 4, 2, 3, 4, 5, 8}));
        assertEquals(-1, minPickUpCoupons(new int[]{2, 5, 0, 3}));
    }

    int minPickUpCoupons(int[] coupons) {
        int ans = coupons.length;
        int l = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < coupons.length; i++) {
            count.put(coupons[i], count.getOrDefault(coupons[i], 0) + 1);
            while (count.get(coupons[i]) >= 2) {
                ans = Math.min(ans, i - l + 1);
                int left = coupons[l++];
                count.put(left, count.get(left) - 1);
                if (count.get(left) == 0) {
                    count.remove(left);
                }
            }
        }
        return ans == coupons.length ? -1 : ans;
    }
}
