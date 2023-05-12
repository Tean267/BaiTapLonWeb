package com.Tean.ofM.Service;

import java.util.List;

import com.Tean.ofM.Model.Invoice;

public interface InvoiceService {
	Invoice save(Invoice invoice);
	
	List<Invoice> findAll();
	
	Invoice findById(Long id);
	
	List<Invoice> InvoiceOfMonth(int Month,int Year);
}
