import org.jahia.registries.ServicesRegistry
import org.jahia.services.mail.MailSettings

String smtpHost = System.getenv("MAIL_SMTP_HOST") ?: "mailpit"
String smtpPort = System.getenv("MAIL_SMTP_PORT") ?: "1025"

MailSettings mailSettings = new MailSettings()
mailSettings.setServiceActivated(true)
mailSettings.setUri("smtp://${smtpHost}:${smtpPort}")
mailSettings.setFrom("admin@prism.com")
mailSettings.setTo("admin@prism.com")
mailSettings.setNotificationLevel("Paranoid")
ServicesRegistry.instance.mailService.store(mailSettings)
