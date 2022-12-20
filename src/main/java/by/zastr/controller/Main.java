package by.zastr.controller;
import by.zastr.controller.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Controller controller = context.getBean(Controller.class);
        controller.start(args);
    }
}