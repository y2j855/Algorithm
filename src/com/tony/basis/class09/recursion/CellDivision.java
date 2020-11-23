package com.tony.basis.class09.recursion;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/23 13:24
 * Description: 面试题：细胞分裂
 * 细胞分裂，有一个细胞每一个小时分裂一次，一次分裂一个细胞，第三个小时后会死亡。那么n个小时有多少细胞？
 */
public class CellDivision {

    /**
     * 递归思路
     * 1.将细胞分为初始a，幼年b，成熟c，死亡。我们只需要统计n个小时初始，幼年成熟的个数。
     * 2.需要画图，比较清晰看到上边几种状态的情况
     * 3.通过关系图，推导出递归关系
     *      f(n) = fa(n)+fb(n)+fc(n)
     *      fa(n) = fa(n-1) + fb(n-1) + fc(n-1)
     *      如果n=1，fa(1)=1
     *      fb(n) = fa(n-1)
     *      如果n=1，fb(1)=0
     *      fc(n) = fb(n-1)
     *      如果n=1,n=2,fc(1)=0,fc(2)=0
     *
     * @param hours
     * @return
     */
    public int allCells(int hours) {
        if (hours == 1) {
            return 1;
        }
        return aCells(hours) + bCells(hours) + cCells(hours);
    }

    private int aCells(int hours) {
        if(hours==1){
            return 1;
        }
        return aCells(hours - 1) + bCells(hours - 1) + cCells(hours - 1);
    }

    private int bCells(int hours) {
        if(hours==1){
            return 0;
        }
        return aCells(hours-1);
    }

    private int cCells(int hours) {
        if(hours==1 || hours==2){
            return 0;
        }
        return bCells(hours-1);
    }

    public static void main(String[] args) {
        CellDivision cell = new CellDivision();
        System.out.println(cell.allCells(3));
    }

}
