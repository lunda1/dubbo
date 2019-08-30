package com.liupeng.learning.algorithm.greedy;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Author oliver.liu
 * @Date 2019/8/29 17:21
 * https://www.cnblogs.com/cxmhy/p/4477670.html
 */
public class JobArrangeTest {
    public static void main(String[] args) {
        System.out.println(totalTime());
    }

    public static int totalTime(){
        int time = 0;
        int machine = 3;
        int[] jobNoArr = {1,2,3,4,5,6,7};
        int[] jobTimeArr = {2,14,4,16,6,5,3};
        //排序
        Arrays.sort(jobTimeArr);
        //反转
        ArrayUtils.reverse(jobTimeArr);

        Integer[] machineSum = new Integer[machine];
        for (int i=0;i<machine;i++) {
            machineSum[i] = 0;
        }

        for (Integer jobTime : jobTimeArr) {
            //每次都找到当前最早结束的机器添加
            Integer index = findMinIndex(machineSum);
            machineSum[index] = machineSum[index]+jobTime;
        }

        Arrays.sort(machineSum);

        //选最大的数组值代表总用时
        return machineSum[machineSum.length-1];
    }

    public static int findMinIndex(Integer[] results){
        Integer minIndex = 0;
        Integer minVal = results[0];
        for (int i=0;i<results.length;i++) {
            if (results[i] < minVal) {
                minVal = results[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public List<JobInfo> buildOriginJobList(){
        return null;
    }

    private class JobInfo{
        int jobNo;
        int jobTime;

        public int getJobNo() {
            return jobNo;
        }

        public void setJobNo(int jobNo) {
            this.jobNo = jobNo;
        }

        public int getJobTime() {
            return jobTime;
        }

        public void setJobTime(int jobTime) {
            this.jobTime = jobTime;
        }
    }
}
