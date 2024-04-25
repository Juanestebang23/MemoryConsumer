package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
public class MemoryConsumerApplication {

    // Endpoint para mostrar la IP del contenedor y el ID del contenedor
    @GetMapping("/info")
    public String getInfo() throws UnknownHostException {
        // Obtiene la IP del contenedor
        String ipAddress = InetAddress.getLocalHost().getHostAddress();
        
        // Obtiene el ID del contenedor (este es un valor simulado)
        String containerId = System.getenv().getOrDefault("HOSTNAME", "Unknown");
        
        return "IP del contenedor: " + ipAddress + "\nID del contenedor: " + containerId;
    }

    // Método para consumir memoria
    public void consumeMemory() {
        try {
            // Simula el consumo de memoria llenando un array
            long[] memoryAlloc = new long[Integer.MAX_VALUE];
        } catch (OutOfMemoryError e) {
            System.out.println("¡La memoria se ha agotado!");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MemoryConsumerApplication.class, args);
        MemoryConsumerApplication memoryConsumer = new MemoryConsumerApplication();

        // Bucle infinito para consumir memoria continuamente
        while (true) {
            memoryConsumer.consumeMemory();
        }
    }
}
