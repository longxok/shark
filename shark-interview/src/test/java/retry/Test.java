package retry;

import com.cloudwalk.shark.interview.retryUtil.RetryUtil;

public class Test {
    public static void main(String[] args){
        retryTest(3);
    }

    public static String retryTest(int i){
        if(i>3){
            System.out.println(i+"");
            return i+"";
        }
        else{
            System.out.println("12213");
            RetryUtil.setRetryTimes(3).retry(i);
        }
        return "0";
    }
}
