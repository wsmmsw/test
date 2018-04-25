package com.baicheng.fork.web.dto.response;

import java.util.List;

import com.baicheng.domain.crm.meta.BranchCompany;

/**
 * 分公司返回数据结构
 * 
 * @author wsm 2018年4月2日下午4:30:34
 */
@SuppressWarnings("serial")
public class BranchCompanyResponseDTO extends BaseResponseDTO {

	private List<BranchCompany> branchCompanies;

	public List<BranchCompany> getBranchCompanies() {
		return branchCompanies;
	}

	public void setBranchCompanies(List<BranchCompany> branchCompanies) {
		this.branchCompanies = branchCompanies;
	}
}
