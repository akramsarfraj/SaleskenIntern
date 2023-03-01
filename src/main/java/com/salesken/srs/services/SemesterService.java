package com.salesken.srs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesken.srs.model.SemesterMark;
import com.salesken.srs.repository.SemesterMarkRepository;

@Service
public class SemesterService {

	@Autowired
	SemesterMarkRepository markrep;
	
	public SemesterMark addSemestermark(SemesterMark sm) {
		return markrep.save(sm);
	}
	
	public SemesterMark getSemestermark(int markid) {
		
		return markrep.findById(markid).get();
	}
}
