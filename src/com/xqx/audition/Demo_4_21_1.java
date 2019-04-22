package com.xqx.audition;

/**
 *
 */
public class Demo_4_21_1 {
    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        for(int i=0;i<10;i++)     {
            ok:
            for(int j=0;j<10;j++)            {

                for (int k = 0; k < 10; k++) {
                    System.out.println("i=" + i + ",j=" + j + ",k=" + k);

                    if(k == 5) break ok;
                }
            }
        }
        System.out.println("hello");
    }
    public static void test2(){
        int arr[][] ={{1,2,3},{4,5,6,7},{9}};

        boolean found = false;

        for(int i=0;i<arr.length&& !found;i++)       {

            for(int j=0;j<arr[i].length;j++){

                System.out.println("i=" + i + ",j=" + j);

                if(arr[i][j]  ==5) {

                    found = true;

                    break;

                }

            }

        }
    }

}
