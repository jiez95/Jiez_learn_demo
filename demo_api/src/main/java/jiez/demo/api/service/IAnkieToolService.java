package jiez.demo.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface IAnkieToolService {


    String generateWeeklyPublication(MultipartFile file) throws Exception;
}
