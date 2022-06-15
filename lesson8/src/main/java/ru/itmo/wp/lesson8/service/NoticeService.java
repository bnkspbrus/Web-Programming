package ru.itmo.wp.lesson8.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.lesson8.domain.Notice;
import ru.itmo.wp.lesson8.repository.NoticeRepository;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public Notice findById(Long attribute) {
        return null;
    }

    public List<Notice> findAllByOrderByCreationTimeDesc() {
        return noticeRepository.findAllByOrderByCreationTimeDesc();
    }

    public void save(Notice notice) {
        noticeRepository.save(notice);
    }
}
