package com.tn.skillexchange.skillexchange.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBadgeEmail(String to, String badgeTitle, String badgeDescription) {
        System.out.println("📨 Envoi d'email à : " + to);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("🎖 Nouveau badge obtenu : " + badgeTitle);
            helper.setText("Félicitations ! Vous avez obtenu le badge : " + badgeTitle +
                    "\n\nDescription : " + badgeDescription, false);

            mailSender.send(message);
            System.out.println("✅ Email envoyé avec succès à : " + to);

        } catch (MessagingException e) {
            System.out.println("❌ Erreur lors de l'envoi de l'email à : " + to);
            e.printStackTrace();
        }
    }
}
