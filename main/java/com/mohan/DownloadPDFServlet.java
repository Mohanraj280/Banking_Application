package com.mohan;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.mohan.CustomerService.CustomerService;
import com.mohan.CustomerServiceDAO.CustomerServiceDAO;
import com.mohan.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class DownloadPDFServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       
       
        String accountNumber = request.getParameter("accountNumber");
        if (accountNumber == null || accountNumber.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Account number is required");
            return;
        }

        CustomerServiceDAO dao = new CustomerServiceDAO();
        List<CustomerService> transactions = dao.getLast10Transactions(accountNumber);

     

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=transaction_history.pdf");

        Document document = new Document();
        try {
            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph title = new Paragraph("Last 10 Transactions");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            PdfPTable table = new PdfPTable(5); 
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            String[] headers = {"Transaction ID", "Account Number", "Actions", "Date", "Amount"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            for (CustomerService transaction : transactions) {
                table.addCell(transaction.getTransactionId());
                table.addCell(transaction.getAccountNumber());
                table.addCell(transaction.getActions());
                table.addCell(transaction.getTransactionDate().toString());
                table.addCell(transaction.getAmount().toString());
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }
}
