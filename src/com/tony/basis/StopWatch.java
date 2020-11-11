package com.tony.basis;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/27 15:03
 * Description:
 */
public class StopWatch {
    private final long start;

    public StopWatch() {
        this.start = System.currentTimeMillis();
    }

    /**
     * 运行多少秒
     * @return
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000;
    }
}
