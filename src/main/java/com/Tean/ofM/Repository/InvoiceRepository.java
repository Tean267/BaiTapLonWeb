package com.Tean.ofM.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Tean.ofM.Model.Invoice;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice,Long>{
	
		@Query(value = "select * from invoice WHERE MONTH(payment_date) = ?1 AND YEAR(payment_date) =?2", nativeQuery = true)
		List<Invoice> InvoiceOfMonth(int Month,int Year);
		
}
