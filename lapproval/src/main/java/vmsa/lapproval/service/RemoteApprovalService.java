package vmsa.lapproval.service;

import vmsa.lapproval.vo.ApprovalVO;

public interface RemoteApprovalService {

    String sendApproval(ApprovalVO approvalVO);

    String getApproval(String doc_no);
}
