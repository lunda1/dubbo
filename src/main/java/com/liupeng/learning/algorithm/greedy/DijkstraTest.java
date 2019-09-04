package com.liupeng.learning.algorithm.greedy;

/**
 * @Author oliver.liu
 * @Date 2019/9/3 15:38
 * https://www.jianshu.com/p/fa2e4c7d8202
 */
public class DijkstraTest {
    public static Integer[] startArr =  new Integer[]{1,1,2,2,4,4,5,4,3};
    public static Integer[] endArr =    new Integer[]{2,3,3,4,3,5,6,6,5};
    public static Integer[] weightArr = new Integer[]{1,12,9,3,4,13,4,15,5};
    public static int[][] data = new int[7][7];

    public static void initData(){
        for (int i=0;i<startArr.length;i++) {
            data[startArr[i]][endArr[i]] = weightArr[i];
        }

        for (int i=1;i<data.length;i++){
            for (int j=1;j<data[i].length;j++){
                if (i == j) {
                    data[i][i] = 0;
                } else if (data[i][j] == 0){
                    data[i][j] = 999;
                }
            }
        }
    }

    public static void main(String[] args) {

        initData();

        int fromIndex = 1;
        int toIndex = 6;
        int tmpMin = 999;
        int tmpIndex = 0;
        //动态记录from到其他各个点的距离
        int[] dis = new int[7];

        //记录已经计算过的节点
        int[] vis = new int[7];
        for (int i=1;i<vis.length;i++) {
            vis[i] = 0;
        }

        vis[fromIndex]=1;

        for(int i=1;i<=toIndex;i++) {
            dis[i]=data[fromIndex][i];
        }

        //Dijkstra方法核心代码
        for(int i=1;i<=toIndex;i++) {

            tmpMin = 999;
            //求距离源点最近的且未访问过的点
            for(int j=1;j<=toIndex;j++) {
                if(vis[j]==0 && dis[j]<tmpMin) {
                    tmpMin=dis[j];tmpIndex=j;
                }
            }//for

            //设置该点已被访问
            vis[tmpIndex]=1;

            //更新从该点到其余各点的最短距离
            for(int v=1;v<=toIndex;v++) {
                if(data[tmpIndex][v]<999) {
                    if(dis[v]>dis[tmpIndex]+data[tmpIndex][v]) {
                        dis[v]=dis[tmpIndex]+data[tmpIndex][v];
                    }//if
                }//if
            }//for

        }//for

        System.out.println("1-6 distance:  "+ dis[6]);

    }

}