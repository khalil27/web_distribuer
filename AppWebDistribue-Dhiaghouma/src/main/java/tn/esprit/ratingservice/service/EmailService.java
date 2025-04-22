package tn.esprit.ratingservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends an email notification to the skill owner about a new rating.
     *
     * @param to            - Recipient's email address (skill owner)
     * @param skillName     - The name of the skill being rated
     * @param ratingScore   - The score given by the user (1 to 5 stars)
     * @param comment       - The comment associated with the rating
     */
    public void sendRatingEmail(String to, String skillName, int ratingScore, String comment) {
        System.out.println("📨 Sending email to: " + to);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("🔔 Nouvelle évaluation reçue pour votre compétence : " + skillName);
            helper.setText("Vous avez reçu une nouvelle évaluation pour votre compétence : " + skillName +
                    "\n\nÉvaluation : " + ratingScore + " étoiles\n\nCommentaire : " + comment, false);

            mailSender.send(message);
            System.out.println("✅ Email sent successfully to: " + to);

        } catch (MessagingException e) {
            System.out.println("❌ Error sending email to: " + to);
            e.printStackTrace();
        }
    }
}
