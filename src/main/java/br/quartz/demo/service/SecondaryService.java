package br.quartz.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecondaryService {

    public void hello() { log.info("Secondary Job"); }
}
