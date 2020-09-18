package github.jadetang.leetcode.solution;

public class _1573_Number_of_Ways_to_Split_a_String {

    public int numWays(String s) {
        int mod = 1_000_000_007;
        int ones = 0;
        for (char i : s.toCharArray()) {
            if (i == '1') {
                ones++;
            }
        }
        if (ones % 3 != 0) {
            return 0;
        }
        int k = ones / 3;
        int[] index = new int[k];
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                index[j] = i;
            }

        }
        int ans = 0;
        for (int i = k - 1; i + k< index.length - 1; i += k) {
            ans += ((index[i + 1]  % mod) * (index[i] % mod )) % mod;
        }
        return ans;
    }
}
