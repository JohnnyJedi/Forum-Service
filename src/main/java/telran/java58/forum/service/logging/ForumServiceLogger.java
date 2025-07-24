package telran.java58.forum.service.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "telran.java58.forum.ForumServiceImpl")
@Aspect
public class ForumServiceLogger {

    @Pointcut("execution(public * telran.java58.forum.service.ForumServiceImpl.*(String)) && args(id)")
    public void findById(String id) {
    }

    @Pointcut("@annotation(telran.java58.forum.service.logging.PostLogger)")
    public void annotatePostLogger() {
    }

    @Pointcut("execution(public java.util.List telran.java58.forum.service.ForumServiceImpl.findPostsByAuthor(..))")
    public void bulkFindPostsLogger() {
    }

    @Before("findById(id)")
    public void logFindById(String id) {
        log.info("find post by id {}", id);
    }

    @AfterReturning("annotatePostLogger()")
    public void logAnnotatedPostLogger(JoinPoint joinPoint) {
        log.info("annotated by post logger method: {}, done", joinPoint.getSignature().getName());
    }

    @Around(value = "bulkFindPostsLogger()")
    public Object logBulkFindPostsLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String str) {
                args[i] = str.toLowerCase();
            }
        }
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(args);
        long end = System.currentTimeMillis();
        log.info("method {}, time: {} ms", joinPoint.getSignature().getName(), end - start);
        return result;

    }

}
