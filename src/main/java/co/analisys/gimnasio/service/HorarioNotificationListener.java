package co.analisys.gimnasio.service;

import co.analisys.gimnasio.config.RabbitMQConfig;
import co.analisys.gimnasio.dto.HorarioClaseMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class HorarioNotificationListener {
    
    @RabbitListener(queues = RabbitMQConfig.HORARIO_QUEUE)
    public void procesarCambioHorario(HorarioClaseMessage mensaje) {
        try {
            System.out.println("Recibido mensaje de cambio de horario: " + mensaje);
            

            System.out.println("   - Clase ID: " + mensaje.getClaseId());
            System.out.println("   - Nombre de la clase: " + mensaje.getNombreClase());
            System.out.println("   - Nuevo horario: " + mensaje.getNuevaFechaHora());
            
            if (mensaje.getMiembrosIds() != null && !mensaje.getMiembrosIds().isEmpty()) {
                System.out.println("Enviando notificaciones individuales:");
                for (Long miembroId : mensaje.getMiembrosIds()) {
                    System.out.println("   Notificación enviada al miembro ID: " + miembroId);
                }
            }
  
                      
        } catch (Exception e) {
            System.err.println("Error procesando notificación de horario para clase: " + mensaje.getNombreClase());
            e.printStackTrace();
        }
    }
}
    