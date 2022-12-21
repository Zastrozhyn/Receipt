package by.zastr.controller;
import by.zastr.controller.config.SpringConfig;
import by.zastr.controller.util.MyFileReader;
import by.zastr.service.exception.EntityException;
import by.zastr.service.exception.ExceptionCode;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private final static String SOURCE = "src\\main\\resources\\product.txt";
    public static void main(String[] args){

        if (args.length == 0){
            MyFileReader reader = new MyFileReader();
            try {
                args = reader.readFile(SOURCE).toArray(new String[0]);
            } catch (EntityException e) {
                System.out.println(ExceptionCode.FILE_NOT_FOUND);
            }
        }

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Controller controller = context.getBean(Controller.class);
        controller.start(args);
    }
}