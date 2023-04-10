package com.techway.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techway.dto.RegistrationDTO;
import com.techway.entity.Account;
import com.techway.entity.Role;
import com.techway.repository.AccountRepository;
import com.techway.repository.RoleRepository;
import com.techway.service.IAccountService;

import net.bytebuddy.utility.RandomString;

@Service
public class AccountService implements IAccountService{

	@Autowired
    private AccountRepository accountRepository;
     
    @Autowired
    private PasswordEncoder passwordEncoder;
     
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    RoleRepository roleRepository;

    public void register(RegistrationDTO dto, String siteURL)
            throws UnsupportedEncodingException, MessagingException {
    	Account account = registrationDtoToEntity(dto);
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
         
        String randomCode = RandomString.make(50);
        account.setVerificationCode(randomCode);
        account.setEnabled(false);
         
        accountRepository.save(account);
         
        sendVerificationEmail(account, siteURL);
    }
    
    @Override
    public boolean verify(String verificationCode) {
        Account account = accountRepository.findByVerificationCode(verificationCode);
        
        if (account == null || account.isEnabled()) {
            return false;
        } else {
            account.setVerificationCode(null);
            account.setEnabled(true);
            accountRepository.save(account);             
            return true;
        }
         
    }
    
    @Override
    public String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        System.out.println(request.getServletPath());
        System.out.println(siteURL);
        System.out.println(siteURL.replace(request.getServletPath(), ""));
        return siteURL.replace(request.getServletPath(), "");
        
    }  
    
	private void sendVerificationEmail(Account account, String siteURL)
	        throws MessagingException, UnsupportedEncodingException {
	    String toAddress = account.getEmail();
	    String fromAddress = "techway.vn";
	    String senderName = "Techway";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<h3><a href=\"[[URL]]\">VERIFY</a></h3>"
	            + "Thank you,<br>"
	            + "Your company name.";
//	    target=\"_self\"
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", account.getFullname());
	    String verifyURL = siteURL + "/api/v1/accounts/verify?code=" + account.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}
	
	//trong trường hợp không nhớ mã xác thực,
	//resending mã xác thực
//    public void resendVerifyCode(RegistrationDTO dto)
//            throws UnsupportedEncodingException, MessagingException {
//    	Account account = registrationDtoToEntity(dto);
//    	Account savedAccount =  accountRepository.findByEmail(account.getEmail()).get();
//    	
//       if(savedAccount == null)
//    	   register(dto);
//       else {
//	       String randomCode = RandomString.make(50);
//	       savedAccount.setVerificationCode(randomCode);
//	       accountRepository.save(account);
//	       sendVerificationEmail(account);
//       }        
//    }

    
    
    private Account registrationDtoToEntity(RegistrationDTO dto) {
    	Set<Role> roles = new HashSet<>();
    	roles.add(roleRepository.findByName("CUSTOMER"));
    	Account account = new Account();
    	account.setEmail(dto.getEmail());
    	account.setPassword(dto.getPassword());
    	account.setFullname(dto.getFullname());
    	account.setPhoto(dto.getPhoto());
    	account.setEnabled(false);
    	account.setRoles(roles);
    	return account;
    }
    

}
