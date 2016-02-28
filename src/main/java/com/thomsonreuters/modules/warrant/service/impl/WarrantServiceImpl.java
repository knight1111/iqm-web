package com.thomsonreuters.modules.warrant.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomsonreuters.modules.warrant.service.IWarrantService;

@Service("warrantService")
@Transactional(readOnly = true)
public class WarrantServiceImpl implements IWarrantService {

}
