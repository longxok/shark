package com.cloudwalk.shark.controller.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.springframework.core.annotation.AnnotationAttributes.fromMap;

@Controller
@NacosPropertySource(dataId = "springboot2-nacos-config", autoRefreshed = true)
public class HealthController {
    @Autowired
    private String healthString;


    @Autowired
    public HealthController(String healthString){
        this.healthString = healthString ;
    }

    @NacosValue(value = "${nacos.test.propertie:123}", autoRefreshed = true)
    private String testProperties;

    @ResponseBody
    @GetMapping("/nacos/test")
    public String test(){
        return testProperties;
    }

    @ResponseBody
    @GetMapping("/nacos/healthString")
    public String healthString() throws IOException {
        MetadataReader metadataReader = new SimpleMetadataReaderFactory().getMetadataReader(EnableNacosConfig.class.getName());
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        AnnotationAttributes attributes = fromMap(annotationMetadata.getAnnotationAttributes(EnableNacosConfig.class.getName()));

        return healthString;
    }

    @ResponseBody
    @RequestMapping(value = "/readiness", method = RequestMethod.GET)
    public Object readiness(HttpServletRequest request) {
        boolean isConfigReadiness = true;
        boolean isNamingReadiness = false;

        if (isConfigReadiness && isNamingReadiness) {
            return ResponseEntity.ok().body("OK");
        }

        if (!isConfigReadiness && !isNamingReadiness) {
            return ResponseEntity.status(500).body("Config and Naming are not in readiness");
        }

        if (!isConfigReadiness) {
            return ResponseEntity.status(500).body("Config is not in readiness");
        }

        return ResponseEntity.status(500).body("Naming is not in readiness");
    }
}
