package com.gaowj.job;

import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**
 * gaowj
 * created on 2020-03-24
 * 布隆过滤器核心类
 */
public class BloomFilterHelper {
    private int numHashFunctions;
    private int bitSize;
    private Funnel<CharSequence> funnel;

    public BloomFilterHelper(Funnel<CharSequence> funnel, int expectedInsertions, double fpp) {
        Preconditions.checkArgument(funnel != null, "funnel不能空");
        this.funnel = funnel;
        this.bitSize = optimalNumOfBits(expectedInsertions, fpp);
        this.numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
    }

    public int[] murmurHashOffset(String value) {
        int[] offset = new int[numHashFunctions];
        long hash64 = Hashing.murmur3_128().hashObject(value, funnel).asLong();
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);
        for (int i = 1; i <= numHashFunctions; i++) {
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0)
                nextHash = ~nextHash;
            offset[i - 1] = nextHash % bitSize;
        }

        return offset;
    }

    /**
     * 计算hash方法执行次数
     *
     * @param n
     * @param m
     * @return
     */
    private int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    /**
     * 计算bit数组长度
     *
     * @param n
     * @param p
     * @return
     */
    private int optimalNumOfBits(long n, double p) {
        if (p == 0)
            p = Double.MIN_VALUE;
        return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    public static void main(String[] args) {
        BloomFilterHelper bloomFilterHelper = new BloomFilterHelper(Funnels.stringFunnel(Charset.defaultCharset()), 1000, 0.1);
        int[] as = bloomFilterHelper.murmurHashOffset("asdkfjiwehsdfjaseihwueghweghdsjahdaguehwgebgauhdgjkashdfjasbdabuefgwebgaudghasdhfasdfuawehgue");
        for (int a : as)
            System.out.print(a + " ");
    }
}
