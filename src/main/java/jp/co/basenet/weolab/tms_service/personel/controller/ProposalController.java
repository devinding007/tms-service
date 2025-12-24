package jp.co.basenet.weolab.tms_service.personel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.basenet.weolab.tms_service.personel.dto.ProposalAnalyseRequest;
import jp.co.basenet.weolab.tms_service.personel.dto.ProposalAnalyseResult;
import jp.co.basenet.weolab.tms_service.personel.service.AnalyseProposalService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProposalController {

    private final AnalyseProposalService service;

    @PostMapping("/proposal/analyse")
    public ResponseEntity<List<ProposalAnalyseResult>> receiveTalents(@RequestBody ProposalAnalyseRequest request) {


        List<ProposalAnalyseResult> response = service.analyse(request);
        return ResponseEntity.ok(response);
    }

}
