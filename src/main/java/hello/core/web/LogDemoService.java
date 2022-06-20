package hello.core.web;

import hello.core.commmon.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    // private final MyLogger myLogger;
    // private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    private final MyLogger myLogger;

    public void logic(String id) {
        // myLoggerObjectProvider.getObject()
        myLogger.log("service id = " + id);
    }
}
