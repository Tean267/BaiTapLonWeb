package com.Tean.ofM.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tean.ofM.Model.Invoice;
import com.Tean.ofM.Model.Office;
import com.Tean.ofM.Repository.InvoiceRepository;
import com.Tean.ofM.Service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	@Autowired
	public InvoiceRepository invoiceRepository;
	@Override
	public Invoice save(Invoice invoice) {		
		return invoiceRepository.save(invoice);
	}

	@Override
	public List<Invoice> findAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice findById(Long id) {
		Optional<Invoice> invoice = invoiceRepository.findById(id);
		if(invoice.isPresent()){
			return invoice.get();
		}
		else {
			
		}
		return null;
	}

	@Override
	public List<Invoice> InvoiceOfMonth(int Month,int Year) {
		return invoiceRepository.InvoiceOfMonth(Month,Year);
	}




}
