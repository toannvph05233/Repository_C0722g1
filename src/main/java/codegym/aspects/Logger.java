package codegym.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    @After(value = "execution(* codegym.controller.StudentController.*(..))")
    public void logger(){
        System.out.println("----------------");
        System.out.println("Bạn đã chạy hàm show");
        System.out.println("----------------");
    }

    @AfterThrowing(value = "execution(* codegym.controller.StudentController.*(..))")
    public void error(){
        System.out.println("----------------");
        System.err.println("Bạn gặp lỗi rồi");
        System.out.println("----------------");
    }
}
