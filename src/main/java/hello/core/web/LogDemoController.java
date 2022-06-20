package hello.core.web;

import hello.core.commmon.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

    // scoper(value="request")로 인해서 request가 요청 되었을 때만 동작하기 때문에 server start 시점에선 Bean으로 등록 되어 있지 않다.
    // private final MyLogger myLogger;

    // private final ObjectProvider<MyLogger>  myLoggerProvider;

    private final MyLogger  myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logdemo(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        // myLoggerProvider.getObject()
        myLogger.setRequestURL(requestUrl);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
