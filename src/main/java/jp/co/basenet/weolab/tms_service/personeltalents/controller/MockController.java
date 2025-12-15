package jp.co.basenet.weolab.tms_service.personeltalents.controller;

import jp.co.basenet.weolab.tms_service.personeltalents.dto.SampleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MockController {

    @GetMapping("/api/mock-data")
    public List<SampleResponse> getMockData() {
        List<SampleResponse> list = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            SampleResponse item = new SampleResponse(
                    UUID.randomUUID().toString(),
                    "Sample Name " + i,
                    i * 10
            );
            list.add(item);
        }

        return list;
    }
}
