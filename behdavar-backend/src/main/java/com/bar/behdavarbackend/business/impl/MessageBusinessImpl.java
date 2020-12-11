package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.MessageBusiness;
import com.bar.behdavarbackend.business.transformer.MessageTransformer;
import com.bar.behdavarbackend.business.transformer.UserTransformer;
import com.bar.behdavarbackend.dto.MessageDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.*;
import com.bar.behdavardatabase.entity.MessageEntity;
import com.bar.behdavardatabase.entity.MessageReceiverEntity;
import com.bar.behdavardatabase.repository.MessageReceiveRepository;
import com.bar.behdavardatabase.repository.MessageRepository;
import com.bar.behdavardatabase.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service(MessageBusinessImpl.BEAN_NAME)
public class MessageBusinessImpl implements MessageBusiness {
    public static final String BEAN_NAME = "MessageBusinessImpl";

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    MessageReceiveRepository messageReceiveRepository;

    @Override
    public MessageDto findById(Long id) {
        MessageEntity entity = messageRepository.findById(id)
                .orElseThrow(() -> new BusinessException("error.Message.not.found", id));
        boolean selfMessage = (entity.getSender().getId() == SecurityUtil.getCurrentUserId());
        Optional<MessageReceiverEntity> first = entity.getReceivers().stream().filter(messageReceiverEntity -> messageReceiverEntity.getReceiver().getId().equals(SecurityUtil.getCurrentUserId())).findFirst();
        if (first.isPresent()) {
            first.get().setIsRead(Boolean.TRUE);
            messageReceiveRepository.save(first.get());
        } else {
            if (!selfMessage) {
                throw new BusinessException("error.message.invalid.access");
            }
        }


        return MessageTransformer.entityToDto(entity, new MessageDto(), MessageEntity.ATTACHMENTS);
    }

    @Override
    public Long saveDraft(MessageDto dto) {
        dto.setReceivers(null);
        dto.setCcs(null);
        MessageEntity entity = MessageTransformer.dtoToEntity(dto, new MessageEntity());
        entity.setSender(UserTransformer.createEntityForRelation(SecurityUtil.getCurrentUserId()));
        entity.setIsSent(Boolean.FALSE);
        entity.setDeleted(Boolean.FALSE);
        return messageRepository.save(entity).getId();
    }

    @Override
    public Long send(MessageDto dto) {
        if (dto.getReceivers().isEmpty()) {
            throw new BusinessException("message.send.receiver.is.empty");
        }
        MessageEntity messageEntity = MessageTransformer.dtoToEntity(dto, new MessageEntity());
        messageEntity.setSender(UserTransformer.createEntityForRelation(SecurityUtil.getCurrentUserId()));
        messageEntity.setIsSent(Boolean.TRUE);
        messageEntity.setDeleted(Boolean.FALSE);
        messageEntity.getReceivers().forEach(mre -> {
            mre.setDeleted(Boolean.FALSE);
            mre.setIsRead(Boolean.FALSE);
        });
        return messageRepository.save(messageEntity).getId();
    }

    @Override
    public List<MessageDto> getInbox() {
        List<MessageDto> messageDtos = new ArrayList<>();
        List<MessageEntity> userMessages = messageRepository.findUserMessages(SecurityUtil.getCurrentUserId());
        userMessages.forEach(messageEntity ->
                messageDtos.add(MessageTransformer.entityToDto(messageEntity, new MessageDto()))
        );
        return messageDtos;
    }

    @Override
    public void update(MessageDto dto) {
        MessageEntity messageEntity = MessageTransformer.dtoToEntity(dto, messageRepository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.Message.not.found", dto.getId())));
        messageRepository.save(messageEntity);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public PagingResponse getInbox(PagingRequest pagingRequest) {
        pagingRequest.getFilters().add(new SearchCriteria("receivers.receiver.id", SecurityUtil.getCurrentUserId(), SearchOperation.EQUAL));
        if (pagingRequest.getSort() == null) {
            pagingRequest.setSort(new SortOperation());
        }
        pagingRequest.getSort().setSortBy("createdDate");
        pagingRequest.getSort().setDirection(Sort.Direction.DESC);
        return findPaging(pagingRequest);
    }

    @Override
    public PagingResponse getSentItems(PagingRequest pagingRequest) {
        pagingRequest.getFilters().add(new SearchCriteria("sender.id", SecurityUtil.getCurrentUserId(), SearchOperation.EQUAL));
        pagingRequest.getFilters().add(new SearchCriteria("isSent", true, SearchOperation.EQUAL));
        return findPaging(pagingRequest);
    }

    @Override
    public PagingResponse getDrafts(PagingRequest pagingRequest) {
        pagingRequest.getFilters().add(new SearchCriteria("sender.id", SecurityUtil.getCurrentUserId(), SearchOperation.EQUAL));
        pagingRequest.getFilters().add(new SearchCriteria("isSent", false, SearchOperation.EQUAL));
        return findPaging(pagingRequest);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        pagingRequest.getFilters().add(new SearchCriteria("deleted", false, SearchOperation.EQUAL));
        PagingExecutor<MessageEntity, Long> executor = new PagingExecutor<>(messageRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<MessageEntity> data = (List<MessageEntity>) pagingResponse.getData();
            List<MessageDto> output = new ArrayList<>();
            data.forEach(e -> output.add(MessageTransformer.entityToDto(e, new MessageDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }
}
