package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.poi.ss.usermodel.*;

public class EmailSender {

    public static void main(String[] args) {
        // Email configuration
        String senderEmail = System.getenv("GMAIL"); // Add Your credentials (Gmail, password) as Environment  Variables and import them here
        String senderPassword = System.getenv("GMAIL_PASSWORD");

        // Email text
        String emailSubject = "Demande de stage en développement Java avec Spring Boot et pratiques DevOps"; //Email Subject
//        String emailBody = "Body of your email";

        // Path to your resume PDF
        String resumeFilePath = "C:\\Users\\ayoub\\Desktop\\CV.pdf"; //add Your CV path here

        // Excel sheet path
        String excelFilePath = "C:\\Users\\ayoub\\Desktop\\companiessheets.xlsx"; // add excel sheet path here
        // Read Excel sheet and send emails
        try {
            FileInputStream file = new FileInputStream(excelFilePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

//            Row row = sheet.getRow(1);

            for (Row row : sheet) {
                String city = row.getCell(2).getStringCellValue();
                String companyName = row.getCell(1).getStringCellValue();
                String recipientEmail = row.getCell(4).getStringCellValue();
                EmailBody emailBody = new EmailBody(companyName);

                if (city.equalsIgnoreCase("Rabat")) {
                    sendEmail(senderEmail, senderPassword, recipientEmail, emailSubject, emailBody.setBody(), resumeFilePath);
                    System.out.println("Email sent to " + companyName + " (" + recipientEmail + ")");
                }
            }

            workbook.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendEmail(String senderEmail, String senderPassword, String recipientEmail,  String subject, String body, String attachmentFilePath) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            multipart.addBodyPart(messageBodyPart);

            // Attach resume PDF
            if (attachmentFilePath != null && !attachmentFilePath.isEmpty()) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                FileDataSource source = new FileDataSource(attachmentFilePath);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(new File(attachmentFilePath).getName());
                multipart.addBodyPart(attachmentBodyPart);
            }

            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
