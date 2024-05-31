package com.solutionspeak.Kamsal.Workshop.service;

import com.solutionspeak.Kamsal.Workshop.dto.MessageDTO;
import com.solutionspeak.Kamsal.Workshop.dto.UserDTO;
import com.solutionspeak.Kamsal.Workshop.entity.Message;
import com.solutionspeak.Kamsal.Workshop.entity.Workshop;
import com.solutionspeak.Kamsal.Workshop.form.MessageForm;
import com.solutionspeak.Kamsal.Workshop.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    public List<MessageDTO> getAllMessages() {
        final List<Message> messages = messageRepository.findAll();
        final Map<Integer, UserDTO> sendersMap =
                getUsersMap(messages.stream().map(Message::getSenderId).toList());
        final Map<Integer, UserDTO> recipientsMap =
                getUsersMap(messages.stream().map(Message::getRecipientId).toList());
        return messages
                .stream()
                .map(message -> MessageDTO
                        .build(message,
                                sendersMap.get(message.getSenderId()),
                                recipientsMap.get(message.getRecipientId())))
                .toList();
    }

    public MessageDTO getMessageById(final int messageid) throws Exception {
        validateIfMessageExist(messageid);
        final Message message = messageRepository.findById(messageid).orElseThrow();
        final UserDTO sender = userService.getUserById(message.getSenderId());
        final UserDTO recipient = userService.getUserById(message.getRecipientId());
        return MessageDTO.build(message, sender, recipient);
    }

    public List<MessageDTO> getConversation(final int senderId, final int recipientId) throws Exception {
        userService.validateIfUserExist(senderId);
        userService.validateIfUserExist(recipientId);
        List<Message> messages = messageRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        final Map<Integer, UserDTO> sendersMap =
                getUsersMap(messages.stream().map(Message::getSenderId).toList());
        final Map<Integer, UserDTO> recipientsMap =
                getUsersMap(messages.stream().map(Message::getRecipientId).toList());
        return messages
                .stream()
                .map(message -> MessageDTO
                        .build(message,
                                sendersMap.get(message.getSenderId()),
                                recipientsMap.get(message.getRecipientId())))
                .toList();
    }

    public MessageDTO createMessage(final MessageForm form) throws Exception {
        userService.validateIfUserExist(form.getSenderId());
        userService.validateIfUserExist(form.getRecipientId());
        final Message message = new Message(form);
        messageRepository.save(message);
        final UserDTO sender = userService.getUserById(message.getSenderId());
        final UserDTO recipient = userService.getUserById(message.getRecipientId());
        return MessageDTO.build(message, sender, recipient);
    }

    public MessageDTO updateMessage(final MessageForm form, int messageId) throws Exception {
        validateIfMessageExist(messageId);
        userService.validateIfUserExist(form.getSenderId());
        userService.validateIfUserExist(form.getRecipientId());
        final Message message = messageRepository.findById(messageId).orElseThrow();
        message.updateMessage(form);
        messageRepository.save(message);
        final UserDTO sender = userService.getUserById(message.getSenderId());
        final UserDTO recipent = userService.getUserById(message.getRecipientId());
        return MessageDTO.build(message, sender, recipent);
    }

    public void deleteMessage(final int messageId) throws Exception {
        validateIfMessageExist(messageId);
        messageRepository.deleteById(messageId);
    }

    public void validateIfMessageExist(final int messageId) throws Exception {
        if (!messageRepository.existsById(messageId)) {
            throw new Exception("No se ha encontrado el mensaje con el ID: " + messageId);
        }
    }

    private Map<Integer, UserDTO> getUsersMap(final List<Integer> usersId) {
        return userService.getUserByIds(usersId);
    }
}
