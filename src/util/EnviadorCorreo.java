package src.util;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Clase de utilidad encargada de enviar correos electrónicos reales
 * mediante el protocolo SMTP, usando una cuenta de Gmail y una
 * contraseña de aplicación. Se agregó para cumplir con el requisito de
 * probar el envío de correo con conexión a internet real, en lugar de
 * simular el envío únicamente por consola.
 *
 * @author Jair Cárdenas
 * @version 1.0
 */
public class EnviadorCorreo {

    /** Correo de Gmail que se usará como remitente para todos los envíos. */
    private static final String REMITENTE = "jcardenasf34@gmail.com";

    /** Contraseña de aplicación de 16 caracteres generada en Gmail (NO tu contraseña normal). */
    private static final String CLAVE_APP = "cdodpsdparptvjna";

    /**
     * Envía un correo electrónico real mediante SMTP usando la cuenta
     * de Gmail configurada en REMITENTE y CLAVE_APP.
     *
     * @param destinatario correo electrónico del destinatario
     * @param asunto asunto del correo
     * @param cuerpo contenido del mensaje
     */
    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(REMITENTE, CLAVE_APP);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(REMITENTE));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);
            System.out.println("[Correo real enviado exitosamente a " + destinatario + "]");
        } catch (MessagingException e) {
            System.out.println("[No se pudo enviar el correo real: " + e.getMessage() + "]");
        }
    }
}