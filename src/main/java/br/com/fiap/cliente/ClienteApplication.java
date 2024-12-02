package br.com.fiap.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.fiap.cliente")
public class ClienteApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClienteApplication.class, args);
    }
}
