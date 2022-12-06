package com.utibe.aspects;

import com.utibe.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import com.utibe.BusinessClass;
import org.springframework.stereotype.Component;


//https://www.eclipse.org/aspectj/doc/released/progguide/
//https://www.eclipse.org/aspectj/doc/released/progguide/semantics-pointcuts.html
//https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop

@Aspect
@Component
public class AspectsUty {
    private static final Logger logger = LogManager.getLogger();
    private long entryTime = 0;

    /*@Before("execution(* com.utibe.BusinessClass.getName())")
    public void doAccessCheck() {
        logger.info("*******************before get check*******************");
    }*/

    @AfterReturning(pointcut="getReturnValuePointcut()",
                    returning="retVal")
    public void doAccessCheck(JoinPoint joinPoint, Object retVal) {
        logger.info("*******************Value returned from {} was {}" +
                        " and args were {} *******************",
                joinPoint.getSignature().getName(), retVal, joinPoint.getArgs());
        BusinessClass businessClass = (BusinessClass) joinPoint.getTarget();

        logger.info("********** Via jointpoint, name is {}*******", businessClass.getName());
        logger.info("********** Via jointpoint, name is {}*******", businessClass.getName());

    }

    @Pointcut( "execution(public * com.utibe.BusinessClass.get*(..) )" )
    public void getReturnValuePointcut(){}

    @Before("getClassMembersPointcut()")
    public void getClassMembers(JoinPoint joinPoint){
        logger.info("**********Executing method {}*******", joinPoint.getSignature().getName());
    }

    @Pointcut( "execution(public * com.utibe.BusinessClass.get*(..) )" )
    public void getClassMembersPointcut(){}


    @Around("toStringPointcut()")
    public Object doBasicProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("**********Around pointcut start**********");
        Object retVal = proceedingJoinPoint.proceed();
        logger.info("**********Around pointcut stop**********");
        return retVal;
    }
    @Pointcut( "execution(public * com.utibe.BusinessClass.logToString(..) )" )
    public void toStringPointcut(){}

    @Around("setEmailPointcut(email)")
    public Object setEmailAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Email email) throws Throwable {
        logger.info("**********Around pointcut start**********");
        Object retVal = proceedingJoinPoint.proceed();
        logger.info("**********Around pointcut stop**********");

        logger.info("*****Email is ******* {}", email.toString());

        return retVal;
    }
    @Pointcut( "execution(public * com.utibe.BusinessClass.setEmail(..) ) && args(email, ..)" )
    public void setEmailPointcut(Email email){}




    /*@Before("setNamePointcut()")
    public void beforeLoggingAdvice(JoinPoint joinPoint){
        this.entryTime = System.nanoTime();
        logger.info("************************************");
        logger.info("Now ENTERING method {} of class {}",joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType()
                );
    }

    //@After("setNamePointcut()")
    public void afterLoggingAdvice(JoinPoint joinPoint){
        long totalTime = System.nanoTime() - this.entryTime ;
        logger.info("Now EXITING method {} of class {}, took {} milliseconds", joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType(), totalTime/1E6);
        logger.info("************************************\n");
    }

    @Before("execution(public * com.utibe.BusinessClass.*(..) )")
    public void doAccessCheck() {
        logger.info("*******************doAccessCheck()***************\n");
    }

   @AfterReturning( pointcut="com.utibe.BusinessClass.getName() )",
                     returning="retVal")
    public void doAccessCheck(Object retVal) {
        logger.info("Intercepted return value from {} is {}", "Eee", retVal);
    }

    //execution(void m(..))

    //@Pointcut( "execution(public * com.utibe.BusinessClass.*(..) )" )
    //@Pointcut( "execution(public * com.utibe.BusinessClass.*(..) )" )
    //@Pointcut( "bean(*essClassBean)" )
    //public void setNamePointcut(){}*/

    //@Pointcut( "execution(public * com.utibe.BusinessClass.*(..) )" )
    //@Pointcut( "execution(public * com.utibe.BusinessClass.*(..) )" )
    //@Pointcut( "bean(*essClassBean)" )
    //public void setNamePointcut(){}






}
