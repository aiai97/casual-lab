package org.springframework.samples.casuallab.bitwise;

/**
 * Bitwise Operations Cheat Sheet - With Before/After Comparison
 *
 * Author: Leslie Tsai
 *
 * Purpose:
 *   I want to redefine how people understand bitwise operations.
 *   They are not about memorizing binary digits, but about speeding up computation,
 *   especially when handling differences, common bits, and powers of 2.
 *
 *   Traditional tutorials forced me to waste energy memorizing useless APIs
 *   and binary tables. I believe programming should be about reasoning,
 *   not rote memorization.
 *
 * ---------------------------------------------------
 * 1. Core Concepts
 * & → both are 1 → intersection (mask / clear bits)
 * | → either is 1 → union (set bits)
 * ^ → different is 1 → difference / toggle
 *
 * ---------------------------------------------------
 * 2. Common Formulas
 * n & (n - 1)    → remove the lowest set bit
 * n | (n - 1)    → set all bits to the right of the lowest set bit
 * n ^ (n - 1)    → flip all bits up to the lowest set bit
 * x ^ x = 0      → same cancels out
 * x & -x         → extract lowest set bit
 * x | (1 << k)   → set k-th bit
 * x & ~(1 << k)  → clear k-th bit
 * x ^ (1 << k)   → toggle k-th bit
 *
 * ---------------------------------------------------
 * 3. Additional Useful Tricks
 * (x & 1) == 0                 → check if x is even
 * (x ^ (x >> 31)) - (x >> 31)  → absolute value of x
 * (x & y) + ((x ^ y) >> 1)     → average of x and y (avoid overflow)
 * c ^ 32                       → toggle character case (A ↔ a)
 *
 * ---------------------------------------------------
 */

public class BitwiseCheatSheetWithCompare {

    // Format binary with leading zeros (for clarity)
    private static String toBinary(int value) {
        return String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0');
    }

    // Print comparison: before -> after
    private static void showBits(String label, int before, int after) {
        System.out.println(label);
        System.out.println("  Before: " + before + " (" + toBinary(before) + ")");
        System.out.println("  After : " + after  + " (" + toBinary(after)  + ")");
        System.out.println();
    }

    // 1) n & (n-1): remove the lowest set bit (1)
    public static void removeLowestOne(int n) {
        int result = n & (n - 1);
        showBits("1) n & (n-1): Remove lowest 1", n, result);
    }

    // 2) n | (n-1): set all bits to the right of the lowest 1
    public static void fillRightOnes(int n) {
        int result = n | (n - 1);
        showBits("2) n | (n-1): Fill right with 1s", n, result);
    }

    // 3) n ^ (n-1): flip all bits to the right of the lowest 1
    public static void toggleRightOnes(int n) {
        int result = n ^ (n - 1);
        showBits("3) n ^ (n-1): Toggle right bits", n, result);
    }

    // 4) x ^ x = 0
    public static void xorSelf(int x) {
        int result = x ^ x;
        showBits("4) x ^ x = 0", x, result);
    }

    // 5) x & -x: extract lowest set bit
    public static void lowestOneBit(int x) {
        int result = x & -x;
        showBits("5) x & -x: Extract lowest 1", x, result);
    }

    // 6) x | (1 << k): set the k-th bit
    public static void setKthBit(int x, int k) {
        int result = x | (1 << k);
        showBits("6) Set k-th bit", x, result);
    }

    // 7) x & ~(1 << k): clear the k-th bit
    public static void clearKthBit(int x, int k) {
        int result = x & ~(1 << k);
        showBits("7) Clear k-th bit", x, result);
    }

    // 8) x ^ (1 << k): toggle the k-th bit
    public static void toggleKthBit(int x, int k) {
        int result = x ^ (1 << k);
        showBits("8) Toggle k-th bit", x, result);
    }

    public static void main(String[] args) {
        int n = 12; // binary 1100
        int x = 10; // binary 1010

        System.out.println("=== Bitwise Operations with Before/After Comparison ===\n");

        removeLowestOne(n);
        fillRightOnes(n);
        toggleRightOnes(n);
        xorSelf(x);
        lowestOneBit(x);
        setKthBit(x, 1);
        clearKthBit(x, 1);
        toggleKthBit(x, 1);
    }
}
