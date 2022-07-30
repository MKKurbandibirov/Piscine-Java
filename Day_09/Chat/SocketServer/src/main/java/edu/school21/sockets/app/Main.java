package edu.school21.sockets.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    private static Integer port;
    public static void main(String[] args) {
        if (args.length == 1) {
            String[] tmp = args[0].split("=");
            if (!tmp[0].equals("--port")) {
                System.err.println("Illegal Argument!");
                System.exit(-1);
            }
            ApplicationContext context = new AnnotationConfigApplicationContext("edu.school21.sockets");
            UsersService service = context.getBean("usersService", UsersService.class);
            port = Integer.parseInt(tmp[1]);
            Server server = new Server(port, service);
            server.connect();
        } else {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }
    }
}
