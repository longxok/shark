package com.cloudwalk.shark.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.trace.http.HttpTrace;

import java.util.Collections;
import java.util.List;

@Slf4j
public class RemoteHttpTraceRepository  {

    //@Override
    public List<HttpTrace> findAll() {
        return Collections.emptyList();
    }

   // @Override
    public void add(org.springframework.boot.actuate.trace.http.HttpTrace trace) {
        String path = trace.getRequest().getUri().getPath();
        String queryPara = trace.getRequest().getUri().getQuery();
        String queryParaRaw = trace.getRequest().getUri().getRawQuery();
        String method = trace.getRequest().getMethod();
        long timeTaken = trace.getTimeTaken();
        String time = trace.getTimestamp().toString();
        log.info("path: {}, queryPara: {}, queryParaRaw: {}, timeTaken: {}, time: {}, method: {}", path, queryPara, queryParaRaw,
                timeTaken, time, method);
    }
}
