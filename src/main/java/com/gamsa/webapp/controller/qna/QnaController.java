package com.gamsa.webapp.controller.qna;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.SecretKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gamsa.webapp.dao.NoticeDao;
import com.gamsa.webapp.dao.QnaDao;
import com.gamsa.webapp.dao.QnaReplyDao;
import com.gamsa.webapp.entity.Notice;
import com.gamsa.webapp.entity.Qna;
import com.gamsa.webapp.entity.QnaReply;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	@Autowired
	private QnaDao qnaDao;
	private QnaReplyDao qnaReplayDao;
	
	
	@RequestMapping("list")
	public String questionReg(
			@RequestParam(value="p", defaultValue="1") Integer page,
			@RequestParam(value="f", defaultValue="title") String field, //title을 기본값으로 검색하겠다
			@RequestParam(value="q", defaultValue="") String query,
			Model model) {
		model.addAttribute("list", qnaDao.getList(page, field, query));
		return "qna.question.list";
	}

	@RequestMapping(value="detail/{id}",method=RequestMethod.GET)
	public String noticeDetail(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("question", qnaDao.get(id));
		//model.addAttribute("answer", qnaReplayDao.getList(id));
		return "qna.question.detail";
	}
	
/*	@RequestMapping(value="detail/{id}",method=RequestMethod.POST)
	public String noticeDetail(@PathVariable("id") String id, Model model, 
			QnaReply qnaReply, HttpServletRequest request) throws UnsupportedEncodingException {
		
		model.addAttribute("question", qnaDao.get(id));
		//model.addAttribute("answer", qnaReplayDao.getList(id));
		//qnaReplayDao.insert(qnaReply);
		return "qna.question.detail";
	}*/
	
	@RequestMapping(value="reg", method=RequestMethod.GET)
	public String noticeReg() {
		
		return "qna.question.reg";
	}
	
	
	@RequestMapping(value="reg", method=RequestMethod.POST)
	public String noticeReg(
			Qna qna, HttpServletRequest request
			) throws UnsupportedEncodingException {
		qnaDao.insert(qna);
		
		return "redirect:../qna/list";
	}
	
	@RequestMapping(value="edit/{id}", method=RequestMethod.GET)
	public String noticeEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("question", qnaDao.get(id));
		return "qna.question.edit";
	}
	
	@RequestMapping(value="edit/{id}", method=RequestMethod.POST)
	public String noticeEdit(
			Qna qna, HttpServletRequest request
			) throws UnsupportedEncodingException {
		qnaDao.update(qna);
		
		return "redirect:../list";
	}
	
	@RequestMapping("delete/{id}")
	public String noticeDelete(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("question", qnaDao.delete(id));
		
		return "redirect:../list";
	}
	@RequestMapping(value="detail/regcomment", method=RequestMethod.POST)
	public Map<String, String> regInsert( @RequestParam(value = "comment") String comment) {
    	
    		
			return qnaReplayDao.insert(comment);;
    	        

    	        
	}
	



/*	@RequestMapping(value="detail/regcomment", method=RequestMethod.POST)
	public Map<String , String> Reply(HttpServletRequest request) {
	    //String title = request.getParameter("title");
	    String content = request.getParameter("content");    
	 
	    Map<String , String> Map = new HashMap<String, String>();
	    Map.put("key", "value");

	    return Map;
	}*/
	


	
}






















