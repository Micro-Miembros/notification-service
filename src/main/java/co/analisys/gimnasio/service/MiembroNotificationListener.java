package co.analisys.gimnasio.service;

import co.analisys.gimnasio.config.RabbitMQConfig;
import co.analisys.gimnasio.dto.MiembroRegistroMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MiembroNotificationListener {
    
    @RabbitListener(queues = RabbitMQConfig.MIEMBRO_QUEUE)
    public void procesarRegistroMiembro(MiembroRegistroMessage mensaje) {
        try {
            System.out.println("Recibido mensaje de registro de miembro: " + mensaje);
            
            System.out.println("Email de bienvenida enviado a: " + mensaje.getEmail());
            
        } catch (Exception e) {
            System.err.println("Error procesando notificación para miembro: " + mensaje.getNombre());
            e.printStackTrace();
        }
    }
    
}
