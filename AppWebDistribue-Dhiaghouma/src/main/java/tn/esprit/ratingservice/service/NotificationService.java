package tn.esprit.ratingservice.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.ratingservice.model.Notification;
import tn.esprit.ratingservice.model.User;
import tn.esprit.ratingservice.repository.NotificationRepository;
import tn.esprit.ratingservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public void createRatingNotification(Long recipientId, Long senderId, Long skillId, Integer score) {
        Optional<User> sender = userRepository.findById(senderId);

        String senderName = sender.map(User::getUsername).orElse("A user");

        Notification notification = new Notification();
        notification.setRecipientId(recipientId);
        notification.setSenderId(senderId);
        notification.setType("RATING");
        notification.setRelatedItemId(skillId);
        notification.setMessage(senderName + " rated your skill with " + score + " stars.");
        notification.setRead(false);

        notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByRecipientIdOrderByCreatedAtDesc(userId);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByRecipientIdAndIsReadOrderByCreatedAtDesc(userId, false);
    }

    public int getUnreadNotificationsCount(Long userId) {
        return notificationRepository.countByRecipientIdAndIsRead(userId, false);
    }

    public void markAsRead(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }

    public void markAllAsRead(Long userId) {
        List<Notification> unreadNotifications = getUnreadNotifications(userId);
        unreadNotifications.forEach(notification -> notification.setRead(true));
        notificationRepository.saveAll(unreadNotifications);
    }
}