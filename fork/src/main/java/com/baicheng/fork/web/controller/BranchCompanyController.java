package com.baicheng.fork.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baicheng.domain.crm.meta.BranchCompany;
import com.baicheng.fork.core.util.JSONPUtils;
import com.baicheng.fork.web.dao.BranchCompanyMapper;
import com.baicheng.fork.web.dto.response.BaseResponseDTO;
import com.baicheng.fork.web.dto.response.BranchCompanyResponseDTO;

/**
 * 获取分公司接口
 * 
 * @author wsm 2018年4月2日下午4:25:24
 */
@Controller
@RequestMapping("/branchCompany")
public class BranchCompanyController {
	private static final Logger LOGGER = Logger.getLogger(BranchCompanyController.class);

	@Autowired
	private BranchCompanyMapper branchCompanyMapper;

	/**
	 * 获取分公司数据
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public void getBranchCompanyList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BranchCompanyResponseDTO responseDTO = new BranchCompanyResponseDTO();
		try {
			List<BranchCompany> branchCompanies = branchCompanyMapper.selectBranchCompanyList();
			responseDTO.setCode(BaseResponseDTO.SUCCESS);
			responseDTO.setBranchCompanies(branchCompanies);
		} catch (Exception e) {
			LOGGER.error("########## getBranchCompanyList 异常 " + e.getMessage(), e);
			responseDTO.setCode(BaseResponseDTO.FAILURE);
			responseDTO.setMessage("获取数据发生异常");
		}
		JSONPUtils.jsonp(responseDTO);
	}

}
