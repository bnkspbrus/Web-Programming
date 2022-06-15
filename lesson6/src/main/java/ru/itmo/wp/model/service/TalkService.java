package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;

import java.util.List;

public class TalkService {
    private final TalkRepositoryImpl talkRepositoryImpl = new TalkRepositoryImpl();
    public void save(Talk talk) {
        talkRepositoryImpl.save(talk);
    }
    public List<Talk> findAllByUserId(long userId) {
        return talkRepositoryImpl.findAllByUserId(userId);
    }
}
