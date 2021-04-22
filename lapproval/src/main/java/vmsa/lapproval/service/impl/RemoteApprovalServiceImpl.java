package vmsa.lapproval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vmsa.lapproval.service.RemoteApprovalService;
import vmsa.lapproval.vo.ApprovalVO;

@Service
public class RemoteApprovalServiceImpl implements RemoteApprovalService {


    public static final String URL = "http://localhost:8113/rapproval/";

//    @Autowired
    private RestTemplate restTemplate = new RestTemplate();;


    @Override
    public String sendApproval(ApprovalVO approvalVO) {
        return null;
    }

    @Override
    public String getApproval(String doc_no) {

        return restTemplate.getForObject(URL + doc_no, String.class);
    }
}
