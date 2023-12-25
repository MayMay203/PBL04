package com.example.pbl04.service;


import com.example.pbl04.dao.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.pbl04.entity.*;
@Service
public class TopicServiceImple implements TopicService {
    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImple(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }
    public Chude getChuDeByTen(String tenchude) {return  topicRepository.getChuDeByTen(tenchude);}
    public void addChude(Chude chude) {topicRepository.save(chude);}
    public Integer getMaChuDeByTen(String tenchude){ return topicRepository.getChuDeByTen(tenchude).getId();}

    @Override
    public List<Chude> getAllTopics() {
       return topicRepository.getAllTopics();
    }

    @Override
    public Chude getTopicByID(Integer IdTop) {
        return topicRepository.getTopicByID(IdTop);
    }

}
