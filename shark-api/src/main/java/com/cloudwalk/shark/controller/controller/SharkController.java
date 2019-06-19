package com.cloudwalk.shark.controller.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloudwalk.shark.config.utils.TokenUtils;
import com.cloudwalk.shark.controller.dto.InitVo;
import com.cloudwalk.shark.controller.dto.UserDto;
import com.cloudwalk.shark.controller.dto.UserFathderDto;
import com.cloudwalk.shark.controller.model.User;
import com.cloudwalk.shark.service.SharkServiceInter;
import com.cloudwalk.shark.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;


@Controller
@Api(tags = "账户管理")
public class SharkController implements InitializingBean/*, ApplicationListener*/ {
    @Autowired
    private SharkServiceInter sharkService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationContext applicationContext;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.context-path]")
    private String serverName;

    private InitVo initVo = new InitVo();
    private boolean detectHandlersInAncestorContexts = false;

    private Set<String> excludedPackages = Collections.singleton("org.springframework.web.servlet.mvc");

    private static final String CONTEXT_CLASS_PARAM = "contextClass";
    private static final String CONTEXT_ID_PARAM = "contextId";

    @GetMapping("/user/{id}/{name}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "获取用户列表", notes = "")
    public String SharkControllerMethod(@PathVariable("id") Integer id, @PathVariable("name") String name) throws ClassNotFoundException {
        Map<String, MappedInterceptor> handlerMappingMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, MappedInterceptor.class, true, false);
        System.out.println("serverPort:" + serverPort);
        System.out.println("serverPath:" + serverName);

        String[] beanNames = (this.detectHandlersInAncestorContexts ?
                BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext, Object.class) :
                applicationContext.getBeanNamesForType(Object.class));

        for (String beanName : beanNames) {
            Class<?> beanClass = (Class<?>) applicationContext.getType(beanName);
            if (!(AnnotatedElementUtils.hasAnnotation(beanClass, Controller.class) ||
                    AnnotatedElementUtils.hasAnnotation(beanClass, RequestMapping.class))) {
                continue;
            }

            System.out.println((String)null);
            String [] typeHandlersPackageArray = StringUtils.tokenizeToStringArray("a,;b,;c", ",;");
            System.out.println("typeHandlersPackageArray:" + typeHandlersPackageArray);
            System.out.println("beanName:" + beanName);
            if (Controller.class.isAssignableFrom(beanClass)) {
                System.out.println("&&&&&&&beanName:" + beanName);
            }
        }
        return "index";
    }

    @RequestMapping(value = "/index/test")
    @ResponseBody
    public Object checkFastJsonAnno(HttpServletRequest request, @RequestBody String body) {
        UserDto userModel = JSONObject.parseObject(body, UserDto.class);
       // User user = userService.findUserByName(userModel.getName());
        //System.out.println(user.getId());
        //System.out.println(Integer.toHexString(System.identityHashCode(userModel)));
        ServletContext servletContext = request.getSession().getServletContext();
        WebApplicationContext wac = (WebApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        if (UserFathderDto.class.isAssignableFrom(userModel.getClass())) {
        }
       List<String> nameList = new ArrayList<String>();
        nameList.add("aa");
        nameList.add("张书");
        List<User> list = userService.queryUserByName(nameList);
        //PageHelper.startPage(1, 10);
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("userName",userModel.getUserName());
       // User user = userService.queryUsersByName(queryMap);
        // 取分页信息
       // PageInfo<User> pageInfo = new PageInfo<User>(listAll);
       // long total = pageInfo.getTotal(); //获取总记录数
        if (ConfigurableApplicationContext.class.isAssignableFrom(wac.getClass())) {
            ConfigurableApplicationContext cac = (ConfigurableApplicationContext) wac;
            System.out.println(cac.isActive());
        }
        String contextClassName = servletContext.getInitParameter(CONTEXT_CLASS_PARAM);
        String idParam = servletContext.getInitParameter(CONTEXT_ID_PARAM);
        System.out.println(idParam);
        System.out.println(contextClassName);
        System.out.println(String.format("Hi,%s:%s", userModel.getUserName(), userModel.getAge()));
        return list;
    }

    @PostMapping("/login")
    @ResponseBody
    public Object sharkLogin(HttpServletRequest request, @RequestBody String body) {
        return TokenUtils.createJwtToken("1");
    }


    public Object getMappingForMethod(Method method, Class<?> handlerType) {
        //拿到注解
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
        return requestMapping != null ? true : false;
    }

    public SharkController() {
        System.out.println("abc");
    }

    /**
     *
     */
    @PostConstruct
    public void postConstractor() {
        this.excludedPackages = (excludedPackages != null) ?
                new HashSet<String>(Arrays.asList(new String[]{"org.springframework.web.servlet.mvc"})) : new HashSet<String>();
        System.out.println("123");
    }

    /**
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        System.out.println("456");
    }

    protected Object createRequestMappingInfo(
            RequestMapping requestMapping, RequestCondition<?> customCondition) {

        return new Object();
    }


    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextRefreshedEvent){
            System.out.println("111111111111111111111111");
        }
    }
}
