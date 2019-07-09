package com.cloudwalk.shark.rule.util.interfaces;


import com.cloudwalk.shark.rule.util.beans.RequestInfo;
import com.cloudwalk.shark.rule.util.beans.RestInfo;

/**
 * 处理网络请求接口
 * 
 * @author 肖文杰
 * @date 2017.4.30
 */
public interface IRequestHandle {
	Object handle(RestInfo restInfo, RequestInfo request);
}
